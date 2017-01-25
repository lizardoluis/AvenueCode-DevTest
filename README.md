# Avenue Code Application #

Development test for Avenue Code.


### Requisites ###

* Java 8
* Maven 3

### How to run automated test ###


```
#!console

../AvenueCode-DevTest$ mvn clean
../AvenueCode-DevTest$ mvn test
```


### How to run the application ###


```
#!console

../AvenueCode-DevTest$ mvn clean
../AvenueCode-DevTest$ mvn install
../AvenueCode-DevTest$ mvn spring-boot:run
```


### API ###

* Get all products: http://localhost:8080/products
* Get all products excluding relationships: http://localhost:8080/products?simple=true

* Get a specific product: http://localhost:8080/products/{id}
* Get a specific product excluding relationships: http://localhost:8080/products/{id}?simple=true

* Get set of child products for specific product: http://localhost:8080/products/{id}/subproducts
* Get set of images for specific product: http://localhost:8080/products/{id}/images

* Get all images: http://localhost:8080/images

* Add a product: 

POST Request: http://localhost:8080/products/

Header: Content-Type : application/json

Body: 

```
#!json

{
  "name": "productName",
  "description": "product description",
  "images" : [{"type": "imageType1", "path": "/home/type/image1"}, {"type": "imageType2", "path": "/home/type/image2"}],
  "subProducts" : [
  		{
  		  "name": "childproduct1",
		  "description": "child product"
  		}
  	]
}
```


### Comments ###

* I assumed that an Image Entity has a path to a image file stored in the file system, instead of storing the bytes of the image.
* I considered deleting an Project, also deletes the images and sub-products associated. Its also not possible to delete an image or sub-product associated with an parent product.
* I implemented a Product Entity with a list of subproducts and images. An alternative is to add a foreign key on both product and image relating them to their parent product. But I think it is more optimized to retrieve if they are on a list inside product.
* Image and Product implements all CRUD operations.
* I had a problem query a specific product and to return this product without images and subproducts. So in this type of query, I get the complete product, erase the images and subproducts and return to the user the simple version. Not a efficient solution though :/