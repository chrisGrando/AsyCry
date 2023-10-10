# AsyCry ~ Criptografia Assimétrica
AV2 do CCR de Segurança Computacional (IFSC ~ Campus Lages).

## Resumo
A criptografia assimétrica é um método de criptografia que usa um par de chaves
distintas, uma pública e outra privada, para criptografar e descriptografar
mensagens. Ela oferece segurança e autenticidade, mas pode ser mais lenta e
exigir mais recursos computacionais do que a criptografia simétrica.

## O que este software faz
AsyCry é capaz de realizar as seguintes operações:

+ Gerar par de chaves (pública e privada).
+ Criptografar arquivos com a chave pública.
+ Decriptografar arquivos com a chave privada.

Suporta:

+ Qualquer formato de arquivo (texto, imagem, binários, etc.).
+ Cross-Platform (Windows, MacOSX, Linux).

## Compilar
Para compilar o AsyCry, você precisa ter instalado:

+ [Liberica JDK 17](https://bell-sw.com/pages/downloads/).
+ [Apache Maven](https://maven.apache.org/download.cgi).
+ Uma IDE Java compatível com Apache Maven. Ex.: [Apache NetBeans](https://netbeans.apache.org/download/index.html).

OBS.: Além da IDE, também é possível compilar na linha de comando com:

```mvn clean compile package```

## Executar
Assumindo que você já tenha **Java 17** ou superior instalado no seu sistema,
pode executar o aplicativo dando um duplo clique em ```AsyCry.jar``` ou
digitando na linha de comando:

```java -jar AsyCry.jar```

## Descrição completa sobre a criptografia assimétrica
A criptografia assimétrica é um método de criptografia que utiliza um par de
chaves distintas: uma chave pública e uma chave privada. A chave pública é
amplamente distribuída e pode ser usada por qualquer pessoa para criptografar
uma mensagem. Por outro lado, a chave privada é mantida em segredo pelo
proprietário e é usada para descriptografar a mensagem criptografada.

Quando alguém deseja enviar uma mensagem para outra pessoa usando criptografia
assimétrica, essa pessoa precisa conhecer a chave pública do destinatário. Eles
usam a chave pública para criptografar a mensagem e, em seguida, enviam a
mensagem criptografada para o destinatário. O destinatário, por sua vez, usa sua
chave privada correspondente para descriptografar a mensagem e ler o conteúdo
original.

A criptografia assimétrica oferece algumas vantagens em relação à criptografia
simétrica, onde a mesma chave é usada tanto para criptografar quanto para
descriptografar a mensagem. Algumas dessas vantagens incluem:

+ Segurança: A chave privada é mantida em segredo e nunca é compartilhada, o que
torna mais difícil para terceiros obterem acesso à mensagem original.

+ Autenticidade: A criptografia assimétrica permite a autenticação de mensagens.
O remetente pode assinar digitalmente a mensagem usando sua chave privada, e o
destinatário pode verificar a assinatura usando a chave pública do remetente,
garantindo que a mensagem não tenha sido alterada durante a transmissão.

+ Escalabilidade: Como a chave pública pode ser amplamente distribuída, qualquer
pessoa pode enviar uma mensagem criptografada para o proprietário da chave
privada, sem a necessidade de compartilhar uma chave secreta com cada pessoa
individualmente.

No entanto, a criptografia assimétrica também possui algumas desvantagens em
relação à criptografia simétrica. É mais lenta em comparação com a criptografia
simétrica, devido aos cálculos envolvidos na criptografia e descriptografia
usando chaves públicas e privadas. Além disso, a criptografia assimétrica requer
maior poder computacional e recursos para operar de forma eficiente.
