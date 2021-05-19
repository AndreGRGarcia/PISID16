package nsei;


import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GUI {

    private static Migrator m;

    private JFrame frame;

    public static void main(String[] args) throws InterruptedException {
        new GUI();
        m = new Migrator();
    }

    public GUI(){
        init();
    }

    private void init(){
        frame = new JFrame("Migrator");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                m.closeApp();
            }
        });
    }

}
