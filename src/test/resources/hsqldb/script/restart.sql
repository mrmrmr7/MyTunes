use mytunes;

drop table if exists user_music_selection;
drop table if exists composition_feedback;
drop table if exists user_composition;
drop table if exists user_bonus;
drop table if exists album_feedback;
drop table if exists user_album;
drop table if exists users;
drop table if exists status;
drop table if exists role;
drop table if exists music_selection_feedback;
drop table if exists music_selection;
drop table if exists composition;
drop table if exists album;
drop table if exists genre;
drop table if exists bonus;
drop table if exists author;
drop table if exists session_data;

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
                     status_id     int     ,
                     constraint user_role_fk   foreign key (role_id)   references role (id) on DELETE cascade ,
                     constraint user_status_fk foreign key (status_id) references status (id) ON DELETE CASCADE
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
                              date     timestamp     NOT NULL,
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

insert into STATUS (STATUS) values ('ACTIVE');
insert into STATUS (STATUS) values ('DEACTIVE');
insert into STATUS (STATUS) values ('DELETE');

insert into ROLE (ROLE) values ('ADMIN');
insert into ROLE (ROLE) values ('CLIENT');

insert into BONUS (BONUS) values ('sale 40%');
insert into BONUS (BONUS) values ('sale 50%');
insert into BONUS (BONUS) values ('sale 60%');
insert into BONUS (BONUS) values ('sale 70%');
insert into BONUS (BONUS) values ('sale 80%');
insert into BONUS (BONUS) values ('sale 90%');
insert into BONUS (BONUS) values ('free song');
insert into BONUS (BONUS) values ('free album');
insert into BONUS (BONUS) values ('free music selection');
insert into BONUS (BONUS) values ('unlim');

insert into AUTHOR (FIRST_NAME, SECOND_NAME, PSEUDONIM) values ('UNKNOWN', 'UNKNOWN', 'UNKNOWN');
insert into AUTHOR (FIRST_NAME, SECOND_NAME, PSEUDONIM) values ('ALEXANDR', 'ZAPOROZHTSEV', 'GRAND-MASTER');
insert into AUTHOR (FIRST_NAME, SECOND_NAME, PSEUDONIM) values ('IOSIPH', 'STALIN', 'SLAVA KPSS');
insert into AUTHOR (FIRST_NAME, SECOND_NAME, PSEUDONIM) values ('LOSKAH', 'ANTON', 'MORGENSTERN');
insert into AUTHOR (FIRST_NAME, SECOND_NAME, PSEUDONIM) values ('DVERI', 'ANON', 'STONE');
insert into AUTHOR (FIRST_NAME, SECOND_NAME, PSEUDONIM) values ('SPICE', 'SNAF', 'LSP');
insert into AUTHOR (FIRST_NAME, SECOND_NAME, PSEUDONIM) values ('ANDREY', 'KIRILL', 'FLOVERS');
insert into AUTHOR (FIRST_NAME, SECOND_NAME, PSEUDONIM) values ('MALOY', 'DILLER', 'TWOBOYS');
insert into AUTHOR (FIRST_NAME, SECOND_NAME, PSEUDONIM) values ('MALOY1', 'DILLER', 'TWOBOYS1');
insert into AUTHOR (FIRST_NAME, SECOND_NAME, PSEUDONIM) values ('MALOY2', 'DILLER1', 'TWOBOYS2');

