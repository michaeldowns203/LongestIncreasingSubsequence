//Code taken from Algorithms by Sedgewick and Wayne, I edited portions of it
import java.util.LinkedList;
import java.util.Queue;
//This class provides instance variables and a constructor for the BreadthFirstPaths object. This object is designed to perform a breadth first search on a given undirected graph from a given vertex and store the data this search provides. Simply calling the constructor in the client performs the search while other methods within the class can be called by the client to access the data it provides. It also contains a bfs method to actually perform the breadth first search on a given graph from a given source vertex, a hasPathTo method to check whether or not a path exists between the source vertex and a given vertex, a pathTo method to create an iterable containing all the vertices along the shortest path from the source vertex to a given vertex (inclusive), and getters for both the edgeTo (the array that stores the last vertex on known path from the source vertex to the vertex that is each array index) and distTo (the array that stores the distance from the source vertex to the vertex that is each array index) arrays.
public class BreadthFirstPaths2 {
	 //instance variables
	 //stores the maximum integer value (to initialize distances to later)
	 private static final int INF = Integer.MAX_VALUE;
	 //boolean array to store whether or not each vertex has been visited
     private boolean[] marked;
     //int array to store the last vertex on known path from the source vertex to the vertex that is each array index
     private int[] edgeTo;
     //stores the source vertex
     private final int s;
     //int array to store the distance from the source vertex to the vertex that is each array index
     private int[] distTo;
     //constructor; takes an undirected graph and a source vertex as arguments
     public BreadthFirstPaths2(Digraph G, int s) {
    	//initialize all three arrays; for each array, call the getter for number of vertices in the given undirected graph and set the size of the array to this value
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        distTo = new int[G.V()];
        //initialize the source vertex to the given source vertex
        this.s = s;
        //call the bfs method to actually perform the breadth first search on the given graph from the given source vertex
        bfs(G, s);
     }
     //This method performs a breadth first search on a given graph from a given source vertex. It finds/gets and stores which vertices have been visited, the last vertex on known path from the source vertex to the vertex that is each array index, and the distance from the source vertex to the vertex that is each array index.
     private void bfs(Digraph G, int s) {
    	//initialize a queue with a linked list as the underlying data structure 
        Queue<Integer> queue = new LinkedList<>();
        //iterate through all of the vertices of the given graph
        for (int v = 0; v < G.V(); v++)
        	//set all indices of the distance array to the maximum integer value (this is another way to check if a path exists between the source vertex and a given vertex - if the distance of a vertex is the maximum integer value then a path to it was not found)
        	distTo[v] = -1;
        //set the distance of the source vertex to 0
        distTo[s] = 0;
        //mark the source vertex as visited
        marked[s] = true;
        //add the source vertex to the queue
        queue.add(s);
        //while the queue is not empty (while there exists a connected vertex has not been visited)
        while (!queue.isEmpty()) {
           //remove and store the vertex at the front of the queue
           int v = queue.remove();
           //call the adj method from the undirected graph class for the vertex that was just removed from the queue to get an iterable of all vertices adjacent to it and iterate through the iterable
           for (int w : G.adj(v))
        	  //if the current adjacent vertex has not been visited
              if (!marked[w]) {
            	  //set the adjacent vertex's last vertex on known path from the source vertex to the vertex that was just removed from the queue
            	  edgeTo[w] = v;
            	  //set the adjacent vertex's distance from the source vertex to the vertex that was just removed's distance from the source vertex plus one (as its distance is exactly one further because it is adjacent)
            	  distTo[w] = distTo[v] + 1;
            	  //mark the adjacent vertex as visited
            	  marked[w] = true;
            	  //add the adjacent vertex to the queue so that its adjacent vertices can be processed
            	  queue.add(w);
              }
        }
     }
     //getter for the edgeTo array (the array that stores the last vertex on known path from the source vertex to the vertex that is each array index)
     public int[] getEdgeTo() {
		return edgeTo;
	 }
     //getter for the distTo array (the array that stores the distance from the source vertex to the vertex that is each array index)
	 public int[] getDistTo() {
		return distTo;
	 }
	 //this method checks whether or not a path exists between the source vertex and a given vertex
	 public boolean hasPathTo(int v) {
		//return the value of the marked array at the given vertex (a path exists if the given vertex has been visited, a path does not exist if the given vertex has not been visited)
    	return marked[v];  
     }
	 //this method creates an iterable containing all the vertices along the shortest path from the source vertex to a given vertex (inclusive)
     public Iterable<Integer> pathTo(int v) {
    	//if there is no path from the source vertex to the given vertex, return null
    	if (!hasPathTo(v)) return null;
    	//initialize a linked list to store the vertices along the shortest path from the source vertex to a given vertex (inclusive)
        LinkedList<Integer> path = new LinkedList<>();
        //iterate through each vertex along the shortest path starting with the given vertex and working backwards until the source vertex is reached (the loop is updated with the edgeTo array, x is set to the last vertex on the known path to the source vertex after each iteration (this means the loop works back one vertex towards the source vertex on the shortest path after each iteration))
    	for (int x = v; x != s; x = edgeTo[x])
    		//insert the current vertex at the beginning of the linked list (this way the iterable will be in source to given vertex order)
    		path.addFirst(x);
    	//insert the source vertex at the beginning of the linked list (the final step so that the iterable will be in source to given vertex order)
    	path.addFirst(s);
    	//the entire shortest path has been traversed, return the complete linked list of vertices
    	return path;
    }
}
	
