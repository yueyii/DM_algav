package Exo1BigInterger;

import java.math.BigInteger;
import java.util.ArrayList;

public class Test {

	public static void main(String[] args) {
		System.out.println("Working Directory = " +
				System.getProperty("user.dir"));
		String pathKeyFile=System.getProperty("user.dir")+"\\cles_alea\\";
		String pathKeyFile1 =pathKeyFile+ "test.txt";
		String pathKeyFile2 =pathKeyFile+ "jeu_2_nb_cles_100.txt";

		ArrayList<Key> keys = new ArrayList<Key>();		
		ArrayList<Key> keys2 = new ArrayList<Key>();
		try {
			keys=Key.getKeysFromFile(pathKeyFile1);
			 keys2=Key.getKeysFromFile(pathKeyFile2);
		} catch (Exception e) {
			e.printStackTrace();
		}

		/*//---------Exo1-Comparer deux cles------------
		System.out.println(Key.inf(keys.get(2), keys.get(3)));
		System.out.println(Key.eg(keys.get(0), keys2.get(0)));
		*/

		/*//---------Exo2-TasMinTableauKey-----------------
		//declarer un nouveau TasMin
		int size=keys.size();
		int parentIndex=0;
		int childIndex=size-1;
		TasMinTableauKey t=new TasMinTableauKey(size,parentIndex,childIndex); 

		System.out.println(" ----1) consIter----");
		t=TasMinTableauKey.consIter(keys,size);
		t.display();
		
		System.out.println(" ---2) Ajout----");	
		String var="00000000000000000000000000000012"; 
		BigInteger bi= new BigInteger(var,16);
		Key key= new Key(bi);
		t=TasMinTableauKey.consIter(keys,size);
		t.Ajout(key);
		t.display();
		
		System.out.println(" ----3) SuppMin----"); 
		t=TasMinTableauKey.consIter(keys,size);
		t.SupprMin();
		t.display();
		
		System.out.println(" ----4) Union-----");
		t=TasMinTableauKey.Union(keys, keys2);
		t.display();
		*/
		
		/*//---------Exo2.5-Calcul le temps d'¨¦xectuion de TasMinTableauKey-----------------
		double res = 0;
		for(double i=0; i<1000; i++) {
			res=res*i/(i+1);
			res += ComplexiteTasMin.consIter(keys)/(i+1);
		}
		System.out.println("le temps d'exection en moyenne en microsecond:");
		System.out.println(res/1000000);
		*/
		/*double res = 0;
		String[] nb_cles = {"100", "1000", "10000", "200", "20000", "500", "5000", "50000"};
		String pathKeyFile=System.getProperty("user.dir")+"\\"+"cles_alea"+"\\";
		int k=0;
		for(String n : nb_cles) {
			System.out.println("----------------------------");
			System.out.println("--Fichiers avec "+n+" cl¨¦s--");
			for(double i=1; i<5; i++) {
				String pathKeyFile1 = pathKeyFile+ "jeu_"+(int)i+"_nb_cles_"+n+".txt";
				try {
					keys=Key.getKeysFromFile(pathKeyFile1);
				} catch (Exception e) {
					e.printStackTrace();
				}
				res=(res*k)/(k+1);
				res += ComplexiteTasMin.consIter(keys)/(k+1);
				k++;
			}
			System.out.println(res/1000000);
		}*/
		/*double res = 0;
		String[] nb_cles = {"100", "1000", "10000", "200", "20000", "500", "5000", "50000"};
		
		for(String n : nb_cles) {
			System.out.println("----------------------------");
			System.out.println("--Fichiers avec "+n+" cl¨¦s--");
			for(double i=1; i<5; i++) {
				for(double j=i+1; j<5; j++) {
					String pathKeyFile=System.getProperty("user.dir")+"\\"+"cles_alea"+"\\";
					String pathKeyFile1 = pathKeyFile+ "jeu_"+(int)i+"_nb_cles_"+n+".txt";
					String pathKeyFile2 = pathKeyFile+ "jeu_"+(int)j+"_nb_cles_"+n+".txt";
					try {
						keys=Key.getKeysFromFile(pathKeyFile1);
						keys2=Key.getKeysFromFile(pathKeyFile2);
					} catch (Exception e) {
						e.printStackTrace();
					}
					res=(res*i)/(i+1);
					res += (ComplexiteTasMin.consIter(keys)/(i+1));
				}
			}
			System.out.println(res/1000000);
		}*/

		/*//-------Exo5-Abre binaire de recherche-----------
		Bst bst= new Bst();

		System.out.println(" ----1) consIter----");
		bst=Bst.consIter(keys);
		bst.displayBst();
		//System.out.println(Bst.consIter(keys));
	
		System.out.println(" ----2) parcourt pr¨¦fixe----");
		bst.preOrderRecursion();
		bst.displayBst();
		
		System.out.println(" ----3) recherche un ¨¦l¨¦ment dans l'arbre----");
		String var="000000000000000000000000000007";
		BigInteger bi= new BigInteger(var,16);
		Key key= new Key(bi);
		bst.research(key);
	}*/
	}
}
