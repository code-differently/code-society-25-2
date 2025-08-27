export class ExpressionCalculator {
  /** Returns a calculation involving a, b, c, d, and e */
  calculate(a: number, b: number, c: number, d: number, e: number): number {
    // Implement your code here to return the correct value.
    const sum = this.add(c, d);

    const denominator = this.pow(sum, e);

    const numerator = this.multiply(a, b);

    return this.divide(numerator, denominator);
  }
  add(a:number, b:number): number {
    return a + b;
  }
  multiply(a:number, b:number): number {
    return a * b;
  }
  divide(a:number, b:number): number {
    if(b === 0) {
      throw new Error("Can't divide by zero");
    } 
    return a / b;
  }

  pow(base: number, exponent: number): number {
    return Math.pow(base, exponent);
  }
}
