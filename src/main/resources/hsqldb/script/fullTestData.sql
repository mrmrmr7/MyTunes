insert into STATUS (STATUS) values ('activated');
insert into STATUS (STATUS) values ('deactivated');
insert into STATUS (STATUS) values ('deleted');

insert into ROLE (ROLE) values ('administrator');
insert into ROLE (ROLE) values ('client');

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

insert into AUTHOR (FIRST_NAME, SECOND_NAME, PSEUDONIM) values ('Unknown', 'Unknown', 'Unknown');
insert into AUTHOR (FIRST_NAME, SECOND_NAME, PSEUDONIM) values ('Alexandr', 'Zaporozhtsev', 'Grand-Master');
insert into AUTHOR (FIRST_NAME, SECOND_NAME, PSEUDONIM) values ('Iosiph', 'Stalin', 'Slava KPSS');
insert into AUTHOR (FIRST_NAME, SECOND_NAME, PSEUDONIM) values ('LoshkAh', 'Anton', 'MORGENSTERN');
insert into AUTHOR (FIRST_NAME, SECOND_NAME, PSEUDONIM) values ('dVeri', 'Anon', 'Stone');
insert into AUTHOR (FIRST_NAME, SECOND_NAME, PSEUDONIM) values ('Spice', 'Snaf', 'LSP');
insert into AUTHOR (FIRST_NAME, SECOND_NAME, PSEUDONIM) values ('Andrey', 'Kirill', 'FLOVERS');
insert into AUTHOR (FIRST_NAME, SECOND_NAME, PSEUDONIM) values ('Maloy', 'Diller', 'TwoBoys');
insert into AUTHOR (FIRST_NAME, SECOND_NAME, PSEUDONIM) values ('Maloy1', 'Diller1', 'TwoBoys1');
insert into AUTHOR (FIRST_NAME, SECOND_NAME, PSEUDONIM) values ('Maloy2', 'Diller2', 'TwoBoys2');

insert into USERS (LOGIN, PASSWORD, FIRST_NAME, SECOND_NAME, REGISTER_DATE, SALE, BALANCE, ROLE_ID, STATUS_ID, EMAIL) values ('mrmrmr1', 'EpamClass2019', 'First name', 'Second name', '2008-08-08 20:08:08', 10, 100, 1, 1, 'aliex.s@yandex.by');
insert into USERS (LOGIN, PASSWORD, FIRST_NAME, SECOND_NAME, REGISTER_DATE, SALE, BALANCE, ROLE_ID, STATUS_ID, EMAIL) values ('mrmrmr2', 'EpamClass2019', 'First name', 'Second name', '2008-08-08 20:08:08', 10, 100, 1, 1, 'aliex.kot1@yandex.by');
insert into USERS (LOGIN, PASSWORD, FIRST_NAME, SECOND_NAME, REGISTER_DATE, SALE, BALANCE, ROLE_ID, STATUS_ID, EMAIL) values ('mrmrmr3', 'EpamClass2019', 'First name', 'Second name', '2008-08-08 20:08:08', 10, 100, 1, 1, 'aliex.kot2@yandex.by');
insert into USERS (LOGIN, PASSWORD, FIRST_NAME, SECOND_NAME, REGISTER_DATE, SALE, BALANCE, ROLE_ID, STATUS_ID, EMAIL) values ('mrmrmr4', 'EpamClass2019', 'First name', 'Second name', '2008-08-08 20:08:08', 10, 100, 1, 1, 'aliex.kot3@yandex.by');
insert into USERS (LOGIN, PASSWORD, FIRST_NAME, SECOND_NAME, REGISTER_DATE, SALE, BALANCE, ROLE_ID, STATUS_ID, EMAIL) values ('mrmrmr5', 'EpamClass2019', 'First name', 'Second name', '2008-08-08 20:08:08', 10, 100, 1, 1, 'aliex.kot4@yandex.by');
insert into USERS (LOGIN, PASSWORD, FIRST_NAME, SECOND_NAME, REGISTER_DATE, SALE, BALANCE, ROLE_ID, STATUS_ID, EMAIL) values ('mrmrmr6', 'EpamClass2019', 'First name', 'Second name', '2008-08-08 20:08:08', 10, 100, 1, 1, 'aliex.kot5@yandex.by');
insert into USERS (LOGIN, PASSWORD, FIRST_NAME, SECOND_NAME, REGISTER_DATE, SALE, BALANCE, ROLE_ID, STATUS_ID, EMAIL) values ('mrmrmr7', 'EpamClass2019', 'First name', 'Second name', '2008-08-08 20:08:08', 10, 100, 1, 1, 'aliex.kot6@yandex.by');
insert into USERS (LOGIN, PASSWORD, FIRST_NAME, SECOND_NAME, REGISTER_DATE, SALE, BALANCE, ROLE_ID, STATUS_ID, EMAIL) values ('mrmrmr8', 'EpamClass2019', 'First name', 'Second name', '2008-08-08 20:08:08', 10, 100, 1, 1, 'aliex.kot7@yandex.by');
insert into USERS (LOGIN, PASSWORD, FIRST_NAME, SECOND_NAME, REGISTER_DATE, SALE, BALANCE, ROLE_ID, STATUS_ID, EMAIL) values ('mrmrmr9', 'EpamClass2019', 'First name', 'Second name', '2008-08-08 20:08:08', 10, 100, 1, 1, 'aliex.kot8@yandex.by');
insert into USERS (LOGIN, PASSWORD, FIRST_NAME, SECOND_NAME, REGISTER_DATE, SALE, BALANCE, ROLE_ID, STATUS_ID, EMAIL) values ('mrmrmr10', 'EpamClass2019', 'First name', 'Second name', '2008-08-08 20:08:08', 10, 100, 1, 1, 'aliex.kot9@yandex.by');

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

