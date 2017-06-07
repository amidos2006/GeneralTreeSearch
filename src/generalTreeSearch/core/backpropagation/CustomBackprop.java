package generalTreeSearch.core.backpropagation;

import generalTreeSearch.core.equationTree.EquationNode;
import generalTreeSearch.core.gameTree.GameNode;

public class CustomBackprop implements BackpropInterface{
    private String[] names;
    private EquationNode[] equations;
    
    public CustomBackprop(String[] names, EquationNode[] eqs){
	this.names = names;
	this.equations = eqs;
    }
    
    @Override
    public void updateTreeNodes(GameNode node, double simValue) throws Exception {
	node.setVariable("simValue", simValue);
	for(int i=0; i<names.length; i++){
	    node.setVariable(names[i], this.equations[i].evaluate(node));
	}
	node.deleteVariable("simValue");
    }

}
