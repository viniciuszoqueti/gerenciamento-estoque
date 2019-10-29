/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import br.com.adapters.AdapterProdutoJTable;
import controllers.ControllerFornecedor;
import controllers.ControllerMarca;
import controllers.ControllerProduto;
import dao.ClienteDAO;
import dao.ConnectionFactory;
import dao.FornecedorDao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;
import model.Fornecedor;
import model.Marca;
import model.Produto;

/**
 *
 * @author Vinicius Ricardo Zoqueti
 */
public class JanelaProduto extends javax.swing.JFrame {

    private ControllerProduto produtoController;
    private AdapterProdutoJTable adapterProdutoJTable;
    private int idSelecionado = 0;
    private boolean editar = false;

    /**
     * Creates new form JanelaProduto
     */
    public JanelaProduto() throws InstantiationException, IllegalAccessException {
        this.produtoController = new ControllerProduto();
        initComponents();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);;
        load();
    }

    public void filtrarTabela(String valueFilter) {

        TableRowSorter sorter = new TableRowSorter(adapterProdutoJTable);
        jtbProdutos.setRowSorter(sorter);

        if (valueFilter.length() == 0) {
            sorter.setRowFilter(null);
        } else {
            try {

                sorter.setRowFilter(RowFilter.regexFilter("(?i)" + valueFilter));

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Erro ao buscar o valor!!!", "AVISO - Erro", JOptionPane.ERROR_MESSAGE);
            }
        }

    }

    private void load() {

        DefaultComboBoxModel boxModelFornecedor = new DefaultComboBoxModel<>();
        DefaultComboBoxModel boxModelMarca = new DefaultComboBoxModel<>();

        try {

            ControllerFornecedor controllerFornecedor = new ControllerFornecedor();
            ControllerMarca controllerMarca = new ControllerMarca();

            for (Fornecedor fornecedor : controllerFornecedor.getFornecedores()) {
                boxModelFornecedor.addElement(fornecedor);
            }

            for (Marca marca : controllerMarca.getMarcas()) {
                boxModelMarca.addElement(marca);
            }

            jcbMarca.setModel(boxModelMarca);
            jcbFornecedor.setModel(boxModelFornecedor);

        } catch (InstantiationException ex) {
            Logger.getLogger(JanelaProduto.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(JanelaProduto.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(JanelaProduto.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {

            adapterProdutoJTable = produtoController.AllTable();
            jtbProdutos.setModel(adapterProdutoJTable);
        } catch (SQLException ex) {
            Logger.getLogger(Produto.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void tableaValue() {

        int row = jtbProdutos.getSelectedRow();
        Produto produto = adapterProdutoJTable.getValue(row);
        idSelecionado = produto.getId();
        jbtnEditar.setEnabled(true);
        jbtnExcluir.setEnabled(true);

        jtxtId.setText(String.valueOf(idSelecionado));
        jtxtNome.setText(produto.getNome());
        jtxtEstoque.setText(String.valueOf(produto.getEstoque()));
        jtxtPrecoVenda.setText(String.valueOf(produto.getPrecoVenda()));
        jtxtPrecoCusto.setText(String.valueOf(produto.getPrecoCusto()));
        if (produto.getDataCompra() != null) {
            jtxtUltimaCompra.setText(produto.getDataCompra().toString());
        }
        if (produto.getDataVenda() != null) {
            jtxtUltimaVenda.setText(produto.getDataVenda().toString());
        }

        jcbFornecedor.setSelectedItem(produto.getFornecedor());
        jcbMarca.setSelectedItem(produto.getMarca());

    }

    private void salvar(boolean editar) {

        String nome = jtxtNome.getText();
        int estoque = Integer.parseInt(jtxtEstoque.getText());
        float precoVenda = Float.parseFloat(jtxtPrecoVenda.getText());
        float precoCusto = Float.parseFloat(jtxtPrecoCusto.getText());

        Fornecedor fornecedor = ((Fornecedor) jcbFornecedor.getSelectedItem());
        Marca marca = ((Marca) jcbMarca.getSelectedItem());

        Produto produto = new Produto(idSelecionado, nome, estoque, precoVenda, precoCusto, marca, fornecedor);

        boolean result = true;

        if (editar) {

            try {

                produtoController.Editar(produto);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(rootPane, ex);
                result = false;
            }
        } else {

            try {
                produtoController.Adicionar(produto);

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(rootPane, ex);
                result = false;
            }
        }

        if (result) {
            idSelecionado = 0;
            jbtnNovo.setEnabled(true);
            jbtnEditar.setEnabled(false);
            jbtnExcluir.setEnabled(false);
            jbtnSalvar.setEnabled(false);
            jbtnCancelar.setEnabled(false);
            jtbProdutos.setEnabled(true);

            jtxtNome.setEnabled(false);
            jtxtEstoque.setEnabled(false);
            jtxtPrecoVenda.setEnabled(false);
            jtxtPrecoCusto.setEnabled(false);
            jtxtUltimaCompra.setEnabled(false);
            jtxtUltimaVenda.setEnabled(false);
            jcbFornecedor.setEnabled(false);
            jcbMarca.setEnabled(false);

            jtxtNome.setText("");
            jtxtEstoque.setText("");
            jtxtPrecoVenda.setText("");
            jtxtPrecoCusto.setText("");
            jtxtUltimaCompra.setText("");
            jtxtUltimaVenda.setText("");
            jcbFornecedor.setSelectedIndex(0);
            jcbMarca.setSelectedIndex(0);

            load();
        } else {
            JOptionPane.showMessageDialog(rootPane, "Erro ao salvar");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabelIDPesq = new javax.swing.JLabel();
        jtxtId = new javax.swing.JTextField();
        jLabelCustoPesq = new javax.swing.JLabel();
        jtxtPrecoVenda = new javax.swing.JTextField();
        jtxtPrecoCusto = new javax.swing.JTextField();
        jLabelVendaPesq = new javax.swing.JLabel();
        jLabelEstoquePesq = new javax.swing.JLabel();
        jtxtEstoque = new javax.swing.JTextField();
        jLabelUltVendaPesq = new javax.swing.JLabel();
        jtxtUltimaVenda = new javax.swing.JTextField();
        jLabelCompraPesq = new javax.swing.JLabel();
        jtxtUltimaCompra = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtbProdutos = new javax.swing.JTable();
        jtxtNome = new javax.swing.JTextField();
        jLabelNomePesq = new javax.swing.JLabel();
        jbtnNovo = new javax.swing.JButton();
        jbtnSalvar = new javax.swing.JButton();
        jbtnEditar = new javax.swing.JButton();
        jbtnExcluir = new javax.swing.JButton();
        jbtnCancelar = new javax.swing.JButton();
        jtxtBuscar = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabelUltVendaPesq1 = new javax.swing.JLabel();
        jLabelCompraPesq1 = new javax.swing.JLabel();
        jcbFornecedor = new javax.swing.JComboBox<>();
        jcbMarca = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(0, 153, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 35, Short.MAX_VALUE)
        );

        jLabel2.setBackground(new java.awt.Color(0, 153, 255));
        jLabel2.setFont(new java.awt.Font("Trebuchet MS", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 153, 255));
        jLabel2.setText("CADASTRO PRODUTO");

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/iconsContabilidade_80px.png"))); // NOI18N

        jLabelIDPesq.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jLabelIDPesq.setText("ID:");

        jtxtId.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        jtxtId.setEnabled(false);

        jLabelCustoPesq.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jLabelCustoPesq.setText("Preço Venda:");

        jtxtPrecoVenda.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        jtxtPrecoVenda.setEnabled(false);

        jtxtPrecoCusto.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        jtxtPrecoCusto.setEnabled(false);

        jLabelVendaPesq.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jLabelVendaPesq.setText("Preço Custo:");

        jLabelEstoquePesq.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jLabelEstoquePesq.setText("Estoque:");

        jtxtEstoque.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        jtxtEstoque.setEnabled(false);

        jLabelUltVendaPesq.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jLabelUltVendaPesq.setText("Ultima venda:");

        jtxtUltimaVenda.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        jtxtUltimaVenda.setEnabled(false);

        jLabelCompraPesq.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jLabelCompraPesq.setText("Ultima compra:");

        jtxtUltimaCompra.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        jtxtUltimaCompra.setEnabled(false);

        jtbProdutos.setFont(new java.awt.Font("Trebuchet MS", 2, 14)); // NOI18N
        jtbProdutos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jtbProdutos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtbProdutosMouseClicked(evt);
            }
        });
        jtbProdutos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtbProdutosKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtbProdutosKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(jtbProdutos);

        jtxtNome.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        jtxtNome.setEnabled(false);

        jLabelNomePesq.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jLabelNomePesq.setText("Nome:");

        jbtnNovo.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jbtnNovo.setText("NOVO");
        jbtnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnNovoActionPerformed(evt);
            }
        });

        jbtnSalvar.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jbtnSalvar.setText("SALVAR");
        jbtnSalvar.setEnabled(false);
        jbtnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnSalvarActionPerformed(evt);
            }
        });

        jbtnEditar.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jbtnEditar.setText("EDITAR");
        jbtnEditar.setEnabled(false);
        jbtnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnEditarActionPerformed(evt);
            }
        });

        jbtnExcluir.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jbtnExcluir.setText("EXCLUIR");
        jbtnExcluir.setEnabled(false);
        jbtnExcluir.setName(""); // NOI18N
        jbtnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnExcluirActionPerformed(evt);
            }
        });

        jbtnCancelar.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jbtnCancelar.setText("CANCELAR");
        jbtnCancelar.setEnabled(false);
        jbtnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnCancelarActionPerformed(evt);
            }
        });

        jtxtBuscar.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        jtxtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtxtBuscarKeyReleased(evt);
            }
        });

        jLabel3.setText("Buscar:");

        jLabelUltVendaPesq1.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jLabelUltVendaPesq1.setText("Fornecedor:");

        jLabelCompraPesq1.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jLabelCompraPesq1.setText("Marca:");

        jcbFornecedor.setEnabled(false);

        jcbMarca.setEnabled(false);
        jcbMarca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbMarcaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelCustoPesq)
                            .addComponent(jLabelVendaPesq, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabelIDPesq, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jtxtPrecoVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabelUltVendaPesq))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jtxtId, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabelNomePesq)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jtxtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(12, 12, 12)
                                        .addComponent(jLabelEstoquePesq)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jtxtEstoque, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabelCompraPesq1)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jtxtUltimaVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabelUltVendaPesq1)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jcbFornecedor, 0, 191, Short.MAX_VALUE)
                                            .addComponent(jcbMarca, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jtxtPrecoCusto, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)
                                .addComponent(jLabelCompraPesq)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jtxtUltimaCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 190, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jtxtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jbtnNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbtnSalvar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbtnEditar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbtnExcluir)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbtnCancelar)))
                        .addGap(17, 17, 17))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jbtnNovo)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jbtnSalvar)
                                .addComponent(jbtnEditar)
                                .addComponent(jbtnExcluir)
                                .addComponent(jbtnCancelar))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtxtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelIDPesq)
                            .addComponent(jtxtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelNomePesq)
                            .addComponent(jLabelEstoquePesq)
                            .addComponent(jtxtEstoque, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jcbFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jtxtPrecoVenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabelUltVendaPesq)
                                .addComponent(jtxtUltimaVenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabelCustoPesq)
                                .addComponent(jLabelUltVendaPesq1)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelCompraPesq)
                            .addComponent(jLabelVendaPesq)
                            .addComponent(jtxtPrecoCusto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtxtUltimaCompra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelCompraPesq1)
                            .addComponent(jcbMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtxtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))))
                .addGap(21, 21, 21)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 587, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("PRODUTO", jPanel1);

        jPanel3.setBackground(new java.awt.Color(0, 153, 255));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 52, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 767, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane1.getAccessibleContext().setAccessibleName("PRODUTO");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnNovoActionPerformed
        idSelecionado = 0;
        this.editar = false;
        jbtnNovo.setEnabled(false);
        jbtnSalvar.setEnabled(true);
        jbtnCancelar.setEnabled(true);
        jtbProdutos.setEnabled(false);

        jtbProdutos.setEnabled(false);

        jtxtNome.setEnabled(true);
        jtxtEstoque.setEnabled(true);
        jtxtPrecoVenda.setEnabled(true);
        jtxtPrecoCusto.setEnabled(true);
        jcbFornecedor.setEnabled(true);
        jcbMarca.setEnabled(true);

        jtxtNome.setText("");
        jtxtEstoque.setText("");
        jtxtPrecoVenda.setText("");
        jtxtPrecoCusto.setText("");
        jtxtUltimaCompra.setText("");
        jtxtUltimaVenda.setText("");
        jcbFornecedor.setSelectedIndex(0);
        jcbMarca.setSelectedIndex(0);
    }//GEN-LAST:event_jbtnNovoActionPerformed

    private void jbtnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnSalvarActionPerformed
        salvar(this.editar);
        this.editar = false;
    }//GEN-LAST:event_jbtnSalvarActionPerformed

    private void jbtnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnEditarActionPerformed
        this.editar = true;
        jbtnNovo.setEnabled(false);
        jbtnEditar.setEnabled(false);
        jbtnExcluir.setEnabled(false);
        jbtnSalvar.setEnabled(true);
        jbtnCancelar.setEnabled(true);

        jtbProdutos.setEnabled(true);

        jtxtNome.setEnabled(true);
        jtxtEstoque.setEnabled(true);
        jtxtPrecoVenda.setEnabled(true);
        jtxtPrecoCusto.setEnabled(true);
        jcbFornecedor.setEnabled(true);
        jcbMarca.setEnabled(true);
    }//GEN-LAST:event_jbtnEditarActionPerformed

    private void jbtnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnExcluirActionPerformed
        int option = JOptionPane.showConfirmDialog(rootPane, "Tem certeza que deseja deletar?");

        if (option != 0) {
            return;
        }

        Produto produto = new Produto();
        produto.setId((int) idSelecionado);
        boolean result = true;

        try {
            produtoController.Excluir(produto);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(rootPane, ex);
            result = false;
        }

        if (result) {
            this.idSelecionado = 0;
            jbtnExcluir.setEnabled(false);

            jtbProdutos.setEnabled(true);

            jtxtNome.setEnabled(false);
            jtxtEstoque.setEnabled(false);
            jtxtPrecoVenda.setEnabled(false);
            jtxtPrecoCusto.setEnabled(false);
            jtxtUltimaCompra.setEnabled(false);
            jtxtUltimaVenda.setEnabled(false);
            jcbFornecedor.setEnabled(false);
            jcbMarca.setEnabled(false);

            jtxtNome.setText("");
            jtxtEstoque.setText("");
            jtxtPrecoVenda.setText("");
            jtxtPrecoCusto.setText("");
            jtxtUltimaCompra.setText("");
            jtxtUltimaVenda.setText("");
            jcbFornecedor.setSelectedIndex(0);
            jcbMarca.setSelectedIndex(0);

            load();
        } else {
            JOptionPane.showMessageDialog(rootPane, "Erro ao salvar");
        }
    }//GEN-LAST:event_jbtnExcluirActionPerformed

    private void jbtnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnCancelarActionPerformed
        this.editar = false;
        jbtnNovo.setEnabled(true);
        jbtnEditar.setEnabled(false);
        jbtnExcluir.setEnabled(false);
        jbtnSalvar.setEnabled(false);
        jbtnCancelar.setEnabled(false);

        jtbProdutos.setEnabled(true);

        jtxtNome.setEnabled(false);
        jtxtEstoque.setEnabled(false);
        jtxtPrecoVenda.setEnabled(false);
        jtxtPrecoCusto.setEnabled(false);
        jtxtUltimaCompra.setEnabled(false);
        jtxtUltimaVenda.setEnabled(false);
        jcbFornecedor.setEnabled(false);
        jcbMarca.setEnabled(false);

    }//GEN-LAST:event_jbtnCancelarActionPerformed

    private void jtxtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxtBuscarKeyReleased
        filtrarTabela(jtxtBuscar.getText());
    }//GEN-LAST:event_jtxtBuscarKeyReleased

    private void jcbMarcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbMarcaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcbMarcaActionPerformed

    private void jtbProdutosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtbProdutosKeyPressed
        //tableaValue();
    }//GEN-LAST:event_jtbProdutosKeyPressed

    private void jtbProdutosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtbProdutosKeyReleased
        tableaValue();
    }//GEN-LAST:event_jtbProdutosKeyReleased

    private void jtbProdutosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtbProdutosMouseClicked
        tableaValue();
    }//GEN-LAST:event_jtbProdutosMouseClicked

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
            java.util.logging.Logger.getLogger(JanelaProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JanelaProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JanelaProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JanelaProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new JanelaProduto().setVisible(true);
                } catch (InstantiationException ex) {
                    Logger.getLogger(JanelaProduto.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(JanelaProduto.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabelCompraPesq;
    private javax.swing.JLabel jLabelCompraPesq1;
    private javax.swing.JLabel jLabelCustoPesq;
    private javax.swing.JLabel jLabelEstoquePesq;
    private javax.swing.JLabel jLabelIDPesq;
    private javax.swing.JLabel jLabelNomePesq;
    private javax.swing.JLabel jLabelUltVendaPesq;
    private javax.swing.JLabel jLabelUltVendaPesq1;
    private javax.swing.JLabel jLabelVendaPesq;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JButton jbtnCancelar;
    private javax.swing.JButton jbtnEditar;
    private javax.swing.JButton jbtnExcluir;
    private javax.swing.JButton jbtnNovo;
    private javax.swing.JButton jbtnSalvar;
    private javax.swing.JComboBox<String> jcbFornecedor;
    private javax.swing.JComboBox<String> jcbMarca;
    private javax.swing.JTable jtbProdutos;
    private javax.swing.JTextField jtxtBuscar;
    private javax.swing.JTextField jtxtEstoque;
    private javax.swing.JTextField jtxtId;
    private javax.swing.JTextField jtxtNome;
    private javax.swing.JTextField jtxtPrecoCusto;
    private javax.swing.JTextField jtxtPrecoVenda;
    private javax.swing.JTextField jtxtUltimaCompra;
    private javax.swing.JTextField jtxtUltimaVenda;
    // End of variables declaration//GEN-END:variables
}
