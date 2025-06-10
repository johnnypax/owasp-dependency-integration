CREATE DATABASE its_recap_01_rubrica;
USE its_recap_01_rubrica;

CREATE TABLE Contatto(
	contattoID INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(250) NOT NULL,
    cognome VARCHAR(250) NOT NULL,
    email VARCHAR(250),
    telefono VARCHAR(250)
);

INSERT INTO Contatto (nome, cognome, email, telefono) VALUES ('Marco', 'Rossi', 'marco.rossi@example.com', '3331234567');
INSERT INTO Contatto (nome, cognome, email, telefono) VALUES ('Laura', 'Bianchi', 'laura.bianchi@example.com', '3349876543');
INSERT INTO Contatto (nome, cognome, email, telefono) VALUES ('Giuseppe', 'Verdi', 'giuseppe.verdi@example.com', '3351122334');
INSERT INTO Contatto (nome, cognome, email, telefono) VALUES ('Anna', 'Russo', 'anna.russo@example.com', '3365432109');
INSERT INTO Contatto (nome, cognome, email, telefono) VALUES ('Paolo', 'Ferrari', 'paolo.ferrari@example.com', '3378765432');
INSERT INTO Contatto (nome, cognome, email, telefono) VALUES ('Maria', 'Esposito', 'maria.esposito@example.com', '3382345678');
INSERT INTO Contatto (nome, cognome, email, telefono) VALUES ('Luca', 'Ricci', 'luca.ricci@example.com', '3397654321');
INSERT INTO Contatto (nome, cognome, email, telefono) VALUES ('Sofia', 'Bruno', 'sofia.bruno@example.com', '3321098765');
INSERT INTO Contatto (nome, cognome, email, telefono) VALUES ('Andrea', 'Gallo', 'andrea.gallo@example.com', '3316789012');
INSERT INTO Contatto (nome, cognome, email, telefono) VALUES ('Elena', 'Conti', 'elena.conti@example.com', '3303456789');

SELECT * FROM Contatto;