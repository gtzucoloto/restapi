package com.gabriel.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gabriel.model.Product;
import com.gabriel.model.repository.ProductRepository;
import com.gabriel.resource.exception.ProductNotFoundException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api")
@Api(value = "Products REST API")
@CrossOrigin(origins="*")
public class ProductResource {
	@Autowired
	ProductRepository productRepository;
	
	@GetMapping("/products")
	@ApiOperation(value="List all products")
	public List<Product> all() {
		return this.productRepository.findAll();
	}
	
	@GetMapping("/products/{id}")
	@ApiOperation(value="Get product by id")
	public Product one(@PathVariable long id) {
		return this.productRepository.findById(id)
				.orElseThrow(() -> new ProductNotFoundException(id));
		
	}
	
	@PostMapping("/products")
	@ApiOperation(value="Save a product")
	@ResponseStatus(HttpStatus.CREATED)
	public Product create(@RequestBody Product product) {
		return this.productRepository.save(product);
	}
	
	@DeleteMapping("/products/{id}")
	@ApiOperation(value="Delete a product")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteProdut(@PathVariable Long id) {
		this.productRepository.deleteById(id);
	}
	
	@PutMapping("/products/{id}")
	@ApiOperation(value="Update a product")
	public Product replace(@PathVariable Long id, @RequestBody Product newProduct) {
		return this.productRepository.findById(id)
				.map(product -> {
					product.setName(newProduct.getName());
					product.setQuantity(newProduct.getQuantity());
					product.setPrice(newProduct.getPrice());
					return this.productRepository.save(product);
				})
				.orElseGet(() -> {
					newProduct.setId(id);
					return this.productRepository.save(newProduct);
				});
	}
}
