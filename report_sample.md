# Report statico
### Versione  v1.0.20240606
Data di esecuzione: 06/06/2024

## Descrizione del progetto
Il progetto ha l'obiettivo principale di salvare dei dati sul Database di tipo MySQL utilizzando la tecnologia JAVA.

## Obiettivo analisi *
Identificazione delle vulnerabilità dirette ed indirette presenti all'interno di un progetto JAVA che si occupa di interazione con il database.
L'analisi è partizionata in due processi, analisi delle dipendenze ed analisi statica del codice sorgente.

# Analisi delle dipendenze
Utilizzando OWASP come analizzatore principale, sono state identificati i seguenti dettagli:
- Strumento utilizzato: **OWASP Dependency Checker v7.0.4**
- Generazione report: **06/06/2024 11:34:06**
- Dipendenze identificate e scansionate: **2**
- Dipendenze vulnerabili: **2**
- Vulnerabilità trovate: **7**
- Vulnerabilità soppresse: **0**

## Dettaglio CVE - DIRETTE
### mysql-connector-java-8.0.26.jar
Connettore MySQL di tipologia JDBC Type 4 driver per MySQL

- **CVE-2021-2471**
Vulnerabilità sulla classe di connettore MySQL Connector/J. In questa classe di connettori la vulnerabilità segnalata ha come massimo di versione: 8.0.26.

Questa tipologia di vulnerabilità permette a degli utenti con elevati privilegi di sfruttare la connessione, con diverse tipologie di protocollo, di sfruttare la vulnerabilità in grado di compromettere l'eseguibilità del connettore MySQL Connector/J.

Un attacco che ha successo compromette l'utilizzabilità del MySQL Connector causando uno o più temporanei crash (DoS) oltre a garantire accesso a dati critici e accesso completo alle funzionalità del MySQL Connector/J.

#### Analisi CVSS v3.1: /AV:N/AC:H/PR:H/UI:N/S:U/C:H/I:N/A:H
- Attack Vector: Network
- Attack Complexity: High
- Priviledges Required: High
- User Interaction: None
- Scope: Unchanged
- Confidentiality: High
- Integrity: ...

#### Remediation

- Si consiglia l'aggiornamento della lib...

...

## Dettaglio CVE - INDIRETTE

### protobuf-java-3.11.4.jar
...

# Analisi statica del codice sorgente
Nell'analisi viene incluso il file **App.java** seguendo i criteri standard dell'analisi statica tecnologica del codice sorgente.

## Dettaglio analisi

### 1. Stringhe di connessione visibili
Il sorgente JAVA che viene compilato in ByteCode non effettua l'encoding o il mascheramento delle stringhe di caratteri nella creazione dei relativi .class.

Un possibile attaccante, che ha accesso ai file .class, può editare il file con un semplice editor di testo e di conseguenza avere accesso a queste informazioni.

#### Identificazione:
- Riga di codice: 20-59

#### Gravità
- High

#### Operazioni consigliate
- Si rimanda al processo di sviluppo

### 2. Password di connessione troppo debole
Una stringa password troppo debole potrebbe permettere all'attaccante...

#### Identificazione:
- Riga di codice: 27

#### Gravità
- High

#### Operazioni consigliate
- Si raccomanda l'utilizzo di una password complessa che comprenda in misura del 20% caratteri non alfabetici e che abbia una lunghezza di almeno 12 caratteri.