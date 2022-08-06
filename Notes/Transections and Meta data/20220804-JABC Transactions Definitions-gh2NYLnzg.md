# JABC Transactions: Definitions

1. What are transactions?
+ A transaction is a unit of Work.
+ One or More SQL statements executed together.
  + Either all of the statements are executed - **Commit **
  + or none of the statements are executed - **Rollback **

+ By default, the database connection is set to auto-commit (or auto commit is set to true)
+ need to turn off auto commit 
```
myConn.serAutoCommit(false);
```

+  this this condition, Developer control Commit and Rollback 
 ```` java
myConn.commit(),
...
myConn.rollback(),

 ````

This is how, the code looks like
````java
// Start Transection
myConn.serAutoCommit(false);

// Perform multiple commit actioon
boolean ok = askUserIfOkayToSave();

if(ok) {
        //store in database 
        myConn.commit();
}
else
        //discard
        myConn.rollback();
````



2. Developing transactions