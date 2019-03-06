USE mytunes;

create table author (
    id INTEGER auto_increment primary key,
    first_name  varchar(50) NOT NULL,
    second_name varchar(50) NOT NULL,
    pseudonim   varchar(50)   NOT NULL unique
    );

create table bonus (
    id INTEGER auto_increment primary key,
    bonus varchar(500) NOT NULL unique
    );

create table genre (
    id INTEGER auto_increment primary key,
    genre varchar(50) NOT NULL unique
    );

create table album (
    id INTEGER auto_increment primary key,
    price       int           NOT NULL,
    description varchar(500) NOT NULL,
    author_id   int       default '1' NOT NULL,
    genre_id    int       ,
    constraint album_author_id_fk foreign key (author_id) references author (id) on DELETE cascade ,
    constraint album_genre_fk     foreign key (genre_id)  references genre (id) on DELETE cascade
    );

create table composition (
    id INTEGER auto_increment primary key,
    price       int         NOT NULL,
    name        varchar(50) NOT NULL,
    album_id    int         NOT NULL,
    year        int,
    constraint composition_album_fk foreign key (album_id) references album (id) on DELETE cascade
    );

create table music_selection (
    id INTEGER auto_increment primary key,
    selection_id   int NOT NULL,
    composition_id int NOT NULL,
    constraint music_selection_composition_fk foreign key (composition_id) references composition (id) on DELETE cascade
    );

create table music_selection_feedback (
    id       int           NOT NULL,
    feedback varchar(500) NOT NULL,
    date     timestamp     NOT NULL,
    constraint music_selection_feedback_music_selection_fk foreign key (id) references music_selection (id) on delete cascade
    );

create table ROLE (
    id   INTEGER auto_increment primary key,
    role VARCHAR(50) NOT NULL
    );

create table status (
    id     INTEGER auto_increment primary key,
    status varchar(50) NOT NULL
    );

create table users (
    id            INTEGER auto_increment
    primary key,
    register_date timestamp   NOT NULL,
    login         varchar(40) NOT NULL unique,
    password      varchar(60) NOT NULL,
    first_name    varchar(50) NOT NULL,
    second_name   varchar(50) NOT NULL,
    email         varchar(50) NOT NULL unique,
    balance       int      NOT NULL,
    sale          int     NOT NULL,
    role_id       int     ,
    statusId     int     ,
    constraint user_role_fk   foreign key (role_id)   references role (id) on DELETE cascade ,
    constraint user_status_fk foreign key (statusId) references status (id) ON DELETE CASCADE
    );

create table user_album (
    id       INTEGER auto_increment
    primary key,
    user_id  int NOT NULL,
    album_id int NOT NULL,
    constraint user_album_user_fk  foreign key (user_id)  references users (id) ON DELETE CASCADE,
    constraint user_album_album_fk foreign key (album_id) references album (id) ON DELETE CASCADE
    );

create table album_feedback (
    id       int           NOT NULL,
    feedback varchar(500) NOT NULL,
    date     date     NOT NULL,
    constraint album_feedback_user_album_fk foreign key (id) references user_album (id) on delete cascade
    );

create table user_bonus (
    id       INTEGER auto_increment
    primary key,
    user_id  int     NOT NULL,
    bonus_id int     NOT NULL,
    constraint user_id_fk  foreign key (user_id)  references users (id) ON DELETE CASCADE,
    constraint bonus_id_fk foreign key (bonus_id) references bonus (id) ON DELETE CASCADE
    );

create table user_composition (
    id             INTEGER auto_increment
    primary key,
    user_id        int NOT NULL,
    composition_id int NOT NULL,
    constraint user_composition_composition_fk foreign key (composition_id) references composition (id) on DELETE cascade ,
    constraint user_composition_user_fk foreign key (user_id) references users (id) on DELETE cascade
    );

create table composition_feedback (
    id       INTEGER       NOT NULL,
    feedback varchar(500) NOT NULL,
    date     timestamp     NOT NULL,
    constraint composition_feedback_user_composition_fk foreign key (id) references user_composition (id) on delete cascade
    );

create table user_music_selection (
    id INTEGER auto_increment primary key,
    user_id         int NOT NULL,
    selection_id    int NOT NULL
    );

create table session_data (
     id INTEGER auto_increment primary key,
     user_id int not null,
     sessonhash varchar(60) not null
);