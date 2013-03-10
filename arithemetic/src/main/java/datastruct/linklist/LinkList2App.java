package datastruct.linklist;

////////////////////////////////////////////////////////////////
class LinkList1 {
	private Link first; // ref to first link on list

	// -------------------------------------------------------------
	public LinkList1() // constructor
	{
		first = null; // no links on list yet
	}

	// -------------------------------------------------------------
	public void insertFirst(int id, double dd) { // make new link
		Link newLink = new Link(id, dd);
		newLink.next = first; // it points to old first link
		first = newLink; // now first points to this
	}

	// -------------------------------------------------------------
	public Link find(int key) // find link with given key
	{ // (assumes non-empty list)
		Link current = first; // start at 'first'
		while (current.iData != key) // while no match,
		{
			if (current.next == null) // if end of list,
				return null; // didn't find it
			else
				// not end of list,
				current = current.next; // go to next link
		}
		return current; // found it
	}

	// -------------------------------------------------------------
	public Link delete(int key) // delete link with given key
	{ // (assumes non-empty list)
		Link current = first; // search for link
		Link previous = first;
		while (current.iData != key) {
			if (current.next == null)
				return null; // didn't find it
			else {
				previous = current; // go to next link
				current = current.next;
			}
		} // found it
		if (current == first) // if first link,
			first = first.next; // change first
		else
			// otherwise,
			previous.next = current.next; // bypass it
		return current;
	}

	// -------------------------------------------------------------
	public void displayList() // display the list
	{
		System.out.print("List (first-->last): ");
		Link current = first; // start at beginning of list
		while (current != null) // until end of list,
		{
			current.displayLink(); // print data
			current = current.next; // move to next link
		}
		System.out.println("");
	}
	// -------------------------------------------------------------
} // end class LinkList
// //////////////////////////////////////////////////////////////

public class LinkList2App {
	public static void main(String[] args) {
		LinkList1 theList = new LinkList1(); // make list

		theList.insertFirst(22, 2.99); // insert 4 items
		theList.insertFirst(44, 4.99);
		theList.insertFirst(66, 6.99);
		theList.insertFirst(88, 8.99);

		theList.displayList(); // display list

		Link f = theList.find(44); // find item
		if (f != null)
			System.out.println("Found link with key " + f.iData);
		else
			System.out.println("Can't find link");

		Link d = theList.delete(66); // delete item
		if (d != null)
			System.out.println("Deleted link with key " + d.iData);
		else
			System.out.println("Can't delete link");

		theList.displayList(); // display list
	} // end main()
} // end class LinkList2App
// //////////////////////////////////////////////////////////////
