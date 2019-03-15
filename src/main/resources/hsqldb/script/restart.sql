use audio_store;

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
drop table if exists music_selection_info;
drop table if exists composition;
drop table if exists album;
drop table if exists genre;
drop table if exists bonus;
drop table if exists author;

create table author
(
  id          INTEGER auto_increment primary key,
  first_name  varchar(50) NOT NULL,
  second_name varchar(50) NOT NULL,
  pseudonim   varchar(50) NOT NULL unique
);

create table bonus
(
  id    INTEGER auto_increment primary key,
  bonus varchar(500) NOT NULL
);

create table genre
(
  id    INTEGER auto_increment primary key,
  genre varchar(50) NOT NULL unique
);

create table album
(
  id          INTEGER auto_increment primary key,
  price       int             NOT NULL,
  name        varchar(50)     NOT NULL,
  description varchar(500)    NOT NULL,
  author_id   int default '1' NOT NULL,
  genre_id    int,
  year        int             NOT NULL,
  constraint album_author_id_fk foreign key (author_id) references author (id) on DELETE cascade,
  constraint album_genre_fk foreign key (genre_id) references genre (id) on DELETE cascade
);

create table composition
(
  id       INTEGER auto_increment primary key,
  price    int         NOT NULL,
  name     varchar(50) NOT NULL,
  album_id int         NOT NULL,
  year     int,
  constraint composition_album_fk foreign key (album_id) references album (id) on DELETE cascade
);

create table music_selection_info
(
  id INTEGER auto_increment primary key,
  price        int not null,
  name         varchar(50),
  description  varchar(500)
);

create table music_selection
(
  id             INTEGER auto_increment primary key,
  selection_id   int NOT NULL,
  composition_id int NOT NULL,
  constraint music_selection_composition_fk foreign key (composition_id) references composition (id) on DELETE cascade,
  constraint music_selection_music_selection_info_fk foreign key (selection_id) references music_selection_info (id) on DELETE cascade
);

create table music_selection_feedback
(
  id       int          NOT NULL,
  feedback varchar(500) NOT NULL,
  date     timestamp    NOT NULL,
  constraint music_selection_feedback_music_selection_fk foreign key (id) references music_selection (id) on delete cascade
);

create table role
(
  id   INTEGER auto_increment primary key,
  role VARCHAR(50) NOT NULL
);

create table status
(
  id     INTEGER auto_increment primary key,
  status varchar(50) NOT NULL
);

create table users
(
  id            INTEGER auto_increment
    primary key,
  register_date timestamp     NOT NULL,
  login         varchar(40)   NOT NULL unique,
  password      varchar(60)   NOT NULL,
  first_name    varchar(50)   NOT NULL,
  second_name   varchar(50)   NOT NULL,
  email         varchar(50)   NOT NULL unique,
  balance       int           NOT NULL,
  sale          int           NOT NULL,
  role_id       int,
  status_id     int,
  public_key    VARCHAR(392) NOT NULL,
  private_key   VARCHAR(1624)  NOT NULL,
  constraint user_role_fk foreign key (role_id) references role (id) on DELETE cascade,
  constraint user_status_fk foreign key (status_id) references status (id) ON DELETE CASCADE
);

create table user_album
(
  id       INTEGER auto_increment
    primary key,
  user_id  int NOT NULL,
  album_id int NOT NULL,
  constraint user_album_user_fk foreign key (user_id) references users (id) ON DELETE CASCADE,
  constraint user_album_album_fk foreign key (album_id) references album (id) ON DELETE CASCADE
);

create table album_feedback
(
  id       int          NOT NULL,
  feedback varchar(500) NOT NULL,
  date     date         NOT NULL,
  constraint album_feedback_user_album_fk foreign key (id) references user_album (id) on delete cascade
);

create table user_bonus
(
  id       INTEGER auto_increment
    primary key,
  user_id  int NOT NULL,
  bonus_id int NOT NULL,
  constraint user_id_fk foreign key (user_id) references users (id) ON DELETE CASCADE,
  constraint bonus_id_fk foreign key (bonus_id) references bonus (id) ON DELETE CASCADE
);

create table user_composition
(
  id             INTEGER auto_increment
    primary key,
  user_id        int NOT NULL,
  composition_id int NOT NULL,
  constraint user_composition_composition_fk foreign key (composition_id) references composition (id) on DELETE cascade,
  constraint user_composition_user_fk foreign key (user_id) references users (id) on DELETE cascade
);

create table composition_feedback
(
  id       INTEGER      NOT NULL,
  feedback varchar(500) NOT NULL,
  date     timestamp    NOT NULL,
  constraint composition_feedback_user_composition_fk foreign key (id) references user_composition (id) on delete cascade
);

create table user_music_selection
(
  id           INTEGER auto_increment primary key,
  user_id      int NOT NULL,
  selection_id int NOT NULL
);

insert into status (status)
values ('ACTIVE');
insert into status (status)
values ('NOT CONFIRMED');
insert into status (status)
values ('DELETE');

insert into role (role)
values ('ADMIN');
insert into role (role)
values ('CLIENT');

