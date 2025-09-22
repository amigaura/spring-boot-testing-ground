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

