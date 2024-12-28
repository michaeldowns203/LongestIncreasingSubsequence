//Code taken from Algorithms by Sedgewick and Wayne (graph construction, getters and adj method) and GeeksforGeeks (topologicalSortUtil and longestPath methods), I edited portions of it
import java.util.*;
//This class provides instance variables and two constructors to create a directed graph. The first constructor is called by the second constructor to initialize the graph and takes the number of integer vertices as an argument. The second constructor calls the first constructor to initialize the graph then adds all of the edges to the graph using the addEdges method and takes an integer array that includes number of vertices at index 0, number of edges at index 1, and all vertex pairs (edges) as an argument. This class also includes a getter for the number of vertices within the graph, a getter for the number of edges within the graph, an addEdge method to add an edge between two integer vertices to the graph, an adj method to create an iterable of the adjacent integer vertices to a given integer vertex, a longestPath method to calculate the longest distance from a given integer vertex to each vertex that is reachable from it (each connected vertex), and a topologicalSortUtil method to put the graph's vertices in topological order (which is required for the longestPath method, this method is a helper method for the longestPath method).
public class Digraph {
	//instance variables
    private final int V;
    private int E;
    private LinkedList4<Integer>[] adj;
    //constructor with number of integer vertices as argument; this constructor is called by the other constructor to initialize the graph
    public Digraph(int V) {
    	//set number of vertices and number of edges
        this.V = V;
        this.E = 0;
        //initialize an array of linked lists of size number of vertices
        adj = (LinkedList4<Integer>[]) new LinkedList4[V];
        //iterate through the array of linked lists and initialize an integer linked list at each array index
        for (int v = 0; v < V; v++)
            adj[v] = new LinkedList4<Integer>();
    }
    //constructor with integer array that includes number of vertices at index 0, number of edges at index 1, and all vertex pairs (edges) as argument; this constructor calls the other constructor to initialize the graph then adds all of the edges to the graph using the addEdges method
    public Digraph(int[] arr) {
    	//call the other constructor with the number of vertices (obtained from the first index of the given array) to initialize the graph
        this(arr[0]);
        //initialize and store the number of edges (obtained from the second index of the given array)
        int E = arr[1];
        //starting at index 2 (as indices 0 and 1 were number of vertices and number of edges), iterate through the entire input array (increment i by 2 to move to a new integer vertex pair for each loop iteration)
        for (int i = 2; i < E * 2 + 2; i += 2) {
        	//store the integer vertices at indices i and i+1 (the integer vertex pair)
            int v = arr[i];
            int w = arr[i + 1];
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
        //increment the total number of edges
        E++;
    }
    //this method creates an iterable of the adjacent integer vertices to a given integer vertex
    public Iterable<Integer> adj(int v) {
    	//call the keys method from the linked list 4 class for the linked list at the given vertex and return the iterable it returns (this linked list contains all of the adjacent integer vertices to the given vertex)
        return adj[v].keys();
    }
    //This is a helper method for the longestPath method. It is the method that puts the graph's vertices in topological order. It takes a vertex, an array containing whether each vertex has been visited or not, and a topological order stack as arguments. It recursively calls itself for the first unvisited adjacent vertex of the argument vertex until it reaches a vertex with no unvisited adjacent vertices. Then, it pushes the vertex with no unvisited adjacent vertices to the topological order stack. 
    private void topologicalSortUtil(int v, boolean visited[], Stack<Integer> stack) { 
    	//mark the current vertex as visited 
    	visited[v] = true; 
    	//initialize an arraylist of Integers (a) to store the adjacent vertices to the current vertex, call the keys method for the current vertex to get an iterable of its adjacent vertices and cast this returned iterable to an arraylist of integers, set a to this arraylist
    	ArrayList<Integer> a = (ArrayList<Integer>) adj[v].keys();
    	//iterate through the arraylist of vertices adjacent to the current vertex
    	for (int i = 0; i < a.size(); i++) { 
    		//initialize an avertex variable to store the current adjacent vertex, call the java built-in get method to get the current adjacent vertex from the arraylist of adjacent vertices, set avertex to this vertex
    		int avertex = a.get(i);
    		//if the current adjacent vertex hasn't been visited yet
    		if (!visited[avertex])
    			//recursively call this method for the current adjacent vertex with the given visited array and topological order stack
    			topologicalSortUtil(avertex, visited, stack); 
    	} 

    	//when the recursive calls have finished (the current vertex has no unvisited adjacent vertices), push the current vertex to the topological order stack
    	stack.push(v); 
    } 

    //This method calculates the longest distance from a given integer vertex to each vertex that is reachable from it (each connected vertex). It takes a source vertex as an argument and returns an iterable of all the longest distances from the source vertex to every other connected vertex. To do this, it creates a topological order stack, a distance array, and a visited array. Then, it calls the topologicalSortUtil helper method until every connected vertex has been visited to fill the topological order stack with vertices in topological order (it uses the topologicalSortUtil helper method to topologically sort the graph from each vertex). Then, it pops an element off of the topological order stack and calculates the distance to each vertex adjacent to the vertex that was popped off of the stack based on the vertex that was popped off the stack's distance until the topological order stack is empty (when the stack is empty, all the distances to every connected vertex are correct/fully updated). Lastly, it creates an arraylist of the calculated longest distances of the connected vertices and returns this arraylist as an Integer iterable. 
    public Iterable<Integer> longestPath(int s) { 
    	//initialize a stack of Integers to store each vertex without any unmarked adjacent vertices (stores the topological order of each vertex from the given vertex)
    	Stack<Integer> stack = new Stack<Integer>(); 
    	//initialize an integer array of size number of vertices to store the longest distance from the given vertex to every other vertex
    	int dist[] = new int[V]; 

    	//initialize an integer array of size number of vertices to store whether or not each vertex in the directed graph has been visited
    	boolean visited[] = new boolean[V]; 
    	//iterate through the visited array and set all indices to false (set all vertices to not visited)
    	for (int i = 0; i < V; i++) 
    		visited[i] = false; 

    	//iterate through all of the graph's vertices
    	for (int i = 0; i < V; i++) 
    		//if the current vertex hasn't been visited
    		if (visited[i] == false) 
    			//call the topologicalSortUtil helper function for the current vertex with the visited array and topological order stack (fill the topological order stack / topologically sort the graph from each vertex)
    			topologicalSortUtil(i, visited, stack); 

    	//iterate through all of the graph's vertices
    	for (int i = 0; i < V; i++) 
    		//set all indices of the distance array to the minimum integer value (this is another way to check if a path exists between the source vertex and a given vertex - if the distance of a vertex is the minimum integer value then a path to it was not found)
    		dist[i] = Integer.MIN_VALUE; 
    	//set the distance of the source vertex to 0
    	dist[s] = 0; 
    	
    	//process vertices in topological order
    	//while the topological order stack is not yet empty (there are still vertices in topological order that haven't had their longest distance from the source vertex calculated)
    	while (stack.isEmpty() == false) {
    		//initialize a variable (u) to store the next vertex in topological order (the vertex at the top of the topological order stack), call the java built-in peek method for the topological order stack, set u to the vertex this method returns
    		int u = stack.peek();
    		//call the java built-in pop method for the topological order stack to remove the vertex at the top of the stack from the stack
    		stack.pop(); 
    		
    		//update distances of all adjacent vertices
    		//if the distance to the vertex just removed from the top of the topological order stack isn't the minimum integer value (if a path exist to the vertex just removed from the top of the topological order stack)
    		if (dist[u] != Integer.MIN_VALUE) {
    			//initialize an arraylist of Integers (a) to store the adjacent vertices to the current vertex, call the keys method for the current vertex to get an iterable of its adjacent vertices and cast this returned iterable to an arraylist of integers, set a to this arraylist
    			ArrayList<Integer> a = (ArrayList<Integer>) adj[u].keys();
    			//iterate through the arraylist of vertices adjacent to the current vertex
    			for (int i = 0; i < a.size(); i++) {
    				//initialize an avertex variable to store the current adjacent vertex, call the java built-in get method to get the current adjacent vertex from the arraylist of adjacent vertices, set avertex to this vertex
    				int avertex = a.get(i);
    				//if the distance to the current adjacent vertex is less than the distance to the vertex just removed from the top of the topological order stack plus one
    				if (dist[avertex] < dist[u] + 1) 
    					//set the distance to the current adjacent vertex to the distance to the vertex just removed from the top of the topological order stack plus one
    					dist[avertex] = dist[u] + 1;
    			}
    		} 
    	}
    	//initialize an arraylist of Integers to store each calculated longest distance from the source vertex to each connected vertex
    	ArrayList<Integer> toreturn = new ArrayList<>();
    	//iterate through all of the graph's vertices
    	for (int i = 0; i < V; i++) {
    		//if the distance to the current vertex isn't the minimum integer value (if a path exist to the current vertex)
    		if(dist[i] != Integer.MIN_VALUE) {
    			//add the current vertex's distance plus 1 (so that first number in the subsequence (the source) is included in the distance) to the arraylist of longest distances
    			toreturn.add(dist[i] + 1);
    		}
    	}
    	//return the arraylist of longest distances from the source vertex to each connected vertex
    	return toreturn;
    } 
}
