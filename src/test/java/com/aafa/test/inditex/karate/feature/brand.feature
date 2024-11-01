Feature: Testing the brand API

  Scenario: Get all brands
    Given url 'http://localhost:8081/brands'
    When method get
    Then status 200
    And match response == [    { "id": 1, "name": "Zara" },    { "id": 2, "name": "H&M" },    { "id": 3, "name": "C&A" },    { "id": 4, "name": "Primark" },    { "id": 5, "name": "Mango" }    ]

  Scenario Outline: Get brand by id
    Given url 'http://localhost:8081/brands/<brandId>'
    When method get
    Then status 200
    And match response == { "id": <brandId>, "name": <brandName> }
    Examples:
      | brandId | brandName |
      | 1       | Zara      |
      | 2       | H&M       |
      | 3       | C&A       |
      | 4       | Primark   |
      | 5       | Mango     |

  Scenario: Get brand by non-existent id
    Given url 'http://localhost:8081/brands/999'
    When method get
    Then status 404
    And match response == { "message": "Brand with id: 999 not found" }