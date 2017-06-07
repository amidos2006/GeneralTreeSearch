package generalTreeSearch.core.selectionOperator;

import java.util.Random;

public class RouletteWheelSelector implements Selector{
    private Random rnd;
    
    public RouletteWheelSelector(Random rnd){
	this.rnd = rnd;
    }
    
    @Override
    public int selectIndex(double[] values) {
	double[] cdf = new double[values.length];
	cdf[0] = values[0];
	for(int i=1; i<cdf.length; i++){
	    cdf[i] = cdf[i-1] + values[i];
	}
	double randValue = this.rnd.nextDouble() * (cdf[cdf.length-1] - cdf[0]) + cdf[0];
	for(int i=0; i<cdf.length; i++){
	    if(randValue <= cdf[i]){
		return i;
	    }
	}
	return cdf.length - 1;
    }

}
