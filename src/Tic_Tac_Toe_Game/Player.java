

package Tic_Tac_Toe_Game;


public abstract class Player {
    
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
    
    public abstract int play(String[] board,Player opponent,int turn);
    
    public static int isWin(int[] alignments){
        for (int i=0;i<8;i++){
            if (alignments[i]==3) return i;
        }
        return -1;
    }
    
    public void setAlignment(int pos,int add){
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
