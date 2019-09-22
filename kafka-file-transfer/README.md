Spring Boot Kafka File Transfer
================

Sometimes the object you have to send to Kafka is not just a simple string or integer. Could be a zip file, Csv, Xls etc.
With that in mind, you have a choice of either using a generic serialization library like Avro or Thrift to
create records, or implement a custom serialization for objects.

