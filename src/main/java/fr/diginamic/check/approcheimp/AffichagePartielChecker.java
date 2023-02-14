package fr.diginamic.check.approcheimp;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AffichagePartielChecker extends AbstractChecker {

	public void check1(Map<String, Object> fieldValues) {
		List<String> lignes = commonChecks();
		
		List<Integer> result = new ArrayList<>();
		int[] array = (int[])fieldValues.get("array");
		for (int i = 0; i < array.length; i++) {
			if (array[i] >= 3) {
				result.add(array[i]);
			}
		}
	
		assertTrue("Vous devez afficher les valeurs supérieures ou égales à 3. Or, vous affichez "+lignes.size()+" valeurs au lieu de "+result.size(), lignes.size()==result.size());
		
		for (int i=0; i<lignes.size(); i++) {
			assertTrue("La valeur n°"+(i+1)+" est fausse. Elle vaut "+lignes.get(i)+" au lieu de "+result.get(i), lignes.get(i).equals(Integer.toString(result.get(i))));
		}
	}
	
	public void check2(Map<String, Object> fieldValues) {
		List<String> lignes = commonChecks();
	
		List<Integer> result = new ArrayList<>();
		int[] array = (int[])fieldValues.get("array");
		for (int i = 0; i < array.length; i++) {
			if (array[i] %2== 0) {
				result.add(array[i]);
			}
		}
	
		assertTrue("Vous devez afficher les valeurs paires du tableau. Or, vous affichez "+lignes.size()+" valeurs au lieu de "+result.size(), lignes.size()==result.size());
		
		for (int i=0; i<lignes.size(); i++) {
			assertTrue("La valeur n°"+(i+1)+" est fausse. Elle vaut "+lignes.get(i)+" au lieu de "+result.get(i), lignes.get(i).equals(Integer.toString(result.get(i))));
		}
	}

	public void check3(Map<String, Object> fieldValues) {
		List<String> lignes = commonChecks();
		
		List<Integer> result = new ArrayList<>();
		int[] array = (int[])fieldValues.get("array");
		for (int i = 0; i < array.length; i++) {
			if (i%2 == 0) {
				result.add(array[i]);
			}
		}
	
		assertTrue("Vous devez afficher les valeurs correspondant aux index pairs du tableau. Or, vous affichez "+lignes.size()+" valeurs au lieu de "+result.size(), lignes.size()==result.size());
		
		for (int i=0; i<lignes.size(); i++) {
			assertTrue("La valeur n°"+(i+1)+" est fausse. Elle vaut "+lignes.get(i)+" au lieu de "+result.get(i), lignes.get(i).equals(Integer.toString(result.get(i))));
		}
	}
	
	public void check4(Map<String, Object> fieldValues) {
		List<String> lignes = commonChecks();
	
		List<Integer> result = new ArrayList<>();
		int[] array = (int[])fieldValues.get("array");
		for (int i = 0; i < array.length; i++) {
			if (array[i] %2== 1) {
				result.add(array[i]);
			}
		}
	
		assertTrue("Vous devez afficher les valeurs impaires du tableau. Or, vous affichez "+lignes.size()+" valeurs au lieu de "+result.size(), lignes.size()==result.size());
		
		for (int i=0; i<lignes.size(); i++) {
			assertTrue("La valeur n°"+(i+1)+" est fausse. Elle vaut "+lignes.get(i)+" au lieu de "+result.get(i), lignes.get(i).equals(Integer.toString(result.get(i))));
		}
	}
}
