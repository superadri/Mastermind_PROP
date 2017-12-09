import javax.swing.*;
import java.io.IOException;
import java.awt.*;

public class CtrlPresentacion {
    private GameFactory gameFactory;

    private InitMaster initMaster;
    private NewGameQuestion questionRolDif;
    JFrame frame;
    ViewUserExits dialog;
    boolean entry;

    public CtrlPresentacion() throws IOException {
        gameFactory = new GameFactory();
    }
    public void initGame() throws IOException {
        entry = false;
        frame = new JFrame("InitMaster");
        initMaster = new InitMaster(this);
        frame.setContentPane(initMaster.PanelIni);
        frame.setSize(400, 400);
        frame.setLocationRelativeTo(null);
        frame.setMinimumSize(new Dimension(200, 300));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        entry = false;
    }

    public void sendUsername(String username) throws IOException{
        gameFactory.menu(this);
        gameFactory.setUsername(username);
    }

    public void userExits() {
        entry = true;
        dialog = new ViewUserExits(this);
        dialog.setLocationRelativeTo(frame);
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

    public void newgameView() {
        if (entry) dialog.setVisible(false);
        frame.setVisible(false);
        frame = new JFrame("Choose");
        questionRolDif = new NewGameQuestion(this);
        frame.setContentPane(questionRolDif.granPanel);
        frame.setSize(400, 400);
        frame.setLocationRelativeTo(null);
        frame.setMinimumSize(new Dimension(75, 200));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
    public void setnewgameView(String rol,String difficulty) throws IOException {
        if (rol.equals("CodeMaker")) rol = "CM";
        else if (rol.equals("CodeBreaker")) rol = "CB";
        else if (rol.equals("MachinevsMachine")) rol = "MACHINE";

        if (difficulty.equals("Hard")) difficulty = "HARD";
        else if (difficulty.equals("Medium")) difficulty = "MEDIUM";
        else if (difficulty.equals("Easy")) difficulty = "EASY";
        frame.setVisible(false);

        gameFactory.newgame(rol,difficulty);
    }


    public void continueGameView() {
        if (entry) dialog.setVisible(false);
        frame.setVisible(false);
        try {
            gameFactory.continuegame();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
