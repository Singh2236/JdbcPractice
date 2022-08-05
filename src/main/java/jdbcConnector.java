import java.sql.*;

public class jdbcConnector {
    public static void main(String[] args) throws SQLException {
        String dbUrl = "jdbc:mysql://localhost:3306/demo";
        String user = "student";
        String pass = "student";

        Connection myConn = null;
        PreparedStatement myStatement = null;
        ResultSet myResultSet = null;

        try {
            //get the connection to the database
            myConn = DriverManager.getConnection(dbUrl, user, pass);

            // Using prepared statements
            myStatement = myConn.prepareStatement("SELECT * FROM employees " +
                    "where salary > ? and department=?");

            myStatement.setDouble(1, 80000);
            myStatement.setString(2, "Legal");

            myResultSet = myStatement.executeQuery();

            //Grabbing the data and displaying the data on the console
            while (myResultSet.next()) {
                int id = myResultSet.getInt(1);
                String last_name = myResultSet.getString(2);
                String email = myResultSet.getString(4);

                System.out.println(id + " " + last_name + " " + email);
            }


            //
            // Reusing the prepred statements
            //

            System.out.println("\n Reusing the prepared statements");
            myStatement.setDouble(1, 5000);
            myStatement.setString(2, "HR");

            myResultSet = myStatement.executeQuery();

            //Grabbing the data and displaying the data on the console
            while (myResultSet.next()) {
                int id = myResultSet.getInt(1);
                String last_name = myResultSet.getString(2);
                String email = myResultSet.getString(4);

                System.out.println(id + " " + last_name + " " + email);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (myResultSet != null) {
                myResultSet.close();
            }
        }


    }

}
