# Documentação Backend Ponto 8 App v2
A ideia do projeto é renovar completamente o antigo Ponto8WebApp, sistema criado em **Node.Js com Typescript**,
trazendo uma linguagem mais robusta, e seguindo padrões e convenções não inseridas no projeto anteriormente. \
Sistema de gestão para oficina mecânica. Este documento cobre apenas o **backend** do projeto `/ponto8app/`,
incluindo arquitetura, configuração, modelo de dados e endpoints da API.

## 1. Visão Geral
O backend é uma API REST originalmente construida com Node.js + Typescript, usando Express e postgreSQL, com
autenticação baseada em JWT + refresh token e geração de PDFs de ordens de serviço com PDFKit. \
Ao final do projeto a stack dele se tornara Java + Spring Boot, postgreSQL, autenticação baseada em JWT + refresh token e geração de PDFs
com JasperReports.
### 1.1. Stack e dependências
| Dependência                           | Uso                                     |
|---------------------------------------|-----------------------------------------|
| Springboot                            | Servidor HTTP e roteamento              |
| Spring Data                           | Gerenciamento de Banco de Dados         |
| JWT                                   | Emissão/Verificação de acess tokens JWT |
| Pacote de Hash de senhas desconhecido | Hash de Senhas                          |
| JasperReports                         | Geração de PDFs de ordem de serviço     |

### 1.2. Estrutura do projeto
```
ponto8app/
├── .mvn/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com.kaiserraf.ponto8app/
│   │   │       ├── controllers/         # controllers
│   │   │       ├── database/            # Interfaces para DB
│   │   │       │     └── models/        # Entidades para o Banco de Dados
│   │   │       ├── dto/                 # DTOs
│   │   │       ├── exceptions/          # exceções personalizadas
│   │   │       ├── services/            # services
│   │   │       └── Ponto8appApplication # arquivo principal
│   │   └── resources/
│   └── test/
├── .gitattributes
├── .gitignore
├── HELP.md
└── pom.xml
```

## 2. Modelo de dados (PostgreSQL)
### 2.1. clients
| Coluna | Tipo | Restrições |
|---|---|---|
| `id` | SERIAL | PK |
| `name` | VARCHAR(100) | NOT NULL |
| `address` | VARCHAR(200) | NOT NULL |
| `phone` | BIGINT | NOT NULL |
| `cpf` | VARCHAR(14) | NOT NULL, UNIQUE |
| `email` | VARCHAR(100) | NOT NULL, UNIQUE |

### 2.2. vehicles
| Coluna | Tipo | Restrições |
|---|---|---|
| `id_vehicle` | SERIAL | PK |
| `vehicle_model` | VARCHAR(60) | NOT NULL |
| `vehicle_brand` | VARCHAR(60) | NOT NULL |
| `year` | INT | NOT NULL |
| `chassi` | VARCHAR(17) | NOT NULL, UNIQUE |
| `plate` | VARCHAR(10) | NOT NULL, UNIQUE |
| `client_id` | INT | FK → `clients.id`, `ON DELETE CASCADE` |

### 2.3. parts
| Coluna | Tipo | Restrições |
|---|---|---|
| `id_part` | SERIAL | PK |
| `name_part` | VARCHAR(100) | NOT NULL |
| `amount` | INT | NOT NULL, DEFAULT 0 |
| `buy_value` | NUMERIC(10,2) | NOT NULL |
| `sale_value` | NUMERIC(10,2) | NOT NULL |

### 2.4. labors
| Coluna | Tipo | Restrições |
|---|---|---|
| `id_labor` | SERIAL | PK |
| `labor_name` | VARCHAR(100) | NOT NULL |

### 2.5. users
| Coluna | Tipo | Restrições |
|---|---|---|
| `id` | SERIAL | PK |
| `name` | VARCHAR(100) | NOT NULL |
| `email` | VARCHAR(100) | NOT NULL, UNIQUE |
| `password_hash` | VARCHAR(255) | NOT NULL |
| `created_at` | TIMESTAMP | NOT NULL, DEFAULT NOW() |

### 2.6. refresh_tokens
| Coluna | Tipo | Restrições |
|---|---|---|
| `id` | SERIAL | PK |
| `user_id` | INT | FK → `users.id`, `ON DELETE CASCADE` |
| `token` | TEXT | NOT NULL, UNIQUE |
| `expires_at` | TIMESTAMP | NOT NULL |
| `created_at` | TIMESTAMP | NOT NULL, DEFAULT NOW() |

### 2.6. service_orders
| Coluna | Tipo | Restrições |
|---|---|---|
| `id_so` | SERIAL | PK |
| `id_client` | INT | NOT NULL, FK → `clients.id` |
| `id_vehicle` | INT | NOT NULL, FK → `vehicles.id_vehicle` |
| `mechanic` | INT | NOT NULL, FK → `users.id` |
| `description` | TEXT | — |
| `total_price` | NUMERIC(10,2) | NOT NULL, DEFAULT 0 |
| `pdf_path` | VARCHAR(300) | — |
| `created_at` | TIMESTAMP | NOT NULL, DEFAULT NOW() |

### 2.7. order_parts
| Coluna | Tipo | Restrições |
|---|---|---|
| `id` | SERIAL | PK |
| `id_so` | INT | NOT NULL, FK → `service_orders.id_so`, `ON DELETE CASCADE` |
| `id_part` | INT | NOT NULL, FK → `parts.id_part` |
| `amount` | INT | NOT NULL |
| `unit_price` | NUMERIC(10,2) | NOT NULL |

### 2.8. order_labor
| Coluna | Tipo | Restrições |
|---|---|---|
| `id` | SERIAL | PK |
| `id_so` | INT | NOT NULL, FK → `service_orders.id_so`, `ON DELETE CASCADE` |
| `id_labor` | INT | NOT NULL, FK → `labors.id_labor` |
| `amount` | INT | NOT NULL, DEFAULT 1 |
| `unit_price` | NUMERIC(10,2) | NOT NULL |

## 3. Padrão de Resposta

## 4. Autenticação

## 5. Geração de PDF