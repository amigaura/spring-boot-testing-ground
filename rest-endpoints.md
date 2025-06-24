# Authentication and Authorization Endpoints
```shell
curl --location 'http://localhost:8080/auth/login' \
--header 'Content-Type: application/json' \
--data '{
  "username": "amit",
  "password": "password"
}'
```

# Adding Society Endpoints
```shell
curl --location 'http://localhost:8080/api/societies' \
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhbWl0IiwiaWF0IjoxNzUwNzkwOTE0LCJleHAiOjE3NTA3OTE4MTR9.UffRyB7LG1pMFHwFF4_CEf-hxYEN9EKfXpxylOWOyHY' \
--data '{"name":"GM Infinite","address":"Electronics City, Bangalore"}'
```

# Updating Society Endpoints
```shell
curl --location --request PUT 'http://localhost:8080/api/societies/10' \
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhbWl0IiwiaWF0IjoxNzUwNzkxMjMzLCJleHAiOjE3NTA3OTIxMzN9.3eqi6GTXgIxIe56ImDFHLb6PGjr8fQZjIAgpN96ag7w' \
--data '{"name":"Prestige Song of the South","description":"Prestige Song of the South, Begur, Bangalore"}'
```

# Deleting Society Endpoints
```shell
curl --location --request DELETE 'http://localhost:8080/api/societies/10' \
```

# Fetching Society Endpoints
```shell
curl --location 'http://localhost:8080/api/societies/11' \
```
