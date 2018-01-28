package progAssignment3;
//Cheung Lap Yan

public class Person {
	//Person's name stored as string
	String name;
	//Linked list to record the friends the Person has
	MyList1<Person> friends = new MyList1<Person>();
	
	//specific toString method for correct output
	public String toString(){
		return name;
	}
}
