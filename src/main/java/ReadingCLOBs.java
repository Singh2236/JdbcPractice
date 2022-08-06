import java.io.*;
import java.sql.*;

public class ReadingCLOBs {
    public static void main(String[] args) {
        Reader input;


        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "student", "student");
            Statement statement = connection.createStatement();

            String sql = "select resume from employees where email = 'bill.brown@foo.com'";


            ResultSet resultSet = statement.executeQuery(sql);


            File file = new File("CLOB_txt_file_from_DB");
            FileWriter fileWriter = new FileWriter(file);

            //read CLOB and store the output file
            if (resultSet.next()) {

                input = resultSet.getCharacterStream("resume");
                int theChar;
                while ((theChar = input.read()) > 0) {
                    fileWriter.write(theChar);
                    System.out.println(theChar);

                }


            }




        } catch (SQLException e) {
            System.out.println("SQLExecption");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
