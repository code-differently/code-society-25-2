const { isPrime } = require('./isPrime');

test('isPrime basic', () => {
  expect(isPrime(0)).toBe(false);
  expect(isPrime(1)).toBe(false);
  expect(isPrime(2)).toBe(true);
  expect(isPrime(3)).toBe(true);
  expect(isPrime(4)).toBe(false);
  expect(isPrime(17)).toBe(true);
  expect(isPrime(18)).toBe(false);
});
