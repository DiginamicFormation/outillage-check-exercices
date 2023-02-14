package fr.diginamic.check;

import java.util.ArrayList;
import java.util.List;

public class Resultat {

	private static ArrayList<String> resultats = new ArrayList<>();
	
	public static void log(Object resultat)  {
		if (resultat!=null) {
			resultats.add(resultat.toString());
		}
		else {
			resultats.add(null);
		}
		System.out.println(resultat);
	}
	
	public static void log(boolean resultat)  {
		resultats.add(Boolean.toString(resultat));
		System.out.println(resultat);
	}
	
	public static void log(byte resultat)  {
		resultats.add(Byte.toString(resultat));
		System.out.println(resultat);
	}
	
	public static void log(short resultat)  {
		resultats.add(Short.toString(resultat));
		System.out.println(resultat);
	}
	
	public static void log(char resultat)  {
		resultats.add(Character.toString(resultat));
		System.out.println(resultat);
	}
	
	public static void log(int resultat)  {
		resultats.add(Integer.toString(resultat));
		System.out.println(resultat);
	}
	
	public static void log(long resultat)  {
		resultats.add(Long.toString(resultat));
		System.out.println(resultat);
	}
	
	public static void log(float resultat)  {
		resultats.add(Float.toString(resultat));
		System.out.println(resultat);
	}
	
	public static void log(double resultat)  {
		resultats.add(Double.toString(resultat));
		System.out.println(resultat);
	}
	
	public static List<String> getLogs(){
		return resultats;
	}
}
