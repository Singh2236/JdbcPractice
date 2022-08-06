# Transections (ACID)

# Transections (ACID)
A transaction is a unit of work that is performed against a database. Transactions are units or sequences of work accomplished in a logical order, whether in a manual fashion by a user or automatically by some sort of a database program.

A transaction is the propagation of one or more changes to the database. For example, if you are creating a record or updating a record or deleting a record from the table, then you are performing a transaction on that table. It is important to control these transactions to ensure the data integrity and to handle database errors.

Practically, you will club many SQL queries into a group and you will execute all of them together as a part of a transaction.

### Properties of Transactions
Transactions have the following four standard properties, usually referred to by the acronym **ACID**.

* **Atomicity** − ensures that all operations within the work unit are completed successfully. Otherwise, the transaction is *aborted* at the point of failure and all the previous operations are rolled back to their former state.

* **Consistency** − ensures that the database properly changes states upon a successfully committed transaction.

* **Isolation** − enables transactions to operate independently of and transparent to each other.

* **Durability** − ensures that the result or effect of a committed transaction persists in case of a system failure.

### Transaction Control
The following commands are used to control transactions.

* **COMMIT** − to save the changes.

* **ROLLBACK** − to roll back the changes.

* **SAVEPOINT** − creates points within the groups of transactions in which to ROLLBACK.

* **SET TRANSACTION** − Places a name on a transaction.

[referances.](https://www.tutorialspoint.com/sql/sql-transactions.htm)

