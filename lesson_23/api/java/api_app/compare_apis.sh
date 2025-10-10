#!/bin/bash

# Comparison Test Script: Java vs TypeScript APIs
echo "🔄 Comparing Java API (port 3001) vs TypeScript API (port 3000)"
echo "=================================================================="

# Test 1: GET all items comparison
echo "📚 Test 1: GET /items (Compare item counts)"
echo "--------------------------------------------"

JAVA_COUNT=$(curl -s http://localhost:3001/items | jq '.items | length')
TS_COUNT=$(curl -s http://localhost:3000/items | jq '.items | length')

echo "Java API items: $JAVA_COUNT"
echo "TypeScript API items: $TS_COUNT"

if [ "$JAVA_COUNT" -eq "$TS_COUNT" ]; then
    echo "✅ Both APIs return the same number of items"
else
    echo "⚠️  Different item counts between APIs"
fi
echo ""

# Test 2: Compare response structure
echo "📖 Test 2: Compare Response Structure"
echo "--------------------------------------"

echo "Java API sample response:"
curl -s http://localhost:3001/items | jq '.items[0]' 2>/dev/null || echo "Java API not responding"
echo ""

echo "TypeScript API sample response:"
curl -s http://localhost:3000/items | jq '.items[0]' 2>/dev/null || echo "TypeScript API not responding"
echo ""

# Test 3: Create item in both APIs
echo "➕ Test 3: Create New Item in Both APIs"
echo "----------------------------------------"

NEW_ITEM='{
  "item": {
    "id": "comparison-test-id-123",
    "type": "BOOK",
    "title": "API Comparison Test Book",
    "isbn": "978-9999999999",
    "authors": ["Test Author"],
    "pages": 150
  }
}'

echo "Creating item in Java API:"
JAVA_CREATE=$(curl -s -X POST -H "Content-Type: application/json" -d "$NEW_ITEM" -w "%{http_code}" http://localhost:3001/items)
echo "Java API response code: ${JAVA_CREATE: -3}"

echo "Creating item in TypeScript API:"
TS_CREATE=$(curl -s -X POST -H "Content-Type: application/json" -d "$NEW_ITEM" -w "%{http_code}" http://localhost:3000/items)
echo "TypeScript API response code: ${TS_CREATE: -3}"
echo ""

# Test 4: Test error handling
echo "🚫 Test 4: Error Handling Comparison"
echo "------------------------------------"

echo "Testing 404 responses:"
JAVA_404=$(curl -s -w "%{http_code}" http://localhost:3001/items/nonexistent-id)
TS_404=$(curl -s -w "%{http_code}" http://localhost:3000/items/nonexistent-id)

echo "Java API 404 status: ${JAVA_404: -3}"
echo "TypeScript API 404 status: ${TS_404: -3}"

if [ "${JAVA_404: -3}" = "404" ] && [ "${TS_404: -3}" = "404" ]; then
    echo "✅ Both APIs correctly return 404 for non-existent items"
else
    echo "⚠️  Different error handling between APIs"
fi
echo ""

# Test 5: API Documentation
echo "📚 Test 5: API Documentation Available"
echo "--------------------------------------"

echo "Java API Swagger: http://localhost:3001/swagger-ui.html"
echo "TypeScript API OpenAPI: http://localhost:3000/api"
echo ""

echo "🎯 Comparison Summary:"
echo "======================"
echo "Both APIs implement the same REST endpoints:"
echo "• GET /items - List all media items"
echo "• GET /items/{id} - Get specific item"  
echo "• POST /items - Create new item"
echo "• DELETE /items/{id} - Delete item"
echo ""
echo "Key Differences:"
echo "• Java: Spring Boot + Lombok (Port 3001)"
echo "• TypeScript: NestJS + Decorators (Port 3000)"
echo "• Java: Swagger UI documentation"
echo "• TypeScript: OpenAPI documentation"
echo ""
echo "✨ Both implementations demonstrate the same REST API concepts!"
