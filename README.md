# dsy1103_products
Demo del microservicio ```products``` de la app web de un e-commerce. Los microservicios disponibles son:
- https://github.com/toopazo/dsy1103_orders
- https://github.com/toopazo/dsy1103_products

## Framework y librerías
La app web está basada en el framework Spring Boot (Java). Se hace uso del decorador @RestController (https://spring.io/guides/gs/rest-service) para construir una API Rest.

Se usan las librerías:
- JPA: https://spring.io/projects/spring-data-jpa
- Lombok: https://projectlombok.org/
- Datafaker: https://www.datafaker.net/
- OpenApi OAS: https://springdoc.org/
- HATEOAS: https://spring.io/projects/spring-hateoas
- Junit: https://docs.junit.org/current/user-guide/

## Estructura de archivos fuente

La estructura del código se puede explorar con
```bash
tree src/main/java/cl/dsy1103/products/  -L 2
```
```bash
src/main/java/cl/dsy1103/products/
├── assembler
│   └── ProductModelAssembler.java
├── controller
│   └── ProductController.java
├── DataLoader.java
├── model
│   └── Product.java
├── ProductApplication.java
├── repository
│   └── ProductRepository.java
└── services
    └── ProductService.java
```

## Breve ejemplo de respuesta

Un ejemplo de respuesta de la API con el método GET en ```http://localhost:8082/api/v1/products``` es:
```json
{
    "_embedded": {
        "productList": [
            {
                "id": 1,
                "name": "Isaac Lehner",
                "supplier": "Chief Infrastructure Liaison",
                "createdAt": "2025-07-15T19:44:17.751368",
                "_links": {
                    "self": {
                        "href": "http://localhost:8083/api/v1/products/1"
                    },
                    "products": {
                        "href": "http://localhost:8083/api/v1/products"
                    },
                    "delete": {
                        "href": "http://localhost:8083/api/v1/products/1"
                    }
                }
            },
            {
                "id": 2,
                "name": "Miss Frankie Sauer",
                "supplier": "Product Mobility Architect",
                "createdAt": "2025-07-16T19:44:18.149934",
                "_links": {
                    "self": {
                        "href": "http://localhost:8083/api/v1/products/2"
                    },
                    "products": {
                        "href": "http://localhost:8083/api/v1/products"
                    },
                    "delete": {
                        "href": "http://localhost:8083/api/v1/products/2"
                    }
                }
            },


    "_links": {
        "self": {
            "href": "http://localhost:8083/api/v1/products"
        }
    }
}  
```