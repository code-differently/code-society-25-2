export class ExpressionCalculator {
  /** Returns a calculation involving a, b, c, d, and e */
  calculate(a: number, b: number, c: number, d: number, e: number): number {
    // Implement your code here to return the correct value.

    const sum = this.add(a, b);

    const quotient = this.divide(sum, c);

    const powerResult = this.pow(quotient, d);

    const product = this.multiply(powerResult, e);

    return product;
  }

  add(a: number, b: number): number {
    return a + b;
  }

  divide(a: number, b: number): number {
    return a / b;
  }

  multiply(a: number, b: number): number {
    return a * b;
  }

  pow(base: number, exponent: number): number {
    return Math.pow(base, exponent);
  }
}
