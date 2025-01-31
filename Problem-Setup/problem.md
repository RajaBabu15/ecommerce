# The Requirements

The website will have `users` who can buy an array of `products` in different quantities. It will hold the `inventory` of stock and allow users to make `orders` to ship to their `address`.

## User

- ID
- UserName
- Encrypted password
- Email
- First Name
- Middle Name
- Last Name

## Address

- ID
- User
- Address Line 1
- Address Line 2
- City
- Country
- Post Code
- Active?

## Product

- ID
- Name
- Short Description
- Long Description
- Price

 ## Inventory

- Product
- In Stock Quantity

## Order

- ID
- User
- Address
- Products+quantities

# LOGIN

1. Adding the spring boot security to the App.
2. Temporarily put a endpoint security bypass in.
3. Provide an endpoint for users to login and receive a JWT.

# REQUEST_AUTHENTICATION

1. Authenticate requests using the JWT token from the login process.
2. Test that the user is being authenticated or not.