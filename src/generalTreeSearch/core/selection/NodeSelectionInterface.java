package generalTreeSearch.core.selection;

import generalTreeSearch.core.gameTree.GameNode;

public interface NodeSelectionInterface {
    public double getNodeSelectionValue(GameNode node);
}
