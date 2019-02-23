create table author (
    id INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 1, INCREMENT BY 1) primary key,
    first_name  varchar(5000) NOT NULL,
    second_name varchar(5000) NOT NULL,
    pseudonim   varchar(50)   NOT NULL unique
    );

create table bonus (
    id INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 1, INCREMENT BY 1) primary key,
    bonus varchar(5000) NOT NULL unique
    );

create table genre (
    id INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 1, INCREMENT BY 1) primary key,
    genre varchar(5000) NOT NULL unique
    );

create table album (
    id INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 1, INCREMENT BY 1) primary key,
    price       int           NOT NULL,
    description varchar(5000) NOT NULL,
    author_id   TINYINT       default '1' NOT NULL,
    genre_id    TINYINT       NOT NULL,
    constraint album_author_id_fk foreign key (author_id) references author (id) on DELETE set default ,
    constraint album_genre_fk     foreign key (genre_id)  references genre (id)
    );

create table composition (
    id INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 1, INCREMENT BY 1) primary key,
    price       int         NOT NULL,
    name        varchar(50) NOT NULL,
    album_id    int         NOT NULL,
    year        int,
    constraint composition_album_fk foreign key (album_id) references album (id)
    );

create table music_selection (
    id INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 1, INCREMENT BY 1) primary key,
    composition_id int NOT NULL,
    selection_id   int NOT NULL,
    constraint music_selection_composition_fk foreign key (composition_id) references composition (id)
    );

create table music_selection_feedback (
    id       int           NOT NULL,
    feedback varchar(5000) NOT NULL,
    date     timestamp     NOT NULL,
    constraint music_selection_feedback_music_selection_fk foreign key (id) references music_selection (id)
    );

create table ROLE (
    id   INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 1, INCREMENT BY 1) primary key,
    role VARCHAR(50) NOT NULL
    );

create table status (
    id     INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 1, INCREMENT BY 1) primary key,
    status varchar(50) NOT NULL
    );

create table users (
    id            INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 1, INCREMENT BY 1)
    primary key,
    register_date timestamp   NOT NULL,
    login         varchar(50) NOT NULL unique,
    password      varchar(70) NOT NULL,
    first_name    varchar(50) NOT NULL,
    second_name   varchar(50) NOT NULL,
    email         varchar(50) NOT NULL unique,
    balance       BIGINT      NOT NULL,
    sale          TINYINT     NOT NULL,
    role_id       TINYINT     NOT NULL,
    status_id     TINYINT     NOT NULL,
    constraint user_role_fk   foreign key (role_id)   references role (id),
    constraint user_status_fk foreign key (status_id) references status (id)
    );

create table user_album (
    id       INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 1, INCREMENT BY 1)
    primary key,
    user_id  TINYINT NOT NULL,
    album_id TINYINT NOT NULL,
    constraint user_album_user_fk  foreign key (user_id)  references users (id) ON DELETE CASCADE,
    constraint user_album_album_fk foreign key (album_id) references album (id) ON DELETE CASCADE
    );

create table album_feedback (
    id       int           NOT NULL,
    feedback varchar(5000) NOT NULL,
    date     timestamp     NOT NULL,
    constraint album_feedback_user_album_fk foreign key (id) references user_album (id)
    );

create table user_bonus (
    id       INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 1, INCREMENT BY 1)
    primary key,
    user_id  int     NOT NULL,
    bonus_id TINYINT NOT NULL,
    constraint user_id_fk  foreign key (user_id)  references users (id)  ON DELETE CASCADE,
    constraint bonus_id_fk foreign key (bonus_id) references bonus (id)
    );

create table user_composition (
    id             INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 1, INCREMENT BY 1)
    primary key,
    user_id        int NOT NULL,
    composition_id int NOT NULL,
    constraint user_composition_composition_fk foreign key (composition_id) references composition (id) on DELETE cascade ,
    constraint user_composition_user_fk foreign key (user_id) references users (id) on DELETE cascade
    );

create table composition_feedback (
    id       INTEGER       NOT NULL,
    feedback varchar(5000) NOT NULL,
    date     timestamp     NOT NULL,
    constraint composition_feedback_user_composition_fk foreign key (id) references user_composition (id) on delete cascade
    );

create table user_music_selection (
    id INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 1, INCREMENT BY 1) primary key,
    user_id            int NOT NULL,
    music_selection_id int NOT NULL
    );