-- This script drops all tables in the database to reset the schema.
-- Drop existing tables if they exist
DROP TABLE IF EXISTS app_user_roles;
DROP TABLE IF EXISTS app_user_society_mapping;
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
DROP TABLE IF EXISTS amenity_booking;
DROP TABLE IF EXISTS amenity;
DROP TABLE IF EXISTS society;
DROP TABLE IF EXISTS unit;
DROP TABLE IF EXISTS permission_user_role;