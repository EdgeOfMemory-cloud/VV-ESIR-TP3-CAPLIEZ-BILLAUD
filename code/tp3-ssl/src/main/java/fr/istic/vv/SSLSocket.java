/**
 * @author Louis-Gabriel CAPLIEZ (EdgeOfMemory-cloud), Valere BILLAUD, ESIR 2 Spe INFO, option SI, Groupe 1
 * @date 20221109
 */

package fr.istic.vv;

public interface SSLSocket {

    public String[] getSupportedProtocols() ;

    public String[] getEnabledProtocols();

    public void setEnabledProtocols(String[] protocols);

}
