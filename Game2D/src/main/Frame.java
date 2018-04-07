package main;

import javax.swing.JFrame;

/**
 *
 * @author nhatt_000
 */
public class Frame extends JFrame {

    public Frame(){
        setTitle("Game Project");
//        setIconImage(GameMain.images.get(ImageLibrary.ICON));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setContentPane(new Panel());
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
