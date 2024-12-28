//Statement to actually generate the random numbers (int rand = (int) Math.floor(Math.random() * (max - min + 1) + min);) taken from Educative, I wrote the rest of the code
import java.util.ArrayList;
//This class provides a constructor for the RandNums20 object and a getNums method. However, this constructor is empty as this object doesn't actually contain any data, it only needs to be instantiated so that the getNums method can be called. The getNums method performs the tasks required to acheive the purpose of this class, it uses the java built-in random number generator to generate 20 random numbers (between 1 and 20 inclusive) then gets rid of any duplicate numbers that were generated and returns the list of unique randomly generated numbers as an Integer array. 
public class RandNums20 {
	//constructor (it is empty because this object only needs to be instantiated so that the genNums method can be called, it doesn't actually contain any data)
	public RandNums20() {
	}
	//this method uses the java built-in random number generator to generate 20 random numbers (between 1 and 20 inclusive) then gets rid of any duplicate numbers that were generated and returns the list of unique randomly generated numbers as an Integer array 
	public Integer[] genNums() {
		//initialize an arraylist of Integers to store the random numbers
		ArrayList<Integer> list = new ArrayList<>();
		//initialize max and min variables and set them to 20 and 1 respectively (to be used as the "bounds" for the random number generator)
		int max = 20;
		int min = 1;
		//iterate through a for loop 20 times (to generate 20 numbers)
		for (int i = 0; i < max; i++) {
			//initialize a rand variable to store the current random number, call the java built-in random method to generate a random number between 0 and 1 (inclusive) then multiply this number by max - min + 1 then add min then call the java built-in floor method and cast the number to an int to generate a random integer between 1 and 20 (inclusive), set the rand variable to this number 
			int rand = (int) Math.floor(Math.random() * (max - min + 1) + min);
			//if the arraylist doesn't already contain the current random number (this way duplicate numbers are discarded)
			if (!list.contains(rand)) {
				//add the current random number to the arraylist
				list.add(rand);
			}
			
		}
		//initialize an arrf variable to store the final Integer array of randomly generated numbers, call the java built-in toArray array method on the arraylist of random numbers to convert the arraylist of random numbers to an Integer array of random numbers (that is the size that the list was), set the arrf array to this array
		Integer[] arrf = list.toArray(new Integer[list.size()]);
		//return the final Integer array of unique randomly generated numbers between 1 and 20 (inclusive)
		return arrf;
	}

}
