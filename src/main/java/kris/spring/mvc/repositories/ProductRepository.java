package kris.spring.mvc.repositories;

import kris.spring.mvc.models.Product;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class ProductRepository {
    private List<Product> productList;
    private long nextID;

    @PostConstruct
    public void init() {
        productList = new ArrayList<>();
        nextID = 1L;
    }

    public List<Product> getAllProducts(){
        return Collections.unmodifiableList(productList);
    }

    public void addProduct(Product product){
        nextID++;
        productList.add(product);
    }

    public void deleteByID(long id){
        productList.removeIf(a -> a.getId() == id);
    }

    public long getNextID() {
        return nextID;
    }
}
