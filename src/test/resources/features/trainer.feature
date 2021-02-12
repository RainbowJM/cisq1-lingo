Feature: Train lingo
  As a Lingo-player
  I want to guess 5,6,7 letter words
  In order to win lingo during the live show

  Scenario: Start new game
    When I start the game
    Then the word to guess has "5" letters
    And I should see the first letter of the word

  Scenario Outline: Start a new round
    Given I am playing a game
    And the round was won
    And the last word had "<previous length>" letters
    When I start a new round
    Then the word to guess has "<next length>" letters

    Examples:
      | previous length | next length |
      | 5               | 6           |
      | 6               | 7           |
      | 7               | 5           |

    #Failure path
    Given I am playing a game
    And the round was lost
    Then I cannot start a new round

  Scenario Outline: Check the word that was guessed
    Given the word to guess in this round is "<word>"
    When I give my "<guess>"
    Then I will get a "<feedback>"

    Examples:
      | word    | guess |               feedback                      |
      | APPLE   | ADOPT | CORRECT, ABSENT, ABSENT, PRESENT, ABSENT    |
      | APPLE   | ALIBI | ABSENT, ABSENT, ABSENT, ABSENT, ABSENT      |
      | APPLE   | alias | ABSENT, ABSENT, ABSENT, ABSENT, ABSENT      |
      | APPLE   | APPLE | CORRECT, CORRECT, CORRECT, CORRECT, CORRECT |

