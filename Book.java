package homework;

public class Book {
	
	//Quinn Schlussel - 202410-CEN-3024C - 9/10/2023
	//Software Development I - Prof Walauskis
	//This class just represents the individual books in the ArrayList kept by the main method.
	//its attributes are ID, Book Title, and Book Author.  There is nothing in this current build
	//to keep ID numbers unique, that will have to be worked out at a later point since it's a big
	//part of constructing a proper database
	
	private int id;
	private String title;
	private String author;

	public Book(int id, String title, String author) {
		this.id = id;
		this.title = title;
		this.author = author;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
	
	public String toString() {
		
		//a basic toString() that is called by the fullBookList() method.
		
		return "ID: #" + id + " - Title: " + title + " - Author: " + author;
	}

}
