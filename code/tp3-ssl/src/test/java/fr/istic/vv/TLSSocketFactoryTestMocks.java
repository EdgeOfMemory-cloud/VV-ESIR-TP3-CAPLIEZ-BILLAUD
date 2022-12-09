/**
 * @author Louis-Gabriel CAPLIEZ (EdgeOfMemory-cloud), Valere BILLAUD, ESIR 2 Spe INFO, option SI, Groupe 1
 * @date 20221109
 */

package fr.istic.vv;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TLSSocketFactoryTestMocks {

	
	@Test
	public void preparedSocket_NullProtocols_Mockito() {
		TLSSocketFactory f = new TLSSocketFactory();
		
		// Creation du mock
		SSLSocket mockSSLSocket = mock(SSLSocket.class);
		// Configuration type de retour non void
		when(mockSSLSocket.getEnabledProtocols()).thenReturn(null);
		when(mockSSLSocket.getSupportedProtocols()).thenReturn(null);
		// Configuration type de retour void. Implique lambda expression
		doAnswer((i) -> { // un parametre est requis semble-t-il, meme si inutilise
			fail();
			return null; // Obligatoire
		}).when(mockSSLSocket).setEnabledProtocols(any(String[].class));
		
		f.prepareSocket(mockSSLSocket);
	}
	
	
	@Test
	public void typical_Mockito() {
		TLSSocketFactory f = new TLSSocketFactory();
		
		// Creation du mock
		SSLSocket mockSSLSocket = mock(SSLSocket.class);
		
		// Configuration type de retour non void
		when(mockSSLSocket.getEnabledProtocols()).thenReturn(shuffle(new String[]{"SSLv2Hello", "SSLv3", "TLSv1", "TLSv1.1", "TLSv1.2"}));
		when(mockSSLSocket.getSupportedProtocols()).thenReturn(new String[]{"SSLv3", "TLSv1"});
		
		// Configuration type de retour void. Implique lambda expression
		doAnswer((i) -> { // un parametre est requis semble-t-il, meme si inutilise
			assertTrue(Arrays.equals(i.getArgument(0), new String[] {"TLSv1.2", "TLSv1.1", "TLSv1", "SSLv3" }));
			return null; // Obligatoire
		}).when(mockSSLSocket).setEnabledProtocols(new String[] {"TLSv1.2", "TLSv1.1", "TLSv1", "SSLv3" });
		f.prepareSocket(mockSSLSocket);
	}
	
	
	private String[] shuffle(String[] in) {
        List<String> list = new ArrayList<String>(Arrays.asList(in));
        Collections.shuffle(list);
        return list.toArray(new String[0]);
    }
	
}