insert into bonus (BONUS)
values ('sale 40%');
insert into bonus (BONUS)
values ('sale 50%');
insert into bonus (BONUS)
values ('sale 60%');
insert into bonus (BONUS)
values ('sale 70%');
insert into bonus (BONUS)
values ('sale 80%');
insert into bonus (BONUS)
values ('sale 90%');
insert into bonus (BONUS)
values ('free song');
insert into bonus (BONUS)
values ('free album');
insert into bonus (BONUS)
values ('free music selection');
insert into bonus (BONUS)
values ('unlim');

insert into author (FIRST_NAME, SECOND_NAME, PSEUDONIM)
values ('UNKNOWN', 'UNKNOWN', 'UNKNOWN');
insert into author (FIRST_NAME, SECOND_NAME, PSEUDONIM)
values ('ALEXANDR', 'ZAPOROZHTSEV', 'GRAND-MASTER');
insert into author (FIRST_NAME, SECOND_NAME, PSEUDONIM)
values ('IOSIPH', 'STALIN', 'SLAVA KPSS');
insert into author (FIRST_NAME, SECOND_NAME, PSEUDONIM)
values ('LOSKAH', 'ANTON', 'MORGENSTERN');
insert into author (FIRST_NAME, SECOND_NAME, PSEUDONIM)
values ('DVERI', 'ANON', 'STONE');
insert into author (FIRST_NAME, SECOND_NAME, PSEUDONIM)
values ('SPICE', 'SNAF', 'LSP');
insert into author (FIRST_NAME, SECOND_NAME, PSEUDONIM)
values ('ANDREY', 'KIRILL', 'FLOVERS');
insert into author (FIRST_NAME, SECOND_NAME, PSEUDONIM)
values ('MALOY', 'DILLER', 'TWOBOYS');
insert into author (FIRST_NAME, SECOND_NAME, PSEUDONIM)
values ('MALOY1', 'DILLER', 'TWOBOYS1');
insert into author (FIRST_NAME, SECOND_NAME, PSEUDONIM)
values ('MALOY2', 'DILLER1', 'TWOBOYS2');

insert into users (LOGIN, PASSWORD, FIRST_NAME, SECOND_NAME, REGISTER_DATE, SALE, BALANCE, ROLE_ID, STATUS_ID, EMAIL,
                   PUBLIC_KEY, PRIVATE_KEY)
values ('MRMRMR11', '$2a$10$PyrB3GSKF5wo2zXpTh1SC.GLx7c7f92oy3rxzAmYLeAOriYIzuIy.', 'FIRST NAME', 'SECOND NAME',
        '2008-08-08 20:08:08', 10, 1000, 1, 1, 'ALIEX.KOT1@yandex.by',
        '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');
insert into users (LOGIN, PASSWORD, FIRST_NAME, SECOND_NAME, REGISTER_DATE, SALE, BALANCE, ROLE_ID, STATUS_ID, EMAIL,
                   PUBLIC_KEY, PRIVATE_KEY)
values ('MRMRMR12', '$2a$10$mlAlOP7Qwq1nEMRdqeU87OjWKjmV7/cxHTdeeJT5OxFBB4eHJ0uGS', 'FIRST NAME', 'SECOND NAME',
        '2008-08-08 20:08:08', 10, 1000, 1, 1, 'ALIEX.KOT2@yandex.by',
        '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');
insert into users (LOGIN, PASSWORD, FIRST_NAME, SECOND_NAME, REGISTER_DATE, SALE, BALANCE, ROLE_ID, STATUS_ID, EMAIL,
                   PUBLIC_KEY, PRIVATE_KEY)
values ('MRMRMR13', '$2a$10$tfIFXPz6VxdRi5KOGxB9runD0sHVI2dSmrOCvCzcZRZoXH.hZBL8.', 'FIRST NAME', 'SECOND NAME',
        '2008-08-08 20:08:08', 10, 1000, 1, 1, 'ALIEX.KOT3@yandex.by',
        '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');
insert into users (LOGIN, PASSWORD, FIRST_NAME, SECOND_NAME, REGISTER_DATE, SALE, BALANCE, ROLE_ID, STATUS_ID, EMAIL,
                   PUBLIC_KEY, PRIVATE_KEY)
values ('MRMRMR14', '$2a$10$YCsHtEdLw2djrLQOzhUP.eFzjnFyeyr.9dDr2ePQ.j8gP3OEgH0Uy', 'FIRST NAME', 'SECOND NAME',
        '2008-08-08 20:08:08', 10, 1000, 1, 1, 'ALIEX.KOT4@yandex.by',
        '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');
insert into users (LOGIN, PASSWORD, FIRST_NAME, SECOND_NAME, REGISTER_DATE, SALE, BALANCE, ROLE_ID, STATUS_ID, EMAIL,
                   PUBLIC_KEY, PRIVATE_KEY)
values ('MRMRMR15', '$2a$10$fxOSMDJhcZc7xEBkAjfmG.Dq1rV04h0qTS4UpoxWrvEDOa8UyFsvG', 'FIRST NAME', 'SECOND NAME',
        '2008-08-08 20:08:08', 10, 1000, 1, 1, 'ALIEX.KOT5@yandex.by',
        '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');
