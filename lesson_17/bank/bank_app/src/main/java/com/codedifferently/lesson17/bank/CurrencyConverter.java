public class CurrencyConverter {
    private double USD = 1.0;
    private double EUR = 1.18;
    private double AUD = 0.67;
    private double GBP = 1.36;
    private double IDR = 0.011;
    private double JPY = 0.0068;
    private double MEX = 0.055;
    private double CAD = 0.72;
    public double convertToUSD(double amount, String currencyType) {
        return switch (currencyType.toUpperCase()) {
            case "USD" -> amount * USD;
            case "EUR" -> amount * EUR;
            case "AUD" -> amount * AUD;
            case "GBP" -> amount * GBP;
            case "IDR" -> amount * IDR;
            case "JPY" -> amount * JPY;
            case "MEX" -> amount * MEX;
            case "CAD" -> amount * CAD;
            default -> throw new IllegalArgumentException("Unsupported currency type: " + currencyType);
        };
    }
}
