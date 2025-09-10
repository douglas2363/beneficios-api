# Benefícios API

API de gerenciamento de benefícios desenvolvida em **Spring Boot (Java 17)** com segurança JWT, documentação Swagger, banco em memória **H2** e mensageria assíncrona via **Kafka**.

---

## 🚀 Funcionalidades
- Autenticação JWT (`/api/auth/login`)
- CRUD de benefícios (`/api/beneficios`)
- Desativação de benefício (`PUT /api/beneficios/{id}/deactivate`)
- Auditoria de eventos em banco
- Publicação de eventos no **Kafka**
- Consumo de eventos do **Kafka** (logs, auditoria, notificações)
- Monitoramento via **Spring Boot Actuator** + **Prometheus**

---

## 📦 Tecnologias
- **Java 17**
- **Spring Boot 3**
- **Spring Security** (JWT)
- **Spring Data JPA + H2**
- **Spring for Kafka**
- **Swagger (springdoc-openapi)**
- **Actuator + Micrometer**
- **Docker + Docker Compose**

---

## 🐳 Subindo infraestrutura com Docker Compose

> ⚠️ No Linux, é preciso usar `sudo` para rodar Docker.  
> Certifique-se que o arquivo `docker-compose.yml` está na raiz do projeto.

### Subir Kafka + Zookeeper
```bash
sudo docker compose up -d
```

### Verificar status
```bash
sudo docker compose ps
sudo docker compose logs -f kafka
```

Se tudo certo, o Kafka deve estar ouvindo em **localhost:9092**.

### Derrubar containers
```bash
sudo docker compose down -v
```

---

## ▶️ Rodando a aplicação

### Build
```bash
./mvnw clean package
```

### Rodar local
```bash
./mvnw spring-boot:run
```

---

## 🌐 Endpoints principais

### Swagger UI
👉 [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

### H2 Console
👉 [http://localhost:8080/h2-console](http://localhost:8080/h2-console)  
JDBC URL: `jdbc:h2:mem:beneficiosdb`  
Usuário: `sa`  
Senha: (vazio)

### Actuator
👉 [http://localhost:8080/actuator/health](http://localhost:8080/actuator/health)  
👉 [http://localhost:8080/actuator/prometheus](http://localhost:8080/actuator/prometheus)

---

## 🔑 Autenticação (JWT)

### Login
```http
POST /api/auth/login
```
Body:
```json
{
  "username": "admin",
  "password": "123456"
}
```

Resposta:
```json
{ "token": "eyJhbGciOiJIUzUxMiJ9..." }
```

No Swagger, clique em **Authorize** e insira:
```
Bearer <token>
```

---

## 📡 Fluxo com Kafka

1. Ao **salvar** um benefício → evento `CREATED` publicado em `benefit-events`.  
2. Ao **desativar** → evento `DEACTIVATED`.  
3. Ao **deletar** → evento `DELETED`.  
4. **Consumer** (`BenefitEventsListener`) recebe eventos e:  
   - Loga no console  
   - Grava auditoria no banco  
   - (Opcional) Notifica webhook ou indexa no Elastic

---

## 📖 Exemplos de uso

### Criar benefício
```http
POST /api/beneficios
Authorization: Bearer <token>
Content-Type: application/json

{
  "nome": "Plano de Saúde",
  "descricao": "Cobertura completa",
  "active": true
}
```

### Desativar benefício
```http
PUT /api/beneficios/1/deactivate
Authorization: Bearer <token>
```

---

## 🧪 Testando Kafka manualmente

Listar tópicos:
```bash
sudo docker compose exec kafka kafka-topics --bootstrap-server localhost:9092 --list
```

Consumir mensagens:
```bash
sudo docker compose exec kafka kafka-console-consumer \
  --bootstrap-server localhost:9092 \
  --topic benefit-events \
  --from-beginning
```

---

## 🛠 Fluxo completo
1. Sobe Kafka + Zookeeper (`sudo docker compose up -d`)  
2. Roda a aplicação (`./mvnw spring-boot:run`)  
3. Acessa Swagger e faz login (`/api/auth/login`)  
4. Usa o token no Authorize  
5. Cria/edita/deleta benefícios → eventos vão pro Kafka  
6. Consumer registra auditoria no banco e logs aparecem na aplicação  
