Feature: tout client reserve un VTC
  AS a user I want to test google search function

  Background:
    Given Des client existent
      | id  | fisrtName | lastName|
      | abc | Ibrahim   | TAKOU   |
      | def | Paul      | SAA SAA |

    Given des vtc existes
      | id  | fisrtName | lastName|
      | abc | marc      | SOP     |
      | def | Paul      | ELFA    |

  Scenario Outline: solde suffisant
    Given je suis authentifier en tant que "<prenom_client>"
    And le solde de mon compte est de "<solde_avant>" euros TTC avec "<avoir_avant>" euros TTC d'avoir
    When je tente de reserver le VTC "<prenom_vtc>" de "<point_depart>" a "<point_arriver>"
    Then la reservation est effective
    And le solde de mon compte est de "<solde_apres>" euros TTC avec "<avoir_apres>" euros TTC d'avoir
      Examples:
        |  prenom_client| solde_avant| solde_apres | avoir_avant | avoir_apres | prenom_vtc | point_depart                 | point_arriver |
        |  Ibrahim      | 50         | 20          | 5           | 5           | Marc       | 5 rue de Coudray 95100 Paris | 5 rue Clisson 75013 Paris |

  Scenario Outline: solde insuffisant
    Given je suis authentifier en tant que "<prenom_client>"
    And le solde de mon compte est de "<solde_avant>" euros TTC avec "<avoir_avant>" euros TTC d'avoir
    When je tente de reserver le VTC "<prenom_vtc>" de "<point_depart>" a "<point_arriver>"
    Then la reservation n'est pas effective
    And  et une alerte pour solde insufisant
    And le solde de mon compte est de "<solde_apres>" euros TTC avec "<avoir_apres>" euros TTC d'avoir
    Examples:
      |  prenom_client| solde_avant| solde_apres | avoir_avant | avoir_apres | prenom_vtc | point_depart                 | point_arriver |
      |  Ibrahim      | 50         | 20          | 5           | 5           | Marc       | 5 rue de Coudray 95100 Paris | 5 rue Clisson 75013 Paris |

  Scenario Outline: je ne suis pas authentifier
    Given je suis authentifier en tant que "<prenom_client>"
    And le solde de mon compte est de "<solde_avant>" euros TTC avec "<avoir_avant>" euros TTC d'avoir
    When je tente de reserver le VTC "<prenom_vtc>" de "<point_depart>" a "<point_arriver>"
    Then la reservation est effective
    And le solde de mon compte est de "<solde_apres>" euros TTC avec "<avoir_apres>" euros TTC d'avoir
    Examples:
      |  prenom_client| solde_avant| solde_apres | avoir_avant | avoir_apres | prenom_vtc | point_depart                 | point_arriver |
      |  Ibrahim      | 50         | 20          | 5           | 5           | Marc       | 5 rue de Coudray 95100 Paris | 5 rue Clisson 75013 Paris |
