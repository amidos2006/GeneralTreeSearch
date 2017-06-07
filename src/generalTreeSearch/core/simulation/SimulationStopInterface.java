package generalTreeSearch.core.simulation;

import generalTreeSearch.core.gameTree.GameNode;

public interface SimulationStopInterface {
    public double getStopProbability(GameNode node, int currentIteration, double simValue);
}
