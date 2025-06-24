

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

id|address                                       |city|contact_number|country|created_by|created_date|description|email|last_updated|last_updated_by|logo|logo_alt_text|logo_caption|logo_description|logo_url|name                     |pincode|state|status|website|
--+----------------------------------------------+----+--------------+-------+----------+------------+-----------+-----+------------+---------------+----+-------------+------------+----------------+--------+-------------------------+-------+-----+------+-------+
 1|Begur Main Road, Yelenahalli, Begur           |    |              |       |          |            |           |     |            |               |    |             |            |                |        |SNN Raj Serenity         |       |     |      |       |
 3|Hosur Road, Electronic City Phase 1, Bangalore|    |              |       |          |            |           |     |            |               |    |             |            |                |        |Prestige Sunrise Park    |       |     |      |       |
 4|Varthur Road, Whitefield, Bangalore           |    |              |       |          |            |           |     |            |               |    |             |            |                |        |Brigade Lakefront        |       |     |      |       |
 5|Sarjapur Road, Bangalore                      |    |              |       |          |            |           |     |            |               |    |             |            |                |        |Sobha Forest View        |       |     |      |       |
 6|Sarjapur Road, Bangalore                      |    |              |       |          |            |           |     |            |               |    |             |            |                |        |Godrej Woodland          |       |     |      |       |
 7|Hennur Road, Bangalore                        |    |              |       |          |            |           |     |            |               |    |             |            |                |        |Purva Palm Beach         |       |     |      |       |
 8|Varthur Road, Whitefield, Bangalore           |    |              |       |          |            |           |     |            |               |    |             |            |                |        |Prestige Lakeside Habitat|       |     |      |       |
 9|Kanakapura Road, Bangalore                    |    |              |       |          |            |           |     |            |               |    |             |            |                |        |Brigade Meadows          |       |     |      |       |
10|Panathur Road, Bangalore                      |    |              |       |          |            |           |     |            |               |    |             |            |                |        |Sobha Dream Acres        |       |     |      |       |
11|Kanakapura Road, Bangalore                    |    |              |       |          |            |           |     |            |               |    |             |            |                |        |Godrej Eternity          |       |     |      |       |
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

