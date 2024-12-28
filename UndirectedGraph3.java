//Code taken from Algorithms by Sedgewick and Wayne, I edited portions of it
//This class provides instance variables and two constructors to create an undirected graph. The first constructor is called by the second constructor to initialize the graph and takes the number of integer vertices as an argument. The second constructor calls the first constructor to initialize the graph then adds all of the edges to the graph using the addEdges method and takes an integer array that includes number of vertices at index 0, number of edges at index 1, and all integer vertices as argument. This class also includes a getter for the number of vertices within the graph, a getter for the number of edges within the graph, an addEdge method to add an edge between two integer vertices to the graph, and an adj method to create an iterable of the adjacent integer vertices to a given integer vertex.
public class UndirectedGraph3 {
	//instance variables
    private final int V;
    private int E;
    private LinkedList4<Integer>[] adj;
    //constructor with number of integer vertices as argument; this constructor is called by the other constructor to initialize the graph
    public UndirectedGraph3(int V) {
    	//set number of vertices and number of edges
        this.V = V; 
        this.E = 0;
        //initialize an array of linked lists of size number of vertices
        adj = (LinkedList4<Integer>[]) new LinkedList4[V];
        //iterate through the array of linked lists and initialize an integer linked list at each array index
        for (int v = 0; v < V; v++)
           adj[v] = new LinkedList4<Integer>();
    }
    //constructor with integer array that includes number of vertices at index 0, number of edges at index 1, and all integer vertices as argument; this constructor calls the other constructor to initialize the graph then adds all of the edges to the graph using the addEdges method
    public UndirectedGraph3(int[] arr) {
    	//call the other constructor with the number of vertices (obtained from the first index of the given array) to initialize the graph
        this(arr[0]);
        //initialize and store the number of edges (obtained from the second index of the given array)
        int E = arr[1];
        //starting at index 2 (as indices 0 and 1 were number of vertices and number of edges), iterate through the entire input array (increment i by 2 to move to a new integer vertex pair for each loop iteration)
        for (int i = 2; i < E * 2 + 2; i+=2) {
        	//store the integer vertices at indices i and i+1 (the integer vertex pair)
        	int v = arr[i];
        	int w = arr[i+1];
        	//call the add edge method with the integer vertex pair to put an edge between these vertices
        	addEdge(v, w);
        }
    }
    //getter for number of vertices
    public int V() {
	    return V;
    }
    //getter for number of edges
    public int E() {  
	    return E;
    }
    //this method adds an edge between two integer vertices to the graph
    public void addEdge(int v, int w) {
    		//put the second vertex in the first vertex's linked list
    		adj[v].put(w);
    		//put the first vertex in the second vertex's linked list
    		adj[w].put(v);
    		//increment the total number of edges
    		E++;
    }
    //this method creates an iterable of the adjacent integer vertices to a given integer vertex
    public Iterable<Integer> adj(int v) {
    	//call the keys method from the linked list 2 class for the linked list at the given vertex and return the iterable it returns (this linked list contains all of the adjacent integer vertices to the given vertex)
    	return adj[v].keys();
    }
}