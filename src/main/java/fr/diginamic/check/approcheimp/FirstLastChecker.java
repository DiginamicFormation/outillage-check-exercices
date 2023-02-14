package fr.diginamic.check.approcheimp;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FirstLastChecker extends AbstractChecker {

	public void check1(Map<String, Object> fieldValues) {
		List<String> lignes = commonChecks();
		
		int[] tab1 = (int[])fieldValues.get("tab1");
		int[] tab2 = (int[])fieldValues.get("tab2");
		int[] tab3 = (int[])fieldValues.get("tab3");
		int[] tab4 = (int[])fieldValues.get("tab4");
		
		List<Boolean> result = new ArrayList<>();
		
		int[] tab = tab1;
		result.add(tab.length>=1 && tab[0]==tab[tab.length-1]);
		tab = tab2;
		result.add(tab.length>=1 && tab[0]==tab[tab.length-1]);
		tab = tab3;
		result.add(tab.length>=1 && tab[0]==tab[tab.length-1]);
		tab = tab4;
		result.add(tab.length>=1 && tab[0]==tab[tab.length-1]);
	
		assertTrue("Vous devez afficher 4 valeurs booléennes au total. Or vous affichez "+lignes.size()+" valeurs au lieu de "+result.size(), lignes.size()==result.size());
		
		for (int i=0; i<lignes.size(); i++) {
			assertTrue("La valeur n°"+(i+1)+" est fausse. Elle vaut "+lignes.get(i)+" au lieu de "+result.get(i), lignes.get(i).equals(Boolean.toString(result.get(i))));
		}
	}
}
