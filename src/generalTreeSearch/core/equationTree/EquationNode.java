package generalTreeSearch.core.equationTree;

import generalTreeSearch.core.gameTree.GameNode;

public abstract class EquationNode {
    public EquationNode(){
    }
    
    public abstract double evaluate(GameNode node);
    
    public abstract String toString();
}
