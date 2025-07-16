package cl.dsy1103.products.services;

import java.util.List;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.dsy1103.products.model.Product;
import cl.dsy1103.products.repository.ProductRepository;

@Service
@Transactional
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public Product addProduct(Product product) {
        Product newProduct = productRepository.save(product);
        return newProduct;
    }

    public Product getProductById(int id) {
        return productRepository.findById(id).orElse(null);
    }

    public Product updateProduct(Product product) {
        Product oldProduct = productRepository.findById(product.getId()).orElse(null);
        if (oldProduct == null) {
            // throw new ProductNotFoundException("Product not found with id: " + id);
            return null;
        }
        Product updatedProduct = productRepository.save(product);
        return updatedProduct;
    }

    public Product deleteProduct(int id) {
        Product product = productRepository.findById(id).orElse(null);
        if (product ==  null){
            return null;
        }
        productRepository.deleteById(id);
        return product;
    }

}
