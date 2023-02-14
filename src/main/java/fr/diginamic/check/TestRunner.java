package fr.diginamic.check;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Assert;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.runner.Description;
import org.junit.runner.Runner;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.model.InitializationError;

public class TestRunner extends Runner  {
	
	private Class<?> testClass;
	
    public TestRunner(Class<?> testClass) throws InitializationError {
       	this.testClass = testClass;
    }
    
    @Override
    public Description getDescription() {
        return Description
          .createTestDescription(testClass, "My runner description");
    }
	
	@Override
    public void run(RunNotifier notifier) {
		
		SystemOutRule systemOutRule = new SystemOutRule();
		Method[] methods = testClass.getDeclaredMethods();
		Object instance = null;
		try {
			instance = testClass.getConstructor().newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e1) {
			throw new RuntimeException("Impossible d'exécuter votre exercice");
		}
		for (Method method: methods) {
			if (method.isAnnotationPresent(Question.class)) {
				Question question = method.getAnnotation(Question.class);
				try {
					notifier.fireTestStarted(Description
		                      .createTestDescription(testClass, method.getName()));
					method.invoke(instance);
					checkCode(systemOutRule, testClass.getSimpleName(), question.numero());
					systemOutRule.clearLog();
				} catch (IllegalAccessException e) {
					Assert.fail(e.getMessage());
				} catch (IllegalArgumentException e) {
					Assert.fail(e.getMessage());
				} catch (InvocationTargetException e) {
					Assert.fail(e.getMessage());
				}
			}
		}
	}

	protected final void checkCode(SystemOutRule systemOutRule, String exerciceName, int question) {
		
		String className = "fr.algorithmie.outils.checkers."+testClass.getSimpleName()+"Checker";
		String methodName = "check"+question;
		try {
			Class<?> classe = Class.forName(className);
			Object instance = classe.getConstructor().newInstance();
			Method check = classe.getMethod(methodName, String.class);
			check.invoke(instance, systemOutRule.getLog());
		} catch (ClassNotFoundException e) {
			Assert.fail("Vous avez supprimé la classe "+className+" ou réorganisé les packages. Merci d'effectuer un retour arrière afin de ne pas perturber le TP.");
		} catch (NoSuchMethodException e) {
			Assert.fail("Vous avez modifié le nom de la méthode "+methodName+" dans la classe "+className+". Merci d'effectuer un retour arrière afin de ne pas perturber le TP.");
		} catch (SecurityException | IllegalAccessException e) {
			Assert.fail("Vous avez modifié la visibilité de la méthode "+methodName+" dans la classe "+className+". Merci d'effectuer un retour arrière afin de ne pas perturber le TP.");
		} catch (IllegalArgumentException e) {
			Assert.fail("Vous avez modifié la signature de la méthode "+methodName+" dans la classe "+className+". Merci d'effectuer un retour arrière afin de ne pas perturber le TP.");
		} catch (InstantiationException e) {
			Assert.fail("Vous avez supprimé le constructeur par défaut de la classe "+className+". Merci d'effectuer un retour arrière afin de ne pas perturber le TP.");
		} catch (InvocationTargetException e) {
			// Affiche l'exception générée par l'assertion fausse.
			Assert.fail("Echec "+exerciceName+" - question n°"+question+" : "+e.getCause().getMessage());
		} 
	}
}
