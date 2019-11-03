/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entradas.model;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author RENATO
 */
public class Formatador {

    private static SimpleDateFormat formatadorData = new SimpleDateFormat("dd/MM/yyyy");
    private static DecimalFormat formatadorValor = new DecimalFormat();
    private static DecimalFormat formatadorDecimal = new DecimalFormat();
    private static SimpleDateFormat formatadorHora = new SimpleDateFormat("hh:mm:ss");
    private static String MASCARA_DATA = "  /  /    ";

    /**
     * Retorna a data no formato sql yyyy/MM/dd
     * @param data Calendar
     * @return String
     */
    public static String getDataFormatoSql(Calendar data) {
        Formatador.formatadorData = new SimpleDateFormat("yyyy-MM-dd");
        return Formatador.formatadorData.format(data.getTime());
    }

    /**
     * Retorna a data no formato dia/mes/ano
     * @return data String
     */
    public static String getDataFormatada(Date data) {
        if (data == null) {
            return "";
        }
        return Formatador.formatadorData.format(data);
    }

    /**
     * Retorna a hora no formato hora:minitos:segundos(00:00:00)
     * @return data String
     */
    public static String getHoraFormatada(Calendar hora) {
        if (hora == null) {
            return "";
        }
        return Formatador.formatadorHora.format(hora.getTime());
    }

    /**
     * Retorna o valor no formato moeda(R$ 0,00)
     * @return valor String
     */
    public static String getValorFormatado(BigDecimal valor) {

        Formatador.formatadorValor.applyPattern("R$ ###,###,###,###.00;R$ (###,###,###,###.00)");

        if ((valor == null) || (valor.compareTo(new BigDecimal(0)) == 0)) {
            return "R$ 0,00";
        }
        return Formatador.formatadorValor.format(valor);
    }

    /**
     * Retorna o valor no formato decimal (0,00)
     * @return valor String
     */
    public static String getDecimalFormatado(BigDecimal valor) {

        Formatador.formatadorDecimal.applyPattern("###,###,###,###.00;(###,###,###,###.00)");

        if ((valor == null) || (valor.compareTo(new BigDecimal(0)) == 0)) {
            return "0,00";
        }
        return Formatador.formatadorDecimal.format(valor);
    }

    /**
     * Retina a mascara dos valores(moeda ou decimal) e substitui a virgula(,) por ponto(.)
     * @param t com.sun.webui.jsf.component.TextField
     * @return tx String
     * @throws java.lang.Exception
     */
    public static String retiraMascaraValor(String t) {
        try {
            String text = t;
            String tx = "";

            for (int i = 0; i < text.length(); i++) {
                if (text.substring(i, i + 1).equals(",")) {
                    tx = tx.concat(".");
                //else if(text.substring(i, i+1).equals("."))
                //    tx = tx.concat("");
                } else if (text.substring(i, i + 1).equals("R")) {
                    tx = tx.concat("");
                } else if (text.substring(i, i + 1).equals("$")) {
                    tx = tx.concat("");
                } else if (text.substring(i, i + 1).equals("%")) {
                    tx = tx.concat("");
                } else {
                    tx = tx.concat(text.substring(i, i + 1));
                }
            }

            if (tx.equals("")) {
                tx = "0";
            }
            return tx.trim();

        } catch (Exception e) {
            return "0";
        }

    }

    /**
     * Converte uma data formatada no tipo String para o tipo Calendar
     * @param dataString String
     * @return data Calendar
     * @throws ValorInvalidoException
     */
    public static Calendar parseData(String dataString) throws Exception {
        Calendar data = Calendar.getInstance();
        DateFormat dateFormat = DateFormat.getDateInstance();
        dateFormat.setLenient(false);
        try {
            data.setTime(dateFormat.parse(dataString));
        } catch (ParseException ex) {
            throw new Exception("Data informada não é válida");
        }
        return data;
    }
    
    /**
     * Converte uma data formatada no tipo String para o tipo Calendar
     * @param dataString String
     * @return data Calendar
     * @throws ValorInvalidoException
     */
    public static Date parseData2(String dataString) throws Exception {
        Calendar data = Calendar.getInstance();
        DateFormat dateFormat = DateFormat.getDateInstance();
        dateFormat.setLenient(false);
        
        if(dataString.equals("") || dataString.equals(MASCARA_DATA)){
            return null;
        }
        
        try {
            data.setTime(dateFormat.parse(dataString));
        } catch (ParseException ex) {
            throw new Exception("Data informada nÃ£o Ã© vÃ¡lida");
        }
        return data.getTime();
    }
    /**
     * Converte um valor formatado no tipo String para o tipo double
     * @param dataString String
     * @return data Calendar
     * @throws ValorInvalidoException
     */
    public static double parseValor(String valor) throws Exception {
        
        valor = Formatador.retiraMascaraValor(valor);
        
        valor = valor.replace(',', '.');
        
        double valorNumerico = 0.00;

        if (!valor.equals("")) {
            try {
                valorNumerico = Double.parseDouble(valor);
            } catch (Exception ex) {
                throw new Exception("Valor invalido");
            }
        }

        return valorNumerico;
    }

    /**
     * Retorna o valor no formato decimal (0,00)
     * @return valor String
     */
    public static String getDecimalFormatadoDouble(double valor) {

        Formatador.formatadorDecimal.applyPattern("############.00;(############.00)");

        if (valor == 0) {
            return "0,00";
        }
        return Formatador.formatadorDecimal.format(valor);
    }
    
    /**
     * Retorna o valor no formato cifrado (R$ 0,00)
     * @return valor String
     */
    public static String getValorCifrado(double valor) {

        Formatador.formatadorDecimal.applyPattern("############.00;(############.00)");

        if (valor == 0) {
            return "R$ 0,00";
        }
        return "R$ " + Formatador.formatadorDecimal.format(valor);
    }

    public static String getValorPercentual(double valor) {

        Formatador.formatadorDecimal.applyPattern("############.00;(############.00)");

        if (valor == 0) {
            return "0,00%";
        }
        return Formatador.formatadorDecimal.format(valor)+"%";
    }
    
    /**
     * Adiciona zeros a esquerda de um nÃºmero inteiro atÃ© o tamanho tam
     * @param int valor, tamanho tam
     * @return String numeroZerosEsquerda      
     */
    public static String adicionarZeroEsquerda(int valor, int tam) {
        String valorString = valor + "";
        
        if (valorString.length() < tam) {
            String zeros = "";
            for (int i = 0; i < tam; i++) {
                zeros = zeros.concat("0");
            }
            String numero = zeros + valorString;
            return numero.substring(numero.length() - tam);
        }
        return valorString;
    }

    
}