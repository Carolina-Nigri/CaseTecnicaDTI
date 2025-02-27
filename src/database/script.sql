CREATE TABLE IF NOT EXISTS Livros(
    id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    titulo VARCHAR(70) NOT NULL,
    autor VARCHAR(70) NOT NULL,
    dataPublicacao VARCHAR(20) NOT NULL,
    qtdPaginas INTEGER,
    idioma VARCHAR(50),
    editora VARCHAR(50)
);

INSERT INTO Livros (titulo, autor, dataPublicacao)
VALUES ('Desbravando SOLID', 'Maria Clara', '2025-02-26');

INSERT INTO Livros (titulo, autor, dataPublicacao)
VALUES ('Percy Jackson', 'Rick Riordan', '2014-01-01');

INSERT INTO Livros (titulo, autor, dataPublicacao, qtdPaginas, idioma, editora)
VALUES ('Harry Potter', 'JK Rowling', '2012-01-01', 400, "Inglês", "Pottermore");