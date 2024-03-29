/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entradas.view;

import entradas.controller.ControladorFornecedor;
import entradas.controller.ControladorProduto;
import entradas.model.Fornecedor;
import entradas.model.Produto;
import entradas.model.ValorInvalidoException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Vinicius
 */
public class TelaImportacoes extends javax.swing.JDialog {

    /**
     * Creates new form TelaImportacoes
     */
    public TelaImportacoes(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        jpbImportacao.setMinimum(0);
        jpbImportacao.setMaximum(100);
    }

    private void lerArquivos(String acao) {

        File arquivosDiretorio = new File(jtxtCaminho.getText());
        File afile[] = arquivosDiretorio.listFiles();
        int i = 0;
        for (int j = afile.length; i < j; i++) {

            File arquivo = afile[i];
            String acaoObj = arquivo.getName().substring(0, arquivo.getName().indexOf("@"));
            FileReader fileReader = null;
            BufferedReader bufferedReader = null;

            if (acaoObj.equals(acao)) {

                try {

                    System.out.println(arquivo.getName());

                    String caminhoArquivo = arquivo.toString();
                    fileReader = new FileReader(caminhoArquivo);

                    bufferedReader = new BufferedReader(fileReader);
                    String texto = bufferedReader.readLine();

                    String[] itens = texto.split(";");

                    String nameObj = arquivo.getName().substring(
                            arquivo.getName().indexOf("-") + 1,
                            arquivo.getName().length() - 4);

                    bufferedReader.close();
                    fileReader.close();

                    switch (nameObj) {
                        case "Fornecedor":
                            try {
                                acaoFonecedor(itens, acao);
                                if (arquivo.exists()) {

                                    bufferedReader.close();
                                    fileReader.close();

                                    if (!arquivo.delete()) {
                                        System.err.println("O arquivo não pode ser deletado porque está em uso");
                                    }
                                } else {
                                    System.err.println(
                                            "Erro ao encontrar o arquivo '" + arquivo + "' ('" + arquivo.getAbsolutePath() + "')");
                                }
                            } catch (Exception ex) {
                                System.err.println(ex);
                            }
                            break;
                        case "Produto":
                            try {
                                acaoProdutos(itens, acao);
                                if (arquivo.exists()) {

                                    bufferedReader.close();
                                    fileReader.close();

                                    if (!arquivo.delete()) {
                                        System.err.println("O arquivo não pode ser deletado porque está em uso");
                                    }
                                } else {
                                    System.err.println(
                                            "Erro ao encontrar o arquivo '" + arquivo + "' ('" + arquivo.getAbsolutePath() + "')");
                                }
                            } catch (Exception ex) {
                                System.err.println(ex);
                            }

                            break;
                    }

                } catch (FileNotFoundException ex) {
                    System.out.println(ex);
                } catch (IOException ex) {
                    System.out.println(ex);
                } finally {
                    try {
                        bufferedReader.close();
                        fileReader.close();
                    } catch (IOException ex) {
                        System.out.println(ex);
                    }

                }
            }

            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException ex) {
                    System.out.println(ex);
                }
            }

            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (IOException ex) {
                    System.out.println(ex);
                }
            }

        }
    }

