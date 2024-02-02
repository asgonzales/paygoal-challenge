![banner](./readmeMedia/LargeBanner.jpg)

# Introducción

Instalación y documentación del challenge de PayGoal para Jr Developer

# Requisitos

- JDK 1.8
- Maven 3

# Instalación del Proyecto


### En el directorio raíz

```bash
    mvn spring-boot:run
```

# Endpoints

## Ruta inicial localhost:8080/api

las respuestas tienen el siguiente formato
si salió bien:
```bash
    {
        data: {} | []
        success: true
        httpStatus: OK | CREATED
    }
```
si hubo algún error
```bash
    {
        success: false
        httpStatus: BAD_REQUEST | NOT_FOUND | UNPROCESSABLE_ENTITY
        message: origen del error
    }
```

# Productos

### GET localhost:8080/api/products

    Endpoint para obtener todos los productos.
    Se puede agregar las queries name y orderByPrice.
    name: busca por coincidencia de nombre.
    orderByPrice: Puede ser "asc" o "desc". Ordena los resultados por precio.

### Ejemplo

### Respuesta
```bash
    {
        data: [
        {
            id: 1,
            name: "vaso",
            description: "Es un vaso de cristal",
            price: 50.0,
            stock: 200
        },
        {
            id: 2,
            name: "tenedor",
            description: "Es un tenedor de madera",
            price: 120.0,
            stock: 2000
        },
        ...
        ],
        success: true,
        HttpStatus: OK
    }
```    
    
### Ejemplo

### GET localhost:8080/api/products?name=cu
    devuelve los productos que contengan "cu" en su nombre

### Respuesta
```bash
    {
        data: [
            {
            id: 3,
            name: "cuchara",
            description: "Una cuchara de cemento",
            price: 150.0,
            stock: 250
        },
        {
            id: 4,
            name: "cuchillo",
            description: "Es un cuchillo de plastico",
            price: 70.0,
            stock: 300
        }]
        success: true
        httpStatus: OK
    }    
```

### Ejemplo

### GET localhost:8080/api/products?name=cu&orderByPrice=asc
    devuelve los productos que contengan "cu" en su nombre, ordenados ascendentemente por el precio

### Respuesta
```bash
    {
        data: [
            {
            id: 4,
            name: "cuchillo",
            description: "Es un cuchillo de plastico",
            price: 70.0,
            stock: 300
        },  
        {
            id: 3,
            name: "cuchara",
            description: "Una cuchara de cemento",
            price: 150.0,
            stock: 250
        }]
        success: true
        httpStatus: OK
    }    
```

## GET localhost:8080/api/products/:id

    Endpoint para obtener un producto por id.

### Ejemplo

### GET localhost:8080/api/products/2
### Respuesta
```bash
    {
        data: {
            id: 2,
            name: "tenedor",
            description: "Es un tenedor de madera",
            price: 120.0,
            stock: 2000
        },
        success: true
        httpStatus: OK
    }    
```

## POST localhost:8080/api/products
    Endpoint para crear un producto.

### Ejemplo

### BODY:
```bash
    {
        name: "nuevoProducto",
        description: "Descripcion del nuevo producto",
        price: 12,
        stock: 123
    }    
```
### Respuesta
```bash
    {
        data: {
            id: 8,
            name: "nuevoProducto",
            description: "Descripcion del nuevo producto",
            price: 12.0,
            stock: 123
        },
        success: true,
        httpStatus: "CREATED"
    }    
```


## PUT localhost:8080/api/products/:id
    Endpoint para actualizar un producto por id.

### Ejemplo

### PUT localhost:8080/api/products/2
### BODY:
```bash
    {
        name: "nombreActualizado",
        description: "Descripcion actualizada del producto",
        price: 112,
        stock: 13
    }    
```
### Respuesta:
```bash
    {
        data: {
            id: 2,
            name: "nombreActualizado",
            description: "Descripcion actualizada del producto",
            price: 112,
            stock: 13
        },
        success: true,
        httpStatus: "OK"
    }    
```

## DELETE localhost:8080/api/products/:id
    Endpoint para eliminar un producto por id. Devuelve el producto eliminado.

### Ejemplo

### DELETE localhost:8080/api/products/1
### Respuesta:
```bash
    {
        data: {
            id: 1,
            name: "vaso",
            description: "Es un vaso de cristal",
            price: 50.0,
            stock: 200
        },
        success: true,
        httpStatus: "OK"
    }  
```