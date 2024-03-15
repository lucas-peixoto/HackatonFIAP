# Cenário:
Somos uma startup de Sistemas de Hospitalidade, e queremos oferecer uma solução de bom
custo-benefício para os nossos Clientes, para tanto, provemos plataformas de hospitalidade simples e
efetivas que permite que nossos Clientes, pequenos Hotéis e Pousadas, não tenham dificuldades
implementando ou utilizando o sistema.
Nossa solução deverá ser 100% Cloud Based e todas as funções devem ser APIs, no futuro faremos uma
solução híbrida, evitando assim problemas de conectividade. Sendo assim, tanto nosso Front End Web e
Mobile utilizarão os serviços de nossa plataforma, sem distinção, ou seja, nosso Back End proverá os
serviços necessários para qualquer uma de nossas interfaces.

# Objetivo:
Criar um sistema, que atenda um dos clientes do cenário acima descrito, considerando que o sistema
em questão deve possuir as seguintes funcionalidades:
Gestão de Quartos: o módulo de Gestão de Quartos, faz a gestão (Inclusão, Atualização, Deleção,
Consulta) de todos os quartos por propriedade, ou seja, caso tenhamos mais de uma localidade ou prédio,
o módulo consegue diferenciar quais quartos estão em qual prédio de qual localidade.

# MicroServices

## User

### Create: 
- URI: http://localhost:8081/api/users/users
- Method: POST

- Curl:
``` 
curl -X POST \
  -H "Content-Type: application/json" \
  -d '{
    "name": "John Doe",
    "country": "Brazil",
    "cpf": "12345678900",
    "passport": "123456789",
    "birthDate": "1990-01-01",
    "address": "123 Main St",
    "phone": "+55 11 98765432",
    "email": "john.doe@example.com"
  }' http://localhost:8081/api/users/users
```
- Json: 
```json
    {
      "name": "John Doe",
      "country": "Brazil",
      "cpf": "12345678900",
      "passport": "123456789",
      "birthDate": "1990-01-01",
      "address": "123 Main St",
      "phone": "+55 11 98765432",
      "email": "john.doe@example.com"
    }
```

### FindById
- URI: http://localhost:8081/api/users/users/{id}
- Method: GET
- Curl: 
```
curl -X GET \
  http://localhost:8081/api/users/users/1
```

### FindAll
- URI: http://localhost:8081/api/users/users/
- Method: GET
- Curl:
```
curl -X GET \
  http://localhost:8081/api/users/users?page=0&size=10
```

### Update
- URI: http://localhost:8081/api/users/users/{id}
- Method: PUT
- Curl:
```
curl -X PUT \
  -H "Content-Type: application/json" \
  -d '{
    "id": 123,
    "name": "João Silva",
    "country": "Brasil",
    "cpf": "12345678970",
    "passport": "123456780",
    "birthDate": "1990-01-01",
    "address": "Rua das Flores, 123",
    "phone": "+55 11 98765432",
    "email": "joaosilva23@exemplo.com"
  }' \
  http://localhost:8081/api/users/users/1
```


