# 🏢 Leave Management System — Java EE / JSP / Servlets & DAO Pattern

> Application d'entreprise complète pour la gestion et la dématérialisation des congés des employés, avec **workflow d'approbation hiérarchique à 2 niveaux (Chef de Service N+1 et RH N+2)**, **génération d'attestations PDF (iText)** et **notifications par email (JavaMail)**.

![Java](https://img.shields.io/badge/Java-EE_/_Servlets_3.1-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![JSP](https://img.shields.io/badge/Frontend-JSP_/_JSTL-1572B6?style=for-the-badge)
![DAO Pattern](https://img.shields.io/badge/Architecture-MVC_&_DAO_Pattern-59666C?style=for-the-badge)
![MySQL](https://img.shields.io/badge/Database-MySQL_/_JDBC-4479A1?style=for-the-badge&logo=mysql&logoColor=white)
![iText PDF](https://img.shields.io/badge/Reporting-iText_PDF_5.5-E01E5A?style=for-the-badge)
![JavaMail](https://img.shields.io/badge/Email-JavaMail_API_1.6-EA4335?style=for-the-badge&logo=gmail&logoColor=white)

---

## 📌 Présentation du Projet

Cette plateforme permet de dématérialiser l'intégralité du processus de demande de congé au sein d'une organisation :

- 👤 **Espace Employé** : Connexion via numéro PPR, consultation du solde de congés restants et soumission de nouvelles demandes.
- 👔 **Validation Niveau 1 (Chef de Service / N+1)** : Tableau de bord dédié pour examiner et approuver/refuser les demandes de son département.
- 🏢 **Validation Finale Niveau 2 (Responsable RH / N+2)** : Validation définitive, décompte automatique du solde de jours, gestion des collaborateurs et export des attestations.
- 📄 **Génération PDF Automatique** : Production à la volée d'attestations de congé officielles via la librairie **iText PDF**.
- ✉️ **Système d'Alertes Email** : Envoi de notifications SMTP automatiques aux employés à chaque étape de validation.

---

## 🏗️ Architecture Technique & Structure du Code

Le projet est structuré selon le patron de conception **MVC (Modèle-Vue-Contrôleur)** et le **DAO Pattern** pour isoler la couche d'accès aux données :

```
conge_main/
├── java/
│   ├── DB/
│   │   └── DBUtil.java                     # Gestionnaire Singleton de connexion JDBC MySQL
│   ├── model/                              # POJOs / Modèles métiers
│   │   ├── Employee.java                   # Entité Employé (PPR, Nom/Prénom Arabe, Grade, Solde)
│   │   └── DemandeConge.java               # Entité Demande (DateDébut, Durée, DateFin, État)
│   ├── interfaces/                         # Contrats d'accès aux données
│   │   ├── EmployeeDAO.java
│   │   ├── DemandeCongeDAO.java
│   │   └── login.java
│   ├── Impl/                               # Implémentations concrètes DAO (Requêtes SQL/JDBC)
│   │   ├── EmployeeDAOImpl.java
│   │   └── DemandeCongeDAOImpl.java
│   ├── servlets/                           # Contrôleurs HTTP (Servlets @WebServlet)
│   │   ├── LoginServlet.java               # Authentification Employé
│   │   ├── Admin1LoginServlet.java         # Authentification Chef de Service (N+1)
│   │   ├── Admin2LoginServlet.java         # Authentification Direction RH (N+2)
│   │   ├── EnregistrerDemandeCongeServlet.java # Création de demande de congé
│   │   ├── AccepterDemandeServlet.java     # Validation Niveau 1 (Chef)
│   │   ├── RefuserDemandeServlet.java      # Rejet Niveau 1
│   │   ├── AccepterDemandeServlet2.java    # Validation Niveau 2 (RH)
│   │   ├── RefuserDemandeServlet2.java     # Rejet Niveau 2
│   │   ├── DashboardServlet.java           # Dashboard Manager N+1
│   │   ├── DashboardServlet2.java          # Dashboard RH N+2
│   │   ├── AjouterEmployeServlet.java      # Enregistrement d'un collaborateur
│   │   ├── AfficherDemandesServlet.java    # Consultation des listes
│   │   └── PDFGenerationServlet.java       # Génération d'attestation PDF (iText)
│   └── controller/
│       └── EmailUtil.java                  # Service d'envoi SMTP (JavaMail)
│
└── webapp/                                 # Vues & Interface Web (JSP / CSS / Assets)
    ├── login.jsp                           # Mire de connexion Employé
    ├── login_chef.jsp                      # Mire de connexion Chef de Service
    ├── login_RH.jsp                        # Mire de connexion Responsable RH
    ├── welcome.jsp                         # Accueil employé & formulaire de demande
    ├── dashboard.jsp                       # Tableau de bord validation Chef N+1
    ├── dashboard2.jsp                      # Tableau de bord validation RH N+2
    ├── afficher_demandes.jsp               # Liste des demandes en cours
    ├── NewEmp.jsp                          # Formulaire d'ajout d'employé
    ├── downloadPDF.jsp                     # Interface de téléchargement PDF
    ├── confirmation.jsp / success.jsp      # Pages de confirmation
    └── WEB-INF/
        ├── web.xml                         # Descripteur de déploiement Servlet 3.1
        └── lib/
            ├── mysql-connector-j-8.2.0.jar # Driver JDBC MySQL
            ├── itextpdf-5.5.9.jar          # Moteur de génération PDF
            └── javax.mail-1.6.2.jar        # API JavaMail
```

---

## 🔄 Workflow de Validation Hiérarchique

```
┌──────────────┐
│   Employé    │──(Soumission de demande)──┐
└──────────────┘                           │
                                           ▼
┌──────────────────────────────────────────────────────┐
│  Niveau 1 : Chef de Service (Admin1LoginServlet)     │
│  ➜ Statut : "En attente de validation Chef"         │
└──────────────────────────┬───────────────────────────┘
                           │
             [ Approuvé par le Chef ]
                           │
                           ▼
┌──────────────────────────────────────────────────────┐
│  Niveau 2 : Responsable RH (Admin2LoginServlet)      │
│  ➜ Statut : "En attente de validation RH"           │
└──────────────────────────┬───────────────────────────┘
                           │
              [ Validation Finale RH ]
                           │
              ┌────────────┴────────────┐
              ▼                         ▼
   ┌────────────────────┐    ┌────────────────────┐
   │  Notification Mail │    │   Attestation PDF  │
   │   (EmailUtil.java) │    │  (iText Engine)    │
   └────────────────────┘    └────────────────────┘
```

---

## 🗄️ Modèle de Données (Base MySQL `conge`)

- **Table `employee`** :
  - `numeroPPR` (Clé primaire / Identifiant employé)
  - `nomArabe`, `prenomArabe`
  - `imputationBudgetaire`
  - `grade`
  - `congeJours` (Solde annuel disponible)
- **Table `demande_conge`** :
  - `id` (Clé primaire auto-incrémentée)
  - `numeroPPR` (Clé étrangère vers `employee`)
  - `dateDebut`, `duree`, `dateFin`
  - `etat` (*En attente N+1* / *En attente N+2* / *Acceptée* / *Refusée*)

---

## ⚙️ Déploiement & Installation

### Prérequis
- **Java JDK 8 ou supérieur**
- **Serveur d'application Apache Tomcat 8.5 / 9 / 10**
- **MySQL Server 5.7 / 8.0+**

### 1. Configuration de la base de données
Créer la base MySQL :
```sql
CREATE DATABASE conge;
```

Ajuster les identifiants de connexion dans `java/DB/DBUtil.java` si nécessaire :
```java
private static final String URL = "jdbc:mysql://localhost:3306/conge";
private static final String USER = "root";
private static final String PASSWORD = "votre_mot_de_passe";
```

### 2. Déploiement sur Apache Tomcat
1. Importer le projet dans **Eclipse IDE for Enterprise Java** ou **IntelliJ IDEA Ultimate**.
2. Configurer le serveur **Apache Tomcat** et ajouter le projet au runtime.
3. Déployer et accéder à l'application via : `http://localhost:8080/conge_main/login.jsp`

---

## 👤 Auteur
**Salah Eddine Abouelkemhe** — Développeur Full-Stack & Spécialiste Java
