public abstract class Heap {
	int growth;
	int[] list;
	int count;
	Heap(int growth){
		this.growth = growth;
		list = new int[growth];
		count = 0;
	}
	public void insert(int value) {
		//if list is full, extend list
		if(count==list.length) {
			extend();
		}
		//add this value at the count-th position
		list[count] = value;
		//adjust ordering
		heapify();
		count++;
	}
	public abstract void heapify();
	//delete an element
	public boolean delete(int value) {
		int index = getIndexOf(value);
		//if item doesn't exist in the list, then return false
		if(index==-1) {
			return false;
		}
		//actually delete the item
		del(index);
		return true;
	}
	//get the index of a value
	public int getIndexOf(int value) {
		int index = 0;
		while(index<count && list[index]!=value) {
			index++;
		}
		if(index<count) {
			return index;
		}
		else {
			return -1;
		}
	}
	public abstract void del(int index);
	//print
	public void print() {
		for(int i = 0; i<count; i++) {
			System.out.print(list[i] + " ");
		}
		System.out.println();
	}
	//extend the list
	public void extend() {
		System.out.println("Extending...");
		int[] newlist = new int[list.length*2];
		//copy the old list's items into the new list
		for(int i = 0; i<list.length; i++) {
			newlist[i] = list[i];
		}
		//make this new list the default list
		list = newlist;
	}
	//helper function to swap 2 elements
	public void swap(int a, int b, int[] list) {
		int temp = list[a];
		list[a] = list[b];
		list[b] = temp;
	}
}

class MinHeap extends Heap{
	//constructor
	MinHeap(int growth){
		super(growth);
	}
	public void heapify() {
		int current = count;
		while(current>0 && list[current]<list[(current-1)/2]) {
			swap(current, (current-1)/2, list);
			//update current 
			current = (current-1)/2;
		}
	}
	public void del(int index) {
		//reduce count by one
		count--;
		//take the last element to the index-th index
		list[index] = list[count];
		//preserve the order
		int current = index;
		while(current<count) {
			int left = (current*2)+1;
			int right = (current*2)+2;
			//if this doesn't have a left child (then it surely doesn't have a right child) then break
			if(left >= count) {
				break;
			}
			//if this has a left child, but no right child, then try to swap with left child
			if(right >= count) {
				if(list[left] < list[current]) {
					swap(left, current, list);
					current = left;
				}
				else {
					break;
				}
			}
			//if the list has both left and right children, then swap with the minimum if possible
			else {
				if(list[left]<list[current] || list[right]<list[current]) {
					if(list[left]<=list[right]) {
						swap(left, current, list);
						current = left;
					}
					else {
						swap(right, current, list);
						current = right;
					}
				}
				else {
					break;
				}
			}
		}
	}
}

class MaxHeap extends Heap{
	//constructor
	MaxHeap(int growth){
		super(growth);
	}
	public void heapify() {
		int current = count;
		while(current>0 && list[current]>list[(current-1)/2]) {
			swap(current, (current-1)/2, list);
			//update current 
			current = (current-1)/2;
		}
	}
	public void del(int index) {
		//reduce count by one
		count--;
		//take the last element to the index-th index
		list[index] = list[count];
		//preserve the order
		int current = index;
		while(current<count) {
			int left = (current*2)+1;
			int right = (current*2)+2;
			//if this doesn't have a left child (then it surely doesn't have a right child) then break
			if(left >= count) {
				break;
			}
			//if this has a left child, but no right child, then try to swap with left child
			if(right >= count) {
				if(list[left] > list[current]) {
					swap(left, current, list);
					current = left;
				}
				else {
					break;
				}
			}
			//if the list has both left and right children, then swap with the minimum if possible
			else {
				if(list[left]>list[current] || list[right]>list[current]) {
					if(list[left]>=list[right]) {
						swap(left, current, list);
						current = left;
					}
					else {
						swap(right, current, list);
						current = right;
					}
				}
				else {
					break;
				}
			}
		}
	}
}