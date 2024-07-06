package com.example.springboot.controller;


import com.example.springboot.model.ProductModel;
import com.example.springboot.repositories.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class ProductController {
    @Autowired
    ProductRepository productRepository;

    @GetMapping("/products")
    public ResponseEntity<List<ProductModel>> getAllProducts(){
        return new ResponseEntity<List<ProductModel>>(productRepository.findAll(), HttpStatus.OK);
    }
    @GetMapping("/products/{id}")
    public ResponseEntity<ProductModel> getOneProduct(@PathVariable(value="id") UUID id){
        Optional<ProductModel> productO = productRepository.findById(id);
        /*
        * Uma maneira de ser feita utilizando uma recomendação da IDE é utilizando o método map
        * return productO.map(productModel -> new ResponseEntity<>(productModel, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        * */
        if(productO.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<ProductModel>(productO.get(), HttpStatus.OK);
    }
    @PostMapping("/products")
    public ResponseEntity<ProductModel> saveProduct(@RequestBody @Valid ProductModel product){
        return new ResponseEntity<ProductModel>(productRepository.save(product), HttpStatus.CREATED);
    }
    @DeleteMapping("/products/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable(value="id") UUID id){
        Optional<ProductModel> product0 = productRepository.findById(id);
        if(product0.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        productRepository.delete(product0.get());
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping("/product/{id}")
    public ResponseEntity<ProductModel> updateProduct(@PathVariable(value="id") UUID id, @RequestBody @Valid ProductModel product){
        Optional<ProductModel> product0 = productRepository.findById(id);
        if(product0.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        product.setIdProduct(product0.get().getIdProduct());
        return new ResponseEntity<ProductModel>(productRepository.save(product), HttpStatus.OK);
    }
}
