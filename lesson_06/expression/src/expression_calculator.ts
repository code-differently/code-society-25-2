export class ExpressionCalculator {
  /** Returns a calculation involving a, b, c, d, and e */
  calculate(a: number, b: number, c: number, d: number, e: number): number {
  // Math.pow(a + b, c) / (d * e)  Implement your code here to return the correct value.
  
    const addition: number = this.add(a, b);
    const power: number = this.pow(addition, c);
    const multiplication: number = this.multiply(d, e);
    const combine: number = this.divide(power, multiplication);
    return combine;

    
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
