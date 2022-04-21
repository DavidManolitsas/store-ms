-- customer
INSERT INTO CUSTOMER (id, name, tier) VALUES (1, 'Stefan Walker', 'Bronze');
INSERT INTO CUSTOMER (id, name, tier) VALUES (2, 'Daija Von', 'Bronze');
INSERT INTO CUSTOMER (id, name, tier) VALUES (3, 'Ariane Rodriguez', 'Silver');
INSERT INTO CUSTOMER (id, name, tier) VALUES (4, 'Marques Nikolaus', 'Gold');
INSERT INTO CUSTOMER (id, name, tier) VALUES (5, 'Rachelle Greenfelder', 'Bronze');
INSERT INTO CUSTOMER (id, name, tier) VALUES (6, 'Larissa White', 'Gold');
INSERT INTO CUSTOMER (id, name, tier) VALUES (7, 'Fae Heidenreich', 'Silver');
INSERT INTO CUSTOMER (id, name, tier) VALUES (8, 'Dino Will', 'Gold');
INSERT INTO CUSTOMER (id, name, tier) VALUES (9, 'Eloy Stroman', 'Silver');
INSERT INTO CUSTOMER (id, name, tier) VALUES (10, 'Brisa O''Connell', 'Silver');
-- products
INSERT INTO product (id, name, category, price) VALUES (1, 'Xbox Series S','Console', 499.95);
INSERT INTO product (id, name, category, price) VALUES (2, 'Xbox Series X','Console', 749.95);
INSERT INTO product (id, name, category, price) VALUES (3, 'PowerA Wireless Controller','Controller', 69.95);
INSERT INTO product (id, name, category, price) VALUES (4, 'Xbox Wireless Controller - White','Controller', 89.95);
INSERT INTO product (id, name, category, price) VALUES (5, 'Xbox Wireless Controller - Black','Controller', 89.95);
INSERT INTO product (id, name, category, price) VALUES (6, 'Xbox Wireless Controller - Blue','Controller', 94.95);
INSERT INTO product (id, name, category, price) VALUES (7, 'Xbox Wireless Controller - Red','Controller', 94.95);
INSERT INTO product (id, name, category, price) VALUES (8, 'Xbox Wireless Controller - Volt','Controller', 94.95);
INSERT INTO product (id, name, category, price) VALUES (9,'Xbox Adaptive Controller','Controller',129.99);
INSERT INTO product (id, name, category, price) VALUES (10,'Xbox Elite Wireless Controller Series 2','Controller',249.95);
INSERT INTO product (id, name, category, price) VALUES (11,'Xbox Wireless Adapter Windows 10','Controller', 29.95);
INSERT INTO product (id, name, category, price) VALUES (12,'Xbox Stereo Headset - 20th Anniversary', 'Headset', 99.95);
INSERT INTO product (id, name, category, price) VALUES (13,'Xbox Stereo Headset','Headset', 89.95);
INSERT INTO product (id, name, category, price) VALUES (14,'Xbox Wireless Headset','Headset', 149.95);
INSERT INTO product (id, name, category, price) VALUES (15,'Bang & Olufsen Beoplay','Headset', 840.00);
INSERT INTO product (id, name, category, price) VALUES (16,'Xbox One Chat Headset','Headset', 29.95);
INSERT INTO product (id, name, category, price) VALUES (17,'Seagate Storage Expansion Card - 1TB','Hard Drives & Storage', 349.95);
INSERT INTO product (id, name, category, price) VALUES (18,'Seagate Storage Expansion Card - 2TB','Hard Drives & Storage', 719.95);
INSERT INTO product (id, name, category, price) VALUES (19,'LEGO Star Wars The Skywalker Saga','Games', 89.95);
INSERT INTO product (id, name, category, price) VALUES (20,'Elden Ring','Games', 94.95);
INSERT INTO product (id, name, category, price) VALUES (21,'Tiny Tinas Wonderland','Games', 99.95);
INSERT INTO product (id, name, category, price) VALUES (22,'Call of Duty Vanguard','Games', 59.97);
INSERT INTO product (id, name, category, price) VALUES (23,'Minecraft','Games', 29.95);
INSERT INTO product (id, name, category, price) VALUES (24,'Cuphead','Games', 29.95);
INSERT INTO product (id, name, category, price) VALUES (25,'Dying Light 2','Games', 99.95);
INSERT INTO product (id, name, category, price) VALUES (26,'Halo: The Master Chief Collection','Games', 19.98);
INSERT INTO product (id, name, category, price) VALUES (27,'Forza Horizon 5', 'Games', 84.95);
INSERT INTO product (id, name, category, price) VALUES (28,'Among Us', 'Games', 5.96);
INSERT INTO product (id, name, category, price) VALUES (29,'Sea of Thieves', 'Games', 24.72);
INSERT INTO product (id, name, category, price) VALUES (30,'The Witcher 3', 'Games', 15.99);
INSERT INTO product (id, name, category, price) VALUES (31,'Halo Infinite', 'Games', 79.96);
INSERT INTO product (id, name, category, price) VALUES (32,'Cyberpunk 2077', 'Games', 49.97);
INSERT INTO product (id, name, category, price) VALUES (33,'Overwatch', 'Games', 32.98);
INSERT INTO product (id, name, category, price) VALUES (34,'F1 2021', 'Games', 19.99);
INSERT INTO product (id, name, category, price) VALUES (35,'It Takes Two', 'Games', 59.95);
-- orders
INSERT INTO orders (id, customer_id, status, order_date, delivery_date) VALUES (1, 3,'Pending', '2021-02-28', '2021-03-08');
INSERT INTO orders (id, customer_id, status, order_date, delivery_date) VALUES (2, 6,'Pending', '2021-02-28', '2021-03-05');
INSERT INTO orders (id, customer_id, status, order_date, delivery_date) VALUES (3, 4,'Delivered', '2021-04-10', '2021-04-18');
INSERT INTO orders (id, customer_id, status, order_date, delivery_date) VALUES (4, 2,'Shipped', '2021-03-22', '2021-03-27');
INSERT INTO orders (id, customer_id, status, order_date, delivery_date) VALUES (5, 3,'Pending', '2021-03-04', '2021-03-12');
INSERT INTO orders (id, customer_id, status, order_date, delivery_date) VALUES (6, 7,'Delivered', '2021-03-30', '2021-04-07');
INSERT INTO orders (id, customer_id, status, order_date, delivery_date) VALUES (7, 1,'Shipped', '2021-03-05', '2021-03-09');
INSERT INTO orders (id, customer_id, status, order_date, delivery_date) VALUES (8, 2,'Pending', '2021-03-27', '2021-04-05');
INSERT INTO orders (id, customer_id, status, order_date, delivery_date) VALUES (9, 8,'Pending', '2021-04-14', '2021-04-18');
INSERT INTO orders (id, customer_id, status, order_date, delivery_date) VALUES (10,9,'Pending', '2021-04-22', '2021-04-29');
-- order lines
-- order 1
INSERT INTO order_line(order_id, product_id, quantity) VALUES (1, 4, 1);
INSERT INTO order_line(order_id, product_id, quantity) VALUES (1, 1, 1);
INSERT INTO order_line(order_id, product_id, quantity) VALUES (1, 29, 1);
-- order 2
INSERT INTO order_line(order_id, product_id, quantity) VALUES (2, 17, 2);
-- order 3
INSERT INTO order_line(order_id, product_id, quantity) VALUES (3, 30, 1);
-- order 4
INSERT INTO order_line(order_id, product_id, quantity) VALUES (4, 20, 1);
INSERT INTO order_line(order_id, product_id, quantity) VALUES (4, 21, 1);
-- order 5
INSERT INTO order_line(order_id, product_id, quantity) VALUES (5, 33, 1);
-- order 6
INSERT INTO order_line(order_id, product_id, quantity) VALUES (6, 5, 2);
INSERT INTO order_line(order_id, product_id, quantity) VALUES (6, 13, 1);
INSERT INTO order_line(order_id, product_id, quantity) VALUES (6, 24, 1);
-- order 7
INSERT INTO order_line(order_id, product_id, quantity) VALUES (7, 34, 1);
INSERT INTO order_line(order_id, product_id, quantity) VALUES (7, 2, 1);
-- order 8
INSERT INTO order_line(order_id, product_id, quantity) VALUES (8, 2, 1);
-- order 9
INSERT INTO order_line(order_id, product_id, quantity) VALUES (9, 15, 1);
INSERT INTO order_line(order_id, product_id, quantity) VALUES (9, 26, 1);
-- order 10
INSERT INTO order_line(order_id, product_id, quantity) VALUES (10, 10, 1);
INSERT INTO order_line(order_id, product_id, quantity) VALUES (10, 32, 1);
INSERT INTO order_line(order_id, product_id, quantity) VALUES (10, 27, 1);
INSERT INTO order_line(order_id, product_id, quantity) VALUES (10, 25, 1);