insert into users (LOGIN, PASSWORD, FIRST_NAME, SECOND_NAME, REGISTER_DATE, SALE, BALANCE, ROLE_ID, STATUS_ID, EMAIL,
                   PUBLIC_KEY, PRIVATE_KEY)
values ('MRMRMR16', '$2a$10$SiA79Eiouun12HvmtoYf7u6c8j/mx/SFHlNm0NOMmVkYI7RV9X5gu', 'FIRST NAME', 'SECOND NAME',
        '2008-08-08 20:08:08', 10, 1000, 1, 1, 'ALIEX.KOT6@yandex.by',
        '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');
insert into users (LOGIN, PASSWORD, FIRST_NAME, SECOND_NAME, REGISTER_DATE, SALE, BALANCE, ROLE_ID, STATUS_ID, EMAIL,
                   PUBLIC_KEY, PRIVATE_KEY)
values ('MRMRMR17', '$2a$10$kVtSXe2CxB/LxzxFIi4d7OpaTtmv.OjNke9v38NtAI8MgfjiEAnT.', 'FIRST NAME', 'SECOND NAME',
        '2008-08-08 20:08:08', 10, 1000, 1, 1, 'ALIEX.KOT7@yandex.by',
        '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');
insert into users (LOGIN, PASSWORD, FIRST_NAME, SECOND_NAME, REGISTER_DATE, SALE, BALANCE, ROLE_ID, STATUS_ID, EMAIL,
                   PUBLIC_KEY, PRIVATE_KEY)
values ('MRMRMR18', '$2a$10$Xef0CHEFBJnLaYWrYl2RE.8zGBJqK4pCKxAwirM3ku28vPxvecIFu', 'FIRST NAME', 'SECOND NAME',
        '2008-08-08 20:08:08', 10, 1000, 1, 1, 'ALIEX.KOT8@yandex.by',
        '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');
insert into users (LOGIN, PASSWORD, FIRST_NAME, SECOND_NAME, REGISTER_DATE, SALE, BALANCE, ROLE_ID, STATUS_ID, EMAIL,
                   PUBLIC_KEY, PRIVATE_KEY)
values ('MRMRMR19', '$2a$10$FdNnLot6QA2mIj/wWFR6uu4a.Fvtrf3Y9VXdIMUOfLq9R5EUFFjxS', 'FIRST NAME', 'SECOND NAME',
        '2008-08-08 20:08:08', 10, 1000, 1, 1, 'ALIEX.KOT9@yandex.by',
        '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');
insert into users (LOGIN, PASSWORD, FIRST_NAME, SECOND_NAME, REGISTER_DATE, SALE, BALANCE, ROLE_ID, STATUS_ID, EMAIL,
                   PUBLIC_KEY, PRIVATE_KEY)