insert into USERS (LOGIN, PASSWORD, FIRST_NAME, SECOND_NAME, REGISTER_DATE, SALE, BALANCE, ROLE_ID, STATUS_ID, EMAIL) values ('MRMRMR1', '$2a$10$PyrB3GSKF5wo2zXpTh1SC.GLx7c7f92oy3rxzAmYLeAOriYIzuIy.', 'FIRST NAME', 'SECOND NAME', '2008-08-08 20:08:08', 10, 100, 1, 1, 'ALIEX.KOT1@yandex.by');
insert into USERS (LOGIN, PASSWORD, FIRST_NAME, SECOND_NAME, REGISTER_DATE, SALE, BALANCE, ROLE_ID, STATUS_ID, EMAIL) values ('MRMRMR2', '$2a$10$mlAlOP7Qwq1nEMRdqeU87OjWKjmV7/cxHTdeeJT5OxFBB4eHJ0uGS', 'FIRST NAME', 'SECOND NAME', '2008-08-08 20:08:08', 10, 100, 1, 1, 'ALIEX.KOT2@yandex.by');
insert into USERS (LOGIN, PASSWORD, FIRST_NAME, SECOND_NAME, REGISTER_DATE, SALE, BALANCE, ROLE_ID, STATUS_ID, EMAIL) values ('MRMRMR3', '$2a$10$tfIFXPz6VxdRi5KOGxB9runD0sHVI2dSmrOCvCzcZRZoXH.hZBL8.', 'FIRST NAME', 'SECOND NAME', '2008-08-08 20:08:08', 10, 100, 1, 1, 'ALIEX.KOT3@yandex.by');
insert into USERS (LOGIN, PASSWORD, FIRST_NAME, SECOND_NAME, REGISTER_DATE, SALE, BALANCE, ROLE_ID, STATUS_ID, EMAIL) values ('MRMRMR4', '$2a$10$YCsHtEdLw2djrLQOzhUP.eFzjnFyeyr.9dDr2ePQ.j8gP3OEgH0Uy', 'FIRST NAME', 'SECOND NAME', '2008-08-08 20:08:08', 10, 100, 1, 1, 'ALIEX.KOT4@yandex.by');
insert into USERS (LOGIN, PASSWORD, FIRST_NAME, SECOND_NAME, REGISTER_DATE, SALE, BALANCE, ROLE_ID, STATUS_ID, EMAIL) values ('MRMRMR5', '$2a$10$fxOSMDJhcZc7xEBkAjfmG.Dq1rV04h0qTS4UpoxWrvEDOa8UyFsvG', 'FIRST NAME', 'SECOND NAME', '2008-08-08 20:08:08', 10, 100, 1, 1, 'ALIEX.KOT5@yandex.by');
insert into USERS (LOGIN, PASSWORD, FIRST_NAME, SECOND_NAME, REGISTER_DATE, SALE, BALANCE, ROLE_ID, STATUS_ID, EMAIL) values ('MRMRMR6', '$2a$10$SiA79Eiouun12HvmtoYf7u6c8j/mx/SFHlNm0NOMmVkYI7RV9X5gu', 'FIRST NAME', 'SECOND NAME', '2008-08-08 20:08:08', 10, 100, 1, 1, 'ALIEX.KOT6@yandex.by');
insert into USERS (LOGIN, PASSWORD, FIRST_NAME, SECOND_NAME, REGISTER_DATE, SALE, BALANCE, ROLE_ID, STATUS_ID, EMAIL) values ('MRMRMR7', '$2a$10$kVtSXe2CxB/LxzxFIi4d7OpaTtmv.OjNke9v38NtAI8MgfjiEAnT.', 'FIRST NAME', 'SECOND NAME', '2008-08-08 20:08:08', 10, 100, 1, 1, 'ALIEX.KOT7@yandex.by');
insert into USERS (LOGIN, PASSWORD, FIRST_NAME, SECOND_NAME, REGISTER_DATE, SALE, BALANCE, ROLE_ID, STATUS_ID, EMAIL) values ('MRMRMR8', '$2a$10$Xef0CHEFBJnLaYWrYl2RE.8zGBJqK4pCKxAwirM3ku28vPxvecIFu', 'FIRST NAME', 'SECOND NAME', '2008-08-08 20:08:08', 10, 100, 1, 1, 'ALIEX.KOT8@yandex.by');
insert into USERS (LOGIN, PASSWORD, FIRST_NAME, SECOND_NAME, REGISTER_DATE, SALE, BALANCE, ROLE_ID, STATUS_ID, EMAIL) values ('MRMRMR9', '$2a$10$FdNnLot6QA2mIj/wWFR6uu4a.Fvtrf3Y9VXdIMUOfLq9R5EUFFjxS', 'FIRST NAME', 'SECOND NAME', '2008-08-08 20:08:08', 10, 100, 1, 1, 'ALIEX.KOT9@yandex.by');
insert into USERS (LOGIN, PASSWORD, FIRST_NAME, SECOND_NAME, REGISTER_DATE, SALE, BALANCE, ROLE_ID, STATUS_ID, EMAIL) values ('MRMRMR10', '$2a$10$nJzOa80/gZFdfBjEsgHlq.DlCAuV9gtluYPTkf9bs8XZj2EiIBm1G', 'FIRST NAME', 'SECOND NAME', '2008-08-08 20:08:08', 10, 100, 1, 1, 'ALIEX.KOT10@yandex.by');

