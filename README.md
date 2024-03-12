# Fake Store API Custom Implementation

This is a simple custom implementation of the Fake Store API.

# API Endpoints

### Get entire cart

```
GET /cart
```

### Get product from cart

```
GET /cart/{id}
```

### Get all categories

```
GET /cart/categories
```

### Get cart in a specific category

```
GET /cart/category/{name}
```

### Add a new product to cart

```
POST /cart
```
```
body:{
  title: {title},
  price: {price},
  category: {
    name: {categoryname}
  },
  description: {description},
  imageURL: {imageURL}
}
```

### Delete a product

```
DELETE /cart/{id}
```