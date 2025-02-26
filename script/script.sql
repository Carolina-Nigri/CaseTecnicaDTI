CREATE TABLE Livros(
    id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    titulo VARCHAR(70) NOT NULL,
    autor VARCHAR(70) NOT NULL,
    dataPublicacao VARCHAR(10) NOT NULL,
    qtdPaginas INTEGER,
    idioma VARCHAR(50),
    editora VARCHAR(50)
);