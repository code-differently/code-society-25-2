export class ExpressionCalculator {
  /** Returns a calculation involving a, b, c, d, and e */
  calculate(a: number, b: number, c: number, d: number, e: number): number {
    // Compute (a / b) + (c * d^e) using helper methods so tests can spy on them
    const division = this.divide(a, b);
    const power = this.pow(d, e);
    const product = this.multiply(c, power);
    return this.add(division, product);
  }

  add(a: number, b: number): number {
    return a + b;
  }

  multiply(a: number, b: number): number {
    return a * b;
  }

  divide(a: number, b: number): number {
    return a / b;
  }

  pow(base: number, exponent: number): number {
    return Math.pow(base, exponent);
  }
}
