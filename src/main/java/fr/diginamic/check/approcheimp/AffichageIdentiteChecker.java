package fr.diginamic.check.approcheimp;

import java.util.List;
import java.util.Map;

import org.junit.Assert;

public class AffichageIdentiteChecker extends AbstractChecker {

	public void check1(Map<String, Object> fieldValues) {
		List<String> lines = commonChecks();
	
		// Vérification de votre code
		Assert.assertTrue("Vous avez utilisé la boucle "+lines.size()+" fois au lieu de 10.", lines.size()==10);
	}

}