insert into USER_BONUS (USER_ID, BONUS_ID) values (1, 1);
insert into USER_BONUS (USER_ID, BONUS_ID) values (2, 2);
insert into USER_BONUS (USER_ID, BONUS_ID) values (3, 2);
insert into USER_BONUS (USER_ID, BONUS_ID) values (4, 4);
insert into USER_BONUS (USER_ID, BONUS_ID) values (5, 5);
insert into USER_BONUS (USER_ID, BONUS_ID) values (7, 7);
insert into USER_BONUS (USER_ID, BONUS_ID) values (8, 4);
insert into USER_BONUS (USER_ID, BONUS_ID) values (9, 9);
insert into USER_BONUS (USER_ID, BONUS_ID) values (6, 2);
insert into USER_BONUS (USER_ID, BONUS_ID) values (6, 3);
insert into USER_BONUS (USER_ID, BONUS_ID) values (10, 2);

insert into GENRE (GENRE) values ('ROCK');
insert into GENRE (GENRE) values ('HARD ROCK');
insert into GENRE (GENRE) values ('DUBSTEP');
insert into GENRE (GENRE) values ('RAP');
insert into GENRE (GENRE) values ('HIPHOP');
insert into GENRE (GENRE) values ('SQUAD');
insert into GENRE (GENRE) values ('POP');
insert into GENRE (GENRE) values ('K-POP');
insert into GENRE (GENRE) values ('DJ');
insert into GENRE (GENRE) values ('METALL');

insert into ALBUM (PRICE, DESCRIPTION, AUTHOR_ID, GENRE_ID) values (123, 'Back side of moon', 1, 1);
insert into ALBUM (PRICE, DESCRIPTION, AUTHOR_ID, GENRE_ID) values (123, 'Gorgorod', 2, 2);
insert into ALBUM (PRICE, DESCRIPTION, AUTHOR_ID, GENRE_ID) values (123, 'Downtown', 3, 1);
insert into ALBUM (PRICE, DESCRIPTION, AUTHOR_ID, GENRE_ID) values (123, 'Some1', 2, 4);
insert into ALBUM (PRICE, DESCRIPTION, AUTHOR_ID, GENRE_ID) values (123, 'Downtown2', 5, 1);
insert into ALBUM (PRICE, DESCRIPTION, AUTHOR_ID, GENRE_ID) values (123, 'Back 3oon', 6, 1);
insert into ALBUM (PRICE, DESCRIPTION, AUTHOR_ID, GENRE_ID) values (123, 'Gorgorod3', 7, 2);
insert into ALBUM (PRICE, DESCRIPTION, AUTHOR_ID, GENRE_ID) values (123, 'Downtown4', 8, 1);
insert into ALBUM (PRICE, DESCRIPTION, AUTHOR_ID, GENRE_ID) values (123, 'Downtown5', 9, 1);
insert into ALBUM (PRICE, DESCRIPTION, AUTHOR_ID, GENRE_ID) values (123, 'Downtown6', 1, 1);

insert into USER_ALBUM (USER_ID, ALBUM_ID) values (1, 3);
insert into USER_ALBUM (USER_ID, ALBUM_ID) values (1, 5);
insert into USER_ALBUM (USER_ID, ALBUM_ID) values (1, 4);
insert into USER_ALBUM (USER_ID, ALBUM_ID) values (2, 3);
insert into USER_ALBUM (USER_ID, ALBUM_ID) values (3, 4);
insert into USER_ALBUM (USER_ID, ALBUM_ID) values (4, 1);
insert into USER_ALBUM (USER_ID, ALBUM_ID) values (5, 2);
insert into USER_ALBUM (USER_ID, ALBUM_ID) values (6, 5);
insert into USER_ALBUM (USER_ID, ALBUM_ID) values (7, 8);
insert into USER_ALBUM (USER_ID, ALBUM_ID) values (8, 3);
insert into USER_ALBUM (USER_ID, ALBUM_ID) values (9, 4);
insert into USER_ALBUM (USER_ID, ALBUM_ID) values (9, 2);
insert into USER_ALBUM (USER_ID, ALBUM_ID) values (10, 1);

