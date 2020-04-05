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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.omnicuris.Dto.ProductDto;
import com.omnicuris.exceptionhandle.DataExceptionHandler;
import com.omnicuris.exceptionhandle.ServiceExceptionHandler;
import com.omnicuris.service.ProductSvc;


@RestController
@RequestMapping(value={"/product"})
public class ProductController {
	final static Logger log = LoggerFactory.getLogger(ProductController.class);
	
	@Autowired
	@Qualifier("productsvc")
	ProductSvc productsvc;
	
	 @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	 public ArrayList<ProductDto> getProduct(@PathVariable("id") int id) throws DataExceptionHandler, ServiceExceptionHandler{
		 System.out.println("Fetching User with id " + id);
		 ArrayList<ProductDto> listproduct=null;
		 try {
			 listproduct=productsvc.selectProduct(id);
		 
		 if(listproduct == null) {
			 return listproduct;
		 }
		 }
		 
		 catch(DataExceptionHandler dataexc) {
			 log.error("Exception in dao",dataexc);
		 }
		 catch(ServiceExceptionHandler svcexc) {
			 log.error("Exception",svcexc);
		 }
		 return listproduct;
		 
		 
	 }
	
	 @PostMapping(value="/insert",headers="Accept=application/json")
	 public ResponseEntity<Void> insertProduct(@RequestBody ProductDto productdto, UriComponentsBuilder ucBuilder){
		 System.out.println("Creating user");
		 try {
			String check_insert=productsvc.insertProduct(productdto);
		} catch (DataExceptionHandler | ServiceExceptionHandler e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 HttpHeaders headers = new HttpHeaders();
	        headers.setLocation(ucBuilder.path("/product/{id}").buildAndExpand(productdto.getProductid()).toUri());
	        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	 }
	 
	 @DeleteMapping(value="/delete/{id}")
	 public  void deleteProduct(@PathVariable("id")int id ) {
		 String delete;
		 try {
			 delete=productsvc.deleteCheckProduct(id);
////			 if(del=="deleted") {
//				 return  Response("deleted",HttpStatus.OK);
//				 else
//				return 	  ResponseEntity("
//			 }
		 }
		 catch(DataExceptionHandler dataexc) {
			 log.error("Exception in dao",dataexc);
		 }
		 catch(ServiceExceptionHandler svcexc) {
			 log.error("Exception",svcexc);
		 }
			 
			 
		 }
		 
	 }
	 


