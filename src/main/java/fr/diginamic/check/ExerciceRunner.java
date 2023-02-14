package fr.diginamic.check;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;

import org.junit.Ignore;
import org.junit.internal.AssumptionViolatedException;
import org.junit.internal.runners.model.EachTestNotifier;
import org.junit.rules.RunRules;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;

import fr.diginamic.check.approcheimp.AbstractChecker;

public class ExerciceRunner extends BlockJUnit4ClassRunner  {
	
	private String exoName;
	private boolean test;
	
    public ExerciceRunner(Class<?> testClass) throws InitializationError {
        super(testClass);
        ResourceBundle config = ResourceBundle.getBundle("application");
        test = Boolean.parseBoolean(config.getString("test"));

        if (testClass.getAnnotation(Exercice.class)!=null) {
        	exoName = testClass.getAnnotation(Exercice.class).nom();
        }
        else {
        	throw new InitializationError("Echec. La classe d'exercice "+testClass.getSimpleName()+" n'a pas l'annotation @Exercice");
        }
    }
	
	@Override
    public void runChild(final FrameworkMethod method, RunNotifier notifier) {
		
		Description description = Description.createTestDescription(getTestClass().getJavaClass(), method.getName());
		Resultat.getLogs().clear();
        if (method.getAnnotation(Ignore.class) != null) {
            notifier.fireTestIgnored(description);
        } else {
        	if (method.getAnnotation(Question.class)!=null) {
        		
        		Question question = method.getAnnotation(Question.class);
        		
        		try {
					AbstractChecker checker = getChecker();
					
					if (checker.getJeuTest()!=null && test) {
	        	   		InputStream in = new ByteArrayInputStream(checker.getJeuTest().getBytes());
	        	    	System.setIn(in);
					}
	        		
					RunRules runRules = new RunRules(methodBlock(method), Arrays.asList(new TestRule[]{}), description);

					// Fix - runLeaf
					// pour éviter qu'il marque le test comme terminé avant la vérification du code

					// runLeaf(runRules, description, notifier);

					// Code de runLeaf ci-dessous sans la fin du test
					EachTestNotifier eachNotifier = new EachTestNotifier(notifier, description);
					eachNotifier.fireTestStarted();
					try {
						runRules.evaluate();
					} catch (AssumptionViolatedException e) {
						eachNotifier.addFailedAssumption(e);
					} catch (Throwable e) {
						eachNotifier.addFailure(e);
					}
	        		
	        		Optional<Failure> optFailure = checkCode(description, getTestClass().getName(), question.numero());
	        		if (optFailure.isPresent()) {
	        			notifier.fireTestFailure(optFailure.get());
	        		}

					// marquer la fin du test
					eachNotifier.fireTestFinished();
	        		
				} catch (ReflectiveOperationException e) {
					notifier.fireTestFailure(new Failure(description, new RuntimeException("Echec "+exoName+" - question n°"+question+" : "+e.getCause().getMessage())));
				}
        		
        		
        	}
        }
	}

	protected final Optional<Failure> checkCode(Description description, String exerciceName, int question) {
		
		String className = "outils.checkers."+getTestClass().getJavaClass().getSimpleName()+"Checker";
		String methodName = "check"+question;
		try {
			Object testClassInstance = getTestClass().getOnlyConstructor().newInstance();
			Field[] fields = getTestClass().getJavaClass().getDeclaredFields();
			Map<String, Object> fieldValues = new HashMap<>();
			for (int i=0; i<fields.length; i++) {
				fields[i].setAccessible(true);
				fieldValues.put(fields[i].getName(), fields[i].get(testClassInstance));
			}
			
			AbstractChecker checker = getChecker();
			Method check = checker.getClass().getMethod(methodName, Map.class);
			check.invoke(checker, fieldValues);
			return Optional.empty();
		} catch (ClassNotFoundException e) {
			return Optional.of(new Failure(description, new RuntimeException("Vous avez supprimé la classe "+className+" ou réorganisé les packages. Merci d'effectuer un retour arrière afin de ne pas perturber le TP.")));
		} catch (NoSuchMethodException e) {
			return Optional.of(new Failure(description, new RuntimeException("Vous avez modifié le nom de la méthode "+methodName+" dans la classe "+className+". Merci d'effectuer un retour arrière afin de ne pas perturber le TP.")));
		} catch (SecurityException | IllegalAccessException e) {
			return Optional.of(new Failure(description, new RuntimeException("Vous avez modifié la visibilité de la méthode "+methodName+" dans la classe "+className+". Merci d'effectuer un retour arrière afin de ne pas perturber le TP.")));
		} catch (IllegalArgumentException e) {
			return Optional.of(new Failure(description, new RuntimeException("Vous avez modifié la signature de la méthode "+methodName+" dans la classe "+className+". Merci d'effectuer un retour arrière afin de ne pas perturber le TP.")));
		} catch (InstantiationException e) {
			return Optional.of(new Failure(description, new RuntimeException("Vous avez supprimé le constructeur par défaut de la classe "+className+". Merci d'effectuer un retour arrière afin de ne pas perturber le TP.")));
		} catch (ReflectiveOperationException e) {
			return Optional.of(new Failure(description, new RuntimeException("Echec "+exerciceName+" - question n°"+question+" : "+e.getCause().getMessage())));
		} 
	}
	
	public AbstractChecker getChecker() throws ReflectiveOperationException {
		String className = "outils.checkers."+exoName+"Checker";
		
		Class<?> checkerClass = Class.forName(className);
		Object checkerInstance = checkerClass.getConstructor().newInstance();
		return (AbstractChecker)checkerInstance;
	}
}