insert into ALBUM_FEEDBACK (ID, FEEDBACK, DATE) values (1, 'NORM TAKOY ALBUM1', '2019-05-03 12:00:03');
insert into ALBUM_FEEDBACK (ID, FEEDBACK, DATE) values (2, 'NORM TAKOY ALBUM2', '2019-05-03 12:00:03');
insert into ALBUM_FEEDBACK (ID, FEEDBACK, DATE) values (3, 'NORM TAKOY ALBUM3', '2019-05-03 12:00:03');
insert into ALBUM_FEEDBACK (ID, FEEDBACK, DATE) values (4, 'NORM TAKOY ALBUM4', '2019-05-03 12:00:03');
insert into ALBUM_FEEDBACK (ID, FEEDBACK, DATE) values (5, 'NORM TAKOY ALBUM5', '2019-05-03 12:00:03');
insert into ALBUM_FEEDBACK (ID, FEEDBACK, DATE) values (6, 'NORM TAKOY ALBUM6', '2019-05-03 12:00:03');
insert into ALBUM_FEEDBACK (ID, FEEDBACK, DATE) values (7, 'NORM TAKOY ALBUM7', '2019-05-03 12:00:03');
insert into ALBUM_FEEDBACK (ID, FEEDBACK, DATE) values (8, 'NORM TAKOY ALBUM8', '2019-05-03 12:00:03');
insert into ALBUM_FEEDBACK (ID, FEEDBACK, DATE) values (9, 'NORM TAKOY ALBUM9', '2019-05-03 12:00:03');

insert into COMPOSITION (PRICE, NAME, ALBUM_ID, YEAR) values (1, 'Vasilisa', 1, 2018);
insert into COMPOSITION (PRICE, NAME, ALBUM_ID, YEAR) values (1, 'Vse OK', 2, 2018);
insert into COMPOSITION (PRICE, NAME, ALBUM_ID, YEAR) values (1, 'Hype', 3, 2018);
insert into COMPOSITION (PRICE, NAME, ALBUM_ID, YEAR) values (1, 'Space', 4, 2018);
insert into COMPOSITION (PRICE, NAME, ALBUM_ID, YEAR) values (1, 'Dor', 4, 2018);
insert into COMPOSITION (PRICE, NAME, ALBUM_ID, YEAR) values (1, 'Mom', 5, 2018);
insert into COMPOSITION (PRICE, NAME, ALBUM_ID, YEAR) values (1, 'Home', 6, 2018);
insert into COMPOSITION (PRICE, NAME, ALBUM_ID, YEAR) values (1, 'LSD', 7, 2018);
insert into COMPOSITION (PRICE, NAME, ALBUM_ID, YEAR) values (1, 'Fire', 7, 2018);
insert into COMPOSITION (PRICE, NAME, ALBUM_ID, YEAR) values (1, 'OLDUST', 5, 2018);

insert into USER_COMPOSITION (USER_ID, COMPOSITION_ID) values (1, 1);
insert into USER_COMPOSITION (USER_ID, COMPOSITION_ID) values (1, 2);
insert into USER_COMPOSITION (USER_ID, COMPOSITION_ID) values (1, 3);
insert into USER_COMPOSITION (USER_ID, COMPOSITION_ID) values (1, 4);
insert into USER_COMPOSITION (USER_ID, COMPOSITION_ID) values (1, 5);
insert into USER_COMPOSITION (USER_ID, COMPOSITION_ID) values (2, 1);
insert into USER_COMPOSITION (USER_ID, COMPOSITION_ID) values (2, 2);
insert into USER_COMPOSITION (USER_ID, COMPOSITION_ID) values (2, 3);
insert into USER_COMPOSITION (USER_ID, COMPOSITION_ID) values (3, 1);
insert into USER_COMPOSITION (USER_ID, COMPOSITION_ID) values (3, 3);

