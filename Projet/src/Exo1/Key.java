package Exo1;

import java.math.BigInteger;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Key {


	//une cl¨¦ est repr¨¦sent par 4 entiers
	//ici, le bit au poid faible se trouve? l'index 0
	int[] key;
	public static final int SIZE=4;
	public static final int LINE_SIZE=32;
	public static final int INT_HEX_REP_SIZE=8;
	public static final String ZERO_X="0x";

	//on v¨¦ifie qu'on a bien 4 int;
	public Key(int[] key) {
		this.key = new int[4];
		if(isRightSize(key)) {
			this.key=key;
		}

	}


	public int[] getKey() {
		return key;
	}


	public void setKey(int[] key) {
		this.key = new int[4];
		if(isRightSize(key)) {
			this.key=key;
		}
	}

	boolean isRightSize(int[] key) {
		return key.length == SIZE;
	}

	public static boolean inf(Key key1, Key key2) {

		int[] keyRep1 = key1.getKey();
		int[] keyRep2 = key2.getKey();
		int index;
		//int fromLastIndex;
		for(index=0; index < SIZE;index++) {
			//fromLastIndex=(SIZE-1)-index;
			if(keyRep1[index] < keyRep2[index] ) {
				return true;
			}
		}
		return false;
	}

	public static boolean eg(Key key1, Key key2) {

		
		int[] keyRep1 = key1.getKey();
		int[] keyRep2 = key2.getKey();
		int index;
		//int fromLastIndex;
		for(index=0; index < SIZE;index++) {
			//fromLastIndex=(SIZE-1)-index;
			if(keyRep1[index] != keyRep2[index] ) {
				return false;
			}
		}
		return true;
	}
	
	//remplir les zeros pour les cles moins de 32 caractere
	static String fillLineOfzero( String line) {
		String result=line;
		//System.out.println(result);
		int missed=LINE_SIZE-line.length();
		for (int i = 0; i < missed; i++) {
			result='0'+result;
		}
		//System.out.println(result);
		return result;
	} 
	
	public static ArrayList<Key> getKeysFromFile(String pathKeyFile) throws Exception {
		ArrayList<Key> result = new ArrayList<Key>();
		List<String> lines = Files.readAllLines(Paths.get(pathKeyFile),Charset.defaultCharset() );

		for (String line : lines) {
			int[] key = new int[4];
			key=convertingLineToArrayInt(line);
			
			result.add(new Key(key));
		}
		return result;
	}


	//on utilise biginteger pour convertir  les 8 characteres HEX en INT ( on note que le bigint est bien 32 bits)
	public static int parseFrom8CharHex(String hexas){
		BigInteger bi= new BigInteger(hexas,16);
		return  bi.intValue();  

	}
	
	public static int[] convertingLineToArrayInt(String line) {

		int deb;
		int fin;
		int[] result = new int[4];
		int index;
		String hexaLine=line.substring(ZERO_X.length());

		//on compare bien si la ligne repr¨¦sente bien 128 bits, sans le "0x"
	
		
		//prends les premier 32 caracteres, sauf le "0x"
		if(hexaLine.length() < LINE_SIZE)
		{
			hexaLine=fillLineOfzero(hexaLine);
			//System.out.println(hexaLine.length());
		}
		for(index=0; index < SIZE;index++) {
			deb=index*INT_HEX_REP_SIZE;
			fin=deb+INT_HEX_REP_SIZE;
			//fromLastIndex=(SIZE-1)-index;

			result[index]=parseFrom8CharHex(hexaLine.substring(deb,fin));
			
		}
		return result;
	};
	
	public void showIntsOfKey() {
		StringBuilder sb= new StringBuilder();
		for(int index = 0 ; index < SIZE; index ++) {
			sb.append("--index-> "+index+ ":"+key[index] +" \n");
			
		} 
		System.out.println(sb.toString());
	}
	
	public static void displayArrayOfKeys(ArrayList<Key>  keys) {
		int index= 0;
		for (Key key : keys) {
			System.out.println("key n"+ index);
			key.showIntsOfKey();
			index++;
		}		
	}



}
