package generalTreeSearch.core.selectionOperator;

import java.util.Random;

import generalTreeSearch.tools.Utils;

public class RankSelector implements Selector{
    private Random rnd;
    
    RankSelector(Random rnd){
	this.rnd = rnd;
    }
    
    @Override
    public int selectIndex(double[] values) {
	int[] ranks = Utils.getSortedIndeces(values);
	for(int i=0; i<ranks.length; i++){
	    ranks[i] += 1;
	}
	double[] cdf = new double[values.length];
	cdf[0] = ranks[0];
	for(int i=1; i<cdf.length; i++){
	    cdf[i] = cdf[i-1] + ranks[i];
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
