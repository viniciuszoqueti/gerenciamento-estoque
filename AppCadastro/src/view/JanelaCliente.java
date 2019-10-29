/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import br.com.adapters.AdapterClienteJTable;
import controllers.ControllerCliente;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;
import model.Cliente;

/**
 *
 * @author Vinicius Ricardo Zoqueti
 */
public class JanelaCliente extends javax.swing.JFrame {

    private final ControllerCliente controllerCliente;
    private AdapterClienteJTable adapterClienteJTable;
    private long idSelecionado = 0;
    private boolean editar = false;
    /**
     * Creates new form JanelaCliente
     */
    public JanelaCliente() throws InstantiationException, IllegalAccessException {
        this.controllerCliente = new ControllerCliente();
        initComponents();
        setExtendedState(JFrame.MAXIMIZED_BOTH); 
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        load();
    }
    
    private void tableaValue() {
        int row = jtbClientes.getSelectedRow();
        Cliente cliente = adapterClienteJTable.getValue(row);
        idSelecionado = cliente.getId();
        jTextFieldEndPesq.setText(cliente.getEndereco());
        jTextFieldNomePesq.setText(cliente.getNome());
        jTextFieldNumPesq.setText(cliente.getNumero());
        jTextFieldPesqBairro.setText(cliente.getBairro());
        jTextFieldPesqCPF.setText(cliente.getCpf());
        jTextFieldPesqCidade.setText(cliente.getCidade());
        jTextFieldPesqDtNasc.setText(cliente.getDat_nascimento().toString());
        jTextFieldPesqEstado.setText(cliente.getEstado());
        jTextFieldPesqRG.setText(cliente.getRg());
        jTextFieldPesquisaCEP.setText(cliente.getCep());
        
    }
    
