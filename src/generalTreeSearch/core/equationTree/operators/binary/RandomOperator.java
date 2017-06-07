package generalTreeSearch.core.equationTree.operators.binary;

import java.util.Random;

import generalTreeSearch.core.equationTree.EquationNode;
import generalTreeSearch.core.gameTree.GameNode;

public class RandomOperator extends BinaryOperator {
    public final static int INTEGER = 0;
    public final static int FLOAT = 1;
    
    private Random random;
    private int type;
    private EquationNode left;
    private EquationNode right;
    
    public RandomOperator(Random random, int type, EquationNode left, EquationNode right) {
	super(left, right);
	
	this.random = random;
	this.type = type;
	this.left = left;
	this.right = right;
    }

    @Override
    public double evaluate(GameNode node) {
	double highBound = right.evaluate(node);
	double lowBound = left.evaluate(node);
	if(type == RandomOperator.INTEGER){
	    return random.nextInt((int)(highBound - lowBound)) + lowBound;
	}
	return random.nextDouble() * (highBound - lowBound) + lowBound;
    }

    @Override
    public String toString() {
	String stringType = "randFloat";
	if(type == RandomOperator.INTEGER){
	    stringType = "randInt";
	}
	return stringType + "(" + left.toString() + "," + right.toString() + ")";
    }

}
