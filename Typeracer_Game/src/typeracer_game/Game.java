/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package typeracer_game;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;
import java.net.*;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Rashi
 */
public class Game extends javax.swing.JFrame {

    float current_speed;

    String users_word;
    int flag = 0;
    int start_time;
    int end_time;
    String para = null;
    int space = 0, error_count = 0;
    int character_count = 0, curr_error_count = 0,uncorr_error = 0;
    int count = 0, totalWords;
    boolean gameover=false;
    StringTokenizer words;
    String correct_word = "";
    static Socket socket;
    static DataInputStream in;
    static DataOutputStream out;
    String port;
    String Ip;
    DefaultTableModel model;
    float wpm;
    float accuracy;
    
    /**
     * Creates new form NewJFrame
     */
    public Game(String name, String ip, String port) {
        initComponents();
        showPara();
        this.Ip=ip;
        this.port=port;
        paraTextPane.setEditable(false);
        currentInputTF.requestFocus(true);
        words = new StringTokenizer(para);
        totalWords = words.countTokens();
        start_time = (int) System.currentTimeMillis();
        try {
            socket=new Socket(ip,Integer.valueOf(port));
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            Input input = new Input(in,out,name,this);
            Thread thread = new Thread(input);
            thread.start();
            
        } catch (IOException ex) {
            System.out.println("Unable to connect to server.");
            JOptionPane.showMessageDialog(rootPane, "Unable to connect to server.");
            System.exit(0);
        }
    }
    
    public void updatetable(List<String> onlineplylist){
        model = (DefaultTableModel) onlineTable.getModel();
        for(int i=0;i<onlineplylist.size();i++){
                    model.addRow(new Object[] {onlineplylist.get(i)});
                }
    }
    
    public void gameover(List<String> leaderborad, String name){
        
        GameOver gameover = new GameOver(leaderborad,name,wpm,accuracy,Ip,port);
        this.setVisible(false);
        gameover.setVisible(true);
        
    }
    
    public void updateleaderboard(List<String> onlineplylist){
        for(int i=0;i<onlineplylist.size();i++){
                    model.addRow(new Object[] {onlineplylist.get(i)});
                }
    }
    
    public boolean isgameover(){
        return gameover;
    }
    
