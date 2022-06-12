Feature: feature to test login functionality
  Scenario: periksa login dengan akun yang benar
    Given browser open
    And user berada di halaman login
    When user memasukan <email> dan <password>
    And tombol login ditekan
    Then user redirect ke home page
    Examples:
      |email                | password    |
      |                     |             |


