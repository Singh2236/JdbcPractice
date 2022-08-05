import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;

public class WritingBlobs {
    public static void main(String[] args) throws SQLException {
        FileInputStream fileInputStream = null;
        Connection connection = null;
        File file = null;
        PreparedStatement statement = null;

        try {
            //Get a connection to the database
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "student", "student");

            //prepare statement
            String sql = "update employees set resume = ? where email='bill.brown@foo.com'";
            statement = connection.prepareStatement(sql);


            //set parameter for resume file name
            file = new File("src/main/java/._sample_resume.pdf");
            fileInputStream = new FileInputStream(file);
            statement.setBinaryStream(1,fileInputStream);

            //executing the statement
            System.out.println("Storing the file in the database : " + file);
            System.out.println(sql);

            statement.executeUpdate();

            System.out.println("Completed sucessfully!");


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}
