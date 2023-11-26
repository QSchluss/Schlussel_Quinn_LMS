import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Vector;


public class Lmsfull {

    /*
    A library management program created by Quinn Schlussel
    CEN 3024C Software Development I - Professor Walauskis
    The class Lmsfull contains all necessary methods for interacting with
    and displaying the connected database, along with Lmsfull.form
    the main categories of the database are as follows:
    id: Primary key, a unique identifier for each individual book. 4 digits long for the purpose of testing
    this program but can easily be made longer
    title: the title of the book
    author: the author of the book
    ISBN: identifier for the particular printing of a book, hardcover/softcover, edition, etc.
    genre: genre of the book.
    checkOutStatus: There are two possible settings for each book, In Stock, which means the book
    is currently in the library, and Checked Out, which means it is currently out.
    dueDate: is automatically set for a date 4 weeks from the current date, if the book is In Stock
    the field is set to null.
     */

    private JPanel LMSmain;
    private JTabbedPane tabbedPane1;
    private JTextField textField1;
    private JButton submitButton;
    private JTextField textField2;
    private JButton submitButton1;
    private JTable table1;
    private JTextField textField3;
    private JButton submitButton2;
    private JTextField textField4;
    private JButton submitButton3;

    public Lmsfull() {

        /*
        An action button listener, this one takes the title from the marked text field
        and checks out the corresponding book by its title, creating a new dueDate as well.
         */
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            String input = textField1.getText();
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "admin");
                    Statement stmt = con.createStatement();
                    String sql = "UPDATE books SET checkOutStatus = 'Checked Out', dueDate = DATE(NOW() + INTERVAL 4 WEEK) WHERE title = '" + input + "'";
                    stmt.executeUpdate(sql);
                } catch (ClassNotFoundException | SQLException n) {
                    throw new RuntimeException(n);
                }
            }
        });

        /*
        An action button listener, this one takes the title from the marked text field
        and checks in the corresponding book by its title, it removes the dueDate as well
         */
        submitButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = textField2.getText();
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "admin");
                    Statement stmt = con.createStatement();
                    String sql = "UPDATE books SET checkOutStatus = 'In Stock', dueDate = NULL WHERE title = '" + input + "'";
                    stmt.executeUpdate(sql);
                } catch (ClassNotFoundException | SQLException n) {
                    throw new RuntimeException(n);
                }
            }
        });

        /*
        An action button listener, this one takes the title from the marked text field
        and removes the corresponding book by its title.
         */
        submitButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = textField3.getText();
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "admin");
                    Statement stmt = con.createStatement();
                    String sql = "DELETE FROM books WHERE title = '" + input + "'";
                    stmt.executeUpdate(sql);
                } catch (ClassNotFoundException | SQLException n) {
                    throw new RuntimeException(n);
                }
            }
        });

        /*
        An action button listener, this one takes the title from the marked text field
        and removes the corresponding book by its id
       .
         */
        submitButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = textField3.getText();
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "admin");
                    Statement stmt = con.createStatement();
                    String sql = "DELETE FROM books WHERE id = '" + input + "'";
                    stmt.executeUpdate(sql);
                } catch (ClassNotFoundException | SQLException n) {
                    throw new RuntimeException(n);
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Library Management System by Quinn Schlussel");
        frame.setContentPane(new Lmsfull().LMSmain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void createUIComponents() throws SQLException {
        table1 = new JTable(buildTableModel());
    }

    /*
    Creates a table model to be input into the Jtable embedded in the scrollpane
    iterates over every entry in the resultset and sends each vector one at a time
    doesn't grab the column names for some strange reason but the rest of the fields display properly
     */
    public static DefaultTableModel buildTableModel()
            throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "admin");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM books");
            ResultSetMetaData metaData = rs.getMetaData();

        // names of columns
        Vector<String> columnNames = new Vector<String>();
        int columnCount = metaData.getColumnCount();
        for (int column = 1; column <= columnCount; column++) {
            columnNames.add(metaData.getColumnName(column));
        }

        // data of the table
        Vector<Vector<Object>> data = new Vector<Vector<Object>>();
        while (rs.next()) {
            Vector<Object> vector = new Vector<Object>();
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                vector.add(rs.getObject(columnIndex));
            }
            data.add(vector);
        }
            return new DefaultTableModel(data, columnNames);
        } catch (ClassNotFoundException | SQLException n) {
            throw new RuntimeException(n);
        }
    }
}
