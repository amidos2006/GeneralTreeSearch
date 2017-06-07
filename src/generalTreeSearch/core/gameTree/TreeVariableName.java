package generalTreeSearch.core.gameTree;

public enum TreeVariableName {
    numVisits("numVisits"),
    depth("depth"),
    isTerminal("isTerminal"),
    stateEvaluation("stateEvaluation"),
    totalChildren("totalChildren"),
    totalExpandedChildren("totalExpandedChildren"),
    totalSibling("totalSibling"),
    actionNumber("actionNum"),
    isRoot("isRoot"),
    isLeaf("isLeaf");
    
    private final String name;
    
    private TreeVariableName(String name){
	this.name = name;
    }
    
    public String toString(){
	return this.name;
    }
}
