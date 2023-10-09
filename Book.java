package homework;

public class Book {
	
	//Quinn Schlussel - 202410-CEN-3024C - 10/8/2023
	//Software Development I - Prof Walauskis
	//This class just represents the individual books in the ArrayList kept by the main method.
	//its attributes are ID, Book Title, Book Author, ISBN, and a boolean for Check out status. There is nothing in this current build
	//to keep ID numbers unique, that will have to be worked out at a later point since it's a big part of constructing a proper database

	//The ID serves as the 'Barcode' number that the library should establish since the ISBN is actual, accurate numerical identifier used to
	//identify different printings of books.
	
	//all books are instantiated as being 'in stock'.  Since this is meant to be an 'always running database' it makes sense to have books being
	//added as checked in, since the data entry portion will require you have the book on hand anyway.
	
	private String id;
	private String title;
	private String author;
	private String ISBN;
	private boolean checkOutStatus;

	public Book(String id, String title, String author, String ISBN, boolean checkOutStatus) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.ISBN = ISBN;
		this.checkOutStatus = checkOutStatus;
	}

	// ID setters and getters
	public String getId() {
		return id; }
	
	public void setId(String id) {
		this.id = id; }
	
	// Title setters and getters
	public String getTitle() {
		return title; }
	
	public void setTitle(String title) {
		this.title = title; }

	//Author setters and getters
	public String getAuthor() {
		return author; }

	public void setAuthor(String author) {
		this.author = author; }
	
	//ISBN setters and getters
	public String getISBN() {
		return ISBN; }
	
	public void setISBN(String ISBN) {
		this.ISBN = ISBN; }
	
	// Check out setters and getters
	public boolean getCheckOutStatus() {
		return checkOutStatus; }
	
	public void checkIn() {
		this.checkOutStatus = true; }
	
	public void checkOut() {
		this.checkOutStatus = false; }
	
	// a toString to print entries
	public String toString() {
		return "ID: #" + id + " - Title: " + title + " - Author: " + author + " - ISBN: " + ISBN + " - In Stock: " + checkOutStatus;
	}
}