id|aadhar_number|alternate_email|alternate_email2|alternate_email3|alternate_email_verified|alternate_phone|alternate_phone2|alternate_phone3|alternate_phone_verified|blood_group|club_membership|club_membership_details|created_by|created_date           |date_of_birth|default_property_id|driving_license_number|email                 |email_verified|emergency_contact_address|emergency_contact_address_verified|emergency_contact_email|emergency_contact_email_verified|emergency_contact_name|emergency_contact_phone|emergency_contact_phone_verified|emergency_contact_relationship|gender|last_updated           |last_updated_by|marital_status|name          |occupation|pan_number|passport_number|pets|pets_details|phone_number|phone_verified|profile_picture|profile_picture_url|residing_from_date|username|voter_id_number|address                                                   |
--+-------------+---------------+----------------+----------------+------------------------+---------------+----------------+----------------+------------------------+-----------+---------------+-----------------------+----------+-----------------------+-------------+-------------------+----------------------+----------------------+--------------+-------------------------+----------------------------------+-----------------------+--------------------------------+----------------------+-----------------------+--------------------------------+------------------------------+------+-----------------------+---------------+--------------+--------------+----------+----------+---------------+----+------------+------------+--------------+---------------+-------------------+------------------+--------+---------------+----------------------------------------------------------+
 1|             |               |                |                |                        |               |                |                |                        |           |               |                       |admin     |2025-06-24 17:56:23.498|             |                   |                      |amitgaurav25@gmail.com|              |                         |                                  |                       |                                |                      |                       |                                |                              |      |2025-06-24 17:56:23.498|admin          |              |Amit Gaurav   |          |          |               |    |            |9731855220  |              |               |                   |                  |        |               |C2-902, SNN Raj Serenity, Begur, Bangalore                |
 2|             |               |                |                |                        |               |                |                |                        |           |               |                       |admin     |2025-06-24 17:56:23.498|             |                   |                      |john@gmail.com        |              |                         |                                  |                       |                                |                      |                       |                                |                              |      |2025-06-24 17:56:23.498|admin          |              |Jane Smith    |          |          |               |    |            |9876543210  |              |               |                   |                  |        |               |C3-101, Prestige Sunrise Park, Hosur Road, Bangalore      |
 3|             |               |                |                |                        |               |                |                |                        |           |               |                       |admin     |2025-06-24 17:56:23.498|             |                   |                      |alice@gmail.com       |              |                         |                                  |                       |                                |                      |                       |                                |                              |      |2025-06-24 17:56:23.498|admin          |              |Alice Wonder  |          |          |               |    |            |9123456789  |              |               |                   |                  |        |               |D4-202, Brigade Lakefront, Varthur Road, Bangalore        |
 4|             |               |                |                |                        |               |                |                |                        |           |               |                       |admin     |2025-06-24 17:56:23.498|             |                   |                      |bob@gmail.com         |              |                         |                                  |                       |                                |                      |                       |                                |                              |      |2025-06-24 17:56:23.498|admin          |              |Bob Builder   |          |          |               |    |            |9988776655  |              |               |                   |                  |        |               |E5-303, Sobha Forest View, Sarjapur Road, Bangalore       |
 5|             |               |                |                |                        |               |                |                |                        |           |               |                       |admin     |2025-06-24 17:56:23.498|             |                   |                      |charlie@yahoo.com     |              |                         |                                  |                       |                                |                      |                       |                                |                              |      |2025-06-24 17:56:23.498|admin          |              |Charlie Brown |          |          |               |    |            |8765432109  |              |               |                   |                  |        |               |F6-404, Godrej Woodland, Sarjapur Road, Bangalore         |
 6|             |               |                |                |                        |               |                |                |                        |           |               |                       |admin     |2025-06-24 17:56:23.498|             |                   |                      |diana@msn.in          |              |                         |                                  |                       |                                |                      |                       |                                |                              |      |2025-06-24 17:56:23.498|admin          |              |Diana Prince  |          |          |               |    |            |7654321098  |              |               |                   |                  |        |               |G7-505, Purva Palm Beach, Hennur Road, Bangalore          |
 7|             |               |                |                |                        |               |                |                |                        |           |               |                       |admin     |2025-06-24 17:56:23.498|             |                   |                      |ethan@apple.com       |              |                         |                                  |                       |                                |                      |                       |                                |                              |      |2025-06-24 17:56:23.498|admin          |              |Ethan Hunt    |          |          |               |    |            |6543210987  |              |               |                   |                  |        |               |H8-606, Prestige Lakeside Habitat, Varthur Road, Bangalore|
 8|             |               |                |                |                        |               |                |                |                        |           |               |                       |admin     |2025-06-24 17:56:23.498|             |                   |                      |fiona@microsoft.com   |              |                         |                                  |                       |                                |                      |                       |                                |                              |      |2025-06-24 17:56:23.498|admin          |              |Fiona Grey    |          |          |               |    |            |5432109876  |              |               |                   |                  |        |               |I9-707, Brigade Meadows, Kanakapura Road, Bangalore       |
 9|             |               |                |                |                        |               |                |                |                        |           |               |                       |admin     |2025-06-24 17:56:23.498|             |                   |                      |george@google.com     |              |                         |                                  |                       |                                |                      |                       |                                |                              |      |2025-06-24 17:56:23.498|admin          |              |George Clark  |          |          |               |    |            |4321098765  |              |               |                   |                  |        |               |J10-808, Sobha Dream Acres, Panathur Road, Bangalore      |
10|             |               |                |                |                        |               |                |                |                        |           |               |                       |admin     |2025-06-24 17:56:23.498|             |                   |                      |hanah@netflix.com     |              |                         |                                  |                       |                                |                      |                       |                                |                              |      |2025-06-24 17:56:23.498|admin          |              |Hannah Montana|          |          |               |    |            |3210987654  |              |               |                   |                  |        |               |K11-909, Godrej Eternity, Kanakapura Road, Bangalore      |
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

id|society_id|username      |user_detail_id|
--+----------+--------------+--------------+
 5|         1|amit          |             1|
 6|         1|jane_smith    |             2|
 7|         2|alice_wonder  |             3|
 8|         2|bob_builder   |             4|
 9|         3|charlie_brown |             5|
