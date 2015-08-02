
package gui;

import Tic_Tac_Toe_Game.Computer;
import Tic_Tac_Toe_Game.Human;
import Tic_Tac_Toe_Game.Player;
import io.*;
import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.apache.log4j.Logger;

public class Board extends Canvas implements MouseListener{
    
    private int position;
    private int boundryX;
    private int boundryY;
    private String[] moves;
    private Player[] player;
    private int turn = 0,currentPlayer;
    private boolean win,draw,netMyTurn;
    private GameFrame game;
    private DBHandler dbHandler;
    private NetworkHandler networkHandler;
    
    private static Logger logger = Logger.getLogger(Board.class);
    
    public Board(GameFrame game,Player player1, Player player2,DBHandler dbHandler){
        moves = new String[]{"","","","","","","","",""};
        player = new Player[]{player1,player2};
        win = false; draw = false;
        this.netMyTurn = false;
        this.game = game;
        this.dbHandler = dbHandler;
        this.setIgnoreRepaint(true);
    }
    
    public void drawBoard(){
        BufferedImage bg = null;
        try {
            bg = ImageIO.read(getClass().getResourceAsStream("/Resources/background.jpg"));
        } catch (IOException ex) {
            logger.error("Cannot read background image",ex);
        }
        Graphics2D g = (Graphics2D) this.getGraphics();
        g.drawImage(bg, 0, 0, this);
        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }
    
    private void drawX(){
        for (int i=20;i<=80;i++){
            int j = i;
            Graphics2D g = (Graphics2D) this.getGraphics();
            g.setColor(Color.BLUE);
            g.fillOval(i+boundryX, j+boundryY, 5, 5);
            try {
                Thread.sleep(1);
            } catch (InterruptedException ex) {
                logger.debug("Thread interrupted",ex);
            }
            Toolkit.getDefaultToolkit().sync();
            g.dispose();
        }
        try {
            Thread.sleep(2);
        } catch (InterruptedException ex) {
            logger.debug("Thread interrupted",ex);
        }
        for (int i=80;i>=20;i--){
            int j = 100-i;
            Graphics2D g = (Graphics2D) this.getGraphics();
            g.setColor(Color.BLUE);
            g.fillOval(i+boundryX, j+boundryY, 5, 5);
            try {
                Thread.sleep(2);
            } catch (InterruptedException ex) {
                logger.debug("Thread interrupted",ex);
            }
            Toolkit.getDefaultToolkit().sync();
            g.dispose();
        }
    }
    
    private void drawO(){
        Graphics2D g = (Graphics2D) this.getGraphics();
        g.setColor(Color.BLUE);
        g.setStroke(new BasicStroke(3));
        g.drawOval(boundryX+20,boundryY+20 , 60, 60);
        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }
    
    private void drawAlignment(int alignment){
        if (alignment < 3){
            int x = 50,boundryX = 100*alignment;
            for (int y = 10; y < 290; y++){
                Graphics2D g = (Graphics2D) this.getGraphics();
                g.setColor(Color.RED);
                g.fillOval(x+boundryX, y, 5, 5);
                try {
                    Thread.sleep(1);
                } catch (InterruptedException ex) {
                    logger.debug("Thread interrupted",ex);
                }
                Toolkit.getDefaultToolkit().sync();
                g.dispose();
            }
        }
        else if (alignment < 6){
            alignment -= 3;
            int y = 50,boundryY = 100*alignment;
            for (int x = 10; x < 290; x++){
                Graphics2D g = (Graphics2D) this.getGraphics();
                g.setColor(Color.RED);
                g.fillOval(x, y+boundryY, 5, 5);
                try {
                    Thread.sleep(1);
                } catch (InterruptedException ex) {
                    logger.debug("Thread interrupted",ex);
                }
                Toolkit.getDefaultToolkit().sync();
                g.dispose();
            }
        }
        else if (alignment == 6){
            for (int x = 10; x < 290; x++){
                int y = x;
                Graphics2D g = (Graphics2D) this.getGraphics();
                g.setColor(Color.RED);
                g.fillOval(x-2, y, 5, 5);
                try {
                    Thread.sleep(1);
                } catch (InterruptedException ex) {
                    logger.debug("Thread interrupted",ex);
                }
                Toolkit.getDefaultToolkit().sync();
                g.dispose();
            }
        }
        else{
            for (int x = 290; x >= 10; x--){
                int y = 300-x;
                Graphics2D g = (Graphics2D) this.getGraphics();
                g.setColor(Color.RED);
                g.fillOval(x-2, y, 5, 5);
                try {
                    Thread.sleep(1);
                } catch (InterruptedException ex) {
                    logger.debug("Thread interrupted",ex);
                }
                Toolkit.getDefaultToolkit().sync();
                g.dispose();
            }
        }
    }
    
