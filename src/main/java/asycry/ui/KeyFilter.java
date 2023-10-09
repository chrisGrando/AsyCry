package asycry.ui;

import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 * Classe destinada ao filtro de arquivos de chave.
 * @author chrisGrando
 */
public class KeyFilter extends FileFilter {
    
    @Override
    public boolean accept(File file) {
        //Permite apenas diretórios ou arquivos com extensão ".dat"
        return file.isDirectory() || file.getAbsolutePath().endsWith(".dat");
    }
    
    @Override
    public String getDescription() {
        //Esta descrição será exibida na caixa de diálogo
        return "Chave (*.dat)";
    }
    
}
