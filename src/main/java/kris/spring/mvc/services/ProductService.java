package kris.spring.mvc.services;

import kris.spring.mvc.models.Product;
import kris.spring.mvc.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.List;

@Component
public class ProductService {
    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    public List<Product> getAllProducts() {
        return productRepository.getAllProducts();
    }

    public void add(Product product) {
        productRepository.addProduct(product);
    }

    public void deleteByID(long id) {
        productRepository.deleteByID(id);
    }

    public long getNextID(){
        return productRepository.getNextID();
    }
}
