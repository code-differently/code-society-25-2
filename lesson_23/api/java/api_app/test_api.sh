#!/bin/bash

# API Testing Script for Library Management System
BASE_URL="http://localhost:3001"

echo "Testing Library API Endpoints"
echo "================================"

# Test 1: GET all items
echo "Test 1: GET /items (Get all media items)"
echo "Request: GET $BASE_URL/items"
curl -s -w "\nHTTP Status: %{http_code}\n" "$BASE_URL/items" | head -5
echo -e "\n"

# Test 2: GET specific item
echo "Test 2: GET /items/{id} (Get specific item)"
ITEM_ID="31616162-3831-3832-2d34-3334352d3465"
echo "Request: GET $BASE_URL/items/$ITEM_ID"
curl -s -w "\nHTTP Status: %{http_code}\n" "$BASE_URL/items/$ITEM_ID"
echo -e "\n"

# Test 3: GET non-existent item (should return 404)
echo "Test 3: GET /items/{id} (Non-existent item - should return 404)"
echo "Request: GET $BASE_URL/items/nonexistent-id"
curl -s -w "\nHTTP Status: %{http_code}\n" "$BASE_URL/items/nonexistent-id"
echo -e "\n"

# Test 4: POST new item
echo "Test 4: POST /items (Create new book)"
NEW_BOOK='{
  "item": {
    "id": "12345678-1234-1234-1234-123456789abc",
    "type": "BOOK",
    "title": "Test Book for Debugging",
    "isbn": "978-1234567890",
    "authors": ["Test Author"],
    "pages": 200
  }
}'
echo "Request: POST $BASE_URL/items"
echo "Body: $NEW_BOOK"
curl -s -X POST -H "Content-Type: application/json" -d "$NEW_BOOK" -w "\nHTTP Status: %{http_code}\n" "$BASE_URL/items"
echo -e "\n"

# Test 5: POST invalid item (should return 400)
echo "Test 5: POST /items (Invalid item - should return 400)"
INVALID_ITEM='{"item": {}}'
echo "Request: POST $BASE_URL/items"
echo "Body: $INVALID_ITEM"
curl -s -X POST -H "Content-Type: application/json" -d "$INVALID_ITEM" -w "\nHTTP Status: %{http_code}\n" "$BASE_URL/items"
echo -e "\n"

# Test 6: DELETE item
echo "Test 6: DELETE /items/{id} (Delete an item)"
DELETE_ID="32623932-6566-3364-2d62-3232342d3435"
echo "Request: DELETE $BASE_URL/items/$DELETE_ID"
curl -s -X DELETE -w "\nHTTP Status: %{http_code}\n" "$BASE_URL/items/$DELETE_ID"
echo -e "\n"

# Test 7: DELETE non-existent item (should return 404)
echo "Test 7: DELETE /items/{id} (Non-existent item - should return 404)"
echo "Request: DELETE $BASE_URL/items/nonexistent-id"
curl -s -X DELETE -w "\nHTTP Status: %{http_code}\n" "$BASE_URL/items/nonexistent-id"
echo -e "\n"

echo "API Testing Complete!"
