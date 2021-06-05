package model.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Init {

    public static void creationTables() {
        Connection connection = DatabaseConnection.getInstance();
        Statement statement;
        String sql;
        try {
            statement = connection.createStatement();

            // Années scolaires, organisations, sessions, périodes

            sql = "CREATE TABLE IF NOT EXISTS AnneesScolaires (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "debut DATE," +
                    "fin DATE" +
                    ")";
            statement.execute(sql);

            sql = "CREATE TABLE IF NOT EXISTS Organisations (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "nom VARCHAR(100) NOT NULL," +
                    "code VARCHAR(100)," +
                    "nbSessions INTEGER NOT NULL" +
                    ")";
            statement.execute(sql);

            sql = "CREATE TABLE IF NOT EXISTS Sessions (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "nom VARCHAR(100) NOT NULL," +
                    "code VARCHAR(100)," +
                    "organisationId INTEGER NOT NULL," +
                    "sessionId INTEGER NOT NULL," +
                    "nbPeriodes INTEGER NOT NULL," +
                    "FOREIGN KEY (organisationId) REFERENCES Organisations(id) " +
                    ")";
            statement.execute(sql);

            sql = "CREATE TABLE IF NOT EXISTS Periodes (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "nom VARCHAR(100) NOT NULL," +
                    "code VARCHAR(100)," +
                    "sessionId INTEGER NOT NULL," +
                    "periodeId INTEGER NOT NULL," +
                    "FOREIGN KEY (sessionId) REFERENCES Sessions(id) " +
                    ")";
            statement.execute(sql);

            // Classes, Sections, Niveaux

            sql = "CREATE TABLE IF NOT EXISTS Niveaux (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "nom VARCHAR(100) NOT NULL," +
                    "code VARCHAR(100)" +
                    ")";
            statement.execute(sql);

            sql = "CREATE TABLE IF NOT EXISTS Sections (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "nom VARCHAR(100) NOT NULL," +
                    "code VARCHAR(100)," +
                    "niveauId INTEGER," +
                    "FOREIGN KEY (niveauId) REFERENCES Niveaux(id) " +
                    ")";
            statement.execute(sql);

            sql = "CREATE TABLE IF NOT EXISTS Classes (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "nom VARCHAR(100) NOT NULL," +
                    "numero INTEGER," +
                    "label VARCHAR(20)," +
                    "code VARCHAR(100)," +
                    "sectionId INTEGER," +
                    "organisationId INTEGER," +
                    "FOREIGN KEY (sectionId) REFERENCES Sections(id)," +
                    "FOREIGN KEY (organisationId) REFERENCES Organisations(id)" +
                    ")";
            statement.execute(sql);

            // cours et unités d'enseignements

            sql = "CREATE TABLE IF NOT EXISTS Cours (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "noms VARCHAR(100) NOT NULL," +
                    "code VARCHAR(100) NOT NULL" +
                    ")";
            statement.execute(sql);

            sql = "CREATE TABLE IF NOT EXISTS UnitesEnseignements (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "noms VARCHAR(100) NOT NULL," +
                    "code VARCHAR(100) NOT NULL" +
                    ")";
            statement.execute(sql);

            // Adresses et informations personnelles sur les personnes

            sql = "CREATE TABLE IF NOT EXISTS Adresses (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "pays VARCHAR(100)," +
                    "ville VARCHAR(100)," +
                    "codepostal VARCHAR(100)," +
                    "adresse VARCHAR(100)," +
                    "complement VARCHAR(100)" +
                    ")";
            statement.execute(sql);

            // Elèves

            sql = "CREATE TABLE IF NOT EXISTS Eleves (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "code VARCHAR(100)," +
                    "nom VARCHAR(100) NOT NULL," +
                    "deuxiemeNom VARCHAR(100)," +
                    "prenom VARCHAR(100) NOT NULL," +
                    // TODO : remplacer par un booléen
                    "sexe INTEGER NOT NULL," +
                    "dateNaissance DATE," +
                    "paysNaissance VARCHAR(100)," +
                    "villeNaissance VARCHAR(100)," +
                    "telephone VARCHAR(20)," +
                    "email VARCHAR(255)," +
                    "adresseId INTEGER," +
                    // TODO : ajouter la colonne photo
                    "FOREIGN KEY (adresseId) REFERENCES Adresses(id) " +
                    ")";
            statement.execute(sql);

            // Elèves et année scolaire
            sql = "CREATE TABLE IF NOT EXISTS ElevesAnneesScolaires (" +
                    "eleveId INTEGER NOT NULL," +
                    "anneeScolaireId INTEGER NOT NULL," +
                    "classeId INTEGER" +
                    "PRIMARY KEY (eleveId, anneeScolaireId)," +
                    "FOREIGN KEY (eleveId) REFERENCES Eleves(id)," +
                    "FOREIGN KEY (anneeScolaireId) REFERENCES AnneesScolaires(id) " +
                    "FOREIGN KEY (classeId) REFERENCES Classes(Id)" +
                    ")";
            statement.execute(sql);


            // table des tuteurs
            // TODO : ajouter la colonne photo
            sql = "CREATE TABLE IF NOT EXISTS Tuteurs (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "noms VARCHAR(100) NOT NULL," +
                    "prenom VARCHAR(100) NOT NULL," +
                    "sexe INTEGER NOT NULL," +
                    "dateNaissance DATE," +
                    "telephone VARCHAR(20)," +
                    "email VARCHAR(255)," +
                    "adresse TEXT," +
                    "profession VARCHAR(255)" +
                    ")";
            statement.execute(sql);

            // table association tuteurs - élèves
            sql = "CREATE TABLE IF NOT EXISTS TuteursEleves (" +
                    "tuteurId INTEGER NOT NULL," +
                    "eleveId INTEGER NOT NULL," +
                    "PRIMARY KEY (tuteurId, eleveId)," +
                    "FOREIGN KEY (tuteurId) REFERENCES Tuteurs(Id)," +
                    "FOREIGN KEY (eleveId) REFERENCES Eleves(Id)" +
                    ")";
            statement.execute(sql);


            // table association cours - élèves
            sql = "CREATE TABLE IF NOT EXISTS CoursEleves (" +
                    "coursId INTEGER NOT NULL," +
                    "eleveId INTEGER NOT NULL," +
                    "PRIMARY KEY (coursId, eleveId)," +
                    "FOREIGN KEY (coursId) REFERENCES Cours(Id)," +
                    "FOREIGN KEY (eleveId) REFERENCES Eleves(Id)" +
                    ")";
            statement.execute(sql);

            // GESTION DU PERSONNEL

            sql = "CREATE TABLE IF NOT EXISTS Postes (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "nom VARCHAR(100) NOT NULL," +
                    "code VARCHAR(100)" +
                    ")";
            statement.execute(sql);

            // TODO : ajouter la colonne photo
            sql = "CREATE TABLE IF NOT EXISTS Personnels (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "code VARCHAR(100)," +
                    "nom VARCHAR(100) NOT NULL," +
                    "deuxiemeNom VARCHAR(100)," +
                    "prenom VARCHAR(100) NOT NULL," +
                    "sexe INTEGER NOT NULL," +
                    "dateNaissance DATE," +
                    "paysNaissance VARCHAR(100)," +
                    "villeNaissance VARCHAR(100)," +
                    "telephone VARCHAR(20)," +
                    "email VARCHAR(255)," +
                    "adresseId INTEGER," +
                    "FOREIGN KEY (adresseId) REFERENCES Adresses(id) " +
                    ")";
            statement.execute(sql);

            // association personnel-poste
            sql = "CREATE TABLE IF NOT EXISTS PersonnelsPostes (" +
                    "personnelId INTEGER NOT NULL," +
                    "posteId INTEGER NOT NULL," +
                    "PRIMARY KEY (personnelId, posteId)," +
                    "FOREIGN KEY (personnelId) REFERENCES Personnels(id) " +
                    "FOREIGN KEY (posteId) REFERENCES Postes(id)" +
                    ")";
            statement.execute(sql);

            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        creationTables();
    }
}
