package Exo1;

import java.util.ArrayList;

public class TasMinTableau2 {
	int[] key;
	int size;
	int parentIndex;//le noeud parent
	int childIndex;//le noeud fils
	ArrayList<Key> tasMin;
	int cmp=0;

	public  TasMinTableau2(int size, int parentIndex,int childIndex) {
		this.size=size;
		this.parentIndex=parentIndex;
		this.childIndex=childIndex;

		tasMin= new ArrayList<>();
	};
	public  TasMinTableau2() {
		tasMin= new ArrayList<>();
	};
	
	public ArrayList<Key> getTasMin() {
		return tasMin;
	}

	public void setTasMin(ArrayList<Key> tasMin) {
		this.tasMin = tasMin;
	}
	
	/*idee :
	 * - remplacer premier element par le dernier element
	 * - supprimer dernier element
	 * - supprimer dernier element une seconde fois, mais en le gardant dans un tmp
	 * - le rajouter via la fonction ajout	
	 *  */
	public void supprimerMin() {
		System.out.println(this.tasMin.size());
		Key lastKey= this.tasMin.get(this.tasMin.size()-1);
		this.tasMin.set(0,lastKey);
		this.tasMin.remove(this.tasMin.size()-1);
		TasMinTableau2 tasminTmp=constIter(this.tasMin);
		this.tasMin=tasminTmp.getTasMin();
	}
	
	//Union deux tas
	public static ArrayList<Key>  union(ArrayList<Key> keys1, ArrayList<Key>keys2){
		ArrayList<Key> result= new ArrayList<>();
		//ajouter tous les elements dans keys1
		keys1.addAll(keys2);
		//consIter keys1
		TasMinTableau2 tasminTmp=constIter(keys1);
		result=tasminTmp.getTasMin();
		return result;
		
	}

	//ajouter un element dans tasMin
	public void ajout(Key key) {
		//verifie si l'element existe, sinon ajoute le nouveau element a la fin de tableau.
		if(hasElement(key)) return;
		tasMin.add(key);
		//tmp pour stocker element parent
		Key tmp = null;
		size=tasMin.size();
		//si la taille est 1, retourne le tasMin
		if(size==1)
		{
			return ;
		}
		
		//on verifie si le child element a toujours un parent
		while(parentIndex >0) {
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
				if(Key.inf(tmp,tasMin.get(childIndex))
						|| Key.eg(tmp,tasMin.get(childIndex))) {
					return;
				}
				//echange le parent et le fils
				tasMin.set(parentIndex, tasMin.get(childIndex));
				tasMin.set(childIndex,tmp);
				childIndex=parentIndex;
			}
	}

//ajouter successfivement element dans tasMin
	public static TasMinTableau2 constIter(ArrayList<Key> keys) {
		TasMinTableau2 result = new TasMinTableau2();
		for (int i = 0; i <keys.size(); i++) {
			result.ajout(keys.get(i));
		}
		return result;
	}

//verifie si element existe deja
	public boolean hasElement(Key key) {
		for (int i = 0; i < this.tasMin.size()-1; i++) {
			if(Key.eg(key, this.tasMin.get(i))) {
				//comment convertir var ид string ???
				System.out.println("variable "+key+" has existed already");
				return true;
			}
		}
		return false;
	}

	

}
