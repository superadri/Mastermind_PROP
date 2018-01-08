# Prop_MasterMind
// Para visualizar y editar Diagrama -> https://www.draw.io/

// Download the project

    ```
	    https://drive.google.com/file/d/1RYI0ddH9cvWyZTsosOVA_PDMah0K66XC/view?usp=sharing
	``` 

// Si queremos hacer .jar:
    
    ```
	    jar cfm Mastermind_Orlando.jar manifest.mf *.class
	```    
	
// manifest.mf

    ```
		Manifest-version: 2.0
		Main-Class: GameFactory
	```
	
// New execution version: 2.0

	```
		javac Main.java
		java Main
	```
	
// Delete .class

	```
		find . -name "*.class" -type f -delete
	```

// Ejecutar .jar:

	```
		java -jar Mastermind_Orlando.jar
	```

// jUnit .jar: (Tienes que tener la clase a analizar Time.java, clase test, TimeTest.java, y ejecutor del test, TestRunner.java)

	```
		javac -cp .:junit-4.12.jar:hamcrest-core-1.3.jar TimeTest.java TestRunner.java
		java -cp .:junit-4.12.jar:hamcrest-core-1.3.jar TestRunner
	```	
