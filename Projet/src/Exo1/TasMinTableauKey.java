package Exo1BigInterger;

import java.util.ArrayList;

public class TasMinTableauKey {
	Key key;
	int size;
	int parentIndex;//le noeud parent
	int childIndex;//le noeud fils
	static int cmp=0;
	ArrayList<Key> tasMin;

	public  TasMinTableauKey(int size, int parentIndex,int childIndex) {
		this.size=size;
		this.parentIndex=parentIndex;
		this.childIndex=childIndex;
		tasMin= new ArrayList<>();
	};

	public ArrayList<Key> getTasMin() {
		return tasMin;
	}

	public void setTasMin(ArrayList<Key> tasMin) {
		this.tasMin = tasMin;
	}

	//trier tasMin en montant
	public void percolateUp(Key key) {
		//tmp pour stocker element parent
		Key tmp = null;
		size=tasMin.size();
		//on initie le+ parentIndex en positif  et le childIndex au dernier element du tab
		childIndex=size-1;
		parentIndex=1;
		//si la taille est 1, retourne le tasMin
		if(size==1) return ;
		//on verifie si le child element a toujours un parent
		while(parentIndex >0) {
			//si child est a gauche
			if(childIndex%2==1){
				parentIndex=(childIndex-1)/2;
				tmp=tasMin.get(parentIndex);
			}
			//si child est a droit
			if(childIndex%2==0){
				parentIndex=(childIndex-2)/2;
				tmp=tasMin.get(parentIndex);
			}
			//comparer le child est parent, si le parent est plus petit, retourne
			if(Key.inf(tmp,tasMin.get(childIndex))
					|| Key.eg(tmp,tasMin.get(childIndex))) {
				return;
			}
			//echange le parent et le fils
			tasMin.set(parentIndex, tasMin.get(childIndex));
			tasMin.set(childIndex,tmp);
			childIndex=parentIndex;
			//compter le nombre operateur
		}
	}

	//Ranger TasMin en descendant
	public void percolateDown (ArrayList<Key> arrayNew,int parentIndex) {
		//donner la valuer arrayNew au tasMin
		setTasMin(arrayNew);
		Key tmp=tasMin.get(parentIndex);
		childIndex=(2*parentIndex)+1;
		//si le noeud est feuille, sortir
		while(childIndex<tasMin.size()) {
			//si le fils droit est plus petit que fils gauche

			if(childIndex+1<arrayNew.size() && 
					(Key.inf(arrayNew.get(childIndex+1),arrayNew.get(childIndex))==true)) {
				childIndex++;} 
			
			//si le parent est plus petit que fils, on sort
			if(Key.inf(tmp,arrayNew.get(childIndex))
					|| Key.eg(tmp,arrayNew.get(childIndex))){
				break;}

			//echange le parent et le fils
			tasMin.set(parentIndex, tasMin.get(childIndex));
			tasMin.set(childIndex,tmp);	

			parentIndex=childIndex;
			childIndex=2*childIndex+1;
		}
	}

	public static TasMinTableauKey consIter(ArrayList<Key> arrayNew,int size) {
		// commence par le permier parentIndex
		TasMinTableauKey result =new TasMinTableauKey(0, 0, 0);
		size=arrayNew.size();
		int parentIndex=(size-1)/2;
		for(int i=parentIndex; i>=0; i--) {
			result.percolateDown(arrayNew,i);
		}
		return result;
	}

	//Ajout un ¨¦l¨¦ment dans TasMin
	public void Ajout(Key var) {
		//si il n'existe pas 
		if(!this.tasMin.contains(var)) {
			this.tasMin.add(var);
			percolateUp(var);
		}
	}

	//supprimer un ¨¦l¨¦ment dans TasMin
	public void SupprMin() {
		Key a = tasMin.get(0);
		//¨¦change le premier ¨¦l¨¦ment et le dernier ¨¦l¨¦ment
		tasMin.set(0, tasMin.get(tasMin.size() - 1));
		tasMin.set(tasMin.size() - 1, a);
		//supprimer le plus petit ¨¦l¨¦ment
		tasMin.remove(tasMin.size() - 1);
		size=tasMin.size();
		percolateDown(tasMin, 0);
	}

		//Union deux Tasmin
		public static TasMinTableauKey Union(ArrayList<Key> keys1, ArrayList<Key>keys2) {
			ArrayList<Key> result= new ArrayList<>();
			TasMinTableauKey newTasMin = new TasMinTableauKey(0,0,0);
			//donner les valeurs de keys1 ¨¤ result 
			result=keys1;
			for (Key var: keys2) {
				if(!result.contains(var))
				{
					result.add(var);
				}
			}
			int parentIndex=(result.size()-1)/2;
			for(int i=parentIndex; i>=0; i--) {
				newTasMin.percolateDown(result,i);
			}
			newTasMin.setTasMin(result);
			return newTasMin;
		}
		
		//afficher tasMin
		public void display() {
			System.out.println(this);
		}
		
		@Override
		public String toString() {
			String str = "";
			for (Key key:this.tasMin) {
				str+=key.getKey().toString(16)+"\n";
			}
			return str;
		} 

}
