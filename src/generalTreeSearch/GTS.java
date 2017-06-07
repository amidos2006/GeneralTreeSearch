package generalTreeSearch;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Random;

import generalTreeSearch.core.backpropagation.*;
import generalTreeSearch.core.equationTree.*;
import generalTreeSearch.core.expansion.*;
import generalTreeSearch.core.gameTree.*;
import generalTreeSearch.core.partialExpansion.*;
import generalTreeSearch.core.selection.*;
import generalTreeSearch.core.selectionOperator.*;
import generalTreeSearch.core.simulation.*;
import generalTreeSearch.core.state.*;

public class GTS {
    private Random rnd;
    private GameNode root;
    
    private PartialExpansionInterface partialExpansion;
    
    private NodeSelectionInterface nodeSelection;
    private Selector nodeSelectionSelector;
    
    private NodeExpansionInterface nodeExpansion;
    private Selector nodeExpansionSelector;
    
    private SimulationStopInterface simulationStop;
    private SimulationSelectionInterface simulationSelection;
    private Selector simulationSelectionSelector;
    
    private BackpropInterface noChildrenBackprop;
    private BackpropInterface childrenBackprop;
    
    public GTS(Random rnd, StateInterface rootState){
	this.rnd = rnd;
	this.root = new GameNode(null, -1, rootState);
	
	this.nodeSelectionSelector = new MaxSelector();
	this.nodeExpansionSelector = new MaxSelector();
	this.simulationSelectionSelector = new RouletteWheelSelector(this.rnd);
    }
    
    public void setPartialExpansion(String equation) throws Exception{
	this.partialExpansion = new CustomPartialExpansion(EquationParser.getInstance(this.rnd).parse(equation));
    }
    
    public void setNodeSelection(String equation) throws Exception{
	this.setNodeSelection(equation, null);
    }
    
    public void setNodeSelection(String equation, Selectors selector) throws Exception{
	this.nodeSelection = new CustomSelection(EquationParser.getInstance(this.rnd).parse(equation));
	if(selector != null){
	    Class<?> c = Class.forName("generalTreeSearch.core.selectionOperator." + selector.toString());
	    Constructor<?> constructor = c.getConstructor();
	    this.nodeSelectionSelector = (Selector)constructor.newInstance();
	}
    }
    
    public void setNodeExpansion(String equation) throws Exception{
	this.setNodeExpansion(equation, null);
    }
    
    public void setNodeExpansion(String equation, Selectors selector) throws Exception{
	this.nodeExpansion = new CustomExpansion(EquationParser.getInstance(this.rnd).parse(equation));
	if(selector != null){
	    Class<?> c = Class.forName("generalTreeSearch.core.selectionOperator." + selector.toString());
	    Constructor<?> constructor = c.getConstructor();
	    this.nodeSelectionSelector = (Selector)constructor.newInstance();
	}
    }
    
    public void setSimulation(String stop, String selection) throws Exception{
	this.setSimulation(stop, selection, null);
    }
    
    public void setSimulation(String stop, String selection, Selectors selector) throws Exception{
	this.simulationStop = new CustomSimStop(EquationParser.getInstance(this.rnd).parse(stop));
	this.simulationSelection = new CustomSimSelection(EquationParser.getInstance(this.rnd).parse(selection));
	if(selector != null){
	    Class<?> c = Class.forName("generalTreeSearch.core.selectionOperator." + selector.toString());
	    Constructor<?> constructor = c.getConstructor();
	    this.nodeSelectionSelector = (Selector)constructor.newInstance();
	}
    }
    
    public void setBackprop(String[] noChildren, String[] children) throws Exception{
	String[] names = new String[noChildren.length];
	EquationNode[] eqs = new EquationNode[noChildren.length];
	for(int i=0; i<names.length; i++){
	    String[] parts = noChildren[i].split("=");
	    names[i] = parts[0].trim();
	    eqs[i] = EquationParser.getInstance(this.rnd).parse(parts[1]);
	}
	this.noChildrenBackprop = new CustomBackprop(names, eqs);
	
	names = new String[children.length];
	eqs = new EquationNode[children.length];
	for(int i=0; i<names.length; i++){
	    String[] parts = children[i].split("=");
	    names[i] = parts[0].trim();
	    eqs[i] = EquationParser.getInstance(this.rnd).parse(parts[1]);
	}
	this.childrenBackprop = new CustomBackprop(names, eqs);
    }
    
