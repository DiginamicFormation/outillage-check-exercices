package fr.diginamic.check.approcheimp;

import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;

public class InteractifChiffresSuivantsChecker extends AbstractChecker {
	
	@Override
	public String getJeuTest() {
		return "11";
	}

	public void check1(Map<String, Object> fieldValues) {
		List<String> lignes = commonChecks();
	
		assertTrue("Vous devez loguer 10 chiffres mais vous n'en affichez que "+lignes.size()+".", lignes.size()==10);
		
		String premiereLigne = lignes.get(0);
		
		int nb = Integer.parseInt(premiereLigne);
		for (int i=1; i<=10; i++) {
			int value = Integer.parseInt(lignes.get(i-1));
			assertTrue("La valeur affichée pour i="+i+" est fausse. Elle doit être égale à "+(nb+i-1), value==nb+i-1);
		}
	}
}
