package generalTreeSearch.core.selectionOperator;

public enum Selectors {
    max("MaxSelector"),
    min("MinSelector"),
    rank("RankSelector"),
    rouletteWheel("RouletteWheelSelector");
    
    private String name;
    private Selectors(String name){
	this.name = name;
    }
    
    public String toString(){
	return this.name;
    }
}