10|         4|diana_prince  |             6|
11|         5|ethan_hunt    |             7|
12|         6|fiona_grey    |             8|
13|         6|george_clark  |             9|
14|         7|hannah_montana|            10|
------------------------------------------------------------------------------------------------------------

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

app_user_id|failed_login_attempts|is_temporary|lockout_until|password_hash|society_id|
-----------+---------------------+------------+-------------+-------------+----------+
          5|                    0|false       |             |password     |         1|
          6|                    0|false       |             |password     |         2|
          7|                    0|false       |             |password     |         3|
          8|                    0|false       |             |password     |         4|
          9|                    0|false       |             |password     |         5|
         10|                    0|false       |             |password     |         6|
         11|                    0|false       |             |password     |         7|
         12|                    0|false       |             |password     |         8|
         13|                    0|false       |             |password     |         9|
         14|                    0|false       |             |password     |        10|
------------------------------------------------------------------------------------------------------------

INSERT INTO user_role (created_by, created_date, last_updated, last_updated_by, name, society_id)
values('admin', NOW(), NOW(), 'admin', 'admin', 1),
('admin', NOW(), NOW(), 'admin', 'user', 1),
('admin', NOW(), NOW(), 'admin', 'guest', 1);

INSERT INTO user_role (created_by, created_date, last_updated, last_updated_by, name, society_id)
values('admin', NOW(), NOW(), 'admin', 'admin', 1),
('admin', NOW(), NOW(), 'admin', 'user', 2),
('admin', NOW(), NOW(), 'admin', 'guest', 2);

role_id|created_by|created_date           |description|last_updated           |last_updated_by|name |society_id|
-------+----------+-----------------------+-----------+-----------------------+---------------+-----+----------+
      1|admin     |2025-06-24 22:54:36.021|           |2025-06-24 22:54:36.021|admin          |admin|         1|
      2|admin     |2025-06-24 22:54:36.021|           |2025-06-24 22:54:36.021|admin          |user |         1|
      3|admin     |2025-06-24 22:54:36.021|           |2025-06-24 22:54:36.021|admin          |guest|         1|
      4|admin     |2025-06-24 22:55:12.288|           |2025-06-24 22:55:12.288|admin          |admin|         2|
      5|admin     |2025-06-24 22:55:12.288|           |2025-06-24 22:55:12.288|admin          |user |         2|
      6|admin     |2025-06-24 22:55:12.288|           |2025-06-24 22:55:12.288|admin          |guest|         2|
 ------------------------------------------------------------------------------------------------------------

INSERT INTO app_user_society_mapping (active, joined_at, app_user_id, society_id) values
(true, NOW(), 5, 1),
(true, NOW(), 6, 1),
(true, NOW(), 7, 3),
(true, NOW(), 8, 3)

id|active|joined_at              |local_user_id|app_user_id|society_id|
--+------+-----------------------+-------------+-----------+----------+
 5|true  |2025-06-24 23:09:25.605|             |          5|         1|
 6|true  |2025-06-24 23:09:25.605|             |          6|         1|
 7|true  |2025-06-24 23:09:25.605|             |          7|         3|
 8|true  |2025-06-24 23:09:25.605|             |          8|         3|
--+------+-----------------------+-------------+-----------+----------+

INSERT INTO app_user_roles (app_user_id, role_id) values
(5, 1),
(6, 2),
(7, 1),
(7, 2),
(8, 3);

app_user_id|role_id|
-----------+-------+
          5|      1|
          6|      2|
          7|      1|
          7|      2|
          8|      3|
