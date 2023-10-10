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
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 * Classe destinada para decriptografar arquivos.
 * @author chrisGrando
 */
public class Decryption {
    private final String ALGORITHM = "RSA";
    private PrivateKey privateKey = null;
    private File inputFile = null;
    private File outputFile = null;
    
    /**
     * Carrega a chave privada de um arquivo e armazena seu conteúdo.
     * @param path
     * Local da chave.
     */
    public void loadPrivateKey(String path) {
        try {
            //Lê a chave
            File keyFile = new File(path);
            FileInputStream fis = new FileInputStream(keyFile);
            ConsoleText.updateConsole("Lendo a chave...");
            byte[] keyData = fis.readAllBytes();
            fis.close();
            
            //Armazena a chave
            ConsoleText.updateConsole("Armazenando a chave...");
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyData);
            KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
            privateKey = keyFactory.generatePrivate(keySpec);
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
     * Configura o arquivo de entrada para decriptografar.
     * @param path
     * Local do arquivo.
     */
    public void setInputFile(String path) {
        File file = new File(path);
        inputFile = file;
    }
    
    /**
     * Configura o arquivo de saída com o conteúdo decriptografado.
     * @param path
     * Local do arquivo.
     */
    public void setOutputFile(String path) {
        File file = new File(path);
        outputFile = file;
    }
    
    /**
     * Executa a decriptação do arquivo.
     */
    public void runDecryption() {
        //Aborta se os arquivos necessários não foram informados
        if(privateKey == null || inputFile == null || outputFile == null) {
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
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            
            //Decriptografa o conteúdo do arquivo
            ConsoleText.updateConsole("Lendo o arquivo criptografado...");
            byte[] cryptoData = input.readAllBytes();
            final int SIZE = cryptoData.length;
            final int SECTION = 256;
            int index = 0;
            
            while(index <= (SIZE - 1)) {
                //Posição final do trecho atual
                int max = SECTION;
                if((SIZE - 1) - (index + SECTION) < 0)
                    max = SIZE - index;
                
                //Decriptografia
                ConsoleText.updateConsole("Decriptografando posição " + Integer.toString(index) + " até " + Integer.toString(index + max) + "...");
                byte[] decryptoData = cipher.doFinal(cryptoData, index, max);
                
                //Gravação
                ConsoleText.updateConsole("Gravando dados...");
                output.write(decryptoData);
                
                index += SECTION;
            }
            
            //Fim
            ConsoleText.updateConsole("Pronto!");
            ConsoleText.updateConsole("Arquivo decriptografado salvo em: " + outputFile.getAbsolutePath());
            
            //Fecha arquivos
            input.close();
            output.close();
        }
        catch(FileNotFoundException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException error) {
            String msg = "Não foi possível realizar a decriptografia...";
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
