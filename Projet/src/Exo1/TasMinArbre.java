package Exo1;

import java.util.LinkedList;

public class TasMinArbre {
	Node root;
	LinkedList<Node> queue = new LinkedList<>();
	public class Node {
		int value;
		Node left;
		Node right;

		public Node(int value) {
			this.left=null;
			this.right=null;
			this.value=value;
		}
	};
	/*methods*/

	public TasMinArbre() {
		root=null;
	}

	public void buildTree(Node node,int value){
		if(root==null){
			root=new Node(value);
		}	else{
			Node test=checkTas(node);
			if(test.left==null) {
				node.left =new Node(value);
				System.out.print(node.left.value + "left ");
			} 
			else if(test.right==null) {
				node.right =new Node(value);
				System.out.print(node.right.value + "right ");
			}
		else {
			buildTree(test,value);
			}
		}

	}

	Node checkTas(Node cur) {
		if(root==null){
			return null;
		}
		Node result=cur;
		queue.push(result);
		System.out.print(cur.value + "check");
		
		while(!queue.isEmpty()) {
			result=queue.removeFirst();
			//System.out.print(result.value + "check");
			//System.out.print(cur.value + " ");
			if(result.left!=null)
			{
				queue.add(result.left);
			}
			if(result.right!=null) {
				queue.add(result.right);
			}	
		}
		return result;
	}

	/*public void consIter(TasMinArbre tasMin,Key value) {
		if(root==null) {
			root=new TasMinArbre(value,null,null);
		}else {
			//si key est inf¨¦rieur que l'¨¦l¨¦ment d'arbre
			if(Key.infe(this.value, tasMin.value)) {
				if(tasMin.leftChild==null)
				{
					tasMin.leftChild=new TasMinArbre(value,null,null);
				}else {
					consIter(tasMin.leftChild,value);
				}
			//si le droit est plus grand que key
			}else if(Key.infe(tasMin.value,this.value)){
				if(tasMin.rightChild==null)
				{
					tasMin.rightChild=new TasMinArbre(value,null,null);
				}else {
					consIter(tasMin.rightChild,value);
				}
			}
		}
	}*/


	public void swap(Key parentValue, Key child) {
	}

	public static void main(String[] args) {
		TasMinArbre t=new TasMinArbre();
		int[]a= {2,3,4};
		for (int i = 0; i < a.length; i++) {
			t.buildTree(t.root, a[i]);
		}		

	}


	/*constructor , getters setters*/
	public Node getRoot() {
		return root;
	}

	public void setRoot(Node root) {
		this.root = root;
	}

}
