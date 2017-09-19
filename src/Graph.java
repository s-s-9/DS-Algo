abstract class UnweightedGraph {
	//our graph can have specific number of nodes fron 0 to nodes-1
	int nodes;
	int edges;
	//visited, cost and parent arrays; will need them later
	boolean[] visited;
	int[] cost;
	int[] parent;
	//variable to store whether the graph has a cycle
	boolean hasCycle = false;
	//constructor
	UnweightedGraph(int nodes){
		this.nodes = nodes;
		edges = 0;
		visited = new boolean[nodes];
		cost = new int[nodes];
		parent = new int[nodes];
	}
	//add an edge
	public abstract boolean addEdge(int u, int v);
	//print the adjacencies
	public abstract void printAdjacencies();
	//bfs
	public abstract boolean bfs(int source);
	//print nodes along a breadth first traversal
	public void bfsPrint(int source) {
		//run bfs
		bfs(source);
		System.out.println("BFS...");
		for(int i = 0; i<nodes; i++) {
			if(visited[i]) {
				System.out.print(i + "(cost: " + cost[i] + ", parent: " + parent[i] + ")" + " ");
			}
		}
		System.out.println();
	}
	//dfs
	public abstract boolean dfs(int source);
	//print nodes along depth first traversal
	public void dfsPrint(int source) {
		//run dfs
		dfs(source);
		//print visited nodes with their parents
		System.out.println("DFS...");
		for(int i = 0; i<nodes; i++) {
			if(visited[i]) {
				System.out.print(i + "(parent: " + parent[i] + ") ");
			}
		}
		System.out.println();
	}
	//get the shortest path
	public int shortestPath(int from, int to) {
		//check bounds
		if(from<0 || from>=nodes || to<0 || to>=nodes) {
			System.out.println("Bullshit!");
			return -1;
		}
		//run bfs from source
		bfs(from);
		//if we could not reach the destination, then return -1
		if(!visited[to]) {
			System.out.println("Unreachable!");
			return -1;
		}
		//output the path length
		System.out.println("Cost: " + cost[to]);
		//output the path
		System.out.println("Path: ");
		printPath(to);
		System.out.println();
		return cost[to];
	}
	//print the path from source to destination
	public void printPath(int current) {
		//if source is negative, then stop
		if(current==-1) {
			return;
		}
		printPath(parent[current]);
		System.out.print(current + " ");
	}
	//return whether the graph has a cycle
	public boolean hasCycle() {
		//run bfs starting with every source
		for(int i = 0; i<nodes; i++) {
			bfs(i);
			//check if a cycle has already been found
			if(hasCycle) {
				return true;
			}
		}
		if(hasCycle) {
			return true;
		}
		else {
			return false;
		}
	}
	//get the number of connected components
	public int connectedComponents() {
		int components = 0;
		for(int i = 0; i<nodes; i++) {
			if(!visited[i]) {
				dfs(i);
				components++;
			}
		}
		return components;
	}
}

