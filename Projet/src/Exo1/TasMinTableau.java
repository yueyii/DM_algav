package Exo1;

import java.util.ArrayList;

public class TasMinTableau {
	int[] key;
	int size;
	int parentIndex;//le noeud parent
	int childIndex;//le noeud fils

	public  TasMinTableau(int size, int parentIndex,int childIndex) {
		this.size=size;
		this.parentIndex=parentIndex;
		this.childIndex=childIndex;
	};

	//Ranger TasMin en descendant
	void DownAdd (ArrayList<Key> arrayNew,int parentIndex) {
		//System.out.println("parentindexInit"+arrayNew.get(parentIndex));
		Key tmp=arrayNew.get(parentIndex);
		childIndex=2*parentIndex+1;
		while(childIndex<arrayNew.size()) {
			//si le fils droit est plus petit que fils gauche
			/*if(childIndex+1<size && (arrayNew.get(childIndex+1)<arrayNew.get(childIndex))) {
				childIndex++;}*/
			if(childIndex+1<arrayNew.size() && 
					(Key.inf(arrayNew.get(childIndex+1),arrayNew.get(childIndex))==true)) {
				childIndex++;;
			}
			//si le parent est plus petit que fils, on sort
			/*if(tmp<=arrayNew.get(childIndex)){
				break;
			}*/
			if(Key.inf(tmp,arrayNew.get(childIndex))
					|| Key.eg(tmp,arrayNew.get(childIndex))){
				break;

			}
			//echange le parent et le fils
			arrayNew.set(parentIndex, arrayNew.get(childIndex));
			parentIndex=childIndex;
			childIndex=2*childIndex+1;
		}
		arrayNew.set(parentIndex,tmp);
	}

	void consIter(ArrayList<Key> arrayNew,int size) {
		// commence par le permier parentIndex
		size=arrayNew.size();
		int parentIndex=(size-1)/2;
		long cmp=0;
		//long startTime = System.nanoTime(); 
		//long startTime =System.currentTimeMillis(); 
		for(int i=parentIndex; i>=0; i--) {
			DownAdd(arrayNew,i);
			cmp++;
		}
		//Key.displayArrayOfKeys(arrayNew);
		//System.out.println("compteur:" + cmp);
		//long endTime = System.nanoTime(); 
		//long endTime=System.currentTimeMillis(); 
		//System.out.println("temps d'execution: " + (endTime - startTime) + "NanoSecond"); 
		//System.out.println("temps d'execution en moy: " + ((endTime - startTime)/cmp) + "NanoSecond");
	}

	/*static TasMinTableau consIter(ArrayList<Integer> arrayOfInteger) {
		TasMinTableau result = new TasMinTableau(arrayOfInteger.size(),0,0);
		int parentIndex=(arrayOfInteger.size()/2)-1;
		for(int i=parentIndex; i>=0; i--) {
			result.DownAdd(arrayOfInteger,i,arrayOfInteger.size());
		}
		System.out.println(arrayOfInteger);
		return result;

	}*/

	//Ajout un ¨¦l¨¦ment dans TasMin
	boolean Add(ArrayList<Key> arrayNew, Key var) {
		//int size=arrayNew.size();
		consIter(arrayNew,size);
		for (int i = 0; i < arrayNew.size(); i++) {
			if(Key.eg(var, arrayNew.get(i))) {
				//comment convertir var ¨¤ string ???
				System.out.println("variable "+var+" has existed already");
				return false;
			}
		}
		arrayNew.add(var);
		size++;
		consIter(arrayNew,size);
		return true;
	}

	//supprimer un ¨¦l¨¦ment dans TasMin
	void RemoveMin(ArrayList<Key> arrayNew) {
		Key a = arrayNew.get(0);
		//¨¦change le premier ¨¦l¨¦ment et le dernier ¨¦l¨¦ment
		arrayNew.set(0, arrayNew.get(arrayNew.size() - 1));
		arrayNew.set(arrayNew.size() - 1, a);
		System.out.println(arrayNew);
		//supprimer le plus petit ¨¦l¨¦ment
		arrayNew.remove(arrayNew.size() - 1);
		size=arrayNew.size();
		consIter(arrayNew,size);
	}

	//Union deux Tasmin
	void Union(ArrayList<Key> arrayNew,ArrayList<Key> arrayNew2) {
		/*arrayNew2.add(6);
		arrayNew2.add(13);
		arrayNew2.add(11);
		arrayNew2.add(15);
		arrayNew2.add(10);*/
		int cmp=0;
		long startTime = System.currentTimeMillis(); 
		for(int i=0; i<arrayNew2.size();i++)
		{
			Add(arrayNew,arrayNew2.get(i));
			cmp++;
		}
		System.out.println("Result of TasMin after the Union");
		long endTime = System.currentTimeMillis(); 
		System.out.println("compteur:" + cmp);
		System.out.println("temps d'execution: " + (endTime - startTime) + "NanoSecond"); 
		System.out.println("temps d'execution en moy: " + ((endTime - startTime)/cmp) + "NanoSecond");
	
	}

	/*public static void main(String[] args) {
		ArrayList<Integer> array=new ArrayList<>();
		array.add(7);
		array.add(1);
		array.add(3);
		array.add(10);
		array.add(5);
		array.add(2);
		array.add(8);
		array.add(9);
		array.add(6);
		int size=array.size();
		int parentIndex=0;
		int childIndex=0;
		TasMinTableau t=new TasMinTableau(size,parentIndex,childIndex); 
		t.BuildTasMin(array,size);
		t.Add(array, 4);
		t.RemoveMin(array);
		t.Union(array);
		//consIter(array);
	}*/
}
