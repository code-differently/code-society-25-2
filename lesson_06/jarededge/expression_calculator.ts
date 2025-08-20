export class ExpressionCalculator {
  /** Returns a calculation involving a, b, c, d, and e */
  calculate(a: number, b: number, c: number, d: number, e: number): number {
    // Implement your code here to return the correct value.
    const sum = c + d;

    const denominator = this.pow(sum, e);

    const numerator = a * b;

    return numerator / denominator;
  }

  pow(base: number, exponent: number): number {
    return Math.pow(base, exponent);
  }
}
