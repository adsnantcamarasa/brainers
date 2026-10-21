package com.brainers.demo.initialDataDemo;

import com.brainers.demo.model.domain.Note;
import com.brainers.demo.repository.NoteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {
    private final NoteRepository noteRepository;

    public DataLoader(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

@Override
    public void run(String... args) throws Exception {
        // Vos 4 notes initiales
        this.noteRepository.save(new Note("Stream java", "LEs streams en java servent à traiter des collections d'objets de manière fonctionnelle."));
        this.noteRepository.save(new Note("Spring boot framework", "Spring Boot est un framework open source basé sur le framework Spring, qui simplifie le développement d'applications Java en fournissant une configuration automatique"));
        this.noteRepository.save(new Note("Les lambda en java", "Une expression lambda est une fonction anonyme qui peut être utilisée pour créer des instances de classes fonctionnelles en Java."));
        this.noteRepository.save(new Note("Qu'est ce qu'un statut http", "Les codes de statut HTTP sont des codes numériques renvoyés par un serveur web pour indiquer le résultat d'une requête HTTP. Ils sont utilisés pour informer le client (navigateur ou application) de l'état de la demande et de la réponse du serveur."));
        
        // 16 nouvelles notes pour atteindre un total de 20
        this.noteRepository.save(new Note("Optional Java", "La classe Optional introduite en Java 8 permet de réduire les risques de NullPointerException en encapsulant des valeurs potentiellement nulles."));
        this.noteRepository.save(new Note("Inversion de Contrôle", "L'IoC (Inversion of Control) délègue la création et la gestion du cycle de vie des objets au framework Spring plutôt qu'à l'application."));
        this.noteRepository.save(new Note("Injection de dépendances", "L'injection de dépendances (DI) consiste à fournir automatiquement les objets requis par une classe, souvent via le constructeur avec Spring."));
        this.noteRepository.save(new Note("Spring Data JPA", "Un module Spring qui simplifie grandement l'accès aux bases de données en générant automatiquement les requêtes SQL à partir d'interfaces Repository."));
        this.noteRepository.save(new Note("Hibernate", "Un framework de mapping objet-relationnel (ORM) pour Java permettant de lier les classes de l'application aux tables d'une base de données."));
        this.noteRepository.save(new Note("Base de données H2", "Un système de gestion de base de données relationnelle écrit en Java, très rapide, souvent utilisé en mémoire pour les phases de test."));
        this.noteRepository.save(new Note("Maven", "Un outil de gestion et d'automatisation de production de projets logiciels Java basé sur le fichier de configuration centralisé pom.xml."));
        this.noteRepository.save(new Note("Architecture REST", "Un style d'architecture logicielle définissant un ensemble de contraintes à utiliser pour créer des services web exploitant pleinement les méthodes HTTP."));
        this.noteRepository.save(new Note("Méthode GET", "Une méthode HTTP utilisée exclusivement pour récupérer des données à partir d'un serveur, sans altérer l'état de la ressource visée."));
        this.noteRepository.save(new Note("Méthode POST", "Une méthode HTTP utilisée pour envoyer des données au serveur afin de créer une nouvelle ressource (comme l'enregistrement d'une entité)."));
        this.noteRepository.save(new Note("Annotation @RestController", "Annotation Spring combinant @Controller et @ResponseBody, indiquant que la classe gère des requêtes web et retourne directement du JSON."));
        this.noteRepository.save(new Note("Garbage Collector", "Le mécanisme de gestion automatique de la mémoire en Java qui détruit les objets du tas (Heap) qui ne sont plus référencés."));
        this.noteRepository.save(new Note("Java Virtual Machine", "La JVM est l'environnement d'exécution qui interprète et exécute le bytecode Java sur n'importe quel système d'exploitation compatible."));
        this.noteRepository.save(new Note("Records en Java", "Introduits de façon permanente en Java 16, les records sont des classes immuables spéciales conçues pour encapsuler des porteurs de données purs."));
        this.noteRepository.save(new Note("Exception Handling", "Le mécanisme permettant de gérer les erreurs d'exécution avec les blocs try-catch, ou globalement dans Spring avec @RestControllerAdvice."));
        this.noteRepository.save(new Note("Git et GitHub", "Git est un système de contrôle de version décentralisé, tandis que GitHub est une plateforme d'hébergement cloud pour ces dépôts de code."));
    }
}