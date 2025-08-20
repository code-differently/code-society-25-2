export class ExpressionCalculator {
  /** Returns a calculation involving a, b, c, d, and e */
  calculate(a: number, b: number, c: number, d: number, e: number): number {
  //Math.pow(a + b, c) / (d * e)/ =
  return this.divide(this.pow(this.add(a, b), c), (this.multiply(d, e)));
  }

  pow(base: number, exponent: number): number {
    return Math.pow(base, exponent);
  }

  add(a: number, b: number): number {
    // const addition: number = a + b;
    return a + b;
  }

  multiply(d: number, e: number): number {
    // const multiplication: number = d + e;
    return d * e;
  }

  divide(a: number, b: number): number {
    // const multiplication: number = d + e;
    return a / b;
  }
}
