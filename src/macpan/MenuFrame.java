/*
 * Jack Luhta
 * December 21, 2023
 * Menu frame of MacPan
 */
package macpan;

import java.awt.Color;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.Image;
import javax.swing.ImageIcon;

public class MenuFrame extends javax.swing.JFrame {

    //Variables for the other two frames that will be switched to
    private GameFrame firstWindow;
    private InstructionsFrame secondWindow;
    private HighscoreFrame thirdWindow;
    
    private Image icon  = new ImageIcon("src/macpan/images/Consumables/cherry.png").getImage();
    
    /**
     * Creates the menu frame with the gui builder
     */
    public MenuFrame() {
        initComponents();
        //Set title of jframe, move frame to centre of screen, and disallow resizing the window
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("MacPan");
        setIconImage(icon);
        getContentPane().setBackground(Color.BLACK);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        backgroundLbl = new javax.swing.JLabel();
        btnPlay = new javax.swing.JButton();
        btnInstructions = new javax.swing.JButton();
        btnHighscore = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        backgroundLbl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/macpan/images/JFrames/Menu.png"))); // NOI18N
        backgroundLbl.setText("jLabel1");
        jPanel1.add(backgroundLbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1010, 720));

        btnPlay.setText("jButton1");
        btnPlay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPlayActionPerformed(evt);
            }
        });
        jPanel1.add(btnPlay, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 280, 230, 70));

        btnInstructions.setText("jButton1");
        btnInstructions.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInstructionsActionPerformed(evt);
            }
        });
        jPanel1.add(btnInstructions, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 430, 350, 40));

        btnHighscore.setText("jButton1");
        btnHighscore.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHighscoreActionPerformed(evt);
            }
        });
        jPanel1.add(btnHighscore, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 520, 260, 40));

        btnExit.setText("jButton1");
        btnExit.setMinimumSize(new java.awt.Dimension(30, 23));
        btnExit.setPreferredSize(new java.awt.Dimension(30, 23));
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });
        jPanel1.add(btnExit, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 660, 110, 40));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 0, 1010, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnPlayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPlayActionPerformed
        //If play button is clicked...
        if (firstWindow == null) {
            firstWindow = new GameFrame(this);
        }
        this.setVisible(false);
        firstWindow.setVisible(true);
    }//GEN-LAST:event_btnPlayActionPerformed

    private void btnInstructionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInstructionsActionPerformed
        //If instructions button is clicked...
        //Open instructions frame
        //Making sure there is actually an instructions frame
        if (secondWindow == null) {
            secondWindow = new InstructionsFrame(this);
        }
        this.setVisible(false); //Set main window to not visible
        secondWindow.setVisible(true); //Set instructions window to visible
    }//GEN-LAST:event_btnInstructionsActionPerformed

    private void btnHighscoreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHighscoreActionPerformed
        //If high score button is clicked...
        //Open high score frame
        //Making sure there is actually an high score frame
        if (thirdWindow == null) {
            try {
                thirdWindow = new HighscoreFrame(this);
            } catch (IOException ex) {
                Logger.getLogger(MenuFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.setVisible(false); //Set main window to not visible
        thirdWindow.setVisible(true); //Set high score window to visible
    }//GEN-LAST:event_btnHighscoreActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        //If exit button is clicked...
        //Terminate program
        System.exit(0);
    }//GEN-LAST:event_btnExitActionPerformed

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(MenuFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel backgroundLbl;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnHighscore;
    private javax.swing.JButton btnInstructions;
    private javax.swing.JButton btnPlay;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
