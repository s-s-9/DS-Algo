public class LinkedList {

}

class SinglyLinkedList{
	//list will have a head, and optionally, a tail
	SinglyListNode head, tail;
	//save the number of elements currently in the list
	int size;
	//constructor
	SinglyLinkedList(){
		head = null;
		tail = null;
		size = 0;
	}
	//traverse the list
	public void traverse() {
		System.out.println("Traversing the list...");
		//start with the head
		SinglyListNode current = head;
		//continue until the last node
		while(current!=null) {
			System.out.print(current.value + " ");
			current = current.next;
		}
		System.out.println("");
	}
	//traverse in reverse order
	public void reverseTraverse() {
		System.out.println("Traversing in reverse order...");
		revTrav(head);
		System.out.println("");
	}
	public void revTrav(SinglyListNode current) {
		//if this node is null, return
		if(current==null) {
			return;
		}
		revTrav(current.next);
		System.out.print(current.value + " ");
	}
	//insert at the end
	public void append(int value) {
		//increment size
		size++;
		//create a node with this value
		SinglyListNode node = new SinglyListNode(value);
		//if list is empty, make this node the head and tail
		if(head==null) {
			head = node;
			tail = node;
			return;
		}
		//if list had elements add it to the end of the list and make this the new tail
		tail.next = node;
		tail = node;
	}
	//insert at the beginning
	public void pushFront(int value) {
		//increment size
		size++;
		//create a node with this value
		SinglyListNode node = new SinglyListNode(value);
		//if list is empty, make this node the head and tail
		if(head==null) {
			head = node;
			tail = node;
			return;
		}
		//make the previous head be the new node's next
		node.next = head;
		//make this node the new head
		head = node;
	}
	//sorted insert
	public void sortedInsert(int value) {
		//increment size
		size++;
		//create a node with the given value
		SinglyListNode node = new SinglyListNode(value);
		//if the list is empty, make this the head and tail
		if(head==null) {
			head = node;
			tail = node;
			return;
		}
		//if the value is less than the head's value, just call pushFront
		if(value<head.value) {
			this.pushFront(value);
			return;
		}
		//if the value is greater than the tail's value, just call append
		if(value>tail.value) {
			this.append(value);
			return;
		}
		//if the value is in between, then insert at appropriate place
		SinglyListNode current = head;
		while(current.next.value<value) {
			current = current.next;
		}
		node.next = current.next;
		current.next = node;
	}
	//search for a value
	public boolean search(int value) {
		//start with the head
		SinglyListNode current = head;
		while(current!=null) {
			if(current.value==value) {
				return true;
			}
			current = current.next;
		}
		return false;
	}
	//delete a node
	public boolean delete(int value) {
		//if list is empty, return false
		if(head==null) {
			return false;
		}
		//if there is only one node and it is being deleted, reset head and tail
		if(head==tail && head.value==value) {
			//decrement size
			size--;
			head = null;
			tail = null;
			return true;
		}
		//if the head is being deleted, update head
		if(head.value==value) {
			//decrement size
			size--;
			head = head.next;
			return true;
		}
		//find the appropriate node to delete
		SinglyListNode current = head;
		while(current.next instanceof SinglyListNode && current.next.value!=value) {
			current = current.next;
		}
		//if some node is being deleted, delete the node
		if(current.next instanceof SinglyListNode && current.next.value==value) {
			//if the tail is being deleted, update tail
			if(current.next==tail) {
				current.next = null;
				tail = current;
			}
			else {
				current.next = current.next.next;
			}
			//decrement size
			size--;
			return true;
		}
		//nothing worked, return false
		return false;
	}
	//reverse the list
	public void reverse() {
		rev(head);
	}
	public void rev(SinglyListNode current) {
		//if list is empty, or has only 1 element, then return
		if(head==tail) {
			return;
		}
		//save the next node
		SinglyListNode nextNode = current.next;
		//the previous head will have null at its next
		if(current==head) {
			current.next = null;
		}
		//if we're at the last node, then make it the head
		if(nextNode==null) {
			head = current;
			return;
		}
		//recurse to the next node
		rev(nextNode);
		//reverse the connection
		nextNode.next = current;
	}
}

class SinglyListNode{
	//each node will have a value and a reference to the next node
	int value;
	SinglyListNode next;
	//constructor
	SinglyListNode(int value){
		this.value = value;
		this.next = null;
	}
}