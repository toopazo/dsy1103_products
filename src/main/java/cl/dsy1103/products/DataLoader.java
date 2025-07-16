package cl.dsy1103.products;

import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import cl.dsy1103.products.model.Product;
import cl.dsy1103.products.repository.ProductRepository;

import java.time.LocalDateTime;
// import java.util.Random;

// Ver detalles de Profile en Spring Boot en la siguiente URL: https://docs.spring.io/spring-boot/reference/features/profiles.html
@Profile("dev")
// @Profile("do_not_run")
@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker();
        // Random random = new Random();

        // Generar libros
        for (int i = 0; i < 10; i++) {
            Product product = new Product();
            // product.setId(i + 1);
            product.setName(faker.name().fullName());
            product.setSupplier(faker.name().title());
            LocalDateTime localDateTime = LocalDateTime.now();
            product.setCreatedAt(localDateTime.plusDays(i));

            System.out.println("Object generated: " + product);

            // Guardar el objeto en la base de datos
            try {
                productRepository.save(product);
            } catch (org.springframework.dao.DataIntegrityViolationException | org.hibernate.exception.GenericJDBCException e) {
            // } catch (org.springframework.dao.DataIntegrityViolationException e) {
                // Handle the exception
                System.err.println(e.getMessage());
            } 
        }
    }
}
