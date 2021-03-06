package domain;

import java.util.ArrayList;

public class Mastermind {

	private CtrlDominio controladorDominio;

		// Attribute
	public boolean gameSave;
	public boolean repetition;
	public int height, width, nLetters;
	private String difficulty;
	private String computerCM, computerCB;
	public Game game;
	public boolean controlFin;

	// Constructor
	public Mastermind(CtrlDominio controladorDominio, String computerCM, String computerCB, String difficulty) {
		System.out.println("Mastermind - NewGame");
		this.controladorDominio = controladorDominio;
		this.computerCM = computerCM;
		this.computerCB = computerCB;
		this.gameSave = false;
		this.difficulty = difficulty;
		setDataNextGame(difficulty);
		this.game = new Game(controladorDominio, this);
        this.game.startNewGame();
        this.controlFin = false;
    }

	public Mastermind(CtrlDominio controladorDominio, String computerCM, String computerCB, String difficulty, String[][] am) {
		System.out.println("Mastermind - NewGame M vs M");
		this.controladorDominio = controladorDominio;
		this.computerCM = computerCM;
		this.computerCB = computerCB;
		this.gameSave = false;
		this.difficulty = difficulty;
		setDataNextGame(difficulty);
		this.game = new Game(controladorDominio, this, am);
        this.game.startNewGame();
        this.controlFin = false;
    }

	public Mastermind(CtrlDominio controladorDominio, String computerCM, String computerCB, String difficulty, double time, String code, ArrayList<String> respuesta){
		System.out.println("Mastermind - ContinueGame");
		this.controladorDominio = controladorDominio;
		this.computerCM = computerCM;
		this.computerCB = computerCB;
		this.gameSave = false;
		this.difficulty = difficulty;
		setDataNextGame(difficulty);
		game = new Game(controladorDominio, this);
		String[] respuestaFinal = new String[respuesta.size()];
		for (int i = 0; i < respuesta.size(); ++i) { respuestaFinal[i] = respuesta.get(i); }
		game.continueGame(time, code, respuestaFinal);
	}
	
	public double getTime(){ return (this.game).getTime(); }

	public String getWhoisCM(){ return this.computerCM; }

	public String getWhoisCB(){ return this.computerCB; }

	public int getwidth(){ return this.width; }

	public int getheight(){ return this.height; }

	public int getnLetters(){ return this.nLetters; }

	public boolean getrepetition(){ return this.repetition; }

	private void setDataNextGame(String difficulty) {
		if ( difficulty.equals("EASY") ){
			this.repetition = false;
			this.nLetters = 6;
			this.height = 10;
			this.width = 4;
		} else if ( difficulty.equals("MEDIUM") ) {
			this.repetition = false;
			this.nLetters = 6;
			this.height = 7;
			this.width = 4;
		} else if ( difficulty.equals("HARD") ) {
			this.repetition = true;
			this.nLetters = 6;
			this.height = 6;
			this.width = 4;
		}
	}

	/*
		// Test Method
	public static void main(String[] args) {
		Mastermind mastermind = new Mastermind("1","1","EASY");
	}
	*/
}
