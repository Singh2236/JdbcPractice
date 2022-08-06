import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.*;

public class WritingCLOBs {
    public static void main(String[] args) {
        // reading file from system and saving it in the resume

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "student", "student");
            String sql = "update employees set resume = ? where email = 'bill.brown@foo.com'";

            PreparedStatement statement = connection.prepareStatement(sql);

            //set parameters for resume file
            File file = new File("._sample_resume.txt");
            FileReader fileReader = new FileReader(file);

            statement.setCharacterStream(1, fileReader);

            //execute


            System.out.println("Reading the file:" + file.getAbsolutePath());
            System.out.println(sql);

            statement.executeUpdate();

            System.out.println("Complete successfully");


        } catch (SQLException | FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}