values ('MRMRMR10', '$2a$10$nJzOa80/gZFdfBjEsgHlq.DlCAuV9gtluYPTkf9bs8XZj2EiIBm1G', 'FIRST NAME', 'SECOND NAME',
        '2008-08-08 20:08:08', 10, 1000, 1, 1, 'ALIEX.KOT10@yandex.by',
        'MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAnmSCiFnYpFHkVFJZI9LxGCcdLsF5eOI/8VKIxi1aCVkg1T0RAgYdZyIuMnT6gXVRyYVbNA0OVuKHkusdEZiDs5XVvc1yRcSCKuPy+wx/TsQDwwPu8HV27sRBOaNF2h5XksXTZzzQCup9OKHzlq5yDnI5m3o9s/sLepEm7bPH1MN1w0kAli74k9ZLabLtqoVVaW7BLS+LFuMUGG4G0aphEPlW1zgIuPVf8n8N+VFXhxsTzWkcnZ6if12DPtleAPZ5WXjmTwrWg2h4nkBSMh0tG2ylDEpeTNPgk1KEco8qDhfdNA4KhBWQ9QoD0AMdicYePgRa8Y+RXBqtocgxFVNAWwIDAQAB',
        'MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCeZIKIWdikUeRUUlkj0vEYJx0uwXl44j/xUojGLVoJWSDVPRECBh1nIi4ydPqBdVHJhVs0DQ5W4oeS6x0RmIOzldW9zXJFxIIq4/L7DH9OxAPDA+7wdXbuxEE5o0XaHleSxdNnPNAK6n04ofOWrnIOcjmbej2z+wt6kSbts8fUw3XDSQCWLviT1ktpsu2qhVVpbsEtL4sW4xQYbgbRqmEQ+VbXOAi49V/yfw35UVeHGxPNaRydnqJ/XYM+2V4A9nlZeOZPCtaDaHieQFIyHS0bbKUMSl5M0+CTUoRyjyoOF900DgqEFZD1CgPQAx2Jxh4+BFrxj5FcGq2hyDEVU0BbAgMBAAECggEAYzYdtP5h/nr1BAQYpaHJNhG568FwLtG4r5W86jUaAmT7ddWsQ5JE9IaqXRx8lH5B3nJTcEUFSFLYDKSMLqtTQzeFDEAVZbHyvp7NYYawb0vD93ENRzysZF41ZL7qT/dVby/OfQnQXiYGjWSTyTw3YOkXQFXPNF75D6q/sYA8mP1dhC3SHxZEUa4vUW5FRz7RQrlUDlZ9aCZUtWQVKCzCfVxXYAko/nX7Qo38QXZMsW1juQjcQO3wr6BxfqA+tiKaXQSSFMk82lkQJmBcjNcnLWxd1EmWkQlrsClQziYN7FLzFDwrAWAvsxR2NDG7zL32tKRJl8/lwhuZk5iU4q/aMQKBgQDuGyiYoVuuU4P7xeIGsbN+Z9nrBZUYsiRrKlocIR1TxkR79fCwrOQSa0uT3BhhXOhcTowMdnhz9RYXclK+7DGOBYRUfXL5MGuPVLUfjExHnaFY46SpcD0iva1wodzIHLyzXQcdKeD6jSuTrfvrUVyJa5Sy5Wvmn82IbfI39CnOhQKBgQCqS8VqqEDwuSpUiBRcsPoZJrVo+/IhetXagqJJuq3jW7OQ0+3/DrsLS5lpFHz8c+Xtph+lcE/q4QgEEfZhRu0AyH5Skv9dSHlcFqoy7VXxBmJlKYlWpvtQ5snT/w1VEZeiYVPwDJ5SzxDueekP+ulaojLNGlYYqA/07RPr6cQ5XwKBgH7/N1DpR2gcnVnNerfNUgpKw91ptdNdOLV7+mHke6ryJhbj34OsQ11bowzzKIHNtxV8w4/mJGrcvvK0hFPW0LuKT8Fsw8ULQWNKb+yqvQi5l5Q8ntfMyJ7eAkL/4i1NvowAQ0oXyyp331U+SA6D79JP8hjU6w8wDsUEt1uGvEGtAoGAD0tq2Z9u5a/bq2flXEXQ0H13gYxPPM6JcmyVIsYPZAISaImAMI4kImmRx6w9ZXcLSYLICNXDd7bqP2NVm3sBQja/ka7+HGSr8adfdd92/oSyRZ+rZArvO17e9ZlMmz1PPxg6r66ScaGDRcfRNMw2NRuoMF+TJ/fAJyE7zs5Z/0UCgYB29N5rxv5DR8xEpRACH8qCvxgvKE5ux4MXjUnmQ+jL/bN8jTY5C/b1vYuRYXMLbZv9NQ+G6JpnwldlDg0AP2ieTVnSEWO+HH78+Gm8C9LZldGyakTkgDGmr6Yrilr5DWSz9T9cH21A2xPW0ws6MjJtEVO31y6HZ4AJ4jhlVPVHXg==');

insert into user_bonus (USER_ID, BONUS_ID)
values (1, 1);
insert into user_bonus (USER_ID, BONUS_ID)
values (2, 2);
insert into user_bonus (USER_ID, BONUS_ID)
values (3, 2);
insert into user_bonus (USER_ID, BONUS_ID)
values (4, 4);
insert into user_bonus (USER_ID, BONUS_ID)
values (5, 5);
insert into user_bonus (USER_ID, BONUS_ID)
values (7, 7);
insert into user_bonus (USER_ID, BONUS_ID)
values (8, 4);
insert into user_bonus (USER_ID, BONUS_ID)
values (9, 9);
insert into user_bonus (USER_ID, BONUS_ID)
values (6, 2);
insert into user_bonus (USER_ID, BONUS_ID)
values (6, 3);
insert into user_bonus (USER_ID, BONUS_ID)
values (10, 2);

insert into genre (GENRE)
values ('ROCK');
insert into genre (GENRE)
values ('HARD ROCK');
insert into genre (GENRE)
values ('DUBSTEP');
insert into genre (GENRE)
values ('RAP');
insert into genre (GENRE)
values ('HIPHOP');
insert into genre (GENRE)
values ('SQUAD');
insert into genre (GENRE)
values ('POP');
insert into genre (GENRE)
values ('K-POP');
insert into genre (GENRE)
values ('DJ');
insert into genre (GENRE)
values ('METALL');

