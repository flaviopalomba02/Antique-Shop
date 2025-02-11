package boundaryCliente;

import java.awt.Cursor;
import java.util.List;
import java.util.ArrayList;

import entity.LineaProdotto;
import entity.Ordine;


import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import boundary.BoundaryCliente;

/**
 *
 * @author flavi
 */
public class Ordini extends javax.swing.JFrame {
	
	BoundaryCliente boundaryCliente = BoundaryCliente.getIstance();

    /**
     * Creates new form Ordini 
     */
    public Ordini() {
        initComponents();
        setResizable(false);
        CatalogoLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        CarrelloLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        OrdiniLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        LogoutLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        setLocationRelativeTo(null);
        VisualizzaOrdini();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    
    //ogni ordine avrà tante righe quanti sono i prodotti facenti parte dell'ordine
    // le righe di uno stesso ordine hanno stesso id e stesso prezzo complessivo, ma
    //in ogni riga diversa c'è un diverso prodotto con relativa quantità
    private void VisualizzaOrdini() {
        List<Ordine> ordini = boundaryCliente.VisualizzaOrdiniCliente(Login.getUsernameClienteOnline());
        
        DefaultTableModel model = (DefaultTableModel) OrdiniTable.getModel();
        model.setRowCount(0);

        for (Ordine ordine : ordini) {
            List<LineaProdotto> prodotti = ordine.getProdotti();
            
            for (LineaProdotto prodotto : prodotti) {
                String prodottoOrdine = boundaryCliente.stampaLineaProdotto(prodotto);
                Object[] rowData = { ordine.getId(), ordine.getData(), prodottoOrdine, ordine.getPrezzoComplessivo() };
                model.addRow(rowData);
            }
        }
    }

    
    private void CatalogoLabelMouseClicked(java.awt.event.MouseEvent evt) {                                           
        
        new Catalogo().setVisible(true);
        this.dispose();
    }                                          

    private void CarrelloLabelMouseClicked(java.awt.event.MouseEvent evt) {                                           
 
        new Carrello().setVisible(true);
        this.dispose();
    }                                          

    private void LogoutLabelMouseClicked(java.awt.event.MouseEvent evt) {                                         
        
        new Login().setVisible(true);
        this.dispose();
    }                                        

    private void OrdiniTableMouseClicked(java.awt.event.MouseEvent evt) {                                         
      
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        OrdiniTable = new javax.swing.JTable();
        CatalogoLabel = new javax.swing.JLabel();
        LogoutLabel = new javax.swing.JLabel();
        OrdiniLabel = new javax.swing.JLabel();
        CarrelloLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(51, 51, 255));
        jPanel2.setForeground(new java.awt.Color(255, 255, 255));

        jLabel11.setFont(new java.awt.Font("Ebrima", 1, 36)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Ordini");

        OrdiniTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Data", "Prodotti", "Prezzo complessivo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        
        TableColumnModel columnModel = OrdiniTable.getColumnModel();
        TableColumn prodottiColumn = columnModel.getColumn(2);
        prodottiColumn.setPreferredWidth((int) ((2.5) * prodottiColumn.getPreferredWidth()));
        
        OrdiniTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                OrdiniTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(OrdiniTable);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(229, 229, 229)
                        .addComponent(jLabel11))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 560, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(126, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel11)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        CatalogoLabel.setFont(new java.awt.Font("Ebrima", 1, 18)); // NOI18N
        CatalogoLabel.setForeground(new java.awt.Color(51, 51, 255));
        CatalogoLabel.setText("Catalogo");
        CatalogoLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CatalogoLabelMouseClicked(evt);
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(215, 215, 215))
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
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 679, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
            java.util.logging.Logger.getLogger(Ordini.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ordini.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ordini.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ordini.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ordini().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JLabel CarrelloLabel;
    private javax.swing.JLabel CatalogoLabel;
    private javax.swing.JLabel LogoutLabel;
    private javax.swing.JLabel OrdiniLabel;
    private javax.swing.JTable OrdiniTable;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration                   
}
