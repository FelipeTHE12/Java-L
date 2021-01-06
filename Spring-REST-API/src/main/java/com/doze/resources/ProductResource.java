package com.doze.resources;

import com.doze.models.Product;
import com.doze.repository.ProductRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value ="/doze")
@Api(value =" API REST Products")
@CrossOrigin(origins = "*")

public class ProductResource {
    @Autowired
    ProductRepository productRepository;

    @GetMapping("/product")
    @ApiOperation(value="Return a list with all products")
    public List<Product> listProducts(){
        return productRepository.findAll();
    }

    @GetMapping("/product/{id}")
    @ApiOperation(value="Return a unique product from the list")
    public Product unicProduct(@PathVariable(value="id") long id){
        return productRepository.findById(id);
    }


    @PostMapping("/product")
    @ApiOperation(value="Save a product in the list")
    public Product saveProduct(@RequestBody Product product){
        return productRepository.save(product);
    }

    @DeleteMapping("/product")
    @ApiOperation(value="Delete a product from the list")
    public void deleteProduct(@RequestBody Product product){
        productRepository.delete(product);
    }

    @PutMapping("/product")
    @ApiOperation(value="Updates a product from the list")
        public Product refreshProduct(@RequestBody Product product){
            return productRepository.save(product);
        }



}
