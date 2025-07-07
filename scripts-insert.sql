-- This script creates the necessary tables and inserts initial data for the application user management system.
INSERT INTO society ("name", "address") VALUES ('SNN Raj Serenity', 'Begur Main Road, Yelenahalli, Begur');
INSERT INTO society ("name", "address") VALUES ('Prestige Sunrise Park', 'Hosur Road, Electronic City Phase 1, Bangalore');
INSERT INTO society ("name", "address") VALUES ('Brigade Lakefront', 'Varthur Road, Whitefield, Bangalore');
INSERT INTO society ("name", "address") VALUES ('Sobha Forest View', 'Sarjapur Road, Bangalore');
INSERT INTO society ("name", "address") VALUES ('Godrej Woodland', 'Sarjapur Road, Bangalore');
INSERT INTO society ("name", "address") VALUES ('Purva Palm Beach', 'Hennur Road, Bangalore');
INSERT INTO society ("name", "address") VALUES ('Prestige Lakeside Habitat', 'Varthur Road, Whitefield, Bangalore');
INSERT INTO society ("name", "address") VALUES ('Brigade Meadows', 'Kanakapura Road, Bangalore');
INSERT INTO society ("name", "address") VALUES ('Sobha Dream Acres', 'Panathur Road, Bangalore');
INSERT INTO society ("name", "address") VALUES ('Godrej Eternity', 'Kanakapura Road, Bangalore');
------------------------------------------------------------------------------------------------------------
INSERT INTO user_detail (
    name, email, phone_number, address,
    created_date, last_updated, last_updated_by, created_by
) VALUES
('Amit Gaurav', 'amitgaurav25@gmail.com', '9731855220', 'C2-902, SNN Raj Serenity, Begur, Bangalore', NOW(), NOW(), 'admin', 'admin'),
('Jane Smith', 'john@gmail.com', '9876543210', 'C3-101, Prestige Sunrise Park, Hosur Road, Bangalore', NOW(), NOW(), 'admin', 'admin'),
('Alice Wonder', 'alice@gmail.com', '9123456789', 'D4-202, Brigade Lakefront, Varthur Road, Bangalore',NOW(), NOW(), 'admin', 'admin'),
('Bob Builder', 'bob@gmail.com', '9988776655', 'E5-303, Sobha Forest View, Sarjapur Road, Bangalore', NOW(), NOW(), 'admin', 'admin'),
('Charlie Brown', 'charlie@yahoo.com', '8765432109', 'F6-404, Godrej Woodland, Sarjapur Road, Bangalore', NOW(), NOW(), 'admin', 'admin'),
('Diana Prince', 'diana@msn.in', '7654321098', 'G7-505, Purva Palm Beach, Hennur Road, Bangalore', NOW(), NOW(), 'admin', 'admin'),
('Ethan Hunt', 'ethan@apple.com', '6543210987', 'H8-606, Prestige Lakeside Habitat, Varthur Road, Bangalore', NOW(), NOW(), 'admin', 'admin'),
('Fiona Grey', 'fiona@microsoft.com', '5432109876', 'I9-707, Brigade Meadows, Kanakapura Road, Bangalore', NOW(), NOW(), 'admin', 'admin'),
('George Clark', 'george@google.com', '4321098765', 'J10-808, Sobha Dream Acres, Panathur Road, Bangalore', NOW(), NOW(), 'admin', 'admin'),
('Hannah Montana', 'hanah@netflix.com', '3210987654', 'K11-909, Godrej Eternity, Kanakapura Road, Bangalore', NOW(), NOW(), 'admin', 'admin');
-------------------------------------------------------------------------------------------------------------
INSERT INTO app_user (society_id, username, user_detail_id) VALUES
(1, 'amit', 1),
(1, 'jane_smith', 2),
(2, 'alice_wonder', 3),
(2, 'bob_builder', 4),
(3, 'charlie_brown', 5),
(4, 'diana_prince', 6),
(5, 'ethan_hunt', 7),
(6, 'fiona_grey', 8),
(6, 'george_clark', 9),
(7, 'hannah_montana', 10);
-------------------------------------------------------------------------------------------------------------
INSERT INTO user_credential (app_user_id, failed_login_attempts, is_temporary, society_id, password_hash) VALUES
(5,  0, false,  1, 'password'),
(6,  0, false, 2, 'password'),
(7,  0, false, 3, 'password'),
(8,  0, false, 4, 'password'),
(9,  0, false, 5, 'password'),
(10,  0, false, 6, 'password'),
(11,  0, false, 7, 'password'),
(12,  0, false, 8, 'password'),
(13,  0, false, 9, 'password'),
(14,  0, false, 10, 'password');
------------------------------------------------------------------------------------------------------------
INSERT INTO user_role (created_by, created_date, last_updated, last_updated_by, name, society_id)
values('admin', NOW(), NOW(), 'admin', 'admin', 1),
('admin', NOW(), NOW(), 'admin', 'user', 1),
('admin', NOW(), NOW(), 'admin', 'guest', 1);
------------------------------------------------------------------------------------------------------------
INSERT INTO user_role (created_by, created_date, last_updated, last_updated_by, name, society_id)
values('admin', NOW(), NOW(), 'admin', 'admin', 3),
('admin', NOW(), NOW(), 'admin', 'user', 3),
('admin', NOW(), NOW(), 'admin', 'guest', 3);
 ------------------------------------------------------------------------------------------------------------
