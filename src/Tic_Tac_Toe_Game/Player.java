

package Tic_Tac_Toe_Game;


public abstract class Player {
    abstract void playTurn(int pl,int turn);
    void playerInit(){}
    void notifyWin(int pl){}
    void notifyLose(int pl){}
}
