public class NoobSort {
	//this is the list to sort
	static int[] listToSort;
	//bubble sort
	public static void bubbleSort(int[] list) {
		listToSort = list;
		//save the size
		int size = listToSort.length;
		//at the start of the i-th iteration, i elements at properly placed at the end
		for(int i = 0; i<size-1; i++) {
			//i elements have already been properly placed so loop until size-i
			for(int j = 0; j<size-i-1; j++) {
				if(listToSort[j]>listToSort[j+1]) {
					swap(j, j+1, listToSort);
				}
			}
		}
	}
	//insertion sort
	public static void insertionSort(int[] list) {
		listToSort = list;
		//save the size
		int size = listToSort.length;
		//anything at the left of current is always sorted; start from the second index 
		int current = 1;
		//loop until current = size, because when current = size, the list is sorted upto index size-1 (meaning the entire list)
		while(current<size) {
			//save the value as we'll need it later
			int value = listToSort[current];
			//start comparing from the immediate left index
			int comparewith = current-1;
			//loop until we get our deserved place
			while(comparewith>=0 && value < listToSort[comparewith]) {
				listToSort[comparewith + 1] = listToSort[comparewith];
				comparewith--;
			}
			//our deserved place is comparewith + 1, because current comparewith is either -1 or listToSort[comparewith] <= value
			listToSort[comparewith+1] = value;
			//proceed to the next index
			current++;
		}
	}
	//selection sort
	public static void selectionSort(int[] list) {
		//save the size
		int size = list.length;
		//this will hold the starting of the unsorted part
		int unsorted = 0;
		//loop until the list is sorted till the last index
		while(unsorted<size) {
			//find the minimum index of the unsorted part and swap it with the first position of the unsorted list
			int min = list[unsorted];
			int minat = unsorted;
			for(int i = unsorted+1; i<size; i++) {
				if(list[i]<min) {
					min = list[i];
					minat = i;
				}
			}
			swap(minat, unsorted, list);
			//another element has been put into its place, so increment unsorted
			unsorted++;
		}
	}
	//counting sort
	public static void countingSort(int[] list) {
		//save the size
		int size = list.length;
		//store the sorted list in a temporary array
		int[] temp = new int[size];
		//our range is [0-100)
		int[] count = new int[100];
		//traverse the list and update counts
		for(int i = 0; i<size; i++) {
			//if the number is out of range, ignore
			if(list[i]>=0 && list[i]<100) {
				count[list[i]]++;
			}
		}
		//now modify count to store cumulative counts
		for(int i = 1; i<100; i++) {
			count[i]+=count[i-1];
		}
		//now traverse each element and add it to the appropriate place in the list
		for(int i = 0; i<size; i++) {
			//save the new index; -1 because our indexing is 0 based, whereas the counting starts from 1
			int index = count[list[i]]-1;
			temp[index] = list[i];
			//decrement count
			count[list[i]]--;
		}
		//copy contents of temp into the list
		for(int i = 0; i<size; i++) {
			list[i] = temp[i];
		}
	}
	//radix sort
	public static void radixSort(int[] list) {
		//save the size
		int size = list.length;
		//save the maximum
		int max = Integer.MIN_VALUE;
		for(int i = 0; i<size; i++) {
			max = Math.max(max, list[i]);
		}
		//call simulate counting sort at many steps
		int divideby = 1;
		while(max>1) {
			countingSortForRadixSort(list, divideby);
			divideby*=10;
			max/=10;
		}
	}
	public static void countingSortForRadixSort(int[] list, int divideby) {
		//save the size
		int size = list.length;
		//digits array
		int[] digits = new int[10];
		//the contents of this temporary array will later be transferred into the original array
		int[] temp = new int[size];
		//update digit counts
		for(int i = 0; i<size; i++) {
			//get the proper digit (ones, tens, 100s...)
			int digit = (list[i]/divideby)%10;
			digits[digit]++;
		}
		//get cumulative counts
		for(int i = 1; i<10; i++) {
			digits[i] += digits[i-1];
		}
		//traverse the list in reverse order and put numbers into their places
		for(int i = size-1; i>=0; i--) {
			int digit = (list[i]/divideby)%10;
			temp[digits[digit]-1] = list[i];
			//decrement count
			digits[digit]--;
		}
		//copy the contents of the temporary list into the original list
		for(int i = 0; i<size; i++) {
			list[i] = temp[i];
		}
	}
	//helper function to swap
	public static void swap(int a, int b, int[] list) {
		int temp = list[a];
		list[a] = list[b];
		list[b] = temp;
	}
}
