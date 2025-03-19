# Front Salao Beleza

Este projeto é a parte front-end de um sistema de gerenciamento para um salão de beleza, onde os clientes podem realizar login, visualizar serviços, fazer agendamentos e consultar seus dados e agendamentos anteriores.

## Tecnologias Utilizadas

- **Spring Boot**: Framework Java utilizado para desenvolvimento de APIs.
- **Thymeleaf**: Motor de templates para renderização de HTML.
- **Tailwind CSS**: Framework CSS para estilização responsiva.
- **Spring Security**: Para autenticação e autorização.
- **RestTemplate**: Para comunicação com a API backend.

## Funcionalidades

### 1. **Login de Cliente**
O cliente pode se autenticar utilizando email e senha através da página de login. Caso as credenciais sejam corretas, o cliente será redirecionado para a página de perfil.

### 2. **Perfil do Cliente**
Após o login, o cliente pode acessar seu perfil, onde são exibidas suas informações pessoais, como nome, email e telefone. Esta página está protegida e só pode ser acessada por clientes autenticados.

### 3. **Serviços Disponíveis**
O cliente pode visualizar uma lista de serviços oferecidos pelo salão. O serviço pode ser agendado na página de agendamentos.

### 4. **Agendamentos**
O cliente pode visualizar seus agendamentos e filtrar pelo período (data de início e de fim). Também é possível agendar novos serviços através de um formulário.

## Como Executar o Projeto

### Passo 1:
Compile o Projeto utilizando o comando
```bash 
mvn clean install
```

### Passo 2:
Execute a Aplicação utilizando o comando
```bash
mvn spring-boot:run
```

# Não consegui finalizar o projeto no tempo proposto.
## Licença

[MIT](https://choosealicense.com/licenses/mit/)