// FORNECEDOR
    private void acaoFonecedor(String[] itens, String acao) throws ValorInvalidoException, Exception {

        Fornecedor fornecedor = new Fornecedor();
        ControladorFornecedor cf = new ControladorFornecedor();

        for (int i = 0; i < itens.length; i++) {
            System.out.println(itens[i]);

            switch (i) {
                case 0:
                    fornecedor.setId(Integer.parseInt(itens[i]));
                    break;
                case 1:
                    fornecedor.setRazao(String.valueOf(itens[i]));
                    break;
                case 2:
                    fornecedor.setCnpj(itens[i]);
                    break;
                case 3:
                    fornecedor.setInscricao(itens[i]);
                    break;
                case 4:
                    fornecedor.setTelefone(itens[i]);
                    break;
                case 5:
                    fornecedor.setFantasia(itens[i]);
                    break;
            }
        }

        switch (acao) {
            case "INSERIR": {
                try {
                    cf.Inserir(fornecedor);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, ex);
                    throw ex;
                }
                break;
            }
            case "ALTERAR": {
                try {
                    cf.Editar(fornecedor);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, ex);
                    throw ex;
                }
                break;
            }
            case "DELETAR": {
                try {
                    cf.Excluir(fornecedor);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, ex);
                    throw ex;
                }
                break;
            }
        }

    }

    // PRODUTOS
    private void acaoProdutos(String[] itens, String acao) throws ValorInvalidoException, Exception {

        Produto produto = new Produto();
        ControladorProduto cp = new ControladorProduto();

        for (int i = 0; i < itens.length; i++) {
            System.out.println(itens[i]);

            switch (i) {
                case 0:
                    produto.setId(Integer.parseInt(itens[i]));
                    break;
                case 1:
                    produto.setNome(String.valueOf(itens[i]));
                    break;
                case 2:
                    produto.setEstoque(Integer.parseInt(itens[i]));
                    break;
                case 3: {
                    if (acao.equals("DELETAR")) {
                        produto.setVenda(1000);
                    } else {
                        produto.setVenda(Double.parseDouble(itens[i]));
                    }

                    break;
                }
                case 4:
                    produto.setCusto(Double.parseDouble(itens[i]));
                    break;
                case 5: {
                    Fornecedor fornecedor = new Fornecedor();
                    fornecedor.setId(Integer.parseInt(itens[i]));
                    fornecedor.setRazao(String.valueOf(itens[i]));

                    produto.setFornecedor(fornecedor);
                }
            }
        }

        switch (acao) {
            case "INSERIR": {
                try {
                    cp.Inserir(produto);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, ex);
                    throw ex;
                }
                break;
            }
            case "ALTERAR": {
                try {
                    cp.Editar(produto);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, ex);
                    throw ex;
                }
                break;
            }
            case "DELETAR": {
                try {
                    cp.Excluir(produto);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, ex);
                    throw ex;
                }
                break;
            }
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

        jLabel1 = new javax.swing.JLabel();
        jtxtCaminho = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jpbImportacao = new javax.swing.JProgressBar();
        jbtnSair = new javax.swing.JButton();
        jbtnImportar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jLabel1.setText("Caminho padrão:");

        jtxtCaminho.setText("C:\\Users\\Vinicius\\Documents\\GitHub\\Trabalho-Tiago\\AppCadastro\\arquivos");

        jLabel2.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        jLabel2.setText("IMPORTAÇÃO CADASTRO");

        jbtnSair.setText("SAIR");
        jbtnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnSairActionPerformed(evt);
            }
        });

        jbtnImportar.setText("IMPORTAR");
        jbtnImportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnImportarActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 51, 51));
        jLabel3.setText("Para acessar o sistema você deve realizar a importação dos cadastros!");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jpbImportacao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtxtCaminho))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(130, 130, 130)
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(182, 182, 182)
                        .addComponent(jbtnImportar, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(jbtnSair, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 206, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(206, 206, 206))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel2)
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtxtCaminho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(37, 37, 37)
                .addComponent(jLabel3)
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtnSair, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnImportar, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(jpbImportacao, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jbtnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnSairActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jbtnSairActionPerformed

    private void jbtnImportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnImportarActionPerformed

        lerArquivos("INSERIR");
        lerArquivos("ALTERAR");
        lerArquivos("DELETAR");
        this.dispose();


    }//GEN-LAST:event_jbtnImportarActionPerformed

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
            java.util.logging.Logger.getLogger(TelaImportacoes.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaImportacoes.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaImportacoes.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaImportacoes.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                TelaImportacoes dialog = new TelaImportacoes(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JButton jbtnImportar;
    private javax.swing.JButton jbtnSair;
    private javax.swing.JProgressBar jpbImportacao;
    private javax.swing.JTextField jtxtCaminho;
    // End of variables declaration//GEN-END:variables
}
