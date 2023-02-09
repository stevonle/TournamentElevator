BEGIN TRANSACTION;

INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');

/* Test data */
--user info
INSERT INTO users (username,password_hash,role) VALUES ('bob', '$2a$10$mihfqI6/oyAd4JhYdAln5O/hwfiys9qgmhsfJcdELKMtuKl6iVVEO','ROLE_USER'); --3 user_id
INSERT INTO users (username,password_hash,role) VALUES ('steve', '$2a$10$ZrJAV.lP6U24dNCAOSlkWepbD6aQ4KOK6tAqe.1jH4L1j3LUOHiIC','ROLE_USER'); --4
INSERT INTO users (username,password_hash,role) VALUES ('bruce', '$2a$10$65Kh5OVfB20JypFHEeeZ3efjtg3sI35dxjGMScx79yyxqI/G1QRDK','ROLE_USER'); --5
INSERT INTO users (username,password_hash,role) VALUES ('jane', '$2a$10$d8iGd6yBlhlzD/WFcG5.VukgWxu6/dt4WvxBFS652hxOSBZOyPKXi','ROLE_USER'); --6
INSERT INTO users (username,password_hash,role) VALUES ('carol', '$2a$10$C3D6K67Tz7o.pRoTQdnfIuXTcoFi94n71SdGQlziJw6xM/rA8M1J6','ROLE_USER'); --7
INSERT INTO users (username,password_hash,role) VALUES ('donald','$2a$10$UNZcJJd1yjld7gtlokQFheEtTgXyM.PLNCXXVzxsbQffxDcKLrSyO','ROLE_USER'); --8
INSERT INTO users (username,password_hash,role) VALUES ('tony','$2a$10$3C.nT9cM76hA1cAvDXQOv.BSMXaD58TTUWIn4GhiVTYm1DE7vJM9G','ROLE_USER'); --9
INSERT INTO users (username,password_hash,role) VALUES ('hank','$2a$10$sEGRP7N1hUeZp6XlkXa.P.dGWGNYyqMcSWUd8PAUyMjRpxUI6lvs2','ROLE_USER'); --10
INSERT INTO users (username,password_hash,role) VALUES ('clint','$2a$10$3U80Hu2ImucTrqwQraMvmOcmFEMR2lCy2n32q7nmZOyvxK.kfNNUm','ROLE_USER'); --11
INSERT INTO users (username,password_hash,role) VALUES ('victor','$2a$10$2GKf6HFwAt2YN/QdtisZ0ulMtplxzxqKLpE7Hf2KN5TM.Y.8Zbone','ROLE_USER'); --12


--game types
INSERT INTO games (game_name) VALUES ('Football');
INSERT INTO games (game_name) VALUES ('Soccer');
INSERT INTO games (game_name) VALUES ('Basketball');
INSERT INTO games (game_name) VALUES ('Volleyball');
INSERT INTO games (game_name) VALUES ('Quidditch');

--create teams
INSERT INTO teams(team_name, isacceptingmembers, team_captain, team_description) VALUES ('The Avengers', FALSE ,'4','We can do this all day '); --1 team_id
INSERT INTO teams(team_name, isacceptingmembers, team_captain, team_description) VALUES ('Toon Squad', TRUE ,'7','Whatever it is, we have a lot of it '); --2
INSERT INTO teams(team_name, isacceptingmembers, team_captain, team_description) VALUES ('Army of Whatever', TRUE ,'5','Whenever, wherever, whatever '); --3
INSERT INTO teams(team_name, isacceptingmembers, team_captain, team_description) VALUES ('Legion of Doom', TRUE ,'3','We will become an unstoppable force that no one will be able to... stop '); --4
INSERT INTO teams(team_name, isacceptingmembers, team_captain, team_description) VALUES ('Janes Green Turtles', FALSE ,'6','Turtle Time! '); --5
INSERT INTO teams(team_name, isacceptingmembers, team_captain, team_description) VALUES ('Bruces Blue Berries', FALSE ,'5','We''re new, but not newbs '); --6
INSERT INTO teams(team_name, isacceptingmembers, team_captain, team_description) VALUES ('Steve and the pirates', FALSE ,'4','If you can dodge a wrench, you can dodge a ...');--7
INSERT INTO teams(team_name, isacceptingmembers, team_captain, team_description) VALUES ('Defenders', FALSE ,'5','Defense wins championships'); --8
INSERT INTO teams(team_name, isacceptingmembers, team_captain, team_description) VALUES ('Latvarian Dooms', FALSE ,'12','DOOM!'); --9
INSERT INTO teams(team_name, isacceptingmembers, team_captain, team_description) VALUES ('Bob''s Boys', FALSE ,'3','Bob is pretty cool.'); --10



