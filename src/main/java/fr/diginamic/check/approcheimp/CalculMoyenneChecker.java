package fr.diginamic.check.approcheimp;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CalculMoyenneChecker extends AbstractChecker {

	public void check1(Map<String, Object> fieldValues) {
		List<String> lignes = commonChecks();
		
		int[] array = (int[])fieldValues.get("array");
		
		List<Double> result = new ArrayList<>();
		double sum = 0.0;
		for (int val:array) {
			sum+=val;
		}
		result.add(sum/array.length);
	
		assertTrue("Vous devez afficher une seule valeur. Vous affichez"+lignes.size()+" valeurs au lieu de "+result.size(), lignes.size()==result.size());
		
		assertTrue("Vous avez trouvé "+lignes.get(0)+" au lieu de "+result.get(0), lignes.get(0).equals(Double.toString(result.get(0))));
	}
	
	public void check2(Map<String, Object> fieldValues) {
		List<String> lignes = commonChecks();
		
		int[] array = (int[])fieldValues.get("array");
		
		List<Double> result = new ArrayList<>();
		double sum = 0.0;
		int compteur = 0;
		for (int i=0; i<array.length; i++) {
			if (array[i]>=0) {
				sum+=array[i];
				compteur++;
			}
		}
		result.add(sum/compteur);
	
		assertTrue("Vous devez afficher une seule valeur. Vous affichez"+lignes.size()+" valeurs au lieu de "+result.size(), lignes.size()==result.size());
		
		assertTrue("Vous avez trouvé "+lignes.get(0)+" au lieu de "+result.get(0), lignes.get(0).equals(Double.toString(result.get(0))));
	}
}
