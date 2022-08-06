# Calling Stored Procedures: IN

### What are stored procedures?

+ A stored procedure is a set of statements that perform a perticular task
+ Normally Created by DBA
+ Created in a SQL language, supported by the nativ database
+ Can have combination of **input**, **output** and **input/output** parameters.


### Using Callable Statements 
+ To call stored procedures from java 
 - The jdbc api provides the **callable statement ** 

````java
callable statement myCall = myConn.preparecall("{call some_stored_proc(-parameters-)}");
...
myCall.execute();
````

#### Parameters 
+ The JDBC support different parameters 
  - IN(default)
  - INOUT
  - OUT

+ The stored procedures can also return result set
+ Following code examples will show how to register parametertypes and values 

### Call Stored Procedures that take parameters 
- IN Parameters
- INOUT Parameters
- OUT Parameters
- Return a result set 



## IN Parameters
+ Our DBA created a stored procedure 
  + developed for mysql

````mysql
PROCEDURE `some_kind_of_procedure`(
  IN procedure_name VARCHAR(64), IN another_procedure DECIMAL(10,2))

BEGIN 
  UPDATE table SET column_name = column_name + another_procedure 
  WHERE another_coloumn_name = procedure_name;

END

````
Example:
+ Increase salary for everyone in the department 
  + First parameter is department name
  + Second param is increase amount
````mysql
CREATE PROCEDURE `increase_salaries_for_department` (
	IN the_department varchar(64), IN increase_amount DECIMAL(10,2))
BEGIN
	UPDATE employees SET salary = salary + increase_amount
    Where department = the_department;
END
````
#### IN Parameters in Java Coading 
+ Prepare the callable sstatement 
````java
Callable myStmt = myConn.prepareCall(
  "{call increase_salaries_for_department(?,?)}");

myStmt.setString(1, "Engineering");
myStmt.setString(2, 1000);

//now execute the statement 

myStmt.execute();
````

Code from IDE 
````java
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CallinProceduresIN {
    public static void main(String[] args) {
        Connection myConn = null;
        CallableStatement myStmt = null;

        try {
            // get connection to the database
            myConn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/demo", "student", "student");
            String the_department = "Engineering";
            int the_increase_Amount = 10000;

            //show salaries before, tobe implemented

            //prepare the stored procedure call
            myStmt = myConn
                    .prepareCall("{call increase_salaries_for_department(?,?)}");

            //Set the parameters
            myStmt.setString(1, the_department);
            myStmt.setDouble(2, the_increase_Amount);

            //call stored procedure
            myStmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

````







## INOUT Parameters
## OUT Parameters
## Return a result set 
