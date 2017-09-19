public class BinarySearchTree {
	//a bst is represented by the root
	BSTNode root;
	//constructor
	BinarySearchTree(){
		root = null;
	}
	//insert a node
	public void insert(int value) {
		//create a tree node with this value
		BSTNode node = new BSTNode(value);
		//start with the root and find an appropriate place to insert this node
		BSTNode current = root;
		//if tree was empty, make the new node the root
		if(current==null) {
			root = node;
			return;
		}
		//this will hold the previous node
		BSTNode previous = null;
		while(current!=null) {
			//this will be the previous in the next step
			previous = current;
			//if the value to be inserted >= current's value, then go to right child
			if(value>=current.value) {
				current = current.right;
			}
			//if the value to be inserted < current's value, then go to right child
			else if(value<current.value) {
				current = current.left;
			}
		}
		//our new node will be child of previous, but will it be the left child, or the right?
		if(value>=previous.value) {
			previous.right = node;
		}
		else {
			previous.left = node;
		}
	}
	//preorder traverse
	public void preorderTraverse() {
		System.out.println("Preorder traversal...");
		preTra(root);
		System.out.println("");
	}
	public void preTra(BSTNode root) {
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
	public void inTra(BSTNode root) {
		//if root is null, return
		if(root==null) {
			return;
		}
		//traverse the left subtree
		inTra(root.left);
		//print the value
		System.out.print(root.value + " ");
		//traverse the right subtree
		inTra(root.right);
	}
	//postorder traverse
	public void postorderTraverse() {
		System.out.println("Postorder traversal...");
		postTra(root);
		System.out.println("");
	}
	public void postTra(BSTNode root) {
		//if root is null, return
		if(root==null) {
			return;
		}
		//traverse the left subtree
		postTra(root.left);
		//traverse the right subtree
		postTra(root.right);
		//print the value
		System.out.print(root.value + " ");
	}
	//breadth first traversal
	public void breadthFirstTraverse() {
		System.out.println("Breadth first traversal...");
		bfTra(root);
		System.out.println("");
	}
	public void bfTra(BSTNode root) {
		//queue for bfs
		Queue q = new Queue();
		//if tree is empty, return
		if(root==null) {
			return;
		}
		//add the root into queue
		q.push(root.value);
		while(!q.isEmpty()) {
			//visit the current node
			int qfront = q.front();
			System.out.print(qfront + " ");
			q.pop();
			//push the left and right child in the queue
			BSTNode node = findNode(root, qfront);
			if(node.left!=null) {
				q.push(node.left.value);
			}
			if(node.right!=null) {
				q.push(node.right.value);
			}
		}
	}
	//search
	public boolean search(int value) {
		//start from the root node
		BSTNode current = root;
		while(current instanceof BSTNode) {
			//if the searched value is found, return true
			if(value==current.value) {
				return true;
			}
			//if searched value is greater, go to the right subtree
			if(value>current.value) {
				current = current.right;
			}
			//else go to the left subtree
			else {
				current = current.left;
			}
		}
		return false;
	}
	//delete a node
	public boolean delete(int value) {
		//get a reference to the node containing the value to be deleted
		BSTNode node = findNode(root, value);
		//if the value was not in the tree, return false
		if(node==null) {
			return false;
		}
		//get a reference to the parent, will need it later
		BSTNode parent = findParent(root, value);
		//if the node to be deleted has no children, then the link from its parent to the node itself will be cut off
		if(node.left==null && node.right==null) {
			//if the node is the root, then the tree will be empty
			if(node==root) {
				root = null;
				return true;
			}
			if(parent.left==node) {
				parent.left = null;
			}
			else if(parent.right==node) {
				parent.right = null;
			}
			return true;
		}
		//if the node being deleted has a left child, but no right child, then add this left child to the appropriate place
		if(node.left instanceof BSTNode && node.right==null) {
			//if the node was the root, the left child will be the new root
			if(node==root) {
				root = node.left;
				return true;
			}
			//if the node was the left child, add this node's left child to the parent's left
			if(parent.left==node) {
				parent.left = node.left;
			}
			//if the node was the right child, add this node's left child to the parent's right
			if(parent.right==node) {
				parent.right = node.left;
			}
			return true;
		}
		//if the node being deleted has a right child, but no left child, then add this right child to the appropriate place
		if(node.right instanceof BSTNode && node.left==null) {
			//if the node was the root, the right child will be the new root
			if(node==root) {
				root = node.right;
				return true;
			}
			//if the node was the left child, add this node's right child to the parent's left
			if(parent.left==node) {
				parent.left = node.right;
			}
			//if the node was the right child, add this node's right child to the parent's right
			if(parent.right==node) {
				parent.right = node.right;
			}
			return true;
		}
		//if the node being deleted has both left and right children, then we've got some work to do
		if(node.left instanceof BSTNode && node.right instanceof BSTNode) {
			
			//get the rightmost child of the left child of node
			BSTNode rmofln = rightMostOfLeftNode(node);
			//cut off this node's connection with its parent
			BSTNode parentofrmofln = findParent(root, rmofln.value);
			if(parentofrmofln.left==rmofln) {
				parentofrmofln.left = rmofln.left;
			}
			else if(parentofrmofln.right==rmofln) {
				parentofrmofln.right = rmofln.left;
			}
			//update the node to be deleted's value with value of the rightmost child of the left child
			node.value = rmofln.value;
			return true;
		}
		return false;
	}
	BSTNode rightMostOfLeftNode(BSTNode node) {
		//start with the left child
		BSTNode current = node.left;
		//keep going right
		while(current.right instanceof BSTNode) {
			current = current.right;
		}
		return current;
	}
	public BSTNode findNode(BSTNode root, int value) {
		//if the root is null, return null
		if(root==null) {
			return null;
		}
		//if the value matches, return the root
		if(value==root.value) {
			return root;
		}
		//if the value is less than root's value, go to the left subtree
		if(value<root.value) {
			return findNode(root.left, value);
		}
		//else go to the right subtree
		else {
			return findNode(root.right, value);
		}
	}
	public BSTNode findParent(BSTNode root, int value) {
		//if value equals root's value, then there is no parent
		if(value==root.value) {
			return null;
		}
		//if value less than root's value, go to the left subtree
		if(value<root.value) {
			//if the value is found in the left child, return root as the parent
			if(value==root.left.value) {
				return root;
			}
			//else search the left subtree
			else {
				return findParent(root.left, value);
			}
		}
		//if the value is greater than root's value, go to the right subtree
		else {
			//if the value is found in the right child, return root as the parent
			if(value==root.right.value) {
				return root;
			}
			//else search the right subtree
			else {
				return findParent(root.right, value);
			}
		}
	}
	//get the minimum element
	public int getMin() {
		BSTNode current = root;
		while(current.left instanceof BSTNode) {
			current = current.left;
		}
		return current.value;
	}
	//get the maximum element
	public int getMax() {
		BSTNode current = root;
		while(current.right instanceof BSTNode) {
			current = current.right;
		}
		return current.value;
	}
}

class BSTNode{
	//each node will have a value, a left child and a right child
	int value;
	BSTNode left, right;
	//constructor
	BSTNode(int value){
		this.value = value;
		this.left = null;
		this.right = null;
	}
}
