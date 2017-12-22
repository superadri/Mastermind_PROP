package domain;

import java.io.IOException;
import java.util.*;

public class Register {

    private  CtrlDominio controladorDominio;

    public HashMap<String, Player> listUsers;

    public Register(CtrlDominio controladorDominio) {
        this.controladorDominio = controladorDominio;
        listUsers = new HashMap<String, Player>();
        listUsers = controladorDominio.getListUsers();
    }

    public boolean user_exists(String username){ return listUsers.containsKey(username); }

    public Player getPlayer(String username){ return listUsers.get(username); }

    public boolean game_start_user(String username){ return listUsers.get(username).getGame_start() == '1'; }

    public void finished_game(String newNameUser) throws IOException {
        controladorDominio.finished_game(newNameUser,this);
    }

    public void set_continueGame(char game_start, String newNameUser, ArrayList<String> respuestas,double time,String codigo,String dificultat,String rol) throws IOException {
        controladorDominio.set_continueGame(game_start,newNameUser, respuestas, time, codigo, dificultat, rol);
    }

	/*
	public static void main(String[] args) {
		Register register = new Register();
	}
	*/
}
