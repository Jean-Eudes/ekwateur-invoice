# Kata autour d'un sujet sur la facturetion

## Enoncé de l'exercice
Le product owner te demande de developper un programme qui permet de calculer le montant à facturer à un client d'Ekwateur pour
un mois calendaire.

Ce programme devra gérer 2 types de clients :

A) Les clients Pro, qui ont les propriétés suivantes :
- Reference Client (EKW + 8 caractères numériques)
- N° SIRET
- Raison Sociale
- CA

B) Les particuliers, qui ont les propriétés suivantes :
- Reference Client (EKW + 8 caractères numériques)
- Civilité
- Nom
- Prénom

Un client peut consommer deux types d'énergies :
- Electricité
- Gaz

Chaque énergie est facturée au kWh.
- Pour les particuliers, le prix du kWh est de 0,121 € pour l'électricité et 0,115€ pour le gaz
- Pour les pro, ayant un CA supérieur à 1 000 000 €, le prix du kWh est de 0,114 € pour l'électricité et 0,111€ pour le gaz
- Pour les pro, ayant un CA inférieur à 1 000 000 €, le prix du kWh est de 0,118 € pour l'électricité et 0,113€ pour le gaz

## Contraintes techniques
La seule contrainte technique est l'utilisation du langage Java dans sa version 8 au minimum.

## Ce qui est attendu
Le minimum attendu est un programme fonctionnel qui puisse etre executé et testé.

## Comment soumettre ton travail
- Créer un nouveau repository public sur un espace à toi (et non un fork de ce repo sur l'espace gitlab Ekwateur)
- Rendre le projet sur la branche master de ton repository
- Enfin, nous communiquer par mail le lien de ton repository quand l'exercice est terminé

## Build du projet

Lancement des tests
```bash
./gradlew check
```

Build du projet
```bash
./gradlew build
```

Lancement du projet avec gradle
```bash
./gradlew run --args="business EKW12345678 802954785 arcus 10000000 12.4 2134.90"
./gradlew run --args="private EKW12345678 MR jean durand 12.4 2134.90"
```
Lancement du projet avec un client java (nécessite java 21)
```bash
./gradlew build
unzip app/build/distributions/app.zip -d /tmp/invoice
JAVA_OPTS="--enable-preview" app/bin/app business EKW12345678 802954785 arcus 10000000 12.4 2134.9
```

## Quelques choix métiers à challenger par le PO
  - la devise est toujours l'euro, on ne gere pas la notion de devise pour le moment
  - le chiffre d'affaire est toujours un entier (pas de nombre decimaux)
  - un CA à 1 000 000 d'euros compte dans les petites sociétés.
  - l'affichage du montant de la facture avec deux chiffres apres la virgule se fait dans le partie edition.