package asycry.crypto;

import asycry.ui.ConsoleText;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe destinada para gerar par de chaves (pública e privada).
 * @author chrisGrando
 */
public class MyKeyPair {
    private final String ALGORITHM = "RSA";
    private final int KEY_SIZE = 2048;
    private String publicKeyPath = null;
    private String privateKeyPath = null;
    
    /**
     * Configura o local da chave pública.
     * @param path
     * Caminho absoluto ou relativo do arquivo da chave.
     */
    public void setPublicKeyPath(String path) {
        publicKeyPath = path;
    }
    
    /**
     * Configura o local da chave privada.
     * @param path
     * Caminho absoluto ou relativo do arquivo da chave.
     */
    public void setPrivateKeyPath(String path) {
        privateKeyPath = path;
    }
    
    /**
     * Gera um par de chaves (pública e privada) e salva em arquivos.
     */
    public void generateMyKeys() {
        //Aborta se os diretórios não foram informados
        if(publicKeyPath == null || privateKeyPath == null) {
            System.out.println("Diretório da chave pública/privada não informado...");
            return;
        }
        
        try {
            //Cria gerador de valores aleatórios
            SecureRandom secureRandom = new SecureRandom();
            
            //Cria um gerador de par de chaves
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(ALGORITHM);

            //Define o tamanho e aleatoridade das chaves
            keyPairGenerator.initialize(KEY_SIZE, secureRandom);

            //Gera um par de chaves
            ConsoleText.updateConsole("Gerando par de chaves...");
            KeyPair keyPair = keyPairGenerator.generateKeyPair();

            //Obtém a chave pública
            PublicKey publicKey = keyPair.getPublic();
            ConsoleText.updateConsole("Chave pública gerada!");

            //Obtém a chave privada
            PrivateKey privateKey = keyPair.getPrivate();
            ConsoleText.updateConsole("Chave privada gerada!");
            
            //Cria arquivo da chave pública
            File publicKeyFile = new File(publicKeyPath);
            FileOutputStream outPublicKey = new FileOutputStream(publicKeyFile);
            outPublicKey.write(publicKey.getEncoded());
            outPublicKey.close();
            ConsoleText.updateConsole("Chave pública salva em: " + publicKeyFile.getAbsolutePath());
            
            //Cria arquivo da chave privada
            File privateKeyFile = new File(privateKeyPath);
            FileOutputStream outPrivateKey = new FileOutputStream(privateKeyFile);
            outPrivateKey.write(privateKey.getEncoded());
            outPrivateKey.close();
            ConsoleText.updateConsole("Chave privada salva em: " + privateKeyFile.getAbsolutePath());
        }
        catch(NoSuchAlgorithmException | FileNotFoundException error) {
            String msg = "Não foi possível salvar o par de chaves...";
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, msg, error);
            ConsoleText.updateConsole(msg);
            ConsoleText.updateConsole(error.toString());
        }
        catch(IOException error) {
            String msg = "Não foi possível gravar o par de chaves...";
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, msg, error);
            ConsoleText.updateConsole(msg);
            ConsoleText.updateConsole(error.toString());
        }
    }
    
}
