Feature: Testing the price API

  Scenario Outline: Get price for brand 1, product 35455 and date 2020-06-14-10.00.00
    Given url 'http://localhost:8081/brand/<brandId>/product/<productId>/price/<dateTime>'
    When method get
    Then status 200
    And match response == { "brandId": <brandId>, "productId": <productId>,  "tariffId": 1, "startDate": "2020-06-14T00:00",  "endDate": "2020-12-31T23:59:59","price": 35.5}
    Examples:
      | brandId | productId | dateTime            |
      | 1       | 35455     | 2020-06-14-10.00.00 |
      | 1       | 35455     | 2020-06-14-16.00.00 |
      | 1       | 35455     | 2020-06-14-21.00.00 |
      | 1       | 35455     | 2020-06-15-10.00.00 |
      | 1       | 35455     | 2020-06-16-21.00.00 |

  Scenario Outline: Price not found for unknown brand or product on specific date
    Given url 'http://localhost:8081/brand/<brandId>/product/<productId>/price/<dateTime>'
    When method get
    Then status 404
    And match response == { "message": "Not found price for product: <productId> and brand: <brandId> and date: <dateTimeResponse>" }
    Examples:
      | brandId | productId | dateTime            | dateTimeResponse |
      | 1       | 99999     | 2020-06-14-10.00.00 | 2020-06-14T10:00 |
      | 2       | 35455     | 2020-06-14-10.00.00 | 2020-06-14T10:00 |
