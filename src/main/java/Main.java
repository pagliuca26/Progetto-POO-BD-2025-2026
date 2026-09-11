import controller.Controller;
import gui.Login;
import javax.swing.SwingUtilities;

public class Main {

    //punto di ingresso del programma
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Controller controller = new Controller();
                Login login = new Login(controller);
                login.mostraFinestra();
            }
        });
    }
}