insert into album (PRICE, NAME, DESCRIPTION, AUTHOR_ID, GENRE_ID, YEAR)
values (123, 'Back side of moon', 'Back side of moon', 1, 1, '1998');
insert into album (PRICE, NAME, DESCRIPTION, AUTHOR_ID, GENRE_ID, YEAR)
values (123, 'Gorgorod', 'Gorgorod', 2, 2, '1998');
insert into album (PRICE, NAME, DESCRIPTION, AUTHOR_ID, GENRE_ID, YEAR)
values (123, 'Downtown', 'Downtown', 3, 1, '1998');
insert into album (PRICE, NAME, DESCRIPTION, AUTHOR_ID, GENRE_ID, YEAR)
values (123, 'Some', 'Some', 2, 4, '1998');
insert into album (PRICE, NAME, DESCRIPTION, AUTHOR_ID, GENRE_ID, YEAR)
values (123, 'Downtown2', 'Downtown2', 5, 1, '1998');
insert into album (PRICE, NAME, DESCRIPTION, AUTHOR_ID, GENRE_ID, YEAR)
values (123, 'Back 3oon', 'Back 3oon', 6, 1, '1998');
insert into album (PRICE, NAME, DESCRIPTION, AUTHOR_ID, GENRE_ID, YEAR)
values (123, 'Gorgorod3', 'Gorgorod3', 7, 2, '1998');
insert into album (PRICE, NAME, DESCRIPTION, AUTHOR_ID, GENRE_ID, YEAR)
values (123, 'Downtown4', 'Downtown4', 8, 1, '1998');
insert into album (PRICE, NAME, DESCRIPTION, AUTHOR_ID, GENRE_ID, YEAR)
values (123, 'Downtown5', 'Downtown5', 9, 1, '1998');
insert into album (PRICE, NAME, DESCRIPTION, AUTHOR_ID, GENRE_ID, YEAR)
values (123, 'Downtown6', 'Downtown6', 1, 1, '1998');

insert into user_album (USER_ID, ALBUM_ID)
values (1, 3);
insert into user_album (USER_ID, ALBUM_ID)
values (1, 5);
insert into user_album (USER_ID, ALBUM_ID)
values (1, 4);
insert into user_album (USER_ID, ALBUM_ID)
values (2, 3);
insert into user_album (USER_ID, ALBUM_ID)
values (3, 4);
insert into user_album (USER_ID, ALBUM_ID)
values (4, 1);
insert into user_album (USER_ID, ALBUM_ID)
values (5, 2);
insert into user_album (USER_ID, ALBUM_ID)
values (6, 5);
insert into user_album (USER_ID, ALBUM_ID)
values (7, 8);
insert into user_album (USER_ID, ALBUM_ID)
values (8, 3);
insert into user_album (USER_ID, ALBUM_ID)
values (9, 4);
insert into user_album (USER_ID, ALBUM_ID)
values (9, 2);
insert into user_album (USER_ID, ALBUM_ID)
values (10, 1);

insert into album_feedback (ID, FEEDBACK, DATE)
values (1, 'NORM TAKOY ALBUM1', '2019-05-03 12:00:03');
insert into album_feedback (ID, FEEDBACK, DATE)
values (2, 'NORM TAKOY ALBUM2', '2019-05-03 12:00:03');
insert into album_feedback (ID, FEEDBACK, DATE)
values (3, 'NORM TAKOY ALBUM3', '2019-05-03 12:00:03');
insert into album_feedback (ID, FEEDBACK, DATE)
values (4, 'NORM TAKOY ALBUM4', '2019-05-03 12:00:03');
insert into album_feedback (ID, FEEDBACK, DATE)
values (5, 'NORM TAKOY ALBUM5', '2019-05-03 12:00:03');
insert into album_feedback (ID, FEEDBACK, DATE)
values (6, 'NORM TAKOY ALBUM6', '2019-05-03 12:00:03');
insert into album_feedback (ID, FEEDBACK, DATE)
values (7, 'NORM TAKOY ALBUM7', '2019-05-03 12:00:03');
insert into album_feedback (ID, FEEDBACK, DATE)
values (8, 'NORM TAKOY ALBUM8', '2019-05-03 12:00:03');
insert into album_feedback (ID, FEEDBACK, DATE)
values (9, 'NORM TAKOY ALBUM9', '2019-05-03 12:00:03');

insert into composition (PRICE, NAME, ALBUM_ID, YEAR)
values (1, 'Vasilisa', 1, 2018);
insert into composition (PRICE, NAME, ALBUM_ID, YEAR)
values (1, 'Vse OK', 2, 2018);
insert into composition (PRICE, NAME, ALBUM_ID, YEAR)
values (1, 'Hype', 3, 2018);
insert into composition (PRICE, NAME, ALBUM_ID, YEAR)
values (1, 'Space', 4, 2018);
insert into composition (PRICE, NAME, ALBUM_ID, YEAR)
values (1, 'Dor', 4, 2018);
insert into composition (PRICE, NAME, ALBUM_ID, YEAR)
values (1, 'Mom', 5, 2018);
insert into composition (PRICE, NAME, ALBUM_ID, YEAR)
values (1, 'Home', 6, 2018);
insert into composition (PRICE, NAME, ALBUM_ID, YEAR)
values (1, 'LSD', 7, 2018);
insert into composition (PRICE, NAME, ALBUM_ID, YEAR)
values (1, 'Fire', 7, 2018);
insert into composition (PRICE, NAME, ALBUM_ID, YEAR)
values (1, 'OLDUST', 5, 2018);
insert into composition (PRICE, NAME, ALBUM_ID, YEAR)
values (1, 'LSDBSD', 7, 2018);


