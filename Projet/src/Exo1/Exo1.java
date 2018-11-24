package Exo1;

import java.util.ArrayList;

public class Exo1 {

	public static void main(String[] args) {
		System.out.println("Working Directory = " +
	              System.getProperty("user.dir"));
		String pathKeyFile=System.getProperty("user.dir")+"\\cles_alea\\";
		String pathKeyFile1 =pathKeyFile+ "yueyi_test.txt";
		String pathKeyFile2 =pathKeyFile+ "yueyi_test2.txt";

		ArrayList<Key> keys2 = new ArrayList<Key>();		
		ArrayList<Key> keys = new ArrayList<Key>();
		try {
			keys=Key.getKeysFromFile(pathKeyFile1);
			keys2=Key.getKeysFromFile(pathKeyFile2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//Key.displayArrayOfKeys(keys); 
		//comparer les deux cles 
		Key.inf(keys2.get(0), keys.get(1));
		Key.eg(keys2.get(0), keys.get(1));
		
		//declarer un nouveau TasMin
		int size=keys.size();
		int parentIndex=0;
		int childIndex=0;
		TasMinTableau2 t=new TasMinTableau2(size,parentIndex,childIndex); 
		
		//consIter 
		t=TasMinTableau2.constIter(keys);
		Key.displayArrayOfKeys(t.getTasMin());
		t.supprimerMin();
		t.supprimerMin();
		Key.displayArrayOfKeys(t.getTasMin());
		
		//union
		t.setTasMin(TasMinTableau2.union(keys, keys2));
		
		//ajout
		String var="0x00000000000000000000000000000007";
		int[] newkey = new int[4]; 
		//convertir le string a Int pour ajouter
		newkey=Key.convertingLineToArrayInt(var);
		Key newKey=new Key(newkey);
		t.ajout(newKey);
	}

}
