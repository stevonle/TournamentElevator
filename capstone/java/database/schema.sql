BEGIN TRANSACTION;

DROP TABLE IF EXISTS users, teams, tournaments, pairings, games, user_teams, team_games CASCADE;


CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user_key PRIMARY KEY (user_id)
);

CREATE TABLE teams (
	team_id SERIAL,
	team_name varchar(100),
	isAcceptingMembers boolean,
	team_captain int,
	team_description varchar(500),
	CONSTRAINT PK_teams_key PRIMARY KEY (team_id)
);

CREATE TABLE games (
	game_id SERIAL,
	game_name varChar(50),
	CONSTRAINT PK_games_key PRIMARY KEY (game_id)
);
CREATE TABLE tournaments (
	tournament_id SERIAL,
	tournament_name varchar(50),
	game_type int,
	tournament_date timestamp,
	tournament_location varChar(200),
	fee numeric(10,2),
	tournament_description varchar(500),
	prize varChar(100),
	host int,
	completed boolean DEFAULT false,
	round int DEFAULT 1,
	CONSTRAINT PK_tournaments_key PRIMARY KEY (tournament_id),
	CONSTRAINT FK_host_foreign FOREIGN KEY (host) REFERENCES users(user_id),
	CONSTRAINT game_type FOREIGN KEY (game_type) REFERENCES games(game_id)
);

CREATE TABLE pairings (
	pairing_id SERIAL,
	tournament_id int,
	teamOne int,
	teamTwo int,
	team_one_wins int,
	team_two_wins int,
	round int,
	CONSTRAINT PK_pairings_key PRIMARY KEY (pairing_id),
	CONSTRAINT FK_tournament_pairings FOREIGN KEY (tournament_id) REFERENCES tournaments(tournament_id)
);



CREATE TABLE team_games (
	team_id int,
	game_id int,
	CONSTRAINT PK_team_games_key PRIMARY KEY (team_id, game_id),
	CONSTRAINT FK_team_game_joiner FOREIGN KEY (team_id) REFERENCES teams(team_id),
	CONSTRAINT FK_game_team_joiner FOREIGN KEY (game_id) REFERENCES games(game_id)
);

CREATE TABLE user_teams (
	user_id int,
	team_id int,
	isAccepted boolean DEFAULT false,
	CONSTRAINT PK_user_team_key PRIMARY KEY (user_id, team_id),
	CONSTRAINT FK_user_team_joiner FOREIGN KEY (user_id) REFERENCES users (user_id),
	CONSTRAINT FK_team_user_joiner FOREIGN KEY (team_id) REFERENCES teams (team_id)
);

CREATE TABLE teams_tournament(
	tournament_id int,
	team_id int,
	isAccepted boolean DEFAULT false,
	CONSTRAINT PK_tournament_team_key PRIMARY KEY (tournament_id, team_id),
	CONSTRAINT FK_team_tournament_joiner FOREIGN KEY (tournament_id) REFERENCES tournaments(tournament_id),
	CONSTRAINT FK_tournament_team_joiner FOREIGN KEY (team_id) REFERENCES teams (team_id)
);

COMMIT TRANSACTION;
