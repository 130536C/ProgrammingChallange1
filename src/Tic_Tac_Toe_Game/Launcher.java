
package Tic_Tac_Toe_Game;

import gui.GameFrame;

public class Launcher {
    
    public static void main(String[] args){
        GameFrame g = new GameFrame();
        g.setResizable(false);
        g.setLocationRelativeTo(null);      // show the window in the middle of screen
        g.setVisible(true);
    }
    
}