insert into COMPOSITION_FEEDBACK (ID, FEEDBACK, DATE) values (1, 'NORM TAKAYA PESNYA1', '2009-06-04 19:14:20');
insert into COMPOSITION_FEEDBACK (ID, FEEDBACK, DATE) values (2, 'NORM TAKAYA PESNYA2', '2009-06-04 19:14:20');
insert into COMPOSITION_FEEDBACK (ID, FEEDBACK, DATE) values (3, 'NORM TAKAYA PESNYA3', '2009-06-04 19:14:20');
insert into COMPOSITION_FEEDBACK (ID, FEEDBACK, DATE) values (4, 'NORM TAKAYA PESNYA4', '2009-06-04 19:14:20');
insert into COMPOSITION_FEEDBACK (ID, FEEDBACK, DATE) values (5, 'NORM TAKAYA PESNYA5', '2009-06-04 19:14:20');
insert into COMPOSITION_FEEDBACK (ID, FEEDBACK, DATE) values (6, 'NORM TAKAYA PESNYA6', '2009-06-04 19:14:20');
insert into COMPOSITION_FEEDBACK (ID, FEEDBACK, DATE) values (7, 'NORM TAKAYA PESNYA7', '2009-06-04 19:14:20');
insert into COMPOSITION_FEEDBACK (ID, FEEDBACK, DATE) values (8, 'NORM TAKAYA PESNYA8', '2009-06-04 19:14:20');
insert into COMPOSITION_FEEDBACK (ID, FEEDBACK, DATE) values (9, 'NORM TAKAYA PESNYA9', '2009-06-04 19:14:20');

insert into MUSIC_SELECTION (SELECTION_ID, COMPOSITION_ID) values (1, 1);
insert into MUSIC_SELECTION (SELECTION_ID, COMPOSITION_ID) values (1, 2);
insert into MUSIC_SELECTION (SELECTION_ID, COMPOSITION_ID) values (1, 3);
insert into MUSIC_SELECTION (SELECTION_ID, COMPOSITION_ID) values (1, 4);
insert into MUSIC_SELECTION (SELECTION_ID, COMPOSITION_ID) values (1, 5);
insert into MUSIC_SELECTION (SELECTION_ID, COMPOSITION_ID) values (1, 6);
insert into MUSIC_SELECTION (SELECTION_ID, COMPOSITION_ID) values (1, 7);
insert into MUSIC_SELECTION (SELECTION_ID, COMPOSITION_ID) values (1, 8);
insert into MUSIC_SELECTION (SELECTION_ID, COMPOSITION_ID) values (1, 9);
insert into MUSIC_SELECTION (SELECTION_ID, COMPOSITION_ID) values (1, 10);

insert into MUSIC_SELECTION (SELECTION_ID, COMPOSITION_ID) values (2, 1);
insert into MUSIC_SELECTION (SELECTION_ID, COMPOSITION_ID) values (2, 2);
insert into MUSIC_SELECTION (SELECTION_ID, COMPOSITION_ID) values (2, 3);
insert into MUSIC_SELECTION (SELECTION_ID, COMPOSITION_ID) values (2, 4);
insert into MUSIC_SELECTION (SELECTION_ID, COMPOSITION_ID) values (2, 5);
insert into MUSIC_SELECTION (SELECTION_ID, COMPOSITION_ID) values (2, 6);
insert into MUSIC_SELECTION (SELECTION_ID, COMPOSITION_ID) values (2, 7);
insert into MUSIC_SELECTION (SELECTION_ID, COMPOSITION_ID) values (2, 8);
insert into MUSIC_SELECTION (SELECTION_ID, COMPOSITION_ID) values (2, 9);
insert into MUSIC_SELECTION (SELECTION_ID, COMPOSITION_ID) values (2, 10);

insert into MUSIC_SELECTION (SELECTION_ID, COMPOSITION_ID) values (3, 1);
insert into MUSIC_SELECTION (SELECTION_ID, COMPOSITION_ID) values (3, 2);
insert into MUSIC_SELECTION (SELECTION_ID, COMPOSITION_ID) values (3, 3);
insert into MUSIC_SELECTION (SELECTION_ID, COMPOSITION_ID) values (3, 4);
insert into MUSIC_SELECTION (SELECTION_ID, COMPOSITION_ID) values (3, 5);
insert into MUSIC_SELECTION (SELECTION_ID, COMPOSITION_ID) values (3, 6);
insert into MUSIC_SELECTION (SELECTION_ID, COMPOSITION_ID) values (3, 7);
insert into MUSIC_SELECTION (SELECTION_ID, COMPOSITION_ID) values (3, 8);
insert into MUSIC_SELECTION (SELECTION_ID, COMPOSITION_ID) values (3, 9);
insert into MUSIC_SELECTION (SELECTION_ID, COMPOSITION_ID) values (3, 10);

