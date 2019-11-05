/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entradas.view;

import entradas.controller.ControladorCompra;
import entradas.dao.DBErrorException;
import entradas.model.CompraItem;
import entradas.model.Formatador;
import entradas.model.ValorInvalidoException;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author Tiago Rosa
 */
public class FrmCompra extends javax.swing.JFrame {

    private SimpleDateFormat formatadorData;
    private ControladorCompra controlador;
    private DecimalFormat formatadorValor;
    private DecimalFormat formatadorDecimal;

    /**
     * Creates new form FrmCompra
     */
    public FrmCompra() {
        initComponents();

        this.setSize(800, 600);
        this.setLocationRelativeTo(null);

        formatadorData = new SimpleDateFormat("dd/MM/yyyy");
        formatadorValor = new DecimalFormat();
        formatadorDecimal = new DecimalFormat();

        this.controlador = new ControladorCompra();

        this.defineRenderers();
        this.limpaTabela();

    }

    private void defineRenderers() {

        DefaultTableCellRenderer rendererCentro = new DefaultTableCellRenderer();
        rendererCentro.setHorizontalAlignment(SwingConstants.CENTER);
        DefaultTableCellRenderer rendererDireita = new DefaultTableCellRenderer();
        rendererDireita.setHorizontalAlignment(SwingConstants.RIGHT);
        DefaultTableCellRenderer rendererEsquerda = new DefaultTableCellRenderer();
        rendererEsquerda.setHorizontalAlignment(SwingConstants.LEFT);

        TableColumnModel modeloDaColuna = this.tblProdutos.getColumnModel();

        modeloDaColuna.getColumn(0).setCellRenderer(rendererDireita);
        modeloDaColuna.getColumn(1).setCellRenderer(rendererEsquerda);
        modeloDaColuna.getColumn(2).setCellRenderer(rendererDireita);
        modeloDaColuna.getColumn(3).setCellRenderer(rendererDireita);

        this.atualizaTela();

    }

    private void limpaTabela() {
        while (((DefaultTableModel) this.tblProdutos.getModel()).getRowCount() > 0) {
            ((DefaultTableModel) this.tblProdutos.getModel()).removeRow(0);
        }
    }

    private void atualizaTela() {

        this.edtData.setText(getDataFormatada(this.controlador.getCompra().getDatCompra()));
        this.edtNota.setText(this.controlador.getCompra().getNota());
        if (this.controlador.getCompra().getFornecedor() != null) {
            this.edtCodFor.setText(Integer.toString(this.controlador.getCompra().getFornecedor().getId()));
            this.edtNomeFor.setText(this.controlador.getCompra().getFornecedor().getRazao());
        } else {
            this.edtCodFor.setText("");
            this.edtNomeFor.setText("");
        }

        this.preencheTabela();

        this.edtNota.requestFocusInWindow();

    }

    /**
     * Retorna a data no formato dia/mes/ano
     *
     * @return data String
     */
    private String getDataFormatada(Calendar data) {
        if (data == null) {
            return "";
        }
        return formatadorData.format(data.getTime());
    }

    private void preencheTabela() {
        this.limpaTabela();
        for (CompraItem item : this.controlador.getCompra().getItens()) {
            ((DefaultTableModel) this.tblProdutos.getModel()).addRow(
                    new Object[]{
                        item.getProduto().getId(),
                        item.getProduto().getNome(),
                        getDecimalFormatadoDouble(item.getQuantidade()),
                        getValorCifrado(item.getValor()),});
        }

    }

    private String getDecimalFormatadoDouble(double valor) {

        formatadorDecimal.applyPattern("############.00;(############.00)");

        if (valor == 0) {
            return "0,00";
        }
        return formatadorDecimal.format(valor);
    }

    private String getValorCifrado(double valor) {

        formatadorDecimal.applyPattern("############.00;(############.00)");

        if (valor == 0) {
            return "R$ 0,00";
        }
        return "R$ " + formatadorDecimal.format(valor);
    }

