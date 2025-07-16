package cl.dsy1103.products.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.dsy1103.products.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    // List<Product> findAll(); // This method is inherited from JpaRepository

    // List<Product> findByCreatedAt(LocalDateTime createdAt); // This method is
    // inherited from JpaRepository

    // @Query("SELECT m FROM dinner_product m WHERE m.menu_id = 'id'")
    // List<Product> findByMenuId(int id); // Custom query to find menus by name
    // pattern

    // @Query(value = "select * from dinner_product", nativeQuery = true)
    // List<Product> findAllProducts(); // Custom query to find all menus
}