     private void load() {

        List<Cliente> clientes = null;
        try {

            adapterClienteJTable = controllerCliente.AllTable();
            jtbClientes.setModel(adapterClienteJTable);
        } catch (SQLException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
     
     public void filtrarTabela(String valueFilter) {

        TableRowSorter sorter = new TableRowSorter(adapterClienteJTable);
        jtbClientes.setRowSorter(sorter);

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
  
     private void salvar(boolean editar) throws ParseException {

        String nome     = jTextFieldNomePesq.getText();
        String endereco = jTextFieldEndPesq.getText();
        String numero   = jTextFieldNumPesq.getText();
        String bairro   = jTextFieldPesqBairro.getText();
        String cpf      = jTextFieldPesqCPF.getText();
        String cidade   = jTextFieldPesqCidade.getText();
        String data_nas = jTextFieldPesqDtNasc.getText();
        String estado   = jTextFieldPesqEstado.getText();
        String rg       = jTextFieldPesqRG.getText();
        String Cep      = jTextFieldPesquisaCEP.getText();
     
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        java.sql.Date data_nascimento = new java.sql.Date(simpleDateFormat.parse(data_nas).getTime());
        
           
        Cliente cliente = new Cliente((int)idSelecionado,nome,data_nascimento,cpf,rg,endereco,numero,bairro,cidade,estado,Cep);

        boolean result = true;

        if (editar) {

            try {

                controllerCliente.Editar(cliente);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(rootPane, ex);
                result = false;
            }
        } else {

            try {
                controllerCliente.Adicionar(cliente);
                 JOptionPane.showMessageDialog(rootPane, "Cliente Criado com Sucesso!");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(rootPane, ex);
                result = false;
            }
        }

        if (result) {
        idSelecionado = 0;
        jTextFieldIDPesq.setEnabled(false);
        jTextFieldEndPesq.setEnabled(true);
        jTextFieldNomePesq.setEnabled(true);
        jTextFieldNumPesq.setEnabled(true);
        jTextFieldPesqBairro.setEnabled(true);
        jTextFieldPesqCPF.setEnabled(true);
        jTextFieldPesqCidade.setEnabled(true);
        jTextFieldPesqDtNasc.setEnabled(true);
        jTextFieldPesqEstado.setEnabled(true);
        jTextFieldPesqRG.setEnabled(true);
        jTextFieldPesquisaCEP.setEnabled(true);

        jTextFieldEndPesq.setText("");
        jTextFieldNomePesq.setText("");
        jTextFieldNumPesq.setText("");
        jTextFieldPesqBairro.setText("");
        jTextFieldPesqCPF.setText("");
        jTextFieldPesqCidade.setText("");
        jTextFieldPesqDtNasc.setText("");
        jTextFieldPesqEstado.setText("");
        jTextFieldPesqRG.setText("");
        jTextFieldPesquisaCEP.setText("");

            load();
        } else {
            JOptionPane.showMessageDialog(rootPane, "Erro ao salvar");
        }
     }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabelIDPesq = new javax.swing.JLabel();
        jTextFieldIDPesq = new javax.swing.JTextField();
        jTextFieldEndPesq = new javax.swing.JTextField();
        jLabelCodBarrasPesq = new javax.swing.JLabel();
        jLabelCustoPesq = new javax.swing.JLabel();
        jTextFieldPesqCPF = new javax.swing.JTextField();
        jTextFieldPesqBairro = new javax.swing.JTextField();
        jLabelVendaPesq = new javax.swing.JLabel();
        jTextFieldPesqDtNasc = new javax.swing.JTextField();
        jLabelMargemPesq = new javax.swing.JLabel();
        jLabelEstoquePesq = new javax.swing.JLabel();
        jTextFieldPesqRG = new javax.swing.JTextField();
        jLabelUltVendaPesq = new javax.swing.JLabel();
        jTextFieldNumPesq = new javax.swing.JTextField();
        jLabelCompraPesq = new javax.swing.JLabel();
        jTextFieldPesqCidade = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtbClientes = new javax.swing.JTable();
        jButtonPesq = new javax.swing.JButton();
        jTextFieldNomePesq = new javax.swing.JTextField();
        jLabelNomePesq = new javax.swing.JLabel();
        jLabelMargemPesq1 = new javax.swing.JLabel();
        jTextFieldPesqEstado = new javax.swing.JTextField();
        jTextFieldPesquisaCEP = new javax.swing.JTextField();
        jLabelMargemPesq2 = new javax.swing.JLabel();
        jButtonSalvar = new javax.swing.JButton();
        jButtonDeletar = new javax.swing.JButton();
        jButtonEditar = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();

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
        jLabel2.setText("CLIENTE");

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/iconsContabilidade_80px.png"))); // NOI18N

        jLabelIDPesq.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jLabelIDPesq.setText("ID:");

        jTextFieldIDPesq.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        jTextFieldIDPesq.setEnabled(false);

        jTextFieldEndPesq.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        jTextFieldEndPesq.setEnabled(false);

        jLabelCodBarrasPesq.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jLabelCodBarrasPesq.setText("Endereço:");

        jLabelCustoPesq.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jLabelCustoPesq.setText("CPF:");

        jTextFieldPesqCPF.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        jTextFieldPesqCPF.setEnabled(false);

        jTextFieldPesqBairro.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        jTextFieldPesqBairro.setEnabled(false);

        jLabelVendaPesq.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jLabelVendaPesq.setText("Bairro");

        jTextFieldPesqDtNasc.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        jTextFieldPesqDtNasc.setEnabled(false);

        jLabelMargemPesq.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jLabelMargemPesq.setText("Data Nascimento:");

        jLabelEstoquePesq.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jLabelEstoquePesq.setText("RG:");

        jTextFieldPesqRG.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        jTextFieldPesqRG.setEnabled(false);

        jLabelUltVendaPesq.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jLabelUltVendaPesq.setText("Número:");

        jTextFieldNumPesq.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        jTextFieldNumPesq.setEnabled(false);

        jLabelCompraPesq.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jLabelCompraPesq.setText("Cidade:");

        jTextFieldPesqCidade.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        jTextFieldPesqCidade.setEnabled(false);

        jtbClientes.setFont(new java.awt.Font("Trebuchet MS", 2, 14));
        jtbClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jtbClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtbClientesMouseClicked(evt);
            }
        });
        jtbClientes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtbClientesKeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(jtbClientes);

