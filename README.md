
## **Instruções Gerais:**

1. **Formação de Duplas**:
   - Os trabalhos serão feitos em duplas, com divisão equilibrada de tarefas entre ambos os membros.
   
2. **Tecnologias a Utilizar**:
   - **Java** para o desenvolvimento da aplicação backend.
   - **JSP** ou **JSTL** para a camada de apresentação (interface do usuário).
   - **Servlets** para o controle de fluxo de dados entre as páginas JSP e o backend.
   - **DTOs (Data Transfer Objects)** para transferência de dados entre a camada de apresentação e a camada de persistência.
   - **JDBC** para acesso ao banco de dados MySQL.
   - **MySQL** como banco de dados relacional para persistência dos dados.

3. **Requisitos Mínimos da Aplicação**:
   - A aplicação deve implementar o padrão MVC (Model-View-Controller).
     - Model: **Book**
     - View: **BookDAO**
     - Controller: **BookServlet**
   - A aplicação deverá permitir as seguintes operações (CRUD) para o tema escolhido:
     - **Create**: Inserir novos registros no banco de dados. **OK**
     - **Read**: Listar registros armazenados no banco. **OK**
     - **Update**: Atualizar registros existentes. **OK**
     - **Delete**: Excluir registros do banco. **OK**
   - Cada entidade do sistema deve ser mapeada para uma tabela no banco de dados. **OK**
   - Utilizar **PreparedStatements** para garantir segurança e prevenir SQL Injection. **OK**
   - O sistema deve ser simples, mas funcional, priorizando a organização do código e a clareza na implementação dos conceitos. **OK**

4. **Funcionalidades Extras**:
   - Utilizar paginadores (pagination) para a listagem de dados. 
   - Implementar validações nos formulários (ex: campos obrigatórios, validação de e-mail, etc.). **OK**
   - Implementar feedback visual para o usuário após operações de CRUD (mensagens de sucesso/erro). **OK**

5. **Documentação**:
   - Incluir uma breve documentação explicando o funcionamento do sistema, as tecnologias utilizadas e as principais dificuldades encontradas (1 ou 2 páginas).

6. **Entrega**:
   - O código deve ser versionado em um repositório Git e submetido até a data estabelecida.
   - Além do código, a entrega deve incluir um **script SQL** para a criação das tabelas no banco de dados MySQL e um breve **manual de execução** do sistema.
