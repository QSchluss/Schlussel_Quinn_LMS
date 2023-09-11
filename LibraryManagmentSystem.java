package homework;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;

public class LibraryManagmentSystem {
	
	//Quinn Schlussel - 202410-CEN-3024C - 9/10/2023
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
		
		//the file points to a test file on my own Desktop and would have to be altered to point towards where ever the library.txt file is located.
		
		
		ArrayList<Book> arrList = new ArrayList<Book>();
		File file = new File(
				"C:\\Users\\space\\Desktop\\test.txt");
	        Scanner scan = new Scanner(file);
	 
	    while (scan.hasNextLine()) {
	      //      System.out.println(sc.nextLine());
		String str = scan.nextLine();
		String[] arrOfStr = str.split(",");
		Book newBook = new Book(Integer.parseInt(arrOfStr[0]), arrOfStr[1], arrOfStr[2]);
		arrList.add(newBook);
	    }
		return arrList;
	}
	
	public static void fullBookList(ArrayList<Book> bookList) {
		
		//This method reads out each item in the ArrayList.  It simply iterates over every element in the ArrayList and calls its
		//toString().  The toString() method is contained in the Book class.
		
		for (Book e : bookList) {
			System.out.println(e.toString());	}
	}
	
	public static ArrayList<Book> removeBook(ArrayList<Book> bookList) {
		
		//This method removes book entries according to the internal ID number, it iterates over each entry and compares user input
		//against the ID stored in the book object.  The variable i is used to grab the index of the entry to be removed and is initialized
		//before the for-loop to prevent concurrency issues.

	    Scanner scan = new Scanner(System.in);
	    System.out.println("Please input ID to remove: ");
	    int userSelection = scan.nextInt();
	    int i = -1;
	    for (Book e : bookList) {
	    	if (userSelection == e.getId()) {
	    		i = bookList.indexOf(e);
	    	}
	    }
	    if (i > -1) {
	    	bookList.remove(i); }
		return bookList;
		
	}

	public static void main(String[] args) throws FileNotFoundException {
		
		ArrayList<Book> bookList = importBooks();
		int userSelection = 0;
		
		while (userSelection != 3) {
			System.out.println("Menu\n1. Remove entry by ID number.\n2. Print full library.\n3. Exit\nPlease input selection: "); 
			Scanner scan = new Scanner(System.in);
			userSelection = scan.nextInt();
			switch (userSelection) {
			
			case 1:
				removeBook(bookList);
				break;
			case 2:
				fullBookList(bookList);
				break;
			case 3:
				System.out.println("Exiting...");
				break;
			default:
				System.out.println("Please enter a valid number");
			}
	    
		}
		
		

	}

}
