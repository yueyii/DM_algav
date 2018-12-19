package Exo1BigInterger;

import java.util.ArrayList;

public class TasMinTableauSimple {
	int[] key;
	int size;
	int parentIndex;//le noeud parent
	int childIndex;//le noeud fils
	static int cmp=0;
	ArrayList<Integer> tasMin;

	public  TasMinTableauSimple(int size, int parentIndex,int childIndex) {
		this.size=size;
		this.parentIndex=parentIndex;
		this.childIndex=childIndex;
		tasMin= new ArrayList<>();
	};

	public ArrayList<Integer> getTasMin() {
		return tasMin;
	}

	public void setTasMin(ArrayList<Integer> tasMin) {
		this.tasMin = tasMin;
	}
	 
	//Ranger tasMin en montant
	public void percolateUp(int key) {
		//tmp pour stocker element parent
		int tmp = 0;
		size=tasMin.size();
		//si la taille est 1, retourne le tasMin
		if(size==1) return ;
		else{//on initie le parentIndex en positif  et le childIndex au dernier element du tab
			childIndex=size-1;
			parentIndex=1;
			//on verifie si le child element a toujours un parent
			while(parentIndex > 0) {	
				//si child est a gauche
				if(childIndex%2==1){			
					parentIndex=(childIndex-1)/2;
					tmp=tasMin.get(parentIndex);
				}
				//si chile est a droit
				if(childIndex%2==0){
					parentIndex=(childIndex-2)/2;
					tmp=tasMin.get(parentIndex);
				}
				//comparer le child est parent, si le parent est plus petit, retourne
				if(tmp<tasMin.get(childIndex)
						|| tmp==tasMin.get(childIndex)) {
					return;
				}
				//echange le parent et le fils
				tasMin.set(parentIndex, tasMin.get(childIndex));
				tasMin.set(childIndex,tmp);
				childIndex=parentIndex;
			}
		}
	}
	
	//Ranger TasMin en descendant
	public void percolateDown (ArrayList<Integer> arrayNew,int parentIndex) {
		//donner la valuer arrayNew au tasMin
		setTasMin(arrayNew);
		int tmp=tasMin.get(parentIndex);
		childIndex=(2*parentIndex)+1;
		
		while(childIndex<tasMin.size()) {
			//si le fils droite est plus petit que fils gauche, on pointe au fils droite
			if((childIndex+1<tasMin.size())&& 
					((tasMin.get(childIndex+1))<(tasMin.get(childIndex)))) {
				childIndex++;
			}

			//si le parent est plus petit que fils, on sort
			if(tmp<=tasMin.get(childIndex)){
				return;
			}
			
			//echange le parent et le fils
			tasMin.set(parentIndex, tasMin.get(childIndex));
			tasMin.set(childIndex,tmp);	
			//pointer au fils comme un nouveau parent
			parentIndex=childIndex;
			childIndex=2*childIndex+1;
		}
	}

	//consIter 
	public static TasMinTableauSimple consIter(ArrayList<Integer> arrayNew,int size) {
		// commence par le permier parentIndex
		TasMinTableauSimple result =new TasMinTableauSimple(0, 0, 0);
		size=arrayNew.size();
		int parentIndex=(size-1)/2;
		long startTime = System.nanoTime(); 
		for(int i=parentIndex; i>=0; i--) {
			result.percolateDown(arrayNew,i);
		}
		long endTime = System.nanoTime(); 
		System.out.println("temps d'execution: " + (endTime - startTime) + "NanoSecond"); 
		return result;
	}
	
	//Algorithme naif
	public static TasMinTableauSimple consIter2(ArrayList<Integer> arrayNew,int size) {
		TasMinTableauSimple result =new TasMinTableauSimple(0, 0, 0);
		size=arrayNew.size();
		long startTime = System.nanoTime(); 
		for (int i = 0; i <arrayNew.size(); i++) {
			result.Insert(arrayNew.get(i));
		}
		long endTime = System.nanoTime(); 
		System.out.println("temps d'execution: " + (endTime - startTime) + "NanoSecond"); 
		return result;
	}

	
	
	//Ajout un ¨¦l¨¦ment dans TasMin
	void Insert(int var) {
		if(!hasElement(var)) {
			this.tasMin.add(var);
			percolateUp(var);
		}

	}

	//supprimer un ¨¦l¨¦ment dans TasMin
	public void RemoveMin() {
		//stocker la racine dans tmp
		int tmp = tasMin.get(0);
		
		//¨¦change le premier ¨¦l¨¦ment et le dernier ¨¦l¨¦ment
		tasMin.set(0, tasMin.get(tasMin.size() - 1));
		tasMin.set(tasMin.size() - 1, tmp);
		
		//supprimer le plus petit ¨¦l¨¦ment
		tasMin.remove(tasMin.size() - 1);
		size=tasMin.size();
		//trier le tasMin
		percolateDown(tasMin, 0);
	}

	//Union deux Tasmin
	void Union(ArrayList<Integer> arrayNew2) {
		for (int var:arrayNew2) {
			if(!hasElement(var))
			{
				this.tasMin.add(var);
			}
		}
		size=tasMin.size();
		int parentIndex=(size-1)/2;
		for(int i=parentIndex; i>=0; i--) {
			percolateDown(tasMin,i);
		}
		
	}
	/*
		long startTime = System.nanoTime(); 
	 	System.out.println("Result of TasMin after the Union");
		long endTime = System.nanoTime(); 
		System.out.println("compteur:" + cmp);
		System.out.println("temps d'execution: " + (endTime - startTime) + "NanoSecond"); 
		System.out.println("temps d'execution en moy: " + ((endTime - startTime)/cmp) + "NanoSecond");
*/
	
	//verifie si element existe deja
	public boolean hasElement(int key) {
		for (int i = 0; i < this.tasMin.size()-1; i++) {
			if(key== this.tasMin.get(i)) {
				System.out.println("variable "+key+" has existed already");
				return true;
			}
		}
		return false;
	}
	
	//afficher le tasMin
	void display() {
		System.out.println("Current tasMin is:");
		System.out.println(getTasMin());
	}

	public static void main(String[] args) {
		ArrayList<Integer> array=new ArrayList<>();
	array.add(13);
		array.add(12);
		array.add(11);
		array.add(10);
		array.add(9);
		array.add(8);
		array.add(7);
		array.add(6);
		array.add(5);
		array.add(4);
		array.add(3);
		array.add(2);
		array.add(1);
		array.add(0);
		int size=array.size();
		int parentIndex=0;
		int childIndex=0;
		TasMinTableauSimple t=new TasMinTableauSimple(size,parentIndex,childIndex); 

		System.out.println(" ---1) consIter : Algor naif---- ");
		t=TasMinTableauSimple.consIter2(array,size);
		t.display();
		
		System.out.println(" ----1) consIter----");
		t=TasMinTableauSimple.consIter(array,size);
		t.display();

		System.out.println(" ---2) Ajout----");
		t.Insert(3);
		t.display();
		
		System.out.println(" ----3) SuppMin----"); 
		t.RemoveMin();
		t.display();
		
		System.out.println(" ----4) Union-----");
		ArrayList<Integer> array2=new ArrayList<>();
		array2.add(17);
		array2.add(1);
		array2.add(3);
		array2.add(9);
		array2.add(13);
		t.Union(array2);
		t.display();
	}
}
