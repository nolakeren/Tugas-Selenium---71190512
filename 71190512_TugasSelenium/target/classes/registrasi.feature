Feature: feature to test register functionality
  Scenario Outline: Tahapan registrasi
    Given browser dibuka
    And user ada di halaman register
    When user memasukan <firstname> <email> <password>
    And tombol register diklik
    Then user diarahkan ke halaman login
    Examples:
      |firstname     |email                | password    |
      | nola         |noker15@gmail.com    | 123456      |
      | nola         |noker15@gmail.com    | 123456ab    |