    private GameNode selection(GameNode node, NodeSelectionInterface nodeSelection, Selector selector){
	GameNode[] children = node.getExpandedChildren();
	double[] values = new double[children.length];
	for(int i=0; i<children.length; i++){
	    values[i] = nodeSelection.getNodeSelectionValue(children[i]);
	}
	
	return children[selector.selectIndex(values)];
    }
    
    private int expansion(GameNode node, NodeExpansionInterface nodeExpansion, Selector selector){
	Integer[] indeces = node.getUnexpandedIndeces();
	double[] values = new double[indeces.length];
	for(int i=0; i<indeces.length; i++){
	    values[i] = nodeExpansion.getNodeExpansionValue(node, indeces[i]);
	}
	
	return indeces[selector.selectIndex(values)];
    }
    
    private double simulation(GameNode node){
	StateInterface state = node.getState().cloneState();
	int currentIteration = 0;
	while(this.rnd.nextDouble() < this.simulationStop.getStopProbability(node, currentIteration, state.evaluateState(node))){
	    double[] values = new double[(int) node.getVariable(TreeVariableName.totalChildren.toString())];
	    for(int i=0; i<values.length; i++){
		StateInterface child = state.cloneState();
		child.advance(i);
		values[i] = this.simulationSelection.getActionValue(node, i, currentIteration, child.evaluateState(node));
	    }
	    state.advance(this.simulationSelectionSelector.selectIndex(values));
	    currentIteration += 1;
	}
	return state.evaluateState(node);
    }
    
    private void backpropagation(GameNode node, double simValue) throws Exception{
	while(node != null){
	    if(node.getVariable(TreeVariableName.totalExpandedChildren.toString()) == 0){
		this.noChildrenBackprop.updateTreeNodes(node, simValue);
	    }
	    else{
		this.childrenBackprop.updateTreeNodes(node, simValue);
	    }
	    node.incrementNumVisits();
	    node = node.getParent();
	}
    }
    
    public void updateStep() throws Exception{
	GameNode currentNode = this.root;
	boolean expand = false;
	
	if(currentNode.getVariable(TreeVariableName.totalExpandedChildren.toString()) <= 0){
	    this.backpropagation(currentNode, currentNode.getState().evaluateState(this.root));
	}
	
	while(!expand){
            if(currentNode.getVariable(TreeVariableName.totalExpandedChildren.toString()) == 0){
        	expand = true;
            }
            else if(currentNode.getVariable(TreeVariableName.totalExpandedChildren.toString()) < 
        	    currentNode.getVariable(TreeVariableName.totalChildren.toString())){
        	if(this.rnd.nextDouble() < this.partialExpansion.getExpansionProbability(currentNode)){
        	    expand = true;
        	}
            }
            if(!expand){
        	currentNode = this.selection(currentNode, this.nodeSelection, this.nodeSelectionSelector);
            }
	}
	
	if(currentNode.getVariable(TreeVariableName.isTerminal.toString()) == 0){
	    int action = this.expansion(currentNode, this.nodeExpansion, this.nodeExpansionSelector);
	    currentNode = currentNode.addChild(action);
	}
	
	double simValue = this.simulation(currentNode);
	this.backpropagation(currentNode, simValue);
    }
    
    public Integer[] getBestPath() throws Exception{
	return getBestPath(null, null);
    }
    
    public Integer[] getBestPath(String equation, Selectors selector) throws Exception{
	NodeSelectionInterface selection = this.nodeSelection;
	if(equation != null && equation.trim().length() > 0){
	    selection = new CustomSelection(EquationParser.getInstance(this.rnd).parse(equation));;
	}
	Selector sel = this.nodeSelectionSelector;
	if(selector != null){
	    Class<?> c = Class.forName("generalTreeSearch.core.selectionOperator." + selector.toString());
	    Constructor<?> constructor = c.getConstructor();
	    sel = (Selector)constructor.newInstance();
	}
	
	GameNode node = this.root;
	ArrayList<Integer> path = new ArrayList<Integer>();
	while(node.getVariable(TreeVariableName.totalExpandedChildren.toString()) > 0){
	    node = this.selection(node, selection, sel);
	    path.add((int) node.getVariable(TreeVariableName.actionNumber.toString()));
	}
	
	return path.toArray(new Integer[path.size()]);
    }
    
    @Override
    public String toString() {
	return this.root.toString();
    }
}