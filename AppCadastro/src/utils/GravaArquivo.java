/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vinicius
 */
public class GravaArquivo {

    public static void criaArquivo(String conteudo, String acao, Object obj) {

        Writer writer;
        String nomeClasse = obj.getClass().getName();
        nomeClasse = nomeClasse.substring(6, nomeClasse.length());

        Path currentRelativePath = Paths.get("");
        String currentPath = currentRelativePath.toAbsolutePath().toString();

        String diretorio = currentPath + "\\arquivos\\";

        try {

            File dir = new File(diretorio);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            Path pathToFile = Paths.get(
                    diretorio
                    + acao
                    + "-"
                    + nomeClasse
                    + ".csv");

            writer = Files.newBufferedWriter(pathToFile);
            writer.write(conteudo);
            writer.flush();
            writer.close();

        } catch (IOException ex) {
            System.out.println(ex);
        }

    }

}
