

package Tic_Tac_Toe_Game;


public abstract class Player {
    
    /* these varibles need to be inherited. So they are given protected access level. 
       allignment will store how many symbols, a player has played in a row. first three of this array for horizontal
       allignments. Next three are for vertical and last two for diagonal allignments.  */
    protected String name;
    protected String symbol;
    protected int[] alignments;
    
    public Player(String name, String symbol){
        alignments = new int[]{0,0,0,0,0,0,0,0};
        this.name = name;
        this.symbol = symbol;
    }
    
    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String Symbol) {
        this.symbol = Symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public abstract int play(int turn);
    
    public static int isWin(int[] alignments){
        /* if a player has played 3 symbols in a row, then he wins */
        for (int i=0;i<8;i++){
            if (alignments[i]==3) return i;     // return the allignment. it is needed to draw allignment on the canvas
        }
        return -1;  // not a win
    }
    
    public void resetAllignment(){
        // current allignments are cleared. called when user wants to play another round with same players
        for (int i=0;i<8;i++)
            alignments[i] = 0;
    }
    
    public void setAlignment(int pos,int add){
        // increase or decrease allignment according to played position. decreaseing is used to undo in minmax algorithm
        switch(pos){
            case 0:
                alignments[0] += add; alignments[3] += add; alignments[6] += add; break;
            case 1:
                alignments[1] += add; alignments[3] += add; break;
            case 2:
                alignments[2] += add; alignments[3] += add; alignments[7] += add; break;
            case 3:
                alignments[0] += add; alignments[4] += add; break;
            case 4:
                alignments[1] += add; alignments[4] += add; alignments[6] += add; alignments[7] += add; break;
            case 5:
                alignments[2] += add; alignments[4] += add; break;
            case 6:
                alignments[0] += add; alignments[5] += add; alignments[7] += add; break;
            case 7:
                alignments[1] += add; alignments[5] += add; break;
            case 8:
                alignments[2] += add; alignments[5] += add; alignments[6] += add; break;
        }
    }
    
    public int[] getAlignments() {
        return alignments;
    }
    
}
