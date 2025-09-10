create table if not exists beneficio (
  id bigint generated always as identity primary key,
  nome varchar(120) not null,
  descricao varchar(255),
  active boolean not null default true
);