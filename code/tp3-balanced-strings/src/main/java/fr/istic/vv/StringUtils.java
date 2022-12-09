/**
 * @author Louis-Gabriel CAPLIEZ (EdgeOfMemory-cloud), Valere BILLAUD, ESIR 2 Spe INFO, option SI, Groupe 1
 * @date 20221109
 */

package fr.istic.vv;


import java.util.Stack;

public class StringUtils {
	
    private StringUtils() {}
    
    

    public static boolean isBalanced(String str) {
    	
    	Stack<Character> stringsOpen = new Stack<>(); 
    	for(int x = 0 ; x <str.length() ; x++) {
    		char courant = str.charAt(x);
    		switch (courant) {
    		case '(' : 
    			stringsOpen.push(courant);
    			break;
    		case '[' : 
    			stringsOpen.push(courant);
    			break;
    		case '{' : 
    			stringsOpen.push(courant);
    			break;
    		case '}' : 
    			if (stringsOpen.isEmpty()) {
    				return false;
    			}
    			if (stringsOpen.peek().equals('{') ) {
    				stringsOpen.pop();
    			}
    			break;
    		case ']' : 
    			if (stringsOpen.isEmpty()) {
    				return false;
    			}
    			if (stringsOpen.peek().equals('[') ) {
    				stringsOpen.pop();
    			}
    			break;
    		case ')' : 
    			if (stringsOpen.isEmpty()) {
    				return false;
    			}
    			if ( stringsOpen.peek().equals('(') ) {
    				stringsOpen.pop();
    			}
    			break;
    		default :
    			break;
    		
    		}
    		
    	}
    	if (stringsOpen.isEmpty()) {
			return true;
		} else {
			return false;
		}
    }

}
