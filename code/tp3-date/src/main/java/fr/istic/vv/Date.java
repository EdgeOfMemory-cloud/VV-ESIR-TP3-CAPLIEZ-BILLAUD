/**
 * @author Louis-Gabriel CAPLIEZ (EdgeOfMemory-cloud), Valere BILLAUD, ESIR 2 Spe INFO, option SI, Groupe 1
 * @date 20221109
 */

package fr.istic.vv;

class Date implements Comparable<Date> {
	Throwable exeption =  new Throwable("Date invaldie");
	
	private int day;
	private int month;
	private int year;
	

    public int getDay() {
		return day;
	}

	public int getMonth() {
		return month;
	}

	public int getYear() {
		return year;
	}
	
	//Constructeur de la classe Date renvoie une exepetion si la date n'est pas valide
	public Date(int day, int month, int year)  { 
		if (isValidDate(day, month, year)) {
			this.day = day;
			this.month = month;
			this.year = year;
		} else {
			//throw new Exception("Date invaldie");
		}
	}
	
	
	// Methode privé qui renvoie true si l'élèment en entrée appartient à {1,3,5,7,8,10,12}
	private static boolean appM31(int mois) {
		int[] M31= {1,3,5,7,8,10,12};
		for(int x : M31) {
			if (x == mois) {
				return true;
			}
		}
		return false ;
		
	}
	// Methode privé qui renvoie true si l'élèment en entrée appartient à {4,6,9,11}
		private static boolean appM30(int mois) {
			int[] M30= {4,6,9,11};
			for(int x : M30) {
				if (x == mois) {
					return true;
				}
			}
			return false ;
			
		}
	
	
	// Methode qui renvoie true si la date suit les normes suivante 
	// Années suit les normes  ISO 8601 (L'an zero exist) donc year app Z (int)
	// Mois suit les normes ISO 8601 1 - 12 
	// Jours suit les normes ISO 8601 1 -31 ou 1-30 ou 1-(28.29) pour fevrier
    public static boolean isValidDate(int day, int month, int year) { 
    	if(month < 13 && month > 0 && day > 0 && day < 32) { 
    		if (appM31(month)) {
    			return true ;    		
    		} else {
    			if (day < 31 ) {
    				if (! (month ==2)) {
    					return true ;
    				} else {
    					if (day <30) {
    					   	if (! isLeapYear(year)) {
    					   		return day <29;
    					   	} else {
    					   		return true;
    					   	}
    					} else {
    						return false ;
    					}
    				}
    			} else {
    				return false;
    			}
    				
    		}	
    	} else {
    		return false;
    	}
    }
    
    // Méthode qui renvoie true l'années est bisséxtile 
    public static boolean isLeapYear(int year) {
    	if (year % 400 == 0) {
    		return true ;
    	}
    	if  ( year % 100 == 0) {
    		return false ;
    	}
    	if (year % 4 == 0) {
    		return true;
    	} else {
    		return false;
    	}
    }

    // Méthode qui renvoie la prochaine Date valide
    public Date nextDate() { 
    	if (isValidDate(day +1, month, year)) {
    		return new Date(day +1, month, year);
    	} 
    	if (isValidDate(1, month +1, year)) {
    		return new Date(1, month+1, year);
    	} 
    	return new Date(1, 1, year+1);
    
    
    }

    //Méthode qui renvoie la précédente date valide
    public Date previousDate() { 
    	if (isValidDate(day -1, month, year)) {
    		return new Date(day -1, month, year);
    	} 
    	if (appM31(month-1)) {
    		return new Date(31, month-1, year);
    	}
    	if (appM30(month-1)) {
    		return new Date(30, month-1, year);
    	}
    	if (month == 3) {
    		if( isLeapYear(year)) {
    			return new Date(29, month -1, year);
    		} else {
    			return new Date(28, month -1, year);
    		}
    	}
    	return new Date(31, 12, year -1);
    	
    }

    //Méthode qui renvoir un nombre positif si Date > other 
    // négatif si Date < other 
    // 0 sir date == other
    public int compareTo(Date other) { 
    	if (year > other.getYear()) {
    		return 1;
    	}
    	if (year < other.getYear()) {
    		return -1;
    	}
    	if (month > other.getMonth()) {
    		return 1;
    	}
    	if (month < other.getMonth()) {
    		return -1;
    	}
    	if (day > other.getDay()) {
    		return 1;
    	}
    	if (day < other.getDay()) {
    		return -1;
    	}
    	return 0;
    	
    }

}