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

# Como executar a aplicação:
- cd HackatonFIAP/
- docker compose up

> Arquitetura
Microserviços

> User

##### Create: 
- URI: http://localhost:8081/api/users/users
- Method: POST

- Curl:
``` bash
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

##### FindById
- URI: http://localhost:8081/api/users/users/{id}
- Method: GET
- Curl: 
```bash
    curl -X GET \
      http://localhost:8081/api/users/users/1
```

##### FindAll
- URI: http://localhost:8081/api/users/users/
- Method: GET
- Curl:
```bash
    curl -X GET \
      http://localhost:8081/api/users/users?page=0&size=10
```

##### Update
- URI: http://localhost:8081/api/users/users/{id}
- Method: PUT
- Curl:
```bash
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
> Service

#### Product

###### Create
- URI: http://localhost:8081/api/services/products
- Method: POST
- Curl:
```bash
    curl -X POST \
      -H "Content-Type: application/json" \
      -d '{
        "name": "Limpeza",
        "price": 199.99,
        "type": "SERVICE"
      }' http://localhost:8081/api/services/products
```
- Json
```json
    {
      "name": "Limpeza",
      "price": 199.99,
      "type": "SERVICE"
    }
```
###### FindById
- URI: http://localhost:8081/api/services/products/{id}
- Method: GET
- Curl:
```bash
    curl -X GET \
      http://localhost:8081/api/services/products/1
```

###### FindAll

URI: http://localhost:8081/api/services/products/
Method: GET
Curl: 
```bash
    curl -X GET \
      http://localhost:8081/api/services/products?page=0&size=10
```

###### Update
URI: http://localhost:8081/api/services/products/
Method: PUT
Curl: 
```bash
    curl -X PUT \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Serviço de quarto",
    "price": 249.99,
    "type": "SERVICE"
  }' http://localhost:8081/api/services/products/1
```

###### Delete
URI: http://localhost:8081/api/services/products/
Method: DELETE
Curl:
```bash
    curl -X DELETE \
  -H "Content-Type: application/json" \
  http://localhost:8081/api/services/products/2
```

>  Room

##### Location
- URI: http://localhost:8081/api/room/locations
- Method: POST

```bash
    curl -X POST \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Beachfront Resort",
    "address": {
      "street": "123 Ocean View Dr",
      "city": "Miami",
      "state": "FL",
      "zipCode": "12345"
    },
    "amenities": {
      "1": "Swimming Pool",
      "2": "Spa",
      "3": "Restaurant"
    }
  }' http://localhost:8081/api/room/locations
```

##### FindById
- URI: /api/room/locations/{id} (A confirmação desse endpoint depende da sua API específica.)
- Método: GET
- Curl:
```bash
  curl -X GET \
  http://localhost:8081/api/room/locations/1
```

##### FindAll
- URI: /api/room/locations (A confirmação desse endpoint depende da sua API específica.)
- Método: GET
- Curl:
```bash 
    curl -X GET \
      http://localhost:8081/api/room/locations?page=0&size=25
```

##### Update
- URI: /api/room/locations/{id}
- Método: PUT
- Curl: 
```bash
curl -X PUT \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Mountain View Hotel",
    "address": {
      "street": "100 Evergreen St",
      "city": "Denver",
      "state": "CO",
      "zipCode": "80203"
    },
    "amenities": {
      "1": "Ski Rentals",
      "2": "Hot Tub",
      "3": "Fireplace"
    }
  }' http://localhost:8081/api/room/locations/1
```

##### Delete

- URI: /api/room/locations/{id}
- Método: DELETE
- Curl:
```bash
  curl -X DELETE \
  http://localhost:8081/api/room/locations/1
```

### Building

##### Create
- URI: http://localhost:8081/api/room/location/{id}/buildings
- Método: Post
- Curl:
```bash
  curl -X POST \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Edifício Copan",
    "address": {
      "street": "Avenida São João, 374",
      "city": "São Paulo",
      "state": "SP",
      "zipCode": "01030-000"
    },
    "numberOfFloors": 38,
    "numberOfApartments": 1140
  }' http://localhost:8081/api/room/location/2/buildings
```

##### FindById
- URI: http://localhost:8081/api/room/buildings/{id}
- Método: GET
- Curl: 
```bash
    curl -X GET \
  http://localhost:8081/api/room/buildings/1
```

##### FindAll
- URI: http://localhost:8081/api/room/buildings
- Método: GET
- Curl:
```bash
    curl -X GET \
  http://localhost:8081/api/room/buildings
```