    private void atualizaItemTela() {
        if (this.controlador.getItem().getProduto() != null) {
            this.edtCodProd.setText(Integer.toString(this.controlador.getItem().getProduto().getId()));
            this.edtDescricao.setText(this.controlador.getItem().getProduto().getNome());
            this.edtQtde.setText(getDecimalFormatadoDouble(this.controlador.getItem().getQuantidade()));
            this.edtValor.setText(getValorCifrado(this.controlador.getItem().getValor()));
        } else {
            this.edtCodProd.setText("");
            this.edtDescricao.setText("");
            this.edtQtde.setText("");
            this.edtValor.setText("");
        }

    }

    /**
     * This method is called rom within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        edtData = new javax.swing.JTextField();
        edtNota = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        edtCodFor = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        edtNomeFor = new javax.swing.JTextField();
        botaoPesquisarCompra = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        edtCodProd = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        edtDescricao = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        edtQtde = new javax.swing.JTextField();
        edtValor = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        botaoAdicionarProduto = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        botaoFechar = new javax.swing.JButton();
        botaoSalver = new javax.swing.JButton();
        botaoExcluir = new javax.swing.JButton();
        botaoExcluir1 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProdutos = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Compras de Produtos");
        setBounds(new java.awt.Rectangle(0, 0, 800, 600));
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(102, 102, 102), null));

        jLabel1.setText("Data");

        edtNota.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                edtNotaKeyPressed(evt);
            }
        });

        jLabel2.setText("Numero da Nota Fiscal");

        jLabel3.setText("Fornecedor");

        edtCodFor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                edtCodForKeyPressed(evt);
            }
        });

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        edtNomeFor.setEnabled(false);

        botaoPesquisarCompra.setText("Pesquisar");
        botaoPesquisarCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoPesquisarCompraActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(edtData, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(edtCodFor, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(edtNota, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(botaoPesquisarCompra))
                    .addComponent(edtNomeFor, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(209, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(edtData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(edtNota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoPesquisarCompra))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(edtCodFor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(edtNomeFor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel4.setText("Produto");

        edtCodProd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                edtCodProdKeyPressed(evt);
            }
        });

        jButton3.setText("jButton1");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        edtDescricao.setEnabled(false);

        jLabel5.setText("Qtde.");

        edtQtde.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                edtQtdeFocusGained(evt);
            }
        });
        edtQtde.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                edtQtdeKeyPressed(evt);
            }
        });

        edtValor.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                edtValorFocusGained(evt);
            }
        });
        edtValor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                edtValorKeyPressed(evt);
            }
        });

        jLabel6.setText("Valor");

        botaoAdicionarProduto.setText("+");
        botaoAdicionarProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoAdicionarProdutoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(edtCodProd, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(edtDescricao)))
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(38, 38, 38))
                    .addComponent(edtQtde))
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(edtValor, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(botaoAdicionarProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel6))
                .addGap(70, 70, 70))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(edtCodProd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3)
                    .addComponent(edtDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(edtQtde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(edtValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoAdicionarProduto))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        botaoFechar.setText("Fechar");
        botaoFechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoFecharActionPerformed(evt);
            }
        });

        botaoSalver.setText("Salvar");
        botaoSalver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoSalverActionPerformed(evt);
            }
        });

        botaoExcluir.setText("Excluir");
        botaoExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoExcluirActionPerformed(evt);
            }
        });

        botaoExcluir1.setText("Novo");
        botaoExcluir1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoExcluir1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(botaoExcluir1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoExcluir)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoSalver)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoFechar)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoFechar)
                    .addComponent(botaoSalver)
                    .addComponent(botaoExcluir)
                    .addComponent(botaoExcluir1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tblProdutos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Cod. Produto", "Produto", "Qtde", "Valor"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
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
            tblProdutos.getColumnModel().getColumn(0).setPreferredWidth(10);
            tblProdutos.getColumnModel().getColumn(1).setResizable(false);
            tblProdutos.getColumnModel().getColumn(1).setPreferredWidth(400);
            tblProdutos.getColumnModel().getColumn(2).setResizable(false);
            tblProdutos.getColumnModel().getColumn(2).setPreferredWidth(10);
            tblProdutos.getColumnModel().getColumn(3).setResizable(false);
            tblProdutos.getColumnModel().getColumn(3).setPreferredWidth(10);
        }

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(40, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
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
    }// </editor-fold>//GEN-END:initComponents

    private void botaoPesquisarCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoPesquisarCompraActionPerformed
        FrmPesquisaCompra frm = new FrmPesquisaCompra(this);
        frm.setVisible(true);
        if (frm.getCompraSelecionada() != null) {
            this.controlador.setCompra(frm.getCompraSelecionada());
            this.atualizaTela();
        }
        frm = null;
    }//GEN-LAST:event_botaoPesquisarCompraActionPerformed

    private void edtNotaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_edtNotaKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            if (this.edtNota.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "É obrigatório informar o numero da nota fiscal", "Atençao", JOptionPane.INFORMATION_MESSAGE);
                this.edtNota.requestFocusInWindow();
            } else {

                try {
                    this.controlador.getCompraPorNotaFiscal(this.edtNota.getText());
                    this.atualizaTela();
                    this.edtCodFor.requestFocusInWindow();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage(), "Atençao", JOptionPane.INFORMATION_MESSAGE);
                    this.edtNota.requestFocusInWindow();
                }

            }

        }

    }//GEN-LAST:event_edtNotaKeyPressed

    private void edtCodForKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_edtCodForKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            if (!this.edtCodFor.getText().equals("")) {

                try {
                    this.controlador.buscaFornecedor(this.edtCodFor.getText());

                    this.atualizaTela();

                    this.edtCodProd.requestFocusInWindow();

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage(), "Atençao", JOptionPane.INFORMATION_MESSAGE);
                    this.edtCodFor.requestFocusInWindow();
                }

            } else {
                JOptionPane.showMessageDialog(this, "É necessário informar o fonecedor", "Atençao", JOptionPane.INFORMATION_MESSAGE);
                this.edtCodFor.requestFocusInWindow();
            }

        }

    }//GEN-LAST:event_edtCodForKeyPressed

    private void edtCodProdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_edtCodProdKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            if (this.edtCodProd.getText().equals("")) {

                JOptionPane.showMessageDialog(this, "É necessário informar o produto", "Atençao", JOptionPane.INFORMATION_MESSAGE);
                this.edtCodProd.requestFocusInWindow();

            } else {

                try {

                    this.controlador.buscaProduto(this.edtCodProd.getText());

                    this.atualizaItemTela();

                    this.edtQtde.requestFocusInWindow();

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage(), "Atençao", JOptionPane.INFORMATION_MESSAGE);
                    this.edtCodProd.requestFocusInWindow();
                }

            }

        }
    }//GEN-LAST:event_edtCodProdKeyPressed

    private void edtQtdeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_edtQtdeKeyPressed

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            try {
                this.controlador.getItem().setQuantidade(Double.parseDouble(this.edtQtde.getText()));
                this.edtValor.requestFocusInWindow();
            } catch (ValorInvalidoException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Atençao", JOptionPane.INFORMATION_MESSAGE);
                this.edtQtde.requestFocusInWindow();
            }

        }
    }//GEN-LAST:event_edtQtdeKeyPressed

    private void edtValorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_edtValorKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            try {
                this.controlador.getItem().setValor(Double.parseDouble(Formatador.retiraMascaraValor(this.edtValor.getText())));
                this.botaoAdicionarProduto.requestFocusInWindow();
            } catch (ValorInvalidoException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Atençao", JOptionPane.INFORMATION_MESSAGE);
                this.edtValor.requestFocusInWindow();
            }

        }
    }//GEN-LAST:event_edtValorKeyPressed

    private void botaoAdicionarProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoAdicionarProdutoActionPerformed
        try {
            this.controlador.adicionaItem();
            this.atualizaItemTela();
            this.preencheTabela();
            this.edtCodProd.requestFocusInWindow();
        } catch (ValorInvalidoException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Atençao", JOptionPane.INFORMATION_MESSAGE);
        }

    }//GEN-LAST:event_botaoAdicionarProdutoActionPerformed

    private void tblProdutosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblProdutosKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_DELETE) {
            if (this.tblProdutos.getSelectedRow() < 0) {

                JOptionPane.showMessageDialog(this, "É necessário selecionar o item a sem excluido!", "Atençao", JOptionPane.INFORMATION_MESSAGE);
                this.tblProdutos.requestFocusInWindow();

            } else {

                try {
                    this.controlador.removeItem(this.tblProdutos.getSelectedRow());
                    this.preencheTabela();
                    this.edtCodProd.requestFocusInWindow();
                } catch (ValorInvalidoException ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage(), "Atençao", JOptionPane.INFORMATION_MESSAGE);
                    this.tblProdutos.requestFocusInWindow();
                }

            }
        }
    }//GEN-LAST:event_tblProdutosKeyPressed

    private void botaoFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoFecharActionPerformed
        this.dispose();
    }//GEN-LAST:event_botaoFecharActionPerformed

    private void botaoSalverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoSalverActionPerformed
        try {
            this.controlador.salvar();

            JOptionPane.showMessageDialog(this, "Compra salva com sucesso!", "Atençao", JOptionPane.INFORMATION_MESSAGE);

            this.atualizaTela();
            this.atualizaItemTela();
            this.edtData.requestFocusInWindow();
        } catch (DBErrorException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Atençao", JOptionPane.INFORMATION_MESSAGE);
            this.tblProdutos.requestFocusInWindow();
        } catch (InstantiationException ex) {
            Logger.getLogger(FrmCompra.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(FrmCompra.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_botaoSalverActionPerformed

    private void botaoExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoExcluirActionPerformed
        try {
            this.controlador.excluiCompra();

            JOptionPane.showMessageDialog(this, "Compra excluida com sucesso!", "Atençao", JOptionPane.INFORMATION_MESSAGE);

            this.atualizaTela();
            this.atualizaItemTela();
            this.edtNota.requestFocusInWindow();

        } catch (DBErrorException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Atençao", JOptionPane.INFORMATION_MESSAGE);
        } catch (InstantiationException ex) {
            Logger.getLogger(FrmCompra.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(FrmCompra.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_botaoExcluirActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        FrmPesquisaFornecedor frm = new FrmPesquisaFornecedor(this);
        frm.setVisible(true);
        if (frm.getFornecedorSelecionado() != null) {
            try {
                this.controlador.getCompra().setFornecedor(frm.getFornecedorSelecionado());
                this.atualizaTela();
                this.edtCodProd.requestFocusInWindow();
            } catch (ValorInvalidoException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Atençao", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        frm = null;
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        FrmPesquisaProdutos frm = new FrmPesquisaProdutos(this);
        frm.setVisible(true);
        if (frm.getProdutoSelecionado() != null) {
            try {
                this.controlador.getItem().setProduto(frm.getProdutoSelecionado());
                this.atualizaItemTela();
                this.edtQtde.requestFocusInWindow();
            } catch (ValorInvalidoException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Atençao", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        frm = null;
    }//GEN-LAST:event_jButton3ActionPerformed

    private void botaoExcluir1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoExcluir1ActionPerformed
        this.controlador.novaCompra();
        this.atualizaTela();
        this.atualizaItemTela();
        this.edtNota.requestFocusInWindow();

    }//GEN-LAST:event_botaoExcluir1ActionPerformed

    private void edtQtdeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_edtQtdeFocusGained
        this.edtQtde.selectAll();
    }//GEN-LAST:event_edtQtdeFocusGained

    private void edtValorFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_edtValorFocusGained
        this.edtValor.selectAll();
    }//GEN-LAST:event_edtValorFocusGained

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
            java.util.logging.Logger.getLogger(FrmCompra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmCompra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmCompra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmCompra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FrmCompra frmCompra = new FrmCompra();
                frmCompra.setVisible(true);
                TelaImportacoes telaImportacoes = new TelaImportacoes(frmCompra, true);
                telaImportacoes.show();
            }
        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoAdicionarProduto;
    private javax.swing.JButton botaoExcluir;
    private javax.swing.JButton botaoExcluir1;
    private javax.swing.JButton botaoFechar;
    private javax.swing.JButton botaoPesquisarCompra;
    private javax.swing.JButton botaoSalver;
    private javax.swing.JTextField edtCodFor;
    private javax.swing.JTextField edtCodProd;
    private javax.swing.JTextField edtData;
    private javax.swing.JTextField edtDescricao;
    private javax.swing.JTextField edtNomeFor;
    private javax.swing.JTextField edtNota;
    private javax.swing.JTextField edtQtde;
    private javax.swing.JTextField edtValor;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblProdutos;
    // End of variables declaration//GEN-END:variables
}
