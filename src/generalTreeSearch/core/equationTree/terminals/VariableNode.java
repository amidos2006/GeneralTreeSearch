package generalTreeSearch.core.equationTree.terminals;

import generalTreeSearch.core.gameTree.GameNode;

public class VariableNode extends TerminalNode {
    private String variableName;
    
    public VariableNode(String variableName) {
	super();
	this.variableName = variableName;
    }

    @Override
    public double evaluate(GameNode node) {
	if(variableName.startsWith("p_")){
	    return node.getParentVariable(this.variableName.substring(2));
	}
	return node.getVariable(this.variableName);
    }

    @Override
    public String toString() {
	return this.variableName;
    }
}
