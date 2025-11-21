# Accessibility Testing Guide

This project includes comprehensive accessibility testing at multiple levels:

## Test Types

### 1. Unit Tests (Jest + jest-axe)
Tests individual components for accessibility violations.

**Run:**
```bash
npm test
```

**Watch mode:**
```bash
npm test -- --watch
```

**Coverage:**
```bash
npm test -- --coverage
```

### 2. E2E Tests (Playwright + axe-playwright)
Tests the full application in a real browser environment.

**Install Playwright:**
```bash
npx playwright install
```

**Run all E2E tests:**
```bash
npm run test:e2e
```

**Run in UI mode:**
```bash
npm run test:e2e:ui
```

**View test report:**
```bash
npx playwright show-report
```

### 3. Automated Scans (Pa11y CI)
Automated accessibility scanning of pages.

**Run:**
```bash
npm run test:a11y
```

### 4. Lighthouse CI
Performance and accessibility audits.

**Run:**
```bash
npm run lighthouse
```

## Test Coverage

The tests cover:

- **Automated Violations**: Using axe-core to detect WCAG violations
- **Keyboard Navigation**: Skip links, tab order, focus management
- **Screen Reader Support**: ARIA labels, semantic HTML, alt text
- **Color Contrast**: WCAG AA compliance for text and interactive elements
- **Form Accessibility**: Labels, error messages, required fields
- **Heading Hierarchy**: Proper h1-h6 structure
- **Focus Indicators**: Visible focus states for keyboard users
- **Dark Mode**: Accessibility in both light and dark themes
- **Responsive Design**: Mobile and desktop viewport testing

## Continuous Integration

Add to your CI/CD pipeline:

```yaml
# .github/workflows/accessibility.yml
name: Accessibility Tests

on: [push, pull_request]

jobs:
  test:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v3
      - uses: actions/setup-node@v3
        with:
          node-version: '20'
      
      - name: Install dependencies
        run: npm ci
      
      - name: Run unit tests
        run: npm test
      
      - name: Install Playwright
        run: npx playwright install --with-deps
      
      - name: Run E2E tests
        run: npm run test:e2e
      
      - name: Run Pa11y
        run: npm run test:a11y
```

## Manual Testing

Remember to also test manually:

1. **Keyboard Navigation**: Tab through the entire page
2. **Screen Reader**: Test with NVDA (Windows) or VoiceOver (Mac)
3. **Zoom**: Test at 200% zoom level
4. **Color Blindness**: Use browser extensions to simulate

## Best Practices

- Run tests before committing code
- Fix violations before they reach production
- Test with real assistive technologies
- Include people with disabilities in user testing
- Keep accessibility test suite up to date

## Resources

- [axe DevTools](https://www.deque.com/axe/devtools/)
- [Playwright Testing](https://playwright.dev/)
- [Pa11y Documentation](https://github.com/pa11y/pa11y)
- [WCAG Guidelines](https://www.w3.org/WAI/WCAG22/quickref/)