##### Update
- URI: http://localhost:8081/api/room/buildings/{id}
- Método: PUT
- Curl:
```bash
    curl -X PUT \
      -H "Content-Type: application/json" \
      -d '{
        "name": "Edifício Itália",
        "address": {
          "street": "Avenida Ipiranga, 345",
          "city": "São Paulo",
          "state": "SP",
          "zipCode": "01046-000"
        },
        "numberOfFloors": 46,
        "numberOfApartments": 1000
      }' http://localhost:8081/api/room/buildings/1
```

##### Delete
- URI: http://localhost:8081/api/room/buildings/{id}
- Método: DELETE
- Curl: 
```bash
    curl -X DELETE \
  http://localhost:8081/api/room/buildings/1
```

>  Room
- URI: http://localhost:8081/api/room/building/{id}/rooms
- Método: POST
- Curl: 
```bash
   curl -X POST \
  -H "Content-Type: application/json" \
  -d '{
    "number": "101",
    "floor": 1,
    "type": "STANDARD",
    "buildingId": 1,
    "dailyRate": 199.99,
    "beds": {
      "1": "Queen size",
      "2": "Twin size"
    },
    "furniture": {
        "1": "bed",
        "2": "TV"
      },
    "capacity": 4
  }' http://localhost:8081/api/room/building/1/rooms
```

##### FindById
- URI: http://localhost:8081/api/room/rooms/{id}
- Método: GET
- Curl:
```bash
    curl -X GET \
    http://localhost:8081/api/room/rooms/1
```


##### FindAll
- URI: http://localhost:8081/api/room/rooms
- Método: GET
- Curl:
```bash
    curl -X GET \
    http://localhost:8081/api/room/rooms?page=0&size=25
```

##### Update
- URI: http://localhost:8081/api/room/rooms/{id}
- Método: PUT
- Curl:
```bash
    curl -X PUT \
  -H "Content-Type: application/json" \
  -d '{
  "number": "102",
    "floor": 1,
    "type": "DELUXE",
    "buildingId": 1,
    "dailyRate": 200.20,
    "beds": {
      "1": "Queen size",
      "2": "Twin size"
    },
    "furniture": {
        "1": "bed",
        "2": "TV"
      },
    "capacity": 4
  }' http://localhost:8081/api/room/rooms/1
```
##### Delete
- URI: http://localhost:8081/api/room/rooms/{id}
- Método: DELETE
- Curl:
```bash
    curl -X DELETE \
  http://localhost:8081/api/room/rooms/1
```

##### Search
- URI: http://localhost:8081/booking/rooms/search
- Método: POST
- Curl:
```bash
  curl -X POST \
  -H "Content-Type: application/json" \
  -d '{
    "checkIn": "2024-08-01",
    "checkOut": "2024-08-05"
  }' http://localhost:8081/api/booking/rooms/search
```

##### RoomSchedule
- URI: http://localhost:8081/api/room/roomSchedule/reserve
- Método: POST
- Curl:
```bash
    curl -X POST \
  -H "Content-Type: application/json" \
  -d '{
    "roomId": 2,
    "clientId": 2,
    "checkIn": "2024-03-20",
    "checkOut": "2024-03-25"
  }' http://localhost:8081/api/room/roomSchedule/reserve
  ``` 
> Booking

##### Create
- URI: http://localhost:8081/api/booking/booking/start
- Method: POST
CURL:
 ```bash
    curl -X POST \
      -H 'Content-Type: application/json' \
      -d '{
        "clientId": 2,
        "peopleAmount": 2,
        "roomsIds": [1],
        "products": [
          {
            "productId": 1,
            "quantity": 1
          }
        ],
        "checkIn": "2024-03-20",
        "checkOut": "2024-03-25"
      }' http://localhost:8081/api/booking/booking/start
```
##### Search
- URI: http://localhost:8081/api/booking/rooms/search
- Method: POST
  CURL:
 ```bash
    curl --request POST \
     -H 'Content-Type: application/json' \
    --data '{
      "checkIn": "2024-08-01",
      "checkOut": "2024-08-05"
}' http://localhost:8081/api/booking/rooms/search
```
##### FindById
- URI: http://localhost:8081/api/booking/booking/{id}
- Method: GET
  CURL: 
 ```bash
    curl -X GET http://localhost:8081/api/booking/booking/1
```

##### Confirm
- URI: http://localhost:8081/api/booking/booking/{id}/confirm
- Method: POST
- CURL:
 ```bash
    curl -X POST http://localhost:8081/api/booking/booking/1/confirm
```



