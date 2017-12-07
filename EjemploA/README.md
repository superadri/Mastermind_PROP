# Prop_MasterMind
// Para visualizar y editar Diagrama -> https://www.draw.io/

// Si queremos hacer .jar:
	jar cfm Mastermind_Orlando.jar manifest.mf *.class    
	```
	// manifest.mf
		Manifest-version: 1.0
		Main-Class: GameFactory
	```

// Ejecutar .jar:
	```
		java -jar Mastermind_Orlando.jar
	```

// jUnit .jar:
	(Tienes que tener la clase a analizar Time.java, clase test, TimeTest.java, y ejecutor del test, TestRunner.java)
	```
		javac -cp .:junit-4.12.jar:hamcrest-core-1.3.jar TimeTest.java TestRunner.java
		java -cp .:junit-4.12.jar:hamcrest-core-1.3.jar TestRunner
	```
