[![Build Status](https://travis-ci.org/cs-joao-felipe/correios-scraper.svg?branch=master)](https://travis-ci.org/cs-joao-felipe/correios-scraper)
[![codecov.io](https://codecov.io/github/cs-joao-felipe/correios-scraper/coverage.svg?branch=master)](https://codecov.io/github/cs-joao-felipe/correios-scraper?branch=master)
[![Codacy Badge](https://api.codacy.com/project/badge/grade/6aab9d1734054ff6a0c7dc0ceb2c6dfc)](https://www.codacy.com/app/jfelipesp/correios-scraper)

## Coverage History
![codecov.io](https://codecov.io/github/cs-joao-felipe/correios-scraper/branch.svg?branch=master)

# correios-scraper
Scraper to retrieve Brazilian Postal Service object tracking and Postal Code information to a JSON output.

# GET /cep?postalcode=20241320
+ Response 200 (application/json;charset=UTF-8)

    + Body
```javascript
    {  
        "street":"Rua Joaquim Murtinho ",
        "district":"Santa Teresa ",
        "city":"Rio de Janeiro",
        "state":"RJ",
        "postalCode":"20241320"
    }
```

# GET /sro?trackingNumber=SW864122406BR

+ Response 200 (application/json;charset=UTF-8)

    + Body
```javascript
    {
      "tracking": [
        {
          "date": "2017-06-16 17:25",
          "location": "SAO PAULO / SP",
          "action": "Objeto postado"
        },
        {
          "date": "2017-06-16 17:49",
          "location": " SAO PAULO / SP",
          "action": "Objeto encaminhado de Agência dos Correios em SAO PAULO / SP para Unidade Operacional em SAO PAULO / SP"
        },
        {
          "date": "2017-06-17 06:35",
          "location": " SAO PAULO / SP",
          "action": "Objeto encaminhado de Unidade Operacional em SAO PAULO / SP para Unidade Operacional em SAO PAULO / SP"
        },
        {
          "date": "2017-06-17 06:56",
          "location": " SAO PAULO / SP",
          "action": "Objeto encaminhado de Unidade Operacional em SAO PAULO / SP para Unidade de Distribuição em SAO PAULO / SP"
        },
        {
          "date": "2017-06-19 13:10",
          "location": "SAO PAULO / SP",
          "action": "Objeto saiu para entrega ao destinatário"
        }
      ],
      "trackingNumber": "SW864122406BR"
    }
```