class DirectedGraph extends UnweightedGraph{
	//we will represent directed graphs as adjacency list
	SinglyLinkedList[] adjacencyList;
	//constructor
	DirectedGraph(int nodes){
		super(nodes);
		//we will represent directed graphs as adjacency list
		adjacencyList = new SinglyLinkedList[nodes];
		for(int i = 0; i<nodes; i++) {
			adjacencyList[i] = new SinglyLinkedList();
		}
	}
	//add an edge
	public boolean addEdge(int u, int v) {
		//if any edge is out of range return false
		if(u<0 || u>=nodes || v<0 || v>=nodes) {
			return false;
		}
		//directed graph will only have one directional edges (from u to v)
		adjacencyList[u].append(v);
		return true;
	}
	//print the adjacencies
	public void printAdjacencies() {
		for(int i = 0; i<nodes; i++) {
			System.out.print(i + ": ");
			SinglyListNode current = adjacencyList[i].head;
			while(current!=null) {
				System.out.print(current.value + " ");
				current = current.next;
			}
			System.out.println();
		}
	}
	//bfs
	public boolean bfs(int source) {
		//if source node is not in the graph, return false
		if(source<0 || source>=nodes) {
			return false;
		}
		//reset visited, cost and parent
		for(int i = 0; i<nodes; i++) {
			visited[i] = false;
			cost[i] = 0;
			parent[i] = -1;
		}
		//need a queue
		Queue q = new Queue();
		//push the source into queue
		q.push(source);
		//mark the source as visited
		visited[source] = true;
		//cost of source is 0
		cost[source] = 0;
		//there is no parent of source
		parent[source] = -1;
		//keep visiting adjacent nodes if not visited yet
		while(!q.isEmpty()) {
			//get the front node
			int current = q.front();	
			//remove this from the queue
			q.pop();
			//keep adding unvisited adjacent nodes to the queue
			for(SinglyListNode node = adjacencyList[current].head; node!=null; node = node.next) {
				//save the value of this node
				int neighbor = node.value;
				if(visited[neighbor]==false) {
					q.push(neighbor);
					//mark this as visited
					visited[neighbor] = true;
					//set the cost
					cost[neighbor] = cost[current] + 1;
					//set current as the neighbor's parent
					parent[neighbor] = current;
				}
				else {
					hasCycle = true;
				}
			}
		}
		return true;
	}
	//dfs
	public boolean dfs(int source) {
		//if index out of bounds, return
		if(source<0 || source>=nodes) {
			return false;
		}
		//clear visited and parent
		for(int i = 0; i<nodes; i++) {
			visited[i] = false;
			parent[i] = -1;
		}
		//perform actual dfs
		dfsActual(source, -1);
		return true;
	}
	public void dfsActual(int source, int p) {
		//visit the source
		visited[source] = true;
		//set the parent
		parent[source] = p;
		//visit unvisited nodes
		for(SinglyListNode node = adjacencyList[source].head; node!=null; node = node.next) {
			//save the neighbor
			int neighbor = node.value;
			if(!visited[neighbor]) {
				dfsActual(neighbor, source);
			}
			else {
				hasCycle = true;
			}
		}
	}
}

class UndirectedGraph extends UnweightedGraph{
	//we will represent undirected graphs as adjacency matrix
	int[][] adjacencyMatrix;
	//constructor
	UndirectedGraph(int nodes){
		super(nodes);
		adjacencyMatrix = new int[nodes][nodes];
	}
	//add an edge
	public boolean addEdge(int u, int v) {
		//if either node is not within range, then return false
		if(u<0 || u>=nodes || v<0 || v>=nodes) {
			return false;
		}
		//create adjacency
		adjacencyMatrix[u][v] = 1;
		adjacencyMatrix[v][u] = 1;
		return true;
	}
	//print the adjacencies
	public void printAdjacencies() {
		for(int i = 0; i<nodes; i++) {
			System.out.print(i + ": ");
			for(int j = 0; j<nodes; j++) {
				if(adjacencyMatrix[i][j]==1) {
					System.out.print(j + " ");
				}
			}
			System.out.println();
		}
	}
	//bfs
	public boolean bfs(int source) {
		//if source node is not in the graph, return false
		if(source<0 || source>=nodes) {
			return false;
		}
		//reset visited, cost and parent
		for(int i = 0; i<nodes; i++) {
			visited[i] = false;
			cost[i] = 0;
			parent[i] = -1;
		}
		//need a queue
		Queue q = new Queue();
		//push the source into queue
		q.push(source);
		//mark the source as visited
		visited[source] = true;
		//cost of source is 0
		cost[source] = 0;
		//there is no parent of source
		parent[source] = -1;
		//keep visiting adjacent nodes if not visited yet
		while(!q.isEmpty()) {
			//get the front node
			int current = q.front();	
			//remove this from the queue
			q.pop();
			//keep adding unvisited adjacent nodes to the queue
			for(int i = 0; i<nodes; i++) {
				if(adjacencyMatrix[current][i]==1) {
					if(visited[i]==false) {
						q.push(i);
						//mark this as visited
						visited[i] = true;
						//set the cost
						cost[i] = cost[current] + 1;
						//set current as the neighbor's parent
						parent[i] = current;
					}
					else {
						hasCycle = true;
					}
				}
			}
		}
		return true;
	}
	//dfs
	public boolean dfs(int source) {
		//if index out of bounds, return
		if(source<0 || source>=nodes) {
			return false;
		}
		//clear visited and parent
		for(int i = 0; i<nodes; i++) {
			visited[i] = false;
			parent[i] = -1;
		}
		//perform actual dfs
		dfsActual(source, -1);
		return true;
	}
	public void dfsActual(int source, int p) {
		//visit the source
		visited[source] = true;
		//set the parent
		parent[source] = p;
		//visit unvisited nodes
		for(int i = 0; i<nodes; i++) {
			if(adjacencyMatrix[source][i]==1) {
				if(!visited[i]) {
					dfsActual(i, source);
				}
				else {
					hasCycle = true;
				}
			}
		}
	}
}