insert into MUSIC_SELECTION (SELECTION_ID, COMPOSITION_ID) values (4, 1);
insert into MUSIC_SELECTION (SELECTION_ID, COMPOSITION_ID) values (4, 2);
insert into MUSIC_SELECTION (SELECTION_ID, COMPOSITION_ID) values (4, 3);
insert into MUSIC_SELECTION (SELECTION_ID, COMPOSITION_ID) values (4, 4);
insert into MUSIC_SELECTION (SELECTION_ID, COMPOSITION_ID) values (4, 5);
insert into MUSIC_SELECTION (SELECTION_ID, COMPOSITION_ID) values (4, 6);
insert into MUSIC_SELECTION (SELECTION_ID, COMPOSITION_ID) values (4, 7);
insert into MUSIC_SELECTION (SELECTION_ID, COMPOSITION_ID) values (4, 8);
insert into MUSIC_SELECTION (SELECTION_ID, COMPOSITION_ID) values (4, 9);
insert into MUSIC_SELECTION (SELECTION_ID, COMPOSITION_ID) values (4, 10);

insert into MUSIC_SELECTION (SELECTION_ID, COMPOSITION_ID) values (5, 1);
insert into MUSIC_SELECTION (SELECTION_ID, COMPOSITION_ID) values (5, 2);
insert into MUSIC_SELECTION (SELECTION_ID, COMPOSITION_ID) values (5, 3);
insert into MUSIC_SELECTION (SELECTION_ID, COMPOSITION_ID) values (5, 4);
insert into MUSIC_SELECTION (SELECTION_ID, COMPOSITION_ID) values (5, 5);
insert into MUSIC_SELECTION (SELECTION_ID, COMPOSITION_ID) values (5, 6);
insert into MUSIC_SELECTION (SELECTION_ID, COMPOSITION_ID) values (5, 7);
insert into MUSIC_SELECTION (SELECTION_ID, COMPOSITION_ID) values (5, 8);
insert into MUSIC_SELECTION (SELECTION_ID, COMPOSITION_ID) values (5, 9);
insert into MUSIC_SELECTION (SELECTION_ID, COMPOSITION_ID) values (5, 10);

insert into MUSIC_SELECTION (SELECTION_ID, COMPOSITION_ID) values (6, 1);
insert into MUSIC_SELECTION (SELECTION_ID, COMPOSITION_ID) values (6, 2);
insert into MUSIC_SELECTION (SELECTION_ID, COMPOSITION_ID) values (6, 3);
insert into MUSIC_SELECTION (SELECTION_ID, COMPOSITION_ID) values (6, 4);
insert into MUSIC_SELECTION (SELECTION_ID, COMPOSITION_ID) values (6, 5);
insert into MUSIC_SELECTION (SELECTION_ID, COMPOSITION_ID) values (6, 6);
insert into MUSIC_SELECTION (SELECTION_ID, COMPOSITION_ID) values (6, 7);
insert into MUSIC_SELECTION (SELECTION_ID, COMPOSITION_ID) values (6, 8);
insert into MUSIC_SELECTION (SELECTION_ID, COMPOSITION_ID) values (6, 9);
insert into MUSIC_SELECTION (SELECTION_ID, COMPOSITION_ID) values (6, 10);

insert into MUSIC_SELECTION (SELECTION_ID, COMPOSITION_ID) values (7, 1);
insert into MUSIC_SELECTION (SELECTION_ID, COMPOSITION_ID) values (7, 2);
insert into MUSIC_SELECTION (SELECTION_ID, COMPOSITION_ID) values (7, 3);
insert into MUSIC_SELECTION (SELECTION_ID, COMPOSITION_ID) values (7, 4);
insert into MUSIC_SELECTION (SELECTION_ID, COMPOSITION_ID) values (7, 5);
insert into MUSIC_SELECTION (SELECTION_ID, COMPOSITION_ID) values (7, 6);
insert into MUSIC_SELECTION (SELECTION_ID, COMPOSITION_ID) values (7, 7);
insert into MUSIC_SELECTION (SELECTION_ID, COMPOSITION_ID) values (7, 8);
insert into MUSIC_SELECTION (SELECTION_ID, COMPOSITION_ID) values (7, 9);
insert into MUSIC_SELECTION (SELECTION_ID, COMPOSITION_ID) values (7, 10);

