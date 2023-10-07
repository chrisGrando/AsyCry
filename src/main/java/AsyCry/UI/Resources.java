package AsyCry.UI;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe destinada para carregar arquivos alocados dentro do executável.
 * @author chrisGrando
 */
public class Resources {
    
    /**
     * Obtém ícone do aplicativo.
     * @return
     * Imagem do arquivo do ícone.
     */
    public Image getIconFile() {
        Image icon;
        final String PATH = "/AsyCry/resources/padlock.png";
        
        //Acessa arquivo no caminho especificado
        icon = Toolkit.getDefaultToolkit().getImage(
            this.getClass().getResource(PATH)
        );
        
        return icon;
    }
    
    /**
     * Obtém a fonte Cousine.
     * @return
     * Fonte do arquivo "Cousine.ttf".
     */
    public Font getCousineFont() {
        Font fontFile = null;
        final String PATH = "/AsyCry/resources/Cousine.ttf";
        
        //Carrega o arquivo da fonte no caminho especificado
        try {
            fontFile = Font.createFont(
                Font.TRUETYPE_FONT,
                this.getClass().getResourceAsStream(PATH)
            );
        }
        catch(IOException | FontFormatException error) {
            String msg = "[AVISO] Não foi possível carregar a fonte em <" + PATH + ">...";
            Logger.getGlobal().log(Level.WARNING, msg, error);
        }
        
        return fontFile;
    }
    
}
