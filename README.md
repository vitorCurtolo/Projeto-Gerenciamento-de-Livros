## Documentação Técnica: Sistema de Gerenciamento de Livros

### 1. Introdução
* **Objetivo:** O sistema tem como objetivo principal gerenciar o acervo de uma biblioteca, permitindo o cadastro, edição, exclusão e pesquisa de livros.
* **Público-alvo:** A ferramenta foi desenvolvida para ser utilizada por funcionários de bibliotecas, com um nível de conhecimento técnico variado.
* **Escopo:** Este documento técnico detalha a arquitetura do sistema, as funcionalidades implementadas, as tecnologias utilizadas e os desafios enfrentados durante o desenvolvimento.

### 2. Arquitetura do Sistema
O sistema foi desenvolvido utilizando uma arquitetura MVC (Model-View-Controller).

* **Model:** Representa a camada de dados, composta por classes que mapeiam as entidades do banco de dados (Livro).
* **View:** Responsável pela interface do usuário, construída utilizando JSP (JavaServer Pages).
* **Controller:** Atua como intermediário entre a view e o model, processando as requisições do usuário e atualizando o modelo. Servlets foram utilizados como controladores.

**Tecnologias:**

* Backend: Java
* Servidor Web: Tomcat
* Banco de dados: MySQL
* Tecnologia Web: JSP
* Conexão com banco de dados: JDBC

### 3. Funcionamento do Sistema
**Fluxo principal:**

* O usuário acessa a aplicação e é apresentado com uma lista de livros cadastrados.
* O usuário pode filtrar a lista por nome, autor ou ISBN.
* Para cadastrar um novo livro, o usuário clica no botão "Adicionar Livro" e preenche o formulário.
* Para editar um livro, o usuário clica no ícone de edição e realiza as alterações necessárias.
* Para excluir um livro, o usuário clica no ícone de exclusão e confirma a operação.

**Funcionalidades detalhadas:**

* **Tela inicial:** A tela inicial apresenta os livros cadastrados em formato de lista ou card, dependendo da preferência do usuário. A pesquisa é realizada em tempo real, filtrando os resultados à medida que o usuário digita.
* **Cadastro de livros:** O formulário de cadastro solicita informações como título, autor, ISBN, ano de publicação e outras relevantes.
* **Edição de livros:** Permite a alteração de todos os campos do livro, exceto o ID.
* **Exclusão de livros:** Ao confirmar a exclusão, o livro é removido do banco de dados.

### 4. Dificuldades e Soluções
* **Usabilidade:** Para garantir uma boa experiência do usuário, foram utilizados componentes visuais intuitivos e mensagens de erro claras. Além disso, a interface foi projetada para ser responsiva, adaptando-se a diferentes tamanhos de tela.
* **Performance:** A performance da aplicação foi otimizada através da utilização de índices no banco de dados e da minificação de arquivos CSS e JavaScript.
* **Segurança:** Foram implementadas medidas de segurança para evitar injeção de SQL e outras vulnerabilidades comuns.

### 5. Considerações Finais
O sistema de gerenciamento de livros apresenta uma solução eficaz para o controle do acervo de uma biblioteca. Futuras melhorias podem incluir a implementação de um sistema de empréstimo, a integração com outros sistemas e a geração de relatórios personalizados.
Agradecimentos: Agradecemos a toda a equipe de desenvolvimento pela dedicação e profissionalismo.

### 6. Time de Desenvolvedores
Murilo Magossi - CP 301830X
Vitor Curtolo - CP 3019055
