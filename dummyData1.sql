-- Declare a variable for user id = 4
DECLARE @userId3 AS INT = 4;

-- Insert an address record for user id 4.
INSERT INTO address (address_line_1, city, country, user_id) 
VALUES ('456 New Avenue', 'Springfield', 'USA', @userId3);

-- Retrieve the newly inserted address id for user id 4.
DECLARE @address3 INT;
SELECT TOP 1 @address3 = id 
FROM address 
WHERE user_id = @userId3 
ORDER BY id DESC;

-- Insert a couple of orders for user id 4 using the new address.
INSERT INTO web_order (address_id, user_id) VALUES (@address3, @userId3);
INSERT INTO web_order (address_id, user_id) VALUES (@address3, @userId3);

-- Retrieve the order ids. Adjust the variable names as needed.
DECLARE @order6 INT, @order7 INT;
SELECT TOP 1 @order6 = id 
FROM web_order 
WHERE address_id = @address3 AND user_id = @userId3 
ORDER BY id DESC;
SELECT @order7 = id 
FROM web_order 
WHERE address_id = @address3 AND user_id = @userId3 
ORDER BY id DESC OFFSET 1 ROW FETCH FIRST 1 ROW ONLY;

-- Insert order quantities for the newly created orders.
-- Assumes that the product variables (@product1, @product2, etc.) are already defined.
INSERT INTO web_order_quantities (order_id, product_id, quantity) 
VALUES (@order6, @product1, 2);  -- e.g., 2 units of Product #1
INSERT INTO web_order_quantities (order_id, product_id, quantity) 
VALUES (@order6, @product4, 1);  -- e.g., 1 unit of Product #4

INSERT INTO web_order_quantities (order_id, product_id, quantity) 
VALUES (@order7, @product3, 3);  -- e.g., 3 units of Product #3
INSERT INTO web_order_quantities (order_id, product_id, quantity) 
VALUES (@order7, @product5, 4);  -- e.g., 4 units of Product #5
