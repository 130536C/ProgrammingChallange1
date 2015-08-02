package gui;

import Tic_Tac_Toe_Game.Computer;
import Tic_Tac_Toe_Game.Human;
import Tic_Tac_Toe_Game.Player;
import io.DBHandler;
import io.NetworkHandler;
import java.awt.Cursor;
import java.awt.event.KeyEvent;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import org.apache.log4j.Logger;

public class GameFrame extends javax.swing.JFrame {
    
    /* This is the main window. All navigations in the game are done through this.*/

    private Board board;
    private Player player1, player2;
    protected int difficulity;   // need for Computer in single player mode. This will be the depth of minmax algorithm
    protected int gameMode;      // 0 for network game, 1 for single player, 2 for 2 player
    private DBHandler dbHandler;    // database oprations handling object
    private NetworkHandler networkHandler;  // network operations handling object
    private PlayerListModel playerList;     // list that allows the user to choose players from database
    protected boolean savePlayer1, savePlayer2;     // if these are true corresponding new players will be saved in database
    private boolean player1Selected, player2Selected;   // to check whether player is a new one or already exiting one
    private boolean newGame;
    protected int player1winStat, player2WinStat, tieStat;  // stores statistics for current session

    private static Logger logger = Logger.getLogger(GameFrame.class);
    
