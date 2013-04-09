-- Authors Mark & Daniel Thomsen
 
drop table odetaljer;
drop table ordrer;
drop table ressourcer;
drop table kunder;
drop table postnummer;
  
create table postnummer (
  postnummer  number(5),
  bynavn      varchar2(30),
  primary key (postnummer));
  
create table kunder (
  knummer       number(5) not null primary key,
  postnummer    number(5) not null references postnummer,
  addresse      varchar2(30) not null,
  navn          varchar2(50) not null,
  telefonnummer number(8) not null unique
  );
  
create table ressourcer (
  vnummer        number(6) primary key not null,
  varernavn      varchar2(10) not null,
  qoh            integer check(qoh >= 0),
  pris           number(6,2) check(pris >= 0.0),
  teltst¿rrelse  varchar2(30)
  );
  
create table ordrer (
  onummer  number(6) not null primary key,
  knummer  number(5) references kunder,
  status varchar2(30),
  modtaget date,
  sendt    date);

create table odetaljer (
  onummer  number(6) not null references ordrer,
  vnummer  number(6) not null references ressourcer on delete cascade,
  m¾ngde   integer check(m¾ngde > 0),
  primary key (onummer,vnummer));
  

insert into postnummer values
  (2800,'Kongens Lyngby');
insert into  postnummer values
  (3660,'Stenl¿se');
insert into  postnummer values
  (2860,'S¿borg');

insert into kunder values
  (67226, 2800, 'Lyngby Torv 14', 'Karl Karlsen', 34786490);
insert into kunder values
  (67227, 3660, 'Henriettevej 6', 'Simon Thomsen', 61774567);
insert into kunder values
  (67228, 2860, 'K¿bmandsgade 59', 'Tina Olesen', 48907433);

insert into ressourcer values
  (389011, 'Gaffel', 1033, 0.25, Null);
insert into ressourcer values
  (389012, 'Kniv', 1033, 0.25, Null);
insert into ressourcer values
  (389013, 'Telt', 7, 2500, 'Stor');

insert into ordrer values
  (123456, 67226,'Hej', to_date('10-DEC-94','DD MON YY'),to_date('12-DEC-94','DD MON YY'));
insert into ordrer values
  (123457, 67227,'Hej', to_date('02-APR-13','DD MON YY'),to_date('06-APR-13','DD MON YY'));
insert into ordrer values
  (123458, 67228,'Hej', to_date('02-JAN-13','DD MON YY'),to_date('04-JAN-13','DD MON YY'));
  
insert into odetaljer values
  (123456,389011,60);
insert into odetaljer values
  (123457,389012,60);
insert into odetaljer values
  (123458,389013,1);
  
commit;


