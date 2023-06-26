# System Design

Resources:

- Grokking the System Design Interview

## interview steps

1. Requirements clarifications
2. System interface definition
3. Back-of-the-envelope estimation
4. Defining data model
5. High-level design
6. Detailed design
7. Identifying and resolving bottlenecks

## step1: Requirements

requirements can be clarified as:

1. Functional Requirements
2. Non-Functional Requirements
3. Extended Requirements

## Step2: Back-of-the-envelope estimation or Capacity Estimation and Constraints

inputs or your own estimations:

- daily active users (DAU)
- request per second (RPS)
- read vs write ratio

outputs:

- Traffic estimates
  - request per second (RPS)
  - query per second (QPS)
- Storage estimates (B)
- Bandwidth estimates (in and out B/s)
- Memory estimates
  - 80-20 rule


## Step3: System interface definition or System APIs
## step 4: Defining data model or DB design
- choose the db
  - SQL and transactional: MySQL, SQL server
  - NoSQL: DynamoDB, Cassandra, Riak
- db schema
