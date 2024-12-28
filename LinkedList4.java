//Code taken from ChatGPT, I edited portions of it
import java.util.ArrayList;
import java.util.List;
//This class provides instance variables and a constructor to create a linked list. It also contains a nested Node class to create the nodes that store the data in the linked list, a put method to put keys into the linked list, a contains method to check if the linked list contains a given key, and a keys method to create an iterable of the keys in the linked list.
public class LinkedList4<Key> {
	//instance variable for the head node
	private Node<Key> head;
	//this class creates the nodes that store the data in the linked list
	private class Node<Key> {
    	//instance variable for key
    	Key key;
        //instance variable for next node in the list
        Node<Key> next;
        //constructor
        Node(Key key) {
        	this.key = key;
            this.next = null;
        } 
    }
	//constructor
	public LinkedList4() {
		this.head = null;
	}
	//this method puts a key into the linked list
	public void put(Key key) {
		//create a new node that contains the key given by the input
        Node<Key> newNode = new Node<>(key);
        //if the list is empty, set the head of the list to the new node
        if (head == null) {
            head = newNode;
        } 
        //else the list is not empty
        else {
        	//initialize a variable to store the current node of the list and set it to the head node of the list
            Node<Key> current = head;
            //while the current node is not the last node in the list
            while (current.next != null) {
            	//set the current node to the next node in the list
                current = current.next;
            }
            //the current node is finally the last node in the list so set the next node in the list to the new node
            current.next = newNode;
        }
    }
	//This method creates an iterable of the keys in the linked list. To do this, it traverses the linked list and adds each node's key to an arraylist of keys, which is returned once the entire linked list has been traversed.
    public Iterable<Key> keys() {
    	//initialize an arraylist of keys that will be used to store each node's key
        List<Key> keyList = new ArrayList<>();
        //initialize a variable to store the current node of the list and set it to the head node of the list
        Node<Key> current = head;
        //while the current node isn't null
        while (current != null) {
        	//add the current node's key to the arraylist of keys
            keyList.add(current.key);
            //set the current node to the next node in the list
            current = current.next;
        }
        //the entire linked list has been traversed, return the complete arraylist of keys
        return keyList;
    }
    //this method checks if the linked list contains a given key
    public boolean contains(Key key) {
        //initialize a variable to store the current node of the list and set it to the head node of the list
        Node<Key> current = head;
        //while the current node isn't null
        while (current != null) {
        	//get and store the current node's key
        	Key k = current.key;
        	//if the given key matches the current node's key, it's found, return true
        	if (k.equals(key)) {
        		return true;
        	}
            //set the current node to the next node in the list
            current = current.next;
        }
        //the entire linked list has been traversed and the given key was not found, return false
        return false;
    }
}

