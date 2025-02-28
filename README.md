# Documentação
Este README apresenta e detalha a aplicação desenvolvida para a case técnica da dti digital.

Autora: Carolina Morais Nigri

Data: 28/02/2025

## Descrição inicial da aplicação
Esta aplicação apresenta um CRUD de Livros, feito utilizando Java para o backend e SQLite para o banco de dados. 

Uma entidade do tipo `Livro` possui as seguintes propriedades:

* `id` do tipo `int`: Identificador da instância de Livro, valor único para cada livro gerado automaticamente ao inserir no banco de dados (obrigatório)
* `titulo` do tipo `String`: Título do livro (obrigatório)
* `autor` do tipo `String`: Autor do livro (obrigatório)
* `dataPublicacao` do tipo `LocalDate`: Data de publicação do livro (obrigatório)
* `qtdPaginas` do tipo `int`: Quantidade de páginas do livro (opcional)
* `idioma` do tipo `String`: Idioma no qual foi escrito o livro (opcional)
* `editora` do tipo `String`: Editora na qual foi publicado o livro (opcional)

## Organização do projeto
O projeto está organizado em pastas seguindo a seguinte estrutura:

```
CaseTecnicaDTI
|   README.md
|___lib
|   |   sqlite-jdbc-3.49.1.0.jar   
|___src
    |___app
    |   |   Aplicacao.java
    |___dao
    |   |   DAO.java
    |   |   LivroDAO.java
    |___database
    |   |   livraria.db
    |   |   script.sql
    |___model
        |   Livro.java
```

Dentro da pasta `lib` foi colocada a biblioteca SQLite JDBC para permitir o acesso e criação do banco de dados SQLite pelo Java.
Dentro da pasta `src` estão todos os códigos fonte em Java e o script SQL. Na pasta `app` está o arquivo principal de execução da aplicação, `Aplicacao.java`. Na pasta `dao` se encontram as classes que fazem o acesso e controle do banco de dados SQL (_Data Access Objects_): `DAO.java` e sua sub-classe `LivroDAO.java`. A última classe java está na pasta `model`: `Livro.java`, onde é definida a entidade `Livro` com todas as propriedades descritas anteriormente. Por fim, a pasta `database` contêm o script SQL (`script.sql`) de criação da tabela `Livros` no banco de dados. Além disso, é onde o banco de dados (`livraria.db`) é criado após execução da aplicação.

## Funcionalidades implementadas
A aplicação é executada no console, oferecendo um menu de opções para o usuário realizar as operações de CRUD sobre o banco de dados. Quando iniciada a execução, já é criada a base de dados `livraria.db` na pasta `src/database`, por meio da conexão da classe `LivroDAO` com o banco de dados, na qual é feito todo o controle das operações do CRUD pelas queries SQL. Ao instanciar `LivroDAO`, também já é criada a tabela `Livros` por meio do script SQL (disponível na pasta `src/database`).

```
Aplicação - CRUD de Livros
0 - Sair
1 - Listar Livros
2 - Buscar por ID
3 - Cadastrar Livro
4 - Atualizar Livro
5 - Deletar Livro

Digite uma opção (0 a 5): 
```

O usuário pode escolher uma das opções digitando seu respectivo número (de 0 a 5) no console e pressionando `enter`. A opção 0 encerra a aplicação e fecha o programa. As demais serão explicadas com mais detalhes a seguir. 
Caso seja digitada uma opção inválida (seja um caractere que não representa um número ou um número no intervalo < 0 ou > 5), uma mensagem aparece no console e é solicitado que o usuário digite novamente.

```
Digite uma opção (0 a 5): a
Formato do número inválido.
Opção inválida! Tente novamente.
Digite uma opção (0 a 5): -1
Opção inválida! Tente novamente.
Digite uma opção (0 a 5): 6
Opção inválida! Tente novamente.
Digite uma opção (0 a 5):
```

### Listar Livros
Ao escolher a opção 1, os livros registrados no banco de dados são mostrados no console, conforme exemplo:

