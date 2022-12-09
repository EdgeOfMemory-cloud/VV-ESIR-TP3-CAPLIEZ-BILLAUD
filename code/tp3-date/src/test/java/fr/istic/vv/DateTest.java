/**
 * @author Louis-Gabriel CAPLIEZ (EdgeOfMemory-cloud), Valere BILLAUD, ESIR 2 Spe INFO, option SI, Groupe 1
 * @date 20221109
 */

package fr.istic.vv;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class DateTest {

	// Tests de isLeapYear().

	// Multiples de 400.
	@Test
	public void isLeapYearTest1() {
		assertEquals(Date.isLeapYear(400), true);
	}

	// Multiples de 100 privés des multiples de 400.
	@Test
	public void isLeapYearTest2() {
		assertEquals(Date.isLeapYear(100), false);
	}

	// Multiples de 4 privés des multiples de 100 (et donc 400).
	@Test
	public void isLeapYearTest3() {
		assertEquals(Date.isLeapYear(4), true);
	}

	// Nombres quelconques qui ne sont pas multiples de 4.
	@Test
	public void isLeapYearTest4() {
		assertEquals(Date.isLeapYear(-1), false);
	}

	// Tests de la méthode isValidDate().

	// Le mois n'appartient pas à 1-12 ou le jour n'appartient pas a 1-31.
	@Test
	public void isValidDateTest1() {
		assertEquals(Date.isValidDate(-1, -1, 2000), false);
	}

	// Le mois n'appartient pas à 1-12 ou le jour n'appartient pas a 1-31.
	// Deuxème test pour respecter tous les tests de la fonction logique.
	@Test
	public void isValidDateTest9() {
		assertEquals(Date.isValidDate(32, 13, 2000), false);
	}

	// Le mois appartient a 1-3-5-7-8-10-12 et le jour appartient  a 1-31.
	@Test
	public void isValidDateTest2() {
		assertEquals(Date.isValidDate(1, 1, 2000), true);
	}

	// Le mois n'appartient pas a 1-3-5-7-8-10-12 et le jour = 31.
	@Test
	public void isValidDateTest3() {
		assertEquals(Date.isValidDate(31, 2, 2000), false);
	}

	// Le mois n'appartient pas a 1-2-3-5-7-8-10-12 et le jour appartient 1-30.
	@Test
	public void isValidDateTest4() {

		assertEquals(Date.isValidDate(30, 4, 2000), true);
	}

	// Le mois = 2 le jour = 30.
	@Test
	public void isValidDateTest5() {
		assertEquals(Date.isValidDate(30, 2, 2000), false);
	}

	// Le mois = 2 le jour appartient 1-29 et l'annee est bissextile.
	@Test
	public void isValidDateTest6() {
		assertEquals(Date.isValidDate(29, 2, 2000), true);
	}

	// Le mois = 2 le jour = 29 et l'annee n'est pas bissextile.
	@Test
	public void isValidDatevTest7() {
		assertEquals(Date.isValidDate(29, 2, 2001), false);
	}

	// Le mois = 2 le jour = 29 et l'annee n'est pas bissextile.
	@Test
	public void isValidDateTest8() {
		assertEquals(Date.isValidDate(28, 2, 2001), true);
	}

	// Tests de compareTo().
	
	@Test
	public void comparToTest1() {
		Date date = new Date(1, 1, 2001);
		assertEquals(date.compareTo(new Date(1,1, 2000)), 1);
	}

	@Test
	public void comparToTest2() {
		Date date = new Date(1, 1, 2001);
		assertEquals(date.compareTo(new Date(1,1, 2002)), -1);
	}

	@Test
	public void comparToTest3() {
		Date date = new Date(1, 2, 2001);
		assertEquals(date.compareTo(new Date(1,1, 2001)), 1);
	}

	@Test
	public void comparToTest4() {
		Date date = new Date(1, 1, 2001);
		assertEquals(date.compareTo(new Date(1,2, 2001)), -1);
	}

	@Test
	public void comparToTest5() {
		Date date = new Date(2, 1, 2001);
		assertEquals(date.compareTo(new Date(1,1, 2001)), 1);
	}

	@Test
	public void comparToTest6() {
		Date date = new Date(1, 1, 2001);
		assertEquals(date.compareTo(new Date(2,1, 2001)), -1);
	}

	@Test
	public void comparToTest7() {
		Date date = new Date(1, 1, 2001);
		assertEquals(date.compareTo(new Date(1,1, 2001)), 0);
	}

	// Tests de la methode nextDate().

	// Cas ou nous pouvons augmenter de 1 l'indice des jours.
	@Test
	public void nextDateTest1() {
		Date date = new Date(1, 1, 2001);
		Date res = new Date(2, 1, 2001);
		assertEquals(res.compareTo(date.nextDate()), 0);
	}

	// Cas ou nous ne pouvons pas augmenter de 1 l'indice des jours mais nous pouvons augmenter de 1 l'indice des mois.
	@Test
	public void nextDateTest2() {
		Date date = new Date(31, 1, 2001);
		Date res = new Date(1, 2, 2001);
		assertEquals(res.compareTo(date.nextDate()), 0);
	}

	// Cas ou nous ne pouvons pas augmenter de 1 l'indice des jours et des mois.
	@Test
	public void nextDateTest3() {
		Date date = new Date(31, 12, 2001);
		Date res = new Date(1, 1, 2002);
		assertEquals(res.compareTo(date.nextDate()), 0);
	}

	// Tests de la méthode previousDate.

	// L'indice des jours est superieur 2 strictement.
	@Test
	public void previousDateTest1() {
		Date date = new Date(2, 1, 2001);
		Date res = new Date(1, 1, 2001);
		assertEquals(res.compareTo(date.previousDate()), 0);
	}	

	// L'indice des jours = 1.
	// L'indice des mois appartient a 2-4-6-7-9-11.
	@Test
	public void previousDateTest2() {
		Date date = new Date(1, 2, 2001);
		Date res = new Date(31, 1, 2001);
		assertEquals(res.compareTo(date.previousDate()), 0);
	}

	// L'indice des jours = 1.
	// L'indice des mois appartient a 5-7-10-12.
	@Test
	public void previousDateTest3() {
		Date date = new Date(1, 5, 2001);
		Date res = new Date(30, 4, 2001);
		assertEquals(res.compareTo(date.previousDate()), 0);
	}

	// L'indice des jours = 1.
	// L'indice des mois appartient à 3.
	// L'annee est bissextile.
	@Test
	public void previousDateTest4() {
		Date date = new Date(1, 3, 2000);
		Date res = new Date(29, 2, 2000);
		assertEquals(res.compareTo(date.previousDate()), 0);
	}

	// L'indice des jours = 1.
	// L'indice des mois appartient à 3.
	// L'annee est bissextile.
	@Test
	public void previousDateTest5() {
		Date date = new Date(1, 3, 2001);
		Date res = new Date(28, 2, 2001);
		assertEquals(res.compareTo(date.previousDate()), 0);
	}

	// L'indice des jours = 1.
	// L'indice des mois appartient à 1.
	@Test
	public void previousDateTest6() {
		Date date = new Date(1, 1, 2001);
		Date res = new Date(31, 12, 2000);
		assertEquals(res.compareTo(date.previousDate()), 0);
	}

}