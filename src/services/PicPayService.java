package services;

public class PicPayService implements OnlinePaymentService{
	
	private static final double feePercentage = 0.05;
	private static final double monthlyInterest = 0.02;
	
	@Override
	public double paymentFee(double amount) {
		return amount * feePercentage;
	}
	
	@Override
	public double interest(double amount, int months) {
		return amount * monthlyInterest * months;
	}

}