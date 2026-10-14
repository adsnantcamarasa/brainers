# brainers
Application de Fiches d'Apprentissage (DevNotes)

## 📝 Présentation
**Création d’une application web de fiches d'apprentissage pour développeur : on y écrit des notes courtes (une notion = une note) et on les retrouve en quelques secondes grâce à une barre de recherche.**

## 💼 Contexte
Projet d'intégration réalisé en alternance. Il sert à prendre en main le socle technique de l'équipe (Spring Boot, Angular, Spartan UI) et ses outils, en livrant une application complète. Le périmètre est volontairement limité à un CRUD sur une seule entité : l'objectif est la qualité d'exécution (architecture, validation, tests, documentation), pas le nombre de fonctionnalités.

## 🎯 Objectifs

| Id | Objectif | Critère de réussite |
| :--- | :--- | :--- |
| **01** | Livrer une application complète | Implémentation des 5 user stories. |
| **02** | Appliquer une architecture propre | **Back-end :**<br>→ Architecture découplée en trois couches strictes (*Controller*, *Service*, *Repository*).<br>→ Aucune entité exposée par l'API, utilisation systématique d'un `NoteDTO`. <br><br>**Front-end :**<br>→ Composants front découplés de l'API par un service Angular dédié. |
| **03** | Garantir la qualité | Back-end et Front-end intégralement couverts par des tests. |

## 🛠️ Stack Technique

| Couche | Technologie |
| :--- | :--- |
| **Backend** | Java, Spring Boot |
| **Frontend** | Angular |
| **Librairie UI** | Spartan UI |
| **Base de données** | H2 Database (In-Memory) |
| **Tests** | JUnit |

## 📐 Périmètre

### Inclus
* CRUD complet sur les notes : créer, consulter, modifier, supprimer.
* Recherche par mot-clé dans le titre ou le contenu.
* Une seule table en BDD : `Note`.

### Exclus

| Exclusion | Raison |
| :--- | :--- |
| Tags, Catégories, dossiers | Choix d'une seule entité pour tenir rigoureusement le délai de 3 semaines. |
| Comptes utilisateurs, authentification | Application locale et mono-utilisateur pour le MVP. |
| Mise en forme avancée (Markdown, coloration, images) | Contenu limité à du texte brut. |
| Pagination, recherche plein texte complexe | Volume de données estimé faible. |
| Historique des modifications, corbeille | Suppression définitive immédiatement assumée. |
| Responsive mobile, internationalisation | Cible écran desktop uniquement, interface exclusive en français. |

## 👥 User Stories (US)

| Id | User story | Priorité |
| :--- | :--- | :--- |
| **US-01** | Créer une note | **MUST** |
| **US-02** | Rechercher une note | **MUST** |
| **US-03** | Consulter une note | **MUST** |
| **US-04** | Modifier une note | **MUST** |
| **US-05** | Supprimer une note | **MUST** |

---

### US-01 - Créer une note
*En tant qu’utilisateur, je veux créer une note (titre + contenu) afin de garder trace d’une notion apprise.*

* **Critère 1 :** Quand je saisis un titre et un contenu valide puis clique sur “Enregistrer”, alors la note est enregistrée et j'arrive sur sa page de détail.
* **Critère 2 :** Quand je quitte un champ qui ne respecte pas les règles **RG1** ou **RG2**, alors le message correspondant s’affiche sous ce champ ; le bouton "Enregistrer" reste désactivé tant que le formulaire est invalide.
* **Critère 3 :** Quand je clique sur ”Annuler”, alors je reviens à l’accueil sans rien enregistrer.

### US-02 - Rechercher une note
*En tant qu’utilisateur, je veux rechercher rapidement dans mes notes afin de retrouver promptement une notion.*

* **Critère 1 :** Quand je tape un mot-clé (ex: *« stream »*), alors la liste se met à jour automatiquement après ma dernière frappe (sans bouton de validation), et ne contient que les notes correspondantes (**RG6**).
* **Critère 2 :** Quand aucune note ne correspond, alors le message *« Aucune note trouvée. »* s'affiche.
* **Critère 3 :** Quand je clique sur une note, alors j’arrive sur sa page de détail.

### US-03 - Consulter une note
*En tant qu’utilisateur, je veux afficher une note en entier afin de relire la notion.*

* **Critère 1 :** Quand j'ouvre une note, alors je vois son titre, son contenu, sa date de création et sa date de dernière modification.
* **Critère 2 :** Quand la note demandée n’existe pas, alors la page affiche le message *“Note introuvable”*.

### US-04 - Modifier une note
*En tant qu’utilisateur, je veux modifier une note afin de la corriger ou de la compléter.*

* **Critère 1 :** Quand je clique sur « Modifier » depuis l'écran de détail, alors le formulaire s'ouvre pré-rempli avec le titre et le contenu actuels de la note.
* **Critère 2 :** Quand j'enregistre des valeurs valides, alors la note est mise à jour en base de données, sa date de modification change (sa date de création reste identique) et je bascule à nouveau sur l'écran de détail.
* **Critère 3 :** Le comportement des validations de formulaires est identique à l'**US-01** ; l'action « Annuler » ramène au détail initial sans appliquer de modification.

### US-05 - Supprimer une note
*En tant qu’utilisateur, je veux supprimer une note devenue inutile.*

* **Critère 1 :** Quand je clique sur “Supprimer”, alors une fenêtre de confirmation de l’action m’est demandée.
* **Critère 2 :** Quand je confirme, alors la note est purgée définitivement du système et je suis redirigé vers la page de Recherche, où elle n'apparaît plus.
* **Critère 3 :** Quand j'annule la confirmation, alors l'état de la note reste inchangé.

## 📋 Règles de Gestion (RG)

| ID | Règle |
| :--- | :--- |
| **RG1** | Le titre est obligatoire, comporte au maximum **100 caractères** et ne peut pas être composé d’espaces uniquement. |
| **RG2** | Le contenu est obligatoire, comporte au maximum **10 000 caractères** et ne peut pas être composé uniquement d'espaces. |
| **RG3** | L'identifiant technique et les dates associées sont attribués de manière unique par le serveur ; le client ne peut en aucun cas les forcer. |
| **RG4** | À la création, la date de modification est initialisée à une valeur égale à la date de création. Ensuite, seule la date de modification est rafraîchie à chaque mise à jour. |
| **RG5** | L'unicité des titres n'est pas requise : plusieurs notes distinctes peuvent porter le même titre. |
| **RG6** | La recherche filtre les notes dont le titre ou le contenu contient la chaîne de caractères saisie. |
| **RG7** | Par défaut, les résultats de recherche sont renvoyés triés par date de modification, du plus récent au plus ancien. |
| **RG8** | Toute suppression validée par l'utilisateur est définitive et irréversible. |

## 🗺️ Routes Applicatives

| Route | Écran associé | Composant Angular |
| :--- | :--- | :--- |
| `/` | Accueil | `HomeComponent` |
| `/notes` | Recherche / Liste | `NoteSearchComponent` |
| `/notes/new` | Création | `FormComponent` *(ou `NoteFormComponent`)* |
| `/notes/:id` | Détail | `NoteDetailComponent` |

## 🧪 Tests
* [ ] **Back-end :** Tests unitaires des services et contrôleurs (JUnit / Mockito) - *En cours*
* [ ] **Front-end :** Tests des composants et services Angular - *En cours*
