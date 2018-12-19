package Exo1BigInterger;

import java.util.ArrayList;
import java.util.Stack;


public class Bst {
	Key value;
	Bst leftChild;
	Bst rightChild;
	ArrayList<Bst> listOfBst;

	/*constructor , getters setters*/
	public Bst(Key value, Bst leftChild, Bst rightChild) {
		this.value = value;
		this.leftChild = leftChild;
		this.rightChild = rightChild;

		listOfBst=new ArrayList<>();
	}

	public Bst() {
		listOfBst=new ArrayList<>();
	}

	public ArrayList<Bst> getListOfBst() {
		return listOfBst;
	}

	public void setListOfBst(ArrayList<Bst> listOfBst) {
		this.listOfBst = listOfBst;
	}

	public Key getValue() {
		return value;
	}
	public void setValue(Key value) {
		this.value = value;
	}
	public Bst getLeftChild() {
		return leftChild;
	}
	public void setLeftChild(Bst leftChild) {
		this.leftChild = leftChild;
	}
	public Bst getRightChild() {
		return rightChild;
	}
	public void setRightChild(Bst rightChild) {
		this.rightChild = rightChild;
	}

	
	/* Renvoie vrai si l'arbre courant a un fils gauche. */
	public boolean haveLeftChild() {
		return getLeftChild()!=null;
	}
	
	/* Renvoie vrai si l'arbre courant a un fils droit. */
	public boolean haveRightChild() {
		return getRightChild()!=null;
	}
	static int  cmp=0;
	//ajouter succesivement la cle dans l'arbre
	public void insert(Key value) {
		//si l'arbre est vide,ajouter le root
		if(this.value ==null) {
			this.value=value;
			//calcul la taille d'arbre
			cmp++;
			//System.out.println("--Valaur de racine--");
			//value.showIntsOfKey();
		}
		else {
			//si la valeur est plus petit
			if(Key.inf(value, this.getValue())) {
				//System.out.println("--Fils gauche de racine--");
				if(this.getLeftChild()==null)
				{
					//System.out.println("Fils gauche");
					this.setLeftChild(new Bst(value,null,null));
					cmp++;
					//this.leftChild.getValue().toString();
				}else {
					//System.out.println("Parent actuel" );
					//this.getLeftChild().value.showIntsOfKey();
					//cmp++;
					this.getLeftChild().insert(value);
				}
			//si le droit est plus grand que key
			}else if(Key.inf(this.getValue(),value)){
				//System.out.println("--Fils droit de racine--");
				if(this.getRightChild()==null)
				{	
					//System.out.println("Fils droit");
					this.setRightChild(new Bst(value,null,null));
					cmp++;
					//this.rightChild.getValue().showIntsOfKey();
				}else {
					//cmp++;
					//System.out.println("Parent actuel" );
					//this.getRightChild().value.showIntsOfKey();
					this.getRightChild().insert(value);
				}
			}
		}
	}

	//chercher une cle dans une arbre de recherche
	boolean research(Key valueTo) {
		if(this.getValue()==null) {
			return false;		
		}
		//si la cle en gauche est plus grand que cle
		//et si elle contient un fils gauche
		if((Key.eg(valueTo,this.getValue()))
				&&(this.getLeftChild()!=null)){
			System.out.println("Found out the value in Bst:  ");
			System.out.println(valueTo.toString());
			//valueTo.getKey().toString(16);
			return true;	
		}
		else if(Key.inf(valueTo, this.getValue())) {		
			return this.getLeftChild().research(valueTo);
		}
		//si la cle en droit est plus grand que cle
		//et si elle contient un fils droit
		else if((Key.inf(this.getValue(),valueTo))
				&&(this.getRightChild()!=null)){
			return this.getRightChild().research(valueTo);
		}	
		else {
			System.out.println("The value doesn't existed");
		}

		return true;
	}

	//Ajouter succesivement la cle dans l'arbre
	public static Bst consIter(ArrayList<Key> keys) {
		Bst result = new Bst();
		for (int i = 0; i <keys.size(); i++) {
			result.insert(keys.get(i));
		}
		
		return result;
	} 

	//parcourt prefixe
	public void preOrderRecursion(){
		Stack<Bst> stack=new Stack<Bst>();
		if(this.getValue()==null) {
			return;
		}
		stack.push(this);

		while(!stack.isEmpty()) {
			Bst cur=stack.pop();
			//afficher
			//System.out.println("key n"+ index);
			//cur.value.getKey().toString(16);
			
			//pacourt d'abord l'arbre droit
			if(cur.getRightChild()!=null) {
				stack.push(cur.getRightChild());
			}
			//parcourt l'arbre gauche
			if(cur.getLeftChild()!=null) {
				stack.push(cur.getLeftChild());
			}
		}
	}
	//afficher les arbres
	public void displayBst() {
		System.out.println(this);
		System.out.println("La taille de Bst: "+cmp);
	}
	
	@Override
	public String toString() {
	String s = ""+this.getValue().toString() + 
			" fg : " + 
			((haveLeftChild())?getLeftChild().getValue().toString():"none") + 
			" fd : "+ 
			((haveRightChild())?getRightChild().getValue().toString():"none");
	s+="\n";
	
	if(haveLeftChild()) 
		s+=getLeftChild();
	
	if(haveRightChild()) 
		s+=getRightChild();
		
	return s;
	}	
}
