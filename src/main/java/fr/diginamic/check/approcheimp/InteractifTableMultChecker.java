package fr.diginamic.check.approcheimp;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;
import java.util.Map;

public class InteractifTableMultChecker extends AbstractChecker {
	
	@Override
	public String getJeuTest() {
		return "15 -8 9";
	}

	public void check1(Map<String, Object> fieldValues) {
		List<String> lignes = commonChecks();
	
		assertTrue("Vous devez loguer 10 résultats (table de 1 à 10) mais vous n'affichez que "+lignes.size()+" résultats.", lignes.size()==10);
		
		String premiereLigne = lignes.get(0);
		if (premiereLigne.indexOf("=")==-1 || premiereLigne.indexOf("*")==-1) {
			fail("Vous ne respectez pas le pattern d'affichage : n*1=n");
		}
		int nb = Integer.parseInt(premiereLigne.substring(premiereLigne.indexOf("=")+1).replaceAll(" " , ""));
		for (int i=1; i<=10; i++) {
			int value = Integer.parseInt(lignes.get(i-1).substring(lignes.get(i-1).indexOf("=")+1).replaceAll(" " , ""));
			assertTrue("La valeur affichée est fausse. Elle doit être égale à "+(nb*i), value==nb*i);
		}
	}
}
