create table emprunteurs (
    emp_code serial not null primary key,
    emp_nom varchar(30) not null,
    emp_prenom varchar(30) not null,
    emp_ddn date,
    emp_tel varchar(10),
    emp_email varchar(75) not null unique,
    unique (emp_prenom, emp_nom, emp_ddn)
)
;
create table auteurs (
    aut_code serial not null primary key,
    aut_nom varchar(30) not null,
    aut_prenom varchar(30) not null,
    unique (aut_prenom, aut_nom)
)
;
create table collections (
    col_code serial not null primary key,
    col_nom varchar(30) not null unique
)
;
create table livres (
    liv_code varchar(4) not null primary key,
    liv_titre varchar(50) not null,
    liv_pages smallint,
    liv_edition smallint,
    liv_collection integer not null references collections(col_code),
    liv_parution date
)
;
create table reservations (
    res_code serial not null primary key,
    res_emprunteur integer not null references emprunteurs(emp_code),
    res_livre varchar(4) not null references livres (liv_code),
    res_date date not null default current_date,
    unique (res_livre, res_date)
)
;
create table livres_auteurs (
    id serial not null primary key,
    auteur_id integer not null references auteurs(aut_code) on delete cascade,
    livre_id varchar(4) not null references livres(liv_code) on delete cascade,
    unique (auteur_id, livre_id)
)
;

insert into auteurs(aut_nom, aut_prenom) values('Lutz', 'Mark');
insert into auteurs(aut_nom, aut_prenom) values('Pilgrim', 'Mark');
insert into auteurs(aut_nom, aut_prenom) values('Martelli', 'Alex');
insert into auteurs(aut_nom, aut_prenom) values('Ascher', 'David');

insert into collections(col_nom) values('Apress');
insert into collections(col_nom) values('O''Reilly');

insert into livres values('A108', 'Python précis et concis', 80, 2, 2, '2000/01/01');
insert into livres values('A123', 'Programmation Python', 536, 2, 2, '2006/01/01');
insert into livres values('A124', 'Introduction à Python', 1216, 4, 2, '2009/09/01');
insert into livres values('A256', 'Python en concentré', 654, 3, 2, '2003/01/01');
insert into livres values('B150', 'Python: Le livre de Recettes', 554, 3, 2, '2006/07/01');
insert into livres values('B155', 'Plongez au coeur de Python', 413, 1, 1, '2004/07/19');

insert into livres_auteurs values(default, 1, 'A108');
insert into livres_auteurs values(default, 1, 'A123');
insert into livres_auteurs values(default, 1, 'A124');
insert into livres_auteurs values(default, 4, 'A124');
insert into livres_auteurs values(default, 2, 'B155');
insert into livres_auteurs values(default, 3, 'A256');
insert into livres_auteurs values(default, 3, 'B150');
insert into livres_auteurs values(default, 4, 'B150');

insert into emprunteurs(emp_prenom, emp_nom, emp_email, emp_ddn, emp_tel) 
	values('Betty', 'Boop', 'betty.boop@hollywood.com', '1930-08-09', '043669050');

insert into reservations(res_emprunteur, res_livre)  
	select emprunteurs.emp_code, liv_code 
	from emprunteurs, livres 
	where emp_email = 'betty.boop@hollywood.com' and liv_code = 'A108';
insert into reservations(res_emprunteur, res_livre) 
	select emprunteurs.emp_code, liv_code 
	from emprunteurs, livres 
	where emp_email = 'betty.boop@hollywood.com' and liv_code = 'A123';

