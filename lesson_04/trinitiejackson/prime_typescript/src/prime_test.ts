import { isPrime } from "./prime";

describe("isPrime (super-condensed)", () => {
  test.each([2])(
    "returns true for prime number %i",
    (n) => {
      expect(isPrime(n)).toBe(true);
    }
  );

  test.each([6])(
    "returns false for non-prime number %i",
    (n) => {
      expect(isPrime(n)).toBe(false);
    }
  );
});
