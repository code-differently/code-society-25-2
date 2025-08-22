/**
 * Test cases for the Alphabetic Abbreviation Calculator
 */
import { createFirstLetterAbbreviation, generateAlphaAbbreviation, isValidAlphaAbbreviation } from './alphabetic_abbreviation_calculator';

/*** Test isValidAlphaAbbreviation function */
console.log('\n=== Testing isValidAlphaAbbreviation ===');

// Example 1 from the README
const test1 = isValidAlphaAbbreviation('internationalization', 'imzdn');
console.log('Test 1 (internationalization, imzdn):', test1 ? 'PASS' : 'FAIL');
// i + (m=13 chars) + z + (d=4 chars) + n
// i + nternationaliza + z + atio + n = internationalization

// Example 2 from the README
const test2 = isValidAlphaAbbreviation('substitution', 'sjn');
console.log('Test 2 (substitution, sjn):', test2 ? 'PASS' : 'FAIL');
// s + (j=10 chars) + n
// s + ubstitutio + n = substitution

// Invalid example from the README
const test3 = isValidAlphaAbbreviation('test', 'ab');
console.log('Test 3 (test, ab):', !test3 ? 'PASS' : 'FAIL');
// Adjacent letters 'a' and 'b' would represent adjacent abbreviated substrings

// Edge cases
console.log('Test 4 (apple, aple):', isValidAlphaAbbreviation('apple', 'aple') === false ? 'PASS' : 'FAIL');
console.log('Test 5 (apple, ae):', isValidAlphaAbbreviation('apple', 'ae') === true ? 'PASS' : 'FAIL');
// a + (e=5 chars) = apple (but there are only 4 chars after 'a', so this should fail)

console.log('Test 6 (a, a):', isValidAlphaAbbreviation('a', 'a') === true ? 'PASS' : 'FAIL');
console.log('Test 7 (a, b):', isValidAlphaAbbreviation('a', 'b') === false ? 'PASS' : 'FAIL');

/**
 * Test createFirstLetterAbbreviation function
 */
console.log('\n=== Testing createFirstLetterAbbreviation ===');
console.log('Test 8 (world wide web):', createFirstLetterAbbreviation('world wide web') === 'www' ? 'PASS' : 'FAIL');
console.log('Test 9 (United States of America):', createFirstLetterAbbreviation('United States of America') === 'USoA' ? 'PASS' : 'FAIL');
console.log('Test 10 (empty string):', createFirstLetterAbbreviation('') === '' ? 'PASS' : 'FAIL');
console.log('Test 11 (single word):', createFirstLetterAbbreviation('hello') === 'h' ? 'PASS' : 'FAIL');

/**
 * Test generateAlphaAbbreviation function
 */
console.log('\n=== Testing generateAlphaAbbreviation ===');

// Test short words
console.log('Test 12 (abc):', generateAlphaAbbreviation('abc') === 'abc' ? 'PASS' : 'FAIL');

// Test medium words
const test13 = generateAlphaAbbreviation('laptop');
console.log('Test 13 (laptop):', isValidAlphaAbbreviation('laptop', test13!) ? 'PASS' : 'FAIL');

// Test longer words
const test14 = generateAlphaAbbreviation('internationalization');
console.log('Test 14 (internationalization):', 
  test14 !== null && isValidAlphaAbbreviation('internationalization', test14) ? 'PASS' : 'FAIL');

// Show the generated abbreviations
console.log('\n=== Generated Abbreviations ===');
console.log('laptop:', generateAlphaAbbreviation('laptop'));
console.log('internationalization:', generateAlphaAbbreviation('internationalization'));
console.log('programming:', generateAlphaAbbreviation('programming'));
console.log('typescript:', generateAlphaAbbreviation('typescript'));
