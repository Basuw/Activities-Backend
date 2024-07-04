--  Sequence creation
CREATE SCHEMA activities;
CREATE SCHEMA workout;
CREATE SCHEMA nutrition;

CREATE TABLE public."User" (
    id INTEGER PRIMARY KEY,
    username VARCHAR,
    mail VARCHAR,
    password VARCHAR,
    role VARCHAR,
    created_at TIMESTAMP,
    birth_date TIMESTAMP,
    weight NUMERIC,
    height NUMERIC,
    fat NUMERIC
);

CREATE TABLE activities."Activity" (
       id INTEGER PRIMARY KEY,
       name VARCHAR,
       icon VARCHAR,
       unity VARCHAR,
       description VARCHAR
);

CREATE TABLE activities."ActivitySave" (
    id INTEGER PRIMARY KEY,
    activity_id INTEGER,
    user_id INTEGER,
    frequence INTEGER,
    objective INTEGER,
    FOREIGN KEY (activity_id) REFERENCES activities."Activity" (id),
    FOREIGN KEY (user_id) REFERENCES public."User" (id)
);

CREATE TABLE activities."ActivityDone" (
    id INTEGER PRIMARY KEY,
    done_on TIMESTAMP,
    achievement INTEGER
);

CREATE TABLE workout."Workout" (
    id INTEGER PRIMARY KEY,
    done_on TIMESTAMP,
    name VARCHAR,
    description VARCHAR,
    mark INTEGER,
    duration INTEGER,
    user_id INTEGER,
    FOREIGN KEY (user_id) REFERENCES public."User" (id)
);

CREATE TABLE workout."Exercise" (
    id INTEGER PRIMARY KEY,
    name VARCHAR,
    description VARCHAR,
    icon VARCHAR,
    intensity INTEGER,
    workout_id INTEGER,
    FOREIGN KEY (workout_id) REFERENCES workout."Workout" (id)
);

CREATE TABLE workout."Serie" (
    id INTEGER PRIMARY KEY,
    number INTEGER,
    exercise_id INTEGER,
    FOREIGN KEY (exercise_id) REFERENCES workout."Exercise" (id)
);

CREATE TABLE nutrition."Food" (
    id INTEGER PRIMARY KEY,
    name VARCHAR,
    kcal INTEGER,
    icon VARCHAR
);

CREATE TABLE nutrition."Meal" (
    id INTEGER PRIMARY KEY,
    done_on TIMESTAMP,
    user_id INTEGER,
    FOREIGN KEY (user_id) REFERENCES public."User" (id)
);

CREATE TABLE nutrition."Eaten" (
    meal_id INTEGER,
    food_id INTEGER,
    quantity INTEGER,
    PRIMARY KEY (meal_id, food_id),
    FOREIGN KEY (meal_id) REFERENCES nutrition."Meal" (id),
    FOREIGN KEY (food_id) REFERENCES nutrition."Food" (id)
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

CREATE SEQUENCE workout.exercise_sequence
    START WITH 1
    INCREMENT BY 1;

CREATE SEQUENCE workout.serie_sequence
    START WITH 1
    INCREMENT BY 1;

CREATE SEQUENCE workout.workout
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

INSERT INTO public."User" VALUES (nextval('public.user_sequence'), 'user1', 'admin','email@admin.fr','admin1234', '2021-01-01', '1990-01-01', 80, 180, 15);

INSERT INTO activities."Activity" VALUES (nextval('activities.activity_sequence'), 'activity1', 'icon1', 'unity1', 'description1');
INSERT INTO activities."Activity" VALUES (nextval('activities.activity_sequence'), 'activity2', 'icon2', 'unity2', 'description2');
INSERT INTO activities."Activity" VALUES (nextval('activities.activity_sequence'), 'activity3', 'icon3', 'unity3', 'description3');

INSERT INTO activities."ActivitySave" VALUES (nextval('activities.activity_save_sequence'), 1, 1, 3, 5);
INSERT INTO activities."ActivitySave" VALUES (nextval('activities.activity_save_sequence'), 2, 1, 2, 3);
INSERT INTO activities."ActivitySave" VALUES (nextval('activities.activity_save_sequence'), 3, 1, 1, 1);

INSERT INTO activities."ActivityDone" VALUES (nextval('activities.activity_done_sequence'), '2021-01-01', 5);
INSERT INTO activities."ActivityDone" VALUES (nextval('activities.activity_done_sequence'), '2021-01-02', 3);
INSERT INTO activities."ActivityDone" VALUES (nextval('activities.activity_done_sequence'), '2021-01-03', 1);

INSERT INTO workout."Workout" VALUES (nextval('workout.workout'), '2021-01-01', 'workout1', 'workout1 description', 5, 60, 1);
INSERT INTO workout."Workout" VALUES (nextval('workout.workout'), '2021-01-02', 'workout2', 'workout2 description', 3, 45, 1);
INSERT INTO workout."Workout" VALUES (nextval('workout.workout'), '2021-01-03', 'workout3', 'workout3 description', 1, 30, 1);

INSERT INTO workout."Exercise" VALUES (nextval('workout.exercise_sequence'), 'exercise1', 'exercise1 description', 'icon1', 5, 1);
INSERT INTO workout."Exercise" VALUES (nextval('workout.exercise_sequence'), 'exercise2', 'exercise2 description', 'icon2', 3, 2);
INSERT INTO workout."Exercise" VALUES (nextval('workout.exercise_sequence'), 'exercise3', 'exercise3 description', 'icon3', 1, 3);

INSERT INTO workout."Serie" VALUES (nextval('workout.serie_sequence'), 1, 1);
INSERT INTO workout."Serie" VALUES (nextval('workout.serie_sequence'), 2, 2);
INSERT INTO workout."Serie" VALUES (nextval('workout.serie_sequence'), 3, 3);

INSERT INTO nutrition."Food" VALUES (nextval('nutrition.food_sequence'), 'food1', 100, 'icon1');
INSERT INTO nutrition."Food" VALUES (nextval('nutrition.food_sequence'), 'food2', 200, 'icon2');
INSERT INTO nutrition."Food" VALUES (nextval('nutrition.food_sequence'), 'food3', 300, 'icon3');

INSERT INTO nutrition."Meal" VALUES (nextval('nutrition.meal_sequence'), '2021-01-01', 1);
INSERT INTO nutrition."Meal" VALUES (nextval('nutrition.meal_sequence'), '2021-01-02', 1);
INSERT INTO nutrition."Meal" VALUES (nextval('nutrition.meal_sequence'), '2021-01-03', 1);

INSERT INTO nutrition."Eaten" VALUES (1, 1, 100);
INSERT INTO nutrition."Eaten" VALUES (2, 2, 200);
INSERT INTO nutrition."Eaten" VALUES (3, 3, 300);
