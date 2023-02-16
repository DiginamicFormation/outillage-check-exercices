# Etape 1 
Sur son compte créer un personel access token de type classic
# Etape 2

Dans C:/Users/MonNom/.m2 créer un fichier settings.xml 
```
  &lt;profiles&gt;
    &lt;profile&gt;
      &lt;id&gt;github&lt;/id&gt;
      &lt;repositories&gt;
        &lt;repository&gt;
          &lt;id&gt;central&lt;/id&gt;
          &lt;url&gt;https://repo1.maven.org/maven2&lt;/url&gt;
        &lt;/repository&gt;
        &lt;repository&gt;
          &lt;id&gt;outillage-check-exercices&lt;/id&gt;
          &lt;url&gt;https://maven.pkg.github.com/DiginamicFormation/outillage-check-exercices&lt;/url&gt;
          &lt;snapshots&gt;
            &lt;enabled&gt;true&lt;/enabled&gt;
          &lt;/snapshots&gt;
        &lt;/repository&gt;
      &lt;/repositories&gt;
    &lt;/profile&gt;
  &lt;/profiles&gt;
  
  &lt;servers&gt;
    &lt;server&gt;
        &lt;id&gt;outillage-check-exercices&lt;/id&gt;
        &lt;username&gt;monUser&lt;/username&gt;
        &lt;password&gt;monClassicToken&lt;/password&gt;
    &lt;/server&gt;
  &lt;/servers&gt;
```

# Etape 3
Commande de publication du package : 
mvn clean source:jar deploy -DuniqueVersion=false -Dmaven.source.useDefaultManifestFile=true -Dmaven.source.includePom=true -Dmaven.install.skip=true -DdeplyAtEnd=true -DaltDeploymentRepository='outillage-check-exercices::default::https://maven.pkg.github.com/DiginamicFormation/outillage-check-exercices'