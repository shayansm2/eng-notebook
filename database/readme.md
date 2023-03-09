# Database

Resources:
1. database internals book
2. [isolation levels explained](https://ssudan16.medium.com/database-isolation-levels-explained-61429c4b1e31)
3. [how database works](http://coding-geek.com/how-databases-work/)
4. [build your own database](https://build-your-own.org/database/)

## Concurrency control
### Concurrency problems:
1. dirty reads: read an uncommitted (dirty) value
2. dirty writes: overwrite an uncommitted (dirty) value
3. lost update (write skew): when one transaction overwrites an update made by another transaction
4. fuzzy / non-repeatable reads (read skew): read same row multiple times and get different results (when a write operation happens while these reads are happening in one transaction)
5. phantom reads: 

### isolation levels:

| isolation level  | notes                                         | addressed Concurrency problems   | implementation                                                                                               |
|------------------|-----------------------------------------------|----------------------------------|--------------------------------------------------------------------------------------------------------------|
| Read Uncommitted | weakest<br/>can read uncommitted values       | None                             |                                                                                                              |
| Read Committed   | basic<br/>PostgreSQL default isolation level  | 1. dirty read<br/>2. dirty write | 1. for dirty read: keep 2 copies of data (committed and uncommitted)<br/>2. for dirty write: row-level locks |
| Repeatable Reads | most common<br/>MySQL default isolation level | 1. non-repeatable reads          | for non-repeatable reads: snapshot isolation and MVCC                                                        |
| Serializable     | most strict                                   |                                  ||