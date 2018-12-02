package Exo1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class TestMD5 {

	public static void main(String[] args) throws IOException {
		System.out.println("Working Directory = " +
				System.getProperty("user.dir"));
		String pathKeyFile=System.getProperty("user.dir")+"\\Shakespeare\\";
		String pathKeyFile1 =pathKeyFile+ "test.txt";

		ArrayList<Key> keys = new ArrayList<Key>();		

		try {	
			//--------Question 6.12-------------------------
			//keys=Key.getKeysFromFileMD5(pathKeyFile1);
			//Key.writeStringToFile(pathKeyFile1);
			
			//--------Question 6.13-------------------------
			Map<String,Integer> map=Key.fillMapMD5(pathKeyFile1);
			Key.displayCollisionMapMD5(map);
		}

		catch (Exception e) {
			e.printStackTrace();
		}

		//-------Quetion 6.12----------
		Bst bst= new Bst();
		//bst=Bst.consIter(keys);
		//parcourt prefixe
		//bst.preOrderRecursion();
	}
}
