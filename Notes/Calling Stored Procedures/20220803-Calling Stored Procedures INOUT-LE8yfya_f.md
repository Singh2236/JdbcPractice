# Calling Stored Procedures: INOUT

+ Our BDA created a stored procedure 
  + developed by MySQL
````sql
CREATE PROCEDURE `greet_the_department`(
       INOUT department VARCHAR(64))
BEGIN
       SET department = concat('Hello, you are awesome', department, 'team !');
END
````

+ Very Simple INOUT Example 
  + Parameter is department name
  + Update the param with 
     + **Hello you are awesome (department) team ! **

##### Prepare the callable statement
```` java
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

            //prepare the stored procedure call
            myStmt = myConn
                    .prepareCall("{call greet_the_department(?)}");

            myStmt.registerOutParameter(1, Types.VARCHAR);

            //use this for inout
            myStmt.setString(1, "Engineering");

            //call stored procedure
            myStmt.execute();

            //get the value in the variable
            String results = myStmt.getNString(1);

            System.out.println(results);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

````