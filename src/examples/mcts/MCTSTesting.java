package examples.mcts;

import java.util.Random;

import generalTreeSearch.GTS;
import generalTreeSearch.core.selectionOperator.Selectors;
import generalTreeSearch.core.state.StateInterface;
import examples.problems.ManhattanPathfindingState;

public class MCTSTesting {
    public static void main(String[] args) throws Exception{
	StateInterface start = new ManhattanPathfindingState("sub(1, tanh(mul(0.1,sum(abs(sub(x,endX)),abs(sub(y,endY))))))", 0, 0, 10, 10);
	GTS search = new GTS(new Random(), start);
	search.setPartialExpansion("1");
	search.setNodeSelection("sum(divide(totalValue, numVisits), pow(mul(2,divide(ln(numVisits),p_numVisits)),0.5))");
	search.setNodeExpansion("0");
	search.setSimulation("mul(sub(1,isTerminal), sub(5,currentIteration))", "pow(simValue,2)");
	search.setBackprop(new String[]{"totalValue=sum(1000*isTerminal,simValue)"}, 
		new String[]{"totalValue=sum(totalValue,simValue)"});
	for(int i=0; i<1000; i++){
	    search.updateStep();
	}
	System.out.println(search.toString());
	
	Integer[] bestPath = search.getBestPath("divide(totalValue,numVisits)", Selectors.max);
	StateInterface temp = (ManhattanPathfindingState) start.cloneState();
	String locations = "";
	for(int i=0; i<bestPath.length; i++){
	    locations += temp.toString();
	    temp.advance(bestPath[i]);
	}
	locations += temp.toString();
	System.out.println("Best Path: " + locations);
    }
}