insert into MUSIC_SELECTION (SELECTION_ID, COMPOSITION_ID) values (8, 1);
insert into MUSIC_SELECTION (SELECTION_ID, COMPOSITION_ID) values (8, 2);
insert into MUSIC_SELECTION (SELECTION_ID, COMPOSITION_ID) values (8, 3);
insert into MUSIC_SELECTION (SELECTION_ID, COMPOSITION_ID) values (8, 4);
insert into MUSIC_SELECTION (SELECTION_ID, COMPOSITION_ID) values (8, 5);
insert into MUSIC_SELECTION (SELECTION_ID, COMPOSITION_ID) values (8, 6);
insert into MUSIC_SELECTION (SELECTION_ID, COMPOSITION_ID) values (8, 7);
insert into MUSIC_SELECTION (SELECTION_ID, COMPOSITION_ID) values (8, 8);
insert into MUSIC_SELECTION (SELECTION_ID, COMPOSITION_ID) values (8, 9);
insert into MUSIC_SELECTION (SELECTION_ID, COMPOSITION_ID) values (8, 10);

insert into MUSIC_SELECTION (SELECTION_ID, COMPOSITION_ID) values (9, 1);
insert into MUSIC_SELECTION (SELECTION_ID, COMPOSITION_ID) values (9, 2);
insert into MUSIC_SELECTION (SELECTION_ID, COMPOSITION_ID) values (9, 3);
insert into MUSIC_SELECTION (SELECTION_ID, COMPOSITION_ID) values (9, 4);
insert into MUSIC_SELECTION (SELECTION_ID, COMPOSITION_ID) values (9, 5);
insert into MUSIC_SELECTION (SELECTION_ID, COMPOSITION_ID) values (9, 6);
insert into MUSIC_SELECTION (SELECTION_ID, COMPOSITION_ID) values (9, 7);
insert into MUSIC_SELECTION (SELECTION_ID, COMPOSITION_ID) values (9, 8);
insert into MUSIC_SELECTION (SELECTION_ID, COMPOSITION_ID) values (9, 9);
insert into MUSIC_SELECTION (SELECTION_ID, COMPOSITION_ID) values (9, 10);

insert into MUSIC_SELECTION (SELECTION_ID, COMPOSITION_ID) values (10, 1);
insert into MUSIC_SELECTION (SELECTION_ID, COMPOSITION_ID) values (10, 2);
insert into MUSIC_SELECTION (SELECTION_ID, COMPOSITION_ID) values (10, 3);
insert into MUSIC_SELECTION (SELECTION_ID, COMPOSITION_ID) values (10, 4);
insert into MUSIC_SELECTION (SELECTION_ID, COMPOSITION_ID) values (10, 5);
insert into MUSIC_SELECTION (SELECTION_ID, COMPOSITION_ID) values (10, 6);
insert into MUSIC_SELECTION (SELECTION_ID, COMPOSITION_ID) values (10, 7);
insert into MUSIC_SELECTION (SELECTION_ID, COMPOSITION_ID) values (10, 8);
insert into MUSIC_SELECTION (SELECTION_ID, COMPOSITION_ID) values (10, 9);
insert into MUSIC_SELECTION (SELECTION_ID, COMPOSITION_ID) values (10, 10);

insert into USER_MUSIC_SELECTION (USER_ID, SELECTION_ID) values (1, 10);
insert into USER_MUSIC_SELECTION (USER_ID, SELECTION_ID) values (2, 9);
insert into USER_MUSIC_SELECTION (USER_ID, SELECTION_ID) values (4, 7);
insert into USER_MUSIC_SELECTION (USER_ID, SELECTION_ID) values (5, 6);
insert into USER_MUSIC_SELECTION (USER_ID, SELECTION_ID) values (6, 5);
insert into USER_MUSIC_SELECTION (USER_ID, SELECTION_ID) values (7, 4);
insert into USER_MUSIC_SELECTION (USER_ID, SELECTION_ID) values (8, 3);
insert into USER_MUSIC_SELECTION (USER_ID, SELECTION_ID) values (9, 2);
insert into USER_MUSIC_SELECTION (USER_ID, SELECTION_ID) values (3, 8);
insert into USER_MUSIC_SELECTION (USER_ID, SELECTION_ID) values (10, 1);
insert into USER_MUSIC_SELECTION (USER_ID, SELECTION_ID) values (1, 10);
insert into USER_MUSIC_SELECTION (USER_ID, SELECTION_ID) values (2, 9);
insert into USER_MUSIC_SELECTION (USER_ID, SELECTION_ID) values (3, 8);
insert into USER_MUSIC_SELECTION (USER_ID, SELECTION_ID) values (4, 7);
insert into USER_MUSIC_SELECTION (USER_ID, SELECTION_ID) values (5, 6);
insert into USER_MUSIC_SELECTION (USER_ID, SELECTION_ID) values (6, 5);
insert into USER_MUSIC_SELECTION (USER_ID, SELECTION_ID) values (7, 4);
insert into USER_MUSIC_SELECTION (USER_ID, SELECTION_ID) values (8, 3);
insert into USER_MUSIC_SELECTION (USER_ID, SELECTION_ID) values (9, 2);
insert into USER_MUSIC_SELECTION (USER_ID, SELECTION_ID) values (10, 1);

