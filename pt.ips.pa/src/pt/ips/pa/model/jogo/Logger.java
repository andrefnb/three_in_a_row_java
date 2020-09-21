package pt.ips.pa.model.jogo;

import java.io.*;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;

/**
 * Classe que ira registar tudo o que se passar de relevante no jogo.
 * @author Luis Mestre e Andre Bastos
 */
public final class Logger {

    /**
     * Atributo do tipo Logger
     */
    private static Logger instance = new Logger();

    /**
     * Atributo do tipo File que sera o ficheiro para irao ser guardadas
     * informacoes dadas ao Logger
     */
    private final File ficheiro;

    /**
     * Construtor da classe que inicia o ficheiro do Logger.
     */
    public Logger() {
        this.ficheiro = new File("logger.log");
    }

    /**
     * Metodo que ira escrever uma nova informacao ao ficheiro do Logger.
     * @param informacao
     */
    public void addLog(String informacao) {
        escreverFicheiro("Update " + data(new Date()) + " - " + informacao);
    }

    /**
     * Metodo que ira devolver a data no formato dd/mm/aaaa.
     * @param data
     * @return String da data no formato dd/mm/aaaa
     */
    private String data (Date data){
        return String.format("%02d/%02d/%d",data.getDate(),(data.getMonth()+1),(data.getYear()+1900));
    }
    
    /**
     * Metodo estatico que retorna a instancia do Logger
     * @return
     */
    public static Logger getInstance() {
        return instance;
    }

    /**
     * Metodo que escreve no ficheiro a nova informacao a ser adicionada.
     * @param informacao 
     */
    private void escreverFicheiro(String informacao) {
        PrintWriter writer;
        try {
            writer = new PrintWriter(new BufferedWriter(new FileWriter(ficheiro.getAbsoluteFile(),true)));
            writer.println(informacao);
            writer.flush();
            writer.close();
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(Logger.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
