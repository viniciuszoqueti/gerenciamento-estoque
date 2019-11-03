/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entradas.view;

import entradas.controller.ControladorProduto;
import entradas.dao.DBErrorException;
import entradas.model.Produto;
import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author Tiago Rosa
 */
public class FrmPesquisaProdutos extends JDialog {

    private Produto produtoSelecionado = null;
    private ControladorProduto controlador;
    private List<Produto> produtos;
    private Frame frame;
    
    /**
     * Creates new form FrmPesquisaCompra
     */
    public FrmPesquisaProdutos(Frame frame) {
        super(frame);
        
        initComponents();

        this.frame = frame;
       
        
        this.setModal(true);

        this.controlador = new ControladorProduto();

        this.edtDescricao.setText("");
        this.edtDescricao.requestFocusInWindow();
        
        this.limpaTabela();
        
        this.defineRenderers();
      
    }
    
    private void defineRenderers() {
        
        DefaultTableCellRenderer rendererCentro = new DefaultTableCellRenderer();
        rendererCentro.setHorizontalAlignment(SwingConstants.CENTER);
        DefaultTableCellRenderer rendererDireita = new DefaultTableCellRenderer();
        rendererDireita.setHorizontalAlignment(SwingConstants.RIGHT);
        DefaultTableCellRenderer rendererEsquerda = new DefaultTableCellRenderer();
        rendererEsquerda.setHorizontalAlignment(SwingConstants.LEFT);

        TableColumnModel modeloDaColuna = this.tblProdutos.getColumnModel();

        modeloDaColuna.getColumn(0).setCellRenderer(rendererEsquerda);
        modeloDaColuna.getColumn(1).setCellRenderer(rendererCentro);
        modeloDaColuna.getColumn(2).setCellRenderer(rendererDireita);
        modeloDaColuna.getColumn(3).setCellRenderer(rendererDireita);
        modeloDaColuna.getColumn(4).setCellRenderer(rendererDireita);
        
    }
    
    private void limpaTabela() {
        while (((DefaultTableModel) this.tblProdutos.getModel()).getRowCount() > 0) {
            ((DefaultTableModel) this.tblProdutos.getModel()).removeRow(0);
        }
    }
    
    private void preencheTabela(List<Produto> produtos){
        this.limpaTabela();
        for (Produto p : produtos){
            ((DefaultTableModel) this.tblProdutos.getModel()).addRow(
                    new Object[]{
                        p.getNome(),
                        p.getId(),
                        p.getCusto(),
                        p.getVenda(),
                        p.getEstoque(),
                    });
        }
    
    }

    public Produto getProdutoSelecionado() {
        return produtoSelecionado;
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
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        edtDescricao = new javax.swing.JTextField();
        botaoPesquisarCompra = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProdutos = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        botaoFechar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));

        jLabel3.setText("Descrição");

        edtDescricao.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                edtDescricaoKeyPressed(evt);
            }
        });

        botaoPesquisarCompra.setText("Pesquisar");
        botaoPesquisarCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoPesquisarCompraActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(edtDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 435, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(botaoPesquisarCompra)
                .addContainerGap(233, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(edtDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoPesquisarCompra))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tblProdutos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Descrição", "Código", "Custo", "Valor", "Estoque"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblProdutos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tblProdutosKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblProdutos);
        if (tblProdutos.getColumnModel().getColumnCount() > 0) {
            tblProdutos.getColumnModel().getColumn(0).setResizable(false);
            tblProdutos.getColumnModel().getColumn(0).setPreferredWidth(400);
            tblProdutos.getColumnModel().getColumn(1).setResizable(false);
            tblProdutos.getColumnModel().getColumn(1).setPreferredWidth(100);
            tblProdutos.getColumnModel().getColumn(2).setResizable(false);
            tblProdutos.getColumnModel().getColumn(2).setPreferredWidth(100);
            tblProdutos.getColumnModel().getColumn(3).setResizable(false);
            tblProdutos.getColumnModel().getColumn(3).setPreferredWidth(100);
            tblProdutos.getColumnModel().getColumn(4).setResizable(false);
            tblProdutos.getColumnModel().getColumn(4).setPreferredWidth(100);
        }

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 797, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        botaoFechar.setText("Fechar");
        botaoFechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoFecharActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(botaoFechar)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(botaoFechar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botaoPesquisarCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoPesquisarCompraActionPerformed
        
        try {
            buscaProdutos();
        } catch (InstantiationException ex) {
            Logger.getLogger(FrmPesquisaProdutos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(FrmPesquisaProdutos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
            
        
    }//GEN-LAST:event_botaoPesquisarCompraActionPerformed

    private void tblProdutosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblProdutosKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            this.produtoSelecionado = this.produtos.get(this.tblProdutos.getSelectedRow());
            this.dispose();
        }
    }//GEN-LAST:event_tblProdutosKeyPressed

    private void botaoFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoFecharActionPerformed
        this.dispose();
    }//GEN-LAST:event_botaoFecharActionPerformed

    private void edtDescricaoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_edtDescricaoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            
            try {
                buscaProdutos();
            } catch (InstantiationException ex) {
                Logger.getLogger(FrmPesquisaProdutos.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(FrmPesquisaProdutos.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }//GEN-LAST:event_edtDescricaoKeyPressed

    private void buscaProdutos() throws InstantiationException, IllegalAccessException{
        try {
            
            produtos = this.controlador.buscaProdutos( this.edtDescricao.getText() );
            
            preencheTabela(produtos);
            
        } catch (DBErrorException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Atençao", JOptionPane.INFORMATION_MESSAGE);
            this.edtDescricao.requestFocusInWindow();
        }  
    
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoFechar;
    private javax.swing.JButton botaoPesquisarCompra;
    private javax.swing.JTextField edtDescricao;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblProdutos;
    // End of variables declaration//GEN-END:variables
}
