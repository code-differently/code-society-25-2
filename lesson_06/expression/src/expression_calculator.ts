export class ExpressionCalculator {
  /** Adds two numbers */
  add(a: number, b: number): number {
    return a + b;
  }

  /** Divides two numbers */
  divide(a: number, b: number): number {
    if (b === 0) {
      throw new Error("Cannot divide by zero");
    }
    return a / b;
  }

  /** Multiplies two numbers */
  multiply(a: number, b: number): number {
    return a * b;
  }

  /** Raises a base to an exponent */
  pow(base: number, exponent: number): number {
    return Math.pow(base, exponent);
  }

  /** Returns a calculation involving a, b, c, d, and e */
  calculate(a: number, b: number, c: number, d: number, e: number): number {
    // Implement your code here to return the correct value.
    // (a * Math.pow(b + c, d)) / e,

    return this.divide(this.multiply(a, this.pow(this.add(b, c), d)), e);
  }
}
