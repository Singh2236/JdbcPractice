import java.io.*;
import java.sql.*;

public class ReadingBLOBs {
    public static void main(String[] args) throws SQLException, IOException {
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/demo", "student", "student"
        );
        Statement statement = connection.createStatement();
        String sql = "select resume from employees where email = 'bill.brown@foo.com'";
        ResultSet resultset = statement.executeQuery(sql);

        // Set up the Handle to the output file
        File theFile = new File("resume_from_db.pdf");
        try (FileOutputStream outputStream = new FileOutputStream((theFile))){



        // reading the blob and storing it in the output file
        if(resultset.next()) {

            InputStream inputStream = resultset.getBinaryStream("resume");

            byte[] buffer = new byte[1024];
            while (inputStream.read(buffer) > 0) {
                outputStream.write(buffer);
            }
        }
        }

    }
}
