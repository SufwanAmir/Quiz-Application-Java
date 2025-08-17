import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Quiz q = new Quiz();
        q.setSize(600,400);
        q.setTitle("Quiz Application");
        q.setVisible(true);
        q.setLocationRelativeTo(null);
        q.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}