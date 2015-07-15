// cheak about resizeable the window
// improve code
//single plater

package test_case_Game;

import java.awt.Color;
import java.util.Arrays;


public class Test_Game extends javax.swing.JFrame{
    
    static int winComb[][] = {{1,2,3},{4,5,6},{7,8,9},{1,4,7},{2,5,8},{3,6,9},{1,5,9},{3,5,7}}; // states about winning combinations
    public static int[] buttonIndex;
    public static int[][] button_state;
    public static int button_Click; // 0-9 numbers to the buttons on board to identify which btn click
    int playerMode; // playerMode 1 = 1, playerMode 2 = 2
    public static int currant_player;
    int turn; // tracking the currant player and turn
    Test_Human player1;
    Test_Human player2;
    Test_Computer player3;
    int player1_win;
    int player2_win;
    int drow_times;
    static boolean win; // state of win the player or not
    
    public Test_Game() {
        initComponents();
    }
    
    public void gameInit(){
        button_state = new int[3][3];
        int b[]= {0,0,0,0,0,0,0,0,0,0}; // easy to handle 9 index begin with 1
        buttonIndex = b;
        button_state[0][0] = 0;
        button_state[0][1] = 0;
        button_state[0][2] = 0;
        button_state[1][0] = 0;
        button_state[1][1] = 0;
        button_state[1][2] = 0;
        button_state[2][0] = 0;
        button_state[2][1] = 0;
        button_state[2][2] = 0;
        currant_player = 1;
        turn = 1;
        win = false;
        Set_Button_Text();
    }
    
