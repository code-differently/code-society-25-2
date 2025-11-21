# Accessibility Testing Setup

## Overview

This demo project now includes comprehensive accessibility testing at multiple levels:

## 🧪 Test Types

### 1. **Unit Tests** (Jest + jest-axe)
- **Location**: `__tests__/accessibility.test.tsx`
- **What it tests**: Component-level accessibility violations
- **Run**: `npm test`
- **Coverage**: `npm test -- --coverage`

**Tests include:**
- Automated axe-core scanning
- Heading hierarchy validation
- Skip link functionality
- Form label associations
- ARIA attributes
- Button accessibility

### 2. **End-to-End Tests** (Playwright + axe-playwright)
- **Location**: `e2e/accessibility.spec.ts`
- **What it tests**: Full application accessibility in real browsers
- **Run**: `npm run test:e2e`
- **UI Mode**: `npm run test:e2e:ui`

**Tests include:**
- WCAG 2.1 AA compliance
- Keyboard navigation
- Focus management
- Dark mode accessibility
- Color contrast
- Table accessibility
- Link descriptiveness
- Form controls

### 3. **Automated Scans** (Pa11y CI)
- **Location**: `.pa11yci.json`
- **What it tests**: Page-level accessibility issues
- **Run**: `npm run test:a11y`

**Features:**
- Tests multiple color schemes
- Captures screenshots
- Tests both light and dark modes
- HTMLCS and axe runners

### 4. **Lighthouse CI**
- **Location**: `lighthouse-ci.config.js`
- **What it tests**: Overall site quality and accessibility
- **Run**: `npm run lighthouse`

**Metrics:**
- Accessibility score (min 90%)
- Best practices
- SEO
- Performance indicators

## 🚀 Quick Start

### Install Dependencies
```bash
npm install
```

### Install Playwright Browsers
```bash
npx playwright install
```

### Run All Tests
```bash
# Unit tests
npm test

# E2E tests
npm run test:e2e

# Pa11y scan
npm run test:a11y

# Lighthouse audit
npm run lighthouse
```

## 📋 Test Coverage

The test suite covers all WCAG 2.1 AA requirements:

### ✅ Perceivable
- [ ] Text alternatives (alt text, ARIA labels)
- [ ] Time-based media alternatives
- [ ] Adaptable content structure
- [ ] Color contrast (4.5:1 for text, 3:1 for UI)

### ✅ Operable
- [ ] Keyboard accessibility
- [ ] Skip navigation links
- [ ] Focus order and indicators
- [ ] Link purpose and naming

### ✅ Understandable
- [ ] Language of page specified
- [ ] Form labels and error messages
- [ ] Consistent navigation
- [ ] Input assistance

### ✅ Robust
- [ ] Valid HTML
- [ ] ARIA attributes
- [ ] Name, role, value for components

## 🔄 CI/CD Integration

A GitHub Actions workflow is configured at `.github/workflows/accessibility-tests.yml`:

**On every push/PR:**
1. Runs unit tests with coverage
2. Builds the application
3. Runs E2E Playwright tests
4. Performs Pa11y accessibility scan
5. Generates Lighthouse reports
6. Comments results on PRs

## 📊 Test Reports

### Unit Tests
- Terminal output with pass/fail
- Coverage report in `coverage/` directory
- Open `coverage/lcov-report/index.html` in browser

### E2E Tests
- HTML report: `npx playwright show-report`
- Screenshots on failure
- Video recordings (if enabled)

### Pa11y
- Terminal output with violations
- Screenshots in `pa11y-screenshots/`

### Lighthouse
- HTML reports in `.lighthouseci/`
- Accessibility score breakdown

## 🛠️ Development Workflow

### Before Committing
```bash
# Run quick test
npm test

# Full accessibility check
npm run test:e2e
```

### During Development
```bash
# Watch mode for unit tests
npm test -- --watch

# UI mode for E2E tests
npm run test:e2e:ui
```

### Before Deploying
```bash
# Full test suite
npm test && npm run test:e2e && npm run test:a11y
```

## 🐛 Debugging Failed Tests

### Unit Test Failures
1. Check the violation details in terminal
2. Run specific test: `npm test -- -t "test name"`
3. Add `console.log` for debugging
4. Check `__tests__/accessibility.test.tsx`

### E2E Test Failures
1. View screenshot in test report
2. Run in debug mode: `npm run test:e2e:debug`
3. Use UI mode: `npm run test:e2e:ui`
4. Check browser console logs in report

### Pa11y Failures
1. Review terminal output for specific violations
2. Check screenshots in `pa11y-screenshots/`
3. Test manually with browser DevTools
4. Verify with screen reader

## 📚 Additional Resources

### Testing Tools
- [jest-axe documentation](https://github.com/nickcolley/jest-axe)
- [Playwright Testing Guide](https://playwright.dev/docs/test-assertions)
- [axe-core Rules](https://github.com/dequelabs/axe-core/blob/develop/doc/rule-descriptions.md)
- [Pa11y Documentation](https://github.com/pa11y/pa11y)

### Accessibility Standards
- [WCAG 2.1 Quick Reference](https://www.w3.org/WAI/WCAG21/quickref/)
- [ARIA Authoring Practices](https://www.w3.org/WAI/ARIA/apg/)
- [WebAIM Resources](https://webaim.org/resources/)

## 🎯 Best Practices

1. **Run tests frequently** - Don't wait until the end
2. **Fix violations immediately** - Technical debt grows quickly
3. **Test with real users** - Automated tests can't catch everything
4. **Use multiple tools** - Each tool catches different issues
5. **Manual testing is essential** - Use keyboard and screen readers
6. **Keep learning** - Accessibility standards evolve

## 🤝 Contributing

When adding new features:
1. Write accessibility tests first (TDD)
2. Ensure all tests pass
3. Test manually with keyboard and screen reader
4. Update test documentation if needed

## 📝 Notes

- Tests run on every PR via GitHub Actions
- Minimum accessibility score: 90%
- Tests cover both light and dark modes
- Mobile viewports included in E2E tests
- All test output is preserved in CI artifacts
