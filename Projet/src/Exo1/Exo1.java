package Exo1;

import java.util.ArrayList;

public class Exo1 {

	public static void main(String[] args) {
		System.out.println("Working Directory = " +
	              System.getProperty("user.dir"));
		String pathKeyFile=System.getProperty("user.dir")+"\\cles_alea\\";
		pathKeyFile =pathKeyFile+ "jeu_1_nb_cles_50000.txt";
		System.out.println(pathKeyFile);
		ArrayList<Key> keys = new ArrayList<Key>();
		try {
			keys=Key.getKeysFromFile(pathKeyFile);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Key.displayArrayOfKeys(keys); 
		//comparer les deux cles 
		
		//declarer un nouveau TasMin
		int size=keys.size();
		int parentIndex=0;
		int childIndex=0;
		TasMinTableau t=new TasMinTableau(size,parentIndex,childIndex); 
		
		//consIter 
		t.consIter(keys,0);
		
		//convertir le string a Int pour ajouter
		String var="0x00000000000000000000000000000007";
		int[] newkey = new int[4]; 
		newkey=Key.convertingLineToArrayInt(var);
		Key newKey=new Key(newkey);
		t.Add(keys, newKey);
		
		//suppMin
		t.RemoveMin(keys);
		
		//Union de deux TasMin
		System.out.println("Working Directory = " +
	              System.getProperty("user.dir"));
		String pathKeyFile2=System.getProperty("user.dir")+"\\cles_alea\\";
		pathKeyFile2 =pathKeyFile2+ "jeu_2_nb_cles_50000.txt";
		System.out.println(pathKeyFile2);
		
		ArrayList<Key> keys2 = new ArrayList<Key>();
		try {
			keys2=Key.getKeysFromFile(pathKeyFile2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		t.Union(keys,keys2);
		Key.displayArrayOfKeys(keys); 
		
		/*tas min arbre*/
		/*
		TreeTM quatre=new TreeTM(4, null, null);
		TreeTM cinq=new TreeTM(5, null, null);
		TreeTM trois=new TreeTM(3, cinq, null);
		TreeTM deux=new TreeTM(2, trois, quatre);
		
		TreeTM.levelOrder(deux);
		 */
		
		
	}

}
