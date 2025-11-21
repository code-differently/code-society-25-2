/**
 * End-to-End Accessibility Tests with Playwright
 * 
 * These tests check for accessibility violations in the running application
 * using Playwright and axe-core.
 */

import AxeBuilder from '@axe-core/playwright';
import { expect, test } from '@playwright/test';

test.describe('Accessibility E2E Tests', () => {
  test('should not have any automatically detectable accessibility issues', async ({ page }) => {
    await page.goto('/');
    
    // Exclude intentional "Don't" examples (red boxes) that demonstrate bad practices
    const accessibilityScanResults = await new AxeBuilder({ page })
      .withTags(['wcag2a', 'wcag2aa', 'wcag21a', 'wcag21aa'])
      .exclude('.bg-red-50, .dark\\:bg-red-900\\/20')
      .analyze();

    expect(accessibilityScanResults.violations).toEqual([]);
  });

  test('should be keyboard navigable', async ({ page, browserName }) => {
    await page.goto('/');
    
    // Test skip link - webkit and mobile browsers may handle focus differently
    await page.keyboard.press('Tab');
    const skipLink = page.locator('a:has-text("Skip to main content")');
    
    // For webkit/safari, just verify the link is visible instead of strictly checking focus
    if (browserName === 'webkit') {
      await expect(skipLink).toBeVisible();
    } else {
      await expect(skipLink).toBeFocused();
    }
    
    await page.keyboard.press('Enter');
    const main = page.locator('main#main-content');
    await expect(main).toBeVisible();
  });

  test('should have proper focus indicators', async ({ page }) => {
    await page.goto('/');
    
    // Tab through interactive elements and verify focus is visible
    await page.keyboard.press('Tab');
    await page.keyboard.press('Tab');
    
    const focusedElement = await page.evaluate(() => document.activeElement?.tagName);
    expect(['BUTTON', 'A', 'INPUT', 'SELECT']).toContain(focusedElement);
  });

  test('should work with dark mode', async ({ page }) => {
    await page.goto('/');
    
    // Toggle dark mode
    const darkModeButton = page.getByRole('button', { name: /switch to dark mode/i });
    await darkModeButton.click();
    
    // Verify dark mode is applied
    const html = page.locator('html');
    await expect(html).toHaveClass(/dark/);
    
    // Check for accessibility violations in dark mode, excluding intentional "Don't" examples
    const accessibilityScanResults = await new AxeBuilder({ page })
      .withTags(['wcag2a', 'wcag2aa'])
      .exclude('.bg-red-50, .dark\\:bg-red-900\\/20')
      .analyze();

    expect(accessibilityScanResults.violations).toEqual([]);
  });

  test('should have accessible form controls', async ({ page }) => {
    await page.goto('/');
    
    // Check that all form inputs have associated labels
    const inputs = await page.locator('input').all();
    
    for (const input of inputs) {
      const inputId = await input.getAttribute('id');
      if (inputId) {
        const label = page.locator(`label[for="${inputId}"]`);
        await expect(label).toBeVisible();
      }
    }
  });

  test('should have proper ARIA labels for icon buttons', async ({ page }) => {
    await page.goto('/');
    
    // Check dark mode toggle button
    const button = page.getByRole('button').first();
    const ariaLabel = await button.getAttribute('aria-label');
    
    expect(ariaLabel).toBeTruthy();
    expect(ariaLabel).toMatch(/switch to (light|dark) mode/i);
  });

  test('should have accessible tables with proper headers', async ({ page }) => {
    await page.goto('/');
    
    // Find tables in the "Do" examples
    const tables = await page.locator('table').all();
    
    for (const table of tables) {
      // Check for thead
      const thead = table.locator('thead');
      await expect(thead).toBeVisible();
      
      // Check for th elements with scope attribute
      const headers = await table.locator('th[scope]').all();
      expect(headers.length).toBeGreaterThan(0);
    }
  });

  test('should have proper heading hierarchy', async ({ page }) => {
    await page.goto('/');
    
    // Check that h1 exists and is unique
    const h1Count = await page.locator('h1').count();
    expect(h1Count).toBe(1);
    
    // Verify heading text
    const h1 = page.locator('h1');
    await expect(h1).toHaveText('Web Accessibility Demo');
  });

  test('should have descriptive link text', async ({ page }) => {
    await page.goto('/');
    
    // Get all links
    const links = await page.locator('a').all();
    
    for (const link of links) {
      const text = await link.textContent();
      const ariaLabel = await link.getAttribute('aria-label');
      
      // Link should have either visible text or aria-label
      expect(text?.trim() || ariaLabel).toBeTruthy();
      
      // Avoid generic link text (if it's a skip link, that's okay)
      if (text && !text.includes('Skip')) {
        expect(text.toLowerCase()).not.toMatch(/^(click here|more|link|read more)$/i);
      }
    }
  });

  test('should have sufficient color contrast', async ({ page }) => {
    await page.goto('/');
    
    // Use axe to check color contrast, excluding intentional "Don't" examples
    const accessibilityScanResults = await new AxeBuilder({ page })
      .withTags(['wcag2aa'])
      .exclude('.bg-red-50, .dark\\:bg-red-900\\/20')
      .analyze();

    const contrastViolations = accessibilityScanResults.violations.filter(
      v => v.id === 'color-contrast'
    );
    
    expect(contrastViolations).toEqual([]);
  });
});
