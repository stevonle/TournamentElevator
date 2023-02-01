BEGIN TRANSACTION;

INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');

/* Test data */
INSERT INTO users (username,password_hash,role) VALUES ('bob', '$2a$10$mihfqI6/oyAd4JhYdAln5O/hwfiys9qgmhsfJcdELKMtuKl6iVVEO','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('steve', '$2a$10$ZrJAV.lP6U24dNCAOSlkWepbD6aQ4KOK6tAqe.1jH4L1j3LUOHiIC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('bruce', '$2a$10$65Kh5OVfB20JypFHEeeZ3efjtg3sI35dxjGMScx79yyxqI/G1QRDK','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('jane', '$2a$10$d8iGd6yBlhlzD/WFcG5.VukgWxu6/dt4WvxBFS652hxOSBZOyPKXi','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('carol', '$2a$10$C3D6K67Tz7o.pRoTQdnfIuXTcoFi94n71SdGQlziJw6xM/rA8M1J6','ROLE_USER');


INSERT INTO games (game_name) VALUES ('Football');
INSERT INTO games (game_name) VALUES ('Soccer');
INSERT INTO games (game_name) VALUES ('Basketball');
INSERT INTO games (game_name) VALUES ('Volleyball');
INSERT INTO games (game_name) VALUES ('Quidditch');

INSERT INTO teams(team_name, isacceptingmembers, team_captain, team_description) VALUES ('The Avengers', FALSE ,'4','This is a description. ');
INSERT INTO teams(team_name, isacceptingmembers, team_captain, team_description) VALUES ('Toon Squad', TRUE ,'7','This is a description. This is a description. ');
INSERT INTO teams(team_name, isacceptingmembers, team_captain, team_description) VALUES ('Army of Whatever', TRUE ,'5','This is a description. This is a description. This is a description. ');
INSERT INTO teams(team_name, isacceptingmembers, team_captain, team_description) VALUES ('Legion of Doom', TRUE ,'3','This is a description. This is a description. This is a description. This is a description. ');

INSERT INTO tournaments (tournament_name, game_type, tournament_date, tournament_location, fee,
 tournament_description, prize, host) VALUES ('THE BIG ONE', '1', '2023-02-20 10:23:54', 'Everywhere', '1000.00', 'This is a description. This is the BIG ONE!', 'Everything', '3');


COMMIT TRANSACTION;