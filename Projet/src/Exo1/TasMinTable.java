package Exo1;

import java.util.ArrayList;

public class TasMinTable {
	int size;
	int parentIndex;//le noeud parent
	int childIndex;//le noeud fils

	public  TasMinTable(int size, int parentIndex,int childIndex) {
		this.size=size;
		this.parentIndex=parentIndex;
		this.childIndex=childIndex;
	};

	//Ranger TasMin en descendant
	void DownAdd (ArrayList<Integer> arrayNew,int parentIndex,int size) {
		int tmp=arrayNew.get(parentIndex);
		childIndex=2*parentIndex+1;
		while(childIndex<size) {
			//si le fils droit est plus petit que fils gauche
			if(childIndex+1<size && (arrayNew.get(childIndex+1)<arrayNew.get(childIndex))) {
				childIndex++;}
			//si le parent est plus petit que fils, on sort
			if(tmp<=arrayNew.get(childIndex)){
				break;
			}
			//echange le parent et le fils
			arrayNew.set(parentIndex, arrayNew.get(childIndex));
			parentIndex=childIndex;
			childIndex=2*childIndex+1;
		}
		arrayNew.set(parentIndex,tmp);
	}

	void BuildTasMin(ArrayList<Integer> arrayNew,int size)
	{	// commence par le permier parentIndex 
		//size=arrayNew.size();
		int parentIndex=(size/2)-1;
		for(int i=parentIndex; i>=0; i--) {
			DownAdd(arrayNew,i,size);
		}
		System.out.println(arrayNew);
	}
	
	static TasMinTable constIter(ArrayList<Integer> arrayOfInteger) {
		TasMinTable result = new TasMinTable(arrayOfInteger.size(),0,0);
		int parentIndex=(arrayOfInteger.size()/2)-1;
		for(int i=parentIndex; i>=0; i--) {
			result.DownAdd(arrayOfInteger,i,arrayOfInteger.size());
		}
		System.out.println(arrayOfInteger);
		return result;

	}

	//Ajout un ¨¦l¨¦ment dans TasMin
	void Add(ArrayList<Integer> arrayNew, int var) {
		//int size=arrayNew.size();
		if(!arrayNew.contains(var)){
			arrayNew.add(var);
			size++;
			BuildTasMin(arrayNew,size);
		}
		else {
			System.out.println("variable "+var+" has existed already");
		}
	}

	//supprimer un ¨¦l¨¦ment dans TasMin
	void RemoveMin(ArrayList<Integer> arrayNew) {
		int a = arrayNew.get(0);
		//¨¦change le premier ¨¦l¨¦ment et le dernier ¨¦l¨¦ment
		arrayNew.set(0, arrayNew.get(arrayNew.size() - 1));
		arrayNew.set(arrayNew.size() - 1, a);
		System.out.println(arrayNew);
		//supprimer le plus petit ¨¦l¨¦ment
		arrayNew.remove(arrayNew.size() - 1);
		BuildTasMin(arrayNew,size);
	}



	//Union deux Tasmin
	void Union(ArrayList<Integer> arrayNew) {
		ArrayList<Integer> arrayNew2=new ArrayList<>();
		arrayNew2.add(6);
		arrayNew2.add(13);
		arrayNew2.add(11);
		arrayNew2.add(15);
		arrayNew2.add(10);
		int size=arrayNew2.size();

		for(int i=0; i<size;i++)
		{
			Add(arrayNew, arrayNew2.get(i));
		}
	}

	public static void main(String[] args) {
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
		TasMinTable t=new TasMinTable(size,parentIndex,childIndex); 
		//t.BuildTasMin(array,size);
		//t.Add(array, 4);
		//t.RemoveMin(array);
		//t.Union(array);
		constIter(array);
	}
}
