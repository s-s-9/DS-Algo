public class QuickSort {
	//static int[] listToSort;
	public static void sort(int[] list) {
		//save the size
		int size = list.length;
		//call the method that actually does the sorting
		quickSort(0, size-1, list);
	}
	public static void quickSort(int lower, int upper, int[] list) {
		//base case
		if(lower>upper) {
			return;
		}
		//pick the rightmost element as pivot
		int pivot = list[upper];
		//the next number less than pivot will go to this index
		int smallerhome = lower;
		for(int i = lower; i<=upper; i++) {
			//see if the current number is less than the pivot
			if(list[i]<pivot) {
				//swap this with whatever is in the index for the next smaller element
				swap(smallerhome, i, list);
				//update the address where the next smaller element will go
				smallerhome++;
			}
		}
		//finally, put the pivot itself in place
		swap(smallerhome, upper, list);
		quickSort(lower, smallerhome-1, list);
		quickSort(smallerhome+1, upper, list);
	}
	public static void swap(int a, int b, int[] list) {
		int temp = list[a];
		list[a] = list[b];
		list[b] = temp;
	}
}
