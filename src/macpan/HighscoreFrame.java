/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package macpan;

import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import macpan.objects.Score;
import java.io.IOException; 
import javax.swing.ImageIcon;

public class HighscoreFrame extends javax.swing.JFrame {
    
    // Main window variable
    MenuFrame mainWindow;
    // Cherry Icon
    private Image icon = new ImageIcon("src/macpan/images/Consumables/cherry.png").getImage();
    // Global arraylist for scores
    static ArrayList scores = new ArrayList();
    
    /**
     * Creates the high score frame with gui builder
     * @param m - the main window
     */
    public HighscoreFrame(MenuFrame m) throws IOException {
        initComponents();
        //Set frame to middle of screen, title, and disallow rezizing
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("HI-SCORE List");
        setIconImage(icon);
        getContentPane().setBackground(Color.BLACK);
        mainWindow = m;
        readFile();
        quickSortD(0, scores.size()-1);
        top5();
    }
    
    /**
     * Reads file of scores
     * First line is score value 
     * Second line is player's initials
     * 
     */
    public static void readFile(){
        try {
            File f = new File("src/macpan/score.data");
            Scanner s = new Scanner(f);
            while (s.hasNextLine()){
                int value = Integer.parseInt(s.nextLine());
                String name = s.nextLine();
                Score score = new Score(value, name);
                scores.add(score);
            }
            
        } catch (FileNotFoundException e) {
            System.out.println("Error: " + e);
        }
    }
    
    /**
     * Quick Sort Descending
     * @param low - lowest index
     * @param high - highest index
     */
    public static void quickSortD(int low, int high) {
        if (low < high) {

            // pi is partitioning index, arr[p]
            // is now at right place
            int pi = partitionD(low, high);

            // Separately sort elements before
            // partition and after partition
            quickSortD(low, pi - 1);
            quickSortD(pi + 1, high);
        }
    }
    
    /**
     * Picks the pivot index puts all smaller values to its left and larger
     * values to its right
     * @param low - lowest index
     * @param high - highest index
     * @return - the index of the last pivot
     */
    public static int partitionD(int low, int high) {
        // sets pivot as last index
        Score pivot = (Score) scores.get(high);
        int pivotScore = pivot.getValue();
        // Index of smaller element and
        // indicates the right position
        // of pivot found so far
        int i = (low - 1);

        for (int j = low; j <= high - 1; j++) {
            // If current element is smaller
            // than the pivot
            Score current = (Score) scores.get(j);
            int currentScore = current.getValue();
            if (currentScore > pivotScore) { //if greater than pivot

                // Increment index of
                // smaller element
                i++;
                swap(i, j);
            }
        }
        swap(i + 1, high);
        return (i + 1);
    }
    
    /**
     * Swap function for quick sort
     * @param i - lower index number
     * @param j - higher index number
     */
    public static void swap(int i, int j) {
        Score temp = (Score) scores.get(i);
        scores.set(i, scores.get(j)); // sets them to the same value
        System.out.println(scores.get(i));
        scores.set(j, temp); // places the stored temporary value into higher index number
        System.out.println(scores.get(j));
    }
    
