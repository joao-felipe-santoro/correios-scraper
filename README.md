[![Build Status](https://travis-ci.org/jfelipesp/correios-scraper.svg?branch=master)](https://travis-ci.org/jfelipesp/correios-scraper)
[![codecov.io](https://codecov.io/github/jfelipesp/correios-scraper/coverage.svg?branch=master)](https://codecov.io/github/jfelipesp/correios-scraper?branch=master)

# correios-scraper
Scraper to retrieve Brazilian Postal Service object tracking and Postal Code information to a JSON output.







# GET /cep?postalcode=20241320
+ Response 200 (application/json;charset=UTF-8)

    + Headers

            Transfer-Encoding: chunked

    + Body

            {"street":"Rua Joaquim Murtinho ","district":"Santa Teresa ","city":"Rio de Janeiro","state":"RJ","postalCode":"20241320"}


# GET /sro?trackingNumber=DU156647456BR

+ Response 200 (application/json;charset=UTF-8)

    + Headers

            Transfer-Encoding: chunked

    + Body

            {"tracking":[{"date":1454093280000,"location":"AGF SENADOR VERGUEIRO - Sao Bernardo Do Campo/SP","action":"Postado"},{"date":1454096940000,"location":"AGF SENADOR VERGUEIRO - Sao Bernardo Do Campo/SP","action":"Encaminhado","detail":"Em trânsito para CTE JAGUARE - Sao Paulo/SP"},{"date":1454144160000,"location":"CTE SAUDE - Sao Paulo/SP","action":"Encaminhado","detail":"Encaminhado para CEE MOEMA - Sao Paulo/SP"},{"date":1454144160000,"location":"CTE JAGUARE - Sao Paulo/SP","action":"Encaminhado","detail":"Encaminhado para CTE SAUDE - Sao Paulo/SP"},{"date":1454350020000,"location":"Sao Paulo/SP","action":"Saiu para entrega ao destinatário"},{"date":1454359020000,"location":"CEE MOEMA - Sao Paulo/SP","action":"Entrega Efetuada"}],"trackingNumber":"DU156647456BR","links":[]}

