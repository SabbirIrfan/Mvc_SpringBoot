When designing and writing APIs, it's crucial to follow RESTful principles and HTTP standards for clarity, scalability, and ease of use. Below is a guideline on when to use specific HTTP methods and general best practices for API design:

### 1. **HTTP Methods**

Each HTTP method has a specific use case based on the CRUD (Create, Read, Update, Delete) operations:

- **GET**: 
  - **Purpose**: To retrieve data from the server.
  - **Usage**: Use `GET` requests to read or fetch data without modifying anything on the server.
  - **Example**: Fetching a list of products or a specific product.
    - `GET /products`: Fetch all products.
    - `GET /products/{id}`: Fetch a specific product by its ID.
  - **Idempotent**: Multiple identical `GET` requests should have the same effect as a single one.

- **POST**: 
  - **Purpose**: To create new resources on the server.
  - **Usage**: Use `POST` when you want to create new resources, such as adding new data or submitting forms.
  - **Example**: Creating a new product.
    - `POST /products`: Create a new product with the request body data.
  - **Not idempotent**: Multiple identical `POST` requests may create multiple resources.

- **PUT**: 
  - **Purpose**: To update or replace an existing resource.
  - **Usage**: Use `PUT` when you want to update a resource entirely. The `PUT` request will replace the resource with the new representation provided.
  - **Example**: Updating a product's details.
    - `PUT /products/{id}`: Replace the product with the given ID entirely.
  - **Idempotent**: Multiple identical `PUT` requests should have the same effect as one.

- **PATCH**: 
  - **Purpose**: To partially update an existing resource.
  - **Usage**: Use `PATCH` when you only want to modify a part of the resource rather than replacing it entirely.
  - **Example**: Updating just the price of a product.
    - `PATCH /products/{id}`: Update specific fields of a product.
  - **Idempotent**: Multiple identical `PATCH` requests should have the same effect as one.

- **DELETE**: 
  - **Purpose**: To remove a resource from the server.
  - **Usage**: Use `DELETE` to remove resources.
  - **Example**: Deleting a product by ID.
    - `DELETE /products/{id}`: Remove the product with the given ID.
  - **Idempotent**: Multiple identical `DELETE` requests should have the same effect as one.

### 2. **General API Design Guidelines**

#### 2.1. **Resource Naming Conventions**
- **Use nouns** in plural form to represent resources (not verbs).
  - Example: `/users`, `/products`, `/orders`
- **Avoid verbs** in URIs, as the HTTP method already describes the action.
  - Bad: `/getUser`, `/createProduct`
  - Good: `/users`, `/products`

#### 2.2. **URL Structure**
- **Hierarchy**: Follow a hierarchical structure for resource identification.
  - Example: `/users/{userId}/orders/{orderId}` for a user’s specific order.
- **Avoid deep nesting**: Limit nested resource paths to a maximum of two levels to prevent overly complex URIs.

#### 2.3. **Use Query Parameters for Filtering, Sorting, and Pagination**
- **Filtering**: To filter resources.
  - Example: `GET /products?category=electronics&price_min=100`
- **Sorting**: To sort resources.
  - Example: `GET /products?sort=price,desc`
- **Pagination**: To manage large datasets.
  - Example: `GET /products?page=2&size=10`

#### 2.4. **Status Codes**
Use appropriate status codes to provide feedback on the result of an API call:
- **200 OK**: Request was successful.
- **201 Created**: Resource was successfully created (use with `POST`).
- **204 No Content**: Request was successful but no response body (use with `DELETE`).
- **400 Bad Request**: Request contains invalid data or syntax.
- **401 Unauthorized**: Authentication is required but not provided or invalid.
- **403 Forbidden**: User is authenticated but does not have permission.
- **404 Not Found**: Resource could not be found.
- **409 Conflict**: Request could not be completed due to a conflict (e.g., duplicate resource).
- **500 Internal Server Error**: Generic error when the server fails.

#### 2.5. **Error Handling**
- Use meaningful error messages in the response body.
  - Example:
    ```json
    {
      "error": "Invalid data format",
      "message": "The field 'email' must be a valid email address."
    }
    ```
- Provide details for developers to understand and fix the issue.

#### 2.6. **Versioning**
Always version your API to avoid breaking changes for clients.
- **URL versioning**: Example: `/v1/products`
- **Header versioning** (less common): Use custom headers to specify the version.

#### 2.7. **Security**
- **Authentication**: Use OAuth2, API keys, or JWT tokens for API security.
- **Authorization**: Use role-based access control (RBAC) to determine access levels for different users.
- **HTTPS**: Ensure that all API traffic is sent over HTTPS to protect data in transit.

#### 2.8. **Consistency**
- Keep the API consistent with naming conventions, methods, and response formats.
- Use camelCase for JSON field names: 
  - Example: `{ "productId": 1, "productName": "Laptop" }`
  
#### 2.9. **Rate Limiting**
Implement rate limiting to prevent abuse of the API. Provide clear feedback in case of throttling:
- **429 Too Many Requests**: Return when the rate limit is exceeded.

#### 2.10. **Documentation**
- Always document your API with tools like Swagger, Postman, or Redoc to help developers understand how to use it.
- Include request and response examples, possible error codes, and descriptions.

### 3. **When to Use Specific Methods**

- **GET**: Fetching resources (list or single item).
  - `GET /products`: Retrieves all products.
  - `GET /products/{id}`: Retrieves a specific product.
  
- **POST**: Creating a new resource.
  - `POST /products`: Creates a new product.
  
- **PUT**: Full update of a resource.
  - `PUT /products/{id}`: Updates a specific product with complete new data.

- **PATCH**: Partial update of a resource.
  - `PATCH /products/{id}`: Partially updates the product (e.g., just the price).
  
- **DELETE**: Deleting a resource.
  - `DELETE /products/{id}`: Deletes a specific product.

By following these best practices and guidelines, your API will be more intuitive, easier to use, and scalable.