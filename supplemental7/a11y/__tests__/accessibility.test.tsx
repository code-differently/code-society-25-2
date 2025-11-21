/**
 * Accessibility Unit Tests
 * 
 * These tests use jest-axe to check for accessibility violations
 * in rendered components.
 */

import { render } from '@testing-library/react';
import { axe, toHaveNoViolations } from 'jest-axe';
import Home from '../app/page';

expect.extend(toHaveNoViolations);

describe('Accessibility Tests', () => {
  it('should not have any accessibility violations on the home page', async () => {
    const { container } = render(<Home />);
    const results = await axe(container);
    expect(results).toHaveNoViolations();
  });

  it('should have proper heading hierarchy', () => {
    const { container } = render(<Home />);
    const h1 = container.querySelector('h1');
    const h2s = container.querySelectorAll('h2');
    
    expect(h1).toBeInTheDocument();
    expect(h1?.textContent).toBe('Web Accessibility Demo');
    expect(h2s.length).toBeGreaterThan(0);
  });

  it('should have skip link for keyboard navigation', () => {
    const { getByText } = render(<Home />);
    const skipLink = getByText('Skip to main content');
    
    expect(skipLink).toBeInTheDocument();
    expect(skipLink).toHaveAttribute('href', '#main-content');
  });

  it('should have proper main landmark', () => {
    const { container } = render(<Home />);
    const main = container.querySelector('main');
    
    expect(main).toBeInTheDocument();
    expect(main).toHaveAttribute('id', 'main-content');
  });

  it('should have accessible form labels', () => {
    const { container } = render(<Home />);
    const inputs = container.querySelectorAll('input');
    
    inputs.forEach((input) => {
      const id = input.getAttribute('id');
      if (id) {
        const label = container.querySelector(`label[for="${id}"]`);
        expect(label).toBeInTheDocument();
      }
    });
  });

  it('should have alt text or aria-labels for all interactive elements', () => {
    const { container } = render(<Home />);
    const buttons = container.querySelectorAll('button');
    
    buttons.forEach((button) => {
      const hasText = button.textContent && button.textContent.trim().length > 0;
      const hasAriaLabel = button.hasAttribute('aria-label');
      const hasAriaLabelledBy = button.hasAttribute('aria-labelledby');
      
      expect(hasText || hasAriaLabel || hasAriaLabelledBy).toBe(true);
    });
  });

  it('should have proper ARIA roles for custom components', () => {
    const { container } = render(<Home />);
    const elementsWithRole = container.querySelectorAll('[role]');
    
    expect(elementsWithRole.length).toBeGreaterThan(0);
  });
});
