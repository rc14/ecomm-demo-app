package com.omnicuris.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.mysql.cj.jdbc.MysqlDataSource;

import com.omnicuris.Dto.OrderDto;
import com.omnicuris.exceptionhandle.DataExceptionHandler;

@Component
@Qualifier("orderdao")

public class OrderDao {

	@Autowired
	@Qualifier("mysql")
	MysqlDataSource msds;

	public int insert(OrderDto orderdto) throws DataExceptionHandler {
		int num_of_row = 0;
		try {
			int quant_orders=orderdto.getQuantity();
;			int ordrid = orderdto.getOrderid();
			int prodid = orderdto.getProductid();
			String email = orderdto.getEmail();
			
			Connection con = msds.getConnection();
			String sql = "insert into ecomm.order(orderid,productid,email,quantity) values(?,?,?,?);";
			PreparedStatement pst = con.prepareStatement(sql);

			pst.setInt(1, ordrid);
			pst.setInt(2, prodid);
			pst.setString(3, email);
			pst.setInt(4, quant_orders);
			num_of_row = pst.executeUpdate();

		} catch (SQLException sqldata) {
			throw new DataExceptionHandler("Caused during execution of query", sqldata);
		}
		return num_of_row;
	}
	
	public int quantity_orders(int orderid)throws DataExceptionHandler{
		int quant;
		ResultSet rs=null;
		
			try {
	Connection con=msds.getConnection();
	Statement st=con.createStatement();
	rs=st.executeQuery("select quantity from ecomm.order where orderid = '" + orderid + "'");
	quant=rs.getInt("quantity");
			}
			
			catch(SQLException sqlexc) {
			throw new DataExceptionHandler("Error during order_quantity",sqlexc);	
			}
			return quant;
	}
	
	public ArrayList<OrderDto> select(int order_id) throws DataExceptionHandler  {
		ResultSet rs=null;
		ArrayList<OrderDto> orderlist = new ArrayList<OrderDto>();
		try{
		Connection con = msds.getConnection();
		Statement st=con.createStatement();
		if(order_id == 0){
		 rs=st.executeQuery("select orderid,quantity,email ecomm.order;");
		 }
		 else{
rs=st.executeQuery("select orderid,quantity,email from ecomm.order where orderid = "+ order_id +";");
// use string builder to build the string and change the package names to camel case
		 }
		 
		 while(rs.next()){
		 int orderid=rs.getInt("orderid");
		 System.out.println("orid:" + orderid );
		 String order_email=rs.getString("email");
		 int order_quantity=rs.getInt("quantity");
		 OrderDto orderdto= new OrderDto();
		 orderdto.setOrderid(orderid);
		 orderdto.setEmail(order_email);
		 orderdto.setQuantity(order_quantity);
		 
		 orderlist.add(orderdto);
		 }
		
	}catch ( SQLException exdao) {
		// TODO Auto-generated catch block
		throw new DataExceptionHandler("query not processed",exdao);
	}
		return orderlist;
	
	}
}

