package generalTreeSearch.core.simulation;

import generalTreeSearch.core.gameTree.GameNode;

public interface SimulationSelectionInterface {
    public double getActionValue(GameNode node, int nextAction, int currentIteration, double simValue);
}
