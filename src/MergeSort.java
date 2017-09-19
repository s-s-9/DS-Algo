public class MergeSort {
	static int[] listToSort;
	static int operations = 0;
	static int[] sort(int[] listToSort) {
		MergeSort.listToSort = listToSort;
		//divide the list into two halves
		return divide(listToSort, 0, listToSort.length - 1);
	}
	static int[] divide(int[] listToSort, int first, int last) {
		operations++;
		//save the total number of elements
		int elements = last - first + 1;
		//divide the list into two sublists
		int[] left = new int[elements/2];
		int[] right = new int[elements - (elements/2)];
		//populate the two lists
		int index = 0;
		for(int i = first; i<(first + (elements/2)); i++) {
			left[index] = listToSort[i];
			index++;
		}
		//if this can be further divided, divide
		if(left.length>1) {
			left = divide(left, 0, left.length-1);
		}
		index = 0;
		for(int i = first + (elements/2); i<=last; i++) {
			right[index] = listToSort[i];
			index++;
		}
		//if this can be further divided, divide
		if(right.length>1) {
			right = divide(right, 0, right.length-1);
		}
		//merge the two sublists
		int mergedSize = left.length + right.length;
		int[] mergedList = new int[mergedSize];
		index = 0;
		int leftindex = 0, rightindex = 0;
		while(index<mergedSize) {
			if(leftindex==left.length) {
				mergedList[index] = right[rightindex];
				rightindex++;
			}
			else if(rightindex==right.length){
				mergedList[index] = left[leftindex];
				leftindex++;
			}
			else if(left[leftindex]<right[rightindex]) {
				mergedList[index] = left[leftindex];
				leftindex++;
			}
			else {
				mergedList[index] = right[rightindex];
				rightindex++;
			}
			index++;
		}
		return mergedList;
	}
}
