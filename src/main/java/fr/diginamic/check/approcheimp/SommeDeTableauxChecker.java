package fr.diginamic.check.approcheimp;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SommeDeTableauxChecker extends AbstractChecker {

	public void check1(Map<String, Object> fieldValues) {
		List<String> lignes = commonChecks();
		
		int[] tab1 = (int[])fieldValues.get("tab1");
		int[] tab2 = (int[])fieldValues.get("tab2");
		
		List<Integer> result = new ArrayList<>();
		int[] somme = new int[tab1.length];
		for (int i=0; i<tab1.length; i++) {
			somme[i]=tab1[i]+tab2[i];
			result.add(somme[i]);
		}
	
		assertTrue("Vous devez afficher les valeurs du tableau résultant. Or, vous affichez "+lignes.size()+" valeurs au lieu de "+result.size(), lignes.size()==result.size());
		
		for (int i=0; i<lignes.size(); i++) {
			assertTrue("La valeur n°"+(i+1)+" est fausse. Elle vaut "+lignes.get(i)+" au lieu de "+result.get(i), lignes.get(i).equals(Integer.toString(result.get(i))));
		}
	}
	
	public void check2(String logs, Map<String, Object> fieldValues) {
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
