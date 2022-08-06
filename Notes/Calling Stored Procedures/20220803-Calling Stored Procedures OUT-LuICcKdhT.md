# Calling Stored Procedures: OUT

+ Our DBA created a stored procedure
  + developed by mysql
````mysql
CREATE PROCEDURE `get_count_for_department`(
				IN the_department VARCHAR(64), 
				OUT the_count INT)
BEGIN
				SELECT COUNT(*) INTO the_count
				FROM employees
				WHERE department = the_department;
END
````



````java
import java.lang.reflect.Type;
import java.sql.*;

public class CallinProceduresIN {
    public static void main(String[] args) {
        Connection myConn = null;
        CallableStatement myStmt = null;

        try {
            // get connection to the database
            myConn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/demo", "student", "student");

            String the_department = "HR";
            int the_increase_Amount = 10000;

            //prepare the stored procedure call
            myStmt = myConn
                    .prepareCall("{call get_count_for_department(?,?)}");


            //parameter 1 for setting the value of the_department
            myStmt.setString(1, the_department);

            //parameter 2 for registering the value from the database
            myStmt.registerOutParameter(2, Types.INTEGER);

            //call stored procedure
            myStmt.execute();

            //finally, asssiging the registered value from the sql to a variable called "countFromSQL"
            int countFromSQL = myStmt.getInt(2);

            System.out.println(countFromSQL);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

````