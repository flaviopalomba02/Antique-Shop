package control;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import exception.OperationException;

public class GestioneAcquistoTest {
	/*
	@Test
	public void testRimuoviProdottoCarrello() throws OperationException {
		GestioneAcquisto.RimuoviProdottoCarrello("dario","1D");
		assertEquals(GestioneAcquisto.VisualizzaCarrello("dario").getProdotti().size(), 0);
	}

	@Test 
	public void testAggiungiProdottoCarrello()throws OperationException {
		
		GestioneAcquisto.AggiungiProdottoCarrello("dario","2D", 3);
		assertEquals(GestioneAcquisto.VisualizzaCarrello("dario").getProdotti().size(), 1);
	}
*/	
	@Test 
	public void testEffettuaOrdine() throws OperationException{
		
		GestioneAcquisto.EffettuaOrdine(Date.valueOf(LocalDate.now()),"dario");
		assertEquals(GestioneAcquisto.VisualizzaCarrello("dario").getProdotti().size(), 0);
	}
	

}
