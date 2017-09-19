public class Queue {
	//this will have a linked list
	SinglyLinkedList list;
	//constructor
	Queue(){
		list = new SinglyLinkedList();
	}
	//push method
	public void push(int value) {
		list.append(value);
	}
	//get the front item
	public int front() {
		//if list is empty, return -1
		if(list.head==null) {
			return -1;
		}
		return list.head.value;
	}
	//pop method
	public boolean pop() {
		//if list is empty, return false
		if(list.head==null) {
			return false;
		}
		//if there is one element in the list, reset head and tail
		if(list.head==list.tail) {
			list.head = null;
			list.tail = null;
			return true;
		}
		//shift head one step right
		list.head = list.head.next;
		return true;
	}
	//return whether the queue is empty
	public boolean isEmpty() {
		if(list.head==null) {
			return true;
		}
		else {
			return false;
		}
	}
}

//priority queue
class PriorityQueue{
	//will have a heap underneath
	MaxHeap heap;
	//constructor
	PriorityQueue(){
		heap = new MaxHeap(10);
	}
	//add an item
	public void push(int value) {
		heap.insert(value);
	}
	//remove an item
	public void pop() {
		//if list is empty, return
		if(heap.count==0) {
			return;
		}
		//delete the top item
		heap.delete(heap.list[0]);
	}
	//return the top item without removing
	public int front() {
		//if list is empty, the return -1
		if(heap.count==0) {
			return -1;
		}
		return heap.list[0];
	}
	//tell if list is empty
	public boolean isEmpty() {
		if(heap.count==0) {
			return true;
		}
		else {
			return false;
		}
	}
}