-- Steps to create the database schema and insert initial data

--Step# 1: Create the society table and insert initial data
INSERT INTO society ("name", "address") VALUES ('SNN Raj Serenity', 'Begur Main Road, Yelenahalli, Begur');

-- Step# 2: Introduce a default permission
INSERT INTO permission (
    id, name, description, created_by,
    created_date, last_updated, last_updated_by, society_id
) VALUES
(1, 'Community', 'Permission for Community', 1, NOW(), NOW(), 'admin', 1);

-- Step# 3: Create the user_role table and insert initial roles
INSERT INTO user_role (created_by, created_date, last_updated, last_updated_by, name, society_id)
values('admin', NOW(), NOW(), 'admin', 'admin', 1),
('admin', NOW(), NOW(), 'admin', 'user', 1),
('admin', NOW(), NOW(), 'admin', 'guest', 1);