------------------------------------------------------------------------------------------------------------
INSERT INTO permission (
    permission_id, permission_name, permission_description, created_by,
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
(41, 'Incident Reporting Category Admin', 'Permission for Incident Reporting Category Admin', 1, NOW(), NOW(), 'admin', 1);

permission_id|created_by|created_date           |last_updated           |last_updated_by|permission_description                          |permission_name                  |society_id|
-------------+----------+-----------------------+-----------------------+---------------+------------------------------------------------+---------------------------------+----------+
            1|1         |2025-06-24 23:27:25.787|2025-06-24 23:27:25.787|admin          |Permission for Community                        |Community                        |         1|
            2|1         |2025-06-24 23:27:25.787|2025-06-24 23:27:25.787|admin          |Permission for Noticeboard                      |Noticeboard                      |         1|
            3|1         |2025-06-24 23:27:25.787|2025-06-24 23:27:25.787|admin          |Permission for Complaint Box                    |Complaint Box                    |         1|
            4|1         |2025-06-24 23:27:25.787|2025-06-24 23:27:25.787|admin          |Permission for Complaint Category               |Complaint Category               |         1|
            5|1         |2025-06-24 23:27:25.787|2025-06-24 23:27:25.787|admin          |Permission for Repository                       |Repository                       |         1|
            6|1         |2025-06-24 23:27:25.787|2025-06-24 23:27:25.787|admin          |Permission for Vendor                           |Vendor                           |         1|
            7|1         |2025-06-24 23:27:25.787|2025-06-24 23:27:25.787|admin          |Permission for Accounting                       |Accounting                       |         1|
            8|1         |2025-06-24 23:27:25.787|2025-06-24 23:27:25.787|admin          |Permission for Utility Billing                  |Utility Billing                  |         1|
            9|1         |2025-06-24 23:27:25.787|2025-06-24 23:27:25.787|admin          |Permission for Action Item                      |Action Item                      |         1|
           10|1         |2025-06-24 23:27:25.787|2025-06-24 23:27:25.787|admin          |Permission for Facility Booking                 |Facility Booking                 |         1|
           11|1         |2025-06-24 23:27:25.787|2025-06-24 23:27:25.787|admin          |Permission for Vehicle                          |Vehicle                          |         1|
           12|1         |2025-06-24 23:27:25.787|2025-06-24 23:27:25.787|admin          |Permission for Asset                            |Asset                            |         1|
           13|1         |2025-06-24 23:27:25.787|2025-06-24 23:27:25.787|admin          |Permission for Notification                     |Notification                     |         1|
           14|1         |2025-06-24 23:27:25.787|2025-06-24 23:27:25.787|admin          |Permission for Service Staff                    |Service Staff                    |         1|
           15|1         |2025-06-24 23:27:25.787|2025-06-24 23:27:25.787|admin          |Permission for Data Entry Operator              |Data Entry Operator              |         1|
           16|1         |2025-06-24 23:27:25.787|2025-06-24 23:27:25.787|admin          |Permission for Auditor                          |Auditor                          |         1|
           17|1         |2025-06-24 23:27:25.787|2025-06-24 23:27:25.787|admin          |Permission for Security Front Office            |Security Front Office            |         1|
           18|1         |2025-06-24 23:27:25.787|2025-06-24 23:27:25.787|admin          |Permission for Poll                             |Poll                             |         1|
           19|1         |2025-06-24 23:27:25.787|2025-06-24 23:27:25.787|admin          |Permission for Accounting Assistant             |Accounting Assistant             |         1|
           20|1         |2025-06-24 23:27:25.787|2025-06-24 23:27:25.787|admin          |Permission for Staff                            |Staff                            |         1|
           21|1         |2025-06-24 23:27:25.787|2025-06-24 23:27:25.787|admin          |Permission for Request Approver                 |Request Approver                 |         1|
           22|1         |2025-06-24 23:27:25.787|2025-06-24 23:27:25.787|admin          |Permission for User Management                  |User Management                  |         1|
           23|1         |2025-06-24 23:27:25.787|2025-06-24 23:27:25.787|admin          |Permission for Request Box                      |Request Box                      |         1|
           24|1         |2025-06-24 23:27:25.787|2025-06-24 23:27:25.787|admin          |Permission for Request Box Assistant            |Request Box Assistant            |         1|
           25|1         |2025-06-24 23:27:25.787|2025-06-24 23:27:25.787|admin          |Permission for Forum                            |Forum                            |         1|
           26|1         |2025-06-24 23:27:25.787|2025-06-24 23:27:25.787|admin          |Permission for Wiki Publisher                   |Wiki Publisher                   |         1|
           27|1         |2025-06-24 23:27:25.787|2025-06-24 23:27:25.787|admin          |Permission for Wiki Author                      |Wiki Author                      |         1|
           28|1         |2025-06-24 23:27:25.787|2025-06-24 23:27:25.787|admin          |Permission for User Management Assistant        |User Management Assistant        |         1|
           29|1         |2025-06-24 23:27:25.787|2025-06-24 23:27:25.787|admin          |Permission for Water Tanker                     |Water Tanker                     |         1|
           30|1         |2025-06-24 23:27:25.787|2025-06-24 23:27:25.787|admin          |Permission for Security Supervisor              |Security Supervisor              |         1|
           31|1         |2025-06-24 23:27:25.787|2025-06-24 23:27:25.787|admin          |Permission for PR Requester                     |PR Requester                     |         1|
           32|1         |2025-06-24 23:27:25.787|2025-06-24 23:27:25.787|admin          |Permission for Financial Reports Viewer         |Financial Reports Viewer         |         1|
           33|1         |2025-06-24 23:27:25.787|2025-06-24 23:27:25.787|admin          |Permission for Complaint Field Staff            |Complaint Field Staff            |         1|
           34|1         |2025-06-24 23:27:25.787|2025-06-24 23:27:25.787|admin          |Permission for Asset Operator                   |Asset Operator                   |         1|
           35|1         |2025-06-24 23:27:25.787|2025-06-24 23:27:25.787|admin          |Permission for Store Coordinator                |Store Coordinator                |         1|
           36|1         |2025-06-24 23:27:25.787|2025-06-24 23:27:25.787|admin          |Permission for Facility Booking Assistant       |Facility Booking Assistant       |         1|
           37|1         |2025-06-24 23:27:25.787|2025-06-24 23:27:25.787|admin          |Permission for Security Guard                   |Security Guard                   |         1|
           38|1         |2025-06-24 23:27:25.787|2025-06-24 23:27:25.787|admin          |Permission for CRM                              |CRM                              |         1|
           39|1         |2025-06-24 23:27:25.787|2025-06-24 23:27:25.787|admin          |Permission for Incident Reporting               |Incident Reporting               |         1|
           40|1         |2025-06-24 23:27:25.787|2025-06-24 23:27:25.787|admin          |Permission for Incident Reporting Field Staff   |Incident Reporting Field Staff   |         1|
           41|1         |2025-06-24 23:27:25.787|2025-06-24 23:27:25.787|admin          |Permission for Incident Reporting Category Admin|Incident Reporting Category Admin|         1|
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

role_id|permission_id|
-------+-------------+
      1|            1|
      1|            2|
      1|            3|
      1|           22|
      2|            1|
      2|            4|
      2|            5|
      2|           22|
      3|            1|
      3|           10|
      3|           23|
      4|           17|
      4|           30|
      4|           37|
      5|           16|
      5|           32|
      6|           20|
      6|           14|
------------------------------------------------------------------------------------------------------------


-- Drop existing tables if they exist
DROP TABLE IF EXISTS app_user_society_mapping;
DROP TABLE IF EXISTS app_user_roles;
DROP TABLE IF EXISTS user_credential;
DROP TABLE IF EXISTS app_user_unit_mapping;
DROP TABLE IF EXISTS refresh_token;
DROP TABLE IF EXISTS app_user;

DROP TABLE IF EXISTS user_role_permissions;
DROP TABLE IF EXISTS user_role;

DROP TABLE IF EXISTS Permission;
DROP TABLE IF EXISTS user_detail;

DROP TABLE IF EXISTS complaint_comments;
DROP TABLE IF EXISTS complaint_attachments;
DROP TABLE IF EXISTS complaint;

------------------------------------------------------------------------------------------------------------

select * from app_user
select * from user_detail
select * from user_credential
select * from user_role
select * from app_user_roles
select * from permission
select * from user_role_permissions
select * from app_user_society_mapping
select * from refresh_token

select * from society
select * from amenity

select * from complaint



SELECT
    column_name,
    data_type,
    character_maximum_length,
    is_nullable,
    column_default
FROM
    information_schema.columns
WHERE
    table_name = 'user_detail'
ORDER BY
    ordinal_position;
------------------------------------------------------------------------------------------------------------

