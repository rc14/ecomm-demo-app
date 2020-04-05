package com.omnicuris.Dto;

public class OrderDto {
	
int orderid;
int productid;
String email;
int quantity;

public int getQuantity() {
	return quantity;
}
public void setQuantity(int quantity) {
	this.quantity = quantity;
}
public int getOrderid() {
	return orderid;
}
public void setOrderid(int orderid) {
	this.orderid = orderid;
}
public int getProductid() {
	return productid;
}
public void setProductid(int productid) {
	this.productid = productid;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}


}
