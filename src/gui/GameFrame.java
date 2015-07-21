
package gui;

import Tic_Tac_Toe_Game.Computer;
import Tic_Tac_Toe_Game.Human;
import Tic_Tac_Toe_Game.Player;
import io.DBHandler;
import java.awt.Cursor;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class GameFrame extends javax.swing.JFrame{
    
    private Board board;
    private Player player1,player2;
    protected int difficulity;
    protected int gameMode;      // 0 for network game, 1 for single player, 2 for 2 player
    private ServerSocket server;
    private Socket socket;
    boolean accepted = false;
    private DBHandler dbHandler;
    private PlayerListModel playerList;
    private boolean savePlayer1,savePlayer2;
    private boolean player1Selected,player2Selected;
    
    public GameFrame() {
        this.difficulity = 3;
        this.savePlayer1 = true; this.savePlayer2 = true;
        dbHandler = DBHandler.getInstance();
        dbHandler.refresh();
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
        playerList = new PlayerListModel();
        lstPlayers.setModel(playerList);
        playerList.setNames(dbHandler.getPlayersNames());
        lstPlayers.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (lblSelectPlayerHeader.getText().endsWith("1")){
                    player1Selected = true;
                }else{
                    player2Selected = true;
                }
                txtPlayerFromList.setText((String) lstPlayers.getSelectedValue());
            }
        });
        pnlSelectPlayer.setVisible(false);
        pnlScore.setVisible(false);
        pnlBoard.setVisible(false);
        pnlSettings.setVisible(false);
        pnlNetwork.setVisible(false);
        
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
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblPlayer1wins = new javax.swing.JLabel();
        lblPlayer2wins = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lblTies = new javax.swing.JLabel();
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
        jButton1 = new javax.swing.JButton();
        lblWaiting = new javax.swing.JLabel();

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

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Player 1 Win :");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Player 2 Win :");

        lblPlayer1wins.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblPlayer1wins.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPlayer1wins.setText("     ");
        lblPlayer1wins.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblPlayer2wins.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblPlayer2wins.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPlayer2wins.setText("    ");
        lblPlayer2wins.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("Drow times  :");

        lblTies.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTies.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTies.setText("  ");
        lblTies.setBorder(javax.swing.BorderFactory.createEtchedBorder());

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
                    .addComponent(lblPlayer2wins, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblPlayer1wins, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblTies, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                    .addComponent(lblPlayer1wins))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlScoreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(lblPlayer2wins))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlScoreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(lblTies))
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
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPlayer1NameKeyTyped(evt);
            }
        });

        txtPlayer2Name.setText("NewPlayer2");
        txtPlayer2Name.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPlayer2NameKeyTyped(evt);
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
        lstPlayers.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lstPlayersMouseClicked(evt);
            }
        });
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

        jButton1.setText("Start Game");

        lblWaiting.setText("Waiting for another player...");

        javax.swing.GroupLayout pnlNetworkLayout = new javax.swing.GroupLayout(pnlNetwork);
        pnlNetwork.setLayout(pnlNetworkLayout);
        pnlNetworkLayout.setHorizontalGroup(
            pnlNetworkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNetworkLayout.createSequentialGroup()
                .addGroup(pnlNetworkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlNetworkLayout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(pnlNetworkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlNetworkLayout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlNetworkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtIPJoin, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
                            .addComponent(txtPort, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnJoinGame, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlNetworkLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel15))
                    .addGroup(pnlNetworkLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(pnlNetworkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnHostGame)
                            .addComponent(jLabel13)
                            .addComponent(lblNetworkStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlNetworkLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel14)))
                .addContainerGap(12, Short.MAX_VALUE))
            .addGroup(pnlNetworkLayout.createSequentialGroup()
                .addGap(133, 133, 133)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlNetworkLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(pnlNetworkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPortHost, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblWaiting))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlNetworkLayout.setVerticalGroup(
            pnlNetworkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNetworkLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnHostGame)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblNetworkStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblPortHost, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblWaiting, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlNetworkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlNetworkLayout.createSequentialGroup()
                        .addGroup(pnlNetworkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtIPJoin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlNetworkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlNetworkLayout.createSequentialGroup()
                        .addComponent(btnJoinGame)
                        .addGap(12, 12, 12)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
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
        btnSelectPlayer2.setEnabled(false);
        chkBoxSavePlayer2.setVisible(false);
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
        String temp = txtPlayer1Name.getText();
        txtPlayer1Name.setText(txtPlayer2Name.getText());
        txtPlayer2Name.setText(temp);
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
        pnlSettings.setVisible(false);
        pnlSelectPlayer.setVisible(false);
        pnlNetwork.setVisible(true);
        lblWaiting.setVisible(false);
    }//GEN-LAST:event_btnNetworkGameActionPerformed

    private void btnStatisticsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStatisticsActionPerformed
        this.setEnabled(false);
        PlayersFrame pf = new PlayersFrame(this,dbHandler);
        pf.setUp();
        pf.setLocationRelativeTo(null);
        pf.setResizable(false);
        pf.setVisible(true);
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
        savePlayer1 = false;
        txtPlayerFromList.setText("Type to search...");
        playerList.setNames(dbHandler.getPlayersNames());
        pnlSettings.setVisible(false);
        pnlSelectPlayer.setVisible(true);
    }//GEN-LAST:event_btnSelectPlayer1ActionPerformed

    private void btnOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOKActionPerformed
        if (lblSelectPlayerHeader.getText().endsWith("1")){
            if (!player1Selected ){
                JOptionPane.showMessageDialog(this, "No player selected. Please select a player", "Invalid Name", JOptionPane.WARNING_MESSAGE);
                return;
            }
            txtPlayer1Name.setText(txtPlayerFromList.getText());
            chkBoxSavePlayer1.setVisible(false);
        }else{
            if (!player2Selected ){
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
        if (player1Selected){
            chkBoxSavePlayer1.setVisible(false);
            chkBoxSavePlayer2.setVisible(false);
        }else{
            chkBoxSavePlayer1.setVisible(true);
            chkBoxSavePlayer2.setVisible(true);
        }
        pnlNetwork.setVisible(false);
        pnlSelectPlayer.setVisible(false);
        pnlSettings.setVisible(true);
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnStartGameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStartGameActionPerformed
        if (!isValidName()){
            return;
        }
        if (gameMode==1){
            if (txtPlayer2Name.getText().equals("Computer")){
                player2 = new Computer(txtPlayer2Name.getText(), "O",difficulity);
                if (player1Selected){
                    player1 = dbHandler.getPlayerByName(txtPlayer1Name.getText());
                    player1.setSymbol("X");
                }else{
                    player1 = new Human(txtPlayer1Name.getText(),"X");
                }
            }else{
                player1 = new Computer(txtPlayer1Name.getText(), "X",difficulity);
                if (player2Selected){
                    player2 = dbHandler.getPlayerByName(txtPlayer2Name.getText());
                    player2.setSymbol("O");
                }else{
                    player2 = new Human(txtPlayer2Name.getText(),"O");
                }
            }            
        }else{
            if (player1Selected){
                player1 = dbHandler.getPlayerByName(txtPlayer1Name.getText());
                player1.setSymbol("X");
            }else{
                player1 = new Human(txtPlayer1Name.getText(),"X");
            }
            if (player2Selected){
                player2 = dbHandler.getPlayerByName(txtPlayer2Name.getText());
                player2.setSymbol("O");
            }else{
                player2 = new Human(txtPlayer2Name.getText(),"O");
            }
        }
        jLabel4.setText(txtPlayer1Name.getText()+" wins :");
        jLabel5.setText(txtPlayer2Name.getText()+"wins :");
        //lblPlayer1wins.setText();
        //lblPlayer2wins.setText(player2.getSymbol());
        pnlBoard.removeAll();
        addBoard();
        if (savePlayer1){
            new Thread(){
                public void run(){
                    dbHandler.addNewPlayer((Human) player1);
                }
            }.start();
        }
        if (savePlayer2){
            new Thread(){
                public void run(){
                    dbHandler.addNewPlayer((Human) player2);
                }
            }.start();
        }
        lblStatus.setText(player1.getName()+"'s turn - X");
        pnlSelectPlayer.setVisible(false);
        pnlSettings.setVisible(false);
        pnlStart.setVisible(false);
        pnlBoard.setVisible(true);
        pnlScore.setVisible(true);
        board.drawBoard();
        if (player1.getName().equals("Computer")){
            board.playComputerFirst();
        }
    }//GEN-LAST:event_btnStartGameActionPerformed

    private void btnMainMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMainMenuActionPerformed
        pnlBoard.setVisible(false);
        pnlScore.setVisible(false);
        pnlSettings.setVisible(false);
        pnlSelectPlayer.setVisible(false);
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
        try {
            server = new ServerSocket(0);
            /*InetAddress ip = server.getInetAddress();
            System.out.println(ip.toString());*/
            lblNetworkStatus.setText("Server started Successfully on following port.");
            lblPortHost.setText("Port : "+server.getLocalPort());
            System.out.println(server.getLocalPort());
            lblWaiting.setVisible(true);
            btnJoinGame.setEnabled(false);
            txtIPJoin.setEditable(false);
            txtPort.setEditable(false);
            GameFrame gf = this;
            new Thread(){
                public void run(){
                    try {
                        socket = server.accept();
                        accepted = true;
                        //notify();
                    } catch (IOException ex) {
                        Logger.getLogger(GameFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            };
        } catch (IOException ex) {
            lblNetworkStatus.setText("Sorry! Server cannot be started. Try again.");
            ex.printStackTrace();
        }
    }//GEN-LAST:event_btnHostGameActionPerformed

    private void btnJoinGameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnJoinGameActionPerformed
        try {
            socket = new Socket(txtIPJoin.getText(), Integer.parseInt(txtPort.getText()));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_btnJoinGameActionPerformed

    private void chkBoxSavePlayer1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkBoxSavePlayer1ActionPerformed
        savePlayer1 = chkBoxSavePlayer1.isSelected();
    }//GEN-LAST:event_chkBoxSavePlayer1ActionPerformed

    private void chkBoxSavePlayer2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkBoxSavePlayer2ActionPerformed
        savePlayer2 = chkBoxSavePlayer2.isSelected();
    }//GEN-LAST:event_chkBoxSavePlayer2ActionPerformed

    private void txtPlayer1NameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPlayer1NameKeyTyped
        chkBoxSavePlayer1.setVisible(true);
        chkBoxSavePlayer2.setVisible(true);
    }//GEN-LAST:event_txtPlayer1NameKeyTyped

    private void txtPlayer2NameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPlayer2NameKeyTyped
        chkBoxSavePlayer1.setVisible(true);
        chkBoxSavePlayer2.setVisible(true);
    }//GEN-LAST:event_txtPlayer2NameKeyTyped

    private void txtPlayerFromListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtPlayerFromListMouseClicked
        if (txtPlayerFromList.getText().equals("Type to search...")){
            txtPlayerFromList.setText("");
        }
    }//GEN-LAST:event_txtPlayerFromListMouseClicked

    private void lstPlayersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lstPlayersMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lstPlayersMouseClicked

    private void txtPlayerFromListKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPlayerFromListKeyReleased
        if (evt.getKeyCode()!=KeyEvent.VK_ENTER){
            player1Selected = false;
            playerList.setSelectedNames(txtPlayerFromList.getText());
            System.out.println(evt.getKeyCode());
        }
    }//GEN-LAST:event_txtPlayerFromListKeyReleased

    private boolean isValidName(){
        if (txtPlayer1Name.getText().equals("") || txtPlayer2Name.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Player name empty!\nPleas enter a name.", "Invalid Name", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (savePlayer1 && dbHandler.isAlreadySaved(txtPlayer1Name.getText())){
            JOptionPane.showMessageDialog(this, "A player already exist with same name.\nTry a different name","Invalid Name",JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (savePlayer2 && dbHandler.isAlreadySaved(txtPlayer2Name.getText())){
            JOptionPane.showMessageDialog(this, "A player already exist with same name.\nTry a different name","Invalid Name",JOptionPane.WARNING_MESSAGE);
            return false;
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
    private javax.swing.JButton btnNetworkGame;
    protected javax.swing.JButton btnNextRound;
    private javax.swing.JButton btnOK;
    private javax.swing.JButton btnSelectPlayer1;
    private javax.swing.JButton btnSelectPlayer2;
    private javax.swing.JButton btnSinglePlayer;
    private javax.swing.JButton btnStartGame;
    private javax.swing.JButton btnStatistics;
    private javax.swing.JButton btnTwoPlayer;
    private javax.swing.JCheckBox chkBoxSavePlayer1;
    private javax.swing.JCheckBox chkBoxSavePlayer2;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    protected javax.swing.JLabel jLabel4;
    protected javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblDifficulty;
    private javax.swing.JLabel lblNetworkStatus;
    protected javax.swing.JLabel lblPlayer1wins;
    protected javax.swing.JLabel lblPlayer2wins;
    private javax.swing.JLabel lblPortHost;
    private javax.swing.JLabel lblSelectPlayerHeader;
    protected javax.swing.JLabel lblStatus;
    protected javax.swing.JLabel lblTies;
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
    private javax.swing.JTextField txtPlayer1Name;
    private javax.swing.JTextField txtPlayer2Name;
    private javax.swing.JTextField txtPlayerFromList;
    private javax.swing.JTextField txtPort;
    // End of variables declaration//GEN-END:variables

    private void addBoard() {
        board = new Board(this,player1,player2,dbHandler);
        board.setBounds(40, 55, 300, 300);
        pnlBoard.add(board);
        board.addMouseListener(board);
        board.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

   

    
}   
