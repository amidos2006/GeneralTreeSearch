package examples.astar;

import java.util.Random;

import generalTreeSearch.GTS;
import generalTreeSearch.core.state.StateInterface;
import examples.problems.ManhattanPathfindingState;

public class AStarTesting {
    public static void main(String[] args) throws Exception{
	StateInterface start = new ManhattanPathfindingState("sub(1, tanh(mul(0.1,sum(abs(sub(x,endX)),abs(sub(y,endY))))))", 0, 0, 10, 10);
	GTS search = new GTS(new Random(), start);
	search.setPartialExpansion("1");
	search.setNodeSelection("priority");
	search.setNodeExpansion("0");
	search.setSimulation("0", "0");
	search.setBackprop(new String[]{"priority=simValue"}, 
		new String[]{"priority=max(c_priority)"});
	for(int i=0; i<200; i++){
	    search.updateStep();
	}
	System.out.println(search.toString());
	
	Integer[] bestPath = search.getBestPath();
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
