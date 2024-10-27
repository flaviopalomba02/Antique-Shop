package boundaryCliente;

import java.awt.Cursor;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import boundary.BoundaryCliente;
import boundary.BoundaryGestore;
import entity.Dipinto;
import entity.Scultura;

/**
 *
 * @author flavi
 */

/* questa classe permette di visualizzare il catalogo dei prodotti del catalogo
 * e di aggiungerli al carrello
 */
public class Catalogo extends javax.swing.JFrame {
	
	BoundaryGestore boundaryGestore = BoundaryGestore.getIstance();
	BoundaryCliente boundaryCliente = BoundaryCliente.getIstance();
	
	private String codiceProdottoSelezionato;

    /**
     * Creates new form Catalogo
     */
    public Catalogo() {
    	//opzioni relative alla visualizzazione della finestra
        initComponents();
        setResizable(false);
        CatalogoLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        CarrelloLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        OrdiniLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        LogoutLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        AggiungiCarrelloBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        setLocationRelativeTo(null);
        
        //mi permette di vedere a video i prodotti del catalogo in delle tabelle
        VisualizzaProdotti();

    }
     
    
    private void VisualizzaProdotti() {
    	VisualizzaDipinti();
    	VisualizzaSculture();
    
    }

    
    /*tabella che mi mostra le sculture con tutti gli attributi di ogni istanza
     * facente parte della tabella Sculture del DB
    */
    private void VisualizzaSculture() {
    	
    	DefaultTableModel model = (DefaultTableModel) ScultureTable.getModel();
    	model.setRowCount(0); // Rimuove tutte le righe precedenti
    	
    	ArrayList<Scultura> sculture = boundaryGestore.VisualizzaSculture();
    	
    	for(Scultura scultura : sculture) {
    	    Object[] rowData = { scultura.getCodice(), scultura.getNome(), scultura.getDescrizione(), 
    	    		scultura.getPrezzo(), scultura.getPeso(), scultura.getAltezza()};
    	    model.addRow(rowData);
    	}
    	
    }
    
    /*tabella che mi mostra i dipinti con tutti gli attributi di ogni istanza
     * facente parte della tabella Dipinti del DB
    */
    private void VisualizzaDipinti() {
    	
    	DefaultTableModel model = (DefaultTableModel) DipintiTable.getModel();
    	model.setRowCount(0); // Rimuove tutte le righe precedenti
    	
    	ArrayList<Dipinto> dipinti = boundaryGestore.VisualizzaDipinti();
    	
    	for(Dipinto dipinto : dipinti) {
    	    Object[] rowData = { dipinto.getCodice(), dipinto.getNome(), dipinto.getDescrizione(), 
    	    		dipinto.getPrezzo(), dipinto.getTecnica(), dipinto.getDimensioneTela()};
    	    model.addRow(rowData);
    	}
    	
    }
    

    /*mi permette di selezionare il tipo di prodotto che voglio aggiungere (Dipinto/Scultura),
     * cliccare su un prodotto, inserire una quantità e cliccare su Aggiungi al Carrello
     */
    private void AggiungiProdottoCarrello() {
    	
        boolean aggiunto = false;

        try {
            int quantita = Integer.parseInt(QuantitaProdottoField.getText());

            //il nome del prodotto non può essere nullo
            if (NomeProdottoField.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Nessun prodotto selezionato");
                return;
            }
            
            //la quantità deve essere un intero positivo
            if (quantita < 1) {
                JOptionPane.showMessageDialog(this, "La quantita' deve essere un valore positivo");
                return;
            }
            
            //se ho selezionato il tipo Scultura ma poi clicco su un dipinto e lo voglio aggiungere (o viceversa) mi dà errore
            if( (codiceProdottoSelezionato.substring(1,2).equals("D") && TipoProdottoCB.getSelectedItem().toString().equals("Scultura"))
            		|| (codiceProdottoSelezionato.substring(1,2).equals("S") && TipoProdottoCB.getSelectedItem().toString().equals("Dipinto")) ){
            	
            	JOptionPane.showMessageDialog(this, "Seleziona il tipo giusto di prodotto");
            	return;
            	
            } 
            aggiunto = boundaryCliente.AggiungiProdottoCarrello(Login.getUsernameClienteOnline(), codiceProdottoSelezionato,
                        Integer.parseInt(QuantitaProdottoField.getText()));

            if (aggiunto) 
                JOptionPane.showMessageDialog(this, "Prodotto aggiunto al carrello");

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Inserisci una quantita' valida");
        }
    }
	


    
    private void AggiungiCarrelloBtnActionPerformed(java.awt.event.ActionEvent evt) {                                                    

    	AggiungiProdottoCarrello();
    	
    }
    

