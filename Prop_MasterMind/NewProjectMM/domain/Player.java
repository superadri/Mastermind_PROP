package domain;

import java.util.ArrayList;

public class Player {
    private char game_start;
    private ArrayList<String> respuestas = new ArrayList<String>();
    private double time;
    private String username, codigo, dificultat, rol;

    public Player(char game_start, String username) {
        this.game_start = game_start;
        this.username = username;
    }

     public Player(char game_start, String username, ArrayList<String> respuestas, double time, String codigo, String dificultat, String rol) {
        this.game_start = game_start;
        this.username = username;
        this.respuestas = respuestas;
        this.time = time;
        this.codigo = codigo;
        this.dificultat = dificultat;
        this.rol = rol;
    }

    public void setSaveGame(ArrayList<String> respuestas,double time,String codigo,String dificultat,String rol) {
        this.game_start = '1';
        this.respuestas = respuestas;
        this.time = time;
        this.codigo = codigo;
        this.dificultat = dificultat;
        this.rol = rol;
    }

    public void delete_all() {
        game_start = '0';
        respuestas = null;
        time = 0;
        codigo = null;
        rol = null;
        dificultat = null;
    }

    public ArrayList<String> getRespuestas() {
        return respuestas;
    }

    public double getTime() {
        return time;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getDificultat() {
        return dificultat;
    }

    public String getRol() {
        return rol;
    }

    public char getGame_start() {
        return game_start;
    }

    public String getUsername() {
        return username;
    }

    /*
        public static void main(String[] args) {
            Player player = new Player('y',"Juancho");
            System.out.println("Name: " + player.getUsername() + ", Start: " + player.getGame_start());
        }
	*/
}
