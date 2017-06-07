package generalTreeSearch.core.backpropagation;

import generalTreeSearch.core.gameTree.GameNode;

public interface BackpropInterface {
    public void updateTreeNodes(GameNode node, double simValue) throws Exception;
}
