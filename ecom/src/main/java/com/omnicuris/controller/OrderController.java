package com.omnicuris.controller;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.omnicuris.Dto.OrderDto;
import com.omnicuris.Dto.ProductDto;
import com.omnicuris.exceptionhandle.DataExceptionHandler;
import com.omnicuris.exceptionhandle.ServiceExceptionHandler;
import com.omnicuris.service.OrderSvc;


@RestController
@RequestMapping(value={"/order"})
public class OrderController {
	final static Logger log = LoggerFactory.getLogger(OrderController.class);
	
	@Autowired
	@Qualifier("ordersvc")
	OrderSvc ordersvc;
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	 public ArrayList<OrderDto> getOrder(@PathVariable("id") int id) throws DataExceptionHandler, ServiceExceptionHandler{
		 System.out.println("Fetching User with id " + id);
		 ArrayList<OrderDto> orderlist=null;
		 try {
			 orderlist=ordersvc.selectOrder(id);
		 
		 if(orderlist == null) {
			 return orderlist;
		 }
		 }
		 
		 catch(DataExceptionHandler dataexc) {
			 log.error("Exception in dao",dataexc);
		 }
		 catch(ServiceExceptionHandler svcexc) {
			 log.error("Exception",svcexc);
		 }
		 return orderlist;
		 
		 
	 }
	
	
	
	
	@PostMapping(value="/insert",headers="Accept=application/json")
	public ResponseEntity<String> Insert_ord(@RequestBody OrderDto orderdto, UriComponentsBuilder ucBuilder)  {
		String check_insert="hi";
		try{
			 check_insert=ordersvc.insertOrder(orderdto);
			 
		}
		catch(DataExceptionHandler daexc) {
			log.error("Exception in data",daexc);
		}
		catch(ServiceExceptionHandler svcexceptionhandler) {
			log.error("Exception in service",svcexceptionhandler);
		}
		 HttpHeaders headers = new HttpHeaders();
	        headers.setLocation(ucBuilder.path("/order/{id}").buildAndExpand(orderdto.getOrderid()).toUri());
	        
	        return new ResponseEntity<String>(check_insert, HttpStatus.CREATED);
	}
	
	
	
	

}
