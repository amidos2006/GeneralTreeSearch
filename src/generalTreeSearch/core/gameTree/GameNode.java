package generalTreeSearch.core.gameTree;

import java.util.ArrayList;
import java.util.HashMap;

import generalTreeSearch.core.state.StateInterface;

public class GameNode {
    private StateInterface state;
    private GameNode[] children;
    private GameNode parent;
    
    private HashMap<String, Double> variables;
    
    public GameNode(GameNode parent, int actionNum, StateInterface state){
	this.parent = parent;
	this.state = state.cloneState();
	this.children = new GameNode[state.getChildrenNum()];
	this.variables = new HashMap<String, Double>();
	for(TreeVariableName n:TreeVariableName.values()){
	    this.variables.put(n.toString(), 0.0);
	}
	this.variables.put(TreeVariableName.totalChildren.toString(), (double) this.children.length);
	if(this.parent != null){
	    this.variables.put(TreeVariableName.actionNumber.toString(), (double) actionNum);
	    this.variables.put(TreeVariableName.depth.toString(), this.getParentVariable(TreeVariableName.depth.toString()) + 1);
	    this.variables.put(TreeVariableName.isTerminal.toString(), (double) this.state.isTerminal());
	}
	else{
	    this.variables.put(TreeVariableName.isRoot.toString(), 1.0);
	}
    }

    public GameNode[] getExpandedChildren(){
	ArrayList<GameNode> nodes = new ArrayList<GameNode>();
	for(int i=0; i<this.children.length; i++){
	    if(this.children[i] != null){
		nodes.add(this.children[i]);
	    }
	}
	return nodes.toArray(new GameNode[nodes.size()]);
    }
    
    public Integer[] getUnexpandedIndeces(){
	ArrayList<Integer> indeces = new ArrayList<Integer>();
	for(int i=0; i<this.children.length; i++){
	    if(this.children[i] == null){
		indeces.add(i);
	    }
	}
	return indeces.toArray(new Integer[indeces.size()]);
    }
    
    public StateInterface getState(){
	return this.state;
    }
    
    public GameNode getParent(){
	return this.parent;
    }
    
    public GameNode addChild(int index){
	StateInterface child = state.cloneState();
	child.advance(index);
	this.children[index] = new GameNode(this, index, child);
	return this.children[index];
    }
    
    public void incrementNumVisits(){
	this.variables.put(TreeVariableName.numVisits.toString(), this.variables.get(TreeVariableName.numVisits.toString()) + 1);
    }
    
    public void setVariable(String name, double value) throws Exception{
	for(TreeVariableName n:TreeVariableName.values()){
	    if(name.equals(n.toString())){
		throw new Exception("Can't update a system variable");
	    }
	}
	this.variables.put(name, value);
    }
    
    public void deleteVariable(String name) throws Exception{
	for(TreeVariableName n:TreeVariableName.values()){
	    if(name.equals(n.toString())){
		throw new Exception("Can't delete a system variable");
	    }
	}
	this.variables.remove(name);
    }
    
    public double getVariable(String name){
	if(name.equals(TreeVariableName.totalExpandedChildren.toString())){
	    this.variables.put(TreeVariableName.totalExpandedChildren.toString(), (double) this.getExpandedChildren().length);
	}
	if(name.equals(TreeVariableName.stateEvaluation.toString())){
	    this.variables.put(TreeVariableName.stateEvaluation.toString(), this.state.evaluateState(this));
	}
	if(name.equals(TreeVariableName.totalSibling.toString())){
	    if(this.parent != null){
		this.variables.put(TreeVariableName.totalSibling.toString(), (double) this.parent.getExpandedChildren().length);
	    }
	    else{
		return 1;
	    }
	}
	if(name.equals(TreeVariableName.isLeaf.toString())){
	    this.variables.put(TreeVariableName.isLeaf.toString(), this.getExpandedChildren().length==0?1.0:0.0);
	}
	if(!this.variables.containsKey(name)){
	    return 0.0;
	}
	
	return this.variables.get(name);
    }
    
    public double[] getChildVariable(String name){
	GameNode[] children = this.getExpandedChildren();
	double[] values = new double[children.length];
	for(int i=0; i<children.length; i++){
	    values[i] = children[i].getVariable(name);
	}
	return values;
    }
    
    public double getParentVariable(String name){
	if(this.parent != null){
	    return this.parent.getVariable(name);
	}
	else{
	    return 0;
	}
    }
    
    public double[] getSibilingVariable(String name){
	return this.parent.getChildVariable(name);
    }
    
    @Override
    public String toString() {
	String result = "";
	for(int i=0; i<this.variables.get(TreeVariableName.depth.toString()); i++){
	    result += "  ";
	}
	this.getVariable(TreeVariableName.totalExpandedChildren.toString());
	this.getVariable(TreeVariableName.stateEvaluation.toString());
	this.getVariable(TreeVariableName.isLeaf.toString());
	this.getVariable(TreeVariableName.totalSibling.toString());
	result += this.state.toString() + "[" + this.variables.toString() + "]";
	for(GameNode n:this.getExpandedChildren()){
	    result += "\n" + n.toString();
	}
        return result;
    }
}
