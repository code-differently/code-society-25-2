function isPrime(n) {
  if (!Number.isInteger(n)) return false;
  if (n < 2) return false;
  if (n === 2 || n === 3) return true;
  if (n % 2 === 0) return false;
  for (let i = 3; i * i <= n; i += 2) {
    if (n % i === 0) return false;
  }
  return true;
}

if (typeof module !== 'undefined' && module.exports) {
  module.exports = { isPrime };
} else {
  console.log(isPrime(17)); // true
  console.log(isPrime(18)); // false
}
