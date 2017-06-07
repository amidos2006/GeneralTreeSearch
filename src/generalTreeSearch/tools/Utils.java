package generalTreeSearch.tools;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

public class Utils {
    public static int[] getSortedIndeces(double[] values){
	HashMap<Integer, Double> map = new HashMap<Integer, Double>();
	for(int i=0; i<values.length; i++){
	    map.put(i, values[i]);
	}
	LinkedList list = new LinkedList(map.entrySet());
	Collections.sort(list, new Comparator() {
	    public int compare(Object o1, Object o2) {
		return ((Comparable) ((Map.Entry) (o1)).getValue())
	                  .compareTo(((Map.Entry) (o2)).getValue());}
	});
	int[] indeces = new int[values.length];
	int i=0;
	for (Iterator it = list.iterator(); it.hasNext();) {
	    indeces[i] = (int) ((Map.Entry)it.next()).getValue();
	    i++;
	}
	return indeces;
    }
}
