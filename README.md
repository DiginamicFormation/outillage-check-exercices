1. Sur son compte créer un personel access token de type classic
2. Dans C:/Users/MonNom/.m2 créer un fichier settings.xml 
3. Commande de publication du package : 
mvn clean source:jar deploy -DuniqueVersion=false -Dmaven.source.useDefaultManifestFile=true -Dmaven.source.includePom=true -Dmaven.install.skip=true -DdeplyAtEnd=true -DaltDeploymentRepository='github::default::https://maven.pkg.github.com/DiginamicFormation/outillage-check-exercices'