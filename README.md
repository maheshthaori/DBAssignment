Run the application and use followin url and sample body to execute the test.

1) Create Accounts before transfer the money (Create two account to transfer money accross the account)
   url http://localhost:8080/api/account/create
   sample body : { "accountId":"123", "amount" :"100" }

2)  Transfer Money from to to accounts.
    http://localhost:8080/api/account/transfer
    { "fromAccountId":"123", "toAccountId" :"456", "amount": "100" }

**Following are few points to turn this project making production ready.**

1) Docker file : to describe the docker runtime and repopath
2) Ingress : to mapping the application at pod to cluster level
3) hpa file : for replica set
4) Livess and readiness probes (Actuator)(K8s)
5) Logging
6) API tokens(RBAC)
7) Circuit breaker
8) Pod level agent for logstashing.
9) API gateway
