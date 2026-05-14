-- Creazione del database
CREATE DATABASE IF NOT EXISTS biblioteca_scolastica;
USE biblioteca_scolastica;

-- 1. Tabella utenti (Dati comuni)
CREATE TABLE utenti (
    id_utente INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    cognome VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    tipo_utente ENUM('STUDENTE', 'DOCENTE') NOT NULL
);

-- 2. Tabella studenti (Dati specifici)
CREATE TABLE studenti (
    id_utente INT PRIMARY KEY,
    classe VARCHAR(10) NOT NULL,
    FOREIGN KEY (id_utente) REFERENCES utenti(id_utente) ON DELETE CASCADE
);

-- 3. Tabella docenti (Dati specifici)
CREATE TABLE docenti (
    id_utente INT PRIMARY KEY,
    materia VARCHAR(50) NOT NULL,
    FOREIGN KEY (id_utente) REFERENCES utenti(id_utente) ON DELETE CASCADE
);

-- 4. Tabella libri
CREATE TABLE libri (
    id_libro INT AUTO_INCREMENT PRIMARY KEY,
    titolo VARCHAR(100) NOT NULL,
    autore VARCHAR(100) NOT NULL,
    anno_pubblicazione INT NOT NULL,
    disponibile BOOLEAN NOT NULL DEFAULT TRUE
);

-- 5. Tabella prestiti
CREATE TABLE prestiti (
    id_prestito INT AUTO_INCREMENT PRIMARY KEY,
    id_utente INT NOT NULL,
    id_libro INT NOT NULL,
    data_prestito DATE NOT NULL,
    data_restituzione DATE,
    stato ENUM('ATTIVO', 'RESTITUITO') NOT NULL DEFAULT 'ATTIVO',
    FOREIGN KEY (id_utente) REFERENCES utenti(id_utente) ON DELETE CASCADE,
    FOREIGN KEY (id_libro) REFERENCES libri(id_libro) ON DELETE CASCADE
);

-- ==========================================
-- INSERIMENTO DATI OBBLIGATORI PER I TEST
-- ==========================================

-- Inserimento Utenti (Studenti)
INSERT INTO utenti (nome, cognome, email, tipo_utente) VALUES 
('Mario', 'Rossi', 'mario.rossi@email.com', 'STUDENTE'),
('Giulia', 'Bianchi', 'giulia.bianchi@email.com', 'STUDENTE'),
('Ahmed', 'Benali', 'ahmed.benali@email.com', 'STUDENTE');

-- Recupero gli ID generati e inserisco nella tabella specifica 'studenti'
-- (Assumendo che gli ID generati siano 1, 2 e 3)
INSERT INTO studenti (id_utente, classe) VALUES 
(1, '4A'),
(2, '5B'),
(3, '3C');

-- Inserimento Utenti (Docenti)
INSERT INTO utenti (nome, cognome, email, tipo_utente) VALUES 
('Laura', 'Verdi', 'laura.verdi@email.com', 'DOCENTE'),
('Paolo', 'Neri', 'paolo.neri@email.com', 'DOCENTE');

-- Recupero gli ID generati e inserisco nella tabella specifica 'docenti'
-- (Assumendo che gli ID generati siano 4 e 5)
INSERT INTO docenti (id_utente, materia) VALUES 
(4, 'Informatica'),
(5, 'Matematica');

-- Inserimento Libri (Tutti disponibili di default)
INSERT INTO libri (titolo, autore, anno_pubblicazione, disponibile) VALUES 
('Java Base', 'Marco Riva', 2020, TRUE),
('Programmazione OOP', 'Anna Galli', 2021, TRUE),
('Database MySQL', 'Fabio Costa', 2022, TRUE),
('Algoritmi e Strutture Dati', 'Sara Conti', 2019, TRUE),
('Reti Informatiche', 'Luca Ferri', 2018, TRUE),
('HTML e CSS', 'Marta Leone', 2023, TRUE),
('Sistemi Operativi', 'Andrea Romano', 2020, TRUE);