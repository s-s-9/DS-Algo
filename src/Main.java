import java.util.Random;

public class Main {
	public static void main(String[] args) {
		/*MERGE SORT*/
		/*int[] listToSort = {4, 3, 2, 1};
		Random rand = new Random();
		int[] listToSort = new int[100];
		for(int i = 0; i<100; i++) {
			listToSort[i] = rand.nextInt(10000);
		}
		listToSort = MergeSort.sort(listToSort);
		System.out.println("Total operations: " + MergeSort.operations);
		for(int x: listToSort) {
			System.out.println(x);
		}*/
		
		/*SINGLY LINKED LIST*/
		/*SinglyLinkedList list = new SinglyLinkedList();
		list.sortedInsert(45);
		list.sortedInsert(5);
		list.sortedInsert(12);
		list.sortedInsert(32);
		list.sortedInsert(4);
		//System.out.println(list.delete(5));
		//list.reverseTraverse();
		//list.reverse();
		list.traverse();*/
		//System.out.println(list.search(34));*/
		
		/*BINARY SEARCH TREE*/
		/*BinarySearchTree bst = new BinarySearchTree();
		bst.insert(23);
		bst.insert(14);
		bst.insert(7);
		bst.insert(9);
		bst.insert(31);
		bst.insert(25);
		bst.insert(24);
		bst.insert(30);
		bst.preorderTraverse();
		bst.inorderTraverse();
		bst.postorderTraverse();
		bst.breadthFirstTraverse();
		//System.out.println(bst.delete(25));
		//bst.inorderTraverse();
		//bst.preorderTraverse();
		//System.out.println(bst.getMax());*/
		
		/*QUEUE*/
		/*Queue q = new Queue();
		q.push(1);
		q.push(2);
		q.push(3);
		q.push(4);
		q.push(5);
		while(!q.isEmpty()) {
			System.out.println(q.front());
			q.pop();
		}*/
		
		/*PRIORITY QUEUE*/
		/*PriorityQueue pq = new PriorityQueue();
		Random rand = new Random();
		for(int i = 0; i<10; i++) {
			pq.push(rand.nextInt(100));
		}
		while(!pq.isEmpty()) {
			System.out.println(pq.front());
			pq.pop();
		}*/
		
		/*STACK*/
		/*Stack s = new Stack();
		s.push(1);
		s.push(2);
		s.push(3);
		s.push(4);
		s.push(5);
		while(!s.isEmpty()) {
			System.out.println(s.top());
			s.pop();
		}*/
		
		/*HEAP*/
		/*Heap heap = new MaxHeap(3);
		heap.insert(3);
		heap.print();
		heap.insert(9);
		heap.print();
		heap.insert(12);
		heap.print();
		heap.insert(7);
		heap.print();
		heap.insert(1);
		heap.print();
		System.out.println(heap.delete(12));
		heap.print();*/
		
		/*NOOBSORTS*/
		//int[] list = {5, 4, 3, 2, 1};
		/*int[] list = new int[30];
		Random rand = new Random();
		for(int i = 0; i<30; i++) {
			list[i] = rand.nextInt(100000);
		}
		//list = MergeSort.sort(list);
		//NoobSort.insertionSort(list);
		//NoobSort.selectionSort(list);
		for(int x: list) {
			System.out.print(x + " ");
		}
		System.out.println();
		//NoobSort.countingSort(list);
		NoobSort.radixSort(list);
		for(int x: list) {
			System.out.print(x + " ");
		}
		System.out.println();*/
		//QuickSort.sort(list);
		/*for(int x: list) {
			System.out.print(x + " ");
		}*/
		//System.out.println();
		
		/*AVL TREE*/
		/*AVLTree tree = new AVLTree();
		Random rand = new Random();
		for(int i = 0; i<20; i++) {
			tree.insert(rand.nextInt(100));
		}
		tree.preorderTraverse();
		tree.inorderTraverse();
		//tree.insert(7);
		//tree.insert(23);*/
		
		/*HASH TABLE*/
		/*HashTable<Integer, String> hashtable = new HashTable<Integer, String>();
		hashtable.put(1, "sumusumu");
		hashtable.put(2, "ssfucking-9");
		hashtable.put(3, "number10");
		hashtable.put(4, "kalavuna");
		hashtable.put(5, "haukka");
		hashtable.get(1);
		System.out.println(hashtable.exists(78));
		hashtable.print();*/
		
		/*TRIE*/
		/*Trie trie = new Trie();
		trie.addWord("there");
		trie.addWord("their");
		trie.addWord("answer");
		trie.addWord("any");
		trie.addWord("buyer");
		trie.addWord("the");
		trie.addWord("a");
		trie.addWord("t");
		System.out.println(trie.searchWord("buyer"));
		trie.printTree();*/
		
		/*SEGMENT TREE*/
		//int[] list = {56, 33, 13, 70, 42};
		/*int[] list = {14, 3, 12, 1, 4};
		SegmentTree segmentTree = new SegmentTree(list);
		segmentTree.print();
		segmentTree.update(1,  56);
		segmentTree.update(2,  33);
		segmentTree.update(3,  13);
		segmentTree.update(4,  70);
		segmentTree.update(5,  42);
		segmentTree.print();
		System.out.println(segmentTree.query(2, 4));*/
		
		/*GRAPH*/
		/*Graph graph = new UndirectedGraph(7);
		graph.addEdge(1, 2);
		graph.addEdge(1, 3);
		graph.addEdge(1, 4);
		graph.addEdge(1, 5);
		graph.addEdge(2, 5);
		graph.addEdge(2, 6);
		graph.addEdge(3, 4);*/
		//graph.printAdjacencies();
		//graph.bfs(4);
		//graph.bfsPrint(4);
		//graph.shortestPath(4, 6);
		//System.out.println(graph.hasCycle());
		//graph.dfsPrint(4);
		
		/*UnweightedGraph graph = new DirectedGraph(7);
		graph.addEdge(1, 2);
		graph.addEdge(1, 3);
		graph.addEdge(1, 4);
		graph.addEdge(1, 5);
		graph.addEdge(2, 5);
		graph.addEdge(2, 6);
		graph.addEdge(3, 4);
		graph.printAdjacencies();
		graph.bfsPrint(1);
		graph.dfsPrint(1);
		System.out.println(graph.connectedComponents());*/
		
		/*WeightedGraph graph = new WeightedGraph(9, false);
		graph.addEdge(0, 1, 4);
		graph.addEdge(0, 7, 8);
		graph.addEdge(1, 2, 8);
		graph.addEdge(2, 3, 7);
		graph.addEdge(3, 4, 9);
		graph.addEdge(4, 5, 10);
		graph.addEdge(5, 6, 2);
		graph.addEdge(6, 7, 1);
		graph.addEdge(1, 7, 11);
		graph.addEdge(3, 5, 14);
		graph.addEdge(2, 5, 4);
		graph.addEdge(7, 8, 7);
		graph.addEdge(6, 8, 6);
		graph.addEdge(2, 8, 2);*/
		//graph.printAdjacencies();
		/*int[] sssp = graph.dijkstra(0);
		for(int i = 0; i<sssp.length; i++) {
			System.out.println(i + ": " + sssp[i]);
		}
		graph.printSSSPs(0);*/
		//graph.prim();
		//graph.kruskal();
		
		/*WeightedGraph graph = new WeightedGraph(5, true);
		graph.addEdge(0, 1, -1);
		graph.addEdge(0, 2, 4);
		graph.addEdge(1, 2, 3);
		graph.addEdge(1, 3, 2);
		graph.addEdge(1, 4, 2);
		graph.addEdge(3, 2, 5);
		graph.addEdge(3, 1, 1);
		graph.addEdge(4, 3, -3);
		//graph.printAdjacencies();
		int[] sssp = graph.bellmanFord(0);
		for(int i = 0; i<sssp.length; i++) {
			System.out.println(i + ": " + sssp[i]);
		}
		graph.printSSSPs(0);*/
		
		/*WeightedGraph graph = new WeightedGraph(4, true);
		graph.addEdge(0, 1, 5);
		graph.addEdge(0, 3, 10);
		graph.addEdge(1, 2, 3);
		graph.addEdge(2, 3, 1);
		int[][] apsp = graph.floydWarshall();
		for(int i = 0; i<apsp.length; i++) {
			System.out.print(i + ": ");
			for(int j = 0; j<apsp[0].length; j++) {
				System.out.print(j + "(" + apsp[i][j] + ") ");
			}
			System.out.println();
		}*/
		
		/*WeightedGraph graph = new WeightedGraph(8, false);
		graph.addEdge(1 - 1, 2 - 1, 4);
		graph.addEdge(1 - 1, 4 - 1, 10);
		graph.addEdge(1 - 1, 5 - 1, 2);
		graph.addEdge(2 - 1, 3 - 1, 18);
		graph.addEdge(2 - 1, 4 - 1, 8);
		graph.addEdge(3 - 1, 4 - 1, 11);
		graph.addEdge(4 - 1, 5 - 1, 5);
		graph.addEdge(4 - 1, 7 - 1, 11);
		graph.addEdge(4 - 1, 8 - 1, 9);
		graph.addEdge(6 - 1, 7 - 1, 1);
		graph.addEdge(6 - 1, 8 - 1, 2);
		graph.prim();*/
		
		/*BINARY SEARCH*/
		/*int[] list = {3, 5, 7, 8, 10, 15, 18, 20};
		System.out.println(Misc.binarySearch(list, 7));*/
		
		/*SIEVE*/
		/*int[] primes = Misc.getPrimes(25);
		for(int x: primes) {
			System.out.println(x);
		}*/
	}
}
