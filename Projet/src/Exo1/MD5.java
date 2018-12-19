package Exo1BigInterger;

import java.math.BigInteger;

public class MD5{
	//4 constante s 
	private final int A=0x67452301;
	private final int B=0xefcdab89;
	private final int C=0x98badcfe;
	private final int D=0x10325476;

	//4 temp pour stocker les valeurs de ABCD
	private int Atemp,Btemp,Ctemp,Dtemp;


	//tableau s
	private final int s[]={7,12,17,22,7,12,17,22,7,12,17,22,7,
			12,17,22,5,9,14,20,5,9,14,20,5,9,14,20,5,9,14,20,
			4,11,16,23,4,11,16,23,4,11,16,23,4,11,16,23,6,10,
			15,21,6,10,15,21,6,10,15,21,6,10,15,21};
	/*
	 Constante ti, trouver par formule:
	 floor(abs(sin(i+1))¡Á(2pow32)
	 */
	double[] K = new double[64];
	String[] T=new String[64];
	Integer[] TT=new Integer[64];

	//tableau T[64]
	void setT() {
		for(int i=0; i<64;++i) {
			K[i]=Math.floor(Math.abs(Math.sin(i+1))*Math.pow(2,32));
			T[i]=Long.toHexString((long)K[i]);
			BigInteger conv=new BigInteger(T[i],16);
			TT[i]=conv.intValue();
			//System.out.println("i"+i+" : "+I[i]);
		} 
	}

	String[] getT() {
		return T;
	}

	//initialser les valeurs
	private void init(){
		setT();
		Atemp=A;
		Btemp=B;
		Ctemp=C;
		Dtemp=D;

	}
	
	/*1) Remplissange de donn¨¦es
	 * Pour avoir les bits = 448 (mod 512)
	 * Remplir 1 d'abord reste ajouter 0
	 * Ajouter 64bits (la longueur de message)
	 * */
		private int[] add(String str){
			int num=((str.length()+8)/64)+1;//512bits en total,64bits par group
			int strByte[]=new int[num*16];//64/4=16£¬16 group
			
			for(int i=0;i<num*16;i++){//initialiser les chiffre aux 0
				strByte[i]=0;
			}
			int i;
			for(i=0;i<str.length();i++){
				strByte[i>>2] = strByte[i>>2] | str.charAt(i)<<((i%4)*8);//4 bits par Int
			}
			strByte[i>>2] = strByte[i>>2] | 0x80<<((i%4)*8);//ajouter 1 a la fin
			
			//remplir le string 
			strByte[num*16-2]=str.length()*8;
			return strByte;
		}
	//la boucle pricipale
	private void MainLoop(int M[]){
		int Fun,g;
		int a=Atemp;
		int b=Btemp;
		int c=Ctemp;
		int d=Dtemp;
		for(int i = 0; i < 64; i ++){
			if(i<16){
				Fun=(b&c)|((~b)&d);
				g=i;
				
			}else if(i<32){
				Fun=(d&b)|((~d)&c);
				g=(5*i+1)%16;
				
			}else if(i<48){
				Fun=b^c^d;
				g=(3*i+5)%16;
			
			}else{
				Fun=c^(b|(~d));
				g=(7*i)%16;
			}
			int tmp=d;
			d=c;
			c=b;
			b=b+shift((int)(a+Fun+this.TT[i]+M[g]),s[i]);
			a=tmp;
		}
		//add this chunk's has to result so far:
		Atemp=a+Atemp;
		Btemp=b+Btemp;
		Ctemp=c+Ctemp;
		Dtemp=d+Dtemp;

	}
	
	//leftrotate function definition
	private int shift(int a,int s){
		return (a<<s)|(a>>>(32-s));//Quand on declare a droit, remplir 0 pour bits moins que32
	}
	
	
	
	//appeler le fonction, le programme principal
	public String getMD5(String source){
		init();
		int strByte[]=add(source);
		for(int i=0;i<strByte.length/16;i++){
			int num[]=new int[16];
			for(int j=0;j<16;j++){
				num[j]=strByte[i*16+j];
			}
			MainLoop(num);
		}
		return changeHex(Atemp)+changeHex(Btemp)+changeHex(Ctemp)+changeHex(Dtemp);

	}

	/*convertir int a hexadecimal
	 *du fin au d¨¦but
	 * */
	private String changeHex(int a){
		String str="";
		for(int i=0;i<4;i++){
			str+=String.format("%2s", Integer.toHexString(((a>>i*8)%(1<<8))&0xff)).replace(' ', '0');

		}
		return str;
	}


	//d¨¦clarer une nouvelle instance
	private static MD5 instance;
	public static MD5 getInstance(){
		if(instance==null){
			instance=new MD5();
		}
		return instance;
	}



	public static void main(String[] args){
		String str=MD5.getInstance().getMD5("hello");
		System.out.println(str);
	}
}