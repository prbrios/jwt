create table usuario (
    id bigserial,
    email text,
    senha text,
    primary key (id),
    unique(email)
);

create table perfil (
    id bigserial,
    nome text,
    primary key (id)
);

create table usuario_perfil (
    id_usuario bigint not null,
    id_perfil bigint not null,
    foreign key (id_usuario) references usuario (id),
    foreign key (id_perfil) references perfil (id),
    primary key (id_usuario, id_perfil)
);

insert into usuario (email, senha) values ('prbrios@gmail.com', '$2a$10$0g7ZPXuSkL6ZmZxCE1JDaucfU6FrYewMnHLYGNLsBUu0HlMvd.nJi');
insert into perfil (nome) values ('ADMIN');
insert into usuario_perfil (id_usuario, id_perfil) values (1,1);