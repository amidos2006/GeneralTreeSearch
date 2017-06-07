package generalTreeSearch.core.equationTree.terminals.array;

import generalTreeSearch.core.equationTree.terminals.TerminalNode;

public abstract class ArrayOperator extends TerminalNode {
    public static final int SIBLING = 0;
    public static final int CHILDREN = 1;
    
    protected int type;
    protected String variableName;
    
    public ArrayOperator(String variableName) {
	super();
	
	this.type = CHILDREN;
	if(variableName.startsWith("s_")){
	    this.type = SIBLING;
	}
	this.variableName = variableName.split("_")[1];
    }
}
