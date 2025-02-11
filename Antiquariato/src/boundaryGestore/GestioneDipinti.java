package boundaryGestore;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import boundary.BoundaryGestore;
import entity.Dipinto;

/**
 *
 * @author flavi
 */
public class GestioneDipinti extends javax.swing.JFrame {
	
	private String codiceDipintoSelezionato;

    /**
     * Creates new form GestioneCatalogo
     */
	
	private BoundaryGestore boundaryGestore = BoundaryGestore.getIstance();
	
    public GestioneDipinti() {
        initComponents();
        setResizable(false);
        DipintiLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        ScultureLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        AggiungiBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        ModificaBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        RimuoviBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        LogoutLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        setLocationRelativeTo(null);
        VisualizzaDipinti();
    } 

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">   
    
    private void VisualizzaDipinti() {
    	
    	DefaultTableModel model = (DefaultTableModel) DipintiTable.getModel();
    	model.setRowCount(0); // Rimuovi tutte le righe precedenti
    	
    	ArrayList<Dipinto> dipinti = boundaryGestore.VisualizzaDipinti();
    	
    	for(Dipinto dipinto : dipinti) {
    	    Object[] rowData = { dipinto.getCodice(), dipinto.getNome(), dipinto.getDescrizione(), 
    	    		dipinto.getPrezzo(), dipinto.getTecnica(), dipinto.getDimensioneTela()};
    	    model.addRow(rowData);
    	}
    	
    }
    
    
    private void AggiungiDipinto() {
    	
        String prezzoText = PrezzoField.getText();
        
        try {
        	
            double prezzo = Double.parseDouble(prezzoText);
            
            //il nome del prodotto non può essere nullo
            if(NomeField.getText().equals("")) {
            	JOptionPane.showMessageDialog(this, "Il nome non può essere nullo");
            	return;
            }

            //il prezzo deve essere maggiore di 0
            if(prezzo <= 0) {
            	JOptionPane.showMessageDialog(this, "Il prezzo deve essere un valore positivo");
            	return;
            }
            //le dimensioni devono essere nella forma 2x2 , 3.4x4.5 ecc
            if( !DimensioniTelaField.getText().matches("\\d+(\\.\\d+)?x\\d+(\\.\\d+)?") ) {
            	JOptionPane.showMessageDialog(this, "Le dimensioni devono essere valorexvalore, ad es. 2x2");
            	return;
            }

            boundaryGestore.AggiungiDipinto(NomeField.getText(), DescrizioneField.getText(), 
                    prezzo, DimensioniTelaField.getText(), TecnicaField.getText());
            
            svuotaCampi();
            VisualizzaDipinti();
       

            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Il prezzo deve essere un valore numerico");
        }

    }
    
    private void ModificaDipinto() {
    	
        String prezzoText = PrezzoField.getText();
        
        try {
        	
            double prezzo = Double.parseDouble(prezzoText);
           
            if(NomeField.getText().equals("")) {
            	JOptionPane.showMessageDialog(this, "Il nome non può essere nullo");
            	return;
            }
            
            if(prezzo <= 0) {
            	JOptionPane.showMessageDialog(this, "Prezzo, peso e altezza devono essere valori positivi");
            	return;
            }


            boundaryGestore.ModificaDipinto(codiceDipintoSelezionato, NomeField.getText(), DescrizioneField.getText(), 
                    prezzo, TecnicaField.getText(), DimensioniTelaField.getText());
            VisualizzaDipinti();

            svuotaCampi();

            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Il prezzo deve essere un valore numerico");
        }

    }
    
    private void RimuoviDipinto() {
    	
    	boundaryGestore.RimuoviDipinto(codiceDipintoSelezionato);
    	svuotaCampi();
    	VisualizzaDipinti();
    }
                    

    private void AggiungiBtnActionPerformed(java.awt.event.ActionEvent evt) {                                            
        
    	AggiungiDipinto();
    }
    
    private void ModificaBtnActionPerformed(java.awt.event.ActionEvent evt) {                                            
       
    	if(DipintiTable.getSelectedRow() != -1) {
        	ModificaDipinto();
    	}
    }
    
    private void RimuoviBtnActionPerformed(ActionEvent e) {
	
    	if(DipintiTable.getSelectedRow() != -1) {
        	RimuoviDipinto();
    	}
		
	}

    private void DipintiLabelMouseClicked(java.awt.event.MouseEvent evt) {                                          

    }                                         

    private void ScultureLabelMouseClicked(java.awt.event.MouseEvent evt) {                                           
  
        new GestioneSculture().setVisible(true);
        this.dispose();
    }                                          

    private void LogoutLabelMouseClicked(java.awt.event.MouseEvent evt) {                                         

        new LoginGestore().setVisible(true);
        this.dispose();
    }
    
    private void svuotaCampi() {
        NomeField.setText("");
        DescrizioneField.setText(""); 
        PrezzoField.setText(""); 
        DimensioniTelaField.setText(""); 
        TecnicaField.setText("");
    }

