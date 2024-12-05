INSERT INTO categories (id, name, parent_category_id) VALUES 
('11111111-1111-1111-1111-111111111111', 'Drinks', NULL),
('22222222-2222-2222-2222-222222222222', 'Hot Drinks', '11111111-1111-1111-1111-111111111111'),
('33333333-3333-3333-3333-333333333333', 'Cold Drinks', '11111111-1111-1111-1111-111111111111'),
('44444444-4444-4444-4444-444444444444', 'Snacks', NULL),
('55555555-5555-5555-5555-555555555555', 'Sweet Snacks', '44444444-4444-4444-4444-444444444444'),
('66666666-6666-6666-6666-666666666666', 'Savory Snacks', '44444444-4444-4444-4444-444444444444');

INSERT INTO products (id, name, price, category_id, image, stock) VALUES 
('aaaaaaa1-aaaa-aaaa-aaaa-aaaaaaaaaaaa', 'Espresso Coffee', 3.50, '22222222-2222-2222-2222-222222222222', 'espresso.jpg', 2),
('aaaaaaa2-aaaa-aaaa-aaaa-aaaaaaaaaaaa', 'Green Tea', 3.00, '22222222-2222-2222-2222-222222222222', 'green_tea.jpg', 3),
('aaaaaaa3-aaaa-aaaa-aaaa-aaaaaaaaaaaa', 'Mineral Water', 2.50, '33333333-3333-3333-3333-333333333333', 'mineral_water.jpg', 5),
('aaaaaaa4-aaaa-aaaa-aaaa-aaaaaaaaaaaa', 'Cola Soda', 3.00, '33333333-3333-3333-3333-333333333333', 'cola_soda.jpg', 1),
('aaaaaaa5-aaaa-aaaa-aaaa-aaaaaaaaaaaa', 'Chocolate Cookies', 2.00, '55555555-5555-5555-5555-555555555555', 'chocolate_cookies.jpg', 0),
('aaaaaaa6-aaaa-aaaa-aaaa-aaaaaaaaaaaa', 'Mixed Nuts', 3.50, '66666666-6666-6666-6666-666666666666', 'mixed_nuts.jpg', 2);

INSERT INTO orders (id, status, total_price, card_token, payment_status, payment_date, payment_gateway, buyer_email, seat_letter, seat_number) VALUES 
('bbbbbbb1-bbbb-bbbb-bbbb-bbbbbbbbbbbb', 'OPEN', 6.00, '4111111111111111', 'PENDING', NULL, 'Stripe', 'buyer1@example.com', 'A', 5),
('bbbbbbb2-bbbb-bbbb-bbbb-bbbbbbbbbbbb', 'FINISHED', 5.00, '5500000000000004', 'PAID', '2024-12-01', 'PayPal', 'buyer2@example.com', 'B', 12);

INSERT INTO orders_products (order_id, products_id) VALUES 
('bbbbbbb1-bbbb-bbbb-bbbb-bbbbbbbbbbbb', 'aaaaaaa1-aaaa-aaaa-aaaa-aaaaaaaaaaaa'),
('bbbbbbb1-bbbb-bbbb-bbbb-bbbbbbbbbbbb', 'aaaaaaa3-aaaa-aaaa-aaaa-aaaaaaaaaaaa'),
('bbbbbbb2-bbbb-bbbb-bbbb-bbbbbbbbbbbb', 'aaaaaaa2-aaaa-aaaa-aaaa-aaaaaaaaaaaa'),
('bbbbbbb2-bbbb-bbbb-bbbb-bbbbbbbbbbbb', 'aaaaaaa5-aaaa-aaaa-aaaa-aaaaaaaaaaaa');
