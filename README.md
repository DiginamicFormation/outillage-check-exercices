# Etape 1 
Sur son compte créer un personel access token de type classic
# Etape 2

Dans C:/Users/MonNom/.m2 créer un fichier **settings.xml** et insérer les informations suivantes : 
```xml
  <profiles>
    <profile>
      <id>github</id>
      <repositories>
        <repository>
          <id>central</id>
          <url>https://repo1.maven.org/maven2</url>
        </repository>
        <repository>
          <id>outillage-check-exercices</id>
          <url>https://maven.pkg.github.com/DiginamicFormation/outillage-check-exercices</url>
          <snapshots>
            <enabled>true</enabled>
          </snapshots>
        </repository>
      </repositories>
    </profile>
  </profiles>
  
  <servers>
    <server>
        <id>outillage-check-exercices</id>
        <username>**monUser**</username>
        <password>**monClassicToken**</password>
    </server>
  </servers>
```

Attention, remplacez **monUser** par votre user GitHub et **monClassicToken** par la valeur de votre token.

# Etape 3
Commande de publication du package : 
mvn clean source:jar deploy -DuniqueVersion=false -Dmaven.source.useDefaultManifestFile=true -Dmaven.source.includePom=true -Dmaven.install.skip=true -DdeplyAtEnd=true -DaltDeploymentRepository='outillage-check-exercices::default::https://maven.pkg.github.com/DiginamicFormation/outillage-check-exercices'