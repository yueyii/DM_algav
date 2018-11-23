package Exo1;

public class ABR {
	Key value;
	ABR root;
	ABR leftChild;
	ABR rightChild;

	/*methods*/

	public void consIter() {
		root=null;
	}
	public void consIter(ABR tasMin,Key value) {
		if(root==null) {
			root=new ABR(value,null,null);
		}else {
			//si key est inf¨¦rieur que l'¨¦l¨¦ment d'arbre
			if(Key.inf(this.value, tasMin.value)) {
				if(tasMin.leftChild==null)
				{
					tasMin.leftChild=new ABR(value,null,null);
				}else {
					consIter(tasMin.leftChild,value);
				}
			//si le droit est plus grand que key
			}else if(Key.inf(tasMin.value,this.value)){
				if(tasMin.rightChild==null)
				{
					tasMin.rightChild=new ABR(value,null,null);
				}else {
					consIter(tasMin.rightChild,value);
				}
			}
		}
	}



	/*constructor , getters setters*/
	public ABR(Key value, ABR leftChild, ABR rightChild) {
		this.value = value;
		this.leftChild = null;
		this.rightChild = null;
	}
	public Key getValue() {
		return value;
	}
	public void setValue(Key value) {
		this.value = value;
	}
	public ABR getLeftChild() {
		return leftChild;
	}
	public void setLeftChild(ABR leftChild) {
		this.leftChild = leftChild;
	}
	public ABR getRightChild() {
		return rightChild;
	}
	public void setRightChild(ABR rightChild) {
		this.rightChild = rightChild;
	}
}
