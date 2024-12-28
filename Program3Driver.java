import java.io.IOException;

//This class contains the main method for program 3. It generates data using a separate class (that uses the java built-in random number generator to generate 20 numbers 1-20 inclusive then gets rid of duplicate numbers), uses methods from other classes to perform the required tasks for this program, and prints the required information to the console.
public class Program3Driver {
	//This is the main method for program 3. It generates data using a separate class (that uses the java built-in random number generator to generate 20 numbers 1-20 inclusive then gets rid of duplicate numbers), uses methods from the 4 other classes to perform the required tasks for this program, and prints the required information to the console.
	public static void main (String[] args) throws IOException {
		//create a new RandNums20 object (so that the method from the RandNums20 class that generates the random numbers can be called)
		RandNums20 r = new RandNums20();
		//call the genNums method from the RandNums20 class to generate 20 numbers 1-20 inclusive then get rid of duplicate numbers and store the remaining numbers returned by this method in an Integer array (input)
		Integer[] input = r.genNums();
		
		//iterate through the input array and print each number followed by a space (this is the sequence that this program will find the longest increasing subsequence in)
		for (int i = 0; i < input.length; i++) {
			System.out.printf("%d ", input[i]);
		}
		//print a new line for the next required output line to go on
		System.out.println();
		//create a variable to store the number of edges in the graph and set its value to 0
		int numedges = 0;
		//for each number in the input, iterate through all of the numbers after it in the input
		for (int i = 0; i < input.length; i++) {
			for (int j = i + 1; j < input.length; j++) {
				//if a number in the input after the current number is greater than the current number, increment the number of edges
				if (input[i] < input[j]) {
					numedges++;
				}
			}
		}
		
		//initialize an int array that is size numedges times 2 plus 2 (so that the array can store the number of vertices and number of edges (+2) and every edge's two integer vertices (numedges * 2)
		int[] edges = new int[numedges * 2 + 2];
		//set first element of the array to the number of vertices and the second element of the array to the number of edges (for the graph constructor) 
		//always set the number of vertices to 21 so that each input number can be a vertex regardless of which/how many numbers are generated (vertex 0 is always a disconnected vertex and any vertices 1-20 not in the input (if there are any) are disconnected vertices as these vertices don't really exist according to the input but including them simplifies the program and doesn't affect its output)
		edges[0] = 21;
		edges[1] = numedges;
		//instance variable for current array index (starting at 2)
		int inc = 2;
		//for each number in the input, iterate through all of the numbers after it in the input
		for (int i = 0; i < input.length; i++) {
			for (int j = i + 1; j < input.length; j++) {
				//if a number in the input after the current number is greater than the current number, add an edge from the current number to this number to the edges array (add each number's integer vertex to the array, current first, then the greater number)
				if (input[i] < input[j]) {
					//add the current number to the edges array (the graph is designed so each number is each number's integer vertex)
					edges[inc] = input[i];
					//increment the current array index
					inc++;
					//add the greater number to the edges array
					edges[inc] = input[j];
					//increment the current array index
					inc++;
				}
			}
		}
		//create a new directed graph with the array containing the number of vertices, number of edges, and all the vertex pairs (edges)
		Digraph g = new Digraph(edges);
		//initialize a variable to store the length of the longest increasing subsequence within the input (calculated by the graph) and set it to 0
		int glongest = 0;
		//for each vertex (starting at 1 as vertex 0 is always disconnected), call the longest path method from the digraph class and iterate through all the paths within the graph starting at this vertex (all the increasing subsequences starting at this number)
		for (int i = 1; i < 21; i++) {
			for (Integer c: g.longestPath(i)) {
				//if length of the current path (increasing subsequence) is greater than the value of the longest increasing subsequence variable, update the value of the longest increasing subsequence variable to the length of the current path
				if (c > glongest) {
					glongest = c;
				}
			}
		}
		//print the length of the longest increasing subsequence as found by the directed graph
		System.out.printf("Graph: Longest increasing subsequence is %d\n", glongest);
		//create a new DynamicLIS object (so that the method from the DynamicLIS class that uses dynamic programming to find the length of the longest increasing subsequence can be called)
		DynamicLIS d = new DynamicLIS();
		//initialize a variable to store the length of the longest increasing subsequence within the input (calculated by dynamic programming), call the dlisLength method with the length of the input array and the input array itself to find the length of the longest increasing subsequence within the input using dynamic programming, and set this variable to the value this method returns
		int dlongest = d.dlisLength(input.length, input);
		//print the length of the longest increasing subsequence as found by dynamic programming
		System.out.printf("DP: Length of longest increasing subsequence is %d\n", dlongest);
	}
	
}
