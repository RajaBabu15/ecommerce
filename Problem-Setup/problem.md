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

# BASIC STARTER ENDPOINTS

1. Load some sample data into out database.
2. Create some basic endpoints to get that data.
3. Test to ensure the correct data is presented.


# ENDPOINT SECURITY

1. Make all endpoints secured by authentication by default.
2. Put exclusions to requiring authentication on specific endpoints.
3. Test that our authentication on endpoints work.


# EMAIL VERIFICATION

1. Decide the email verification workflow.
2. Download a tool to facilitate SMTP communication testing.
3. Send an email at user registration with verification link.
4. Add endpoint to verify the user.
5. Block logging in if the user is not verified.

```declarative

BEGIN Workflow

# When a user registers
IF User registers THEN
    Send email with verification link
ENDIF

# When a user attempts to login
IF User attempts login THEN
    IF User is verified THEN
        Continue with normal login
    ELSE
        IF Email was sent in the last hour THEN
            Tell them we've recently sent an email
        ELSE
            Resend email
        ENDIF
    ENDIF
ENDIF

# When a user uses the verification link
IF User verification link used THEN
    IF User is verified THEN
        Do nothing, they're already verified
    ELSE
        Verify the account
        Provide auth token
    ENDIF
ENDIF

END Workflow
```





























