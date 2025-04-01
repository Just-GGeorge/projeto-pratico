# Projeto-pratico
 Projeto prático PROCESSO SELETIVO SIMPLIFICADO Nº 002/2025/ SEPLAG

github: https://github.com/Just-GGeorge/projeto-pratico

Candidato: Guilherme George Oliveira da Silva

email: guilhermegeorge06@gmail.com

Este projeto é um sistema web desenvolvido em **Java + Spring Boot** com persistência em **PostgreSQL**, armazenamento de imagens com **MinIO**, autenticação via **JWT**, e executado totalmente via **Docker**. 

---

## 🔧 Tecnologias Utilizadas

- Java 21  
- Spring Boot 3.4.4  
- Maven  
- PostgreSQL 16+  
- MinIO (Java SDK 8.4.4)  
- JWT (JJWT 0.9.1)  
- Docker 

## 🚀 Como Executar o Projeto com Docker

### Pré-requisitos:

- Docker Desktop instalado

### Passos:

```bash
# 1. Clone o projeto
git clone https://github.com/Just-GGeorge/Projeto-pratico/

# 2. Suba os containers com Docker Compose
docker-compose up --build

# 2. Parar os containers com Docker Compose
docker-compose stop

```

A aplicação estará disponível em:  
📦 **API**: http://localhost:8080  
🛢 **MinIO Console**: http://localhost:9090 (login: `admin`, senha: `admin123`)  
🗄 **MinIO API**: http://localhost:9000  
🐘 **PostgreSQL**: localhost:5432 (admin / admin)

---

## 🔐 Autenticação

### Registro

```http
POST /auth/register
Content-Type: application/json

{
  "username": "meuUsuario",
  "email": "usuario@email.com",
  "password": "minhaSenha123"
}
```

### Login

```http
POST http://localhost:8080/auth/login
Content-Type: application/json

{
  "username": "meuUsuario",
  "password": "minhaSenha123"
}
```
### Exemplificado logo abaixo, passar o token para todas chamadas da API com exceção dos endpoint Registro e Login

![image](https://github.com/user-attachments/assets/14fdbc6a-a232-4454-ae7c-cb49309539e6)


## Endpoints Relevantes

Todos CRUDs foram implementados para cada tabela do projeto

![image](https://github.com/user-attachments/assets/7a43c67e-aab4-4604-846c-eb15c12d1d34)



### Adicionar Fotos

```http
POST /fotos/upload/{pessoaId}
Content-Type: multipart/form-data
file: <arquivo>
```

### Gerar Link da Foto

## 🛠 Endpoints Relevantes

- `/auth/register`: Registro
- `/auth/login`: Login (retorna token JWT)

### Servidores efetivos lotados em determinada unidade
```http
GET /servidores-efetivos/unidade/{id}
```
### Consultar o endereço funcional  a partir de uma parte do nome do servidor efetivo.
```http
GET /servidores-efetivos/endereco-funcional?nome={parte_nome}
```


### 📌 Servidor Efetivo

**GET todos os servidores efetivos**
```http
GET /servidores-efetivos
```

**GET por ID**
```http
GET /servidores-efetivos/{id}
```

**POST - criar**
```http
POST /servidores-efetivos
Content-Type: application/json
{
  "seMatricula": "EFT0011",
  "pessoa": {
    "pesId": 21
  }
}
```

**PUT - atualizar**
```http
PUT /servidores-efetivos/{id}
Content-Type: application/json
{
  "seMatricula": "EFT0011"
}
```

**DELETE**
```http
DELETE /servidores-efetivos/{id}
```

### 📌 Servidor Temporário

**GET todos**
```http
GET /servidores-temporarios
```

**GET por ID**
```http
GET /servidores-temporarios/{id}
```

**POST - criar**
```http
POST /servidores-temporarios
Content-Type: application/json
{
  "stDataAdmissao": "2023-01-01",
  "stDataDemissao": "2024-01-01",
  "pessoa": {
    "pesId": 22
  }
}
```

**PUT - atualizar**
```http
PUT /servidores-temporarios/{id}
Content-Type: application/json
{
  "stDataAdmissao": "2023-01-01",
  "stDataDemissao": "2024-01-01"
}
```

**DELETE**
```http
DELETE /servidores-temporarios/{id}
```

### 🏢 Unidade

**GET todas as unidades**
```http
GET /unidades
```

**GET por ID**
```http
GET /unidades/{id}
```

**POST - criar**
```http
POST /unidades
Content-Type: application/json
{
  "unidNome": "Secretaria de Transporte",
  "unidSigla": "ST"
}
```

**PUT - atualizar**
```http
PUT /unidades/{id}
Content-Type: application/json
{
  "unidNome": "Secretaria de Transportes",
  "unidSigla": "ST"
}
```

**DELETE**
```http
DELETE /unidades/{id}
```

### 🧭 Lotação

**GET todas**
```http
GET /lotacoes
```

**GET por ID**
```http
GET /lotacoes/{id}
```

**POST - criar**
```http
POST /lotacoes
Content-Type: application/json
{
  "lotDataLotacao": "2024-01-01",
  "lotDataRemocao": null,
  "lotPortaria": "Portaria 999",
  "pessoa": {
    "pesId": 21
  },
  "unidade": {
    "unidId": 1
  }
}
```

**PUT - atualizar**
```http
PUT /lotacoes/{id}
Content-Type: application/json
{
  "lotDataLotacao": "2024-01-01",
  "lotDataRemocao": null,
  "lotPortaria": "Portaria 999"
}
```

**DELETE**
```http
DELETE /lotacoes/{id}
```

## 🧾 Dados Iniciais no Projeto

As tabelas e registros são inseridos automaticamente via `initdb/script.sql`:

| Tabela                | Registros |
|-----------------------|-----------|
| cidade                | 2         |
| endereco              | 2         |
| unidade               | 2         |
| pessoa                | 20        |
| pessoa_endereco       | 20        |
| servidor_efetivo      | 10        |
| servidor_temporario   | 10        |
| lotacao               | 20        |

---

## 📦 Dependências e Versões (pom.xml)

| Dependência                    | Versão     |
|-------------------------------|------------|
| spring-boot-starter-web       | 3.4.4      |
| spring-boot-starter-data-jpa  | 3.4.4      |
| spring-boot-starter-security  | 3.4.4      |
| postgresql                    | 42.7.5     |
| jjwt                          | 0.9.1      |
| jakarta.servlet-api           | 6.0.0+     |
| jaxb-api                      | 2.3.1      |
| minio                         | 8.4.4      |
| spring-boot-devtools          | 3.4.4      |

---
