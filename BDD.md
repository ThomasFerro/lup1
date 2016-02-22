# Tables de BDD

## Utilisateurs

| Table | Champs |
| --- | --- |
| role | **rno** *TEXT* |
| credential | **login** *TEXT*, **password** *TEXT* |
| member | **member_id** *INTEGER*, **first_name** *TEXT*, **last_name** *TEXT*, **email** *TEXT*, **birthday** *DATE*, ... |

## Notes

| Table | Champs |
| --- | --- |
| evaluation | **name** *TEXT*, **subject** *TEXT*, **coeff** *FLOAT* |
| subject | **name** *TEXT*, **coeff** *FLOAT* |
| ue | **name** *TEXT*, **semester** *INTEGER*, **coeff** *FLOAT* |
| formation | **name** *TEXT*, **manager** *INTEGER* |
| promotion | **year** *INTEGER*, **formation** *TEXT*, **manager** *INTEGER* |

## Stage

| Table | Champs |
| --- | --- |
| organization | **siret** *TEXT*, **name** *TEXT*, **address** *TEXT*, **phone** *TEXT*, **email** *TEXT*, ... |
| internship | **internship_id** *INTEGER*, **siret** *TEXT*, **title** *TEXT*, **missions** *TEXT*, **description** *TEXT*, **manager** *TEXT*, **duration** *TIMESTAMP* |
| member_internship | **member_id** *INTEGER*, **internship_id** *INTEGER*, **date_debut** *DATE*, **date_fin** *DATE* |
| internship_log | **member_id** *INTEGER*, **internship_id** *INTEGER*, **date** *TIMESTAMP*, **flag** *INTEGER*, **commentary** *TEXT* |
| flag | **flag_id** *INTEGER*, **label** *TEXT* |

## Intervention

| Table | Champs |
| --- | --- |
| intervention | **intervention_id** *INTEGER*, **year** *INTEGER*, **formation** *TEXT*, **intervenant** *INTEGER*, **subject** *TEXT*, **commentary** *TEXT*, **date_intervention** *DATE* |
