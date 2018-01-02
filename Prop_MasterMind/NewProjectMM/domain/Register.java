package domain;

import persistence.CtrlPersistence;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Register {

    private  CtrlPersistence controladorPersistence;
    private  CtrlDominio controladorDominio;

    public HashMap<String, Player> listUsers;
    
    public Register(CtrlDominio controladorDominio, CtrlPersistence controladorPersistence) {
        this.controladorPersistence = controladorPersistence;
        this.controladorDominio = controladorDominio;
        listUsers = new HashMap<String, Player>();
        listUsers = controladorPersistence.getListUsers();
    }

    public boolean user_exists(String username){ return listUsers.containsKey(username); }

    public Player getPlayer(String username){ return listUsers.get(username); }

    public boolean game_start_user(String username){ return listUsers.get(username).getGame_start() == '1'; }

    public void finished_game(String newNameUser) throws IOException{
        controladorPersistence.finished_Game(newNameUser,this);
    }
     public void set_continueGame(char game_start, String newNameUser, ArrayList<String> respuestas,double time,String codigo,String dificultat,String rol) throws IOException{
        controladorPersistence.set_continueGame(game_start,newNameUser, respuestas,time,codigo,dificultat,rol,this);
    }

	/*
	public static void main(String[] args) {
		Register register = new Register();
	}
	*/
}