    public void top5(){
        // displaying highest score
        Score scoreOne = (Score) scores.get(0);
        int value = scoreOne.getValue();
        String name = scoreOne.getName();
        score1.setText("" + value);
        name1.setText(name);
        // displaying second highest score
        Score scoreTwo = (Score) scores.get(1);
        value = scoreTwo.getValue();
        name = scoreTwo.getName();
        score2.setText("" + value);
        name2.setText(name);
        // displaying third highest score
        Score scoreThree = (Score) scores.get(2);
        value = scoreThree.getValue();
        name = scoreThree.getName();
        score3.setText("" + value);
        name3.setText(name);
        // displaying fourth highest score
        Score scoreFour = (Score) scores.get(3);
        value = scoreFour.getValue();
        name = scoreFour.getName();
        score4.setText("" + value);
        name4.setText(name);
        // displaying fifth highest score
        Score scoreFive = (Score) scores.get(4);
        value = scoreFive.getValue();
        name = scoreFive.getName();
        score5.setText("" + value);
        name5.setText(name);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnBack = new javax.swing.JButton();
        HighScoreLabel = new javax.swing.JLabel();
        RankLabel = new javax.swing.JLabel();
        NameLabel = new javax.swing.JLabel();
        ScoreLabel = new javax.swing.JLabel();
        rank1 = new javax.swing.JLabel();
        rank2 = new javax.swing.JLabel();
        rank3 = new javax.swing.JLabel();
        rank4 = new javax.swing.JLabel();
        rank5 = new javax.swing.JLabel();
        name1 = new javax.swing.JLabel();
        name2 = new javax.swing.JLabel();
        name3 = new javax.swing.JLabel();
        name4 = new javax.swing.JLabel();
        name5 = new javax.swing.JLabel();
        score1 = new javax.swing.JLabel();
        score2 = new javax.swing.JLabel();
        score3 = new javax.swing.JLabel();
        score4 = new javax.swing.JLabel();
        score5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnBack.setBackground(new java.awt.Color(0, 0, 0));
        btnBack.setFont(new java.awt.Font("Monospaced", 1, 12)); // NOI18N
        btnBack.setForeground(new java.awt.Color(255, 222, 9));
        btnBack.setText("BACK");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        HighScoreLabel.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        HighScoreLabel.setForeground(new java.awt.Color(255, 222, 9));
        HighScoreLabel.setText("HIGH SCORES");

        RankLabel.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        RankLabel.setForeground(new java.awt.Color(255, 222, 9));
        RankLabel.setText("RANKS");

        NameLabel.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        NameLabel.setForeground(new java.awt.Color(255, 222, 9));
        NameLabel.setText("NAME");

        ScoreLabel.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        ScoreLabel.setForeground(new java.awt.Color(255, 222, 9));
        ScoreLabel.setText("SCORE");

        rank1.setFont(new java.awt.Font("Monospaced", 1, 12)); // NOI18N
        rank1.setForeground(new java.awt.Color(255, 0, 51));
        rank1.setText("1ST");

        rank2.setFont(new java.awt.Font("Monospaced", 1, 12)); // NOI18N
        rank2.setForeground(new java.awt.Color(255, 153, 255));
        rank2.setText("2ND");

        rank3.setFont(new java.awt.Font("Monospaced", 1, 12)); // NOI18N
        rank3.setForeground(new java.awt.Color(102, 255, 255));
        rank3.setText("3RD");

        rank4.setFont(new java.awt.Font("Monospaced", 1, 12)); // NOI18N
        rank4.setForeground(new java.awt.Color(255, 153, 51));
        rank4.setText("4TH");

        rank5.setFont(new java.awt.Font("Monospaced", 1, 12)); // NOI18N
        rank5.setForeground(new java.awt.Color(255, 255, 255));
        rank5.setText("5TH");

        name1.setFont(new java.awt.Font("Monospaced", 1, 12)); // NOI18N
        name1.setForeground(new java.awt.Color(255, 0, 51));
        name1.setText("ABC");

        name2.setFont(new java.awt.Font("Monospaced", 1, 12)); // NOI18N
        name2.setForeground(new java.awt.Color(255, 153, 255));
        name2.setText("ABC");

        name3.setFont(new java.awt.Font("Monospaced", 1, 12)); // NOI18N
        name3.setForeground(new java.awt.Color(102, 255, 255));
        name3.setText("ABC");

        name4.setFont(new java.awt.Font("Monospaced", 1, 12)); // NOI18N
        name4.setForeground(new java.awt.Color(255, 153, 51));
        name4.setText("ABC");

        name5.setFont(new java.awt.Font("Monospaced", 1, 12)); // NOI18N
        name5.setForeground(new java.awt.Color(255, 255, 255));
        name5.setText("ABC");

        score1.setFont(new java.awt.Font("Monospaced", 1, 12)); // NOI18N
        score1.setForeground(new java.awt.Color(255, 0, 51));
        score1.setText("jLabel11");

        score2.setFont(new java.awt.Font("Monospaced", 1, 12)); // NOI18N
        score2.setForeground(new java.awt.Color(255, 153, 255));
        score2.setText("jLabel12");

        score3.setFont(new java.awt.Font("Monospaced", 1, 12)); // NOI18N
        score3.setForeground(new java.awt.Color(102, 255, 255));
        score3.setText("jLabel13");

        score4.setFont(new java.awt.Font("Monospaced", 1, 12)); // NOI18N
        score4.setForeground(new java.awt.Color(255, 153, 51));
        score4.setText("jLabel14");

        score5.setFont(new java.awt.Font("Monospaced", 1, 12)); // NOI18N
        score5.setForeground(new java.awt.Color(255, 255, 255));
        score5.setText("jLabel15");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(141, 141, 141)
                .addComponent(HighScoreLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(RankLabel)
                            .addComponent(rank1)
                            .addComponent(rank2)
                            .addComponent(rank3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 74, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(name1)
                                    .addGap(13, 13, 13))
                                .addComponent(name2)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(name5)
                                    .addComponent(name3)
                                    .addComponent(name4)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(NameLabel)
                                .addGap(13, 13, 13))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rank5)
                            .addComponent(rank4))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(80, 80, 80)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ScoreLabel)
                    .addComponent(score1)
                    .addComponent(score2)
                    .addComponent(score3)
                    .addComponent(score4)
                    .addComponent(score5))
                .addGap(40, 40, 40))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnBack)
                .addGap(164, 164, 164))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(HighScoreLabel)
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(RankLabel)
                    .addComponent(NameLabel)
                    .addComponent(ScoreLabel))
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rank1)
                    .addComponent(name1)
                    .addComponent(score1))
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rank2)
                    .addComponent(name2)
                    .addComponent(score2))
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rank3)
                    .addComponent(name3)
                    .addComponent(score3))
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rank4)
                    .addComponent(name4)
                    .addComponent(score4))
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rank5)
                    .addComponent(name5)
                    .addComponent(score5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addComponent(btnBack)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        //If back button is clicked..
        this.setVisible(false); //HiScoreframe is not visible
        mainWindow.setVisible(true); //Main window is visible
    }//GEN-LAST:event_btnBackActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel HighScoreLabel;
    private javax.swing.JLabel NameLabel;
    private javax.swing.JLabel RankLabel;
    private javax.swing.JLabel ScoreLabel;
    private javax.swing.JButton btnBack;
    private javax.swing.JLabel name1;
    private javax.swing.JLabel name2;
    private javax.swing.JLabel name3;
    private javax.swing.JLabel name4;
    private javax.swing.JLabel name5;
    private javax.swing.JLabel rank1;
    private javax.swing.JLabel rank2;
    private javax.swing.JLabel rank3;
    private javax.swing.JLabel rank4;
    private javax.swing.JLabel rank5;
    private javax.swing.JLabel score1;
    private javax.swing.JLabel score2;
    private javax.swing.JLabel score3;
    private javax.swing.JLabel score4;
    private javax.swing.JLabel score5;
    // End of variables declaration//GEN-END:variables
}
