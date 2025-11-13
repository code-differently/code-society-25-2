#!/bin/bash

# Quick setup script for exposing MCP server over HTTPS

set -e

echo "🔧 MCP Server HTTPS Setup"
echo "=========================="
echo ""

# Check if ngrok is installed
if command -v ngrok &> /dev/null; then
    echo "✅ ngrok is installed"
else
    echo "❌ ngrok is not installed"
    echo ""
    echo "Install options:"
    echo "  macOS:  brew install ngrok"
    echo "  Other:  https://ngrok.com/download"
    echo ""
    exit 1
fi

# Check if authenticated
if ngrok config check &> /dev/null; then
    echo "✅ ngrok is configured"
else
    echo "⚠️  ngrok is not authenticated"
    echo ""
    echo "To authenticate:"
    echo "  1. Sign up at https://ngrok.com"
    echo "  2. Get your auth token from the dashboard"
    echo "  3. Run: ngrok config add-authtoken YOUR_AUTH_TOKEN"
    echo ""
    read -p "Press Enter after authenticating, or Ctrl+C to exit..."
fi

# Check if dev server is running
if curl -s http://localhost:3000 > /dev/null 2>&1; then
    echo "✅ Dev server is running on http://localhost:3000"
else
    echo "⚠️  Dev server is not running"
    echo ""
    echo "Start it with: npm run dev"
    echo ""
    read -p "Press Enter after starting the dev server, or Ctrl+C to exit..."
fi

echo ""
echo "🚀 Starting ngrok tunnel..."
echo ""
echo "This will:"
echo "  1. Create an HTTPS tunnel to localhost:3000"
echo "  2. Display the public HTTPS URL"
echo ""
echo "Next steps after tunnel starts:"
echo "  1. Copy the HTTPS URL (e.g., https://abc123.ngrok-free.app)"
echo "  2. Go to Clerk Dashboard → OAuth Applications"
echo "  3. Add redirect URL: https://YOUR-URL.ngrok-free.app/*"
echo "  4. Go to claude.ai → Settings → Connectors"
echo "  5. Add custom connector: https://YOUR-URL.ngrok-free.app/mcp"
echo ""
echo "Press Ctrl+C to stop the tunnel"
echo ""
sleep 2

# Start ngrok
ngrok http 3000