insert into GENRE (GENRE) values ('Rock');
insert into GENRE (GENRE) values ('Hard Rock');
insert into GENRE (GENRE) values ('Dubstep');
insert into GENRE (GENRE) values ('Rap');
insert into GENRE (GENRE) values ('HipHop');
insert into GENRE (GENRE) values ('Squad');
insert into GENRE (GENRE) values ('Pop');
insert into GENRE (GENRE) values ('K-pop');
insert into GENRE (GENRE) values ('DJ');
insert into GENRE (GENRE) values ('Metall');

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

insert into ALBUM_FEEDBACK (ID, FEEDBACK, DATE) values (1, 'Norm takoy album1', '2019-05-03 12:00:03');
insert into ALBUM_FEEDBACK (ID, FEEDBACK, DATE) values (2, 'Norm takoy album2', '2019-05-03 12:00:03');
insert into ALBUM_FEEDBACK (ID, FEEDBACK, DATE) values (3, 'Norm takoy album3', '2019-05-03 12:00:03');
insert into ALBUM_FEEDBACK (ID, FEEDBACK, DATE) values (4, 'Norm takoy album4', '2019-05-03 12:00:03');
insert into ALBUM_FEEDBACK (ID, FEEDBACK, DATE) values (5, 'Norm takoy album5', '2019-05-03 12:00:03');
insert into ALBUM_FEEDBACK (ID, FEEDBACK, DATE) values (6, 'Norm takoy album6', '2019-05-03 12:00:03');
insert into ALBUM_FEEDBACK (ID, FEEDBACK, DATE) values (7, 'Norm takoy album7', '2019-05-03 12:00:03');
insert into ALBUM_FEEDBACK (ID, FEEDBACK, DATE) values (8, 'Norm takoy album8', '2019-05-03 12:00:03');
insert into ALBUM_FEEDBACK (ID, FEEDBACK, DATE) values (9, 'Norm takoy album9', '2019-05-03 12:00:03');

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

insert into COMPOSITION_FEEDBACK (ID, FEEDBACK, DATE) values (1, 'Norm takaya pesnya1', '2009-06-04 19:14:20');
insert into COMPOSITION_FEEDBACK (ID, FEEDBACK, DATE) values (2, 'Norm takaya pesnya2', '2009-06-04 19:14:20');
insert into COMPOSITION_FEEDBACK (ID, FEEDBACK, DATE) values (3, 'Norm takaya pesnya3', '2009-06-04 19:14:20');
insert into COMPOSITION_FEEDBACK (ID, FEEDBACK, DATE) values (4, 'Norm takaya pesnya4', '2009-06-04 19:14:20');
insert into COMPOSITION_FEEDBACK (ID, FEEDBACK, DATE) values (5, 'Norm takaya pesnya5', '2009-06-04 19:14:20');
insert into COMPOSITION_FEEDBACK (ID, FEEDBACK, DATE) values (6, 'Norm takaya pesnya6', '2009-06-04 19:14:20');
insert into COMPOSITION_FEEDBACK (ID, FEEDBACK, DATE) values (7, 'Norm takaya pesnya7', '2009-06-04 19:14:20');
insert into COMPOSITION_FEEDBACK (ID, FEEDBACK, DATE) values (8, 'Norm takaya pesnya8', '2009-06-04 19:14:20');
insert into COMPOSITION_FEEDBACK (ID, FEEDBACK, DATE) values (9, 'Norm takaya pesnya9', '2009-06-04 19:14:20');

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



insert into MUSIC_SELECTION_FEEDBACK (ID, FEEDBACK, DATE) values (1, 'Norm takaya wiborka1', '2011-11-11 11:11:11');
insert into MUSIC_SELECTION_FEEDBACK (ID, FEEDBACK, DATE) values (2, 'Norm takaya wiborka2', '2011-11-11 11:11:11');
insert into MUSIC_SELECTION_FEEDBACK (ID, FEEDBACK, DATE) values (3, 'Norm takaya wiborka3', '2011-11-11 11:11:11');
insert into MUSIC_SELECTION_FEEDBACK (ID, FEEDBACK, DATE) values (4, 'Norm takaya wiborka4', '2011-11-11 11:11:11');
insert into MUSIC_SELECTION_FEEDBACK (ID, FEEDBACK, DATE) values (5, 'Norm takaya wiborka5', '2011-11-11 11:11:11');
insert into MUSIC_SELECTION_FEEDBACK (ID, FEEDBACK, DATE) values (6, 'Norm takaya wiborka6', '2011-11-11 11:11:11');
insert into MUSIC_SELECTION_FEEDBACK (ID, FEEDBACK, DATE) values (7, 'Norm takaya wiborka7', '2011-11-11 11:11:11');
insert into MUSIC_SELECTION_FEEDBACK (ID, FEEDBACK, DATE) values (8, 'Norm takaya wiborka8', '2011-11-11 11:11:11');
insert into MUSIC_SELECTION_FEEDBACK (ID, FEEDBACK, DATE) values (9, 'Norm takaya wiborka9', '2011-11-11 11:11:11');