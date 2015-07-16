// cheak about resizeable the window
// improve code
//single plater

package Tic_Tac_Toe_Game;

import java.awt.Cursor;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


public class Game extends javax.swing.JFrame{
    
    static int winComb[][] =  {{1,2,3},{4,5,6},{7,8,9},{1,4,7},{2,5,8},{3,6,9},{1,5,9},{3,5,7}}; // states about winning combinations
    public static int[] buttonIndex;
    public static int[][] buttonState;
    public static int button_Click; // 0-9 numbers to the buttons on board to identify which btn click
    int playerMode; // playerMode 1 = 1, playerMode 2 = 2
    public static int currentPlayer;
    int turn; // tracking the current player and turn
    /*Human player1;
    Human player2;
    Computer player3;*/
    int player1_win;
    int player2_win;
    int drow_times;
    static boolean win; // state of win the player or not
    Board board;
    Player player1,player2;
    private boolean saveNewPlayer;
    
    public Game() {
        this.saveNewPlayer = false;
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        }
        initComponents();
        //addBoard();
        pnlSelectPlayer.setVisible(false);
        pnlScore.setVisible(false);
        pnlBoard.setVisible(false);
        pnlSettings.setVisible(false);
    }
    
    public void gameInit(){
        buttonState = new int[3][3];
        int b[]= {0,0,0,0,0,0,0,0,0,0}; // easy to handle 9 index begin with 1
        buttonIndex = b;
        buttonState[0][0] = 0;
        buttonState[0][1] = 0;
        buttonState[0][2] = 0;
        buttonState[1][0] = 0;
        buttonState[1][1] = 0;
        buttonState[1][2] = 0;
        buttonState[2][0] = 0;
        buttonState[2][1] = 0;
        buttonState[2][2] = 0;
        currentPlayer = 1;
        turn = 1;
        win = false;
        Set_Button_Text();
    }
    
    public void init_SinglePlay(){
        //player1 = new Human();
        //player3 = new Computer();
        player1_win =0;
        player2_win = 0;
        drow_times = 0;
        win = false;
    }
    public void init_2player_Mode(){
        //player1 = new Human();
        //player2 = new Human();
        player1_win =0;
        player2_win = 0;
        drow_times = 0;
        win = false;
    }
    //when button click, move on the play......
    private void start_Play(){

        if(playerMode ==1){  
           // single player
                /*In single player mode, this methord call when the player click one of button, then first we set user button 
                visible and then increment the count of button click.
                then cheak the winning chance of player if there is win then lock the game buttons and set win!
            if not then computer chance.
                AI than we implement called.
                it choose the suitable button and update butto index[] and button state[][] arrays.
                then we called previous setbuttonText() and chaeakForWin() to setting button and
                check the winning chance.
           
           we implement common methord for set button Text that "X" or "O" and cheak winning chances and set win and lock buttons.

            */
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                               
                lblStatus.setText(" Your 'X' turn");
                player1.playTurn(currentPlayer,turn);
                turn++;
                Set_Button_Text();
                check_for_win();
                //this part only run human not win the game, otherwise after human win, this part also runs (error)
                if(!win){
                    lblStatus.setText("Computer 'O' turn");
                    currentPlayer = 2;
                    //player3.minmax(); // cheaking available movement in the board 
                    turn++; 
                    Set_Button_Text();                
                    check_for_win();
                    currentPlayer = 1;
                    lblStatus.setText(" Your 'X' turn");
                }
  /////////////////////////////////////////////////////////////////////////////////////////////////////////////                                          
          // }
        }else if(playerMode == 2){
            //doubly player     
            /*
            this multi player algorithms waorks in single application.
            that works in click. when once click, we assume that next click choose by opperant player by sharing mouse pointer.
            */
            if(currentPlayer == 1){
                lblStatus.setText("Player1 'X' turn");
                player1.playTurn(currentPlayer,turn);
                Set_Button_Text();
                lblStatus.setText("Player1 'O' turn");
                check_for_win();
                currentPlayer = 2;

               // System.out.println(" player 1");
            }else if(currentPlayer == 2){
               
               player2.playTurn(currentPlayer, turn);
                Set_Button_Text();                
                lblStatus.setText("Player1 'X' turn");
                check_for_win();
                currentPlayer = 1;                
            }
            turn ++; 
        }
               
    }
    //this methord for setting the signs of player
    private void Set_Button_Text(){

        // if button_stage is 1, for playerMode 1, else equal to 2 , playerMode 2 otherwise null
        /*Btn_11.setText(buttonState[0][0]== 1? "X" : (buttonState[0][0]==2? "O":" "));
        Btn_12.setText(buttonState[0][1]== 1? "X" : (buttonState[0][1]==2? "O":" "));
        Btn_13.setText(buttonState[0][2]== 1? "X" : (buttonState[0][2]==2? "O":" "));
        Btn_21.setText(buttonState[1][0]== 1? "X" : (buttonState[1][0]==2? "O":" "));
        Btn_22.setText(buttonState[1][1]== 1? "X" : (buttonState[1][1]==2? "O":" "));
        Btn_23.setText(buttonState[1][2]== 1? "X" : (buttonState[1][2]==2? "O":" "));
        Btn_31.setText(buttonState[2][0]== 1? "X" : (buttonState[2][0]==2? "O":" "));
        Btn_32.setText(buttonState[2][1]== 1? "X" : (buttonState[2][1]==2? "O":" "));
        Btn_33.setText(buttonState[2][2]== 1? "X" : (buttonState[2][2]==2? "O":" "));*/
        L_Player1_winTimes.setText(Integer.toString(player1_win));
        L_Player2_winTimes.setText(Integer.toString(player2_win));
        L_Drow_times.setText(Integer.toString(drow_times));
        set_Disabled();
    }
    //when click the button by user, disabled it, becouse otherwise player can re click the button again thats error!!
    private void set_Disabled(){
        /*Btn_11.setEnabled(buttonState[0][0]!= 0? false : true);
        Btn_12.setEnabled(buttonState[0][1]!= 0? false : true);
        Btn_13.setEnabled(buttonState[0][2]!= 0? false : true);
        Btn_21.setEnabled(buttonState[1][0]!= 0? false : true);
        Btn_22.setEnabled(buttonState[1][1]!= 0? false : true);
        Btn_23.setEnabled(buttonState[1][2]!= 0? false : true);
        Btn_31.setEnabled(buttonState[2][0]!= 0? false : true);
        Btn_32.setEnabled(buttonState[2][1]!= 0? false : true);
        Btn_33.setEnabled(buttonState[2][2]!= 0? false : true);*/
        System.out.println("ok "+turn);

    }
    
    private void check_for_win(){
       /*
        common methord for cheak win
        we take currant player in variable "currant player" then 
        cheak with winning combination states. 
        to do that, we maintain another single array that ButtonIndex[] like [0 1 2 3 4 5 6 7 8 9].
        then we can easily compare buttons with winning combinations.
        what we have to do is, 
            take one of  winning combination,
            check in arrray that we maintain buttonIndex, that is currant player
        if  all the numbers true, 
            that is win!!!
        then
            we called displayWin() and LockButtons() methord for 
            displayed win state and highlighted the winning combination and
            lock the rest of buttons for not to move further movement.
        */
        boolean state = false;
        int[] winCells = new int[3];
        int count;
        for(int[]item : winComb){
            state= false;
            count = 0;
            for(int i: item){
                
                if(buttonIndex[i]== currentPlayer){
                    state = true;
                    winCells[count] = i;
                    count++;
                }else{
                    state = false;
                    break;
                }
                
            }
            if(state){
                //player  win
                displayWin(winCells);
                break;
            }
        }
        if( turn == 10 && !(state)){
            drow_times ++;
            L_Drow_times.setText(String.valueOf(drow_times));
            int[] stateArray = new int[3];
            lockButton(stateArray);
            lblStatus.setText(" Match is Drow! press Reset to continue...");
        }
    }
    //inter funtion of Cheak for win() only apper when it called. it displays if there is win here.
    private void displayWin(int[] win){
                if(currentPlayer == 1){
                    lblStatus.setText("Player X Win the Game!");
                    player1_win ++;
                    L_Player1_winTimes.setText(String.valueOf(player1_win));
                    lockButton(win);
                    this.win = true; //globle variable
                }else if(currentPlayer == 2){
                    lblStatus.setText("Player O Win the Game!");
                    player2_win ++;
                    L_Player2_winTimes.setText(String.valueOf(player2_win));
                    lockButton(win);
                }
                
                
    }
        //inter funtion of Cheak for win() only apper when it called. it coloured RED to winning combination.
    private void lockButton(int[] win){
        for(int i: win){
            /*switch(i){
                case 1: Btn_11.setForeground(Color.red); ; break;
                case 2: Btn_12.setForeground(Color.red); break;
                case 3: Btn_13.setForeground(Color.red)  ; break;
                case 4: Btn_21.setForeground(Color.red) ; break;
                case 5: Btn_22.setForeground(Color.red) ; break;
                case 6: Btn_23.setForeground(Color.red)  ; break;
                case 7: Btn_31.setForeground(Color.red) ; break;
                case 8: Btn_32.setForeground(Color.red) ; break;
                case 9: Btn_33.setForeground(Color.red)  ; break;
                
            }*/
        }
        
       /* Btn_11.setEnabled(false);
        Btn_12.setEnabled(false);
        Btn_13.setEnabled(false);
        Btn_21.setEnabled(false);
        Btn_22.setEnabled(false);
        Btn_23.setEnabled(false);
        Btn_31.setEnabled(false);
        Btn_32.setEnabled(false);
        Btn_33.setEnabled(false);*/
        btnNextRound.setEnabled(true);
        
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnGroupDifficulty = new javax.swing.ButtonGroup();
        pnlHeader = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        pnlLeft = new javax.swing.JPanel();
        pnlStart = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        Btn_TwoPlayer = new javax.swing.JButton();
        Btn_SinglePlayer = new javax.swing.JButton();
        btnNetworkGame = new javax.swing.JButton();
        btnStatistics = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        pnlScore = new javax.swing.JPanel();
        lblStatus = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        L_Player1_winTimes = new javax.swing.JLabel();
        L_Player2_winTimes = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        L_Drow_times = new javax.swing.JLabel();
        btnNextRound = new javax.swing.JButton();
        btnMainMenu = new javax.swing.JButton();
        pnlRight = new javax.swing.JPanel();
        pnlSettings = new javax.swing.JPanel();
        lblDifficulty = new javax.swing.JLabel();
        rBtnHard = new javax.swing.JRadioButton();
        rBtnNormal = new javax.swing.JRadioButton();
        rBtnEasy = new javax.swing.JRadioButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtPlayer1Name = new javax.swing.JTextField();
        txtPlayer2Name = new javax.swing.JTextField();
        btnSelectPlayer1 = new javax.swing.JButton();
        btnSelectPlayer2 = new javax.swing.JButton();
        btnStartGame = new javax.swing.JButton();
        pnlBoard = new javax.swing.JPanel();
        pnlSelectPlayer = new javax.swing.JPanel();
        lblSelectPlayerHeader = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtNewPlayer = new javax.swing.JTextField();
        jCheckBox1 = new javax.swing.JCheckBox();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstPlayers = new javax.swing.JList();
        btnOK = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        txtSearchPlayer = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Tic Tac Toe");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        pnlHeader.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/Header.jpg"))); // NOI18N

        javax.swing.GroupLayout pnlHeaderLayout = new javax.swing.GroupLayout(pnlHeader);
        pnlHeader.setLayout(pnlHeaderLayout);
        pnlHeaderLayout.setHorizontalGroup(
            pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHeaderLayout.createSequentialGroup()
                .addGap(188, 188, 188)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlHeaderLayout.setVerticalGroup(
            pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHeaderLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlStart.setBorder(javax.swing.BorderFactory.createTitledBorder("Main Menu"));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Game Mode :");

        Btn_TwoPlayer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/Two_Player.jpg"))); // NOI18N
        Btn_TwoPlayer.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Btn_TwoPlayer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_TwoPlayerActionPerformed(evt);
            }
        });

        Btn_SinglePlayer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/Single_Player.jpg"))); // NOI18N
        Btn_SinglePlayer.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Btn_SinglePlayer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_SinglePlayerActionPerformed(evt);
            }
        });

        btnNetworkGame.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/Network.png"))); // NOI18N
        btnNetworkGame.setText("  Network Game");
        btnNetworkGame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNetworkGameActionPerformed(evt);
            }
        });

        btnStatistics.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/Stat.png"))); // NOI18N
        btnStatistics.setText("  View Statistics");
        btnStatistics.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStatisticsActionPerformed(evt);
            }
        });

        btnExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/Exit.png"))); // NOI18N
        btnExit.setText("  Exit Game");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlStartLayout = new javax.swing.GroupLayout(pnlStart);
        pnlStart.setLayout(pnlStartLayout);
        pnlStartLayout.setHorizontalGroup(
            pnlStartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlStartLayout.createSequentialGroup()
                .addContainerGap(32, Short.MAX_VALUE)
                .addGroup(pnlStartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlStartLayout.createSequentialGroup()
                        .addComponent(Btn_TwoPlayer)
                        .addGap(30, 30, 30)
                        .addComponent(Btn_SinglePlayer))
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(30, Short.MAX_VALUE))
            .addGroup(pnlStartLayout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addGroup(pnlStartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnExit, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
                    .addComponent(btnNetworkGame, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
                    .addComponent(btnStatistics, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlStartLayout.setVerticalGroup(
            pnlStartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlStartLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlStartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Btn_TwoPlayer)
                    .addComponent(Btn_SinglePlayer))
                .addGap(45, 45, 45)
                .addComponent(btnNetworkGame)
                .addGap(27, 27, 27)
                .addComponent(btnStatistics, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        pnlScore.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Score Board", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        lblStatus.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblStatus.setForeground(new java.awt.Color(255, 0, 0));
        lblStatus.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblStatus.setText("Player1 turn - X");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Player 1 Win :");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Player 2 Win :");

        L_Player1_winTimes.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        L_Player1_winTimes.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        L_Player1_winTimes.setText("     ");
        L_Player1_winTimes.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        L_Player2_winTimes.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        L_Player2_winTimes.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        L_Player2_winTimes.setText("    ");
        L_Player2_winTimes.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("Drow times  :");

        L_Drow_times.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        L_Drow_times.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        L_Drow_times.setText("  ");
        L_Drow_times.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnNextRound.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnNextRound.setText("Next Round");
        btnNextRound.setEnabled(false);
        btnNextRound.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextRoundActionPerformed(evt);
            }
        });

        btnMainMenu.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnMainMenu.setText("Main Menu");
        btnMainMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMainMenuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlScoreLayout = new javax.swing.GroupLayout(pnlScore);
        pnlScore.setLayout(pnlScoreLayout);
        pnlScoreLayout.setHorizontalGroup(
            pnlScoreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlScoreLayout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(pnlScoreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(pnlScoreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(L_Player2_winTimes, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(L_Player1_winTimes, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(L_Drow_times, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(81, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlScoreLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(btnMainMenu)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnNextRound)
                .addGap(31, 31, 31))
        );
        pnlScoreLayout.setVerticalGroup(
            pnlScoreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlScoreLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(lblStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(pnlScoreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(L_Player1_winTimes))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlScoreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(L_Player2_winTimes))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlScoreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(L_Drow_times))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 157, Short.MAX_VALUE)
                .addGroup(pnlScoreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNextRound)
                    .addComponent(btnMainMenu))
                .addGap(28, 28, 28))
        );

        javax.swing.GroupLayout pnlLeftLayout = new javax.swing.GroupLayout(pnlLeft);
        pnlLeft.setLayout(pnlLeftLayout);
        pnlLeftLayout.setHorizontalGroup(
            pnlLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLeftLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlScore, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(pnlLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlLeftLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(pnlStart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        pnlLeftLayout.setVerticalGroup(
            pnlLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLeftLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlScore, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(pnlLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlLeftLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(pnlStart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        pnlSettings.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblDifficulty.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblDifficulty.setText("Difficulty :");

        btnGroupDifficulty.add(rBtnHard);
        rBtnHard.setText("Hard");

        btnGroupDifficulty.add(rBtnNormal);
        rBtnNormal.setSelected(true);
        rBtnNormal.setText("Normal");

        btnGroupDifficulty.add(rBtnEasy);
        rBtnEasy.setText("Easy");

        jLabel6.setText("Player 1 Name :");

        jLabel7.setText("Player 2 Name :");

        txtPlayer1Name.setEditable(false);
        txtPlayer1Name.setText("Player1");

        txtPlayer2Name.setEditable(false);
        txtPlayer2Name.setText("player2");

        btnSelectPlayer1.setText("Select Player 1");
        btnSelectPlayer1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelectPlayer1ActionPerformed(evt);
            }
        });

        btnSelectPlayer2.setText("Select Player 2");
        btnSelectPlayer2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelectPlayer2ActionPerformed(evt);
            }
        });

        btnStartGame.setText("Start Game");
        btnStartGame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStartGameActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlSettingsLayout = new javax.swing.GroupLayout(pnlSettings);
        pnlSettings.setLayout(pnlSettingsLayout);
        pnlSettingsLayout.setHorizontalGroup(
            pnlSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSettingsLayout.createSequentialGroup()
                .addGroup(pnlSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlSettingsLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pnlSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtPlayer1Name, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
                            .addComponent(txtPlayer2Name))
                        .addGap(18, 18, 18)
                        .addGroup(pnlSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnSelectPlayer1)
                            .addComponent(btnSelectPlayer2)))
                    .addGroup(pnlSettingsLayout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addComponent(lblDifficulty)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlSettingsLayout.createSequentialGroup()
                .addContainerGap(97, Short.MAX_VALUE)
                .addGroup(pnlSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rBtnHard)
                    .addComponent(rBtnNormal)
                    .addComponent(rBtnEasy)
                    .addComponent(btnStartGame, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(141, 141, 141))
        );
        pnlSettingsLayout.setVerticalGroup(
            pnlSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSettingsLayout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addGroup(pnlSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSelectPlayer1)
                    .addComponent(txtPlayer1Name, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPlayer2Name, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSelectPlayer2))
                .addGap(26, 26, 26)
                .addComponent(lblDifficulty, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rBtnEasy)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rBtnNormal)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rBtnHard)
                .addGap(57, 57, 57)
                .addComponent(btnStartGame, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(62, Short.MAX_VALUE))
        );

        pnlBoard.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tic Tac Toe Board", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        javax.swing.GroupLayout pnlBoardLayout = new javax.swing.GroupLayout(pnlBoard);
        pnlBoard.setLayout(pnlBoardLayout);
        pnlBoardLayout.setHorizontalGroup(
            pnlBoardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 362, Short.MAX_VALUE)
        );
        pnlBoardLayout.setVerticalGroup(
            pnlBoardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 379, Short.MAX_VALUE)
        );

        pnlSelectPlayer.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblSelectPlayerHeader.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblSelectPlayerHeader.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSelectPlayerHeader.setText("Select Player");

        jLabel1.setText("New Player :");

        jCheckBox1.setSelected(true);
        jCheckBox1.setText("Add me to players list and save my statistics");

        jLabel9.setText("Select from players list :");

        jScrollPane1.setViewportView(lstPlayers);

        btnOK.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/Ok.png"))); // NOI18N
        btnOK.setText("  OK");
        btnOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOKActionPerformed(evt);
            }
        });

        btnBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/Back.png"))); // NOI18N
        btnBack.setText("  Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        txtSearchPlayer.setText("Type to search...");

        javax.swing.GroupLayout pnlSelectPlayerLayout = new javax.swing.GroupLayout(pnlSelectPlayer);
        pnlSelectPlayer.setLayout(pnlSelectPlayerLayout);
        pnlSelectPlayerLayout.setHorizontalGroup(
            pnlSelectPlayerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSelectPlayerLayout.createSequentialGroup()
                .addGroup(pnlSelectPlayerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlSelectPlayerLayout.createSequentialGroup()
                        .addGap(96, 96, 96)
                        .addComponent(lblSelectPlayerHeader, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlSelectPlayerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlSelectPlayerLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jLabel9)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtSearchPlayer))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlSelectPlayerLayout.createSequentialGroup()
                            .addGap(46, 46, 46)
                            .addGroup(pnlSelectPlayerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlSelectPlayerLayout.createSequentialGroup()
                                    .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnOK, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(pnlSelectPlayerLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlSelectPlayerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCheckBox1)
                            .addComponent(txtNewPlayer, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(52, Short.MAX_VALUE))
        );
        pnlSelectPlayerLayout.setVerticalGroup(
            pnlSelectPlayerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSelectPlayerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblSelectPlayerHeader, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlSelectPlayerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNewPlayer, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox1)
                .addGap(12, 12, 12)
                .addGroup(pnlSelectPlayerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSearchPlayer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlSelectPlayerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnBack, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnOK, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlRightLayout = new javax.swing.GroupLayout(pnlRight);
        pnlRight.setLayout(pnlRightLayout);
        pnlRightLayout.setHorizontalGroup(
            pnlRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRightLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlBoard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(pnlRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlRightLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(pnlSettings, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
            .addGroup(pnlRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlRightLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(pnlSelectPlayer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        pnlRightLayout.setVerticalGroup(
            pnlRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRightLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlBoard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(pnlRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlRightLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(pnlSettings, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
            .addGroup(pnlRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlRightLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(pnlSelectPlayer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlLeft, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlRight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(pnlHeader, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlHeader, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlLeft, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlRight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Btn_SinglePlayerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_SinglePlayerActionPerformed
        /*Label_Display.setText(" Single Player Mode select , Player X turn");
        playerMode =1;
        gameInit();
        init_SinglePlay();
        pnlBoard.setVisible(true);*/
        pnlBoard.setVisible(false);
        pnlScore.setVisible(false);
        pnlSelectPlayer.setVisible(false);
        lblDifficulty.setVisible(true);
        rBtnEasy.setVisible(true);
        rBtnNormal.setVisible(true);
        rBtnHard.setVisible(true);
        pnlSettings.setVisible(true);
        //board.drawBoard();
    }//GEN-LAST:event_Btn_SinglePlayerActionPerformed

    private void Btn_TwoPlayerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_TwoPlayerActionPerformed
            /*Label_Display.setText(" Two Player Mode select , Player X turn");
            playerMode = 2;
            gameInit();
            init_2player_Mode();*/ 
        pnlBoard.setVisible(false);
        pnlScore.setVisible(false);
        pnlSelectPlayer.setVisible(false);
        lblDifficulty.setVisible(false);
        rBtnEasy.setVisible(false);
        rBtnNormal.setVisible(false);
        rBtnHard.setVisible(false);
        pnlSettings.setVisible(true);    
        //board.drawBoard();
    }//GEN-LAST:event_Btn_TwoPlayerActionPerformed

    private void btnNextRoundActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextRoundActionPerformed
        /*lblStatus.setText(" Game Reset. Begin with Player X ");
        /*Btn_11.setEnabled(true); Btn_11.setForeground(Color.BLACK);
        Btn_12.setEnabled(true); Btn_12.setForeground(Color.BLACK);
        Btn_13.setEnabled(true); Btn_13.setForeground(Color.BLACK);
        Btn_21.setEnabled(true); Btn_21.setForeground(Color.BLACK);
        Btn_22.setEnabled(true); Btn_22.setForeground(Color.BLACK);
        Btn_23.setEnabled(true); Btn_23.setForeground(Color.BLACK);
        Btn_31.setEnabled(true); Btn_31.setForeground(Color.BLACK);
        Btn_32.setEnabled(true); Btn_32.setForeground(Color.BLACK);
        Btn_33.setEnabled(true); Btn_33.setForeground(Color.BLACK);
        
        gameInit();
        if(playerMode == 2){
             //    init_2player_Mode();
        }*/
        Player temp = player1;
        player1 = player2;
        player2 = temp;
        btnStartGameActionPerformed(null);
        btnNextRound.setEnabled(false);
    }//GEN-LAST:event_btnNextRoundActionPerformed

    private void btnSelectPlayer2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelectPlayer2ActionPerformed
        lblSelectPlayerHeader.setText("Select Player 2");
        pnlSettings.setVisible(false);
        pnlSelectPlayer.setVisible(true);
    }//GEN-LAST:event_btnSelectPlayer2ActionPerformed

    private void btnNetworkGameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNetworkGameActionPerformed
        
    }//GEN-LAST:event_btnNetworkGameActionPerformed

    private void btnStatisticsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStatisticsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnStatisticsActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        int response = JOptionPane.showConfirmDialog(this, "Are you sure you want to exit?", "Exit Game", JOptionPane.YES_NO_OPTION);
        if (response == JOptionPane.YES_OPTION){
            System.exit(0);
        }
    }//GEN-LAST:event_btnExitActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        btnExitActionPerformed(null);
    }//GEN-LAST:event_formWindowClosing

    private void btnSelectPlayer1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelectPlayer1ActionPerformed
        lblSelectPlayerHeader.setText("Select Player 1");
        pnlSettings.setVisible(false);
        pnlSelectPlayer.setVisible(true);
    }//GEN-LAST:event_btnSelectPlayer1ActionPerformed

    private void btnOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOKActionPerformed
        if (lblSelectPlayerHeader.getText().endsWith("1")){
            txtPlayer1Name.setText(txtNewPlayer.getText());
        }else{
            txtPlayer2Name.setText(txtNewPlayer.getText());
        }
        pnlSelectPlayer.setVisible(false);
        pnlSettings.setVisible(true);
    }//GEN-LAST:event_btnOKActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        pnlSelectPlayer.setVisible(false);
        pnlSettings.setVisible(true);
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnStartGameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStartGameActionPerformed
        pnlBoard.removeAll();
        player1 = new Human(txtPlayer1Name.getText(), "X");
        player2 = new Human(txtPlayer2Name.getText(), "O");
        addBoard();
        lblStatus.setText(player1.getName()+"'s turn - X");
        pnlSelectPlayer.setVisible(false);
        pnlSettings.setVisible(false);
        pnlStart.setVisible(false);
        pnlBoard.setVisible(true);
        pnlScore.setVisible(true);
        board.drawBoard();
    }//GEN-LAST:event_btnStartGameActionPerformed

    private void btnMainMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMainMenuActionPerformed
        pnlBoard.setVisible(false);
        pnlScore.setVisible(false);
        pnlSettings.setVisible(false);
        pnlSelectPlayer.setVisible(false);
        pnlStart.setVisible(true);
    }//GEN-LAST:event_btnMainMenuActionPerformed


     
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Game().setVisible(true);
            }
        });
        
        /*Game t = new Game();
        t.setVisible(true);
        // t.start();
        t.gameInit();*/
                        
                    
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Btn_SinglePlayer;
    private javax.swing.JButton Btn_TwoPlayer;
    private javax.swing.JLabel L_Drow_times;
    private javax.swing.JLabel L_Player1_winTimes;
    private javax.swing.JLabel L_Player2_winTimes;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnExit;
    private javax.swing.ButtonGroup btnGroupDifficulty;
    private javax.swing.JButton btnMainMenu;
    private javax.swing.JButton btnNetworkGame;
    protected javax.swing.JButton btnNextRound;
    private javax.swing.JButton btnOK;
    private javax.swing.JButton btnSelectPlayer1;
    private javax.swing.JButton btnSelectPlayer2;
    private javax.swing.JButton btnStartGame;
    private javax.swing.JButton btnStatistics;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblDifficulty;
    private javax.swing.JLabel lblSelectPlayerHeader;
    protected javax.swing.JLabel lblStatus;
    private javax.swing.JList lstPlayers;
    private javax.swing.JPanel pnlBoard;
    private javax.swing.JPanel pnlHeader;
    private javax.swing.JPanel pnlLeft;
    private javax.swing.JPanel pnlRight;
    private javax.swing.JPanel pnlScore;
    private javax.swing.JPanel pnlSelectPlayer;
    private javax.swing.JPanel pnlSettings;
    private javax.swing.JPanel pnlStart;
    private javax.swing.JRadioButton rBtnEasy;
    private javax.swing.JRadioButton rBtnHard;
    private javax.swing.JRadioButton rBtnNormal;
    private javax.swing.JTextField txtNewPlayer;
    private javax.swing.JTextField txtPlayer1Name;
    private javax.swing.JTextField txtPlayer2Name;
    private javax.swing.JTextField txtSearchPlayer;
    // End of variables declaration//GEN-END:variables

    private void addBoard() {
        board = new Board(this,player1,player2);
        board.setBounds(40, 55, 300, 300);
        pnlBoard.add(board);
        board.addMouseListener(board);
        board.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

   

    
}   
