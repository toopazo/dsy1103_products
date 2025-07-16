package cl.dsy1103.products.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import cl.dsy1103.products.model.Product;
import cl.dsy1103.products.services.ProductService;
import cl.dsy1103.products.assembler.ProductModelAssembler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	// @Autowired
	// private ProductController productController;

	@Autowired
    private ProductModelAssembler assembler;

	@GetMapping(value = "", produces = MediaTypes.HAL_JSON_VALUE)
	public ResponseEntity<CollectionModel<EntityModel<Product>>> getProducts() {
		List<EntityModel<Product>> products = productService.getProducts().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
		// Sort by id
		// products.sort((o1, o2) -> Integer.compare(o1.getContent().getId(), o2.getContent().getId()));
		CollectionModel<EntityModel<Product>> collection = CollectionModel.of(products,
                linkTo(methodOn(ProductController.class).getProducts()).withSelfRel());

		return new ResponseEntity<>(collection, HttpStatus.OK);
	}

	// Create POST
	@PostMapping("")
	public ResponseEntity<EntityModel<Product>> postProduct(@RequestBody Product product) {
		// int id = productService.getProducts().size() + 1;
		// int id = ++counter;
		// product.setId(id);
		product.setCreatedAt(LocalDateTime.now());
		Product newProduct = productService.addProduct(product);

		// return ResponseEntity
        //         .created(linkTo(methodOn(ProductController.class).getProductById(product.getId())).toUri())
        //         .body(assembler.toModel(product));

		EntityModel<Product> entityModel = assembler.toModel(newProduct);
		// URI location = linkTo(methodOn(ProductController.class).getProductById(product.getId())).toUri();
		// URI location = linkTo(productController.getProductById(newProduct.getId())).toUri();
		// return ResponseEntity.created(location).body(entityModel);
		return new ResponseEntity<>(entityModel, HttpStatus.CREATED);
	}

	// Read GET
	@GetMapping("{id}")
	public ResponseEntity<EntityModel<Product>> getProductById(@PathVariable("id") int id) {
		Product product = productService.getProductById(id);
		if (product == null) {
			Product nullProduct = new Product();
			EntityModel<Product> entityModel = assembler.toModel(nullProduct);
			return new ResponseEntity<>(entityModel, HttpStatus.NOT_FOUND);
			// return ResponseEntity.notFound().build();
		}
		EntityModel<Product> entityModel = assembler.toModel(product);
		return ResponseEntity.ok(entityModel);
	}

	// Update PUT
	@PutMapping("{id}")
	public ResponseEntity<EntityModel<Product>> putProductById(@RequestBody Product product) {
		Product updatedProduct = productService.updateProduct(product);
		EntityModel<Product> entityModel = assembler.toModel(updatedProduct);
		return ResponseEntity.ok(entityModel);
	}

	// Delete DELETE
	@DeleteMapping("{id}")
	public ResponseEntity<EntityModel<Product>> deleteProductById(@PathVariable("id") int id) {
		Product deletedProduct = productService.deleteProduct(id);
		if (deletedProduct ==  null){
			Product nullProduct = new Product();
			EntityModel<Product> entityModel = assembler.toModel(nullProduct);
			return new ResponseEntity<>(entityModel, HttpStatus.NOT_FOUND);
			// return ResponseEntity.notFound().build();
        }
		EntityModel<Product> entityModel = assembler.toModel(deletedProduct);
		return ResponseEntity.ok(entityModel);
	}

}
