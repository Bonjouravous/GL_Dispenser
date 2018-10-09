package fr.uha.ensisa.gltd2.dispenser;

import java.util.Arrays;
import java.util.List;

public class Dispenser {
	
	private Provider provider;
	private CashController cashController;

	public Dispenser(Provider provider, CashController cashController) {
		this.provider = provider;
		this.cashController = cashController;
	}

	public List<Drink> getDrinks() {
		
		return Arrays.asList(new Drink());
	}

	public void pay(int amount) {
		if(amount>100) this.cashController.givebackMoney(amount-100);
	}

	public void select(Drink d) {
		provider.giveDrink(d);
		
	}

}
