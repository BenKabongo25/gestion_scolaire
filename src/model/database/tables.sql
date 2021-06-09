CREATE TABLE IF NOT EXISTS AnneesScolaires (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    debut DATE,
    fin DATE
);

CREATE TABLE IF NOT EXISTS Organisations (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nom VARCHAR(100) NOT NULL,
    code VARCHAR(100),
    nbSessions INTEGER NOT NULL
);

CREATE TABLE IF NOT EXISTS Sessions (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nom VARCHAR(100) NOT NULL,
    code VARCHAR(100),
    organisationId INTEGER NOT NULL,
    sessionId INTEGER NOT NULL,
    nbPeriodes INTEGER NOT NULL,
    FOREIGN KEY (organisationId) REFERENCES Organisations(id)
);

CREATE TABLE IF NOT EXISTS Periodes (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nom VARCHAR(100) NOT NULL,
    code VARCHAR(100),
    sessionId INTEGER NOT NULL,
    periodeId INTEGER NOT NULL,
    FOREIGN KEY (sessionId) REFERENCES Sessions(id)
);

CREATE TABLE IF NOT EXISTS Niveaux (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nom VARCHAR(100) NOT NULL,
    code VARCHAR(100)
    );

CREATE TABLE IF NOT EXISTS Sections (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nom VARCHAR(100) NOT NULL,
    code VARCHAR(100),
    niveauId INTEGER,
    FOREIGN KEY (niveauId) REFERENCES Niveaux(id)
);

CREATE TABLE IF NOT EXISTS Classes (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nom VARCHAR(100) NOT NULL,
    numero INTEGER,
    label VARCHAR(20),
    code VARCHAR(100),
    sectionId INTEGER,
    organisationId INTEGER,
    FOREIGN KEY (sectionId) REFERENCES Sections(id),
    FOREIGN KEY (organisationId) REFERENCES Organisations(id)
);

CREATE TABLE IF NOT EXISTS Cours (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    noms VARCHAR(100) NOT NULL,
    code VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS UnitesEnseignements (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    noms VARCHAR(100) NOT NULL,
    code VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS CoursUnitesEnseignements (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    coursId INTEGER NOT NULL,
    uniteEnseignementId INTEGER NOT NULL,
    FOREIGN KEY (coursId) REFERENCES Cours(Id),
    FOREIGN KEY (uniteEnseignementId) REFERENCES UnitesEnseignements(Id)
);

CREATE TABLE IF NOT EXISTS Adresses (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    pays VARCHAR(100),
    ville VARCHAR(100),
    codepostal VARCHAR(100),
    adresse VARCHAR(100),
    complement VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS Eleves (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    code VARCHAR(100),
    nom VARCHAR(100) NOT NULL,
    deuxiemeNom VARCHAR(100),
    prenom VARCHAR(100) NOT NULL,
    sexe INTEGER NOT NULL,
    dateNaissance DATE,
    paysNaissance VARCHAR(100),
    villeNaissance VARCHAR(100),
    telephone VARCHAR(20),
    email VARCHAR(255),
    adresseId INTEGER,
    FOREIGN KEY (adresseId) REFERENCES Adresses(id)
);

CREATE TABLE IF NOT EXISTS ElevesAnneesScolaires (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    eleveId INTEGER NOT NULL,
    anneeScolaireId INTEGER NOT NULL,
    classeId INTEGER,
    FOREIGN KEY (eleveId) REFERENCES Eleves(id),
    FOREIGN KEY (anneeScolaireId) REFERENCES AnneesScolaires(id),
    FOREIGN KEY (classeId) REFERENCES Classes(Id)
);

CREATE TABLE IF NOT EXISTS ElevesAnneesScolairesUnitesEnseignements (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    eleveAnneeScolaireId INTEGER NOT NULL,
    uniteEnseignementId INTEGER NOT NULL,
    FOREIGN KEY (eleveAnneeScolaireId) REFERENCES ElevesAnneesScolaires(id),
    FOREIGN KEY (uniteEnseignementId) REFERENCES UnitesEnseignements(Id)
);

CREATE TABLE IF NOT EXISTS PeriodesUnitesEnseignementsClasses (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    periodeId INTEGER NOT NULL,
    uniteEnseignementId INTEGER NOT NULL,
    classeId INTEGER NOT NULL,
    FOREIGN KEY (periodeId) REFERENCES Periodes(Id),
    FOREIGN KEY (uniteEnseignementId) REFERENCES UnitesEnseignements(Id),
    FOREIGN KEY (classeId) REFERENCES Classes(Id)
);

CREATE TABLE IF NOT EXISTS Postes (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nom VARCHAR(100) NOT NULL,
    code VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS Personnels (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    code VARCHAR(100),
    nom VARCHAR(100) NOT NULL,
    deuxiemeNom VARCHAR(100),
    prenom VARCHAR(100) NOT NULL,
    sexe INTEGER NOT NULL,
    dateNaissance DATE,
    paysNaissance VARCHAR(100),
    villeNaissance VARCHAR(100),
    telephone VARCHAR(20),
    email VARCHAR(255),
    adresseId INTEGER,
    FOREIGN KEY (adresseId) REFERENCES Adresses(id)
);

CREATE TABLE IF NOT EXISTS PersonnelsPostes (
    personnelId INTEGER NOT NULL,
    posteId INTEGER NOT NULL,
    PRIMARY KEY (personnelId, posteId),
    FOREIGN KEY (personnelId) REFERENCES Personnels(id)
    FOREIGN KEY (posteId) REFERENCES Postes(id)
);

