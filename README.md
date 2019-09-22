Kafka-File-Transfer

Sometimes the object you have to send to Kafka is not just a simple string or integer. Could be a zip file, Csv, Xls etc.
With that in mind, you have a choice of either using a generic serialization library like Avro or Thrift to
create records, or implement a custom serialization for objects.


Spring Boot Kafka Samples Project
================

This project is **a collection of Kafka related code samples ** - each module try to solve a use case
which you might have to implement ion your own project.


Building the project from root
====================
For the full build, do: `mvn clean install`


Building a single module
====================
To build a singular module run the command: `mvn clean install` in the module directory


Running a Spring Boot module
====================
To run a Spring Boot module run the command: `mvn spring-boot:run` in the module directory


Working with the IDE
====================
This repo contains several number of modules.
Most probably you will want to check/run the code of an individual module of your own interest,
with that in mind there is no need to import all of them - just import the module of interest in Eclipse or IntelliJ and you're good to go.


## License
[MIT](https://opensource.org/licenses/MIT)
