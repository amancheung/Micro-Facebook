package progAssignment3;
//Cheung Lap Yan

//file MyList1.java from "Lists" lecture
//Singly linked list with header.

class MyList1<T>  {
private T value;
private MyList1<T>  next;

public T setValue(T v) {
 value = v;  return v; }

public MyList1<T> setNext(MyList1<T> n) {
  next = n;  return next; }

public T getValue() {
 return value; }

public MyList1<T> getNext() {
 return next; }

public MyList1<T> locate(T x) { // Locate node containing X
 MyList1<T> n = next;
 while (n != null) {
    if (n.value == x)
      return n;
    n = n.next;
    }
 return null;
}

public MyList1<T> locateBefore(T x) { // Locate node before X
 MyList1<T> n = this;
 while (n.next != null) {
    if (n.next.value == x)
      return n;
    n = n.next;
    }
 return null;
}

//A is a node within the owner. This method adds X after the node A.
//
public void addAfter(MyList1<T> a, T x) { 
 MyList1<T> n = new MyList1<T>();
 n.value = x;
 n.next = a.next;
 a.next = n;
}


//Deletes the node after node A;

public void deleteAfter(MyList1<T> a) {
  a.next = a.next.next; }


public MyList1<T> first() { // First node (after header)
  return next; }

public MyList1<T> last() {  // Last node.
  MyList1<T> n = this;
  while (n.next != null) n = n.next;
  return n;
}

public void addFirst(T x) {   // Add X to the head of the list
  addAfter(this,x); }  

public void addLast(T x) {    // Add X to the tail of the list.
  addAfter(last(),x); }
  
public void deleteFirst(T x) {   // Delete the first occurrence of X
  deleteAfter(locateBefore(x)); }

public MyList1<Integer> append(MyList1<Integer> l, MyList1<Integer> m) {
	MyList1<Integer> newList = new MyList1<Integer>(); //create new linked list
	MyList1<Integer> o1 = l.next; //take the first element of list l
	while (o1 != null) {
		newList.addLast(o1.value); //copy value of l to new list
		o1 = o1.next; //go through the list
	}
	newList.last().setNext(m.first()); //link the new list to list M
	return newList; //return new list
}

public void swap(Integer c) {
	MyList1<T> s1 = this.next; //take first element of list
	MyList1<T> s2 = s1.next; //take second element
	for (int i=0;i<c;i++) {
		s1 = s1.next; //go through list to find the targeted elements
		s2 = s2.next;
	}
	T toSet = s1.getValue(); //save the value of element wanted to be swapped
	s1.setValue(s2.value); //change value of first element to its next element
	s2.setValue(toSet); //vice versa for second element
}

public String toString() {
    MyList1<T> a = next;
    String s = "";
    while (a != null) {
       s = s + a.value.toString() + " ";
       a = a.next;
     }
   return s;
   }
}
