public class Stack {
	//stack will be implemented by a linked list
	SinglyLinkedList list;
	//constructor
	Stack(){
		list = new SinglyLinkedList();
	}
	//push a value
	public void push(int value) {
		//just add it to the end of the list
		list.append(value);
	}
	//pop
	public boolean pop() {
		//if list is empty, return false
		if(list.head==null) {
			return false;
		}
		//if list has one element, make the list empty
		if(list.head==list.tail) {
			list.head = null;
			list.tail = null;
			return true;
		}
		//now popping means just updating the tail
		SinglyListNode current = list.head;
		while(current.next!=list.tail) {
			current = current.next;
		}
		list.tail = current;
		return true;
	}
	//return the top
	public int top() {
		//if list is empty, return -1
		if(list.head==null) {
			return -1;
		}
		return list.tail.value;
	}
	//tell whether list is empty
	public boolean isEmpty() {
		if(list.head==null) {
			return true;
		}
		else {
			return false;
		}
	}
}