    //se clicco sulla label del carrello, visualizzo il carrello del cliente online                                    
    private void CarrelloLabelMouseClicked(java.awt.event.MouseEvent evt) {                                           
    	
        new Carrello().setVisible(true);
        this.dispose();
    }                                          

    //se clicco sulla label ordini, visualizzo la tabella degli ordini del cliente online
    private void OrdiniLabelMouseClicked(java.awt.event.MouseEvent evt) {                                         
        
        new Ordini().setVisible(true);
        this.dispose();
    }                                        

    //se clicco sulla label Logout in basso a sinistra, torno al login
    private void LogoutLabelMouseClicked(java.awt.event.MouseEvent evt) {                                         
        
        new Login().setVisible(true);
        this.dispose();
    }                                        

    //quando clicco su un prodotto della tabella Dipinti, mi salvo il suo codice
    private void DipintiTableMouseClicked(java.awt.event.MouseEvent evt) {                                          
       
    	DefaultTableModel model = (DefaultTableModel) DipintiTable.getModel();
    	
    	if(TipoProdottoCB.getSelectedItem().toString().equals("Dipinto")) {
    		
        	int myRow = DipintiTable.getSelectedRow();
            codiceProdottoSelezionato = model.getValueAt(myRow, 0).toString();
            NomeProdottoField.setText(model.getValueAt(myRow,1).toString());
            
    	} else  {
    		JOptionPane.showMessageDialog(this, "Non puoi selezionare un dipinto se il tipo è 'Scultura'");
    	}

        
        

    }                                         

    //quando clicco su un prodotto della tabella Sculture, mi salvo il suo codice
    private void ScultureTableMouseClicked(java.awt.event.MouseEvent evt) {                                           
        
    	DefaultTableModel model = (DefaultTableModel) ScultureTable.getModel();
    	
    	if(TipoProdottoCB.getSelectedItem().toString().equals("Scultura")) {
    		
        	int myRow = ScultureTable.getSelectedRow();
            codiceProdottoSelezionato = model.getValueAt(myRow, 0).toString();
            NomeProdottoField.setText(model.getValueAt(myRow,1).toString());
            
    	} else {
    		JOptionPane.showMessageDialog(this, "Non puoi selezionare una scultura se il tipo è 'Dipinto'");
    	}

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        NomeProdottoField = new javax.swing.JTextField();
        QuantitaProdottoField = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        ScultureTable = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        DipintiTable = new javax.swing.JTable();
        TipoProdottoCB = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        AggiungiCarrelloBtn = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        CatalogoLabel = new javax.swing.JLabel();
        LogoutLabel = new javax.swing.JLabel();
        OrdiniLabel = new javax.swing.JLabel();
        CarrelloLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        
        NomeProdottoField.setEditable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(51, 51, 255));
        jPanel2.setForeground(new java.awt.Color(255, 255, 255));

