package application;

public class TransactionHandler {


	private double moneyProcessed;




	public TransactionHandler() {
		moneyProcessed = 0.0;
	}



	public void process(double amt) {
		moneyProcessed += amt;
	}


	public double getMoneyProcessed() {
		return moneyProcessed;
	}



}