--tournaments
INSERT INTO tournaments (tournament_name, game_type, tournament_date, tournament_location, fee,
 tournament_description, prize, host) VALUES ('THE BIG ONE', '1', '2023-02-28 10:23:54', 'Everywhere', '1000.00', 'This is the BIG ONE!', 'Everything', '3');
 INSERT INTO tournaments (tournament_name, game_type, tournament_date, tournament_location, fee,
 tournament_description, prize, host) VALUES ('The Medium One', '2', '2023-03-20 10:23:54', 'My House', '100.00', 'The sequel to the big one, not quite as big', 'Pretty Good stuff', '3');
  INSERT INTO tournaments (tournament_name, game_type, tournament_date, tournament_location, fee,
 tournament_description, prize, host) VALUES ('Quidditch Cup', '5', '2023-04-20 10:23:54', 'Hogwarts', '0.00', 'Invitation only! Get that snitch.', 'THE HOUSE CUP', '4');
 INSERT INTO tournaments (tournament_name, game_type, tournament_date, tournament_location, fee,
 tournament_description, prize, host) VALUES ('The Small One', '5', '2023-03-22 10:23:54', 'Ant Hill', '1.00', 'This one is pretty small.', 'Tiny Trinkets', '10');
 INSERT INTO tournaments (tournament_name, game_type, tournament_date, tournament_location, fee,
 tournament_description, prize, host) VALUES ('Tournament Elevator Final', '4', '2023-02-10 9:00:00', 'REMOTE', '0', 'We did it.', 'A job.', '3');
 
--team members
INSERT INTO user_teams (user_id, team_id, isaccepted) values (4, 1, true); -- team 1
INSERT INTO user_teams (user_id, team_id, isaccepted) values (5, 1, true);
INSERT INTO user_teams (user_id, team_id, isaccepted) values (7, 1, false);
INSERT INTO user_teams (user_id, team_id, isaccepted) values (6, 1, false);

INSERT INTO user_teams (user_id, team_id, isaccepted) values (7, 2, true); --team 2
INSERT INTO user_teams (user_id, team_id, isaccepted) values (10, 2, true);
INSERT INTO user_teams (user_id, team_id, isaccepted) values (12, 2, false);

INSERT INTO user_teams (user_id, team_id, isaccepted) values (5, 3, true); -- team 3
INSERT INTO user_teams (user_id, team_id, isaccepted) values (6, 3, true);
INSERT INTO user_teams (user_id, team_id, isaccepted) values (12, 3, false);

INSERT INTO user_teams (user_id, team_id, isaccepted) values (3, 4, true); --team 4
INSERT INTO user_teams (user_id, team_id, isaccepted) values (4, 4, true);
INSERT INTO user_teams (user_id, team_id, isaccepted) values (5, 4, false);
INSERT INTO user_teams (user_id, team_id, isaccepted) values (12, 4, false);

INSERT INTO user_teams (user_id, team_id, isaccepted) values (6, 5, true); --team 5
INSERT INTO user_teams (user_id, team_id, isaccepted) values (7, 5, false);
INSERT INTO user_teams (user_id, team_id, isaccepted) values (12, 5, false);

INSERT INTO user_teams (user_id, team_id, isaccepted) values (5, 6, true); --team 6
INSERT INTO user_teams (user_id, team_id, isaccepted) values (8, 6, true);
INSERT INTO user_teams (user_id, team_id, isaccepted) values (12, 6, false);

INSERT INTO user_teams (user_id, team_id, isaccepted) values (4, 7, true); --team 7
INSERT INTO user_teams (user_id, team_id, isaccepted) values (7, 7, true);
INSERT INTO user_teams (user_id, team_id, isaccepted) values (12, 7, false);

INSERT INTO user_teams (user_id, team_id, isaccepted) values (5, 8, true); --team 8
INSERT INTO user_teams (user_id, team_id, isaccepted) values (10, 8, true);
INSERT INTO user_teams (user_id, team_id, isaccepted) values (12, 8, false);

INSERT INTO user_teams (user_id, team_id, isaccepted) values (12, 9, true); --team 9
INSERT INTO user_teams (user_id, team_id, isaccepted) values (11, 9, false);
INSERT INTO user_teams (user_id, team_id, isaccepted) values (4, 9, false);

INSERT INTO user_teams (user_id, team_id, isaccepted) values (3, 10, true); --team 10
INSERT INTO user_teams (user_id, team_id, isaccepted) values (4, 10, true);
INSERT INTO user_teams (user_id, team_id, isaccepted) values (5, 10, true);
INSERT INTO user_teams (user_id, team_id, isaccepted) values (12, 10, false);

--teams in tournaments
INSERT INTO teams_tournament (tournament_id, team_id, isaccepted) VALUES (1, 1, true);
INSERT INTO teams_tournament (tournament_id, team_id, isaccepted) VALUES (1, 2, true);
INSERT INTO teams_tournament (tournament_id, team_id, isaccepted) VALUES (1, 3, true);
INSERT INTO teams_tournament (tournament_id, team_id, isaccepted) VALUES (1, 7, false);
INSERT INTO teams_tournament (tournament_id, team_id, isaccepted) VALUES (1, 9, false);


--teams in tourneys



COMMIT TRANSACTION;