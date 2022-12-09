/**
 * @author Louis-Gabriel CAPLIEZ (EdgeOfMemory-cloud), Valere BILLAUD, ESIR 2 Spe INFO, option SI, Groupe 1
 * @date 20221109
 */

package fr.istic.vv;

import org.junit.jupiter.api.Test;

import static fr.istic.vv.StringUtils.isBalanced;
import static org.junit.jupiter.api.Assertions.*;

class StringUtilsTest {

	@Test
	public void pasMemeNbOuvrantFermant() {
		String dataToTest= "(()))";
		assertEquals(isBalanced(dataToTest), false);
	}
	
	
	// Ajoute pour instruction manquee.
	@Test
	public void crochFerm(){
		String dataToTest= "]";
		assertEquals(isBalanced(dataToTest), false);
	}
	
	// Ajoute pour instruction manquee.
	@Test
	public void accoladeFerm(){
		String dataToTest= "}";
		assertEquals(isBalanced(dataToTest), false);
	}
	
	@Test
	public void fermantAuDebut(){
		String dataToTest= ")(()";
		assertEquals(isBalanced(dataToTest), false);
	}
	
	@Test
	public void ouvrantALaFin(){
		String dataToTest= "())(";
		assertEquals(isBalanced(dataToTest), false);
	}
	
	@Test
	public void ouvrantEtFermantDifferent(){
		String dataToTest= "({][}}";
		assertEquals(isBalanced(dataToTest), false);
	}
	
	@Test
	public void chaineEquilibree(){
		String dataToTest= "([]{()}[])";
		assertEquals(isBalanced(dataToTest), true);
	}
	
	// Ajoute pour branche manquee.
	@Test
	public void test1(){
		String dataToTest= "(e)";
		assertEquals(isBalanced(dataToTest), true);
	}
	
	// Ajoute pour branche manquee.
	@Test
	public void test2(){
		String dataToTest= "[e)";
		assertEquals(isBalanced(dataToTest), false);
	}

	
}
