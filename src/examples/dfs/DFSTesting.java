package examples.dfs;

import java.util.Random;

import generalTreeSearch.GTS;
import generalTreeSearch.core.state.StateInterface;
import examples.problems.ManhattanPathfindingState;

public class DFSTesting {
    public static void main(String[] args) throws Exception{
	StateInterface start = new ManhattanPathfindingState("isTerminal", 0, 0, 3, 3);
	GTS search = new GTS(new Random(), start);
	search.setPartialExpansion("1");
	search.setNodeSelection("priority");
	search.setNodeExpansion("0");
	search.setSimulation("0", "0");
	search.setBackprop(new String[]{"priority=sum(mul(sub(1,isTerminal),tanh(depth)),mul(1000,simValue))"}, 
		new String[]{"priority=sum(max(0,sub(totalChildren, totalExpandedChildren)),max(c_priority))"});
	for(int i=0; i<100; i++){
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
