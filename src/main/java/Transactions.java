import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Transactions {
    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;
        Scanner scanner = new Scanner(System.in);

        try {
            // get connection to the database and initialise statement
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo","student","student");
            statement = connection.createStatement();

            // Turn of the auto commit
            connection.setAutoCommit(false);

            //show salaries before ( i won't write a method here, but I will just have a look at database)


            // Transaction Step:1, Delete all HR employees
            statement.executeUpdate("delete from employees where department = 'HR'");
            System.out.println("delting query has been in queue");

            // Transaction Step2:, Set salaries to 30000 for all the engineers
            statement.executeUpdate("update employees set salary = 300000 where department = 'Engineering'");
            System.out.println("updating query has been in queue");


            // ask user if it is okay to save
            System.out.println("Should the queries be committed 1 : yes and other : no");
            int a = scanner.nextInt();
            if (a == 1) {
                connection.commit();
                System.out.println("Queries are committed");

            }else {
                connection.rollback();
                System.out.println("Queries are not committed");
            }




        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