    private void DipintiTableMouseClicked(java.awt.event.MouseEvent evt) {                                          
        
    	DefaultTableModel model = (DefaultTableModel) DipintiTable.getModel();
    	int myRow = DipintiTable.getSelectedRow();
        codiceDipintoSelezionato = model.getValueAt(myRow, 0).toString();
        NomeField.setText(model.getValueAt(myRow,1).toString());
        DescrizioneField.setText(model.getValueAt(myRow, 2).toString());
        PrezzoField.setText(model.getValueAt(myRow, 3).toString());
        DimensioniTelaField.setText(model.getValueAt(myRow, 4).toString());
        TecnicaField.setText(model.getValueAt(myRow, 5).toString());
    }                                         

    
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel(); 
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        NomeField = new javax.swing.JTextField();
        DescrizioneField = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        DipintiTable = new javax.swing.JTable();
        DimensioniTelaField = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        PrezzoField = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        TecnicaField = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        ModificaBtn = new javax.swing.JButton();
        AggiungiBtn = new javax.swing.JButton();
        RimuoviBtn = new javax.swing.JButton();
        LogoutLabel = new javax.swing.JLabel();
        ScultureLabel = new javax.swing.JLabel();
        DipintiLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(51, 51, 255));
        jPanel2.setForeground(new java.awt.Color(255, 255, 255));

        jLabel10.setFont(new java.awt.Font("Ebrima", 1, 36)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Catalogo Dipinti");

        jLabel11.setFont(new java.awt.Font("Ebrima", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Nome ");

        jLabel12.setFont(new java.awt.Font("Ebrima", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Descrizione");

        DipintiTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codice", "Nome", "Descrizione", "Prezzo", "Tecnica", "Dimensioni"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        DipintiTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DipintiTableMouseClicked(evt);
            }
        });


        jScrollPane2.setViewportView(DipintiTable);

        jLabel16.setFont(new java.awt.Font("Ebrima", 1, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Dimensioni tela");

        jLabel17.setFont(new java.awt.Font("Ebrima", 1, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Prezzo");

        jLabel18.setFont(new java.awt.Font("Ebrima", 1, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Tecnica d'arte");

        ModificaBtn.setText("Modifica");

        AggiungiBtn.setText("Aggiungi");
        AggiungiBtn.setToolTipText("");
        AggiungiBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AggiungiBtnActionPerformed(evt);
            }
        });
        ModificaBtn.addActionListener(new java.awt.event.ActionListener() {
        	@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ModificaBtnActionPerformed(e);
			}
        });
        
        RimuoviBtn.setText("Rimuovi");
        
        RimuoviBtn.addActionListener(new java.awt.event.ActionListener() {
        	@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				RimuoviBtnActionPerformed(e); 
			}
        });
        


        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(NomeField, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabel11)))
                .addGap(58, 58, 58)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(DescrizioneField, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
                        .addComponent(PrezzoField, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(118, 118, 118))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel17)
                        .addGap(162, 162, 162))))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(86, 86, 86)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 649, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(174, 174, 174)
                        .addComponent(jLabel16)
                        .addGap(196, 196, 196)
                        .addComponent(jLabel18))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(165, 165, 165)
                        .addComponent(DimensioniTelaField, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(150, 150, 150)
                        .addComponent(TecnicaField, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(204, 204, 204)
                .addComponent(AggiungiBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(ModificaBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(RimuoviBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addGap(24, 24, 24)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jLabel11)
                    .addComponent(jLabel17))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(NomeField, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(PrezzoField, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(DescrizioneField, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(27, 27, 27)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(jLabel18))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(DimensioniTelaField, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TecnicaField, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ModificaBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AggiungiBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(RimuoviBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(48, 48, 48)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );

        LogoutLabel.setFont(new java.awt.Font("Ebrima", 1, 18)); // NOI18N
        LogoutLabel.setForeground(new java.awt.Color(51, 51, 255));
        LogoutLabel.setText("Logout");
        LogoutLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LogoutLabelMouseClicked(evt);
            }
        });

        ScultureLabel.setFont(new java.awt.Font("Ebrima", 1, 18)); // NOI18N
        ScultureLabel.setForeground(new java.awt.Color(51, 51, 255));
        ScultureLabel.setText("Sculture");
        ScultureLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ScultureLabelMouseClicked(evt);
            }
        });

        DipintiLabel.setFont(new java.awt.Font("Ebrima", 1, 18)); // NOI18N
        DipintiLabel.setForeground(new java.awt.Color(51, 51, 255));
        DipintiLabel.setText("Dipinti");
        DipintiLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DipintiLabelMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(DipintiLabel)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ScultureLabel)
                            .addComponent(LogoutLabel))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(242, 242, 242)
                .addComponent(DipintiLabel)
                .addGap(18, 18, 18)
                .addComponent(ScultureLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(LogoutLabel)
                .addGap(92, 92, 92))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
  
        pack();
    }// </editor-fold>   


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
            java.util.logging.Logger.getLogger(GestioneDipinti.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GestioneDipinti.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GestioneDipinti.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GestioneDipinti.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GestioneDipinti().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton AggiungiBtn;
    private javax.swing.JTextField DescrizioneField;
    private javax.swing.JTextField DimensioniTelaField;
    private javax.swing.JLabel DipintiLabel;
    private javax.swing.JTable DipintiTable;
    private javax.swing.JLabel LogoutLabel;
    private javax.swing.JButton ModificaBtn;
    private javax.swing.JTextField NomeField;
    private javax.swing.JTextField PrezzoField;
    private javax.swing.JButton RimuoviBtn;
    private javax.swing.JLabel ScultureLabel;
    private javax.swing.JTextField TecnicaField;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration                   
}

