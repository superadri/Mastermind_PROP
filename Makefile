build:
	@javac *.java
	@jar cfm Mastermind_Orlando.jar manifest.mf *.class
	java -jar Mastermind_Orlando.jar

clean:
	rm -rf *.class
