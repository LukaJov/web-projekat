INSERT INTO FITNESS_CENTER(name, address, phone_number, email_address) VALUES('XGym', 'Bulevar 21', '062413213', 'xgym@gmail.com');

INSERT INTO ADMIN(username, password, name, surname, phone_Number, email_Address, birthday, user_Type, active)
VALUES ('Srdjan2', 'Srdjan222', 'Srdjan', 'Mihajlovic', '06233783', 'mihajlsrdj@gmail.com', '1973-1-28', 'Admin', true);

INSERT INTO TRAINER(username, password, name, surname, phone_Number, email_Address, birthday, user_Type, active, fit_center_id)
VALUES ('LukaJov', 'LukaJov123', 'Luka', 'Jovanovic', '06234443', 'lukajov@gmail.com', '2000-7-1', 'Trainer', true, 1);
INSERT INTO TRAINER(username, password, name, surname, phone_Number, email_Address, birthday, user_Type, active, fit_center_id)
VALUES ('Milan1234', 'Lanmi1234', 'Milan', 'Simic', '06453454', 'simicmilan@gmail.com', '2003-4-12', 'Trainer', false, 1);
INSERT INTO TRAINER(username, password, name, surname, phone_Number, email_Address, birthday, user_Type, active, fit_center_id)
VALUES ('gorang', 'gorangoran', 'Goran', 'Goranovic', '064534544', 'rango@gmail.com', '2001-4-12', 'Trainer', false, 1);


INSERT INTO USER (username, password, name, surname, phone_Number, email_Address, birthday, user_Type, active)
VALUES ('MarkoR', 'MarkoRakic1234', 'Marko', 'Rakic', '06137837', 'rakicmarko@gmail.com', '1993-7-12', 'Member', true);
INSERT INTO USER (username, password, name, surname, phone_Number, email_Address, birthday, user_Type, active)
VALUES ('Sasa', 'Sasa1234', 'Sasa', 'Sakic', '068768737', 'sasasale@gmail.com', '1993-7-11', 'Member', true);

INSERT INTO TRAINING(name, desc, training_type, duration) VALUES('ExtraCardio', 'Cardiovascular training for beginners'
, 'Cardio', 2);
INSERT INTO TRAINING(name, desc, training_type, duration) VALUES('Build your body', 'Hypertrophy training', 'Bodybuilding', 1);
INSERT INTO TRAINING(name, desc, training_type, duration) VALUES('Crossfit', 'Functional training', 'Crossfit', 2);
INSERT INTO ROOM(capacity,label, fit_center_id) VALUES (15, 'Sala1',1);
INSERT INTO ROOM(capacity,label, fit_center_id) VALUES (25, 'Sala2',1);

INSERT INTO TERM(date, price, number_of_users,fit_center_id, room_id, training_id, trainer_id ) VALUES('2021-7-6', 2300, 4, 1, 1, 2, 1);
INSERT INTO TERM(date, price, number_of_users,fit_center_id, room_id, training_id, trainer_id ) VALUES('2021-8-30', 1700, 7, 1, 1, 1, 2);
INSERT INTO TERM(date, price, number_of_users,fit_center_id, room_id, training_id, trainer_id ) VALUES('2021-7-3', 2000, 9, 1, 2, 1, 2);
INSERT INTO TERM(date, price, number_of_users,fit_center_id, room_id, training_id, trainer_id ) VALUES('2021-8-7', 1900, 7, 1, 2, 1, 2);

INSERT INTO DONE(term_id, user_id) VALUES(1,1);
INSERT INTO DONE(term_id, user_id) VALUES(3, 1);
/*INSERT INTO TERMS(room_id, term_id) VALUES (1,1);*/

INSERT INTO GRADE(grd, given_by_id, term_id) VALUES(3.5, 1, 1);


INSERT INTO TO_DO(term_id, user_id) VALUES (2,1);
/*INSERT INTO USER_GRADES(user_id, grades_id) VALUES (1,1);*/