export class ExpressionCalculator {
  /** Returns a calculation involving a, b, c, d, and e */
  calculate(a: number, b: number, c: number, d: number, e: number): number {
    let intial_value:number = a*b;
    let second_value:number = this.pow(c+d,e);
    
    return intial_value/second_value;
  }

  pow(base: number, exponent: number): number {
    return Math.pow(base, exponent);
  }
}
