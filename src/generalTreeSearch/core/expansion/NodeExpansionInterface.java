package generalTreeSearch.core.expansion;

import generalTreeSearch.core.gameTree.GameNode;

public interface NodeExpansionInterface {
    public double getNodeExpansionValue(GameNode node, int expandedAction);
}
