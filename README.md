### Problem definition
Create a tiny RESTful web service with the following business requirements:

- Application must expose REST API endpoints for the following functionality:
  - apply for loan (`loan amount`, `term`, `name`, `surname` and `personal id` must be provided)
  - list all approved loans
  - list all approved loans by user
- Service must perform loan application validation according to the following rules and reject application if:
  - Application comes from blacklisted personal id
  - N application / second are received from a single country (essentially we want to limit number of loan applications coming from a country in a given timeframe)
- Service must perform origin country resolution using the following web service and store country code together with the loan application. Because network is unreliable and services tend to fail, let's agree on default country code - "ru".

### Technical requirements

Please use Spring 5 together with Springboot 2. Write the code in Java 8 and/or Kotlin.

### What gets evaluated

- Conformance to business requirements
- Code quality, including testability
- How easy it is to run and deploy the service (don't make us install Oracle database please ;)

**Good luck and have fun!**
