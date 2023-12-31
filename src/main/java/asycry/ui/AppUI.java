package asycry.ui;

import asycry.Main;
import asycry.crypto.Decryption;
import asycry.crypto.Encryption;
import asycry.crypto.MyKeyPair;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * Classe destinada ao front-end e back-end da aplicação.
 * @author chrisGrando
 */
public class AppUI extends JFrame {

    /**
     * Cria nova janela.
     */
    public AppUI() {
        initComponents();
    }
    
    /**
     * Executa a aplicação.
     */
    public void run() {
        //Configura o ambiente "Metal"
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Metal".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException error) {
            Logger.getLogger(AppUI.class.getName()).log(Level.SEVERE, error.getMessage(), error);
        }
        
        //Customiza elementos da janela
        setAppIcon();
        setAppFont();
        setCurrentDirectory();
        setCustomTitle();
        ConsoleText.setConsole(jTextAreaConsole);
        
        //Cria e exibe a janela
        EventQueue.invokeLater(() -> {
            setVisible(true);
        });
    }
    
    /**
     * Configura o ícone do aplicativo.
     */
    private void setAppIcon() {
        Resources res = new Resources();
        setIconImage(res.getIconFile());
    }
    
    /**
     * Configura a fonte do aplicativo.
     */
    private void setAppFont() {
        Resources res = new Resources();
        Font cousine = res.getCousineFont();
        final float SIZE = 12f;
        
        //Aborta caso tenha ocorrido algum erro para carregar o arquivo
        if(cousine == null)
            return;
        
        //Registra a fonte
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(cousine);
        
        //Aplica a fonte nos elementos da janela
        jButtonCrypto.setFont(cousine.deriveFont(Font.BOLD, SIZE + 2f));
        jButtonDecrypto.setFont(cousine.deriveFont(Font.BOLD, SIZE + 2f));
        jButtonGenerate.setFont(cousine.deriveFont(Font.BOLD, SIZE + 2f));
        jButtonOpenFile1.setFont(cousine.deriveFont(Font.BOLD, SIZE));
        jButtonOpenFile2.setFont(cousine.deriveFont(Font.BOLD, SIZE));
        jButtonOpenKey1.setFont(cousine.deriveFont(Font.BOLD, SIZE));
        jButtonOpenKey2.setFont(cousine.deriveFont(Font.BOLD, SIZE));
        jButtonSaveFile1.setFont(cousine.deriveFont(Font.BOLD, SIZE));
        jButtonSaveFile2.setFont(cousine.deriveFont(Font.BOLD, SIZE));
        jButtonSaveKey1.setFont(cousine.deriveFont(Font.BOLD, SIZE));
        jButtonSaveKey2.setFont(cousine.deriveFont(Font.BOLD, SIZE));
        jLabelInput1.setFont(cousine.deriveFont(SIZE));
        jLabelInput2.setFont(cousine.deriveFont(SIZE));
        jLabelKey1.setFont(cousine.deriveFont(SIZE));
        jLabelKey2.setFont(cousine.deriveFont(SIZE));
        jLabelOutput1.setFont(cousine.deriveFont(SIZE));
        jLabelOutput2.setFont(cousine.deriveFont(SIZE));
        jLabelPrivateKey.setFont(cousine.deriveFont(SIZE));
        jLabelPublicKey.setFont(cousine.deriveFont(SIZE));
        jTabbedPane.setFont(cousine.deriveFont(SIZE));
        jTextFieldInputFile1.setFont(cousine.deriveFont(SIZE));
        jTextFieldInputFile2.setFont(cousine.deriveFont(SIZE));
        jTextFieldKey1.setFont(cousine.deriveFont(SIZE));
        jTextFieldKey2.setFont(cousine.deriveFont(SIZE));
        jTextFieldOutputFile1.setFont(cousine.deriveFont(SIZE));
        jTextFieldOutputFile2.setFont(cousine.deriveFont(SIZE));
        jTextFieldPrivateKey.setFont(cousine.deriveFont(SIZE));
        jTextFieldPublicKey.setFont(cousine.deriveFont(SIZE));
        jTextAreaConsole.setFont(cousine.deriveFont(SIZE + 1f));
    }
    
    /**
     * Configura o diretório atual como local padrão para os componentes
     * "jFileChooser".
     */
    private void setCurrentDirectory() {
        final String PATH = System.getProperty("user.dir");
        final File DIR = new File(PATH);
        
        jFileChooserInputFile.setCurrentDirectory(DIR);
        jFileChooserOpenKey.setCurrentDirectory(DIR);
        jFileChooserOutputFile.setCurrentDirectory(DIR);
        jFileChooserSaveKey.setCurrentDirectory(DIR);
    }
    
    /**
     * Configura título da janela com nome e versão do aplicativo.
     */
    private void setCustomTitle() {
        Package mainPackage = Main.class.getPackage();
        String appName = mainPackage.getImplementationTitle();
        String appVersion = mainPackage.getImplementationVersion();
        
        //Aborta se o nome ou versão do app for nulo
        if(appName == null || appVersion == null)
            return;
        
        setTitle(appName + " ~ v" + appVersion);
    }
    
    /**
     * Exibe janela de aviso.
     * @param msg
     * A mensagem para exibir na tela.
     */
    private void showWarning(String msg) {
        //Abre janela de aviso
        JOptionPane.showOptionDialog(
            this,
            msg,
            "AVISO",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.INFORMATION_MESSAGE,
            null,
            null,
            1
        );
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFileChooserInputFile = new javax.swing.JFileChooser();
        jFileChooserOutputFile = new javax.swing.JFileChooser();
        jFileChooserOpenKey = new javax.swing.JFileChooser();
        jFileChooserSaveKey = new javax.swing.JFileChooser();
        jTabbedPane = new javax.swing.JTabbedPane();
        jPanelCrypto = new javax.swing.JPanel();
        jLabelInput1 = new javax.swing.JLabel();
        jLabelOutput1 = new javax.swing.JLabel();
        jLabelKey1 = new javax.swing.JLabel();
        jTextFieldInputFile1 = new javax.swing.JTextField();
        jTextFieldOutputFile1 = new javax.swing.JTextField();
        jTextFieldKey1 = new javax.swing.JTextField();
        jButtonOpenFile1 = new javax.swing.JButton();
        jButtonSaveFile1 = new javax.swing.JButton();
        jButtonOpenKey1 = new javax.swing.JButton();
        jButtonCrypto = new javax.swing.JButton();
        jPanelDecrypto = new javax.swing.JPanel();
        jLabelInput2 = new javax.swing.JLabel();
        jLabelOutput2 = new javax.swing.JLabel();
        jLabelKey2 = new javax.swing.JLabel();
        jTextFieldInputFile2 = new javax.swing.JTextField();
        jTextFieldOutputFile2 = new javax.swing.JTextField();
        jTextFieldKey2 = new javax.swing.JTextField();
        jButtonOpenFile2 = new javax.swing.JButton();
        jButtonSaveFile2 = new javax.swing.JButton();
        jButtonOpenKey2 = new javax.swing.JButton();
        jButtonDecrypto = new javax.swing.JButton();
        jPanelKeys = new javax.swing.JPanel();
        jLabelPublicKey = new javax.swing.JLabel();
        jLabelPrivateKey = new javax.swing.JLabel();
        jTextFieldPublicKey = new javax.swing.JTextField();
        jTextFieldPrivateKey = new javax.swing.JTextField();
        jButtonSaveKey1 = new javax.swing.JButton();
        jButtonSaveKey2 = new javax.swing.JButton();
        jButtonGenerate = new javax.swing.JButton();
        jPanelConsole = new javax.swing.JPanel();
        jScrollPaneConsole = new javax.swing.JScrollPane();
        jTextAreaConsole = new javax.swing.JTextArea();

        jFileChooserInputFile.setCurrentDirectory(null);
        jFileChooserInputFile.setDialogTitle("Abrir arquivo");

        jFileChooserOutputFile.setDialogType(javax.swing.JFileChooser.SAVE_DIALOG);
        jFileChooserOutputFile.setCurrentDirectory(null);
        jFileChooserOutputFile.setDialogTitle("Salvar arquivo");

        jFileChooserOpenKey.setCurrentDirectory(null);
        jFileChooserOpenKey.setDialogTitle("Abrir chave");
        jFileChooserOpenKey.setFileFilter(new KeyFilter());

        jFileChooserSaveKey.setDialogType(javax.swing.JFileChooser.SAVE_DIALOG);
        jFileChooserSaveKey.setCurrentDirectory(null);
        jFileChooserSaveKey.setDialogTitle("Salvar chave");
        jFileChooserSaveKey.setFileFilter(new KeyFilter());

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Criptografia Assimétrica");
        setMinimumSize(new java.awt.Dimension(640, 480));
        setName("appFrame"); // NOI18N
        setPreferredSize(new java.awt.Dimension(640, 480));

        jTabbedPane.setFocusable(false);

        jPanelCrypto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanelCrypto.setFocusable(false);

        jLabelInput1.setText("Entrada");

        jLabelOutput1.setText("Saída");

        jLabelKey1.setText("Chave");
        jLabelKey1.setPreferredSize(new java.awt.Dimension(33, 22));

        jButtonOpenFile1.setText("...");
        jButtonOpenFile1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOpenFile1Clicked(evt);
            }
        });

        jButtonSaveFile1.setText("...");
        jButtonSaveFile1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveFile1Clicked(evt);
            }
        });

        jButtonOpenKey1.setText("...");
        jButtonOpenKey1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOpenKey1Clicked(evt);
            }
        });

        jButtonCrypto.setText("CRIPTOGRAFAR");
        jButtonCrypto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCryptoClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanelCryptoLayout = new javax.swing.GroupLayout(jPanelCrypto);
        jPanelCrypto.setLayout(jPanelCryptoLayout);
        jPanelCryptoLayout.setHorizontalGroup(
            jPanelCryptoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCryptoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelCryptoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabelInput1, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                    .addComponent(jLabelOutput1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelKey1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelCryptoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextFieldOutputFile1, javax.swing.GroupLayout.DEFAULT_SIZE, 423, Short.MAX_VALUE)
                    .addComponent(jTextFieldInputFile1, javax.swing.GroupLayout.DEFAULT_SIZE, 423, Short.MAX_VALUE)
                    .addComponent(jTextFieldKey1, javax.swing.GroupLayout.DEFAULT_SIZE, 423, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelCryptoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonOpenFile1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonSaveFile1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonOpenKey1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addGroup(jPanelCryptoLayout.createSequentialGroup()
                .addGap(192, 192, 192)
                .addComponent(jButtonCrypto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(194, 194, 194))
        );
        jPanelCryptoLayout.setVerticalGroup(
            jPanelCryptoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCryptoLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanelCryptoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelInput1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldInputFile1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonOpenFile1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelCryptoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelOutput1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldOutputFile1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonSaveFile1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelCryptoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelKey1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldKey1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonOpenKey1))
                .addGap(18, 18, 18)
                .addComponent(jButtonCrypto, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jTabbedPane.addTab("Criptografar", jPanelCrypto);

        jPanelDecrypto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanelDecrypto.setFocusable(false);

        jLabelInput2.setText("Entrada");

        jLabelOutput2.setText("Saída");

        jLabelKey2.setText("Chave");
        jLabelKey2.setPreferredSize(new java.awt.Dimension(33, 22));

        jButtonOpenFile2.setText("...");
        jButtonOpenFile2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOpenFile2Clicked(evt);
            }
        });

        jButtonSaveFile2.setText("...");
        jButtonSaveFile2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveFile2Clicked(evt);
            }
        });

        jButtonOpenKey2.setText("...");
        jButtonOpenKey2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOpenKey2Clicked(evt);
            }
        });

        jButtonDecrypto.setText("DECRIPTOGRAFAR");
        jButtonDecrypto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDecryptoClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanelDecryptoLayout = new javax.swing.GroupLayout(jPanelDecrypto);
        jPanelDecrypto.setLayout(jPanelDecryptoLayout);
        jPanelDecryptoLayout.setHorizontalGroup(
            jPanelDecryptoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDecryptoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelDecryptoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabelInput2, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                    .addComponent(jLabelOutput2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelKey2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelDecryptoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextFieldOutputFile2, javax.swing.GroupLayout.DEFAULT_SIZE, 423, Short.MAX_VALUE)
                    .addComponent(jTextFieldInputFile2, javax.swing.GroupLayout.DEFAULT_SIZE, 423, Short.MAX_VALUE)
                    .addComponent(jTextFieldKey2, javax.swing.GroupLayout.DEFAULT_SIZE, 423, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelDecryptoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonOpenFile2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonSaveFile2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonOpenKey2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addGroup(jPanelDecryptoLayout.createSequentialGroup()
                .addGap(192, 192, 192)
                .addComponent(jButtonDecrypto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(194, 194, 194))
        );
        jPanelDecryptoLayout.setVerticalGroup(
            jPanelDecryptoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDecryptoLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanelDecryptoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelInput2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldInputFile2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonOpenFile2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelDecryptoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelOutput2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldOutputFile2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonSaveFile2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelDecryptoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelKey2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldKey2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonOpenKey2))
                .addGap(18, 18, 18)
                .addComponent(jButtonDecrypto, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jTabbedPane.addTab("Decriptografar", jPanelDecrypto);

        jPanelKeys.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanelKeys.setFocusable(false);

        jLabelPublicKey.setText("Chave Pública");

        jLabelPrivateKey.setText("Chave Privada");

        jButtonSaveKey1.setText("...");
        jButtonSaveKey1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveKey1Clicked(evt);
            }
        });

        jButtonSaveKey2.setText("...");
        jButtonSaveKey2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveKey2Clicked(evt);
            }
        });

        jButtonGenerate.setText("GERAR CHAVES");
        jButtonGenerate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGenerateClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanelKeysLayout = new javax.swing.GroupLayout(jPanelKeys);
        jPanelKeys.setLayout(jPanelKeysLayout);
        jPanelKeysLayout.setHorizontalGroup(
            jPanelKeysLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelKeysLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelKeysLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabelPublicKey, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
                    .addComponent(jLabelPrivateKey, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelKeysLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldPublicKey)
                    .addComponent(jTextFieldPrivateKey))
                .addGap(6, 6, 6)
                .addGroup(jPanelKeysLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonSaveKey1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonSaveKey2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addGroup(jPanelKeysLayout.createSequentialGroup()
                .addGap(210, 210, 210)
                .addComponent(jButtonGenerate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(211, 211, 211))
        );
        jPanelKeysLayout.setVerticalGroup(
            jPanelKeysLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelKeysLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanelKeysLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelPublicKey, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldPublicKey, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonSaveKey1))
                .addGap(26, 26, 26)
                .addGroup(jPanelKeysLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelPrivateKey, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldPrivateKey, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonSaveKey2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addComponent(jButtonGenerate, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );

        jTabbedPane.addTab("Gerar Chaves", jPanelKeys);

        jPanelConsole.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "LOG", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP));

        jScrollPaneConsole.setBackground(new java.awt.Color(242, 242, 242));
        jScrollPaneConsole.setBorder(null);

        jTextAreaConsole.setEditable(false);
        jTextAreaConsole.setBackground(new java.awt.Color(241, 241, 241));
        jTextAreaConsole.setColumns(20);
        jTextAreaConsole.setRows(5);
        jTextAreaConsole.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jScrollPaneConsole.setViewportView(jTextAreaConsole);

        javax.swing.GroupLayout jPanelConsoleLayout = new javax.swing.GroupLayout(jPanelConsole);
        jPanelConsole.setLayout(jPanelConsoleLayout);
        jPanelConsoleLayout.setHorizontalGroup(
            jPanelConsoleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPaneConsole)
        );
        jPanelConsoleLayout.setVerticalGroup(
            jPanelConsoleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPaneConsole, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane)
                    .addComponent(jPanelConsole, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelConsole, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane.getAccessibleContext().setAccessibleName("tabForm");

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    //Botão de abrir arquivo para criptografar
    private void jButtonOpenFile1Clicked(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonOpenFile1Clicked
        System.out.println(evt.paramString());
        int value = jFileChooserInputFile.showOpenDialog(this);
        
        if(value == JFileChooser.APPROVE_OPTION) {
            File file = jFileChooserInputFile.getSelectedFile();
            jTextFieldInputFile1.setText(file.getAbsolutePath());
        }
    }//GEN-LAST:event_jButtonOpenFile1Clicked

    //Botão de salvar arquivo criptografado
    private void jButtonSaveFile1Clicked(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveFile1Clicked
        System.out.println(evt.paramString());
        int value = jFileChooserOutputFile.showSaveDialog(this);
        
        if(value == JFileChooser.APPROVE_OPTION) {
            File file = jFileChooserOutputFile.getSelectedFile();
            jTextFieldOutputFile1.setText(file.getAbsolutePath());
        }
    }//GEN-LAST:event_jButtonSaveFile1Clicked

    //Botão de abrir arquivo da chave pública
    private void jButtonOpenKey1Clicked(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonOpenKey1Clicked
        System.out.println(evt.paramString());
        int value = jFileChooserOpenKey.showOpenDialog(this);
        
        if(value == JFileChooser.APPROVE_OPTION) {
            File file = jFileChooserOpenKey.getSelectedFile();
            jTextFieldKey1.setText(file.getAbsolutePath());
        }
    }//GEN-LAST:event_jButtonOpenKey1Clicked

    //Botão de criptografar arquivo
    private void jButtonCryptoClicked(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCryptoClicked
        System.out.println(evt.paramString());
        String oldFile = jTextFieldInputFile1.getText();
        String newFile = jTextFieldOutputFile1.getText();
        String key = jTextFieldKey1.getText();
        
        //Aborta se um dos campos estiver em branco
        if(oldFile.isBlank() || newFile.isBlank() || key.isBlank()) {
            showWarning("Favor preencher todos os campos antes de continuar...");
            return;
        }
        
        //Desativa temporariamente o botão
        jButtonCrypto.setEnabled(false);
        
        //Log
        ConsoleText.updateConsole("*** Criptografando dados ***");
        ConsoleText.updateConsole("Arquivo original: " + (new File(oldFile)).getName());
        ConsoleText.updateConsole("Arquivo criptografado: " + (new File(newFile)).getName());
        ConsoleText.updateConsole("Chave pública: " + (new File(key)).getName());
        
        //Realiza a criptografia
        CompletableFuture.runAsync(() -> {
            Encryption encryption = new Encryption();
            encryption.setInputFile(oldFile);
            encryption.setOutputFile(newFile);
            encryption.loadPublicKey(key);
            encryption.runEncryption();
        })
        .thenRunAsync(() -> {
            //Reativa o botão
            jButtonCrypto.setEnabled(true);
        });
    }//GEN-LAST:event_jButtonCryptoClicked

    //Botão de abrir arquivo para decriptografar
    private void jButtonOpenFile2Clicked(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonOpenFile2Clicked
        System.out.println(evt.paramString());
        int value = jFileChooserInputFile.showOpenDialog(this);
        
        if(value == JFileChooser.APPROVE_OPTION) {
            File file = jFileChooserInputFile.getSelectedFile();
            jTextFieldInputFile2.setText(file.getAbsolutePath());
        }
    }//GEN-LAST:event_jButtonOpenFile2Clicked

    //Botão de salvar arquivo decriptografado
    private void jButtonSaveFile2Clicked(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveFile2Clicked
        System.out.println(evt.paramString());
        int value = jFileChooserOutputFile.showSaveDialog(this);
        
        if(value == JFileChooser.APPROVE_OPTION) {
            File file = jFileChooserOutputFile.getSelectedFile();
            jTextFieldOutputFile2.setText(file.getAbsolutePath());
        }
    }//GEN-LAST:event_jButtonSaveFile2Clicked

    //Botão de abrir arquivo da chave privada
    private void jButtonOpenKey2Clicked(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonOpenKey2Clicked
        System.out.println(evt.paramString());
        int value = jFileChooserOpenKey.showOpenDialog(this);
        
        if(value == JFileChooser.APPROVE_OPTION) {
            File file = jFileChooserOpenKey.getSelectedFile();
            jTextFieldKey2.setText(file.getAbsolutePath());
        }
    }//GEN-LAST:event_jButtonOpenKey2Clicked

    //Botão de decriptografar arquivo
    private void jButtonDecryptoClicked(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDecryptoClicked
        System.out.println(evt.paramString());
        String oldFile = jTextFieldInputFile2.getText();
        String newFile = jTextFieldOutputFile2.getText();
        String key = jTextFieldKey2.getText();
        
        //Aborta se um dos campos estiver em branco
        if(oldFile.isBlank() || newFile.isBlank() || key.isBlank()) {
            showWarning("Favor preencher todos os campos antes de continuar...");
            return;
        }
        
        //Desativa temporariamente o botão
        jButtonDecrypto.setEnabled(false);
        
        //Log
        ConsoleText.updateConsole("*** Decriptografando dados ***");
        ConsoleText.updateConsole("Arquivo original: " + (new File(oldFile)).getName());
        ConsoleText.updateConsole("Arquivo criptografado: " + (new File(newFile)).getName());
        ConsoleText.updateConsole("Chave pública: " + (new File(key)).getName());
        
        //Realiza a decriptografia
        CompletableFuture.runAsync(() -> {
            Decryption decryption = new Decryption();
            decryption.setInputFile(oldFile);
            decryption.setOutputFile(newFile);
            decryption.loadPrivateKey(key);
            decryption.runDecryption();
        })
        .thenRunAsync(() -> {
            //Reativa o botão
            jButtonDecrypto.setEnabled(true);
        });
    }//GEN-LAST:event_jButtonDecryptoClicked

    //Botão de salvar arquivo da chave pública
    private void jButtonSaveKey1Clicked(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveKey1Clicked
        System.out.println(evt.paramString());
        int value = jFileChooserSaveKey.showSaveDialog(this);
        
        if(value == JFileChooser.APPROVE_OPTION) {
            File file = jFileChooserSaveKey.getSelectedFile();
            String path = file.getAbsolutePath();
            
            //Insere ".dat" no nome do arquivo se não houver extensão
            if(!file.getName().contains("."))
                path += ".dat";
            
            jTextFieldPublicKey.setText(path);
        }
    }//GEN-LAST:event_jButtonSaveKey1Clicked

    //Botão de salvar arquivo da chave privada
    private void jButtonSaveKey2Clicked(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveKey2Clicked
        System.out.println(evt.paramString());
        int value = jFileChooserSaveKey.showSaveDialog(this);
        
        if(value == JFileChooser.APPROVE_OPTION) {
            File file = jFileChooserSaveKey.getSelectedFile();
            String path = file.getAbsolutePath();
            
            //Insere ".dat" no nome do arquivo se não houver extensão
            if(!file.getName().contains("."))
                path += ".dat";
            
            jTextFieldPrivateKey.setText(path);
        }
    }//GEN-LAST:event_jButtonSaveKey2Clicked

    //Botão de gerar par de chaves
    private void jButtonGenerateClicked(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGenerateClicked
        System.out.println(evt.paramString());
        String publicKey = jTextFieldPublicKey.getText();
        String privateKey = jTextFieldPrivateKey.getText();
        
        //Aborta se um dos campos estiver em branco
        if(publicKey.isBlank() || privateKey.isBlank()) {
            showWarning("Favor preencher todos os campos antes de continuar...");
            return;
        }
        
        //Desativa temporariamente o botão
        jButtonGenerate.setEnabled(false);
        
        //Log
        ConsoleText.updateConsole("*** Gerando par de chaves ***");
        ConsoleText.updateConsole("Chave pública: " + (new File(publicKey)).getName());
        ConsoleText.updateConsole("Chave privada: " + (new File(privateKey)).getName());
        
        //Gera par de chaves
        CompletableFuture.runAsync(() -> {
            MyKeyPair mkp = new MyKeyPair();
            mkp.setPublicKeyPath(publicKey);
            mkp.setPrivateKeyPath(privateKey);
            mkp.generateMyKeys();
        })
        .thenRunAsync(() -> {
            jButtonGenerate.setEnabled(true);
        });
    }//GEN-LAST:event_jButtonGenerateClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCrypto;
    private javax.swing.JButton jButtonDecrypto;
    private javax.swing.JButton jButtonGenerate;
    private javax.swing.JButton jButtonOpenFile1;
    private javax.swing.JButton jButtonOpenFile2;
    private javax.swing.JButton jButtonOpenKey1;
    private javax.swing.JButton jButtonOpenKey2;
    private javax.swing.JButton jButtonSaveFile1;
    private javax.swing.JButton jButtonSaveFile2;
    private javax.swing.JButton jButtonSaveKey1;
    private javax.swing.JButton jButtonSaveKey2;
    private javax.swing.JFileChooser jFileChooserInputFile;
    private javax.swing.JFileChooser jFileChooserOpenKey;
    private javax.swing.JFileChooser jFileChooserOutputFile;
    private javax.swing.JFileChooser jFileChooserSaveKey;
    private javax.swing.JLabel jLabelInput1;
    private javax.swing.JLabel jLabelInput2;
    private javax.swing.JLabel jLabelKey1;
    private javax.swing.JLabel jLabelKey2;
    private javax.swing.JLabel jLabelOutput1;
    private javax.swing.JLabel jLabelOutput2;
    private javax.swing.JLabel jLabelPrivateKey;
    private javax.swing.JLabel jLabelPublicKey;
    private javax.swing.JPanel jPanelConsole;
    private javax.swing.JPanel jPanelCrypto;
    private javax.swing.JPanel jPanelDecrypto;
    private javax.swing.JPanel jPanelKeys;
    private javax.swing.JScrollPane jScrollPaneConsole;
    private javax.swing.JTabbedPane jTabbedPane;
    private javax.swing.JTextArea jTextAreaConsole;
    private javax.swing.JTextField jTextFieldInputFile1;
    private javax.swing.JTextField jTextFieldInputFile2;
    private javax.swing.JTextField jTextFieldKey1;
    private javax.swing.JTextField jTextFieldKey2;
    private javax.swing.JTextField jTextFieldOutputFile1;
    private javax.swing.JTextField jTextFieldOutputFile2;
    private javax.swing.JTextField jTextFieldPrivateKey;
    private javax.swing.JTextField jTextFieldPublicKey;
    // End of variables declaration//GEN-END:variables
}
