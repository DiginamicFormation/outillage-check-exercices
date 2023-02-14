package fr.diginamic.check.approcheimp;

import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;

public class InteractifPlusGrandChecker extends AbstractChecker {
	
	@Override
	public String getJeuTest() {
		return "15 -2 7 18 3 0 14 15 78 1";
	}

	public void check1(Map<String, Object> fieldValues) {
		List<String> lignes = commonChecks();
	
		assertTrue("Vous devez loguer 11 résultats (les 10 entiers + le max) mais vous en avez logué "+lignes.size()+".", lignes.size()==11);
		int[] array = new int[10];
		int max = Integer.MIN_VALUE;
		for (int i=0; i<array.length; i++) {
			array[i]=Integer.parseInt(lignes.get(i));
			if (array[i]>max) {
				max = array[i];
			}
		}
		int maxFromLogs = Integer.parseInt(lignes.get(10));
		assertTrue("Vous n'avez pas trouvé le bon max. Il doit valoir "+max+".", maxFromLogs==max);
	}
}
