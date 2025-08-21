#!/usr/bin/env bash
# Simple JS test runner without Jest
node - <<'NODE'
const { isPrime } = require('./isPrime');
const cases = [
  [0, false], [1, false], [2, true], [3, true], [4, false], [17, true], [18, false], [7919, true]
];
let ok = true;
for (const [n, expected] of cases) {
  const got = isPrime(n);
  console.log(`${n}: expected=${expected} got=${got}`);
  if (got !== expected) ok = false;
}
console.log(ok ? 'OK' : 'FAIL');
NODE
