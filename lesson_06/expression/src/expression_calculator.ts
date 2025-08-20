export class ExpressionCalculator {
  /** Returns a calculation involving a, b, c, d, and e */
  calculate(a: number, b: number, c: number, d: number, e: number): number {
    // Implement your code here to return the correct value.
    // return (Math.pow(a, b) + c) * d / e;
    return this.divide(this.multiply(d, this.add(c, this.pow(a, b))), e);
  }

  pow(base: number, exponent: number): number {
    return Math.pow(base, exponent);
  }

  add(a : number, b: number): number {
    return a + b;
  }
  subtract(a: number, b: number): number {
    return a - b;
  }
  multiply(a: number, b: number): number {
    return a * b;
  }  
  divide(a: number, b: number): number {
    return a / b;
  }

}
