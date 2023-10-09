package homework;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;

public class LibraryManagmentSystem {
	
	//Quinn Schlussel - 202410-CEN-3024C - 10/8/2023
	//Software Development I - Prof Walauskis
	//LibraryManagmentSystem is the main class, it holds all of the methods and the UI
	//used by the user to organize and manipulate the book list
	//This main class begins by importing the txt file immediately, then asking the user if they'd
	//like to print the whole list, delete an entry or exit the program.  
	//Importing and parsing the text, as well as the search funciton and print function all are different
	//methods that are called by the switch-case system in main.
	
	
	
	
	
	public static ArrayList<Book> importBooks() throws FileNotFoundException {
		
		//This method takes the text file, goes line by line storing the strings in an array, separating by the commas.
		//After the end of the line is reached each string is stored as an attribute in the Book object and the object is stored
		//in an ArrayList.  The full arrayList is returned to main.
		
		//the file points to a test file on my own Desktop and asks for the name of the file. 
		
		System.out.println("Please input txt filename: ");
		Scanner userfile = new Scanner(System.in);
		String filename = userfile.nextLine();

		
		ArrayList<Book> arrList = new ArrayList<Book>();
		
		File file = new File(
				"C:\\Users\\space\\Desktop\\" + filename + ".txt");
	        Scanner filesc = new Scanner(file);
	 
	    while (filesc.hasNextLine()) {
	      //      System.out.println(sc.nextLine());
		String str = filesc.nextLine();
		String[] arrOfStr = str.split(",");
		Book newBook = new Book(arrOfStr[0], arrOfStr[1], arrOfStr[2], arrOfStr[3], true);
		arrList.add(newBook);
	    }
		return arrList;
	}
	
	public static void fullBookList(ArrayList<Book> bookList) {
		
		//This method reads out each item in the ArrayList.  It simply iterates over every element in the ArrayList and calls its
		//toString().  The toString() method is contained in the Book class.
		
		System.out.println("Now Printing Library...");
		
		for (Book e : bookList) {
			System.out.println(e.toString());	}
	}
	
	public static ArrayList<Book> removeBookID(ArrayList<Book> bookList) {
		
		//This method removes book entries according to the internal ID number, it iterates over each entry and compares user input
		//against the ID stored in the book object.  The variable i is used to grab the index of the entry to be removed and is initialized
		//before the for-loop to prevent concurrency issues.  The full library is printed after a successful removal as a confirmation.

	    Scanner scan = new Scanner(System.in);
	    System.out.println("Please input ID to remove: ");
	    String userSelection = scan.nextLine();
	    int i = -1;
	    for (Book e : bookList) {
	    	if (userSelection.equals(e.getId())) {
	    		i = bookList.indexOf(e);
	    	}
	    }
	    if (i > -1) {
	    	bookList.remove(i);
	    	System.out.println("Book successfully removed.");
	    	fullBookList(bookList);}
	    else {
	    	System.out.println("ID not found.");
	    }
		return bookList;
		
	}
	
	public static ArrayList<Book> removeBookTitle(ArrayList<Book> bookList) {
		
		//This method removes book entries according to the book title, it iterates over each entry and compares user input
		//against the title stored in the book object.  The variable i is used to grab the index of the entry to be removed and is initialized
		//before the for-loop to prevent concurrency issues.  The full library is printed after a successful removal as a confirmation.

	    Scanner scan = new Scanner(System.in);
	    System.out.println("Please input Title to remove: ");
	    String userSelection = scan.nextLine();
	    int i = -1;
	    for (Book e : bookList) {
	    	if (userSelection.equals(e.getTitle())) {
	    		i = bookList.indexOf(e);
	    	}
	    }
	    if (i > -1) {
	    	bookList.remove(i);
	    	System.out.println("Book successfully removed.");
	    	fullBookList(bookList);}
	    else {
	    	System.out.println("ID not found.");
	    }
		return bookList;
		
	}
	
	
	public static ArrayList<Book> checkTitleOut(ArrayList<Book> bookList) {
		
		//This method allows users to check out books.  The user is prompted for a title, and the method iterates over the arraylist
		//when the title is found, the boolean is flipped to false and a small success message is printed.  If the title is not found
		//a small error message is displayed and the program returns to the menu.
		
		//Note: no unique message is output if the book is already checked out

	    Scanner scan = new Scanner(System.in);
	    System.out.println("Please input Title to check out: ");
	    String userSelection = scan.nextLine();
	    int i = -1;
	    boolean detector = false;
	    for (Book e : bookList) {
	    	if (userSelection.equals(e.getTitle())) {
	    		e.checkOut();
	    		System.out.println(userSelection + " successfully checked out.");
	    		fullBookList(bookList);
	    		detector = true;
	    	}
	    }
	    if (detector == false) {
	    	System.out.println("Title not found.");
	    }
	    return bookList;
		
	}
	
	
	public static ArrayList<Book> checkTitleIn(ArrayList<Book> bookList) {
		
		//This method allows users to check in books.  The user is prompted for a title, and the method iterates over the arraylist
		//when the title is found, the boolean is flipped to true and a small success message is printed.  If the title is not found
		//a small error message is displayed and the program returns to the menu.
		
		//Note: no unique message is played if the book is already checked in

	    Scanner scan = new Scanner(System.in);
	    System.out.println("Please input Title to check in: ");
	    String userSelection = scan.nextLine();
	    int i = -1;
	    boolean detector = false;
	    for (Book e : bookList) {
	    	if (userSelection.equals(e.getTitle())) {
	    		e.checkIn();
	    		System.out.println(userSelection + " successfully checked in.");
	    		fullBookList(bookList);
	    		detector = true;
	    	}
	    }
	    if (detector == false) {
	    	System.out.println("Title not found.");
	    }
	    return bookList;
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		
		ArrayList<Book> bookList = importBooks();
		int userSelection = 0;
		
		while (userSelection != 6) {
			System.out.println("Menu\n1. Remove entry by ID number.\n2. Remove Entry by Title.\n3. Print Full Library.\n4. Check Out Book by Title.\n"
					+ "5. Check In Book by Title.\n6. Exit.\nPlease input selection: "); 
			Scanner scan = new Scanner(System.in);
			userSelection = scan.nextInt();
			switch (userSelection) {
			case 1:
				removeBookID(bookList);
				break;
			case 2:
				removeBookTitle(bookList);
				break;
			case 3:
				fullBookList(bookList);
				break;
			case 4:
				checkTitleOut(bookList);
				break;
			case 5:
				checkTitleIn(bookList);
				break;
			case 6:
				System.out.println("Exiting...");
				break;
			default:
				System.out.println("Please enter a valid number");
			}
		}
	}
}
