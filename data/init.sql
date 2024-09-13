--  Sequence creation
CREATE SCHEMA activities;
CREATE SCHEMA sport;
CREATE SCHEMA nutrition;

CREATE TABLE public.user (
    id INTEGER PRIMARY KEY,
    username VARCHAR,
    mail VARCHAR,
    password VARCHAR,
    role VARCHAR,
    created_on TIMESTAMP,
    birth_date TIMESTAMP,
    weight NUMERIC,
    height NUMERIC,
    fat NUMERIC,
    target_weight NUMERIC
);

CREATE TABLE activities.activity (
       id INTEGER PRIMARY KEY,
       name VARCHAR,
       icon VARCHAR,
       unity VARCHAR,
       description VARCHAR,
        category VARCHAR
);

CREATE TABLE activities.category (
    id INTEGER PRIMARY KEY,
    name VARCHAR,
    icon VARCHAR,
    description VARCHAR
);

CREATE TABLE activities.activity_save (
    id INTEGER PRIMARY KEY,
    activity_id INTEGER NOT NULL,
    user_id INTEGER NOT NULL,
    frequency INTEGER,
    objective INTEGER,
    mark INTEGER,
    notes VARCHAR,
    time TIMESTAMP,
    day TIMESTAMP,
    FOREIGN KEY (activity_id) REFERENCES activities.activity (id),
    FOREIGN KEY (user_id) REFERENCES public.user (id)
);

CREATE TABLE activities.activity_done (
    id INTEGER PRIMARY KEY,
    done_on TIMESTAMP,
    achievement INTEGER,
    activity_save_id INTEGER,
    duration TIMESTAMP,
    status VARCHAR,
    FOREIGN KEY (activity_save_id) REFERENCES activities.activity_save (id)
);

CREATE TABLE sport.workout (
    id INTEGER PRIMARY KEY,
    done_on TIMESTAMP,
    name VARCHAR,
    description VARCHAR,
    mark INTEGER,
    duration INTEGER,
    user_id INTEGER,
    FOREIGN KEY (user_id) REFERENCES public.user (id)
);

CREATE TABLE sport.exercise (
    id INTEGER PRIMARY KEY,
    name VARCHAR,
    description VARCHAR,
    icon VARCHAR,
    intensity INTEGER,
    sport_id INTEGER,
    FOREIGN KEY (sport_id) REFERENCES sport.workout (id)
);

CREATE TABLE sport.serie (
    id INTEGER PRIMARY KEY,
    number INTEGER,
    exercise_id INTEGER,
    FOREIGN KEY (exercise_id) REFERENCES sport.exercise (id)
);

CREATE TABLE nutrition.food (
    id INTEGER PRIMARY KEY,
    name VARCHAR,
    kcal INTEGER,
    icon VARCHAR
);

CREATE TABLE nutrition.meal (
    id INTEGER PRIMARY KEY,
    done_on TIMESTAMP,
    user_id INTEGER,
    FOREIGN KEY (user_id) REFERENCES public.user (id)
);

CREATE TABLE nutrition.eaten (
    meal_id INTEGER,
    food_id INTEGER,
    quantity INTEGER,
    PRIMARY KEY (meal_id, food_id),
    FOREIGN KEY (meal_id) REFERENCES nutrition.meal (id),
    FOREIGN KEY (food_id) REFERENCES nutrition.food (id)
);

CREATE SEQUENCE nutrition.eaten_sequence
    START WITH 1
    INCREMENT BY 1;

CREATE SEQUENCE nutrition.meal_sequence
    START WITH 1
    INCREMENT BY 1;

CREATE SEQUENCE nutrition.food_sequence
    START WITH 1
    INCREMENT BY 1;

CREATE SEQUENCE sport.exercise_sequence
    START WITH 1
    INCREMENT BY 1;

CREATE SEQUENCE sport.serie_sequence
    START WITH 1
    INCREMENT BY 1;

CREATE SEQUENCE sport.workout_sequence
    START WITH 1
    INCREMENT BY 1;

CREATE SEQUENCE activities.activity_sequence
    START WITH 1
    INCREMENT BY 1;

