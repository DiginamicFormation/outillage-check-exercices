package fr.diginamic.check.approcheimp;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RotationChecker extends AbstractChecker {

	public void check1(Map<String, Object> fieldValues) {
		List<String> lignes = commonChecks();
		
		int[] array = (int[])fieldValues.get("array");
		
		List<Integer> result = new ArrayList<>();
		int rightValue = array[array.length-1];
		for (int i=array.length-1; i>0; i--) {
			array[i]=array[i-1];
		}
		array[0]=rightValue;
		
		for (int i=0; i<array.length; i++) {
			result.add(array[i]);
		}
	
		assertTrue("Vous devez afficher les valeurs du tableau après rotation. Or, vous affichez "+lignes.size()+" valeurs au lieu de "+result.size(), lignes.size()==result.size());
		
		for (int i=0; i<lignes.size(); i++) {
			assertTrue("La valeur n°"+(i+1)+" est fausse. Elle vaut "+lignes.get(i)+" au lieu de "+result.get(i), lignes.get(i).equals(Integer.toString(result.get(i))));
		}
	}
}
