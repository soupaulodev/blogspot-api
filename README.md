# API RESTful BlogSpot

## Visão Geral
**BlogSpot** é uma API RESTful robusta e escalável projetada para gerenciar posts e usuários em uma plataforma de blogs e artigos. Esta API permite que os usuários criem, editem, visualizem e excluam posts, além de gerenciar perfis de usuários. Com base nas melhores práticas de design RESTful, o BlogSpot proporciona uma experiência de desenvolvimento intuitiva e eficiente.

## Funcionalidades Principais

### Gerenciamento de Usuários
- **Cadastro**: Registro de novos usuários na plataforma.
- **Autenticação**: Sistema de login seguro utilizando JSON Web Tokens (JWT).
- **Perfil do Usuário**: Visualização e edição das informações de perfil do usuário.

### Gerenciamento de Posts
- **Criação de Post**: Usuários autenticados podem criar posts com título, conteúdo, resumo e categorias.
- **Edição de Post**: Autores podem editar posts existentes.
- **Exclusão de Post**: Possibilidade de remoção de posts pelos usuários.
- **Listagem de Posts**: Endpoints para listar todos os posts ou filtrar por autor e categorias.
- **Detalhes do Post**: Visualização completa dos detalhes de um post específico, incluindo comentários.

### Comentários
- **Adicionar Comentário**: Usuários podem adicionar comentários a posts.
- **Listar Comentários**: Visualização de todos os comentários associados a um post.

## Estrutura da API
- **Base URL**: `/api/v1`

### Endpoints Principais
#### Usuários
- `POST /users` - Cadastro de novos usuários.
- `POST /users/login` - Login de usuários.
- `GET /users/{id}` - Obter detalhes do usuário.
- `PUT /users/{id}` - Atualizar informações do usuário.

#### Posts
- `POST /posts` - Criar um novo post.
- `GET /posts` - Listar todos os posts.
- `GET /posts/{id}` - Obter detalhes de um post específico.
- `PUT /posts/{id}` - Atualizar um post existente.
- `DELETE /posts/{id}` - Excluir um post.

#### Comentários
- `POST /posts/{postId}/comments` - Adicionar um comentário a um post.
- `GET /posts/{postId}/comments` - Listar comentários de um post.

## Tecnologias Utilizadas
- **Backend**: Java com Spring Boot
- **Persistência**: Spring Data JPA com um banco de dados relacional (ex: PostgreSQL)
- **Autenticação**: JSON Web Tokens (JWT)
- **Documentação**: Swagger para documentação da API

## Instruções de Execução do Ambiente

### Requisitos
- **Java 21**: Certifique-se de que o Java 21 esteja instalado em seu sistema. Você pode verificar a instalação executando:
```bash
java -version
```
- **Docker**: Certifique-se de que o Docker esteja instalado e em execução. Você pode verificar a instalação executando:
```bash
docker --version
```
### Executando a API Localmente
1. Clone o Repositório
```bash
git clone https://github.com/seu-usuario/blogspot.git
cd blogspot
```

2. Compilar o Projeto
```bash
./mvnw clean install
```
3. Executar a Aplicação
```bash
./mvnw spring-boot:run
```

### Executando com Docker
1. Construir a Imagem Docker
```bash
docker build -t blogspot .
```

2. Executar o Contêiner
```bash
docker run -p 8080:8080 blogspot
```
3. Agora a API estará disponível em http://localhost:8080/api/v1.

## Considerações Finais
A API BlogSpot foi desenvolvida com foco em segurança e desempenho, proporcionando uma base sólida para a construção de plataformas de blogs modernas. É projetada para ser extensível e fácil de usar, permitindo que desenvolvedores integrem funcionalidades de gerenciamento de conteúdo em suas aplicações de maneira rápida e eficaz.

## Contribuições
Contribuições são bem-vindas! Sinta-se à vontade para abrir issues ou enviar pull requests.

## Licença
Esta API é licenciada sob a [Apache License 2.0](https://www.apache.org/licenses/LICENSE-2.0).
