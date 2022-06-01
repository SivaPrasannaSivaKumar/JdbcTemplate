CREATE TABLE game (
    game_id integer NOT NULL,
    title varchar(80) NOT NULL,         -- Game Title
    description varchar(500) NOT NULL,  -- Game Description
    PRIMARY KEY (game_id)
);