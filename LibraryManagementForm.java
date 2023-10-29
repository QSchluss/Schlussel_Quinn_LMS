import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class LibraryManagementForm extends JFrame {

    /*
     Quinn Schlussel - 202410-CEN-3024C - 10/29/2023
     Software Development I - Prof Walauskis
     LibraryManagementForm is a new version of main class.
     It is based around the new addition of the UI, many of the methods
     in this class are copy pasted from the original LibraryMagangmentSystem.java
     and tweaked to use text fields and buttons instead of scanner inputs.
     There is a slight overuse of Option Panes to send messages
     I would've liked to better integrate user information into the Swing elements
     but time began to become an issue so I stuck with what worked.
    */


    private JPanel libraryFrame;
    private JTextField txtField;
    private JTextArea welcomeTextArea;
    private JButton submitButton1;
    private JTextPane clickHereToViewTextPane;
    private JButton listButton;
    private JTextField removeBookTitleTxtField;
    private JLabel titleRemoveLabel;
    private JButton removeTitleButton;
    private JLabel idRemoveLabel;
    private JTextField removeBookIDTextField;
    private JButton removeIDButton;
    private JTextField checkOutText;
    private JTextField checkInText;
    private JButton checkOutButton;
    private JButton checkInButton;
    private JLabel checkOutLabel;
    private JLabel checkInLabel;

    private ArrayList<Book> bookList;

    public LibraryManagementForm() {
        JFrame frame = new JFrame();
        setContentPane(libraryFrame);
        setTitle("Library Management System");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(420, 340);
        setLocationRelativeTo(null);
        setVisible(true);
        submitButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    bookList = importBooks(txtField.getText());
                } catch (FileNotFoundException ex) {
                    JOptionPane.showMessageDialog(frame, "Text File Not Found");
                    throw new RuntimeException(ex);
                }
            }
        });
        listButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    JOptionPane.showMessageDialog(frame, fullBookList(bookList));
                } catch (NullPointerException ex2) {
                    JOptionPane.showMessageDialog(frame, "Library not yet uploaded");
                    throw new NullPointerException();
                }
            }
        });
        removeTitleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bookList = removeBookTitle(bookList, removeBookTitleTxtField.getText());
            }
        });
        removeIDButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bookList = removeBookID(bookList, removeBookIDTextField.getText());
            }
        });
        checkOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bookList = checkTitleOut(bookList, checkOutText.getText());
            }
        });
        checkInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bookList = checkTitleIn(bookList, checkInText.getText());
            }
        });
    }

    public static void main(String[] args) {
        new LibraryManagementForm();
    }

    public static ArrayList<Book> importBooks(String str) throws FileNotFoundException {

        //This method takes the text file, goes line by line storing the strings in an array, separating by the commas.
        //After the end of the line is reached each string is stored as an attribute in the Book object and the object is stored
        //in an ArrayList.  The full arrayList is returned to main.

        //the file points to a test file on my own Desktop and asks for the name of the file.

        ArrayList<Book> arrList = new ArrayList<Book>();

        File file = new File(
                "C:\\Users\\space\\Desktop\\" + str + ".txt");
        Scanner filesc = new Scanner(file);

        while (filesc.hasNextLine()) {
            //      System.out.println(sc.nextLine());
            String str2 = filesc.nextLine();
            String[] arrOfStr = str2.split(",");
            Book newBook = new Book(arrOfStr[0], arrOfStr[1], arrOfStr[2], arrOfStr[3], true);
            arrList.add(newBook);
        }
        return arrList;
    }

    public static String fullBookList(ArrayList<Book> bookList) {

        //This method reads out each item in the ArrayList.  It simply iterates over every element in the ArrayList and calls its
        //toString().  The toString() method is contained in the Book class.

        StringBuilder fullList = new StringBuilder("Full library:");
        for (Book e : bookList) {
            fullList.append("\n").append(e.toString());
        }
        return fullList.toString();
    }

    public static ArrayList<Book> removeBookTitle(ArrayList<Book> bookList, String title) {

        //This method removes book entries according to the book title, it iterates over each entry and compares user input
        //against the title stored in the book object.  The variable i is used to grab the index of the entry to be removed and is initialized
        //before the for-loop to prevent concurrency issues.  The full library is printed after a successful removal as a confirmation.

        JFrame frame = new JFrame();
        int i = -1;
        for (Book e : bookList) {
            if (title.equals(e.getTitle())) {
                i = bookList.indexOf(e);
            }
        }
        if (i > -1) {
            bookList.remove(i);
            JOptionPane.showMessageDialog(frame, "Book Successfully Removed");
            fullBookList(bookList);
        } else {
            JOptionPane.showMessageDialog(frame, "Book Not Found");
        }
        return bookList;

    }

    public static ArrayList<Book> removeBookID(ArrayList<Book> bookList, String title) {

        //This method removes book entries according to the book title, it iterates over each entry and compares user input
        //against the title stored in the book object.  The variable i is used to grab the index of the entry to be removed and is initialized
        //before the for-loop to prevent concurrency issues.
        JFrame frame = new JFrame();
        int i = -1;
        for (Book e : bookList) {
            if (title.equals(e.getId())) {
                i = bookList.indexOf(e);
            }
        }
        if (i > -1) {
            bookList.remove(i);
            JOptionPane.showMessageDialog(frame, "Book Successfully Removed");
            fullBookList(bookList);
        } else {
            JOptionPane.showMessageDialog(frame, "Book Not Found");
        }
        return bookList;

    }

    public static ArrayList<Book> checkTitleIn(ArrayList<Book> bookList, String title) {

        //This method allows users to check in books.  The user is prompted for a title, and the method iterates over the arraylist
        //when the title is found, the boolean is flipped to true and a small success message is printed.  If the title is not found
        //a small error message is displayed and the program returns to the menu.

        JFrame frame = new JFrame();
        boolean detector = false;
        for (Book e : bookList) {
            if (title.equals(e.getTitle())) {
                if (e.getCheckOutStatus() == false) {
                    e.checkIn();
                    JOptionPane.showMessageDialog(frame, "Book checked back in!");
                } else {
                    JOptionPane.showMessageDialog(frame, "Book already checked in!");
                }
                detector = true;
            }
        }
        if (detector == false) {
            JOptionPane.showMessageDialog(frame, "Book not found");
        }
        return bookList;
    }

    public static ArrayList<Book> checkTitleOut(ArrayList<Book> bookList, String title) {

        //This method allows users to check out books.  The user is prompted for a title, and the method iterates over the arraylist
        //when the title is found, the boolean is flipped to false and a small success message is printed.  If the title is not found
        //a small error message is displayed and the program returns to the menu.

        JFrame frame = new JFrame();
        boolean detector = false;
        for (Book e : bookList) {
            if (title.equals(e.getTitle())) {
                if (e.getCheckOutStatus() == true) {
                    e.checkOut();
                    JOptionPane.showMessageDialog(frame, "Book checked out!");
                } else {
                    JOptionPane.showMessageDialog(frame, "Book already checked out!" );
                }
                detector = true;
            }
        }
        if (detector == false) {
            JOptionPane.showMessageDialog(frame, "Book not found");
        }
        return bookList;
    }
}

