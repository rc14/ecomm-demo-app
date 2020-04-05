# ecomm-demo-app

To run this app run the following command inside ecomm folder 

$ mvn clean install

$ mvn spring-boot:run

REST APIS

GET http://localhost:8080/order/[orderId] 

PUT http://localhost:8080/order/insert

json body

{  "orderid": 1,
		"quantity": 201,
        "productid": 3,
		"email": "ranjitha"
 }	
 
 GET http://localhost:8080/product/[productId]
 
 PUT http://localhost:8080/insert
 {
 "productid" : 3,
 "productname" : "pencil",
 "quantity": 100}
 
 DELETE http://localhost:8080/delete/[productId]
