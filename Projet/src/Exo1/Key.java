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
		for(index=0; index < SIZE;index++) {
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
		for(index=0; index < SIZE;index++) {
			if(keyRep1[index] != keyRep2[index] ) {
				return false;
			}
		}
		return true;
	}
	
	//pour les 31 ou 30 caracteres, on les remplit les '0' devant 
	static String fillLineOfzero( String line) {
		String result=line;
		int missed=LINE_SIZE-line.length();
		for (int i = 0; i < missed; i++) {
			result='0'+result;
		}
		return result;
	} 
	
	//lire le ficher de jeu_nb_cle
	public static ArrayList<Key> getKeysFromFile(String pathKeyFile) throws Exception {
		ArrayList<Key> result = new ArrayList<Key>();
		List<String> lines = Files.readAllLines(Paths.get(pathKeyFile),Charset.defaultCharset() );
		String hexaLine;
		int index;		
		for (String line : lines) {
			//prends les premier 32 caracteres, sauf le "0x"
			hexaLine=line.substring(ZERO_X.length());
			if(hexaLine.length() < LINE_SIZE)
			{
				hexaLine=fillLineOfzero(hexaLine);
				System.out.println(hexaLine.length());
			}
			int deb;
			int fin;
			int[] key = new int[4];
			for(index=0; index < SIZE;index++) {
				deb=index*INT_HEX_REP_SIZE;
				fin=deb+INT_HEX_REP_SIZE;
				key[index]=parseFrom8CharHex(hexaLine.substring(deb,fin));
			}
			//ajouter les resultat dans le tableau de cle
			result.add(new Key(key));
		}
		return result;
	}

	//on utilise biginteger pour convertir  les 8 characteres HEX en INT ( on note que le bigint est bien 32 bits)
	public static int parseFrom8CharHex(String hexas){
		BigInteger bi= new BigInteger(hexas,16);
		return  bi.intValue();  
	}

	//afficher  le tableau de cles en 32bits
	public void showIntsOfKey() {
		StringBuilder sb= new StringBuilder();
		for(int index = 0 ; index < SIZE; index ++) {
			sb.append("--index-> "+index+ ":"+key[index] +" \n");			
		} 
		System.out.println(sb.toString());
	}

	//afficher numero de cle
	public static void displayArrayOfKeys(ArrayList<Key>  keys) {
		int index= 0;
		for (Key key : keys) {
			System.out.println("key n¡ã"+ index);
			key.showIntsOfKey();
			index++;
		}		
	}
}
