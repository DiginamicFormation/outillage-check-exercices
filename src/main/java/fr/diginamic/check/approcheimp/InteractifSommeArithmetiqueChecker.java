package fr.diginamic.check.approcheimp;

import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;

public class InteractifSommeArithmetiqueChecker extends AbstractChecker {
	
	@Override
	public String getJeuTest() {
		return "9";
	}

	public void check1(Map<String, Object> fieldValues) {
		List<String> lignes = commonChecks();
	
		assertTrue("Vous devez loguer 2 résultats (le nombre et la somme) mais vous en affichez "+lignes.size(), lignes.size()==2);
		
		int nb = Integer.parseInt(lignes.get(0));
		int value = Integer.parseInt(lignes.get(1));
		
		int somme = 0;
		for (int i=1; i<=nb; i++) {
			somme+=i;
		}
		assertTrue("Pour cette somme vous devriez afficher "+somme, somme==value);
	}
}