INSERT INTO app_user_society_mapping (active, joined_at, app_user_id, society_id) values
(true, NOW(), 5, 1),
(true, NOW(), 6, 1),
(true, NOW(), 7, 3),
(true, NOW(), 8, 3)
------------------------------------------------------------------------------------------------------------
INSERT INTO app_user_roles (app_user_id, role_id) values
(5, 1),
(7, 1),
(7, 2),
(8, 3);
------------------------------------------------------------------------------------------------------------
INSERT INTO permission (
    id, name, description, created_by,
    created_date, last_updated, last_updated_by, society_id
) VALUES
(1, 'Community', 'Permission for Community', 1, NOW(), NOW(), 'admin', 1),
(2, 'Noticeboard', 'Permission for Noticeboard', 1, NOW(), NOW(), 'admin', 1),
(3, 'Complaint Box', 'Permission for Complaint Box', 1, NOW(), NOW(), 'admin', 1),
(4, 'Complaint Category', 'Permission for Complaint Category', 1, NOW(), NOW(), 'admin', 1),
(5, 'Repository', 'Permission for Repository', 1, NOW(), NOW(), 'admin', 1),
(6, 'Vendor', 'Permission for Vendor', 1, NOW(), NOW(), 'admin', 1),
(7, 'Accounting', 'Permission for Accounting', 1, NOW(), NOW(), 'admin', 1),
(8, 'Utility Billing', 'Permission for Utility Billing', 1, NOW(), NOW(), 'admin', 1),
(9, 'Action Item', 'Permission for Action Item', 1, NOW(), NOW(), 'admin', 1),
(10, 'Facility Booking', 'Permission for Facility Booking', 1, NOW(), NOW(), 'admin', 1),
(11, 'Vehicle', 'Permission for Vehicle', 1, NOW(), NOW(), 'admin', 1),
(12, 'Asset', 'Permission for Asset', 1, NOW(), NOW(), 'admin', 1),
(13, 'Notification', 'Permission for Notification', 1, NOW(), NOW(), 'admin', 1),
(14, 'Service Staff', 'Permission for Service Staff', 1, NOW(), NOW(), 'admin', 1),
(15, 'Data Entry Operator', 'Permission for Data Entry Operator', 1, NOW(), NOW(), 'admin', 1),
(16, 'Auditor', 'Permission for Auditor', 1, NOW(), NOW(), 'admin', 1),
(17, 'Security Front Office', 'Permission for Security Front Office', 1, NOW(), NOW(), 'admin', 1),
(18, 'Poll', 'Permission for Poll', 1, NOW(), NOW(), 'admin', 1),
(19, 'Accounting Assistant', 'Permission for Accounting Assistant', 1, NOW(), NOW(), 'admin', 1),
(20, 'Staff', 'Permission for Staff', 1, NOW(), NOW(), 'admin', 1),
(21, 'Request Approver', 'Permission for Request Approver', 1, NOW(), NOW(), 'admin', 1),
(22, 'User Management', 'Permission for User Management', 1, NOW(), NOW(), 'admin', 1),
(23, 'Request Box', 'Permission for Request Box', 1, NOW(), NOW(), 'admin', 1),
(24, 'Request Box Assistant', 'Permission for Request Box Assistant', 1, NOW(), NOW(), 'admin', 1),
(25, 'Forum', 'Permission for Forum', 1, NOW(), NOW(), 'admin', 1),
(26, 'Wiki Publisher', 'Permission for Wiki Publisher', 1, NOW(), NOW(), 'admin', 1),
(27, 'Wiki Author', 'Permission for Wiki Author', 1, NOW(), NOW(), 'admin', 1),
(28, 'User Management Assistant', 'Permission for User Management Assistant', 1, NOW(), NOW(), 'admin', 1),
(29, 'Water Tanker', 'Permission for Water Tanker', 1, NOW(), NOW(), 'admin', 1),
(30, 'Security Supervisor', 'Permission for Security Supervisor', 1, NOW(), NOW(), 'admin', 1),
(31, 'PR Requester', 'Permission for PR Requester', 1, NOW(), NOW(), 'admin', 1),
(32, 'Financial Reports Viewer', 'Permission for Financial Reports Viewer', 1, NOW(), NOW(), 'admin', 1),
(33, 'Complaint Field Staff', 'Permission for Complaint Field Staff', 1, NOW(), NOW(), 'admin', 1),
(34, 'Asset Operator', 'Permission for Asset Operator', 1, NOW(), NOW(), 'admin', 1),
(35, 'Store Coordinator', 'Permission for Store Coordinator', 1, NOW(), NOW(), 'admin', 1),
(36, 'Facility Booking Assistant', 'Permission for Facility Booking Assistant', 1, NOW(), NOW(), 'admin', 1),
(37, 'Security Guard', 'Permission for Security Guard', 1, NOW(), NOW(), 'admin', 1),
(38, 'CRM', 'Permission for CRM', 1, NOW(), NOW(), 'admin', 1),
(39, 'Incident Reporting', 'Permission for Incident Reporting', 1, NOW(), NOW(), 'admin', 1),
(40, 'Incident Reporting Field Staff', 'Permission for Incident Reporting Field Staff', 1, NOW(), NOW(), 'admin', 1),
(41, 'Incident Reporting Category Admin', 'Permission for Incident Reporting Category Admin', 1, NOW(), NOW(), 'admin', 1);------------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------------
INSERT INTO user_role_permissions (role_id, permission_id) VALUES
(1, 1), (1, 2), (1, 3), (1, 22), -- Admin
(2, 1), (2, 4), (2, 5), (2, 22), -- Manager
(3, 1), (3, 10), (3, 23),        -- Resident
(4, 17), (4, 30), (4, 37),       -- Security
(5, 16), (5, 32),                -- Auditor
(6, 20), (6, 14)
--,                -- Staff
--(7, 6), (7, 38),                 -- Vendor
--(8, 7), (8, 19),                 -- Accountant
--(9, 34), (9, 35),                -- Operator
--(10, 24), (10, 36);              -- Assistant
------------------------------------------------------------------------------------------------------------