class WeightedGraph {
	//will be represented by an adjacency matrix, and the graph is undirected by design
	int[][] adjacencyMatrix;
	//record the number of nodes and edges
	int nodes, edges;
	//keep track of the visited nodes and parents of each node
	boolean[] visited;
	int[] parent;
	//note if the graph has negative weights (as dijkstra cannot be implemented with negative weights involved)
	boolean negativeweights;
	//note if the graph is directed or not
	boolean directed;
	//constructor
	WeightedGraph(int nodes, boolean directed){
		adjacencyMatrix = new int[nodes][nodes];
		for(int i = 0; i<nodes; i++) {
			for(int j = 0; j<nodes; j++) {
				//vertices not connected will have infinite as weight
				if(i==j) {
					adjacencyMatrix[i][j] = 0;
				}
				//it costs 0 for a node to go from itself to itself
				else {
					adjacencyMatrix[i][j] = Integer.MAX_VALUE;
				}
			}
		}
		visited = new boolean[nodes];
		parent = new int[nodes];
		this.nodes = nodes;
		this.edges = 0;
		this.directed = directed;
		negativeweights = false;
		parent = new int[nodes];
		for(int i = 0; i<nodes; i++) {
			visited[i] = false;
			parent[i] = -1;
		}
	}
	//add an edge
	public boolean addEdge(int u, int v, int weight) {
		//if edge is out of bounds or weight equals 0, return false
		if(u<0 || u>=nodes || v<0 || v>=nodes || weight==0) {
			return false;
		}
		//if weight is negative, note it
		if(weight<0) {
			negativeweights = true;
		}
		//add the edge
		adjacencyMatrix[u][v] = weight;
		if(!directed) {
			adjacencyMatrix[v][u] = weight;
		}
		return true;
	}
	//print the adjacencies
	public void printAdjacencies() {
		for(int i = 0; i<nodes; i++) {
			System.out.print(i + ": ");
			for(int j = 0; j<nodes; j++) {
				if(adjacencyMatrix[i][j]!=Integer.MAX_VALUE && i!=j) {
					System.out.print(j + "(" + adjacencyMatrix[i][j] + ") ");
				}
			}
			System.out.println();
		}
	}
	//dijkstra (single source shortest path) V^2
	public int[] dijkstra(int source) {
		//if graph has negative weights, return null
		if(negativeweights) {
			return null;
		}
		//clear visited and parent
		for(int i = 0; i<nodes; i++) {
			visited[i] = false;
			parent[i] = -1;
		}
		//this will hold the result
		int[] distances = new int[nodes];
		//initially all distances are infinity
		for(int i = 0; i<nodes; i++) {
			distances[i] = Integer.MAX_VALUE;
		}
		//except the source has distance 0
		distances[source] = 0;
		//visit the source
		visited[source] = true;
		//this will tell whether a node has been done or not
		boolean[] done = new boolean[nodes];
		//loop until every element is done (as we have to calculate shortest path to all nodes)
		for(int i = 0; i<nodes; i++) {
			//get the node with the least distance from the source
			int closest = getClosest(distances, done);
			//mark this node as done as it will be soon
			done[closest] = true;
			//traverse its neighbors and try to update each one
			for(int j = 0; j<nodes; j++) {
				//filter out the non-neighbors
				if(adjacencyMatrix[closest][j]!=Integer.MAX_VALUE) {
					//save the neighbor
					int neighbor = j;
					//if the closest node has infinity distance, it means it has no connection with the source, so ignore it
					if(distances[closest]!=Integer.MAX_VALUE) {
						//visit this node
						visited[neighbor] = true;
						//see if this neighbor's distance can be updated
						if(distances[closest] + adjacencyMatrix[closest][j]<distances[neighbor]) {
							distances[neighbor] = distances[closest] + adjacencyMatrix[closest][j];
							//update parent too
							parent[neighbor] = closest;
						}
					}
				}
			}
		}
		return distances;
	}
	public int getClosest(int[] distances, boolean[] done) {
		int closest = -1;
		int min = Integer.MAX_VALUE;
		for(int i = 0; i<nodes; i++) {
			if(done[i]==false && distances[i]<=min) {
				min = distances[i];
				closest = i;
			}
		}
		return closest;
	}
	//bellman-ford (single source shortest path) VE
	public int[] bellmanFord(int source) {
		//if source out of bounds, return null
		if(source<0 || source>=nodes) {
			return null;
		}
		Edge[] edges = getEdges();
		//initially set every node's distance to infinity
		int[] distances = new int[nodes];
		for(int i = 0; i<nodes; i++) {
			distances[i] = Integer.MAX_VALUE;
		}
		//set source's distance to 0
		distances[source] = 0;
		//visit the source
		visited[source] = true;
		//run this loop nodes-1 times, because paths without cycle cannot contain all nodes of the graph
		for(int i = 1; i<=nodes-1; i++) {
			//the i-th iteration can work with paths with length i
			for(int j = 0; j<edges.length; j++) {
				//save the current edge
				Edge current = edges[j];
				//see if the distance can be updated
				if(distances[current.from]!=Integer.MAX_VALUE) {
					if(distances[current.from] + current.weight < distances[current.to]) {
						distances[current.to] = distances[current.from] + current.weight;
						visited[current.to] = true;
						parent[current.to] = current.from;
					}
				}
			}
		}
		//iterate for the node-th time to find negative weight cycles
		for(int i = 0; i<edges.length; i++) {
			Edge current = edges[i];
			if(distances[current.from]!=Integer.MAX_VALUE) {
				if(distances[current.from] + current.weight < distances[current.to]) {
					//System.out.print(current.from + ", " + current.to + ", " + current.weight + " ");
					System.out.println("Eat shit!");
					return null;
				}
			}
		}
		return distances;
	}
	//floyd-warshall (all-pair shortest paths) V^3
	public int[][] floydWarshall(){
		//this will hold the result
		int[][] distances = new int[nodes][nodes];
		//initialize with the original adjacencies
		for(int i = 0; i<nodes; i++) {
			for(int j = 0; j<nodes; j++) {
				distances[i][j] = adjacencyMatrix[i][j];
			}
		}
		//before the k-th iteration, nodes upto k-1 was considered as intermediate nodes
		for(int k = 0; k<nodes; k++) {
			//consider each node as source
			for(int i = 0; i<nodes; i++) {
				//consider each node as a destination from the source
				for(int j = 0; j<nodes; j++) {
					if(distances[i][k]!=Integer.MAX_VALUE && distances[k][j]!=Integer.MAX_VALUE 
					   && distances[i][k] + distances[k][j]<distances[i][j]) {
						distances[i][j] = distances[i][k] + distances[k][j];
					}
				}
			}
			//after the k-th iteration, node k has been examined as an intermediate node between i to j
		}
		return distances;
	}
	//print all single source shortest paths
	public void printSSSPs(int source) {
		int[] sssp;
		//if graph has negative weights, then dijkstra won't work as it finalizes early, but bellman-ford hangs on till the last
		if(negativeweights) {
			sssp = bellmanFord(source);
		}
		//else run dijkstra
		else {
			sssp = dijkstra(source);
		}
		if(sssp==null) {
			return;
		}
		//print the path for each node
		for(int i = 0; i<nodes; i++) {
			if(visited[i]) {
				System.out.print(i + ": ");
				printSSSP(i);
				System.out.println("cost = " + sssp[i]);
			}
		}
	}
	public void printSSSP(int current) {
		if(current==-1) {
			return;
		}
		printSSSP(parent[current]);
		System.out.print(current + " ");
	}
	//minimum spanning trees
	public void prim() {
		//prim is not applicable for directed graphs
		if(directed) {
			System.out.println("Directed graphs not allowed!");
			return;
		}
		//see if the graph is connected, if not, separate mst's would have to be built, which we won't do here
		int scc = 0;
		for(int i = 0; i<nodes; i++) {
			if(!visited[i]) {
				dfs(i);
				scc++;
			}
		}
		if(scc>1) {
			System.out.println("Multiple strongly connected components!");
			return;
		}
		//get the edges
		Edge[] edges = getEdges();
		//this will denote if a node has been done
		boolean[] done = new boolean[nodes];
		//this will denote if an edge has been taken
		boolean[] taken = new boolean[edges.length];
		//add node 0 to the mst (any arbitrary node will do)
		done[0] = true;
		//keep looping until all the nodes are done
		int doneNodes = 1;
		while(doneNodes<nodes) {
			//get the index of the edge with the minimum weight that connects a done node with a not-yet-done node
			int closest = getClosestPrim(done, edges);
			//mark the destination as done
			done[edges[closest].to] = true;
			//mark the edge as taken
			taken[closest] = true;
			//increment doneNodes
			doneNodes++;
		}
		//print the mst
		System.out.println("MST by Prim's algorithm");
		for(int i = 0; i<edges.length; i++) {
			if(taken[i]) {
				System.out.println(edges[i].from + "---" + edges[i].to + ": " + edges[i].weight);
			}
		}
	}
	public void kruskal() {
		//kruskal is not applicable for directed graphs
		if(directed) {
			System.out.println("Directed graphs not allowed!");
			return;
		}
		//see if the graph is connected, if not, separate mst's would have to be built, which we won't do here
		int scc = 0;
		for(int i = 0; i<nodes; i++) {
			if(!visited[i]) {
				dfs(i);
				scc++;
			}
		}
		if(scc>1) {
			System.out.println("Multiple strongly connected components!");
			return;
		}
		//get the edges
		Edge[] edges = getEdges();
		//this will note whether an edge has been taken or not
		boolean[] taken = new boolean[edges.length];
		//create representatives of nodes so that we can tell whether 2 nodes belong to an already found subtree
		int[] representatives = new int[nodes];
		//initially all nodes are representatives of themselves
		for(int i = 0; i<nodes; i++) {
			representatives[i] = i;
		}
		//keep adding the edge that has minimum weight given that it connects 2 edges from different subgraphs
		int addedEdges = 0;
		while(addedEdges<nodes-1) {
			//get the desired edge (having minimum weight and connecting 2 nodes that belong to different subgraphs
			int closest = getClosestKruskal(edges, representatives);
			//as an edge has been established, make sure the 2 ends of the edge has the same representative
			union(edges[closest].from, edges[closest].to, representatives);
			//mark this edge as taken
			taken[closest] = true;
			addedEdges++;
		}
		System.out.println("MST by Kruskal's algorithm");
		for(int i = 0; i<edges.length; i++) {
			if(taken[i]) {
				System.out.println(edges[i].from + "---" + edges[i].to + ": " + edges[i].weight);
			}
		}
	}
	public void union(int a, int b, int[] representatives) {
		int u = find(a, representatives);
		int v = find(b, representatives);
		representatives[u] = v;
	}
	public int find(int a, int[] representatives) {
		if(representatives[a]==a) {
			return a;
		}
		else {
			//representatives[a] = find(representatives[a], representatives);
			//return representatives[a];
			return find(representatives[a], representatives);
		}
	}
	int getClosestPrim(boolean[] done, Edge[] edges) {
		int closest = -1;
		int min = Integer.MAX_VALUE;
		for(int i = 0; i<edges.length; i++) {
			//save the current edge
			Edge e = edges[i];
			//the edge will connect a done node with a not done node and have the minimum weight among such nodes
			if(e.weight<min && done[e.from]==true && done[e.to]==false) {
				min = e.weight;
				closest = i;
			}
		}
		return closest;
	}
	int getClosestKruskal(Edge[] edges, int[] representatives) {
		int closest = -1;
		int min = Integer.MAX_VALUE;
		for(int i = 0; i<edges.length; i++) {
			Edge e = edges[i];
			//check if this edge has the minimum weight
			if(e.weight<min) {
				//check if this edge connects 2 nodes from different subgraphs
				int u = find(e.from, representatives);
				int v = find(e.to, representatives);
				if(u!=v) {
					//update min and closest
					min = e.weight;
					closest = i;
				}
			}
		}
		return closest;
	}
	public void dfs(int source) {
		//visit the source
		visited[source] = true;
		//visit the neighbors
		for(int i = 0; i<nodes; i++) {
			if(visited[i]==false && adjacencyMatrix[source][i]!=Integer.MAX_VALUE) {
				dfs(i);
			}
		}
	}
	public Edge[] getEdges() {
		//count the number of edges
		int e = 0;
		for(int i = 0; i<nodes; i++) {
			for(int j = 0; j<nodes; j++) {
				if(adjacencyMatrix[i][j]!=Integer.MAX_VALUE) {
					e++;
				}
			}
		}
		//create array of edges
		Edge[] edges = new Edge[e];
		int index = 0;
		for(int i = 0; i<nodes; i++) {
			for(int j = 0; j<nodes; j++) {
				if(adjacencyMatrix[i][j]!=Integer.MAX_VALUE) {
					edges[index] = new Edge(i, j, adjacencyMatrix[i][j]);
					index++;
				}
			}
		}
		return edges;
	}

}

class Edge{
	int from, to, weight;
	//constructor
	Edge(int from, int to, int weight){
		this.from = from;
		this.to = to;
		this.weight = weight;
	}
}