insert into user_composition (USER_ID, COMPOSITION_ID)
values (1, 1);
insert into user_composition (USER_ID, COMPOSITION_ID)
values (2, 2);
insert into user_composition (USER_ID, COMPOSITION_ID)
values (3, 3);
insert into user_composition (USER_ID, COMPOSITION_ID)
values (4, 4);
insert into user_composition (USER_ID, COMPOSITION_ID)
values (5, 5);
insert into user_composition (USER_ID, COMPOSITION_ID)
values (6, 1);
insert into user_composition (USER_ID, COMPOSITION_ID)
values (7, 2);
insert into user_composition (USER_ID, COMPOSITION_ID)
values (8, 3);
insert into user_composition (USER_ID, COMPOSITION_ID)
values (9, 1);
insert into user_composition (USER_ID, COMPOSITION_ID)
values (10, 3);

insert into composition_feedback (ID, FEEDBACK, DATE)
values (1, 'NORM TAKAYA PESNYA1', '2009-06-04 19:14:20');
insert into composition_feedback (ID, FEEDBACK, DATE)
values (2, 'NORM TAKAYA PESNYA2', '2009-06-04 19:14:20');
insert into composition_feedback (ID, FEEDBACK, DATE)
values (3, 'NORM TAKAYA PESNYA3', '2009-06-04 19:14:20');
insert into composition_feedback (ID, FEEDBACK, DATE)
values (4, 'NORM TAKAYA PESNYA4', '2009-06-04 19:14:20');
insert into composition_feedback (ID, FEEDBACK, DATE)
values (5, 'NORM TAKAYA PESNYA5', '2009-06-04 19:14:20');
insert into composition_feedback (ID, FEEDBACK, DATE)
values (6, 'NORM TAKAYA PESNYA6', '2009-06-04 19:14:20');
insert into composition_feedback (ID, FEEDBACK, DATE)
values (7, 'NORM TAKAYA PESNYA7', '2009-06-04 19:14:20');
insert into composition_feedback (ID, FEEDBACK, DATE)
values (8, 'NORM TAKAYA PESNYA8', '2009-06-04 19:14:20');
insert into composition_feedback (ID, FEEDBACK, DATE)
values (9, 'NORM TAKAYA PESNYA9', '2009-06-04 19:14:20');

insert into music_selection_info (price, name, description)
VALUES (120, 'First', 'Some long description');
insert into music_selection_info (price, name, description)
VALUES (120, 'Second', 'Some long description');
insert into music_selection_info (price, name, description)
VALUES (120, 'Third', 'Some long description');
insert into music_selection_info (price, name, description)
VALUES (120, 'Fourth', 'Some long description');
insert into music_selection_info (price, name, description)
VALUES (120, 'Fiveth', 'Some long description');
insert into music_selection_info (price, name, description)
VALUES (120, 'Seventh', 'Some long description');
insert into music_selection_info (price, name, description)
VALUES (120, 'Eiggt', 'Some long description');
insert into music_selection_info (price, name, description)
VALUES (120, 'Nine', 'Some long description');
insert into music_selection_info (price, name, description)
VALUES (120, 'Ten', 'Some long description');
insert into music_selection_info (price, name, description)
VALUES (120, 'Addition', 'Some long description');

insert into music_selection (SELECTION_ID, COMPOSITION_ID)
values (1, 1);
insert into music_selection (SELECTION_ID, COMPOSITION_ID)
values (1, 2);
insert into music_selection (SELECTION_ID, COMPOSITION_ID)
values (1, 3);
insert into music_selection (SELECTION_ID, COMPOSITION_ID)
values (1, 4);
insert into music_selection (SELECTION_ID, COMPOSITION_ID)
values (1, 5);
insert into music_selection (SELECTION_ID, COMPOSITION_ID)
values (1, 6);
insert into music_selection (SELECTION_ID, COMPOSITION_ID)
values (1, 7);
insert into music_selection (SELECTION_ID, COMPOSITION_ID)
values (1, 8);
insert into music_selection (SELECTION_ID, COMPOSITION_ID)
values (1, 9);
insert into music_selection (SELECTION_ID, COMPOSITION_ID)
values (1, 10);

insert into music_selection (SELECTION_ID, COMPOSITION_ID)
values (2, 1);
insert into music_selection (SELECTION_ID, COMPOSITION_ID)
values (2, 2);
insert into music_selection (SELECTION_ID, COMPOSITION_ID)
values (2, 3);
insert into music_selection (SELECTION_ID, COMPOSITION_ID)
values (2, 4);
insert into music_selection (SELECTION_ID, COMPOSITION_ID)
values (2, 5);
insert into music_selection (SELECTION_ID, COMPOSITION_ID)
values (2, 6);
insert into music_selection (SELECTION_ID, COMPOSITION_ID)
values (2, 7);
insert into music_selection (SELECTION_ID, COMPOSITION_ID)
values (2, 8);
insert into music_selection (SELECTION_ID, COMPOSITION_ID)
values (2, 9);
insert into music_selection (SELECTION_ID, COMPOSITION_ID)
values (2, 10);

