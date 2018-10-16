package fr.uha.ensisa.gltd2.dispenser;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DispenserTest {

	private Dispenser sut;
	private Provider dummyProvider;
	private CashController dummyCashController;

	@Before
	public void setupSut() {
		 dummyProvider = mock(Provider.class);
		dummyCashController = mock(CashController.class);
		this.sut = new Dispenser(dummyProvider, dummyCashController);
	}
	
	@After
	public void checkComplete() {
		verifyNoMoreInteractions(dummyCashController, dummyProvider);
	}

	@Test
	public void firstDrinkGoodAmount() {
		List<Drink> listDrinks = sut.getDrinks();
		Drink coca = listDrinks.get(0);sut.pay(100);sut.select(coca);
	
		verify(dummyProvider).giveDrink(coca);
		verify(dummyCashController, never()).givebackMoney(anyInt());
	}

	@Test
	public void firstDrinkLargeAmount() {
		List<Drink> listDrinks = sut.getDrinks();
		Drink coca = listDrinks.get(0);

		sut.pay(200);
		verifyZeroInteractions(dummyProvider);
		sut.select(coca);
		verify(dummyCashController).givebackMoney(100);
		verify(dummyProvider).giveDrink(coca);
	}

}
