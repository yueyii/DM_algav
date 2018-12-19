package Exo1BigInterger;

import java.util.ArrayList;

public class ComplexiteTasMin {
	
	public static long consIter(ArrayList<Key> liste) {
		int size=liste.size();
		long startTime = System.nanoTime(); 
		TasMinTableauKey.consIter(liste,size);
		long endTime = System.nanoTime();
		return endTime - startTime;
	}
	
	public static long union(ArrayList<Key> array1,ArrayList<Key> array2) {
		long startTime = System.nanoTime(); 
		TasMinTableauKey.Union(array1,array2);
		long endTime = System.nanoTime();
		return endTime - startTime;
	}
	
}
