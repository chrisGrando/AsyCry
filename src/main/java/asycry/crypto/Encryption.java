package asycry.crypto;

import asycry.ui.ConsoleText;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 * Classe destinada para encriptografar arquivos.
 * @author chrisGrando
 */
public class Encryption {
    private final String ALGORITHM = "RSA";
    private PublicKey publicKey = null;
    private File inputFile = null;
    private File outputFile = null;
    
    /**
     * Carrega a chave pública de um arquivo e armazena seu conteúdo.
     * @param path
     * Local da chave.
     */
    public void loadPublicKey(String path) {
        try {
            //Lê a chave
            File keyFile = new File(path);
            FileInputStream fis = new FileInputStream(keyFile);
            ConsoleText.updateConsole("Lendo a chave...");
            byte[] keyData = fis.readAllBytes();
            
            //Armazena a chave
            ConsoleText.updateConsole("Armazenando a chave...");
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyData);
            KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
            publicKey = keyFactory.generatePublic(keySpec);
        }
        catch(FileNotFoundException | NoSuchAlgorithmException | InvalidKeySpecException error) {
            String msg = "Não foi possível carregar a chave...";
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, msg, error);
            ConsoleText.updateConsole(msg);
            ConsoleText.updateConsole(error.toString());
        }
        catch(IOException error) {
            String msg = "Não foi possível ler a chave...";
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, msg, error);
            ConsoleText.updateConsole(msg);
            ConsoleText.updateConsole(error.toString());
        }
    }
    
    /**
     * Configura o arquivo de entrada para criptografar.
     * @param path
     * Local do arquivo.
     */
    public void setInputFile(String path) {
        File file = new File(path);
        inputFile = file;
    }
    
    /**
     * Configura o arquivo de saída com o conteúdo criptografado.
     * @param path
     * Local do arquivo.
     */
    public void setOutputFile(String path) {
        File file = new File(path);
        outputFile = file;
    }
    
    /**
     * Executa a encriptação do arquivo.
     */
    public void runEncryption() {
        //Aborta se os arquivos necessários não foram informados
        if(publicKey == null || inputFile == null || outputFile == null) {
            System.out.println("Arquivos insuficientes...");
            return;
        }
        
        try {
            //Arquivo de entrada
            FileInputStream input = new FileInputStream(inputFile);
            
            //Arquivo de saída
            FileOutputStream output = new FileOutputStream(outputFile);
            
            //Inicializa o método de criptografia
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            
            //Encriptografa o conteúdo do arquivo
            ConsoleText.updateConsole("Lendo o arquivo original...");
            byte[] originalData = input.readAllBytes();
            final int SIZE = originalData.length;
            final int SECTION = 245;
            int index = 0;
            
            while(index <= (SIZE - 1)) {
                //Posição final do trecho atual
                int max = SECTION;
                if((SIZE - 1) - (index + SECTION) < 0)
                    max = (SIZE - 1) - index;
                
                //Criptografia
                ConsoleText.updateConsole("Criptografando posição " + Integer.toString(index) + " até " + Integer.toString(index + max) + "...");
                byte[] cryptoData = cipher.doFinal(originalData, index, max);
                
                //Gravação
                ConsoleText.updateConsole("Gravando dados...");
                output.write(cryptoData);
                
                index += SECTION + 1;
            }
            
            ConsoleText.updateConsole("Pronto!");
            ConsoleText.updateConsole("Arquivo criptografado salvo em: " + outputFile.getAbsolutePath());
        }
        catch(FileNotFoundException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException error) {
            String msg = "Não foi possível realizar a criptografia...";
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, msg, error);
            ConsoleText.updateConsole(msg);
            ConsoleText.updateConsole(error.toString());
        }
        catch(IOException | IllegalBlockSizeException | BadPaddingException error) {
            String msg = "Não foi possível ler / gravar o arquivo...";
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, msg, error);
            ConsoleText.updateConsole(msg);
            ConsoleText.updateConsole(error.toString());
        }
    }
    
}
