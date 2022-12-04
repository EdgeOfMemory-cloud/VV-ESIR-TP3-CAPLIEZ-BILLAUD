package fr.istic.vv;

import org.junit.jupiter.api.Test;

import static fr.istic.vv.StringUtils.isBalanced;
import static org.junit.jupiter.api.Assertions.*;

class StringUtilsTest {

	@Test
	public void testFloat() {
		assertEquals(3 * 0.4, 1.2, 0.1);
	}
	
	@Test
	public void testSameTrue() {
        String s1 = new String("Aegis");
        String s2 = s1;
        assertEquals(s1, s2); // Vaut true
    }
	
	@Test
	public void testSameFalse() {
        String s1 = new String("Aegis");
        String s2 = new String("Aegis");
        assertNotSame(s1, s2); // Ne passe pas car s1 ne référence pas le même objet que s1.
    } 

	@Test
    public void testEqualsStillTrue() {
        String s1 = new String("Aegis");
        String s2 = new String("Aegis");
        assertEquals(s1, s2); // Passe car le contenu de l'objet pointé par s1 est le même que celui pointé par s2.
    }
}