        jButtonPesq.setText("Pesquisar");
        jButtonPesq.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPesqActionPerformed(evt);
            }
        });

        jTextFieldNomePesq.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        jTextFieldNomePesq.setEnabled(false);

        jLabelNomePesq.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jLabelNomePesq.setText("Nome:");

        jLabelMargemPesq1.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jLabelMargemPesq1.setText("Estado:");

        jTextFieldPesqEstado.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        jTextFieldPesqEstado.setEnabled(false);

        jTextFieldPesquisaCEP.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        jTextFieldPesquisaCEP.setEnabled(false);

        jLabelMargemPesq2.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jLabelMargemPesq2.setText("CEP:");

        jButtonSalvar.setText("Salvar");
        jButtonSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSalvarActionPerformed(evt);
            }
        });

        jButtonDeletar.setText("Deletar");
        jButtonDeletar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeletarActionPerformed(evt);
            }
        });

        jButtonEditar.setText("editar");
        jButtonEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEditarActionPerformed(evt);
            }
        });

        jButtonCancelar.setText("Cancelar");
        jButtonCancelar.setToolTipText("");
        jButtonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelarActionPerformed(evt);
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
                            .addComponent(jLabelCodBarrasPesq)
                            .addComponent(jLabelIDPesq)
                            .addComponent(jLabelCustoPesq)
                            .addComponent(jLabelEstoquePesq))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jTextFieldPesqCPF, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabelVendaPesq))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jTextFieldPesqRG, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabelCompraPesq)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextFieldPesqBairro, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                                    .addComponent(jTextFieldPesqCidade))
                                .addGap(18, 18, 18)
                                .addComponent(jLabelMargemPesq))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jTextFieldIDPesq, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jLabelNomePesq)
                                    .addGap(18, 18, 18)
                                    .addComponent(jTextFieldNomePesq))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(jTextFieldEndPesq, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 67, Short.MAX_VALUE)
                                            .addComponent(jLabelUltVendaPesq))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addGap(0, 0, Short.MAX_VALUE)
                                            .addComponent(jLabelMargemPesq1)))
                                    .addGap(18, 18, 18)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jTextFieldPesqDtNasc, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextFieldNumPesq, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextFieldPesqEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabelMargemPesq2)
                                        .addGap(18, 18, 18)
                                        .addComponent(jTextFieldPesquisaCEP, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jButtonPesq, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jButtonSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButtonEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jButtonDeletar, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButtonCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextFieldNomePesq, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabelNomePesq))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextFieldIDPesq, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabelIDPesq))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButtonPesq)
                        .addComponent(jButtonSalvar)
                        .addComponent(jButtonEditar)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldEndPesq, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelCodBarrasPesq)
                    .addComponent(jLabelUltVendaPesq)
                    .addComponent(jTextFieldNumPesq, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonDeletar)
                    .addComponent(jButtonCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelMargemPesq1)
                        .addComponent(jTextFieldPesqEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextFieldPesqCPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabelCustoPesq)
                        .addComponent(jTextFieldPesqBairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabelVendaPesq)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextFieldPesqRG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabelEstoquePesq)
                        .addComponent(jLabelCompraPesq)
                        .addComponent(jTextFieldPesqCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelMargemPesq2)
                        .addComponent(jTextFieldPesquisaCEP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelMargemPesq)
                        .addComponent(jTextFieldPesqDtNasc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 569, Short.MAX_VALUE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("CLIENTE", jPanel1);

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 767, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonPesqActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPesqActionPerformed
        filtrarTabela(jTextFieldNomePesq.getText());
    }//GEN-LAST:event_jButtonPesqActionPerformed

    private void jButtonSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalvarActionPerformed
        try {
            salvar(this.editar);
        } catch (ParseException ex) {
            Logger.getLogger(JanelaCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.editar = false;
    }//GEN-LAST:event_jButtonSalvarActionPerformed

    private void jButtonDeletarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeletarActionPerformed
        int option = JOptionPane.showConfirmDialog(rootPane, "Tem certeza que deseja deletar?");
         if (option != 0) {
            return;
        }

        Cliente cliente = new Cliente();
        cliente.setId((int) idSelecionado);
        boolean result = true;

        try {
            controllerCliente.Excluir(cliente);
            JOptionPane.showMessageDialog(rootPane, "Cliente Deletado com Sucesso!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(rootPane, ex);
            result = false;
        }
          if (result) {
            this.idSelecionado = 0;
            
        jTextFieldIDPesq.setEnabled(true);
        jTextFieldEndPesq.setEnabled(false);
        jTextFieldNomePesq.setEnabled(false);
        jTextFieldNumPesq.setEnabled(false);
        jTextFieldPesqBairro.setEnabled(false);
        jTextFieldPesqCPF.setEnabled(false);
        jTextFieldPesqCidade.setEnabled(false);
        jTextFieldPesqDtNasc.setEnabled(false);
        jTextFieldPesqEstado.setEnabled(false);
        jTextFieldPesqRG.setEnabled(false);
        jTextFieldPesquisaCEP.setEnabled(false);
         
        jTextFieldEndPesq.setText("");
        jTextFieldNomePesq.setText("");
        jTextFieldNumPesq.setText("");
        jTextFieldPesqBairro.setText("");
        jTextFieldPesqCPF.setText("");
        jTextFieldPesqCidade.setText("");
        jTextFieldPesqDtNasc.setText("");
        jTextFieldPesqEstado.setText("");
        jTextFieldPesqRG.setText("");
        jTextFieldPesquisaCEP.setText("");

            load();
        } else {
            JOptionPane.showMessageDialog(rootPane, "Erro ao Apagar");
        }
          
    }//GEN-LAST:event_jButtonDeletarActionPerformed

    private void jtbClientesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtbClientesKeyPressed
        tableaValue();
    }//GEN-LAST:event_jtbClientesKeyPressed

    private void jtbClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtbClientesMouseClicked
       tableaValue();
    }//GEN-LAST:event_jtbClientesMouseClicked

    private void jButtonEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditarActionPerformed
        // TODO add your handling code here:
        this.editar = true;
        jTextFieldIDPesq.setEnabled(true);
        jTextFieldEndPesq.setEnabled(false);
        jTextFieldNomePesq.setEnabled(false);
        jTextFieldNumPesq.setEnabled(false);
        jTextFieldPesqBairro.setEnabled(false);
        jTextFieldPesqCPF.setEnabled(false);
        jTextFieldPesqCidade.setEnabled(false);
        jTextFieldPesqDtNasc.setEnabled(false);
        jTextFieldPesqEstado.setEnabled(false);
        jTextFieldPesqRG.setEnabled(false);
        jTextFieldPesquisaCEP.setEnabled(false);
    }//GEN-LAST:event_jButtonEditarActionPerformed

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        // TODO add your handling code here:
        this.editar = false;
        jTextFieldIDPesq.setEnabled(false);
        jTextFieldEndPesq.setEnabled(false);
        jTextFieldNomePesq.setEnabled(false);
        jTextFieldNumPesq.setEnabled(false);
        jTextFieldPesqBairro.setEnabled(false);
        jTextFieldPesqCPF.setEnabled(false);
        jTextFieldPesqCidade.setEnabled(false);
        jTextFieldPesqDtNasc.setEnabled(false);
        jTextFieldPesqEstado.setEnabled(false);
        jTextFieldPesqRG.setEnabled(false);
        jTextFieldPesquisaCEP.setEnabled(false);
        
    }//GEN-LAST:event_jButtonCancelarActionPerformed

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
            java.util.logging.Logger.getLogger(JanelaCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JanelaCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JanelaCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JanelaCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new JanelaCliente().setVisible(true);
                } catch (InstantiationException ex) {
                    Logger.getLogger(JanelaCliente.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(JanelaCliente.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonDeletar;
    private javax.swing.JButton jButtonEditar;
    private javax.swing.JButton jButtonPesq;
    private javax.swing.JButton jButtonSalvar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabelCodBarrasPesq;
    private javax.swing.JLabel jLabelCompraPesq;
    private javax.swing.JLabel jLabelCustoPesq;
    private javax.swing.JLabel jLabelEstoquePesq;
    private javax.swing.JLabel jLabelIDPesq;
    private javax.swing.JLabel jLabelMargemPesq;
    private javax.swing.JLabel jLabelMargemPesq1;
    private javax.swing.JLabel jLabelMargemPesq2;
    private javax.swing.JLabel jLabelNomePesq;
    private javax.swing.JLabel jLabelUltVendaPesq;
    private javax.swing.JLabel jLabelVendaPesq;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextFieldEndPesq;
    private javax.swing.JTextField jTextFieldIDPesq;
    private javax.swing.JTextField jTextFieldNomePesq;
    private javax.swing.JTextField jTextFieldNumPesq;
    private javax.swing.JTextField jTextFieldPesqBairro;
    private javax.swing.JTextField jTextFieldPesqCPF;
    private javax.swing.JTextField jTextFieldPesqCidade;
    private javax.swing.JTextField jTextFieldPesqDtNasc;
    private javax.swing.JTextField jTextFieldPesqEstado;
    private javax.swing.JTextField jTextFieldPesqRG;
    private javax.swing.JTextField jTextFieldPesquisaCEP;
    private javax.swing.JTable jtbClientes;
    // End of variables declaration//GEN-END:variables
}
