package Exo1BigInterger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class Key {
	BigInteger key;

	public Key(BigInteger b) {
		key=b;
	}

	public Key(Key k) {
		key=new BigInteger(k.key.toString());
	}

	public BigInteger getKey() {
		return key;
	}

	public void setKey(BigInteger b) {
		this.key=b;
	}

	public static boolean inf(Key key1, Key key2) {
		BigInteger keyRep1 = key1.getKey();
		BigInteger keyRep2 = key2.getKey();

		if(keyRep1.compareTo(keyRep2)<0) {
			return true;
		}
		return false;
	}

	public static boolean eg(Key key1, Key key2) {
		BigInteger keyRep1 = key1.getKey();
		BigInteger keyRep2 = key2.getKey();

		if(keyRep1.compareTo(keyRep2)==0)
		{
			return true;
		}

		return false;
	}

	public static ArrayList<Key> getKeysFromFile(String pathKeyFile) throws Exception {
		ArrayList<Key> result = new ArrayList<Key>();
		List<String> lines = Files.readAllLines(Paths.get(pathKeyFile),Charset.defaultCharset());

		for (String line : lines) {
			BigInteger key = new BigInteger(line.substring(2), 16);

			result.add(new Key(key));
		}

		return result;
	} 


	@Override
	public String toString() {
		return key.toString(16);
	}
	// on aimerait comparer les biginteger et non les classes
	
	@Override
	public boolean equals(Object obj) {
		Key objkey=(Key) obj;
		return objkey.key.toString().equals(this.key.toString());
	}

	/*------------------MD5------------------------*/
	//Lire le fichier et encoder chaque ligne par MD5, les convertir aux cles
	public static ArrayList<Key> getKeysFromFileMD5(String pathKeyFile) throws Exception {
		ArrayList<Key> result = new ArrayList<Key>();
		List<String> lines = Files.readAllLines(Paths.get(pathKeyFile),Charset.defaultCharset() );

		for (String line : lines) {
			String str=MD5.getInstance().getMD5(line);
			//stoccker les cles dans un tableau
			Key key= new Key(new BigInteger(str,16));
			result.add(key);
		}
		return result;
	}

	//filter le fichier pour que chaque mot n'appait qu'une seule fois
	public static void writeStringToFile(String filePath) throws IOException {  
		List<String> lines = Files.readAllLines(Paths.get(filePath),Charset.defaultCharset() );
		//liste de mots
		Set<String> set=new HashSet<String>();
		set.addAll(lines);
		try {  
			//stocker les r¨¦sultat dans un nouveau fichier
			File file =new File("E:\\eclipse\\java2018\\workspeace-java\\Projet\\Shakespeare\\test.txt");
			FileOutputStream fos = new FileOutputStream(file);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

			for (String line : set) {
				bw.write(line);
				bw.newLine();
			}
			bw.close();

		} catch (FileNotFoundException e) {   
			e.printStackTrace();  
		}  
	} 
	//Pour la question 6.13
	public static Map<String,Integer> fillMapMD5(String pathFile) throws IOException{
		List<String> lines = Files.readAllLines(Paths.get(pathFile),Charset.defaultCharset() );
		String[] array=new String[lines.size()];
		int k=0;
		Map<String,Integer> map = new HashMap<>(); 
		for (String line : lines) {
			//MD5
			String str=MD5.getInstance().getMD5(line);
			//stocker les string dans un array
			array[k]= str;

			//Pair<Integer, ArrayList<String>> couple ;
			//si une linge apparait plusieurs fois, on faire augmenter le cmpt
			if(map.containsKey(str)) {
				map.put(str, map.get(str)+1);	
			}
			else {
				map.put(str, 1);	
			}
			k++;
		}
		return map;
	}

	//Afficher les Map de MD5
	public static void displayMapMD5(Map<String,Integer> md5Map) {
		for (Map.Entry<String, Integer> entry  : md5Map.entrySet()) {
			System.out.println(entry.getKey()+ " : " + entry.getValue());
		}
	}

	//verifier les collisions
	public static void displayCollisionMapMD5(Map<String,Integer> md5Map) {
		for (Map.Entry<String, Integer> entry  : md5Map.entrySet()) {
			if(entry.getValue()>1) {
				System.out.println(entry.getKey()+ " : " + entry.getValue());
			}
		}
	}

	// Lire tous les fichiers dans la repetoire
	public static ArrayList<String> getAllFilesFromPath(String  path) {
		System.out.println(path);
		File folder = new File(path);
		ArrayList<String> result= new ArrayList<>();
		for(File fileEntry : folder.listFiles()) {
			System.out.println(fileEntry.getName());
			result.add(fileEntry.getName());
		}
		return result;
	}

}