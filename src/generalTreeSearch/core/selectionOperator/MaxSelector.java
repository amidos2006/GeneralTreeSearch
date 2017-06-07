package generalTreeSearch.core.selectionOperator;

public class MaxSelector implements Selector{
    @Override
    public int selectIndex(double[] values) {
	int index = 0;
	for(int i=1; i<values.length; i++){
	    if(values[index] < values[i]){
		index = i;
	    }
	}
	return index;
    }

}
