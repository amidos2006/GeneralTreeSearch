package generalTreeSearch.core.state;

import generalTreeSearch.core.gameTree.GameNode;

public interface StateInterface {
    public StateInterface cloneState();
    public void advance(int actionNum);
    public double evaluateState(GameNode node);
    public int getChildrenNum();
    public int isTerminal();
}