insert into MUSIC_SELECTION_FEEDBACK (ID, FEEDBACK, DATE) values (1, 'NORM TAKAYA WIBORKA1', '2011-11-11 11:11:11');
insert into MUSIC_SELECTION_FEEDBACK (ID, FEEDBACK, DATE) values (2, 'NORM TAKAYA WIBORKA2', '2011-11-11 11:11:11');
insert into MUSIC_SELECTION_FEEDBACK (ID, FEEDBACK, DATE) values (3, 'NORM TAKAYA WIBORKA3', '2011-11-11 11:11:11');
insert into MUSIC_SELECTION_FEEDBACK (ID, FEEDBACK, DATE) values (4, 'NORM TAKAYA WIBORKA4', '2011-11-11 11:11:11');
insert into MUSIC_SELECTION_FEEDBACK (ID, FEEDBACK, DATE) values (5, 'NORM TAKAYA WIBORKA5', '2011-11-11 11:11:11');
insert into MUSIC_SELECTION_FEEDBACK (ID, FEEDBACK, DATE) values (6, 'NORM TAKAYA WIBORKA6', '2011-11-11 11:11:11');
insert into MUSIC_SELECTION_FEEDBACK (ID, FEEDBACK, DATE) values (7, 'NORM TAKAYA WIBORKA7', '2011-11-11 11:11:11');
insert into MUSIC_SELECTION_FEEDBACK (ID, FEEDBACK, DATE) values (8, 'NORM TAKAYA WIBORKA8', '2011-11-11 11:11:11');
insert into MUSIC_SELECTION_FEEDBACK (ID, FEEDBACK, DATE) values (9, 'NORM TAKAYA WIBORKA9', '2011-11-11 11:11:11');

insert into SESSION_DATA (USER_ID, SESSION_HASH) values (2, '$2a$10$RnNteqUTXvYhFCxa1caSXOhMTtD5N3DEEDwG8QvODU/pSU4u7TOM.');
insert into SESSION_DATA (USER_ID, SESSION_HASH) values (3, '$2a$10$pUgtn0xtWs6MbK7gPkfht.T/ireErbAH4Sw0ozJg35E5ltmYrIWyK');
insert into SESSION_DATA (USER_ID, SESSION_HASH) values (4, '$2a$10$/Zz.W/Um0B3f/RbHY9QwWOjq5GzG7iujJyhR4h6xoh4uJB0bQbaha');
insert into SESSION_DATA (USER_ID, SESSION_HASH) values (5, '$2a$10$WSrOPByualfasFk3vEIQQ.BI5Wh8HI9NmrzjUZY0ddsyYkwpu7ONe');
insert into SESSION_DATA (USER_ID, SESSION_HASH) values (6, '$2a$10$afMBsYbIzXe1GMrep2obTOzJiI1s.BfsjGSjRKie0mMQkeuc4eoKC');
insert into SESSION_DATA (USER_ID, SESSION_HASH) values (7, '$2a$10$IXD.Js6q/aOzNSLgvbZYzujairkQBXg1QPdkts8LyxiQpdhgweawW');
insert into SESSION_DATA (USER_ID, SESSION_HASH) values (8, '$2a$10$v2Y7HzMjIlAEduRzhKB9tuWPwTqrs1M80dlJXJgdyq.H/NuS.WJnK');
insert into SESSION_DATA (USER_ID, SESSION_HASH) values (9, '$2a$10$2I6GdmQC93UTT6jBYiZ4TeDwKeo2/5az6QUkIDGfKOfBIZ/eJu8P2');
insert into SESSION_DATA (USER_ID, SESSION_HASH) values (10, '$2a$10$Qj06YHqatf2Vz.rMkJ5d8e5I0Pcm3ojLAu5hnlxFLsur4t5mtIqfS');