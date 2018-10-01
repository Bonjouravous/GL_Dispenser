package fr.uha.ensisa.gltd2.dispenser;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class DispenserTest {

	Drink testDrink;
	int testAmount;
	private Dispenser sut;

	@Before
	public void clearTmpVars() {
		testDrink = null;
		testAmount = 0;
	}

	@Before
	public void setupSut() {
		Provider dummyProvider = new Provider() {
			@Override
			public void giveDrink(Drink d) {
				testDrink = d;
			}
		};
		CashController dummyCashController = new CashController() {
			@Override
			public void givebackMoney(int amount) {
				testAmount = amount;
			}

		};
		this.sut = new Dispenser(dummyProvider, dummyCashController);
	}

	@Test
	public void firstDrinkGoodAmount() {
		List<Drink> listDrinks = sut.getDrinks();
		Drink coca = listDrinks.get(0);sut.pay(100);sut.select(coca);
	
		assertSame(coca, testDrink);
	}

	@Test
	public void firstDrinkLargeAmount() {
		List<Drink> listDrinks = sut.getDrinks();
		Drink coca = listDrinks.get(0);

		sut.pay(200);
		sut.select(coca);
		assertEquals(100,testAmount);
		assertSame(coca, testDrink);
	}

}
