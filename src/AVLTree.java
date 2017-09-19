public class AVLTree {
	AVLTreeNode root;
	//constructor
	AVLTree(){
		root = null;
	}
	//insert method
	public void insert(int value) {
		//create a node with the value
		AVLTreeNode node = new AVLTreeNode(value);
		//if tree is empty, make this the root
		if(root==null) {
			root = node;
			return;
		}
		//start with the root
		AVLTreeNode current = root;
		while(true) {
			//find the spot to insert this node
			if(value<current.value) {
				//if left is empty, make it the left, else go to the left node
				if(current.left==null) {
					current.left = node;
					node.parent = current;
					break;
				}
				else {
					current = current.left;
				}
			}
			else {
				//if right is empty, make it the right, else go to the right node
				if(current.right==null) {
					current.right = node;
					node.parent = current;
					break;
				}
				else {
					current = current.right;
				}
			}
		}
		//balance the nodes
		current = node;
		while(current!=null) {
			//calculate the heights of the left and right subtrees
			int lheight = getHeight(current.left);
			int rheight = getHeight(current.right);
			//see if balance is alright
			if(lheight-rheight>1) {
				//left side is too much
				AVLTreeNode z = current;
				//y is the left child no matter what
				AVLTreeNode y = z.left;
				//find out x; if x is left child of y, then left left case, else left right case
				if(value<y.value) {
					//left left case
					//AVLTreeNode x = y.left;
					rightRotate(z);
				}
				else {
					//left right case
					//AVLTreeNode x = y.right;
					leftRotate(y);
					rightRotate(z);
				}
			}
			else if(rheight-lheight>1) {
				//right side is too much
				AVLTreeNode z = current;
				//y is the right child no matter what
				AVLTreeNode y = z.right;
				//find out x; if x is right child of y, then right right case, else right left case
				if(value>=y.value) {
					//right right case
					//AVLTreeNode x = y.right;
					leftRotate(z);
				}
				else {
					//right left case
					//AVLTreeNode x = y.left;
					rightRotate(y);
					leftRotate(z);
				}
			}
			current = current.parent;
		}
	}
	public int getHeight(AVLTreeNode root) {
		//base case
		if(root==null) {
			return -1;
		}
		int lheight = getHeight(root.left);
		int rheight = getHeight(root.right);
		return (1 + max(lheight, rheight));
	}
	//utility function to calculate maximum of 2
	int max(int a, int b) {
		return (a>b)? a: b;
	}
	//left rotate
	public void leftRotate(AVLTreeNode root) {
		//right child will be the new root, old root will be new root's left, old root's new right will be new root's old left
		AVLTreeNode parentOfRoot = root.parent;
		
		//save the new root
		AVLTreeNode newRoot = root.right;
		//adjust parent of new root
		newRoot.parent = parentOfRoot;
		
		//give the old root some consolation
		AVLTreeNode rightsleft = newRoot.left;
		root.right = rightsleft;
		//adjust parent of the newly assigned node
		if(rightsleft!=null) {
			rightsleft.parent = root;
		}
		
		//give kingdom to the new root
		newRoot.left = root;
		//adjust parent of old root
		root.parent = newRoot;
		
		//if the passed in root was the root of the tree, then update root
		if(parentOfRoot==null) {
			this.root = newRoot;
		}
		
		//if not, then adjust the parent's outgoing edge
		else {
			if(newRoot.value<parentOfRoot.value) {
				parentOfRoot.left = newRoot;
			}
			else {
				parentOfRoot.right = newRoot;
			}
		}
	}
	//right rotate
	public void rightRotate(AVLTreeNode root) {
		//left child will be the new root, old root will be new root's right, old root's new left will be new root's old right
		AVLTreeNode parentOfRoot = root.parent;
		
		//save the new root
		AVLTreeNode newRoot = root.left;
		//adjust parent of new root
		newRoot.parent = parentOfRoot;
		
		//give the old root some consolation
		AVLTreeNode leftsright = newRoot.right;
		root.left = leftsright;
		//adjust parent of the newly assigned node
		if(leftsright!=null) {
			leftsright.parent = root;
		}
		
		//give kingdom to the new root
		newRoot.right = root;
		//adjust parent of old root
		root.parent = newRoot;
		
		//if the passed in root was the root of the tree, then update root
		if(parentOfRoot==null) {
			this.root = newRoot;
		}
		
		//if not, then adjust the parent's outgoing edge
		else {
			if(newRoot.value<parentOfRoot.value) {
				parentOfRoot.left = newRoot;
			}
			else {
				parentOfRoot.right = newRoot;
			}
		}
	}
	//preorder traverse
	public void preorderTraverse() {
		System.out.println("Preorder traversal...");
		preTra(root);
		System.out.println("");
	}
	public void preTra(AVLTreeNode root) {
		//if root is null, return
		if(root==null) {
			return;
		}
		//print the value
		System.out.print(root.value + " ");
		//traverse the left subtree
		preTra(root.left);
		//traverse the right subtree
		preTra(root.right);
	}
	//inorder traverse
	public void inorderTraverse() {
		System.out.println("Inorder traversal...");
		inTra(root);
		System.out.println("");
	}
	public void inTra(AVLTreeNode root) {
		//if root is null, return
		if(root==null) {
			return;
		}
		//traverse the left subtree
		inTra(root.left);
		//print the value
		System.out.print(root.value + " ");
		//System.out.print("(" + (getHeight(root.left) - getHeight(root.right)) + ") ");
		//traverse the right subtree
		inTra(root.right);
	}
}

class AVLTreeNode{
	//each node will have a value
	int value;
	//and a left and right
	AVLTreeNode left, right;
	//store the parent, will need it later
	AVLTreeNode parent;
	//constructor
	AVLTreeNode(int value){
		this.value = value;
		this.left = null;
		this.right = null;
		this.parent = null;
	}
}