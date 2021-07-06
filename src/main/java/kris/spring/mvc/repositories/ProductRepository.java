package kris.spring.mvc.repositories;

import kris.spring.mvc.exceptions.NoSuchProductException;
import kris.spring.mvc.models.Product;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;

@Component
public class ProductRepository {
    private List<Product> productList;
    private long nextID;

    @PostConstruct
    public void init() {
        productList = new LinkedList<>();
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

    public Product getProductByID(long id) throws NoSuchProductException {
        try{
        return productList.stream().filter(a -> a.getId() == id).findFirst().get();}
        catch (NoSuchElementException e ){
            throw new NoSuchProductException();
        }
    }
}
