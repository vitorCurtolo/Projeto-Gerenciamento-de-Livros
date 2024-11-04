CREATE DATABASE crud_db;

USE crud_db;

create table livros (
	id  int(3) NOT NULL AUTO_INCREMENT,
	nome varchar(250) NOT NULL,
	autor varchar(250) NOT NULL,
	nmrPaginas int,
    isbn float,
    isDeleted boolean default true,
    capa varchar(350),
	PRIMARY KEY (id)
);


INSERT INTO `crud_db`.`livros` (`nome`, `autor`, `nmrPaginas`, `isbn`, `isDeleted`, `capa`) VALUES 
('Código Limpo', 'Robert Martin', '464', '1', '1', 'https://m.media-amazon.com/images/I/71FC1O01QZL._AC_UL800_FMwebp_QL65_.jpg'),
('Refatoração: Aperfeiçoando o Design de Códigos Existentes', 'Martin Fowler', '448', '2', '1', 'https://m.media-amazon.com/images/I/71S3H7aTNmL._AC_UL800_FMwebp_QL65_.jpg'),
('The Pragmatic Programmer', 'Andrew Hunt, David Thomas', '352', '3', '1', 'https://m.media-amazon.com/images/I/41v+tWE4ffL._AC_UL800_FMwebp_QL65_.jpg'),
('Design Patterns: Elements of Reusable Object-Oriented Software', 'Erich Gamma, Richard Helm, Ralph Johnson, John Vlissides', '395', '4', '1', 'https://m.media-amazon.com/images/I/41X2Eob+fhL._AC_UL800_FMwebp_QL65_.jpg'),
('Estruturas de Dados e Algoritmos em Java', 'Robert Lafore', '800', '5', '1', 'https://m.media-amazon.com/images/I/71X6IYZdWmL._AC_UL800_FMwebp_QL65_.jpg'),
('Padrões de Arquitetura de Aplicações Corporativas', 'Martin Fowler', '560', '6', '1', 'https://m.media-amazon.com/images/I/81+GzYsLe0L._AC_UL800_FMwebp_QL65_.jpg'),
('Java: Como Programar', 'Paul Deitel, Harvey Deitel', '1568', '7', '1', 'https://m.media-amazon.com/images/I/71bGFDWg6HL._AC_UL800_FMwebp_QL65_.jpg'),
('Desenvolvimento Web com Node.js', 'Caio Ribeiro Pereira', '256', '8', '1', 'https://m.media-amazon.com/images/I/71F7dpCjdPL._AC_UL800_FMwebp_QL65_.jpg'),
('Use a Cabeça!: Java', 'Kathy Sierra, Bert Bates', '688', '9', '1', 'https://m.media-amazon.com/images/I/71pFCRci6BL._AC_UL800_FMwebp_QL65_.jpg'),
('JavaScript: The Good Parts', 'Douglas Crockford', '176', '10', '1', 'https://m.media-amazon.com/images/I/71WaZmEqwZL._AC_UL800_FMwebp_QL65_.jpg'),
('Python para Análise de Dados', 'Wes McKinney', '522', '11', '1', 'https://m.media-amazon.com/images/I/91F9y1E1zmL._AC_UL800_FMwebp_QL65_.jpg'),
('Estruturas de Dados e Algoritmos com JavaScript', 'Loiane Groner', '408', '12', '1', 'https://m.media-amazon.com/images/I/81e3bh83ymL._AC_UL800_FMwebp_QL65_.jpg'),
('Clean Code em JavaScript', 'James Padolsey', '382', '13', '1', 'https://m.media-amazon.com/images/I/91-u-+pLu8L._AC_UL800_FMwebp_QL65_.jpg'),
('Desenvolvimento Web com PHP e MySQL', 'Jon Duckett', '320', '14', '1', 'https://m.media-amazon.com/images/I/81+cfw+yCQL._AC_UL800_FMwebp_QL65_.jpg'),
('Aprendendo SQL', 'Alan Beaulieu', '352', '15', '1', 'https://m.media-amazon.com/images/I/81EVdTPHRcL._AC_UL800_FMwebp_QL65_.jpg'),
('Engenharia de Software', 'Ian Sommerville', '816', '16', '1', 'https://m.media-amazon.com/images/I/81rtIFwJfmL._AC_UL800_FMwebp_QL65_.jpg'),
('Algoritmos: Teoria e Prática', 'Thomas H. Cormen', '1312', '17', '1', 'https://m.media-amazon.com/images/I/71xd4DeJhOL._AC_UL800_FMwebp_QL65_.jpg'),
('Fundamentos de Arquitetura de Software', 'Neal Ford, Mark Richards', '310', '18', '1', 'https://m.media-amazon.com/images/I/91iKTr4PMqL._AC_UL800_FMwebp_QL65_.jpg'),
('Test Driven Development: Teste e Design no Mundo Real', 'Kent Beck', '240', '19', '1', 'https://m.media-amazon.com/images/I/71bXLcqVUaL._AC_UL800_FMwebp_QL65_.jpg'),
('Git: A Estrada do Conhecimento', 'Nico Steppat', '180', '20', '1', 'https://m.media-amazon.com/images/I/71cC0D3oz2L._AC_UL800_FMwebp_QL65_.jpg');

-- TRUNCATE TABLE livros;
SELECT * FROM crud_db.livros;