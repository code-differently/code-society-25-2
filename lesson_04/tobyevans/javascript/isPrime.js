function isPrime(n) {
  if (!Number.isInteger(n)) {
    throw new TypeError('n must be an integer');
  }
  if (n < 2) return false;
  if (n % 2 === 0) return n === 2;
  if (n % 3 === 0) return n === 3;
  const limit = Math.floor(Math.sqrt(n));
  let i = 5;
  let step = 2;
  while (i <= limit) {
    if (n % i === 0) return false;
    i += step;
    step = 6 - step; // check 6k±1
  }
  return true;
}

if (require.main === module) {
  const tests = [0, 1, 2, 3, 4, 5, 17, 18, 19, 20, 7919, 7920];
  for (const t of tests) {
    console.log(`${t}: ${isPrime(t)}`);
  }
}

module.exports = { isPrime };
