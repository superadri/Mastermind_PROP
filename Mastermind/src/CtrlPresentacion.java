import javax.swing.*;
import java.io.IOException;
import java.awt.*;

public class CtrlPresentacion {
    private GameFactory gameFactory;

    private InitMaster initMaster;
    private NewGameQuestion questionRolDif;
    private JFrame frameP;
    private RulesView dialog1;
    boolean entry;
    private JFrame frameN;
    private JFrame rules;
    private JFrame frameQ;

    public CtrlPresentacion() throws IOException {
        gameFactory = new GameFactory();
    }
    public void initGame() throws IOException {
        entry = false;
        frameP = new JFrame("InitMaster");
        initMaster = new InitMaster(this);
        frameP.setContentPane(initMaster.PanelIni);
        frameP.setSize(400, 400);
        frameP.setLocationRelativeTo(null);
        frameP.setMinimumSize(new Dimension(200, 300));
        frameP.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameP.pack();
        frameP.setVisible(true);


        frameN = new JFrame("Choose");
        questionRolDif = new NewGameQuestion(this);
        frameN.setContentPane(questionRolDif.granPanel);
        frameN.setSize(400, 400);
        frameN.setLocationRelativeTo(null);
        frameN.setMinimumSize(new Dimension(100, 150));
        frameN.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameN.pack();

        frameQ = new JFrame("Rules");
        QuestionViewToContinue qV = new QuestionViewToContinue(this);
        frameQ.setContentPane(qV.questio);
        frameQ.setSize(400, 400);
        frameQ.setLocationRelativeTo(frameP);
        frameQ.setMinimumSize(new Dimension(75, 100));
        frameQ.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameQ.pack();

        rules = new JFrame("Rules");
        RulesView rV = new RulesView(this);
        rules.setContentPane(rV.rules);
        rules.setSize(400, 400);
        rules.setLocationRelativeTo(null);
        rules.setMinimumSize(new Dimension(75, 100));
        rules.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        rules.pack();
    }
    public void newGame() throws IOException{
        entry = false;
        frameP.setVisible(true);
    }


    public void sendUsername(String username) throws IOException{
        gameFactory.menu(this);
        gameFactory.setUsername(username);
    }

    public void userExits() {
        entry = true;
        frameQ.setVisible(true);
    }

    public void newgameView() {
        if (entry) frameQ.setVisible(false);
        frameP.setVisible(false);

        frameN.setVisible(true);
    }
    public void setnewgameView(String rol,String difficulty) throws IOException {
        if (rol.equals("CodeMaker")) rol = "CM";
        else if (rol.equals("CodeBreaker")) rol = "CB";
        else if (rol.equals("MachinevsMachine")) rol = "MACHINE";

        if (difficulty.equals("Hard")) difficulty = "HARD";
        else if (difficulty.equals("Medium")) difficulty = "MEDIUM";
        else if (difficulty.equals("Easy")) difficulty = "EASY";
        frameN.setVisible(false);

        gameFactory.newgame(rol,difficulty);
    }


    public void continueGameView() {
        if (entry) frameQ.setVisible(false);
        frameP.setVisible(false);
        try {
            gameFactory.continuegame();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void showRules() {
        frameP.setVisible(false);
        rules.setVisible(true);
    }

    public void hideRules() {
        rules.setVisible(false);
        frameP.setVisible(true);
    }
}