    public GameFrame() {
        this.difficulity = 4;   // normal difficulty
        this.savePlayer1 = true;
        this.savePlayer2 = true;
        this.newGame = true;
        player1winStat = 0; player2WinStat = 0; tieStat = 0;
        dbHandler = DBHandler.getInstance();
        dbHandler.refresh();
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());    // set current system's look and feel
        } catch (ClassNotFoundException ex) {
            logger.error(ex);
        } catch (InstantiationException ex) {
            logger.error(ex);
        } catch (IllegalAccessException ex) {
            logger.error(ex);
        } catch (UnsupportedLookAndFeelException ex) {
            logger.error(ex);
        }
        initComponents();
        playerList = new PlayerListModel();
        lstPlayers.setModel(playerList);
        playerList.setNames(dbHandler.getPlayersNames());   // initialize the player list with players names available in database
        lstPlayers.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                /* same panel is shown for select player 1 and player 2. the only difference is the text in the header label
                   of "select player panel". It can be checked by ending with 1 or 2 */
                if (lblSelectPlayerHeader.getText().endsWith("1")) {    // player 1 is selected
                    player1Selected = true;
                } else {           // player 2 is selected
                    player2Selected = true;
                }
                txtPlayerFromList.setText((String) lstPlayers.getSelectedValue());  // get the name from list and set to selected player text
            }
        });
        /* show only the start panel */
        pnlSelectPlayer.setVisible(false);
        pnlScore.setVisible(false);
        pnlBoard.setVisible(false);
        pnlSettings.setVisible(false);
        pnlNetwork.setVisible(false);
        pnlStart.setVisible(true);
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
        btnTwoPlayer = new javax.swing.JButton();
        btnSinglePlayer = new javax.swing.JButton();
        btnNetworkGame = new javax.swing.JButton();
        btnStatistics = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        pnlScore = new javax.swing.JPanel();
        lblStatus = new javax.swing.JLabel();
        lblPlayer1Win = new javax.swing.JLabel();
        lblPlayer2Wins = new javax.swing.JLabel();
        lblPlayer1Stat = new javax.swing.JLabel();
        lblPlayer2Stat = new javax.swing.JLabel();
        lblTies = new javax.swing.JLabel();
        lblTiesStat = new javax.swing.JLabel();
        btnNextRound = new javax.swing.JButton();
        btnMainMenu = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btnOverallStats = new javax.swing.JButton();
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
        jLabel10 = new javax.swing.JLabel();
        chkBoxSavePlayer2 = new javax.swing.JCheckBox();
        chkBoxSavePlayer1 = new javax.swing.JCheckBox();
        pnlBoard = new javax.swing.JPanel();
        pnlSelectPlayer = new javax.swing.JPanel();
        lblSelectPlayerHeader = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstPlayers = new javax.swing.JList();
        btnOK = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        txtPlayerFromList = new javax.swing.JTextField();
        pnlNetwork = new javax.swing.JPanel();
        btnHostGame = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        txtIPJoin = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtPort = new javax.swing.JTextField();
        btnJoinGame = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        lblNetworkStatus = new javax.swing.JLabel();
        lblPortHost = new javax.swing.JLabel();
        btnStartNetworkGame = new javax.swing.JButton();
        lblWaiting = new javax.swing.JLabel();
        txtNetPlayerName = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        btnNetSelectFromList = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();

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

        btnTwoPlayer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/Two_Player.jpg"))); // NOI18N
        btnTwoPlayer.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnTwoPlayer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTwoPlayerActionPerformed(evt);
            }
        });

        btnSinglePlayer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/Single_Player.jpg"))); // NOI18N
        btnSinglePlayer.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnSinglePlayer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSinglePlayerActionPerformed(evt);
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
                        .addComponent(btnTwoPlayer)
                        .addGap(30, 30, 30)
                        .addComponent(btnSinglePlayer))
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
                    .addComponent(btnTwoPlayer)
                    .addComponent(btnSinglePlayer))
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

        lblPlayer1Win.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblPlayer1Win.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblPlayer1Win.setText("Player 1 Win :");

        lblPlayer2Wins.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblPlayer2Wins.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblPlayer2Wins.setText("Player 2 Win :");

        lblPlayer1Stat.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblPlayer1Stat.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPlayer1Stat.setText("0");
        lblPlayer1Stat.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblPlayer2Stat.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblPlayer2Stat.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPlayer2Stat.setText("0");
        lblPlayer2Stat.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblTies.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTies.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTies.setText("Ties :");

        lblTiesStat.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTiesStat.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTiesStat.setText("0");
        lblTiesStat.setBorder(javax.swing.BorderFactory.createEtchedBorder());

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

        jLabel1.setText("Statistics for this session:");

        btnOverallStats.setText("View Overall Statistics");
        btnOverallStats.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOverallStatsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlScoreLayout = new javax.swing.GroupLayout(pnlScore);
        pnlScore.setLayout(pnlScoreLayout);
        pnlScoreLayout.setHorizontalGroup(
            pnlScoreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlScoreLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(btnMainMenu)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addComponent(btnNextRound)
                .addGap(31, 31, 31))
            .addGroup(pnlScoreLayout.createSequentialGroup()
                .addGroup(pnlScoreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlScoreLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pnlScoreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblPlayer1Win, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblPlayer2Wins, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTies, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(pnlScoreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lblPlayer2Stat, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblPlayer1Stat, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblTiesStat, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlScoreLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1))
                    .addGroup(pnlScoreLayout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addComponent(btnOverallStats)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlScoreLayout.setVerticalGroup(
            pnlScoreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlScoreLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(lblStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlScoreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPlayer1Win)
                    .addComponent(lblPlayer1Stat))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlScoreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPlayer2Wins)
                    .addComponent(lblPlayer2Stat))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlScoreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTies)
                    .addComponent(lblTiesStat))
                .addGap(32, 32, 32)
                .addComponent(btnOverallStats, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
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
        rBtnHard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rBtnHardActionPerformed(evt);
            }
        });

        btnGroupDifficulty.add(rBtnNormal);
        rBtnNormal.setSelected(true);
        rBtnNormal.setText("Normal");
        rBtnNormal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rBtnNormalActionPerformed(evt);
            }
        });

        btnGroupDifficulty.add(rBtnEasy);
        rBtnEasy.setText("Easy");
        rBtnEasy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rBtnEasyActionPerformed(evt);
            }
        });

        jLabel6.setText("Player 1 Name :");

        jLabel7.setText("Player 2 Name :");

        txtPlayer1Name.setText("NewPlayer1");
        txtPlayer1Name.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPlayer1NameKeyReleased(evt);
            }
        });

        txtPlayer2Name.setText("NewPlayer2");
        txtPlayer2Name.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPlayer2NameKeyReleased(evt);
            }
        });

        btnSelectPlayer1.setText("Select from List");
        btnSelectPlayer1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelectPlayer1ActionPerformed(evt);
            }
        });

        btnSelectPlayer2.setText("Select from list");
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

        jLabel10.setText("Enter name or press the button to select from already saved players");

        chkBoxSavePlayer2.setSelected(true);
        chkBoxSavePlayer2.setText("Add me to players list and save my statistics");
        chkBoxSavePlayer2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkBoxSavePlayer2ActionPerformed(evt);
            }
        });

        chkBoxSavePlayer1.setSelected(true);
        chkBoxSavePlayer1.setText("Add me to players list and save my statistics");
        chkBoxSavePlayer1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkBoxSavePlayer1ActionPerformed(evt);
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
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(pnlSettingsLayout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(pnlSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(chkBoxSavePlayer1)
                                    .addGroup(pnlSettingsLayout.createSequentialGroup()
                                        .addComponent(txtPlayer1Name, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnSelectPlayer1))))))
                    .addGroup(pnlSettingsLayout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addGroup(pnlSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlSettingsLayout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(pnlSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(rBtnHard)
                                        .addComponent(rBtnNormal)
                                        .addComponent(rBtnEasy)))
                                .addComponent(lblDifficulty))
                            .addComponent(btnStartGame, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(18, Short.MAX_VALUE))
            .addGroup(pnlSettingsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlSettingsLayout.createSequentialGroup()
                        .addComponent(txtPlayer2Name)
                        .addGap(18, 18, 18)
                        .addComponent(btnSelectPlayer2, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))
                    .addGroup(pnlSettingsLayout.createSequentialGroup()
                        .addComponent(chkBoxSavePlayer2)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        pnlSettingsLayout.setVerticalGroup(
            pnlSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSettingsLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSelectPlayer1)
                    .addComponent(txtPlayer1Name, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkBoxSavePlayer1)
                .addGap(18, 18, 18)
                .addGroup(pnlSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPlayer2Name, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSelectPlayer2))
                .addGap(3, 3, 3)
                .addComponent(chkBoxSavePlayer2)
                .addGap(18, 18, 18)
                .addComponent(lblDifficulty, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rBtnEasy)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rBtnNormal)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rBtnHard)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(btnStartGame, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39))
        );

        pnlBoard.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tic Tac Toe Board", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N
        pnlBoard.setMaximumSize(new java.awt.Dimension(374, 413));
        pnlBoard.setMinimumSize(new java.awt.Dimension(374, 413));

        javax.swing.GroupLayout pnlBoardLayout = new javax.swing.GroupLayout(pnlBoard);
        pnlBoard.setLayout(pnlBoardLayout);
        pnlBoardLayout.setHorizontalGroup(
            pnlBoardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 362, Short.MAX_VALUE)
        );
        pnlBoardLayout.setVerticalGroup(
            pnlBoardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 390, Short.MAX_VALUE)
        );

        pnlSelectPlayer.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblSelectPlayerHeader.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblSelectPlayerHeader.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSelectPlayerHeader.setText("Select Player");

        jLabel9.setText("Select from players list :");

        lstPlayers.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
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

        txtPlayerFromList.setText("Type to search...");
        txtPlayerFromList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtPlayerFromListMouseClicked(evt);
            }
        });
        txtPlayerFromList.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPlayerFromListKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout pnlSelectPlayerLayout = new javax.swing.GroupLayout(pnlSelectPlayer);
        pnlSelectPlayer.setLayout(pnlSelectPlayerLayout);
        pnlSelectPlayerLayout.setHorizontalGroup(
            pnlSelectPlayerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSelectPlayerLayout.createSequentialGroup()
                .addGroup(pnlSelectPlayerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlSelectPlayerLayout.createSequentialGroup()
                        .addGap(96, 96, 96)
                        .addComponent(lblSelectPlayerHeader, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlSelectPlayerLayout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addGroup(pnlSelectPlayerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlSelectPlayerLayout.createSequentialGroup()
                                .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnOK, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlSelectPlayerLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtPlayerFromList, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(69, Short.MAX_VALUE))
        );
        pnlSelectPlayerLayout.setVerticalGroup(
            pnlSelectPlayerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSelectPlayerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblSelectPlayerHeader, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlSelectPlayerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPlayerFromList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlSelectPlayerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnBack, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnOK, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        pnlNetwork.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnHostGame.setText("Host Game");
        btnHostGame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHostGameActionPerformed(evt);
            }
        });

        jLabel11.setText("IP Address :");

        txtIPJoin.setText("localhost");

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel12.setText("Port :");

        btnJoinGame.setText("Join Game");
        btnJoinGame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnJoinGameActionPerformed(evt);
            }
        });

        jLabel13.setText("Click Host Game button to create a server.");

        jLabel14.setText("Or enter following details from a host and click Join Game button");

        jLabel15.setText("to join a server.");

        lblNetworkStatus.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        btnStartNetworkGame.setText("Start Game");
        btnStartNetworkGame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStartNetworkGameActionPerformed(evt);
            }
        });

        lblWaiting.setText("Waiting for another player...");

        txtNetPlayerName.setText("NewPlayer");
        txtNetPlayerName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNetPlayerNameKeyReleased(evt);
            }
        });

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Player Name :");

        btnNetSelectFromList.setText("Select from List");

        jLabel5.setText("Enter player name or select a player first.");

        jCheckBox1.setText("Add me to players list and save my statistics");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlNetworkLayout = new javax.swing.GroupLayout(pnlNetwork);
        pnlNetwork.setLayout(pnlNetworkLayout);
        pnlNetworkLayout.setHorizontalGroup(
            pnlNetworkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNetworkLayout.createSequentialGroup()
                .addGroup(pnlNetworkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlNetworkLayout.createSequentialGroup()
                        .addGroup(pnlNetworkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pnlNetworkLayout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtPort, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlNetworkLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtIPJoin, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(btnJoinGame, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34))
                    .addGroup(pnlNetworkLayout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addGroup(pnlNetworkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblPortHost, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblWaiting))
                        .addGap(48, 48, 48))
                    .addGroup(pnlNetworkLayout.createSequentialGroup()
                        .addGroup(pnlNetworkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlNetworkLayout.createSequentialGroup()
                                .addGap(133, 133, 133)
                                .addComponent(btnStartNetworkGame, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlNetworkLayout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addGroup(pnlNetworkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addGroup(pnlNetworkLayout.createSequentialGroup()
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(pnlNetworkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jCheckBox1)
                                            .addGroup(pnlNetworkLayout.createSequentialGroup()
                                                .addComponent(txtNetPlayerName, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(btnNetSelectFromList))))))
                            .addGroup(pnlNetworkLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(pnlNetworkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel13)
                                    .addComponent(btnHostGame)))
                            .addGroup(pnlNetworkLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(pnlNetworkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel14)
                                    .addComponent(jLabel15))))
                        .addGap(0, 20, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(pnlNetworkLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblNetworkStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlNetworkLayout.setVerticalGroup(
            pnlNetworkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNetworkLayout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlNetworkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNetPlayerName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(btnNetSelectFromList))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnHostGame)
                .addGap(18, 18, 18)
                .addComponent(jLabel14)
                .addGroup(pnlNetworkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlNetworkLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlNetworkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtIPJoin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlNetworkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12)))
                    .addGroup(pnlNetworkLayout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(btnJoinGame)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addComponent(lblNetworkStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblPortHost, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblWaiting, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(btnStartNetworkGame)
                .addGap(11, 11, 11))
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
            .addGroup(pnlRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlRightLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(pnlNetwork, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        pnlRightLayout.setVerticalGroup(
            pnlRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRightLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlBoard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlRightLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(pnlSettings, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
            .addGroup(pnlRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlRightLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(pnlSelectPlayer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addGroup(pnlRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlRightLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(pnlNetwork, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
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

    private void btnSinglePlayerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSinglePlayerActionPerformed
        gameMode = 1;
        pnlSelectPlayer.setVisible(false);
        pnlNetwork.setVisible(false);
        lblDifficulty.setVisible(true);
        rBtnEasy.setVisible(true);
        rBtnNormal.setVisible(true);
        rBtnHard.setVisible(true);
        pnlSettings.setVisible(true);
        txtPlayer1Name.setText("NewPlayer1");
        txtPlayer2Name.setText("Computer");
        txtPlayer2Name.setEditable(false);
        chkBoxSavePlayer1.setVisible(true);
        chkBoxSavePlayer2.setVisible(false);
        btnSelectPlayer2.setEnabled(false);
        savePlayer2 = false;
        player2Selected = false;
    }//GEN-LAST:event_btnSinglePlayerActionPerformed

    private void btnTwoPlayerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTwoPlayerActionPerformed
        gameMode = 2;
        pnlSelectPlayer.setVisible(false);
        pnlNetwork.setVisible(false);
        lblDifficulty.setVisible(false);
        rBtnEasy.setVisible(false);
        rBtnNormal.setVisible(false);
        rBtnHard.setVisible(false);
        pnlSettings.setVisible(true);
        txtPlayer1Name.setText("NewPlayer1");
        txtPlayer2Name.setText("NewPlayer2");
        txtPlayer2Name.setEditable(true);
        chkBoxSavePlayer1.setVisible(true);
        chkBoxSavePlayer2.setVisible(true);
        btnSelectPlayer2.setEnabled(true);
    }//GEN-LAST:event_btnTwoPlayerActionPerformed

    private void btnNextRoundActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextRoundActionPerformed
        Player temp = player1;          // swap players
        player1 = player2;
        player2 = temp;
        player1.resetAllignment();
        player1.setSymbol("X");
        player2.resetAllignment();
        player2.setSymbol("O");
        boolean tempBool = savePlayer1;     // savePlayer1, savePlayer2 are swapped because they are cheked to load players for overall stats
        savePlayer1 = savePlayer2;
        savePlayer2 = tempBool;
        tempBool = player1Selected;
        player1Selected = player2Selected;
        player2Selected = tempBool;
        int tempVal = player1winStat;
        player1winStat = player2WinStat;
        player2WinStat = tempVal;
        btnStartGameActionPerformed(null);
        btnNextRound.setEnabled(false);
    }//GEN-LAST:event_btnNextRoundActionPerformed

    private void btnSelectPlayer2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelectPlayer2ActionPerformed
        lblSelectPlayerHeader.setText("Select Player 2");
        savePlayer2 = false;
        txtPlayerFromList.setText("Type to search...");
        playerList.setNames(dbHandler.getPlayersNames());
        pnlSettings.setVisible(false);
        pnlSelectPlayer.setVisible(true);
    }//GEN-LAST:event_btnSelectPlayer2ActionPerformed

    private void btnNetworkGameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNetworkGameActionPerformed
        gameMode = 0;
        networkHandler = new NetworkHandler();
        pnlSettings.setVisible(false);
        pnlSelectPlayer.setVisible(false);
        pnlNetwork.setVisible(true);
        lblWaiting.setVisible(false);
        btnStartNetworkGame.setEnabled(false);
    }//GEN-LAST:event_btnNetworkGameActionPerformed

    private void btnStatisticsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStatisticsActionPerformed
        this.setEnabled(false);
        PlayersFrame pf = new PlayersFrame(this, dbHandler, false);
        pf.setUp();
        pf.setLocationRelativeTo(null);
        pf.setResizable(false);
        pf.setVisible(true);
    }//GEN-LAST:event_btnStatisticsActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        int response = JOptionPane.showConfirmDialog(this, "Are you sure you want to exit?", "Exit Game", JOptionPane.YES_NO_OPTION);
        if (response == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }//GEN-LAST:event_btnExitActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        btnExitActionPerformed(null);
    }//GEN-LAST:event_formWindowClosing

    private void btnSelectPlayer1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelectPlayer1ActionPerformed
        lblSelectPlayerHeader.setText("Select Player 1");
        savePlayer1 = false;
        txtPlayerFromList.setText("Type to search...");
        playerList.setNames(dbHandler.getPlayersNames());
        pnlSettings.setVisible(false);
        pnlSelectPlayer.setVisible(true);
    }//GEN-LAST:event_btnSelectPlayer1ActionPerformed

    private void btnOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOKActionPerformed
        if (lblSelectPlayerHeader.getText().endsWith("1")) {
            if (!player1Selected) {
                JOptionPane.showMessageDialog(this, "No player selected. Please select a player", "Invalid Name", JOptionPane.WARNING_MESSAGE);
                return;
            }
            txtPlayer1Name.setText(txtPlayerFromList.getText());
            chkBoxSavePlayer1.setVisible(false);
        } else {
            if (!player2Selected) {
                JOptionPane.showMessageDialog(this, "No player selected. Please select a player", "Invalid Name", JOptionPane.WARNING_MESSAGE);
                return;
            }
            txtPlayer2Name.setText(txtPlayerFromList.getText());
            chkBoxSavePlayer2.setVisible(false);
        }
        pnlSelectPlayer.setVisible(false);
        pnlNetwork.setVisible(false);
        pnlSettings.setVisible(true);
    }//GEN-LAST:event_btnOKActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        if (player1Selected) {
            chkBoxSavePlayer1.setVisible(false);
        } else if(player2Selected){
            chkBoxSavePlayer2.setVisible(false);
        }
        pnlNetwork.setVisible(false);
        pnlSelectPlayer.setVisible(false);
        pnlSettings.setVisible(true);
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnStartGameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStartGameActionPerformed
        if (newGame) {
            if (!isValidName()) {
                return;
            }
            if (gameMode == 1) {
                if (player1Selected) {
                    player1 = dbHandler.getPlayerByName(txtPlayer1Name.getText());
                    player1.setSymbol("X");
                } else {
                    player1 = new Human(txtPlayer1Name.getText(), "X");
                }
                player2 = new Computer(txtPlayer2Name.getText(), "O", difficulity);
            } else if (gameMode == 2) {
                if (player1Selected) {
                    player1 = dbHandler.getPlayerByName(txtPlayer1Name.getText());
                    player1.setSymbol("X");
                } else {
                    player1 = new Human(txtPlayer1Name.getText(), "X");
                }
                if (player2Selected) {
                    player2 = dbHandler.getPlayerByName(txtPlayer2Name.getText());
                    player2.setSymbol("O");
                } else {
                    player2 = new Human(txtPlayer2Name.getText(), "O");
                }
            }
            else{
                if (networkHandler.isClient()){
                    player1 = new Human("Server","X");
                    player2 = new Human(txtNetPlayerName.getText(),"O");
                    new Thread(){
                        @Override
                        public void run(){
                            int temp = networkHandler.recieveMove();
                            System.out.println(temp);
                            board.networkPlay(temp);
                        }
                    }.start();
                }else{
                    player1 = new Human(txtNetPlayerName.getText(),"X");
                    player2 = new Human("Client","O");
                }
            }
            if (savePlayer1) {
                new Thread() {
                    @Override
                    public void run() {
                        dbHandler.addNewPlayer((Human) player1);
                    }
                }.start();
            }
            if (savePlayer2) {
                new Thread() {
                    @Override
                    public void run() {
                        dbHandler.addNewPlayer((Human) player2);
                    }
                }.start();
            }
            newGame = false;
        }
        lblPlayer1Stat.setText(Integer.toString(player1winStat));
        lblPlayer2Stat.setText(Integer.toString(player2WinStat));
        lblTiesStat.setText(Integer.toString(tieStat));
        lblPlayer1Win.setText(player1.getName() + " wins :");
        lblPlayer2Wins.setText(player2.getName() + " wins :");
        pnlBoard.removeAll();
        addBoard();
        lblStatus.setText(player1.getName() + "'s turn - X");
        pnlSelectPlayer.setVisible(false);
        pnlNetwork.setVisible(false);
        pnlSettings.setVisible(false);
        pnlStart.setVisible(false);
        pnlBoard.setVisible(true);
        pnlScore.setVisible(true);
        board.drawBoard();
        if (player1.getName().equals("Computer")) {
            board.playComputerFirst();
        }
    }//GEN-LAST:event_btnStartGameActionPerformed

    private void btnMainMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMainMenuActionPerformed
        difficulity = 3;
        savePlayer1 = true; savePlayer2 = true;
        player1Selected = false; player2Selected = false;
        newGame = true;
        player1winStat = 0; player2WinStat = 0; tieStat = 0;
        chkBoxSavePlayer1.setSelected(true);
        chkBoxSavePlayer2.setSelected(true);
        pnlBoard.setVisible(false);
        pnlScore.setVisible(false);
        pnlSettings.setVisible(false);
        pnlSelectPlayer.setVisible(false);
        pnlNetwork.setVisible(false);
        pnlStart.setVisible(true);
    }//GEN-LAST:event_btnMainMenuActionPerformed

    private void rBtnEasyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rBtnEasyActionPerformed
        difficulity = 2;
    }//GEN-LAST:event_rBtnEasyActionPerformed

    private void rBtnNormalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rBtnNormalActionPerformed
        difficulity = 4;
    }//GEN-LAST:event_rBtnNormalActionPerformed

    private void rBtnHardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rBtnHardActionPerformed
        difficulity = 6;
    }//GEN-LAST:event_rBtnHardActionPerformed

    private void btnHostGameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHostGameActionPerformed
        lblWaiting.setVisible(true);
        btnJoinGame.setEnabled(false);
        txtIPJoin.setEditable(false);
        txtPort.setEditable(false);
        int port = networkHandler.startServer();
        lblPortHost.setText("Port : "+Integer.toString(port));
        new Thread(){
            @Override
            public void run(){
                networkHandler.acceptPlayer();
                btnStartNetworkGame.setEnabled(true);
            }
        }.start();
    }//GEN-LAST:event_btnHostGameActionPerformed

    private void btnJoinGameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnJoinGameActionPerformed
        networkHandler.connectToServer(txtIPJoin.getText(),Integer.parseInt(txtPort.getText()));
        btnStartNetworkGame.setEnabled(true);
    }//GEN-LAST:event_btnJoinGameActionPerformed

    private void chkBoxSavePlayer1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkBoxSavePlayer1ActionPerformed
        savePlayer1 = chkBoxSavePlayer1.isSelected();
    }//GEN-LAST:event_chkBoxSavePlayer1ActionPerformed

    private void chkBoxSavePlayer2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkBoxSavePlayer2ActionPerformed
        savePlayer2 = chkBoxSavePlayer2.isSelected();
    }//GEN-LAST:event_chkBoxSavePlayer2ActionPerformed

    private void txtPlayerFromListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtPlayerFromListMouseClicked
        if (txtPlayerFromList.getText().equals("Type to search...")) {
            txtPlayerFromList.setText("");
        }
    }//GEN-LAST:event_txtPlayerFromListMouseClicked

    private void txtPlayerFromListKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPlayerFromListKeyReleased
        if (evt.getKeyCode() != KeyEvent.VK_ENTER) {
            player1Selected = false;
            playerList.setSelectedNames(txtPlayerFromList.getText());
        }
    }//GEN-LAST:event_txtPlayerFromListKeyReleased

    private void btnOverallStatsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOverallStatsActionPerformed
        this.setEnabled(false);
        PlayersFrame pf = new PlayersFrame(this, dbHandler, true);
        pf.setLocationRelativeTo(null);
        pf.setResizable(false);
        Human temp1 = null, temp2 = null;
        if (savePlayer1 || player1Selected) {
            temp1 = (Human) player1;
        }
        if (savePlayer2 || player2Selected) {
            temp2 = (Human) player2;
        }
        pf.setUp(temp1, temp2);
        pf.setVisible(true);
    }//GEN-LAST:event_btnOverallStatsActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        if (networkHandler.isClient()){savePlayer2 = jCheckBox1.isSelected();}
        else{
            savePlayer1 = jCheckBox1.isSelected();
        }
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void txtPlayer1NameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPlayer1NameKeyReleased
        chkBoxSavePlayer1.setVisible(true);
        player1Selected = false;
    }//GEN-LAST:event_txtPlayer1NameKeyReleased

    private void txtPlayer2NameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPlayer2NameKeyReleased
        chkBoxSavePlayer2.setVisible(true);
        player2Selected = false;
    }//GEN-LAST:event_txtPlayer2NameKeyReleased

    private void txtNetPlayerNameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNetPlayerNameKeyReleased
        if (networkHandler.isClient()){
            player2Selected = false;
        }else{
            player1Selected = false;
        }
        jCheckBox1.setVisible(newGame);
    }//GEN-LAST:event_txtNetPlayerNameKeyReleased

    private void btnStartNetworkGameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStartNetworkGameActionPerformed
        savePlayer1 = false;
        savePlayer2 = false;
        btnStartGameActionPerformed(evt);
    }//GEN-LAST:event_btnStartNetworkGameActionPerformed

    private boolean isValidName() {
        if (txtPlayer1Name.getText().equals("") || txtPlayer2Name.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Player name empty!\nPleas enter a name.", "Invalid Name", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (savePlayer1 && dbHandler.isAlreadySaved(txtPlayer1Name.getText())) {
            JOptionPane.showMessageDialog(this, "Player "+txtPlayer1Name.getText()+" already exist.\nTry a different name", "Invalid Name", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (savePlayer2 && dbHandler.isAlreadySaved(txtPlayer2Name.getText())) {
            JOptionPane.showMessageDialog(this, "Player "+txtPlayer2Name.getText()+" already exist.\nTry a different name", "Invalid Name", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (txtPlayer1Name.getText().equals(txtPlayer2Name.getText())){
            JOptionPane.showMessageDialog(this, "Player 1 and Player 2 names are same.\nPlease enter different names", "Invalid Name", JOptionPane.WARNING_MESSAGE);
        }
        return true;
    }

    public static void main(String args[]) {
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GameFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnExit;
    private javax.swing.ButtonGroup btnGroupDifficulty;
    private javax.swing.JButton btnHostGame;
    private javax.swing.JButton btnJoinGame;
    private javax.swing.JButton btnMainMenu;
    private javax.swing.JButton btnNetSelectFromList;
    private javax.swing.JButton btnNetworkGame;
    protected javax.swing.JButton btnNextRound;
    private javax.swing.JButton btnOK;
    private javax.swing.JButton btnOverallStats;
    private javax.swing.JButton btnSelectPlayer1;
    private javax.swing.JButton btnSelectPlayer2;
    private javax.swing.JButton btnSinglePlayer;
    private javax.swing.JButton btnStartGame;
    private javax.swing.JButton btnStartNetworkGame;
    private javax.swing.JButton btnStatistics;
    private javax.swing.JButton btnTwoPlayer;
    private javax.swing.JCheckBox chkBoxSavePlayer1;
    private javax.swing.JCheckBox chkBoxSavePlayer2;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblDifficulty;
    private javax.swing.JLabel lblNetworkStatus;
    protected javax.swing.JLabel lblPlayer1Stat;
    protected javax.swing.JLabel lblPlayer1Win;
    protected javax.swing.JLabel lblPlayer2Stat;
    protected javax.swing.JLabel lblPlayer2Wins;
    private javax.swing.JLabel lblPortHost;
    private javax.swing.JLabel lblSelectPlayerHeader;
    protected javax.swing.JLabel lblStatus;
    private javax.swing.JLabel lblTies;
    protected javax.swing.JLabel lblTiesStat;
    private javax.swing.JLabel lblWaiting;
    private javax.swing.JList lstPlayers;
    private javax.swing.JPanel pnlBoard;
    private javax.swing.JPanel pnlHeader;
    private javax.swing.JPanel pnlLeft;
    private javax.swing.JPanel pnlNetwork;
    private javax.swing.JPanel pnlRight;
    private javax.swing.JPanel pnlScore;
    private javax.swing.JPanel pnlSelectPlayer;
    private javax.swing.JPanel pnlSettings;
    private javax.swing.JPanel pnlStart;
    private javax.swing.JRadioButton rBtnEasy;
    private javax.swing.JRadioButton rBtnHard;
    private javax.swing.JRadioButton rBtnNormal;
    private javax.swing.JTextField txtIPJoin;
    private javax.swing.JTextField txtNetPlayerName;
    private javax.swing.JTextField txtPlayer1Name;
    private javax.swing.JTextField txtPlayer2Name;
    private javax.swing.JTextField txtPlayerFromList;
    private javax.swing.JTextField txtPort;
    // End of variables declaration//GEN-END:variables

    private void addBoard() {
        board = new Board(this, player1, player2, dbHandler);
        board.setBounds(40, 55, 300, 300);
        pnlBoard.add(board);
        board.addMouseListener(board);
        board.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        if (gameMode==0){
            board.setNetworkHandler(networkHandler);
            networkHandler.setBoard(board);
            if (networkHandler.isClient()){
                board.setnetMyTurn(false);
            }else{
                board.setnetMyTurn(true);
            }
        }
    }
    
    public void updateNetworkStatus(int status){
        switch (status) {
            case 0:
                lblWaiting.setVisible(true);
                btnJoinGame.setEnabled(false);
                txtIPJoin.setEditable(false);
                txtPort.setEditable(false);
                btnStartNetworkGame.setEnabled(true);
                break;
            case 1:
                btnStartNetworkGame.setEnabled(true);
        }
    }
    
}
