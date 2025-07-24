#!/bin/bash

# Repository cleanup script
# Removes temporary files, build artifacts, and cached dependencies

# Note: Removed 'set -e' to prevent early termination when find commands don't find matches

echo "🧹 Starting repository cleanup..."

# Colors for output
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
NC='\033[0m' # No Color

# Function to print colored output
print_status() {
    echo -e "${BLUE}[INFO]${NC} $1"
}

print_success() {
    echo -e "${GREEN}[SUCCESS]${NC} $1"
}

print_warning() {
    echo -e "${YELLOW}[WARNING]${NC} $1"
}

print_error() {
    echo -e "${RED}[ERROR]${NC} $1"
}

# Get the script directory and repository root
SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
REPO_ROOT="$(cd "$SCRIPT_DIR/.." && pwd)"

cd "$REPO_ROOT"

print_status "Cleaning repository at: $REPO_ROOT"

# Counter for cleaned items
cleaned_count=0

# Function to safely remove directories/files
safe_remove() {
    local path="$1"
    local description="$2"
    
    if [ -e "$path" ]; then
        print_status "Removing $description: $path"
        rm -rf "$path"
        ((cleaned_count++))
    fi
}

# Clean Node.js/TypeScript projects
print_status "Cleaning Node.js/TypeScript artifacts..."

# Find and remove node_modules directories
find . -name "node_modules" -type d -print0 2>/dev/null | while IFS= read -r -d '' dir; do
    safe_remove "$dir" "node_modules directory"
done

# Find and remove common JavaScript/TypeScript build directories
for build_dir in "dist" "build" "lib" "out" ".next" ".nuxt" "coverage" ".nyc_output"; do
    find . -name "$build_dir" -type d -print0 2>/dev/null | while IFS= read -r -d '' dir; do
        # Skip if it's our own lib directory at the root
        # Skip slides/dist directory
        # Skip quiz-app source lib directory
        if [[ "$dir" != "./lib" ]] && [[ "$dir" != "./slides/dist" ]] && [[ "$dir" != "./lib/javascript/quiz-app/src/lib" ]]; then
            safe_remove "$dir" "$build_dir directory"
        fi
    done
done

# Clean Java/Gradle projects
print_status "Cleaning Java/Gradle artifacts..."

# Find and remove Gradle build directories
find . -name "build" -type d -print0 2>/dev/null | while IFS= read -r -d '' dir; do
    # Only remove build directories that are in Gradle projects
    if [ -f "$(dirname "$dir")/build.gradle" ] || [ -f "$(dirname "$dir")/build.gradle.kts" ]; then
        safe_remove "$dir" "Gradle build directory"
    fi
done

# Remove Gradle cache and wrapper cache
find . -name ".gradle" -type d -print0 2>/dev/null | while IFS= read -r -d '' dir; do
    safe_remove "$dir" "Gradle cache"
done

# Clean Maven projects (if any)
print_status "Cleaning Maven artifacts..."

# Find and remove Maven target directories
find . -name "target" -type d -print0 2>/dev/null | while IFS= read -r -d '' dir; do
    safe_remove "$dir" "Maven target directory"
done

# Clean IDE files
print_status "Cleaning IDE files..."

# Remove common IDE directories and files
for ide_item in ".vscode/settings.json" ".idea" "*.iml" ".project" ".classpath" ".settings"; do
    find . -name "$ide_item" -print0 2>/dev/null | while IFS= read -r -d '' item; do
        safe_remove "$item" "IDE file/directory"
    done
done

# Clean OS-specific files
print_status "Cleaning OS-specific files..."

# Remove macOS files
find . -name ".DS_Store" -type f -print0 2>/dev/null | while IFS= read -r -d '' file; do
    safe_remove "$file" "macOS DS_Store file"
done

# Remove Windows files
find . -name "Thumbs.db" -type f -print0 2>/dev/null | while IFS= read -r -d '' file; do
    safe_remove "$file" "Windows Thumbs.db file"
done

# Clean temporary and log files
print_status "Cleaning temporary and log files..."

# Remove common temporary files
for temp_pattern in "*.tmp" "*.temp" "*.log" "*.pid" "*.swp" "*.swo" "*~"; do
    find . -name "$temp_pattern" -type f -print0 2>/dev/null | while IFS= read -r -d '' file; do
        safe_remove "$file" "temporary file"
    done
done

# Clean TypeScript compilation cache
find . -name "tsconfig.tsbuildinfo" -type f -print0 2>/dev/null | while IFS= read -r -d '' file; do
    safe_remove "$file" "TypeScript build info"
done

# Clean Jest cache
find . -name ".jest" -type d -print0 2>/dev/null | while IFS= read -r -d '' dir; do
    safe_remove "$dir" "Jest cache"
done

# Clean ESLint cache
find . -name ".eslintcache" -type f -print0 2>/dev/null | while IFS= read -r -d '' file; do
    safe_remove "$file" "ESLint cache"
done

# Clean environment files (be careful with these)
print_warning "Checking for environment files (not removing, just listing)..."
find . -name ".env*" -type f 2>/dev/null | while read -r env_file; do
    print_warning "Found environment file (not removed): $env_file"
done

# Summary
echo ""
print_success "Repository cleanup completed!"
print_status "Total items cleaned: $cleaned_count"

# Optional: Show disk space freed (macOS/Linux)
if command -v du >/dev/null 2>&1; then
    current_size=$(du -sh . 2>/dev/null | cut -f1 || echo "Unknown")
    print_status "Current repository size: $current_size"
fi

echo ""
print_status "Cleanup script finished. Your repository is now clean! 🎉"