insert into music_selection (SELECTION_ID, COMPOSITION_ID)
values (3, 1);
insert into music_selection (SELECTION_ID, COMPOSITION_ID)
values (3, 2);
insert into music_selection (SELECTION_ID, COMPOSITION_ID)
values (3, 3);
insert into music_selection (SELECTION_ID, COMPOSITION_ID)
values (3, 4);
insert into music_selection (SELECTION_ID, COMPOSITION_ID)
values (3, 5);
insert into music_selection (SELECTION_ID, COMPOSITION_ID)
values (3, 6);
insert into music_selection (SELECTION_ID, COMPOSITION_ID)
values (3, 7);
insert into music_selection (SELECTION_ID, COMPOSITION_ID)
values (3, 8);
insert into music_selection (SELECTION_ID, COMPOSITION_ID)
values (3, 9);
insert into music_selection (SELECTION_ID, COMPOSITION_ID)
values (3, 10);

insert into music_selection (SELECTION_ID, COMPOSITION_ID)
values (4, 1);
insert into music_selection (SELECTION_ID, COMPOSITION_ID)
values (4, 2);
insert into music_selection (SELECTION_ID, COMPOSITION_ID)
values (4, 3);
insert into music_selection (SELECTION_ID, COMPOSITION_ID)
values (4, 4);
insert into music_selection (SELECTION_ID, COMPOSITION_ID)
values (4, 5);
insert into music_selection (SELECTION_ID, COMPOSITION_ID)
values (4, 6);
insert into music_selection (SELECTION_ID, COMPOSITION_ID)
values (4, 7);
insert into music_selection (SELECTION_ID, COMPOSITION_ID)
values (4, 8);
insert into music_selection (SELECTION_ID, COMPOSITION_ID)
values (4, 9);
insert into music_selection (SELECTION_ID, COMPOSITION_ID)
values (4, 10);

insert into music_selection (SELECTION_ID, COMPOSITION_ID)
values (5, 1);
insert into music_selection (SELECTION_ID, COMPOSITION_ID)
values (5, 2);
insert into music_selection (SELECTION_ID, COMPOSITION_ID)
values (5, 3);
insert into music_selection (SELECTION_ID, COMPOSITION_ID)
values (5, 4);
insert into music_selection (SELECTION_ID, COMPOSITION_ID)
values (5, 5);
insert into music_selection (SELECTION_ID, COMPOSITION_ID)
values (5, 6);
insert into music_selection (SELECTION_ID, COMPOSITION_ID)
values (5, 7);
insert into music_selection (SELECTION_ID, COMPOSITION_ID)
values (5, 8);
insert into music_selection (SELECTION_ID, COMPOSITION_ID)
values (5, 9);
insert into music_selection (SELECTION_ID, COMPOSITION_ID)
values (5, 10);

insert into music_selection (SELECTION_ID, COMPOSITION_ID)
values (6, 1);
insert into music_selection (SELECTION_ID, COMPOSITION_ID)
values (6, 2);
insert into music_selection (SELECTION_ID, COMPOSITION_ID)
values (6, 3);
insert into music_selection (SELECTION_ID, COMPOSITION_ID)
values (6, 4);
insert into music_selection (SELECTION_ID, COMPOSITION_ID)
values (6, 5);
insert into music_selection (SELECTION_ID, COMPOSITION_ID)
values (6, 6);
insert into music_selection (SELECTION_ID, COMPOSITION_ID)
values (6, 7);
insert into music_selection (SELECTION_ID, COMPOSITION_ID)
values (6, 8);
insert into music_selection (SELECTION_ID, COMPOSITION_ID)
values (6, 9);
insert into music_selection (SELECTION_ID, COMPOSITION_ID)
values (6, 10);

insert into music_selection (SELECTION_ID, COMPOSITION_ID)
values (7, 1);
insert into music_selection (SELECTION_ID, COMPOSITION_ID)
values (7, 2);
insert into music_selection (SELECTION_ID, COMPOSITION_ID)
values (7, 3);
insert into music_selection (SELECTION_ID, COMPOSITION_ID)
values (7, 4);
insert into music_selection (SELECTION_ID, COMPOSITION_ID)
values (7, 5);
insert into music_selection (SELECTION_ID, COMPOSITION_ID)
values (7, 6);
insert into music_selection (SELECTION_ID, COMPOSITION_ID)
values (7, 7);
insert into music_selection (SELECTION_ID, COMPOSITION_ID)
values (7, 8);
insert into music_selection (SELECTION_ID, COMPOSITION_ID)
values (7, 9);
insert into music_selection (SELECTION_ID, COMPOSITION_ID)
values (7, 10);

insert into music_selection (SELECTION_ID, COMPOSITION_ID)
values (8, 1);
insert into music_selection (SELECTION_ID, COMPOSITION_ID)
values (8, 2);
insert into music_selection (SELECTION_ID, COMPOSITION_ID)
values (8, 3);
insert into music_selection (SELECTION_ID, COMPOSITION_ID)
values (8, 4);
insert into music_selection (SELECTION_ID, COMPOSITION_ID)
values (8, 5);
insert into music_selection (SELECTION_ID, COMPOSITION_ID)
values (8, 6);
insert into music_selection (SELECTION_ID, COMPOSITION_ID)
values (8, 7);
insert into music_selection (SELECTION_ID, COMPOSITION_ID)
values (8, 8);
insert into music_selection (SELECTION_ID, COMPOSITION_ID)
values (8, 9);
insert into music_selection (SELECTION_ID, COMPOSITION_ID)
values (8, 10);

