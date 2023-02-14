package fr.diginamic.check.approcheimp;

import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;

public class InteractifTantQueChecker extends AbstractChecker {
	
	@Override
	public String getJeuTest() {
		return "15 -8 9";
	}

	public void check1(Map<String, Object> fieldValues) {
		List<String> lignes = commonChecks();
	
		assertTrue("Il n'y a qu'une seule valeur à afficher, celle lorsque la boucle tant que s'arrête. Or, vous affichez "+lignes.size()+" valeurs au lieu de 1.", lignes.size()==1);
		
		int value = Integer.parseInt(lignes.get(0));
		assertTrue("La valeur affichée est fausse. Elle doit être égale à 9.", value>=0 && value<=9);
	}
}
