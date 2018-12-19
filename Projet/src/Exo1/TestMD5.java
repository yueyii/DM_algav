package Exo1BigInterger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class TestMD5 {

	public static void main(String[] args) throws IOException {
		System.out.println("Working Directory = " +
				System.getProperty("user.dir"));
		String pathKeyFile=System.getProperty("user.dir")+"\\Shakespeare\\";
		String pathKeyFile1 =pathKeyFile+ "1henryiv.txt";

		ArrayList<Key> keys = new ArrayList<Key>();		
		ArrayList<String> mots= new ArrayList<>();
		
		try {	
			//--------Question 6.12-------------------------
			keys=Key.getKeysFromFileMD5(pathKeyFile1);
			
			/*//pour les mots n'apparaient qu'une seule fois
			Key.writeStringToFile(pathKeyFile1);	
			
			//lire tous les fichier dans la repertoire
			ArrayList<Key> keys2 = new ArrayList<Key>();	
			mots=Key. getAllFilesFromPath(pathKeyFile);
			for (String file : mots) {
				keys2=Key.getKeysFromFileMD5(pathKeyFile+file);	
			}
		*/
			
			/*//--------Question 6.13-------------------------
			Map<String,Integer> map=Key.fillMapMD5(pathKeyFile1);
			Key.displayCollisionMapMD5(map);*/
		}

		catch (Exception e) {
			e.printStackTrace();
		}

		//-------Quetion 6.12----------
		Bst bst= new Bst();
		bst=Bst.consIter(keys);
		bst.displayBst();
		
		//parcourt prefixe
		bst.preOrderRecursion();
	}
}