    public void init_SinglePlay(){
        player1 = new Test_Human();
        player3 = new Test_Computer();
        player1_win =0;
        player2_win = 0;
        drow_times = 0;
        win = false;
    }
    public void init_2player_Mode(){
        player1 = new Test_Human();
        player2 = new Test_Human();
        player1_win =0;
        player2_win = 0;
        drow_times = 0;
        win = false;
    }
    //when button click, move on the play......
    private void start_Play(){

        if(playerMode ==1){  
           // single player
          //  if(currant_player == 1){
                
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                               
                Label_Display.setText(" Your 'X' turn");
                player1.playTurn(currant_player,turn);
                turn++;
                Set_Button_Text();
                cheak_for_win();
                             
                //this part only run human not win the game, otherwise after human win, this part also runs (error)
                if(!win){
                    Label_Display.setText("Computer 'O' turn"); 
                    currant_player = 2;
                    System.out.println("line 79: "+Arrays.toString(button_state[0])+ "" + Arrays.toString(button_state[1])+" "+Arrays.toString(button_state[2]));
                    player3.minmax(); // cheaking available movement in the board 
                    // special states of if computer wins, set win flags
        // set_computer_win();
                    turn++; 
                    Set_Button_Text();                
                    cheak_for_win();
                    currant_player = 1;
                    Label_Display.setText(" Your 'X' turn");
                    System.out.println("line 95: "+Arrays.toString(button_state[0])+ "" + Arrays.toString(button_state[1])+" "+Arrays.toString(button_state[2]));

                }
  /////////////////////////////////////////////////////////////////////////////////////////////////////////////                                          
          // }
        }else if(playerMode == 2){
            //doubly player        
            if(currant_player == 1){
                Label_Display.setText("Player1 'X' turn");
                player1.playTurn(currant_player,turn);
                Set_Button_Text();
                Label_Display.setText("Player1 'O' turn");
                cheak_for_win();
                currant_player = 2;

               // System.out.println(" player 1");
            }else if(currant_player == 2){
               
               player2.playTurn(currant_player, turn);
                Set_Button_Text();                
                Label_Display.setText("Player1 'X' turn");
                cheak_for_win();
                currant_player = 1;                
            }
            turn ++; 
        }
               
    }
    //this methord for setting the signs of player
    private void Set_Button_Text(){
        
        // if button_stage is 1, for playerMode 1, else equal to 2 , playerMode 2 otherwise null
        Btn_11.setText(button_state[0][0]== 1? "X" : (button_state[0][0]==2? "O":" "));
        Btn_12.setText(button_state[0][1]== 1? "X" : (button_state[0][1]==2? "O":" "));
        Btn_13.setText(button_state[0][2]== 1? "X" : (button_state[0][2]==2? "O":" "));
        Btn_21.setText(button_state[1][0]== 1? "X" : (button_state[1][0]==2? "O":" "));
        Btn_22.setText(button_state[1][1]== 1? "X" : (button_state[1][1]==2? "O":" "));
        Btn_23.setText(button_state[1][2]== 1? "X" : (button_state[1][2]==2? "O":" "));
        Btn_31.setText(button_state[2][0]== 1? "X" : (button_state[2][0]==2? "O":" "));
        Btn_32.setText(button_state[2][1]== 1? "X" : (button_state[2][1]==2? "O":" "));
        Btn_33.setText(button_state[2][2]== 1? "X" : (button_state[2][2]==2? "O":" "));
        L_Player1_winTimes.setText(Integer.toString(player1_win));
        L_Player2_winTimes.setText(Integer.toString(player2_win));
        L_Drow_times.setText(Integer.toString(drow_times));
        set_Disabled();
    }
    //when click the button by user, disabled it, becouse otherwise player can re click the button again thats error!!
    private void set_Disabled(){
        Btn_11.setEnabled(button_state[0][0]!= 0? false : true);
        Btn_12.setEnabled(button_state[0][1]!= 0? false : true);
        Btn_13.setEnabled(button_state[0][2]!= 0? false : true);
        Btn_21.setEnabled(button_state[1][0]!= 0? false : true);
        Btn_22.setEnabled(button_state[1][1]!= 0? false : true);
        Btn_23.setEnabled(button_state[1][2]!= 0? false : true);
        Btn_31.setEnabled(button_state[2][0]!= 0? false : true);
        Btn_32.setEnabled(button_state[2][1]!= 0? false : true);
        Btn_33.setEnabled(button_state[2][2]!= 0? false : true);
    }
    private void set_computer_win(){
        System.out.println("line 236 : com win!!!!!! ");
        boolean state = false;
        int[] winCells = new int[3];
        int count;
        for(int[]item : winComb){
            state= false;
            count = 0;
            System.out.println("Line248: "+Arrays.toString(item));
            for(int i: item){
                
                if(buttonIndex[i]== 2){
                    state = true;
                    winCells[count] = i;
                    System.out.println("Line248: "+Arrays.toString(winCells));
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
    }

    private void cheak_for_win(){
        System.out.println("cheak for win()" +currant_player+ Arrays.toString(buttonIndex));
        boolean state = false;
        int[] winCells = new int[3];
        int count;
        for(int[]item : winComb){
            state= false;
            count = 0;
            for(int i: item){
                
                if(buttonIndex[i]== currant_player){
                    state = true;
                    winCells[count] = i;
                    System.out.println("Line 195: cheak for win(): "+ Arrays.toString(winCells)+" : "+currant_player);
                    count++;
                }else{
                    state = false;
                    break;
                }
                
            }
            if(state){
                //player  win
                displayWin(winCells);
                System.out.println("line206: player win : currant player : "+ currant_player);
                break;
            }
        }
        if( turn == 10 && !(state)){
            drow_times ++;
            L_Drow_times.setText(String.valueOf(drow_times));
            int[] stateArray = new int[3];
            lockButton(stateArray);
            Label_Display.setText(" Match is Drow! press Reset to continue...");
        }
    }
    //inter funtion of Cheak for win() only apper when it called. it displays if there is win here.
    private void displayWin(int[] win){
                if(currant_player == 1){
                    Label_Display.setText("Player X Win the Game!");
                    player1_win ++;
                    L_Player1_winTimes.setText(String.valueOf(player1_win));
                    lockButton(win);
                    this.win = true; //globle variable
                }else if(currant_player == 2){
                    Label_Display.setText("Player O Win the Game!");
                    player2_win ++;
                    L_Player2_winTimes.setText(String.valueOf(player2_win));
                    lockButton(win);
                    this.win = true; //globle variable

                }
                
                
    }
        //inter funtion of Cheak for win() only apper when it called. it coloured RED to winning combination.
    private void lockButton(int[] win){
        for(int i: win){
            switch(i){
                case 1: Btn_11.setForeground(Color.red); ; break;
                case 2: Btn_12.setForeground(Color.red); break;
                case 3: Btn_13.setForeground(Color.red)  ; break;
                case 4: Btn_21.setForeground(Color.red) ; break;
                case 5: Btn_22.setForeground(Color.red) ; break;
                case 6: Btn_23.setForeground(Color.red)  ; break;
                case 7: Btn_31.setForeground(Color.red) ; break;
                case 8: Btn_32.setForeground(Color.red) ; break;
                case 9: Btn_33.setForeground(Color.red)  ; break;
                
            }
        }
        
        Btn_11.setEnabled(false);
        Btn_12.setEnabled(false);
        Btn_13.setEnabled(false);
        Btn_21.setEnabled(false);
        Btn_22.setEnabled(false);
        Btn_23.setEnabled(false);
        Btn_31.setEnabled(false);
        Btn_32.setEnabled(false);
        Btn_33.setEnabled(false);
        Btn_Reset.setEnabled(true);
        this.win = true; //globle variable

    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        Btn_SinglePlayer = new javax.swing.JButton();
        Btn_TwoPlayer = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        Btn_13 = new javax.swing.JButton();
        Btn_11 = new javax.swing.JButton();
        Btn_12 = new javax.swing.JButton();
        Btn_21 = new javax.swing.JButton();
        Btn_22 = new javax.swing.JButton();
        Btn_23 = new javax.swing.JButton();
        Btn_31 = new javax.swing.JButton();
        Btn_32 = new javax.swing.JButton();
        Btn_33 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        Label_Display = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        L_Player1_winTimes = new javax.swing.JLabel();
        L_Player2_winTimes = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        L_Drow_times = new javax.swing.JLabel();
        Btn_Reset = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/dfdsfdsfds.jpg"))); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        Btn_SinglePlayer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/single player.jpg"))); // NOI18N
        Btn_SinglePlayer.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Btn_SinglePlayer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_SinglePlayerActionPerformed(evt);
            }
        });

        Btn_TwoPlayer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/two.jpg"))); // NOI18N
        Btn_TwoPlayer.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Btn_TwoPlayer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_TwoPlayerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Btn_TwoPlayer)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Btn_SinglePlayer)
                .addGap(22, 22, 22))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Btn_SinglePlayer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Btn_TwoPlayer, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tic Tac Toe Board", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        Btn_13.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        Btn_13.setText("X");
        Btn_13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_13ActionPerformed(evt);
            }
        });

        Btn_11.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        Btn_11.setText("X");
        Btn_11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_11ActionPerformed(evt);
            }
        });

        Btn_12.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        Btn_12.setText("X");
        Btn_12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_12ActionPerformed(evt);
            }
        });

        Btn_21.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        Btn_21.setText("X");
        Btn_21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_21ActionPerformed(evt);
            }
        });

        Btn_22.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        Btn_22.setText("X");
        Btn_22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_22ActionPerformed(evt);
            }
        });

        Btn_23.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        Btn_23.setText("X");
        Btn_23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_23ActionPerformed(evt);
            }
        });

        Btn_31.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        Btn_31.setText("X");
        Btn_31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_31ActionPerformed(evt);
            }
        });

        Btn_32.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        Btn_32.setText("X");
        Btn_32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_32ActionPerformed(evt);
            }
        });

        Btn_33.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        Btn_33.setText("X");
        Btn_33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_33ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(Btn_31, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Btn_32, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Btn_33, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(Btn_21, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Btn_22, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Btn_23, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(Btn_11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Btn_12, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Btn_13, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(14, 14, 14))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Btn_13, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Btn_12, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Btn_11, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Btn_23, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Btn_22, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Btn_21, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Btn_33, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Btn_32, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Btn_31, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Score Board", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        Label_Display.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Label_Display.setForeground(new java.awt.Color(255, 0, 0));
        Label_Display.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Label_Display.setText("Select The Game Mode");
        Label_Display.setVerticalAlignment(javax.swing.SwingConstants.TOP);

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

        Btn_Reset.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Btn_Reset.setText("Reset Game");
        Btn_Reset.setEnabled(false);
        Btn_Reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_ResetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Label_Display, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(L_Player2_winTimes, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(L_Player1_winTimes, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(L_Drow_times, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(81, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Btn_Reset)
                .addGap(31, 31, 31))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(Label_Display, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(L_Player1_winTimes))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(L_Player2_winTimes))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(L_Drow_times))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 156, Short.MAX_VALUE)
                .addComponent(Btn_Reset)
                .addGap(29, 29, 29))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Btn_SinglePlayerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_SinglePlayerActionPerformed
            Label_Display.setText(" Single Player Mode select , Player X turn");
            playerMode =1;
            gameInit();
            init_SinglePlay();
    }//GEN-LAST:event_Btn_SinglePlayerActionPerformed

    private void Btn_TwoPlayerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_TwoPlayerActionPerformed
            Label_Display.setText(" Two Player Mode select , Player X turn");
            playerMode = 2;
            gameInit();
            init_2player_Mode();
    }//GEN-LAST:event_Btn_TwoPlayerActionPerformed

    private void Btn_11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_11ActionPerformed
        if( playerMode == 1){
            button_state[0][0] = 1;
            
        }else if(playerMode ==2){
            button_state[0][0]= 2;           
        }
        if(currant_player == 1){
                buttonIndex[1]= 1;                
        }else{
                buttonIndex[1] = 2;
        }
         button_Click = 1; // top left corner
         start_Play();

    }//GEN-LAST:event_Btn_11ActionPerformed

    private void Btn_12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_12ActionPerformed
        if( playerMode == 1){
            button_state[0][1] = 1;
        }else if(playerMode ==2){
            button_state[0][1]= 2;
        }
        if(currant_player == 1){
                buttonIndex[2]= 1;                
        }else{
                buttonIndex[2] = 2;
        }
        button_Click = 2; // top center
        start_Play();

    }//GEN-LAST:event_Btn_12ActionPerformed

    private void Btn_13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_13ActionPerformed
        if( playerMode == 1){
            button_state[0][2] = 1;
        }else if(playerMode ==2){
            button_state[0][2]= 2;            
        }
        if(currant_player == 1){
                buttonIndex[3]= 1;                
        }else{
                buttonIndex[3] = 2;
        }
        button_Click = 3; // top right corner
        start_Play();

    }//GEN-LAST:event_Btn_13ActionPerformed

    private void Btn_21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_21ActionPerformed
        if( playerMode == 1){
            button_state[1][0] = 1;
        }else if(playerMode ==2){
            button_state[1][0]= 2;            
        } 
        if(currant_player == 1){
                buttonIndex[4]= 1;                
        }else{
                buttonIndex[4] = 2;
         }
        button_Click = 4; //middle left
        start_Play();
    }//GEN-LAST:event_Btn_21ActionPerformed

    private void Btn_22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_22ActionPerformed
        if( playerMode == 1){
            button_state[1][1] = 1;
        }else if(playerMode ==2){
            button_state[1][1]= 2;            
        }
        if(currant_player == 1){
                buttonIndex[5]= 1;                
        }else{
                buttonIndex[5] = 2;
        }
        button_Click = 5;// center
        start_Play();
    }//GEN-LAST:event_Btn_22ActionPerformed

    private void Btn_23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_23ActionPerformed
        if( playerMode == 1){
            button_state[1][2] = 1;
        }else if(playerMode ==2){
            button_state[1][2]= 2;            
        }
        if(currant_player == 1){
                buttonIndex[6]= 1;                
        }else{
                buttonIndex[6] = 2;
        }
        button_Click = 6; // middle right
        start_Play();
    }//GEN-LAST:event_Btn_23ActionPerformed

    private void Btn_31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_31ActionPerformed
        if( playerMode == 1){
            button_state[2][0] = 1;
        }else if(playerMode ==2){
            button_state[2][0]= 2;
        }
        if(currant_player == 1){
                buttonIndex[7]= 1;                
        }else{
                buttonIndex[7] = 2;
        }
       button_Click = 7; // bottom left
       start_Play();
    }//GEN-LAST:event_Btn_31ActionPerformed

    private void Btn_32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_32ActionPerformed
        if( playerMode == 1){
            button_state[2][1] = 1;
        }else if(playerMode ==2){
            button_state[2][1]= 2;            
        }
        if(currant_player == 1){
                buttonIndex[8]= 1;                
        }else{
                buttonIndex[8] = 2;
        }
        button_Click = 8; // bottom center
        start_Play();
    }//GEN-LAST:event_Btn_32ActionPerformed

    private void Btn_33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_33ActionPerformed
        if( playerMode == 1){
            button_state[2][2] = 1;
        }else if(playerMode ==2){
            button_state[2][2]= 2;            
        } 
        if(currant_player == 1){
                buttonIndex[9]= 1;                
        }else{
                buttonIndex[9] = 2;
        }
        button_Click = 9; // bottom right
        start_Play();
    }//GEN-LAST:event_Btn_33ActionPerformed

    private void Btn_ResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_ResetActionPerformed

        Label_Display.setText(" Game Reset. Begin with Player X ");
        Btn_11.setEnabled(true); Btn_11.setForeground(Color.BLACK);
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
        }
        
        
    }//GEN-LAST:event_Btn_ResetActionPerformed


     
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Test_Game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Test_Game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Test_Game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Test_Game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form 
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Game().setVisible(true);
            }
        }); */
        
        Test_Game t = new Test_Game();
        t.setVisible(true);
        // t.start();
        t.gameInit();
                        
                    
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Btn_11;
    private javax.swing.JButton Btn_12;
    private javax.swing.JButton Btn_13;
    private javax.swing.JButton Btn_21;
    private javax.swing.JButton Btn_22;
    private javax.swing.JButton Btn_23;
    private javax.swing.JButton Btn_31;
    private javax.swing.JButton Btn_32;
    private javax.swing.JButton Btn_33;
    private javax.swing.JButton Btn_Reset;
    private javax.swing.JButton Btn_SinglePlayer;
    private javax.swing.JButton Btn_TwoPlayer;
    private javax.swing.JLabel L_Drow_times;
    private javax.swing.JLabel L_Player1_winTimes;
    private javax.swing.JLabel L_Player2_winTimes;
    private javax.swing.JLabel Label_Display;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    // End of variables declaration//GEN-END:variables

   

    
}   
