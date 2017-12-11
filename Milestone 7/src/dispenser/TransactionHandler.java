package dispenser;

public class TransactionHandler {


	/**
	 * Jordan Riley
	 * This is my own work.
	 * CST-135
	 * 9/24/2017
	 *
	 *
	 */


	//member variable
	private double moneyProcessed;




	//default constructor
	public TransactionHandler() {
		moneyProcessed = 0.0;
	}


	//adds amt to moneyspent
	public void process(double amt) {
		moneyProcessed += amt;
	}

	//returns money spent
	public double getMoneyProcessed() {
		return moneyProcessed;
	}



}
