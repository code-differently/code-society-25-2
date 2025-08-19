export class ExpressionCalculator {
  /** Returns a calculation involving a, b, c, d, and e */
  calculate(a: number, b: number, c: number, d: number, e: number): number {
    // Implement your code here to return the correct value.
    return this.add(this.divide(a, b), this.multiply(c, this.pow(d, e)));
  }

  pow(base: number, exponent: number): number {
    return Math.pow(base, exponent);
  }

  multiply(a: number, b: number): number {
    return a * b;
  }

  add(a: number, b: number): number {
    return a + b;
  }

  divide(a: number, b: number): number {
    return a / b;
  }
}