insert into music_selection (SELECTION_ID, COMPOSITION_ID)
values (9, 1);
insert into music_selection (SELECTION_ID, COMPOSITION_ID)
values (9, 2);
insert into music_selection (SELECTION_ID, COMPOSITION_ID)
values (9, 3);
insert into music_selection (SELECTION_ID, COMPOSITION_ID)
values (9, 4);
insert into music_selection (SELECTION_ID, COMPOSITION_ID)
values (9, 5);
insert into music_selection (SELECTION_ID, COMPOSITION_ID)
values (9, 6);
insert into music_selection (SELECTION_ID, COMPOSITION_ID)
values (9, 7);
insert into music_selection (SELECTION_ID, COMPOSITION_ID)
values (9, 8);
insert into music_selection (SELECTION_ID, COMPOSITION_ID)
values (9, 9);
insert into music_selection (SELECTION_ID, COMPOSITION_ID)
values (9, 10);

insert into music_selection (SELECTION_ID, COMPOSITION_ID)
values (10, 1);
insert into music_selection (SELECTION_ID, COMPOSITION_ID)
values (10, 2);
insert into music_selection (SELECTION_ID, COMPOSITION_ID)
values (10, 3);
insert into music_selection (SELECTION_ID, COMPOSITION_ID)
values (10, 4);
insert into music_selection (SELECTION_ID, COMPOSITION_ID)
values (10, 5);
insert into music_selection (SELECTION_ID, COMPOSITION_ID)
values (10, 6);
insert into music_selection (SELECTION_ID, COMPOSITION_ID)
values (10, 7);
insert into music_selection (SELECTION_ID, COMPOSITION_ID)
values (10, 8);
insert into music_selection (SELECTION_ID, COMPOSITION_ID)
values (10, 9);
insert into music_selection (SELECTION_ID, COMPOSITION_ID)
values (10, 10);

insert into user_music_selection (USER_ID, SELECTION_ID)
values (1, 10);
insert into user_music_selection (USER_ID, SELECTION_ID)
values (2, 9);
insert into user_music_selection (USER_ID, SELECTION_ID)
values (4, 7);
insert into user_music_selection (USER_ID, SELECTION_ID)
values (5, 6);
insert into user_music_selection (USER_ID, SELECTION_ID)
values (6, 5);
insert into user_music_selection (USER_ID, SELECTION_ID)
values (7, 4);
insert into user_music_selection (USER_ID, SELECTION_ID)
values (8, 3);
insert into user_music_selection (USER_ID, SELECTION_ID)
values (9, 2);
insert into user_music_selection (USER_ID, SELECTION_ID)
values (3, 8);
insert into user_music_selection (USER_ID, SELECTION_ID)
values (10, 1);
insert into user_music_selection (USER_ID, SELECTION_ID)
values (1, 10);
insert into user_music_selection (USER_ID, SELECTION_ID)
values (2, 9);
insert into user_music_selection (USER_ID, SELECTION_ID)
values (3, 8);
insert into user_music_selection (USER_ID, SELECTION_ID)
values (4, 7);
insert into user_music_selection (USER_ID, SELECTION_ID)
values (5, 6);
insert into user_music_selection (USER_ID, SELECTION_ID)
values (6, 5);
insert into user_music_selection (USER_ID, SELECTION_ID)
values (7, 4);
insert into user_music_selection (USER_ID, SELECTION_ID)
values (8, 3);
insert into user_music_selection (USER_ID, SELECTION_ID)
values (9, 2);
insert into user_music_selection (USER_ID, SELECTION_ID)
values (10, 1);

insert into music_selection_feedback (ID, FEEDBACK, DATE)
values (1, 'NORM TAKAYA WIBORKA1', '2011-11-11 11:11:11');
insert into music_selection_feedback (ID, FEEDBACK, DATE)
values (2, 'NORM TAKAYA WIBORKA2', '2011-11-11 11:11:11');
insert into music_selection_feedback (ID, FEEDBACK, DATE)
values (3, 'NORM TAKAYA WIBORKA3', '2011-11-11 11:11:11');
insert into music_selection_feedback (ID, FEEDBACK, DATE)
values (4, 'NORM TAKAYA WIBORKA4', '2011-11-11 11:11:11');
insert into music_selection_feedback (ID, FEEDBACK, DATE)
values (5, 'NORM TAKAYA WIBORKA5', '2011-11-11 11:11:11');
insert into music_selection_feedback (ID, FEEDBACK, DATE)
values (6, 'NORM TAKAYA WIBORKA6', '2011-11-11 11:11:11');
insert into music_selection_feedback (ID, FEEDBACK, DATE)
values (7, 'NORM TAKAYA WIBORKA7', '2011-11-11 11:11:11');
insert into music_selection_feedback (ID, FEEDBACK, DATE)
values (8, 'NORM TAKAYA WIBORKA8', '2011-11-11 11:11:11');
insert into music_selection_feedback (ID, FEEDBACK, DATE)
values (9, 'NORM TAKAYA WIBORKA9', '2011-11-11 11:11:11');
