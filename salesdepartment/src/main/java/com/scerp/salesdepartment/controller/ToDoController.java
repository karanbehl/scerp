package com.scerp.salesdepartment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.scerp.salesdepartment.domain.OrderEntity;
import com.scerp.salesdepartment.domain.ProductEntity;
import com.scerp.salesdepartment.domain.ShipmentEntity;
import com.scerp.salesdepartment.domain.ToDoEvent;
import com.scerp.salesdepartment.repository.OrderRepository;
import com.scerp.salesdepartment.repository.ProductRepository;
import com.scerp.salesdepartment.repository.SalesQueryRepository;
import com.scerp.salesdepartment.repository.ShipmentRepository;
import com.scerp.salesdepartment.service.ToDoService;


@RestController
@RequestMapping("/")
public class ToDoController {
	@Autowired
	private ToDoService toDoService;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private SalesQueryRepository salesQueryRepository;
	
	@Autowired
	private ShipmentRepository shipmentRepository;
	


	@GetMapping
	public Iterable<ToDoEvent> getToDoEvent() {
		return toDoService.getAllnonDoneEvents();
	}
	
	@GetMapping
	@RequestMapping("/getProducts")
	public String getProduct() {
		return productRepository.findAll().toString();
//		return "hello prodcut, we just did inter project communication!";
	}
	
	@GetMapping
	@RequestMapping("/buyProducts")
	public String buyProducts(@RequestBody OrderEntity orderInfo) {
		ProductEntity product = productRepository.findByProductID(orderInfo.getProductId());
		orderInfo.setProductId(product.getProductId());
		orderInfo.setProductName(product.getProductName());
		int totalInvoiceSum  = 0;
		totalInvoiceSum = Integer.parseInt(orderInfo.getQuantity())*Integer.parseInt(product.getPrice());
		orderInfo.setTotalInvoiceSum(""+totalInvoiceSum);
		orderRepository.save(orderInfo);
		return "Product succesfully bought";
	}
	
	
	@RequestMapping(value = "/addProduct", method = RequestMethod.POST)
	public String addProduct(@RequestBody ProductEntity productInfo){
		productRepository.save(productInfo);
		return "Product Added Succesfully";
	}
	
	@RequestMapping(value = "/deleteProduct", method = RequestMethod.POST)
	public String deleteProduct(@RequestBody ProductEntity productInfo) {
		productRepository.deleteByproductId(productInfo.getProductId());
		return "Product Deleted Successfully";
	}
	
	@RequestMapping(value = "/modifyProduct")
	public String modifyProductDetails(@RequestBody ProductEntity productInfo) {
		productRepository.setNewProductDetailsForProduct(productInfo.getProductName(), productInfo.getPrice(), productInfo.getProductId() );
		return "Product Details Modified";
	}
	
	@RequestMapping(value = "/monitorInventory", method = RequestMethod.POST)
	public String modifyProductInventory(@RequestBody ProductEntity productInfo) {
		productRepository.setNewQuantityForProduct(productInfo.getQuantity(), productInfo.getProductId());
		return "Product Inventory Modified";
	}
	
	@RequestMapping(value = "/createShipment", method = RequestMethod.POST)
	public String addProduct(@RequestBody ShipmentEntity shipmentInfo){
		OrderEntity orderEntity = orderRepository.findByProductId(shipmentInfo.getProductId());
		if(orderEntity.getPaymentInfo()==true) {
		shipmentInfo.setProductId(orderEntity.getProductId());
		shipmentInfo.setAddress(orderEntity.getDeliveryAddress());
		shipmentRepository.save(shipmentInfo);
		return "Shipment Created Succesfully" + shipmentInfo.getOrderNumber() ;
		}
		else {
			return "Shipment cannot be created";
		}
	}

}