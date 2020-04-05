package com.omnicuris.service;

import java.sql.SQLException;
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
@Qualifier("ordersvc")
public class OrderSvc {
	
	@Autowired
	@Qualifier("orderdao")
	OrderDao orderdao;
	
	@Autowired
	ProductDao productdao;
	
	public String insertOrder(OrderDto orderdto) throws DataExceptionHandler,ServiceExceptionHandler  {
		
		try {
			int count=0;
		//	int quant=odao.quantity_orders(odto.getOrderid());
			int quant=orderdto.getQuantity();
			int quant_prod=productdao.quantity_product(orderdto.getProductid());
			System.out.println("p q" + quant_prod);
			System.out.println("order q"+quant);
			
			if(quant > quant_prod) {
				return "out of stock";
			}else {
			 count=orderdao.insert(orderdto);
			 productdao.update(orderdto);
			}
			if(count > 0) 
				return "inserted";
				else
					return "not inserted";
		}
		catch(DataExceptionHandler dataexceptionhandler) {
			throw dataexceptionhandler;
			}
		catch(Exception serviceexceptionhandler) {
			throw new ServiceExceptionHandler("exception in service class",serviceexceptionhandler);
		}
		
	}
	
	public ArrayList<OrderDto> selectOrder(int orderid) throws DataExceptionHandler, ServiceExceptionHandler{
		ArrayList<OrderDto> orderlist=null;
		try {
			
			orderlist= orderdao.select(orderid);
		}
		catch(DataExceptionHandler dataexc) {
			throw dataexc;
		}
		catch(Exception svcexc) {
			throw new ServiceExceptionHandler("service class",svcexc);
		}
		return orderlist;
	}
	
	

}
