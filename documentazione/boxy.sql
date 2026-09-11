DROP TABLE IF EXISTS prenotazione CASCADE;
DROP TABLE IF EXISTS box CASCADE;
DROP TABLE IF EXISTS punto_vendita CASCADE;
DROP TABLE IF EXISTS utente CASCADE;

CREATE TABLE utente (
    id_utente SERIAL PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    cognome VARCHAR(50) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(50) NOT NULL
);

CREATE TABLE punto_vendita (
    id_punto_vendita SERIAL PRIMARY KEY,
    nome VARCHAR(100) UNIQUE NOT NULL,
    indirizzo VARCHAR(150) NOT NULL,
    orario_ritiro VARCHAR(50) NOT NULL,
    tipo VARCHAR(50) NOT NULL CHECK (tipo IN ('SUPERMERCATO', 'RISTORANTE')),
    categoria VARCHAR(50),
    reparto VARCHAR(50)
);

CREATE TABLE box (
    id_box SERIAL PRIMARY KEY,
    id_punto_vendita INT REFERENCES punto_vendita(id_punto_vendita) ON DELETE CASCADE,
    prezzo_originale NUMERIC(5,2) NOT NULL,
    prezzo_scontato NUMERIC(5,2) NOT NULL,
    quantita_disponibile INT NOT NULL CHECK (quantita_disponibile >= 0),
    grandezza_box VARCHAR(50) NOT NULL
);

CREATE TABLE prenotazione (
    id_prenotazione SERIAL PRIMARY KEY,
    id_utente INT REFERENCES utente(id_utente) ON DELETE CASCADE,
    id_box INT REFERENCES box(id_box) ON DELETE CASCADE,
    data_prenotazione DATE DEFAULT CURRENT_DATE,
    codice_ritiro VARCHAR(50),
    stato VARCHAR(30) DEFAULT 'ATTIVA'
);

INSERT INTO punto_vendita (id_punto_vendita, nome, indirizzo, orario_ritiro, tipo, categoria, reparto) VALUES
(1, 'Despar', 'Via Roma 1', '19:00 - 20:30', 'SUPERMERCATO', NULL, 'Ortofrutta'),
(2, 'Conad', 'Via Toledo 10', '19:00 - 20:30', 'SUPERMERCATO', NULL, 'Banco Salumi'),
(3, 'Sole365', 'Via Toledo 20', '20:00 - 21:00', 'SUPERMERCATO', NULL, 'Cassa Centrale'),
(4, 'Italiamo', 'Corso Umberto 12', '21:00 - 22:00', 'RISTORANTE', 'Italiana', NULL),
(5, 'Guacamole', 'Via Chiaia 4', '20:30 - 21:30', 'RISTORANTE', 'Messicana', NULL),
(6, 'Tokyo', 'Via Duomo 88', '21:30 - 22:30', 'RISTORANTE', 'Giapponese', NULL);

INSERT INTO box (id_box, id_punto_vendita, prezzo_originale, prezzo_scontato, quantita_disponibile, grandezza_box) VALUES
(1, 1, 13.00, 6.00, 9, 'Grande'),
(2, 2, 10.00, 3.99, 7, 'Media'),
(3, 3, 8.00, 2.99, 5, 'Piccola'),
(4, 4, 15.00, 5.99, 10, 'Grande'),
(5, 5, 14.00, 5.50, 6, 'Media'),
(6, 6, 18.00, 6.99, 7, 'Grande');

INSERT INTO utente (nome, cognome, email, password) VALUES
('Roberta', 'Pagliuca', 'roberta.pagliuca@studenti.unina.it', '1234'),
('Noemi', 'Savelli', 'noemi.savelli@studenti.unina.it', '1234'),
('Riccardo', 'Caccavale', 'riccardo.caccavale@unina.it', '1234'),
('Bernardo', 'Breve', 'bernardo.breve@unina.it', '1234');

-- Allineamento delle sequenze automatiche SERIAL
SELECT setval('punto_vendita_id_punto_vendita_seq', (SELECT MAX(id_punto_vendita) FROM punto_vendita));
SELECT setval('box_id_box_seq', (SELECT MAX(id_box) FROM box));
SELECT setval('utente_id_utente_seq', (SELECT MAX(id_utente) FROM utente));

-- Trigger 1: decrementa disponibilita se ci sono box, altrimenti blocca l'inserimento
CREATE OR REPLACE FUNCTION trg_decrementa_box()
RETURNS TRIGGER AS $$
BEGIN
    IF (SELECT quantita_disponibile FROM box WHERE id_box = NEW.id_box) <= 0 THEN
        RAISE EXCEPTION 'Errore: Box esaurita, prenotazione non consentita.';
    END IF;

    UPDATE box
    SET quantita_disponibile = quantita_disponibile - 1
    WHERE id_box = NEW.id_box;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS trigger_prenota_box ON prenotazione;
CREATE TRIGGER trigger_prenota_box
BEFORE INSERT ON prenotazione
FOR EACH ROW
EXECUTE FUNCTION trg_decrementa_box();

-- Trigger 2: ripristina disponibilita quando l'utente annulla la prenotazione
CREATE OR REPLACE FUNCTION trg_ripristina_box()
RETURNS TRIGGER AS $$
BEGIN
    IF OLD.stato <> 'ANNULLATA' AND NEW.stato = 'ANNULLATA' THEN
        UPDATE box
        SET quantita_disponibile = quantita_disponibile + 1
        WHERE id_box = NEW.id_box;
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS trigger_annulla_box ON prenotazione;
CREATE TRIGGER trigger_annulla_box
AFTER UPDATE ON prenotazione
FOR EACH ROW
EXECUTE FUNCTION trg_ripristina_box();

-- Trigger 3: controlla che i prezzi siano maggiori di zero e lo sconto valido
CREATE OR REPLACE FUNCTION trg_check_prezzi_box()
RETURNS TRIGGER AS $$
BEGIN
    IF NEW.prezzo_scontato >= NEW.prezzo_originale THEN
        RAISE EXCEPTION 'Errore: Il prezzo scontato deve essere inferiore al prezzo originale.';
    END IF;

    IF NEW.prezzo_scontato <= 0 OR NEW.prezzo_originale <= 0 THEN
        RAISE EXCEPTION 'Errore: I prezzi devono essere maggiori di zero.';
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS trigger_verifica_prezzi ON box;
CREATE TRIGGER trigger_verifica_prezzi
BEFORE INSERT OR UPDATE ON box
FOR EACH ROW
EXECUTE FUNCTION trg_check_prezzi_box();