export class ExpressionCalculator {
  /** Returns a calculation involving a, b, c, d, and e */
  calculate(a: number, b: number, c: number, d: number, e: number): number {
    // Implement the expression: a * Math.pow(b + c, d) / e

    const divisor = (this.multiply(a, this.pow(this.add(b, c), d))) 
    return this.divide(divisor, e);
  }

  add(b: number, c: number): number {
    return b + c;
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
