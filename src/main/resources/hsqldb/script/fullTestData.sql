insert into STATUS (STATUS) values ('activated')
insert into STATUS (STATUS) values ('deactivated')
insert into STATUS (STATUS) values ('deleted')

insert into ROLE (ROLE) values ('administrator')
insert into ROLE (ROLE) values ('client')

insert into BONUS (BONUS) values  ('sale 10%')
insert into BONUS (BONUS) values  ('sale 20%')
insert into BONUS (BONUS) values  ('sale 30%')
insert into BONUS (BONUS) values  ('sale 40%')
insert into BONUS (BONUS) values  ('sale 50%')
insert into BONUS (BONUS) values  ('sale 60%')
insert into BONUS (BONUS) values  ('sale 70%')
insert into BONUS (BONUS) values  ('sale 80%')
insert into BONUS (BONUS) values  ('sale 90%')
insert into BONUS (BONUS) values  ('free song')
insert into BONUS (BONUS) values  ('free album')
insert into BONUS (BONUS) values  ('free music selection')
insert into BONUS (BONUS) values  ('unlim')

insert into AUTHOR (FIRST_NAME, SECOND_NAME, PSEUDONIM) values ('Miron', 'Yanovich', 'Oxxxymiron')
insert into AUTHOR (FIRST_NAME, SECOND_NAME, PSEUDONIM) values ('Alexandr', 'Zaporozhtsev', 'Grand-Master')
insert into AUTHOR (FIRST_NAME, SECOND_NAME, PSEUDONIM) values ('Iosiph', 'Stalin', 'Slava KPSS')

insert into USERS (LOGIN, PASSWORD, FIRST_NAME, SECOND_NAME, REGISTER_DATE, SALE, BALANCE, ROLE_ID, STATUS_ID, EMAIL) values ('mrmrmr7', 'EpamClass2019', 'First name', 'Second name', '2008-08-08 20:08:08', 10, 100, 1, 1, 'aliex.s@yandex.by')
insert into USERS (LOGIN, PASSWORD, FIRST_NAME, SECOND_NAME, REGISTER_DATE, SALE, BALANCE, ROLE_ID, STATUS_ID, EMAIL) values ('mrmrmr8', 'EpamClass2019', 'First name', 'Second name', '2008-08-08 20:08:08', 10, 100, 1, 1, 'aliex.kot@yandex.by')

