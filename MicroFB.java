package progAssignment3;
//Cheung Lap Yan

import java.util.HashMap;
import java.util.Scanner;


public class MicroFB {
	//create global hash maps to store data
	static HashMap<String, Person> allPeople = new HashMap<String, Person>();
	static HashMap<String, Boolean> allFriends = new HashMap<String, Boolean>();
	public static void main(String[] args) {
		boolean go=true; //sentinel for controlling loop
		while (go==true){
			System.out.print("Command: ");
			Scanner in = new Scanner(System.in);
			String lineCommand = in.nextLine();
			String [] keyCommand = lineCommand.split(" ");
			String methodCall = keyCommand[0]; //isolate for input command and values
			if (methodCall.equalsIgnoreCase("P")){
				//check input and invoke P subroutine
				if (keyCommand.length!=2) System.out.println("Error invokation of P command");
				pMethod(keyCommand[1]);
			}
			else if (methodCall.equalsIgnoreCase("F")){
				//check input and invoke F subroutine
				if (keyCommand.length!=3){
					System.out.println("Error invokation of F command");
				} else fMethod(keyCommand[1],keyCommand[2]);
			}
			else if (methodCall.equalsIgnoreCase("L")){
				//check input and invoke L subroutine
				if (keyCommand.length!=2) System.out.println("Error invokation of L command");
				else lMethod(keyCommand[1]);
			}
			else if (methodCall.equalsIgnoreCase("Q")){
				//check input and invoke Q subroutine
				if (keyCommand.length!=3) System.out.println("Error invokation of Q command");
				else System.out.println(qMethod(keyCommand[1],keyCommand[2]));
			}
			else if (methodCall.equalsIgnoreCase("U")){
				//check input and invoke U subroutine
				if (keyCommand.length!=3) System.out.println("Error invokation of U command");
				else uMethod(keyCommand[1],keyCommand[2]);
			}
			else if (methodCall.equalsIgnoreCase("X")){
				//end program is prompted
				go=false;
			}
			else {
				//display ERROR message if command is invalid
				System.out.println("ERROR command");
			}
		}
		

	}
	public static void pMethod(String name){
		//check if a Person who has already been created is added
		if (allPeople.containsKey(name)==true){
			System.out.println("Name already exists, cannot add");
		}
		else {
			//create a new Person object and add to Hash Table
			Person newEntry = new Person();
			newEntry.name = name;
			allPeople.put(name, newEntry);
		}
	}	

	public static void fMethod(String name1, String name2) {
		//check if the person have the same name
		if (name1==name2){
			System.out.println("Both have the same name");
		//check if both person has already been created
		}else if (allPeople.get(name1)==null || allPeople.get(name2)==null){
			System.out.println("One person has not been created");
		}
		else {
			//create the key with a subroutine
			String friendKey = friendKey(name1,name2);
			//check if they are not friends already
			if (allFriends.containsKey(friendKey)==false || allFriends.get(friendKey)==false){
					//register friendship in hash map
					allFriends.put(friendKey, true);
				//add name2 Person to linked list of name1 as friend
					//add to the list's last node if it is not null
				if (allPeople.get(name1).friends!=null){
					allPeople.get(name1).friends.addLast(allPeople.get(name2));
					//add to first node if it is null
				} else allPeople.get(name1).friends.addFirst(allPeople.get(name2));
				
				if (allPeople.get(name2).friends!=null){
					allPeople.get(name2).friends.addLast(allPeople.get(name1));
				} else allPeople.get(name2).friends.addFirst(allPeople.get(name1));
			} else {
				//display error message if they are already registered as friends
				System.out.println("They are already friends");
			}
		}
	}
	
	public static String friendKey(String name1, String name2){
		//create a key for allFriends hash map
		String friendKey;
		//create the key with names in alphabetical order
		if (name1.compareTo(name2)<0){
			friendKey = name1+"*"+name2;
		} else {
			friendKey = name2+"*"+name1;
		}
		return friendKey;
	}
	
	public static void lMethod(String name) {
		//check if the Person has been created
		if (allPeople.containsKey(name)==false){
			System.out.println(name+ " does not exist");
		} else {
			//Display all friends in the object's linked list
			System.out.println(allPeople.get(name).friends.toString());
		}
	}

	public static String qMethod(String name1, String name2) {
		//check if the people have been created
		if (allPeople.get(name1)==null){
			return (name1+" does not exist");
		} else if (allPeople.get(name2)==null){
			return (name2+" does not exist");
		} else {
			//return the result of whether they are friends from hash map
			String friendKey = friendKey(name1,name2);
			if (allFriends.get(friendKey)==true){
				return "Yes";
			}
			return "No";
		}
	}
	
	public static void uMethod(String name1, String name2){
		//check if the people have been created
		if (allPeople.containsKey(name1) && allPeople.containsKey(name2)){
			//create key for allFriends hash map
			String friendKey = friendKey(name1,name2);
			//check if the two people are friends
			if (allFriends.containsKey(friendKey) && allFriends.get(friendKey)==true){
				//delete friendship from the two objects' linked list
				allPeople.get(name1).friends.deleteFirst(allPeople.get(name2));
				allPeople.get(name2).friends.deleteFirst(allPeople.get(name1));
				//remove record in hash map
				allFriends.remove(friendKey);
			} else {
				//display error message if they are not friends already
				System.out.println("They are not friends");
			}
			//display error message if the person has not been created
		} else System.out.println("A person has not been created");
	}
}
