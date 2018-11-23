package Exo1;

import java.util.ArrayList;

public class Exo1 {
	//lecture de fichier
	public static void main(String[] args) {
		System.out.println("Working Directory = " +
	              System.getProperty("user.dir"));
		String pathKeyFile=System.getProperty("user.dir")+"\\cles_alea\\";
		pathKeyFile =pathKeyFile+ "yueyi_test.txt";
		System.out.println(pathKeyFile);
		ArrayList<Key> keys = new ArrayList<Key>();
		try {
			keys=Key.getKeysFromFile(pathKeyFile);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Key.displayArrayOfKeys(keys);
		
		
		System.out.println(Key.eg(keys.get(2),keys.get(1)));
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