    private void showPara() {
        String thisLine = null;
        int a;
        a = (int) (Math.random() * 10);
        System.out.println("a is " + a);
        try {
            // open input stream Para.txt for fetching require pragraphs.
            // Here paragraphs are stored as single lines.
            BufferedReader buffRdr = new BufferedReader(new FileReader(
                    "Para.txt"));
            while ((thisLine = buffRdr.readLine()) != null) {
                char ch = thisLine.charAt(0);
                thisLine = thisLine.substring(1);

                int ch1 = (ch - '0');
                if (ch1 == a) {
                    para = "";
                    para = para + thisLine;
                    System.out.println(para);
                    paraTextPane.setText(para);
                    return;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private float wpmcalc(int typed_entries, int uncorrected_errors){
        float time = (int) System.currentTimeMillis();
        float wpm=((typed_entries/5)-uncorrected_errors)*60000/(time-start_time);
        if(wpm<0){
            wpm=0;
        }
        return(wpm);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        finishButton = new javax.swing.JButton();
        currentInputTF = new javax.swing.JTextField();
        paraDisplayer = new javax.swing.JScrollPane();
        paraTextPane = new javax.swing.JTextPane();
        wpmLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        onlineTable = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        typedTextPane = new javax.swing.JTextPane();
        acclabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        finishButton.setText("Finish");
        finishButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                finishButtonActionPerformed(evt);
            }
        });

        currentInputTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                currentInputTFKeyTyped(evt);
            }
        });

        paraTextPane.setEditable(false);
        paraDisplayer.setViewportView(paraTextPane);

        wpmLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        wpmLabel.setText("WPM: 0.0 words");

        onlineTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Online Players"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(onlineTable);

        jScrollPane2.setViewportView(typedTextPane);

        acclabel.setText("Accuracy: 100 %");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(currentInputTF)
                            .addComponent(paraDisplayer, javax.swing.GroupLayout.DEFAULT_SIZE, 507, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(wpmLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(acclabel, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(94, 94, 94)
                                .addComponent(finishButton, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(10, 10, 10)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(paraDisplayer, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                        .addComponent(currentInputTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(wpmLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(acclabel, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(29, 29, 29)
                                .addComponent(finishButton, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(21, 21, 21))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE))))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void finishButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_finishButtonActionPerformed
        JOptionPane.showMessageDialog(rootPane, "You exit without completing game!!!");
        gameover=true;
    }//GEN-LAST:event_finishButtonActionPerformed

    private void currentInputTFKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_currentInputTFKeyTyped

        char ch = evt.getKeyChar();
        System.out.print(ch);
        character_count++;
        if(character_count > typedTextPane.getText().length()){
            character_count=typedTextPane.getText().length();
        }
        wpm=wpmcalc(character_count, uncorr_error);
        wpmLabel.setText("WPM: "+String.format("%1$.2f",wpm)+" words");
        accuracy = (float) ((float) (space - error_count) / (float) space) * 100;
        accuracy = (float) Math.ceil(accuracy);
        if(accuracy>100){
            accuracy = 100;
        }
        acclabel.setText("Accuracy: "+accuracy+" %");
        currentInputTF.setBackground(Color.WHITE);
        if (ch == KeyEvent.VK_SPACE) //Matches wor whenever you press space
        {
            space++;
            users_word = currentInputTF.getText();
            users_word = users_word.trim();
            if (flag == 0) {
                correct_word = words.nextToken();
                flag = 1;
            }
            if (users_word.equals(correct_word)) {
                typedTextPane.setText(typedTextPane.getText() + " " + correct_word);
                uncorr_error=0;
                currentInputTF.setBackground(Color.GREEN);
                count++;
                if (words.hasMoreTokens()) {
                    correct_word = words.nextToken();
                } else {                                         //The game is over.
                    gameover = true;
                    end_time = (int) System.currentTimeMillis();
                    acclabel.setText("Accuracy: "+accuracy+" %");
                }
            } else {
                error_count++;
                uncorr_error++;
                currentInputTF.setBackground(Color.red);
            }
            users_word = "";
            currentInputTF.setText("");
        }


    }//GEN-LAST:event_currentInputTFKeyTyped

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel acclabel;
    public javax.swing.JTextField currentInputTF;
    private javax.swing.JButton finishButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    public javax.swing.JTable onlineTable;
    private javax.swing.JScrollPane paraDisplayer;
    private javax.swing.JTextPane paraTextPane;
    private javax.swing.JTextPane typedTextPane;
    private javax.swing.JLabel wpmLabel;
    // End of variables declaration//GEN-END:variables
}

class Input implements Runnable {
    DataInputStream in;
    DataOutputStream out;
    String name="",onlineplayers="",leaderboard;
    List<String> onlineplylist,leaderboardlist;
    Game game;
    public Input(DataInputStream in,DataOutputStream out,String name,Game game){
        this.in=in;
        this.out=out;
        this.name=name;
        this.game=game;
    }
    public void run(){
        try{
            out.writeUTF("0");
            out.writeUTF(name);
            out.writeUTF("1");
            onlineplayers = in.readUTF();
            onlineplylist = Arrays.asList(onlineplayers.split(","));
            game.updatetable(onlineplylist);
            while(true){
                if(game.isgameover()){
                    break;
                }
                System.out.print(".");
                try {
                    Thread.sleep(10);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Input.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            out.writeUTF("2");
            out.writeUTF(name+":"+game.wpm);
            out.writeUTF("3");
            leaderboard = in.readUTF();
            leaderboardlist = Arrays.asList(leaderboard.split(","));
            out.writeUTF("4");
            out.writeUTF(name);
            game.gameover(leaderboardlist,name);
            
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}