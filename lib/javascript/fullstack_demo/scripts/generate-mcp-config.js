#!/usr/bin/env node

/**
 * Helper script to extract Clerk domain from environment variables
 * and generate MCP configuration files for different clients.
 */

import dotenv from 'dotenv';
import fs from 'fs';
import path from 'path';
import { fileURLToPath } from 'url';

const __filename = fileURLToPath(import.meta.url);
const __dirname = path.dirname(__filename);

// Load .env file
dotenv.config({ path: path.join(__dirname, '..', '.env') });

const publishableKey = process.env.NEXT_PUBLIC_CLERK_PUBLISHABLE_KEY;

if (!publishableKey) {
  console.error(
    '❌ Error: NEXT_PUBLIC_CLERK_PUBLISHABLE_KEY not found in .env file',
  );
  process.exit(1);
}

// Extract domain from publishable key
// Format: pk_test_<base64-encoded-domain>$
const parts = publishableKey.split('_');
if (parts.length < 3) {
  console.error('❌ Error: Invalid Clerk publishable key format');
  process.exit(1);
}

const encodedDomain = parts[2].replace(/\$+$/, '');
let clerkDomain;

try {
  clerkDomain = Buffer.from(encodedDomain, 'base64')
    .toString('utf-8')
    .replace(/\$+$/, '');
} catch (error) {
  console.error('❌ Error: Could not decode Clerk domain from publishable key');
  process.exit(1);
}

console.log('✅ Clerk Configuration Detected\n');
console.log(`Clerk Domain: ${clerkDomain}`);
console.log(`Authorization Server: https://${clerkDomain}\n`);

console.log('💡 Recommended: Use mcp-remote for easy setup:');
console.log('   npx -y mcp-remote http://localhost:3000/mcp\n');
console.log('⚠️  Note: Claude.ai (web) requires HTTPS. See HTTPS_SETUP.md.\n');

// Generate Goose config
const gooseConfig = {
  mcpServers: {
    fullstack_demo_todos: {
      url: 'http://localhost:3000/mcp',
      transport: 'streamable-http',
      description: 'Todo management for fullstack_demo app',
      oauth: {
        authorizationServer: `https://${clerkDomain}`,
        authorizationEndpoint: `https://${clerkDomain}/oauth/authorize`,
        tokenEndpoint: `https://${clerkDomain}/oauth/token`,
        registrationEndpoint: `https://${clerkDomain}/oauth/register`,
      },
    },
  },
};

console.log('📝 Goose Configuration:');
console.log(JSON.stringify(gooseConfig, null, 2));

// Write to file
const docsDir = path.join(__dirname, '..', 'docs');
fs.writeFileSync(
  path.join(docsDir, 'goose_config.generated.json'),
  JSON.stringify(gooseConfig, null, 2),
);

console.log('\n✅ Configuration file generated:');
console.log('  - docs/goose_config.generated.json');
console.log('\n📋 Next Steps:');
console.log(
  '  1. Ensure Dynamic Client Registration is enabled in Clerk Dashboard',
);
console.log('  2. Start the dev server: npm run dev');
console.log(
  '  3. Connect using mcp-remote: npx -y mcp-remote http://localhost:3000/mcp',
);
console.log('\n  Alternative: Manual Configuration');
console.log(
  '  - For Claude.ai: Settings → Connectors → Add custom connector (requires HTTPS)',
);
console.log(
  '  - For other agents: Copy docs/goose_config.generated.json to your client config',
);
