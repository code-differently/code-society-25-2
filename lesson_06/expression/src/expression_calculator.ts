export class ExpressionCalculator {
  /** Returns a calculation involving a, b, c, d, and e */
  calculate(a: number, b: number, c: number, d: number, e: number): number {
    // Implements: a * Math.pow(b + c, d) / e using class methods
    const sum = this.add(b, c); // b + c
    const power = this.pow(sum, d); // (b + c)^d
    const product = this.multiply(a, power); // a * (b + c)^d
    return this.divide(product, e); // (a * (b + c)^d) / e
  }

  pow(base: number, exponent: number): number {
    return Math.pow(base, exponent);
  }

  add(a: number, b: number): number {
    return a + b;
  }

  subtract(a: number, b: number): number {
    return a - b;
  }

  multiply(a: number, b: number): number {
    return a * b;
  }

  divide(a: number, b: number): number {
    if (b === 0) {
      throw new Error("Division by zero is not allowed.");
    }
    return a / b;
  }
}