CREATE SEQUENCE activities.activity_save_sequence
    START WITH 1
    INCREMENT BY 1;

CREATE SEQUENCE activities.activity_done_sequence
    START WITH 1
    INCREMENT BY 1;

CREATE SEQUENCE public.user_sequence
    START WITH 1
    INCREMENT BY 1;

-- Insertion

INSERT INTO public.user VALUES (nextval('public.user_sequence'), 'user1', 'admin','email@admin.fr','admin1234', '2021-01-01', '1990-01-01', 80, 180, 15);

INSERT INTO activities.activity VALUES (nextval('activities.activity_sequence'), 'activity1', 'icon1', 'unity1', 'description1','category1');
INSERT INTO activities.activity VALUES (nextval('activities.activity_sequence'), 'activity2', 'icon2', 'unity2', 'description2','category2');
INSERT INTO activities.activity VALUES (nextval('activities.activity_sequence'), 'activity3', 'icon3', 'unity3', 'description3','category3');

INSERT INTO activities.activity_save VALUES (nextval('activities.activity_save_sequence'), 1, 1, 3, 5,3,"Felt bad");
INSERT INTO activities.activity_save VALUES (nextval('activities.activity_save_sequence'), 2, 1, 2, 3,5,"Nice feelings");
INSERT INTO activities.activity_save VALUES (nextval('activities.activity_save_sequence'), 3, 1, 1, 1, 1,"worst experience of my life");

INSERT INTO activities.activity_done VALUES (nextval('activities.activity_done_sequence'), '2021-01-01', 5,1);
INSERT INTO activities.activity_done VALUES (nextval('activities.activity_done_sequence'), '2021-01-02', 3,2);
INSERT INTO activities.activity_done VALUES (nextval('activities.activity_done_sequence'), '2021-01-03', 1,3);

INSERT INTO sport.workout VALUES (nextval('sport.workout_sequence'), '2021-01-01', 'sport1', 'sport1 description', 5, 60, 1);
INSERT INTO sport.workout VALUES (nextval('sport.workout_sequence'), '2021-01-02', 'sport2', 'sport2 description', 3, 45, 1);
INSERT INTO sport.workout VALUES (nextval('sport.workout_sequence'), '2021-01-03', 'sport3', 'sport3 description', 1, 30, 1);

INSERT INTO sport.exercise VALUES (nextval('sport.exercise_sequence'), 'exercise1', 'exercise1 description', 'icon1', 5, 1);
INSERT INTO sport.exercise VALUES (nextval('sport.exercise_sequence'), 'exercise2', 'exercise2 description', 'icon2', 3, 2);
INSERT INTO sport.exercise VALUES (nextval('sport.exercise_sequence'), 'exercise3', 'exercise3 description', 'icon3', 1, 3);

INSERT INTO sport.serie VALUES (nextval('sport.serie_sequence'), 1, 1);
INSERT INTO sport.serie VALUES (nextval('sport.serie_sequence'), 2, 2);
INSERT INTO sport.serie VALUES (nextval('sport.serie_sequence'), 3, 3);

INSERT INTO nutrition.food VALUES (nextval('nutrition.food_sequence'), 'food1', 100, 'icon1');
INSERT INTO nutrition.food VALUES (nextval('nutrition.food_sequence'), 'food2', 200, 'icon2');
INSERT INTO nutrition.food VALUES (nextval('nutrition.food_sequence'), 'food3', 300, 'icon3');

INSERT INTO nutrition.meal VALUES (nextval('nutrition.meal_sequence'), '2021-01-01', 1);
INSERT INTO nutrition.meal VALUES (nextval('nutrition.meal_sequence'), '2021-01-02', 1);
INSERT INTO nutrition.meal VALUES (nextval('nutrition.meal_sequence'), '2021-01-03', 1);

INSERT INTO nutrition.eaten VALUES (nextval('nutrition.eaten_sequence'), 1, 100);
INSERT INTO nutrition.eaten VALUES (nextval('nutrition.eaten_sequence'), 2, 200);
INSERT INTO nutrition.eaten VALUES (nextval('nutrition.eaten_sequence'), 3, 300);