```
1 | Dino Lernt Deutsch 1 | Andre Klein | 2013-12-11 | 96 págs. | Idioma desconhecido | Editora desconhecida
2 | O Ladrão de Raios - Percy Jackson e os Olimpianos 1 | Rick Riordan | 2014-08-14 | 400 págs. | Português | Intrínseca
3 | Harry Potter e a Pedra Filosofal | J.K. Rowling | 2015-12-08 | 312 págs. | Português | Pottermore Publishing
4 | Le Comte de Monte-Cristo | Alexandre Dumas | 1846-01-15 | 1304 págs. | Francês | Editora desconhecida
5 | A Biblioteca da Meia-Noite | Matt Haig | 2021-09-27 | Número de páginas desconhecido | Idioma desconhecido | Editora desconhecida
```

Se nenhum livro foi registrado ainda, é mostrada uma mensagem no console.

### Buscar por ID
Ao escolher a opção 2, o usuário pode escolher um ID de livro já registrado no banco de dados para buscar. Caso tente buscar um ID inválido, uma mensagem aparece alertando e a opção de digitar aparece novamente, além de uma opção de cancelar a operação com 0. Se digitar um ID inexistente no banco de dados, o console também exibe mensagem indicando que o livro não foi encontrado no banco de dados.

```
ID do livro [> 0]: -1
ID inválido, tente novamente ou cancele operação (0): 
```

```
ID do livro [> 0]: 3
3 | Harry Potter e a Pedra Filosofal | J.K. Rowling | 2015-12-08 | 312 págs. | Português | Pottermore Publishing
```

```
ID do livro [> 0]: 1
Livro não encontrado
```

### Cadastrar Livro
Ao escolher a opção 3, o usuário pode fornecer dados para cadastrar um novo livro no banco de dados. 
Caso todos dados sejam fornecidos corretamente e a inserção no banco de dados ocorra sem problemas, uma mensagem de sucesso e o livro que foi cadastrado são exibidos no console. 

```
Título: Harry Potter and the Philosopher's Stone
Autor: J. K. Rowling 
Data de publicação [YYYY-MM-DD]: 2014-09-01
Qtd de págs [0 a 40000] (opcional): 352
Idioma (opcional): English
Editora (opcional): Bloomsbury Childrens Books

Livro registrado com sucesso:
8 | Harry Potter and the Philosopher's Stone | J. K. Rowling | 2014-09-01 | 352 págs. | English | Bloomsbury Childrens Books
```

Os dados são consistidos e verificados para cada atributo do `Livro`, conforme seguintes restrições:
* `titulo`: Atributo obrigatório, não permite deixar em branco.
* `autor`: Atributo obrigatório, não permite deixar em branco.
* `dataPublicacao`: Atributo obrigatório, não permite deixar em branco. Datas inválidas (como mês > 13, dia > 31, textos que não representam uma data ou estão fora do formato YYYY-MM-DD) não são aceitas.
* `qtdPaginas`: Atributo opcional, pode ser deixado em branco. Número de páginas inválido (no intervalo < 0 ou > 40000 ou textos que não representam um número) não é aceito.
* `idioma`: Atributo opcional, pode ser deixado em branco. 
* `editora`: Atributo opcional, pode ser deixado em branco. 

### Atualizar Livro
Ao escolher a opção 4, o usuário pode atualizar um livro já existente no banco de dados, a partir do ID do livro em questão.
O ID é verificado da mesma forma que na opção de busca por ID e, após encontrar um livro válido para alteração e exibi-lo no console, é solicitado que os dados do novo livro sejam digitados da mesma forma que é feita na opção de cadastrar livro (com as mesmas verificações de dados). 

```
Livro atualizado com sucesso:
1 | Dino Lernt Deutsch 1 | Andre Klein | 2013-12-11 | 96 págs. | Idioma desconhecido | Editora desconhecida
```

### Deletar Livro
Ao escolher a opção 5, o usuário pode excluir um livro do banco de dados, a partir do ID do livro em questão.
O ID é verificado da mesma forma que nas opções de busca por ID e atualizar livro. Após encontrar um livro válido para exclusão e executar a operação no banco de dados com sucesso, uma mensagem é exibida. 