    private void setPosition(int x, int y) {
        if (x<100 && y<100){position = 0; }
        else if(x<200 && y <100){position = 1; }
        else if(x<300 && y <100){position = 2; }
        else if(x<100 && y <200){position = 3; }
        else if(x<200 && y <200){position = 4; }
        else if(x<300 && y <200){position = 5; }
        else if(x<100 && y <300){position = 6; }
        else if(x<200 && y <300){position = 7; }
        else{position = 8;}
        setBoundries(position);
    }
    
    public void playComputerFirst(){
        Computer computer = (Computer) player[turn%2];
        position = computer.play(moves, player[(turn+1)%2], turn);
        setBoundries(position);
        mouseClicked(null);
    }
    
    private void setBoundries(int position){
        switch(position){
            case 0:
                boundryX=0; boundryY=0; break;
            case 1:
                boundryX=100; boundryY=0; break;
            case 2:
                boundryX=200; boundryY=0; break;
            case 3: 
                boundryX=0; boundryY=100; break;
            case 4:
                boundryX=100; boundryY=100; break;
            case 5:
                boundryX=200; boundryY=100; break;
            case 6:
                boundryX=0; boundryY=200; break;
            case 7:
                boundryX=100; boundryY=200; break;
            case 8: 
                boundryX=200; boundryY=200; break;
        }
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        if (!win && !draw){
            currentPlayer = turn%2;
            if ((game.gameMode>0 && !player[currentPlayer].getName().equals("Computer")) || netMyTurn){
                setPosition(e.getX(),e.getY());
            }
            if (moves[position].equals("")){
                if (netMyTurn){
                    networkHandler.sendMove(position);
                    new Thread(){
                        public void run(){
                            networkHandler.recieveMove();
                        }
                    }.start();
                    netMyTurn = false;
                    System.out.println("move sent");
                }
                if (currentPlayer==0){
                    drawX();
                    game.lblStatus.setText(player[1-currentPlayer].getName()+"'s turn - "+player[1-currentPlayer].getSymbol());
                }
                else{
                    drawO();
                    game.lblStatus.setText(player[1-currentPlayer].getName()+"'s turn - "+player[1-currentPlayer].getSymbol());
                }
                moves[position] = player[currentPlayer].getSymbol();
                if (!player[currentPlayer].getName().equals("Computer")){
                    Human human = (Human) player[currentPlayer];
                    human.play(position);
                }
                int alignment = Player.isWin(player[currentPlayer].getAlignments());
                if (alignment!=-1){
                    win = true;
                    game.lblStatus.setText(player[currentPlayer].getName()+" wins!");
                    drawAlignment(alignment);
                    game.btnNextRound.setEnabled(true);
                    if (currentPlayer==0){
                        game.player1winStat++;
                    }else{
                        game.player2WinStat++;
                    }
                    updateStats();
                    return;
                }
                if (turn==8){
                    draw = true;
                    game.lblStatus.setText("Game draw!");
                    game.btnNextRound.setEnabled(true);
                    game.tieStat++;
                    updateStats();
                    return;
                }
                turn++;
                if (game.gameMode==1 && player[1-currentPlayer].getName().equals("Computer")){
                    Computer computer = (Computer) player[1-currentPlayer];
                    position = computer.play(moves, player[currentPlayer], turn);
                    setBoundries(position);
                    mouseClicked(null);
                }
            }
        }
    }
        
    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    private void updateStats() {
        switch(game.gameMode){
            case 2:
                Human temp1 = (Human) player[currentPlayer];
                Human temp2 = (Human) player[1 - currentPlayer];
                if (win) {
                    
                        temp1.setWins(temp1.getWins() + 1);
                        temp2.setLoses(temp2.getLoses() + 1);
                } else {
                    temp1.setTies(temp1.getTies() + 1);
                    temp2.setTies(temp2.getTies() + 1);
                }
                if (game.savePlayer1){
                    new Thread() {
                        @Override
                        public void run() {
                            dbHandler.updatePlayer(temp1);
                        }
                    }.start();
                }
                if (game.savePlayer2){
                    new Thread() {
                        @Override
                        public void run() {
                            dbHandler.updatePlayer(temp2);
                        }
                    }.start();
                }
                break;
            case 1:
                Human temp;
                if (player[currentPlayer].getName().equals("Computer")){
                    temp = (Human) player[1-currentPlayer];
                    if (win){
                        temp.setLoses(temp.getLoses()+1);
                    }
                }else{
                    temp = (Human) player[currentPlayer];
                    if (win){
                        temp.setWins(temp.getWins()+1);
                    }
                }
                if (draw){
                    temp.setTies(temp.getTies()+1);
                }
                new Thread() {
                    public void run() {
                        dbHandler.updatePlayer(temp);
                    }
                }.start();
                break;
            case 0:
                break;
        }
    }        
 
    public void setnetMyTurn(boolean netMyTurn){
        this.netMyTurn = netMyTurn;
    }
    
    public void setNetworkHandler(NetworkHandler networkHandler) {
        this.networkHandler = networkHandler;
    }
    
    public void networkPlay(int position){
        System.out.println("network "+position);
        setBoundries(position);
        mouseClicked(null);
        System.out.println("network played");
        netMyTurn = true;
    }
    
}
