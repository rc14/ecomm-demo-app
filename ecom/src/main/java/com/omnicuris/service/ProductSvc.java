package com.omnicuris.service;

import org.springframework.stereotype.Component;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.omnicuris.Dao.OrderDao;
import com.omnicuris.Dao.ProductDao;
import com.omnicuris.Dto.OrderDto;
import com.omnicuris.Dto.ProductDto;
import com.omnicuris.exceptionhandle.DataExceptionHandler;
import com.omnicuris.exceptionhandle.ServiceExceptionHandler;

@Component
@Qualifier("productsvc")
public class ProductSvc {
	
	@Autowired
	@Qualifier("prdao")
	ProductDao productdao;
	
	public ArrayList<ProductDto> selectProduct(int productid) throws DataExceptionHandler, ServiceExceptionHandler {
		ArrayList<ProductDto> productlist=null;
		try {
			
			productlist= productdao.select(productid);
		}
		catch(DataExceptionHandler dataexception) {
			throw dataexception;
		}
		catch(Exception serviceexception) {
			throw new ServiceExceptionHandler("service class",serviceexception);
		}
		return productlist;
	}
	
	public String insertProduct(ProductDto productdto) throws DataExceptionHandler, ServiceExceptionHandler  {
		try {
			int count=productdao.insert(productdto);
			if(count>0) 
				return "inserted";
				else
					return "not inserted";
				}
			catch(DataExceptionHandler dataexception) {
				throw dataexception;
			}
			catch(Exception servicexception) {
				throw new ServiceExceptionHandler("excepion at service class",servicexception);
				
			}
			
		}
	public String quantUpdated(OrderDto orderdto) throws DataExceptionHandler {
		try {
			int quantity_updated=productdao.update(orderdto);
			if(quantity_updated > 0) 
				return "updated quantity";
				else
					return "not updated quantity";
			}
		catch(DataExceptionHandler dataexception) {
			throw dataexception;
		}
		
		
	}
	
	public String deleteCheckProduct(int productid) throws DataExceptionHandler,ServiceExceptionHandler{
		try {
			int deleted_row=productdao.delete(productid);
			if(deleted_row>0)
				return "deleted";
				else
					return "not deleted";
		}
		catch(DataExceptionHandler dataexception) {
			throw dataexception;
		}
		catch(Exception svcexc) {
			throw new ServiceExceptionHandler("exception in service ",svcexc);
		}
	}

}
