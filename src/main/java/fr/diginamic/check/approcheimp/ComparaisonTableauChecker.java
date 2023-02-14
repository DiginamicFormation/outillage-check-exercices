package fr.diginamic.check.approcheimp;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ComparaisonTableauChecker extends AbstractChecker {

	public void check1(Map<String, Object> fieldValues) {
		List<String> lignes = commonChecks();
		
		int[] array1 = (int[])fieldValues.get("array1");
		int[] array2 = (int[])fieldValues.get("array2");
		
		List<Integer> result = new ArrayList<>();
		int cpt = 0;
		for (int i=0; i<array1.length; i++) {
			for (int j=0; j<array2.length; j++) {
				if (array1[i]==array2[j]) {
					cpt++;
				}
			}
		}
		result.add(cpt);
	
		assertTrue("Vous devez afficher une seule valeur. Vous affichez"+lignes.size()+" valeurs au lieu de "+result.size(), lignes.size()==result.size());
		
		assertTrue("Vous avez trouvé "+lignes.get(0)+" au lieu de "+result.get(0), lignes.get(0).equals(Integer.toString(result.get(0))));
	}
}
