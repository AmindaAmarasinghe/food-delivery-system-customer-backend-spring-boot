From openjdk:11
copy ./build/libs/food_delivery_customer-0.0.1-SNAPSHOT.jar food_delivery_customer.jar
CMD ["java","-jar","food_delivery_customer.jar"]