```
ID do livro a excluir [> 0]: 1
Livro de ID 1 excluído com sucesso.
```

## Requisitos
Para rodar o código deste projeto, são necessárias as seguintes dependências:

* Java (testado com Java 8)
* SQLite (testado com versão  3.49.1)

Este projeto foi testado somente em ambiente Windows (11) e todas as instruções relativas à execução consideram o uso deste sistema operacional. Também foi usado o VS Code, com extensões Java instaladas para realizar a execução do código.

## Instalação 

### Java
Para instalar o Java (por exemplo na versão 23, a mais atual), navegue até a página [Instalador da Oracle](https://www.oracle.com/java/technologies/downloads/#java23), selecione seu sistema operacional e baixe o instalador.
Inicie o arquivo baixado (`jdk-23_windows-x64_bin.exe`, por exemplo) e após abrir o setup, clique em `Continuar`. Espere a instalação terminar e clique em `Fechar`.

Feito isso, acrescente o Java nas variáveis de ambiente:

1.	Procure `Editar as variáveis de ambiente do sistema` nas configurações.
2.	Clique em `Variáveis de Ambiente...`.
3.	Clique no botão `Novo...` em Variáveis do sistema.
4.	Acrescente a variável `JAVA_HOME` e preencha com o caminho para a pasta do java: `C:\Program Files\Java\jdk-23\`.
5.	Em Variáveis do sistema localize a variável `Path`, clique nela e em `Editar...`.
6.	Clique em `Novo` e acrescente o caminho da pasta bin do java: `C:\Program Files\Java\jdk-23\bin`.
7.	Clique em OK e o caminho para o Java estará configurado. 	

Para testar se a instalação foi feita corretamente, abra o prompt de comando e digite `java --version`. É esperado que seja impresso algo como:
``` 
java 23.0.2 2025-01-21
Java(TM) SE Runtime Environment (build 23.0.2+7-58)
Java HotSpot(TM) 64-Bit Server VM (build 23.0.2+7-58, mixed mode, sharing) 
```

### SQLite
Para instalar o SQLite, na versão 3.49.1, navegue até a página [Download SQLite](https://www.sqlite.org/download.html) e baixe o `Precompiled Binaries` referente ao seu sistema operacional.
Extraia o conteúdo do arquivo baixado (`sqlite-tools-win-x64-3490100.zip`, por exemplo) para uma pasta de sua escolha, de preferência para `C:\sqlite3`.

Feito isso, acrescente o SQLite nas variáveis de ambiente:

1.	Procure `Editar as variáveis de ambiente do sistema` nas configurações.
2.	Clique em `Variáveis de Ambiente...`.
3.	Em Variáveis do sistema localize a variável `Path`, clique nela e em `Editar...`.
4.	Clique em `Novo` e acrescente o caminho da pasta do sqlite: `C:\sqlite3`.
5.	Clique em OK e o caminho para o SQLite estará configurado. 	

Para testar se a instalação foi feita corretamente, abra o prompt de comando e digite `sqlite3 --version`. É esperado que seja impresso algo como:
``` 
3.49.1 2025-02-18 13:38:58 873d4e274b4988d260ba8354a9718324a1c26187a4ab4c1cc0227c03d0f10e70 (64-bit)
```

## Configuração
A configuração do banco de dados SQLite já é feita diretamente no código da aplicação, por meio da classe `DAO.java`, que faz a conexão com o banco de dados e cria o arquivo `livraria.db` na pasta `src/database`. Após criado, a classe `LivroDAO.java` executa a query SQL para criação da tabela `Livros`.

## Execução
Para executar o código da aplicação, siga as instruções abaixo.

1. Navegue até o diretório do arquivo principal da aplicação, `src/app`.
2. Utilizando uma IDE (como VS Code, Eclipse ou IntelliJ), rode o arquivo `Aplicacao.java` por meio das funcionalidades de execução.  
3. Teste as funcionalidades conforme instruções e explicação feitas nesse arquivo.
