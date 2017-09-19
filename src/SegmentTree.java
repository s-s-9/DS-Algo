public class SegmentTree {
	//underlying data structure is an array
	int[] segmentTree;
	int[] list;
	int listsize;
	//constructor
	SegmentTree(int[] list){
		//save the size
		listsize = list.length;
		segmentTree = new int[listsize*2];
		//the actual tree will take advantage of starting indexing from 1
		this.list = new int[listsize+1];
		for(int i = 1; i<=listsize; i++) {
			this.list[i] = list[i-1];
		}
		//create the tree immediately
		init(1, 1, listsize);
	}
	//initialize the tree
	public void init(int insertAt, int start, int end) {
		//if start equals end, then we're at a leaf node; give it the value at that index
		if(start==end) {
			segmentTree[insertAt] = list[start];
			return;
		}
		//else we've got some partitioning to do
		int left = insertAt*2;
		int right = left+1;
		int mid = (start+end)/2;
		//initialize the left and right parts first because without these values their parent cannot know its value 
		init(left, start, mid);
		init(right, mid + 1, end);
		//now that we have initialized the left and right parts, initialize the parent
		segmentTree[insertAt] = segmentTree[left] + segmentTree[right];
	}
	//query
	public int query(int start, int end) {
		return que(1, 1, listsize, start, end);
	}
	public int que(int valueAt, int lookinstart, int lookinend, int lookforstart, int lookforend) {
		//if the values we're looking for falls completely within the range that we're looking in, return value in the range
		if(lookforstart<=lookinstart && lookforend>=lookinend) {
			return segmentTree[valueAt];
		}
		//if the values fall completely out of range, return 0
		if(lookforstart>lookinend || lookforend<lookinstart) {
			return 0;
		}
		//value falls partially within range
		int left = valueAt*2;
		int right = left+1;
		int mid = (lookinstart+lookinend)/2;
		//add up everything that makes sense
		return que(left, lookinstart, mid, lookforstart, lookforend) + que(right, mid+1, lookinend, lookforstart, lookforend);
	}
	//print the tree
	public void print() {
		for(int i = 1; i<(2*listsize); i++) {
			System.out.print(segmentTree[i] + " ");
		}
		System.out.println();
	}
	//update method
	public void update(int index, int value) {
		upd(1, 1, listsize, index, value);
	}
	public void upd(int insertAt, int start, int end, int index, int value) {
		//return if out of range
		if(index<start || index>end) {
			return;
		}
		//if we are at the correct index, update the value
		if(start==end && start==index) {
			segmentTree[insertAt] = value;
			return;
		}
		//else recursively go to the left and right children
		int left = insertAt*2;
		int right = left+1;
		int mid = (start+end)/2;
		upd(left, start, mid, index, value);
		upd(right, mid+1, end, index, value);
		//propagate the updates in the children to the parent
		segmentTree[insertAt] = segmentTree[left] + segmentTree[right];
	}
}
