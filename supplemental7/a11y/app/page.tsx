'use client';
/* eslint-disable @next/next/no-img-element */

import Link from 'next/link';
import { useEffect, useRef, useState } from 'react';
import { CodeBlock } from './components/CodeBlock';

export default function Home() {
  const [darkMode, setDarkMode] = useState(false);
  const initializedRef = useRef(false);

  useEffect(() => {
    if (!initializedRef.current) {
      initializedRef.current = true;
      // Initialize from localStorage or system preference
      const savedTheme = localStorage.getItem('theme');
      const prefersDark = window.matchMedia('(prefers-color-scheme: dark)').matches;
      const shouldBeDark = savedTheme === 'dark' || (!savedTheme && prefersDark);
      
      if (shouldBeDark) {
        document.documentElement.classList.add('dark');
      }
      // Use setTimeout to defer state update and avoid cascading render warning
      setTimeout(() => setDarkMode(shouldBeDark), 0);
    }
  }, []);

  const toggleDarkMode = () => {
    const newMode = !darkMode;
    setDarkMode(newMode);
    
    if (newMode) {
      document.documentElement.classList.add('dark');
      localStorage.setItem('theme', 'dark');
    } else {
      document.documentElement.classList.remove('dark');
      localStorage.setItem('theme', 'light');
    }
  };

  return (
    <div className="min-h-screen bg-gray-50 dark:bg-gray-900">
      {/* Skip Link */}
      <a
        href="#main-content"
        className="sr-only focus:not-sr-only focus:fixed focus:top-4 focus:left-4 focus:z-50 focus:px-4 focus:py-2 focus:bg-blue-600 focus:text-white focus:rounded"
      >
        Skip to main content
      </a>

      {/* Header */}
      <header className="bg-white dark:bg-gray-800 shadow-sm border-b dark:border-gray-700 fixed top-0 left-0 right-0 z-40">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-6 flex items-start justify-between">
          <div>
            <h1 className="text-3xl font-bold text-gray-900 dark:text-white">
              Web Accessibility Demo
            </h1>
            <p className="mt-2 text-gray-900 dark:text-gray-300">
              Comparing inaccessible vs. accessible patterns
            </p>
          </div>
          <button
            onClick={toggleDarkMode}
            className="flex items-center gap-2 px-4 py-2 rounded-lg bg-gray-100 dark:bg-gray-700 text-gray-900 dark:text-white hover:bg-gray-200 dark:hover:bg-gray-600 focus:outline-none focus:ring-2 focus:ring-blue-500 transition-colors"
            aria-label={darkMode ? 'Switch to light mode' : 'Switch to dark mode'}
          >
            {darkMode ? (
              <>
                <span className="text-xl">☀️</span>
                <span className="text-sm font-medium">Light</span>
              </>
            ) : (
              <>
                <span className="text-xl">🌙</span>
                <span className="text-sm font-medium">Dark</span>
              </>
            )}
          </button>
        </div>
      </header>

      {/* Main Content */}
      <main id="main-content" className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-8 pt-[120px]">
        <div className="mb-8 bg-blue-50 dark:bg-blue-900 border-l-4 border-blue-500 p-4 rounded">
          <p className="text-sm text-blue-900 dark:text-blue-100">
            <strong>Note:</strong> The examples on the left demonstrate common accessibility mistakes. 
            The examples on the right show the correct, accessible implementations.
          </p>
        </div>

        {/* Examples Grid */}
        <div className="space-y-12">
          {/* Example 1: Buttons */}
          <section className="bg-white dark:bg-gray-800 rounded-lg shadow-md overflow-hidden">
            <div className="bg-gray-100 dark:bg-gray-700 px-6 py-4 border-b dark:border-gray-600">
              <h2 className="text-xl font-semibold text-gray-900 dark:text-white">
                1. Interactive Elements - Buttons
              </h2>
            </div>
            <div className="grid md:grid-cols-2 divide-x dark:divide-gray-600">
              {/* Don't */}
              <div className="p-6 bg-red-50 dark:bg-red-900/20">
                <div className="flex items-center gap-2 mb-4">
                  <span className="text-2xl">❌</span>
                  <h3 className="text-lg font-semibold text-red-700 dark:text-red-400">Don&apos;t</h3>
                </div>
                <p className="text-sm text-gray-700 dark:text-gray-300 mb-4">
                  Using a div as a button - not keyboard accessible, no semantic meaning
                </p>
                <div className="bg-white dark:bg-gray-800 p-4 rounded border-2 border-red-200 dark:border-red-800">
                  <div 
                    className="px-4 py-2 bg-blue-600 text-white rounded cursor-pointer inline-block"
                    onClick={() => alert('Clicked!')}
                  >
                    Click me
                  </div>
                </div>
                <CodeBlock code={`<div onClick={() => alert('Clicked!')}>
  Click me
</div>`} />
              </div>

              {/* Do */}
              <div className="p-6 bg-green-50 dark:bg-green-900/20">
                <div className="flex items-center gap-2 mb-4">
                  <span className="text-2xl">✅</span>
                  <h3 className="text-lg font-semibold text-green-700 dark:text-green-300">Do</h3>
                </div>
                <p className="text-sm text-gray-700 dark:text-gray-300 mb-4">
                  Use semantic button element - keyboard accessible, proper ARIA role
                </p>
                <div className="bg-white dark:bg-gray-800 p-4 rounded border-2 border-green-200 dark:border-green-800">
                  <button 
                    className="px-4 py-2 bg-blue-600 text-white rounded hover:bg-blue-700 focus:outline-none focus:ring-2 focus:ring-blue-500 focus:ring-offset-2"
                    onClick={() => alert('Clicked!')}
                  >
                    Click me
                  </button>
                </div>
                <CodeBlock code={`<button 
  className="focus:ring-2"
  onClick={() => alert('Clicked!')}
>
  Click me
</button>`} />
              </div>
            </div>
          </section>

          {/* Example 2: Images */}
          <section className="bg-white dark:bg-gray-800 rounded-lg shadow-md overflow-hidden">
            <div className="bg-gray-100 dark:bg-gray-700 px-6 py-4 border-b dark:border-gray-600">
              <h2 className="text-xl font-semibold text-gray-900 dark:text-white">
                2. Images and Alt Text
              </h2>
            </div>
            <div className="grid md:grid-cols-2 divide-x dark:divide-gray-600">
              {/* Don't */}
              <div className="p-6 bg-red-50 dark:bg-red-900/20">
                <div className="flex items-center gap-2 mb-4">
                  <span className="text-2xl">❌</span>
                  <h3 className="text-lg font-semibold text-red-700 dark:text-red-400">Don&apos;t</h3>
                </div>
                <p className="text-sm text-gray-700 dark:text-gray-300 mb-4">
                  Missing or generic alt text provides no context for screen readers
                </p>
                <div className="bg-white dark:bg-gray-800 p-4 rounded border-2 border-red-200 dark:border-red-800">
                  <img 
                    src="https://via.placeholder.com/300x200/4299e1/1a202c?text=Nature+Scene"
                    alt=""
                    className="w-full h-48 object-cover rounded"
                  />
                </div>
                <CodeBlock code={`<img src="nature.jpg" />
// or
<img src="nature.jpg" alt="image" />`} />
              </div>

              {/* Do */}
              <div className="p-6 bg-green-50 dark:bg-green-900/20">
                <div className="flex items-center gap-2 mb-4">
                  <span className="text-2xl">✅</span>
                  <h3 className="text-lg font-semibold text-green-700 dark:text-green-300">Do</h3>
                </div>
                <p className="text-sm text-gray-700 dark:text-gray-300 mb-4">
                  Descriptive alt text conveys the image content and context
                </p>
                <div className="bg-white dark:bg-gray-800 p-4 rounded border-2 border-green-200 dark:border-green-800">
                  <img 
                    src="https://via.placeholder.com/300x200/4299e1/1a202c?text=Nature+Scene"
                    alt="Sunset over mountain range with pine trees in foreground"
                    className="w-full h-48 object-cover rounded"
                  />
                </div>
                <CodeBlock code={`<img 
  src="nature.jpg" 
  alt="Sunset over mountain range 
       with pine trees in foreground"
/>`} />
              </div>
            </div>
          </section>

          {/* Example 3: Form Labels */}
          <section className="bg-white dark:bg-gray-800 rounded-lg shadow-md overflow-hidden">
            <div className="bg-gray-100 dark:bg-gray-700 px-6 py-4 border-b dark:border-gray-600">
              <h2 className="text-xl font-semibold text-gray-900 dark:text-white">
                3. Form Labels
              </h2>
            </div>
            <div className="grid md:grid-cols-2 divide-x dark:divide-gray-600">
              {/* Don't */}
              <div className="p-6 bg-red-50 dark:bg-red-900/20">
                <div className="flex items-center gap-2 mb-4">
                  <span className="text-2xl">❌</span>
                  <h3 className="text-lg font-semibold text-red-700 dark:text-red-400">Don&apos;t</h3>
                </div>
                <p className="text-sm text-gray-700 dark:text-gray-300 mb-4">
                  Label not associated with input - screen readers can&apos;t connect them
                </p>
                <div className="bg-white dark:bg-gray-800 p-4 rounded border-2 border-red-200 dark:border-red-800">
                  <div className="space-y-4">
                    <div>
                      <div className="text-sm text-gray-900 dark:text-gray-100 mb-1">Email Address</div>
                      <input 
                        type="email" 
                        className="w-full px-3 py-2 border rounded bg-white dark:bg-gray-700 text-gray-900 dark:text-gray-100"
                        placeholder="Enter your email"
                      />
                    </div>
                  </div>
                </div>
                <CodeBlock code={`<div>Email Address</div>
<input type="email" 
  placeholder="Enter your email" />`} />
              </div>

              {/* Do */}
              <div className="p-6 bg-green-50 dark:bg-green-900/20">
                <div className="flex items-center gap-2 mb-4">
                  <span className="text-2xl">✅</span>
                  <h3 className="text-lg font-semibold text-green-700 dark:text-green-300">Do</h3>
                </div>
                <p className="text-sm text-gray-700 dark:text-gray-300 mb-4">
                  Properly associated label using htmlFor - screen readers announce the label
                </p>
                <div className="bg-white dark:bg-gray-800 p-4 rounded border-2 border-green-200 dark:border-green-800">
                  <div className="space-y-4">
                    <div>
                      <label htmlFor="email" className="block text-sm font-medium text-gray-900 dark:text-gray-100 mb-1">
                        Email Address
                      </label>
                      <input 
                        id="email"
                        type="email" 
                        className="w-full px-3 py-2 border rounded focus:outline-none focus:ring-2 focus:ring-blue-500 bg-white dark:bg-gray-700 text-gray-900 dark:text-gray-100"
                        placeholder="Enter your email"
                      />
                    </div>
                  </div>
                </div>
                <CodeBlock code={`<label htmlFor="email">
  Email Address
</label>
<input 
  id="email"
  type="email"
  className="focus:ring-2"
/>`} />
              </div>
            </div>
          </section>

          {/* Example 4: Color Contrast */}
          <section className="bg-white dark:bg-gray-800 rounded-lg shadow-md overflow-hidden">
            <div className="bg-gray-100 dark:bg-gray-700 px-6 py-4 border-b dark:border-gray-600">
              <h2 className="text-xl font-semibold text-gray-900 dark:text-white">
                4. Color Contrast
              </h2>
            </div>
            <div className="grid md:grid-cols-2 divide-x dark:divide-gray-600">
              {/* Don't */}
              <div className="p-6 bg-red-50 dark:bg-red-900/20">
                <div className="flex items-center gap-2 mb-4">
                  <span className="text-2xl">❌</span>
                  <h3 className="text-lg font-semibold text-red-700 dark:text-red-400">Don&apos;t</h3>
                </div>
                <p className="text-sm text-gray-700 dark:text-gray-300 mb-4">
                  Low contrast text (2.5:1) - fails WCAG AA standard
                </p>
                <div className="bg-white dark:bg-gray-800 p-4 rounded border-2 border-red-200 dark:border-red-800">
                  <p className="text-gray-400 text-base">
                    This text is hard to read with insufficient contrast ratio
                  </p>
                  <button className="mt-3 px-4 py-2 bg-gray-300 text-gray-400 rounded">
                    Disabled Looking Button
                  </button>
                </div>
                <CodeBlock code={`<p className="text-gray-400">
  This text is hard to read
</p>
// Contrast ratio: ~2.5:1 ❌`} />
              </div>

              {/* Do */}
              <div className="p-6 bg-green-50 dark:bg-green-900/20">
                <div className="flex items-center gap-2 mb-4">
                  <span className="text-2xl">✅</span>
                  <h3 className="text-lg font-semibold text-green-700 dark:text-green-300">Do</h3>
                </div>
                <p className="text-sm text-gray-700 dark:text-gray-300 mb-4">
                  High contrast text (7:1) - exceeds WCAG AAA standard
                </p>
                <div className="bg-white dark:bg-gray-800 p-4 rounded border-2 border-green-200 dark:border-green-800">
                  <p className="text-gray-900 dark:text-gray-100 text-base">
                    This text is easy to read with proper contrast ratio
                  </p>
                  <button className="mt-3 px-4 py-2 bg-blue-600 text-white rounded hover:bg-blue-700 focus:outline-none focus:ring-2 focus:ring-blue-500">
                    Active Button
                  </button>
                </div>
                <CodeBlock code={`<p className="text-gray-900">
  This text is easy to read
</p>
// Contrast ratio: ~15:1 ✅`} />
              </div>
            </div>
          </section>

          {/* Example 5: Links */}
          <section className="bg-white dark:bg-gray-800 rounded-lg shadow-md overflow-hidden">
            <div className="bg-gray-100 dark:bg-gray-700 px-6 py-4 border-b dark:border-gray-600">
              <h2 className="text-xl font-semibold text-gray-900 dark:text-white">
                5. Link Text
              </h2>
            </div>
            <div className="grid md:grid-cols-2 divide-x dark:divide-gray-600">
              {/* Don't */}
              <div className="p-6 bg-red-50 dark:bg-red-900/20">
                <div className="flex items-center gap-2 mb-4">
                  <span className="text-2xl">❌</span>
                  <h3 className="text-lg font-semibold text-red-700 dark:text-red-400">Don&apos;t</h3>
                </div>
                <p className="text-sm text-gray-700 dark:text-gray-300 mb-4">
                  Generic link text provides no context out of context
                </p>
                <div className="bg-white dark:bg-gray-800 p-4 rounded border-2 border-red-200 dark:border-red-800">
                  <p className="mb-2 text-gray-900 dark:text-gray-100">
                    Learn about web accessibility best practices. 
                    <a href="#" className="text-blue-600 dark:text-blue-400 underline ml-1">Read the accessibility guide</a>
                  </p>
                  <p className="text-gray-900 dark:text-gray-100">
                    Read our documentation. 
                    <a href="#" className="text-blue-600 dark:text-blue-400 underline ml-1">More info</a>
                  </p>
                </div>
                <CodeBlock code={`<a href="#">Click here</a>
<a href="#">More info</a>
<a href="#">Read more</a>`} />
              </div>

              {/* Do */}
              <div className="p-6 bg-green-50 dark:bg-green-900/20">
                <div className="flex items-center gap-2 mb-4">
                  <span className="text-2xl">✅</span>
                  <h3 className="text-lg font-semibold text-green-700 dark:text-green-300">Do</h3>
                </div>
                <p className="text-sm text-gray-700 dark:text-gray-300 mb-4">
                  Descriptive link text makes sense when read alone
                </p>
                <div className="bg-white dark:bg-gray-800 p-4 rounded border-2 border-green-200 dark:border-green-800">
                  <p className="mb-2 text-gray-900 dark:text-gray-100">
                    <Link 
                      href="#" 
                      className="text-blue-600 dark:text-blue-400 underline hover:text-blue-800 focus:outline-none focus:ring-2 focus:ring-blue-500 rounded"
                    >
                      Learn about web accessibility best practices
                    </Link>
                  </p>
                  <p className="text-gray-900 dark:text-gray-100">
                    <Link 
                      href="#" 
                      className="text-blue-600 dark:text-blue-400 underline hover:text-blue-800 focus:outline-none focus:ring-2 focus:ring-blue-500 rounded"
                    >
                      Read our accessibility documentation
                    </Link>
                  </p>
                </div>
                <CodeBlock code={`<a href="#">
  Learn about web accessibility 
  best practices
</a>
<a href="#">
  Read our accessibility documentation
</a>`} />
              </div>
            </div>
          </section>

          {/* Example 6: Heading Hierarchy */}
          <section className="bg-white dark:bg-gray-800 rounded-lg shadow-md overflow-hidden">
            <div className="bg-gray-100 dark:bg-gray-700 px-6 py-4 border-b dark:border-gray-600">
              <h2 className="text-xl font-semibold text-gray-900 dark:text-white">
                6. Heading Hierarchy
              </h2>
            </div>
            <div className="grid md:grid-cols-2 divide-x dark:divide-gray-600">
              {/* Don't */}
              <div className="p-6 bg-red-50 dark:bg-red-900/20">
                <div className="flex items-center gap-2 mb-4">
                  <span className="text-2xl">❌</span>
                  <h3 className="text-lg font-semibold text-red-700 dark:text-red-400">Don&apos;t</h3>
                </div>
                <p className="text-sm text-gray-700 dark:text-gray-300 mb-4">
                  Skipping heading levels and using headings for styling only
                </p>
                <div className="bg-white dark:bg-gray-800 p-4 rounded border-2 border-red-200 dark:border-red-800">
                  <div className="text-2xl font-bold text-gray-900 dark:text-gray-100">Page Title</div>
                  <div className="text-sm font-semibold mt-4 text-gray-900 dark:text-gray-100">Subsection</div>
                  <p className="text-xs mt-2 text-gray-900 dark:text-gray-100">Some content here</p>
                  <div className="text-lg font-bold mt-4 text-gray-900 dark:text-gray-100">Another Section</div>
                </div>
                <CodeBlock code={`<div className="text-2xl">Page Title</div>
<div className="text-sm">Subsection</div>
<div className="text-lg">Another Section</div>`} />
              </div>

              {/* Do */}
              <div className="p-6 bg-green-50 dark:bg-green-900/20">
                <div className="flex items-center gap-2 mb-4">
                  <span className="text-2xl">✅</span>
                  <h3 className="text-lg font-semibold text-green-700 dark:text-green-300">Do</h3>
                </div>
                <p className="text-sm text-gray-700 dark:text-gray-300 mb-4">
                  Use semantic heading tags in proper order (h1, h2, h3...)
                </p>
                <div className="bg-white dark:bg-gray-800 p-4 rounded border-2 border-green-200 dark:border-green-800">
                  <h3 className="text-2xl font-bold text-gray-900 dark:text-gray-100">Page Title</h3>
                  <h4 className="text-lg font-bold mt-4 text-gray-900 dark:text-gray-100">Main Section</h4>
                  <h5 className="text-sm font-semibold mt-4 text-gray-900 dark:text-gray-100">Subsection</h5>
                  <p className="text-xs mt-2 text-gray-900 dark:text-gray-100">Some content here</p>
                </div>
                <CodeBlock code={`<h1>Page Title</h1>
<h2>Main Section</h2>
<h3>Subsection</h3>
<p>Some content here</p>`} />
              </div>
            </div>
          </section>

          {/* Example 7: Focus Indicators */}
          <section className="bg-white dark:bg-gray-800 rounded-lg shadow-md overflow-hidden">
            <div className="bg-gray-100 dark:bg-gray-700 px-6 py-4 border-b dark:border-gray-600">
              <h2 className="text-xl font-semibold text-gray-900 dark:text-white">
                7. Focus Indicators
              </h2>
            </div>
            <div className="grid md:grid-cols-2 divide-x dark:divide-gray-600">
              {/* Don't */}
              <div className="p-6 bg-red-50 dark:bg-red-900/20">
                <div className="flex items-center gap-2 mb-4">
                  <span className="text-2xl">❌</span>
                  <h3 className="text-lg font-semibold text-red-700 dark:text-red-400">Don&apos;t</h3>
                </div>
                <p className="text-sm text-gray-700 dark:text-gray-300 mb-4">
                  Removing focus indicators makes keyboard navigation impossible
                </p>
                <div className="bg-white dark:bg-gray-800 p-4 rounded border-2 border-red-200 dark:border-red-800">
                  <button className="px-4 py-2 bg-blue-600 text-white rounded outline-none focus:outline-none mr-2">
                    Button 1
                  </button>
                  <button className="px-4 py-2 bg-blue-600 text-white rounded outline-none focus:outline-none">
                    Button 2
                  </button>
                  <p className="text-xs mt-3 text-gray-900 dark:text-gray-100">Try tabbing through these buttons</p>
                </div>
                <CodeBlock code={`<button className="outline-none 
  focus:outline-none">
  Button
</button>

/* CSS */
*:focus {
  outline: none; /* ❌ Never do this */
}`} language="css" />
              </div>

              {/* Do */}
              <div className="p-6 bg-green-50 dark:bg-green-900/20">
                <div className="flex items-center gap-2 mb-4">
                  <span className="text-2xl">✅</span>
                  <h3 className="text-lg font-semibold text-green-700 dark:text-green-300">Do</h3>
                </div>
                <p className="text-sm text-gray-700 dark:text-gray-300 mb-4">
                  Clear focus indicators help keyboard users navigate
                </p>
                <div className="bg-white dark:bg-gray-800 p-4 rounded border-2 border-green-200 dark:border-green-800">
                  <button className="px-4 py-2 bg-blue-600 text-white rounded hover:bg-blue-700 focus:outline-none focus:ring-2 focus:ring-blue-500 focus:ring-offset-2 mr-2">
                    Button 1
                  </button>
                  <button className="px-4 py-2 bg-blue-600 text-white rounded hover:bg-blue-700 focus:outline-none focus:ring-2 focus:ring-blue-500 focus:ring-offset-2">
                    Button 2
                  </button>
                  <p className="text-xs mt-3 text-gray-900 dark:text-gray-100">Try tabbing through these buttons</p>
                </div>
                <CodeBlock code={`<button className="focus:outline-none 
  focus:ring-2 focus:ring-blue-500 
  focus:ring-offset-2">
  Button
</button>

/* CSS */
button:focus {
  outline: 3px solid #4A90E2;
}`} language="css" />
              </div>
            </div>
          </section>

          {/* Example 8: ARIA Labels */}
          <section className="bg-white dark:bg-gray-800 rounded-lg shadow-md overflow-hidden">
            <div className="bg-gray-100 dark:bg-gray-700 px-6 py-4 border-b dark:border-gray-600">
              <h2 className="text-xl font-semibold text-gray-900 dark:text-white">
                8. ARIA Labels for Icons
              </h2>
            </div>
            <div className="grid md:grid-cols-2 divide-x dark:divide-gray-600">
              {/* Don't */}
              <div className="p-6 bg-red-50 dark:bg-red-900/20">
                <div className="flex items-center gap-2 mb-4">
                  <span className="text-2xl">❌</span>
                  <h3 className="text-lg font-semibold text-red-700 dark:text-red-400">Don&apos;t</h3>
                </div>
                <p className="text-sm text-gray-700 dark:text-gray-300 mb-4">
                  Icon-only buttons without labels are meaningless to screen readers
                </p>
                <div className="bg-white dark:bg-gray-800 p-4 rounded border-2 border-red-200 dark:border-red-800">
                  <button className="p-2 bg-blue-600 text-white rounded mr-2">
                    ✕
                  </button>
                  <button className="p-2 bg-blue-600 text-white rounded mr-2">
                    ☰
                  </button>
                  <button className="p-2 bg-blue-600 text-white rounded">
                    ⚙
                  </button>
                </div>
                <CodeBlock code={`<button>✕</button>
<button>☰</button>
<button>⚙</button>`} />
              </div>

              {/* Do */}
              <div className="p-6 bg-green-50 dark:bg-green-900/20">
                <div className="flex items-center gap-2 mb-4">
                  <span className="text-2xl">✅</span>
                  <h3 className="text-lg font-semibold text-green-700 dark:text-green-300">Do</h3>
                </div>
                <p className="text-sm text-gray-700 dark:text-gray-300 mb-4">
                  Use aria-label to provide context for icon-only buttons
                </p>
                <div className="bg-white dark:bg-gray-800 p-4 rounded border-2 border-green-200 dark:border-green-800">
                  <button 
                    aria-label="Close dialog"
                    className="p-2 bg-blue-600 text-white rounded hover:bg-blue-700 focus:outline-none focus:ring-2 focus:ring-blue-500 mr-2"
                  >
                    ✕
                  </button>
                  <button 
                    aria-label="Open menu"
                    className="p-2 bg-blue-600 text-white rounded hover:bg-blue-700 focus:outline-none focus:ring-2 focus:ring-blue-500 mr-2"
                  >
                    ☰
                  </button>
                  <button 
                    aria-label="Open settings"
                    className="p-2 bg-blue-600 text-white rounded hover:bg-blue-700 focus:outline-none focus:ring-2 focus:ring-blue-500"
                  >
                    ⚙
                  </button>
                </div>
                <CodeBlock code={`<button aria-label="Close dialog">
  ✕
</button>
<button aria-label="Open menu">
  ☰
</button>
<button aria-label="Open settings">
  ⚙
</button>`} />
              </div>
            </div>
          </section>

          {/* Example 9: Form Error Handling */}
          <section className="bg-white dark:bg-gray-800 rounded-lg shadow-md overflow-hidden">
            <div className="bg-gray-100 dark:bg-gray-700 px-6 py-4 border-b dark:border-gray-600">
              <h2 className="text-xl font-semibold text-gray-900 dark:text-white">
                9. Form Error Handling
              </h2>
            </div>
            <div className="grid md:grid-cols-2 divide-x dark:divide-gray-600">
              {/* Don't */}
              <div className="p-6 bg-red-50 dark:bg-red-900/20">
                <div className="flex items-center gap-2 mb-4">
                  <span className="text-2xl">❌</span>
                  <h3 className="text-lg font-semibold text-red-700 dark:text-red-400">Don&apos;t</h3>
                </div>
                <p className="text-sm text-gray-700 dark:text-gray-300 mb-4">
                  Generic error message not associated with the field
                </p>
                <div className="bg-white dark:bg-gray-800 p-4 rounded border-2 border-red-200 dark:border-red-800">
                  <div>
                    <label htmlFor="bad-email" className="block text-sm font-medium text-gray-900 dark:text-gray-100 mb-1">
                      Email
                    </label>
                    <input 
                      id="bad-email"
                      type="email" 
                      className="w-full px-3 py-2 border border-red-500 rounded bg-white dark:bg-gray-700 text-gray-900 dark:text-gray-100"
                    />
                    <p className="text-red-700 dark:text-red-400 text-xs mt-1">Error!</p>
                  </div>
                </div>
                <CodeBlock code={`<input type="email" className="border-red-500" />
<p className="text-red-500">Error!</p>`} />
              </div>

              {/* Do */}
              <div className="p-6 bg-green-50 dark:bg-green-900/20">
                <div className="flex items-center gap-2 mb-4">
                  <span className="text-2xl">✅</span>
                  <h3 className="text-lg font-semibold text-green-700 dark:text-green-300">Do</h3>
                </div>
                <p className="text-sm text-gray-700 dark:text-gray-300 mb-4">
                  Descriptive error with aria-describedby and aria-invalid
                </p>
                <div className="bg-white dark:bg-gray-800 p-4 rounded border-2 border-green-200 dark:border-green-800">
                  <div>
                    <label htmlFor="good-email" className="block text-sm font-medium text-gray-900 dark:text-gray-100 mb-1">
                      Email
                    </label>
                    <input 
                      id="good-email"
                      type="email"
                      aria-invalid="true"
                      aria-describedby="email-error"
                      className="w-full px-3 py-2 border border-red-500 rounded focus:outline-none focus:ring-2 focus:ring-blue-500 bg-white dark:bg-gray-700 text-gray-900 dark:text-gray-100"
                    />
                    <p id="email-error" className="text-red-700 dark:text-red-400 text-xs mt-1" role="alert">
                      ⚠ Please enter a valid email address (e.g., user@example.com)
                    </p>
                  </div>
                </div>
                <CodeBlock code={`<input 
  type="email"
  aria-invalid="true"
  aria-describedby="email-error"
/>
<p id="email-error" role="alert">
  Please enter a valid email address
</p>`} />
              </div>
            </div>
          </section>

          {/* Example 10: Tables */}
          <section className="bg-white dark:bg-gray-800 rounded-lg shadow-md overflow-hidden">
            <div className="bg-gray-100 dark:bg-gray-700 px-6 py-4 border-b dark:border-gray-600">
              <h2 className="text-xl font-semibold text-gray-900 dark:text-white">
                10. Data Tables
              </h2>
            </div>
            <div className="grid md:grid-cols-2 divide-x dark:divide-gray-600">
              {/* Don't */}
              <div className="p-6 bg-red-50 dark:bg-red-900/20">
                <div className="flex items-center gap-2 mb-4">
                  <span className="text-2xl">❌</span>
                  <h3 className="text-lg font-semibold text-red-700 dark:text-red-400">Don&apos;t</h3>
                </div>
                <p className="text-sm text-gray-700 dark:text-gray-300 mb-4">
                  Using divs for tables - no structure for screen readers
                </p>
                <div className="bg-white dark:bg-gray-800 p-4 rounded border-2 border-red-200 dark:border-red-800 overflow-x-auto">
                  <div className="text-xs text-gray-900 dark:text-gray-100">
                    <div className="flex font-bold border-b pb-2">
                      <div className="flex-1">Name</div>
                      <div className="flex-1">Role</div>
                      <div className="flex-1">Status</div>
                    </div>
                    <div className="flex py-2">
                      <div className="flex-1">John Doe</div>
                      <div className="flex-1">Developer</div>
                      <div className="flex-1">Active</div>
                    </div>
                  </div>
                </div>
                <CodeBlock code={`<div>
  <div>Name | Role | Status</div>
  <div>John | Dev | Active</div>
</div>`} />
              </div>

              {/* Do */}
              <div className="p-6 bg-green-50 dark:bg-green-900/20">
                <div className="flex items-center gap-2 mb-4">
                  <span className="text-2xl">✅</span>
                  <h3 className="text-lg font-semibold text-green-700 dark:text-green-300">Do</h3>
                </div>
                <p className="text-sm text-gray-700 dark:text-gray-300 mb-4">
                  Use semantic table elements with proper headers
                </p>
                <div className="bg-white dark:bg-gray-800 p-4 rounded border-2 border-green-200 dark:border-green-800 overflow-x-auto">
                  <table className="w-full text-xs border-collapse text-gray-900 dark:text-gray-100">
                    <caption className="text-left font-semibold mb-2">Team Members</caption>
                    <thead>
                      <tr className="border-b">
                        <th scope="col" className="text-left py-2 pr-4">Name</th>
                        <th scope="col" className="text-left py-2 pr-4">Role</th>
                        <th scope="col" className="text-left py-2">Status</th>
                      </tr>
                    </thead>
                    <tbody>
                      <tr>
                        <td className="py-2 pr-4">John Doe</td>
                        <td className="py-2 pr-4">Developer</td>
                        <td className="py-2">Active</td>
                      </tr>
                    </tbody>
                  </table>
                </div>
                <CodeBlock code={`<table>
  <caption>Team Members</caption>
  <thead>
    <tr>
      <th scope="col">Name</th>
      <th scope="col">Role</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>John Doe</td>
      <td>Developer</td>
    </tr>
  </tbody>
</table>`} />
              </div>
            </div>
          </section>

          {/* Example 11: Required Fields */}
          <section className="bg-white dark:bg-gray-800 rounded-lg shadow-md overflow-hidden">
            <div className="bg-gray-100 dark:bg-gray-700 px-6 py-4 border-b dark:border-gray-600">
              <h2 className="text-xl font-semibold text-gray-900 dark:text-white">
                11. Required Fields
              </h2>
            </div>
            <div className="grid md:grid-cols-2 divide-x dark:divide-gray-600">
              {/* Don't */}
              <div className="p-6 bg-red-50 dark:bg-red-900/20">
                <div className="flex items-center gap-2 mb-4">
                  <span className="text-2xl">❌</span>
                  <h3 className="text-lg font-semibold text-red-700 dark:text-red-400">Don&apos;t</h3>
                </div>
                <p className="text-sm text-gray-700 dark:text-gray-300 mb-4">
                  Using only color/asterisk without programmatic indication
                </p>
                <div className="bg-white dark:bg-gray-800 p-4 rounded border-2 border-red-200 dark:border-red-800">
                  <div>
                    <label htmlFor="bad-name" className="block text-sm font-medium text-red-700 dark:text-red-400 mb-1">
                      Name *
                    </label>
                    <input 
                      id="bad-name"
                      type="text" 
                      className="w-full px-3 py-2 border rounded bg-white dark:bg-gray-700 text-gray-900 dark:text-gray-100"
                    />
                  </div>
                </div>
                <CodeBlock code={`<label className="text-red-600">
  Name *
</label>
<input type="text" />`} />
              </div>

              {/* Do */}
              <div className="p-6 bg-green-50 dark:bg-green-900/20">
                <div className="flex items-center gap-2 mb-4">
                  <span className="text-2xl">✅</span>
                  <h3 className="text-lg font-semibold text-green-700 dark:text-green-300">Do</h3>
                </div>
                <p className="text-sm text-gray-700 dark:text-gray-300 mb-4">
                  Use required attribute and aria-required
                </p>
                <div className="bg-white dark:bg-gray-800 p-4 rounded border-2 border-green-200 dark:border-green-800">
                  <div>
                    <label htmlFor="good-name" className="block text-sm font-medium text-gray-900 dark:text-gray-100 mb-1">
                      Name <span className="text-red-600" aria-label="required">*</span>
                    </label>
                    <input 
                      id="good-name"
                      type="text"
                      required
                      aria-required="true"
                      className="w-full px-3 py-2 border rounded focus:outline-none focus:ring-2 focus:ring-blue-500 bg-white dark:bg-gray-700 text-gray-900 dark:text-gray-100"
                    />
                  </div>
                </div>
                <CodeBlock code={`<label htmlFor="name">
  Name <span aria-label="required">*</span>
</label>
<input 
  id="name"
  type="text"
  required
  aria-required="true"
/>`} />
              </div>
            </div>
          </section>

          {/* Example 12: Decorative Images */}
          <section className="bg-white dark:bg-gray-800 rounded-lg shadow-md overflow-hidden">
            <div className="bg-gray-100 dark:bg-gray-700 px-6 py-4 border-b dark:border-gray-600">
              <h2 className="text-xl font-semibold text-gray-900 dark:text-white">
                12. Decorative Images
              </h2>
            </div>
            <div className="grid md:grid-cols-2 divide-x dark:divide-gray-600">
              {/* Don't */}
              <div className="p-6 bg-red-50 dark:bg-red-900/20">
                <div className="flex items-center gap-2 mb-4">
                  <span className="text-2xl">❌</span>
                  <h3 className="text-lg font-semibold text-red-700 dark:text-red-400">Don&apos;t</h3>
                </div>
                <p className="text-sm text-gray-700 dark:text-gray-300 mb-4">
                  Descriptive alt text for decorative images adds noise
                </p>
                <div className="bg-white dark:bg-gray-800 p-4 rounded border-2 border-red-200 dark:border-red-800">
                  <div className="flex items-center gap-3">
                    <div className="w-8 h-8 bg-blue-200 rounded flex items-center justify-center text-xl">
                      ⭐
                    </div>
                    <p className="text-sm text-gray-900 dark:text-gray-100">Premium Feature</p>
                  </div>
                </div>
                <CodeBlock code={`<img src="star-icon.svg" 
     alt="Yellow star icon" />
<p>Premium Feature</p>
// Screen reader: "Yellow star icon Premium Feature"`} />
              </div>

              {/* Do */}
              <div className="p-6 bg-green-50 dark:bg-green-900/20">
                <div className="flex items-center gap-2 mb-4">
                  <span className="text-2xl">✅</span>
                  <h3 className="text-lg font-semibold text-green-700 dark:text-green-300">Do</h3>
                </div>
                <p className="text-sm text-gray-700 dark:text-gray-300 mb-4">
                  Empty alt or aria-hidden for decorative elements
                </p>
                <div className="bg-white dark:bg-gray-800 p-4 rounded border-2 border-green-200 dark:border-green-800">
                  <div className="flex items-center gap-3">
                    <div className="w-8 h-8 bg-blue-200 rounded flex items-center justify-center text-xl" aria-hidden="true">
                      ⭐
                    </div>
                    <p className="text-sm text-gray-900 dark:text-gray-100">Premium Feature</p>
                  </div>
                </div>
                <CodeBlock code={`<img src="star-icon.svg" 
     alt="" 
     aria-hidden="true" />
<p>Premium Feature</p>
// Screen reader: "Premium Feature" (cleaner)`} />
              </div>
            </div>
          </section>

          {/* Example 13: Custom Select/Dropdown */}
          <section className="bg-white dark:bg-gray-800 rounded-lg shadow-md overflow-hidden">
            <div className="bg-gray-100 dark:bg-gray-700 px-6 py-4 border-b dark:border-gray-600">
              <h2 className="text-xl font-semibold text-gray-900 dark:text-white">
                13. Select Dropdowns
              </h2>
            </div>
            <div className="grid md:grid-cols-2 divide-x dark:divide-gray-600">
              {/* Don't */}
              <div className="p-6 bg-red-50 dark:bg-red-900/20">
                <div className="flex items-center gap-2 mb-4">
                  <span className="text-2xl">❌</span>
                  <h3 className="text-lg font-semibold text-red-700 dark:text-red-400">Don&apos;t</h3>
                </div>
                <p className="text-sm text-gray-700 dark:text-gray-300 mb-4">
                  Custom dropdown without proper ARIA attributes
                </p>
                <div className="bg-white dark:bg-gray-800 p-4 rounded border-2 border-red-200 dark:border-red-800">
                  <div>
                    <div className="text-sm text-gray-900 dark:text-gray-100 mb-1">Choose Option</div>
                    <div className="w-full px-3 py-2 border rounded bg-white dark:bg-gray-700 cursor-pointer text-gray-900 dark:text-gray-100">
                      Select an option ▼
                    </div>
                  </div>
                </div>
                <CodeBlock code={`<div>Choose Option</div>
<div onClick={toggle}>
  Select an option ▼
</div>`} />
              </div>

              {/* Do */}
              <div className="p-6 bg-green-50 dark:bg-green-900/20">
                <div className="flex items-center gap-2 mb-4">
                  <span className="text-2xl">✅</span>
                  <h3 className="text-lg font-semibold text-green-700 dark:text-green-300">Do</h3>
                </div>
                <p className="text-sm text-gray-700 dark:text-gray-300 mb-4">
                  Use native select or proper ARIA combobox pattern
                </p>
                <div className="bg-white dark:bg-gray-800 p-4 rounded border-2 border-green-200 dark:border-green-800">
                  <div>
                    <label htmlFor="option-select" className="block text-sm font-medium text-gray-900 dark:text-gray-100 mb-1">
                      Choose Option
                    </label>
                    <select 
                      id="option-select"
                      defaultValue=""
                      className="w-full px-3 py-2 border rounded focus:outline-none focus:ring-2 focus:ring-blue-500 bg-white dark:bg-gray-700"
                    >
                      <option value="">Select an option</option>
                      <option value="1">Option 1</option>
                      <option value="2">Option 2</option>
                    </select>
                  </div>
                </div>
                <CodeBlock code={`<label htmlFor="option-select">
  Choose Option
</label>
<select id="option-select">
  <option>Select an option</option>
  <option>Option 1</option>
</select>`} />
              </div>
            </div>
          </section>

          {/* Example 14: Lists */}
          <section className="bg-white dark:bg-gray-800 rounded-lg shadow-md overflow-hidden">
            <div className="bg-gray-100 dark:bg-gray-700 px-6 py-4 border-b dark:border-gray-600">
              <h2 className="text-xl font-semibold text-gray-900 dark:text-white">
                14. Lists
              </h2>
            </div>
            <div className="grid md:grid-cols-2 divide-x dark:divide-gray-600">
              {/* Don't */}
              <div className="p-6 bg-red-50 dark:bg-red-900/20">
                <div className="flex items-center gap-2 mb-4">
                  <span className="text-2xl">❌</span>
                  <h3 className="text-lg font-semibold text-red-700 dark:text-red-400">Don&apos;t</h3>
                </div>
                <p className="text-sm text-gray-700 dark:text-gray-300 mb-4">
                  Using divs or line breaks instead of list elements
                </p>
                <div className="bg-white dark:bg-gray-800 p-4 rounded border-2 border-red-200 dark:border-red-800">
                  <div className="text-sm text-gray-900 dark:text-gray-100">
                    <div>• Apples</div>
                    <div>• Oranges</div>
                    <div>• Bananas</div>
                  </div>
                </div>
                <CodeBlock code={`<div>• Apples</div>
<div>• Oranges</div>
<div>• Bananas</div>`} />
              </div>

              {/* Do */}
              <div className="p-6 bg-green-50 dark:bg-green-900/20">
                <div className="flex items-center gap-2 mb-4">
                  <span className="text-2xl">✅</span>
                  <h3 className="text-lg font-semibold text-green-700 dark:text-green-300">Do</h3>
                </div>
                <p className="text-sm text-gray-700 dark:text-gray-300 mb-4">
                  Use semantic ul/ol elements for lists
                </p>
                <div className="bg-white dark:bg-gray-800 p-4 rounded border-2 border-green-200 dark:border-green-800">
                  <ul className="text-sm list-disc list-inside text-gray-900 dark:text-gray-100">
                    <li>Apples</li>
                    <li>Oranges</li>
                    <li>Bananas</li>
                  </ul>
                </div>
                <CodeBlock code={`<ul>
  <li>Apples</li>
  <li>Oranges</li>
  <li>Bananas</li>
</ul>
// Screen reader: "List, 3 items"`} />
              </div>
            </div>
          </section>

          {/* Example 15: Loading States */}
          <section className="bg-white dark:bg-gray-800 rounded-lg shadow-md overflow-hidden">
            <div className="bg-gray-100 dark:bg-gray-700 px-6 py-4 border-b dark:border-gray-600">
              <h2 className="text-xl font-semibold text-gray-900 dark:text-white">
                15. Loading States
              </h2>
            </div>
            <div className="grid md:grid-cols-2 divide-x dark:divide-gray-600">
              {/* Don't */}
              <div className="p-6 bg-red-50 dark:bg-red-900/20">
                <div className="flex items-center gap-2 mb-4">
                  <span className="text-2xl">❌</span>
                  <h3 className="text-lg font-semibold text-red-700 dark:text-red-400">Don&apos;t</h3>
                </div>
                <p className="text-sm text-gray-700 dark:text-gray-300 mb-4">
                  Silent loading with no indication for screen readers
                </p>
                <div className="bg-white dark:bg-gray-800 p-4 rounded border-2 border-red-200 dark:border-red-800">
                  <div className="flex items-center gap-2">
                    <div className="w-4 h-4 border-2 border-blue-500 border-t-transparent rounded-full animate-spin"></div>
                    <span className="text-sm text-gray-900 dark:text-gray-100">Loading...</span>
                  </div>
                </div>
                <CodeBlock code={`<div className="spinner"></div>
<span>Loading...</span>`} />
              </div>

              {/* Do */}
              <div className="p-6 bg-green-50 dark:bg-green-900/20">
                <div className="flex items-center gap-2 mb-4">
                  <span className="text-2xl">✅</span>
                  <h3 className="text-lg font-semibold text-green-700 dark:text-green-300">Do</h3>
                </div>
                <p className="text-sm text-gray-700 dark:text-gray-300 mb-4">
                  Use aria-live to announce loading states
                </p>
                <div className="bg-white dark:bg-gray-800 p-4 rounded border-2 border-green-200 dark:border-green-800">
                  <div className="flex items-center gap-2" role="status" aria-live="polite">
                    <div className="w-4 h-4 border-2 border-blue-600 border-t-transparent rounded-full animate-spin" aria-hidden="true"></div>
                    <span className="text-sm text-gray-900 dark:text-gray-100">Loading content, please wait...</span>
                  </div>
                </div>
                <CodeBlock code={`<div role="status" aria-live="polite">
  <div className="spinner" aria-hidden="true">
  </div>
  <span>Loading content, please wait...</span>
</div>`} />
              </div>
            </div>
          </section>
        </div>

        {/* Testing Tips */}
        <div className="mt-12 bg-blue-50 dark:bg-blue-900/20 border border-blue-200 dark:border-blue-800 rounded-lg p-6">
          <h2 className="text-xl font-semibold text-gray-900 dark:text-white mb-4">
            Testing Tips
          </h2>
          <ul className="space-y-2 text-sm text-gray-700 dark:text-gray-300">
            <li className="flex items-start">
              <span className="mr-2">🎯</span>
              <span><strong>Keyboard Test:</strong> Try navigating this page using only Tab, Shift+Tab, Enter, and Space keys</span>
            </li>
            <li className="flex items-start">
              <span className="mr-2">🔍</span>
              <span><strong>Screen Reader:</strong> Enable VoiceOver (Cmd+F5 on Mac) or NVDA (Windows) to hear how content is announced</span>
            </li>
            <li className="flex items-start">
              <span className="mr-2">🎨</span>
              <span><strong>Contrast:</strong> Use browser DevTools or WebAIM Contrast Checker to verify color ratios</span>
            </li>
            <li className="flex items-start">
              <span className="mr-2">📱</span>
              <span><strong>Zoom:</strong> Test at 200% zoom - content should remain usable and readable</span>
            </li>
          </ul>
        </div>
      </main>

      {/* Footer */}
      <footer className="bg-gray-800 dark:bg-gray-950 text-white mt-12">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-8">
          <p className="text-center text-sm">
            Web Accessibility Demo - Code Society 2025 | 
            <a href="#main-content" className="ml-2 underline hover:text-blue-300 focus:outline-none focus:ring-2 focus:ring-blue-400 rounded">
              Back to top
            </a>
          </p>
        </div>
      </footer>
    </div>
  );
}
