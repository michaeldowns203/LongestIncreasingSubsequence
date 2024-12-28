//Code taken from GeeksforGeeks, I edited portions of it
//This class provides a constructor for the DynamicLIS object and a dlisLength method. However, this constructor is empty as this object doesn't actually contain any data, it only needs to be instantiated so that the dlisLength method can be called. The dlisLength method performs the tasks required to acheive the purpose of this class, it finds and returns the length of the longest increasing subsequence in a sequence of numbers (given as an Integer array of size n) using dynamic programming.
public class DynamicLIS {
	//constructor (it is empty because this object only needs to be instantiated so that the dlisLength method can be called, it doesn't actually contain any data)
	public DynamicLIS () {
	}
	//this method finds and returns the length of the longest increasing subsequence in a sequence of numbers (given as an Integer array of size n) using dynamic programming
	public int dlisLength (int n, Integer[] arr) {
		//initialize an int array of size n (the size of the given sequence of numbers) to store the longest increasing subsequence starting from each number in the given sequence
		int[] lis = new int[n];
		//initialize a variable to store the length of the longest increasing subsequence and set it to 0
		int length = 0;
		//iterate through the newly created longest increasing subsequence array and set the value at all indices to 1
		for (int i = 0; i < n; i++) {
			lis[i] = 1;
		}
		//for each number in the given sequence (starting at the second number), iterate through every number before it starting from the first number in the given sequence and ending one number before the current number
		for (int i = 1; i < n; i++) {
			for (int j = 0; j < i; j++) {
				//if the current number in the given sequence (outer loop num) is greater than the current number before it (inner loop num) and the current number has a lower longest increasing subsequence than the current number before it plus one, set the current number's longest increasing subsequence to the current number before it's longest increasing subsequence plus one
				if (arr[i] > arr[j] && lis[i] < lis[j] + 1) {
					lis[i] = lis[j] + 1;
				}
			}
		}
		//iterate through the longest increasing subsequence array
		for (int i = 0; i < n; i++) {
			//if the length stored at the current longest increasing subsequence array index is greater than the value of the variable that stores the length of the longest increasing subsequence, set the variable that stores the length of the longest increasing subsequence to the length stored at the current longest increasing subsequence array index
			if (length < lis[i]) {
				length = lis[i];
			}
		}
		//return the length of the longest increasing subsequence in the given sequence
		return length;
	}
}
