package generalTreeSearch.core.equationTree.terminals;

import generalTreeSearch.core.gameTree.GameNode;

public class ConstantNode extends TerminalNode {
    private double value;
    
    public ConstantNode(double value) {
	super();
	this.value = value;
    }

    @Override
    public double evaluate(GameNode node) {
	return value;
    }

    @Override
    public String toString() {
	return Double.toString(this.value);
    }

}
