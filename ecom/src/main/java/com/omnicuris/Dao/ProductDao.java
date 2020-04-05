package com.omnicuris.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.mysql.cj.jdbc.MysqlDataSource;
import com.omnicuris.Dto.OrderDto;
import com.omnicuris.Dto.ProductDto;
import com.omnicuris.controller.ProductController;
import com.omnicuris.exceptionhandle.DataExceptionHandler;

@Component
@Qualifier("prdao")
public class ProductDao {
	
	@Autowired
	@Qualifier("mysql")
	MysqlDataSource msds;
	
	final static Logger log = LoggerFactory.getLogger(ProductDao.class);
	
	
	public ArrayList<ProductDto> select(int pid) throws DataExceptionHandler  {
		ResultSet rs=null;
		ArrayList<ProductDto> productlist = new ArrayList<ProductDto>();
		try{
		Connection con = msds.getConnection();
		Statement st=con.createStatement();
		if(pid == 0){
		 rs=st.executeQuery("select productid,productname,quantity from ecomm.product;");
		 }
		 else{
rs=st.executeQuery("select productid,productname,quantity from ecomm.product where productid = "+ pid +";");
// use string builder to build the string and change the package names to camel case
		 }
		 
		 while(rs.next()){
		 int prod_id=rs.getInt("productid");
		 System.out.println("prid:" + prod_id );
		 String prod_name=rs.getString("productname");
		 int prod_quantity=rs.getInt("quantity");
		 ProductDto productdto= new ProductDto();
		 productdto.setProductid(prod_id);
		 productdto.setProductname(prod_name);
		 productdto.setQuantity(prod_quantity);
		 
		 productlist.add(productdto);
		 }
		
	}catch ( SQLException exdao) {
		// TODO Auto-generated catch block
		throw new DataExceptionHandler("query not processed",exdao);
	}
	
	
	return productlist;
}
	
	public int insert(ProductDto productdto) throws DataExceptionHandler {
		int num_of_row=0;
			try {
			int productid=productdto.getProductid();
			String productname=productdto.getProductname();
			int productquantity=productdto.getQuantity();
			Connection con=msds.getConnection();
			
	String sql="insert into ecomm.product(productid,productname,quantity) values(?,?,?);";
			PreparedStatement pst=con.prepareStatement(sql);
			pst.setInt(1, productid);
			pst.setString(2, productname);
			pst.setInt(3, productquantity);
			
			System.out.println("yet to execute");
			num_of_row=pst.executeUpdate();
			 System.out.println("executed");
			
		}catch(SQLException sqlex) {
			throw new DataExceptionHandler("Caused during execution of query", sqlex);
		}return num_of_row;
	}

	
	public int update(OrderDto orderdto) throws DataExceptionHandler {
		int updated=0;
	
		try {
			
		
		Connection con=msds.getConnection();
		int quant=quantity_product(orderdto.getProductid());
		int no_of_quant=orderdto.getQuantity();
		quant=quant-no_of_quant;
		
		String sql="update ecomm.product set quantity=? where productid=?";
		PreparedStatement st=con.prepareStatement(sql);
		
		st.setInt(1,quant);
		st.setInt(2,orderdto.getProductid());
		 updated=st.executeUpdate();
	}catch(SQLException exe) {
		throw new DataExceptionHandler("error while updating status",exe);
	}
return updated;
	}
	
	public int quantity_product(int product_id)throws DataExceptionHandler{
		int quantity;
		ResultSet resultset=null;
		
			try {
	Connection con=msds.getConnection();
	Statement st=con.createStatement();
	System.out.println("inside dao" + product_id);
	resultset=st.executeQuery("select quantity from ecomm.product where productid = " + product_id + ";");
	resultset.next();
	quantity=resultset.getInt("quantity");
	System.out.println(quantity);
			}
			
			catch(SQLException sqlexc) {
				System.out.println(sqlexc.toString());
			throw new DataExceptionHandler("Error during order_quantity",sqlexc);	
			}
			return quantity;
	}
	
	
	public int delete(int productid) throws DataExceptionHandler {
		int deleted=0;
		
		try {
			
			Connection con=msds.getConnection();
			String sql="delete from ecomm.product where productid=?";
			PreparedStatement st=con.prepareStatement(sql);
			
			st.setInt(1, productid);
			deleted=st.executeUpdate();
		}
		catch(SQLException sqlexception) {
			throw new DataExceptionHandler("error while deleting",sqlexception);
		}
		return deleted;
			}
	

}
