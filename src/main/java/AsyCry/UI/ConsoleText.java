package asycry.ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JTextArea;

/**
 * Classe destinada para atualizar o texto do console na janela do aplicativo.
 * @author chrisGrando
 */
public class ConsoleText {
    private static JTextArea console = null;
    
    /**
     * Obtém a hora, minuto e segundo atual do sistema.
     * @return
     * Hora, minuto e segundo em uma String.
     */
    private static String getCurrentTime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDateTime ldt = LocalDateTime.now();
        String time = "[" + dtf.format(ldt) + "] ";
        return time;
    }
    
    /**
     * Configura o elemento do console da janela.
     * @param jTextArea
     * Objeto da área de texto.
     */
    public static void setConsole(JTextArea jTextArea) {
        console = jTextArea;
    }
    
    /**
     * Adiciona texto no final do console.
     * @param txt
     * A mensagem para exibir.
     */
    public static void updateConsole(String txt) {
        if(console != null) {
            String msg = getCurrentTime() + txt + "\n";
            System.out.print(msg);
            console.append(msg);
        }
    }
    
}
