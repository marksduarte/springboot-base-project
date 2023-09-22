create schema if not exists auth;
set schema auth;

create table tb_user (
    id_user uuid not null primary key,
    st_username varchar(100) not null,
    st_email varchar(100) not null,
    st_password varchar(500) not null
);

insert into tb_user (id_user, st_username, st_email, st_password)
values ('5c539b93-b12c-4a1b-9d37-45ea2c421a60', 'marks.duarte', 'eu@marksduarte.dev', 'password'),
       ('40e696fa-31eb-4561-82d1-ff3dc4bfb6ec', 'usuario.novo', 'usuario@novo.dev', 'password2')