        jLabel10.setFont(new java.awt.Font("Ebrima", 1, 36)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Catalogo");

        jLabel11.setFont(new java.awt.Font("Ebrima", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Nome ");

        jLabel12.setFont(new java.awt.Font("Ebrima", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Quantità");

        ScultureTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                "Codice", "Nome", "Descrizione", "Prezzo", "Peso", "Altezza"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        ScultureTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ScultureTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(ScultureTable);

        DipintiTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                "Codice", "Nome", "Descrizione", "Prezzo", "Dimensioni", "Tecnica"
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

        TipoProdottoCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Dipinto", "Scultura" }));

        jLabel13.setFont(new java.awt.Font("Ebrima", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Tipo");

        AggiungiCarrelloBtn.setFont(new java.awt.Font("Ebrima", 1, 12)); // NOI18N
        AggiungiCarrelloBtn.setForeground(new java.awt.Color(51, 51, 255));
        AggiungiCarrelloBtn.setText("Aggiungi al Carrello");
        AggiungiCarrelloBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AggiungiCarrelloBtnActionPerformed(evt);
            }
        });
        
        TipoProdottoCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //TipoProdottoCBActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Ebrima", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Dipinti");

        jLabel15.setFont(new java.awt.Font("Ebrima", 1, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Sculture");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(209, 209, 209)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel15)
                .addGap(214, 214, 214))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 439, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(124, 124, 124)
                                .addComponent(jLabel13))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(100, 100, 100)
                                .addComponent(TipoProdottoCB, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(NomeProdottoField, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(67, 67, 67))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(156, 156, 156)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(QuantitaProdottoField, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(84, 84, 84)
                                .addComponent(AggiungiCarrelloBtn))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel12)))
                        .addGap(103, 103, 103)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel10)
                .addGap(420, 420, 420))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel13)
                        .addComponent(jLabel12)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(QuantitaProdottoField, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(NomeProdottoField, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TipoProdottoCB, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AggiungiCarrelloBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        CatalogoLabel.setFont(new java.awt.Font("Ebrima", 1, 18)); // NOI18N
        CatalogoLabel.setForeground(new java.awt.Color(51, 51, 255));
        CatalogoLabel.setText("Catalogo");
        CatalogoLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                //CatalogoLabelMouseClicked(evt);
            }
        });

        LogoutLabel.setFont(new java.awt.Font("Ebrima", 1, 18)); // NOI18N
        LogoutLabel.setForeground(new java.awt.Color(51, 51, 255));
        LogoutLabel.setText("Logout");
        LogoutLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LogoutLabelMouseClicked(evt);
            }
        });

        OrdiniLabel.setFont(new java.awt.Font("Ebrima", 1, 18)); // NOI18N
        OrdiniLabel.setForeground(new java.awt.Color(51, 51, 255));
        OrdiniLabel.setText("Ordini");
        OrdiniLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                OrdiniLabelMouseClicked(evt);
            }
        });

        CarrelloLabel.setFont(new java.awt.Font("Ebrima", 1, 18)); // NOI18N
        CarrelloLabel.setForeground(new java.awt.Color(51, 51, 255));
        CarrelloLabel.setText("Carrello");
        CarrelloLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CarrelloLabelMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(CatalogoLabel)
                    .addComponent(LogoutLabel)
                    .addComponent(OrdiniLabel)
                    .addComponent(CarrelloLabel))
                .addGap(12, 12, 12)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(110, 110, 110)
                .addComponent(CatalogoLabel)
                .addGap(18, 18, 18)
                .addComponent(CarrelloLabel)
                .addGap(18, 18, 18)
                .addComponent(OrdiniLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(LogoutLabel)
                .addGap(59, 59, 59))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
            java.util.logging.Logger.getLogger(Catalogo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Catalogo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Catalogo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Catalogo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Catalogo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton AggiungiCarrelloBtn;
    private javax.swing.JLabel CarrelloLabel;
    private javax.swing.JLabel CatalogoLabel;
    private javax.swing.JTable DipintiTable;
    private javax.swing.JLabel LogoutLabel;
    private javax.swing.JTextField NomeProdottoField;
    private javax.swing.JLabel OrdiniLabel;
    private javax.swing.JTextField QuantitaProdottoField;
    private javax.swing.JTable ScultureTable;
    private javax.swing.JComboBox<String> TipoProdottoCB;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration                   
}
