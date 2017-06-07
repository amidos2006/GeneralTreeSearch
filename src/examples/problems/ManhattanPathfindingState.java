package examples.problems;

import generalTreeSearch.core.equationTree.EquationParser;
import generalTreeSearch.core.gameTree.GameNode;
import generalTreeSearch.core.state.CustomState;
import generalTreeSearch.core.state.StateInterface;

public class ManhattanPathfindingState extends CustomState{
    private int startX;
    private int startY;
    private int endX;
    private int endY;
    
    private int x;
    private int y;
    
    public ManhattanPathfindingState(String equation, int startX, int startY, int endX, int endY) throws Exception{
	super(EquationParser.getInstance(null).parse(equation));
	
	this.startX = startX;
	this.startY = startY;
	this.endX = endX;
	this.endY = endY;
	
	this.x = startX;
	this.y = startY;
    }

    @Override
    public StateInterface cloneState(){
	ManhattanPathfindingState p = null;
	try{
	    p = new ManhattanPathfindingState(this.equation.toString(), startX, startY, endX, endY);
	    p.x = x;
	    p.y = y;
	}
	catch(Exception e){
	    e.printStackTrace();
	}
	return p;
    }

    @Override
    public void advance(int actionNum) {
	this.x += (actionNum>=2?1:0)*(2 * (actionNum % 2) - 1);
	this.y += (actionNum<2?1:0)*(2 * (actionNum % 2) - 1);
    }

    @Override
    public int getChildrenNum() {
	return 4;
    }

    @Override
    public int isTerminal() {
	return (this.x == this.endX && this.y == this.endY)?1:0;
    }

    @Override
    public double evaluateState(GameNode node) {
        try {
	    node.setVariable("x", x);
	    node.setVariable("y", y);
	    node.setVariable("endX", endX);
	    node.setVariable("endY", endY);
	} catch (Exception e) {
	    e.printStackTrace();
	}
        double value= super.evaluateState(node);
        try {
	    node.deleteVariable("x");
	    node.deleteVariable("y");
	    node.deleteVariable("endX");
	    node.deleteVariable("endY");
	} catch (Exception e) {
	    e.printStackTrace();
	}
        
        return value;
    }
    
    @Override
    public String toString() {
        return "(" + this.x + "," + this.y + ")";
    }
}
