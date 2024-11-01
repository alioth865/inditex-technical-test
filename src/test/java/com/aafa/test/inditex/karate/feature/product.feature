Feature: Testing the product API

  Scenario: Get all products
    Given url 'http://localhost:8081/products'
    When method get
    Then status 200
    And match response == [    {    "id": 1,    "name": "Product 1"    },    {    "id": 2,    "name": "Product 2"    },    {    "id": 3,    "name": "Product 3"    },    {    "id": 4,    "name": "Product 4"    },    {    "id": 35455,    "name": "Product 5"    }    ]

  Scenario Outline: Get product by id
    Given url 'http://localhost:8081/products/<productId>'
    When method get
    Then status 200
    And match response == { "id": <productId>, "name": <productName> }
    Examples:
      | productId | productName |
      | 1         | Product 1   |
      | 2         | Product 2   |
      | 3         | Product 3   |
      | 4         | Product 4   |
      | 35455     | Product 5   |

  Scenario: Get product by non-existent id
    Given url 'http://localhost:8081/products/999'
    When method get
    Then status 404
    And match response == { "message": "Product with id: 999 not found" }