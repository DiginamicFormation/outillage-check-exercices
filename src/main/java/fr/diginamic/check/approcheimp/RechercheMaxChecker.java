package fr.diginamic.check.approcheimp;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class RechercheMaxChecker extends AbstractChecker {

	public void check1(Map<String, Object> fieldValues) {
		List<String> lignes = commonChecks();
		
		int[] array = (int[])fieldValues.get("array");
		int max = Arrays.stream(array).max().getAsInt();
	
		assertTrue("Vous devez afficher une seule valeur. Vous affichez"+lignes.size()+" valeurs au lieu de "+1, lignes.size()==1);
		
		assertTrue("Vous avez trouvé "+lignes.get(0)+" au lieu de "+max, lignes.get(0).equals(Integer.toString(max)));
	}
}
