-- Schema creation
CREATE SCHEMA activities;
CREATE SCHEMA sport;
CREATE SCHEMA nutrition;

-- Tables
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
    category VARCHAR,
    user_id INTEGER,
    FOREIGN KEY (user_id) REFERENCES public.user (id)
);

CREATE TABLE activities.category (
    id INTEGER PRIMARY KEY,
    name VARCHAR,
    icon VARCHAR,
    description VARCHAR
);

CREATE TABLE activities.activity_save_group (
    id INTEGER PRIMARY KEY
);

CREATE TABLE activities.activity_save (
    id INTEGER PRIMARY KEY,
    activity_id INTEGER NOT NULL,
    user_id INTEGER NOT NULL,
    activity_save_group_id INTEGER NOT NULL,
    frequency INTEGER,
    objective INTEGER,
    mark INTEGER,
    notes VARCHAR,
    time TIMESTAMP,
    day TIMESTAMP,
    FOREIGN KEY (activity_id) REFERENCES activities.activity (id),
    FOREIGN KEY (user_id) REFERENCES public.user (id),
    FOREIGN KEY (activity_save_group_id) REFERENCES activities.activity_save_group (id)
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

-- Sequences
CREATE SEQUENCE nutrition.eaten_sequence    START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE nutrition.meal_sequence     START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE nutrition.food_sequence     START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE sport.exercise_sequence     START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE sport.serie_sequence        START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE sport.workout_sequence      START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE activities.activity_sequence      START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE activities.activity_save_group_sequence START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE activities.activity_save_sequence START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE activities.activity_done_sequence START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE public.user_sequence        START WITH 1 INCREMENT BY 1;

-- ========================
-- USERS
-- ========================
-- Password is SHA-256 hash of "admin1234"
INSERT INTO public.user VALUES (nextval('public.user_sequence'), 'Bastien', 'bastien@gmail.com', 'ac9689e2272427085e35b9d3e3e8bed88cb3434828b43b86fc0596cad4c6e270', 'admin', '2021-01-01', '1995-06-15', 73.4, 1.75, 15, 70);

-- ========================
-- ACTIVITIES (common — user_id NULL = available to all)
-- ========================

-- Fitness
INSERT INTO activities.activity (id, name, icon, unity, description, category)
VALUES (nextval('activities.activity_sequence'), 'Running', 'run-fast', 'km', 'Outdoor or treadmill running', 'Fitness');

INSERT INTO activities.activity (id, name, icon, unity, description, category)
VALUES (nextval('activities.activity_sequence'), 'Swimming', 'swim', 'laps', 'Pool or open water swimming', 'Fitness');

INSERT INTO activities.activity (id, name, icon, unity, description, category)
VALUES (nextval('activities.activity_sequence'), 'Cycling', 'bike', 'km', 'Road or mountain biking', 'Fitness');

INSERT INTO activities.activity (id, name, icon, unity, description, category)
VALUES (nextval('activities.activity_sequence'), 'Weight Training', 'weight-lifter', 'sets', 'Strength and resistance training', 'Fitness');

INSERT INTO activities.activity (id, name, icon, unity, description, category)
VALUES (nextval('activities.activity_sequence'), 'Yoga', 'yoga', 'min', 'Yoga practice and flexibility', 'Fitness');

INSERT INTO activities.activity (id, name, icon, unity, description, category)
VALUES (nextval('activities.activity_sequence'), 'Walking', 'walk', 'steps', 'Daily walking or hiking', 'Fitness');

INSERT INTO activities.activity (id, name, icon, unity, description, category)
VALUES (nextval('activities.activity_sequence'), 'Hiking', 'hiking', 'km', 'Trail hiking in nature', 'Fitness');

INSERT INTO activities.activity (id, name, icon, unity, description, category)
VALUES (nextval('activities.activity_sequence'), 'Rowing', 'rowing', 'min', 'Rowing machine or on water', 'Fitness');

INSERT INTO activities.activity (id, name, icon, unity, description, category)
VALUES (nextval('activities.activity_sequence'), 'Jump Rope', 'jump-rope', 'min', 'Skipping rope cardio', 'Fitness');

INSERT INTO activities.activity (id, name, icon, unity, description, category)
VALUES (nextval('activities.activity_sequence'), 'Push Ups', 'arm-flex', 'reps', 'Bodyweight push-up sets', 'Fitness');

INSERT INTO activities.activity (id, name, icon, unity, description, category)
VALUES (nextval('activities.activity_sequence'), 'Basketball', 'basketball', 'min', 'Basketball game or practice', 'Fitness');

INSERT INTO activities.activity (id, name, icon, unity, description, category)
VALUES (nextval('activities.activity_sequence'), 'Tennis', 'tennis', 'min', 'Tennis singles or doubles', 'Fitness');

-- Health
INSERT INTO activities.activity (id, name, icon, unity, description, category)
VALUES (nextval('activities.activity_sequence'), 'Sleep', 'sleep', 'hours', 'Quality sleep tracking', 'Health');

INSERT INTO activities.activity (id, name, icon, unity, description, category)
VALUES (nextval('activities.activity_sequence'), 'Drink Water', 'cup-water', 'glasses', 'Daily hydration tracking', 'Health');

INSERT INTO activities.activity (id, name, icon, unity, description, category)
VALUES (nextval('activities.activity_sequence'), 'Stretching', 'human-greeting-variant', 'min', 'Daily stretching routine', 'Health');

INSERT INTO activities.activity (id, name, icon, unity, description, category)
VALUES (nextval('activities.activity_sequence'), 'Healthy Meal', 'food-apple', 'meals', 'Eat a nutritious meal', 'Health');

INSERT INTO activities.activity (id, name, icon, unity, description, category)
VALUES (nextval('activities.activity_sequence'), 'No Alcohol', 'glass-cocktail-off', 'days', 'Alcohol-free day', 'Health');

INSERT INTO activities.activity (id, name, icon, unity, description, category)
VALUES (nextval('activities.activity_sequence'), 'Cold Shower', 'shower-head', 'min', 'Cold water exposure', 'Health');

-- Mindfulness
INSERT INTO activities.activity (id, name, icon, unity, description, category)
VALUES (nextval('activities.activity_sequence'), 'Meditation', 'peace', 'min', 'Mindfulness and meditation', 'Mindfulness');

INSERT INTO activities.activity (id, name, icon, unity, description, category)
VALUES (nextval('activities.activity_sequence'), 'Journaling', 'notebook-edit', 'entries', 'Daily journal writing', 'Mindfulness');

INSERT INTO activities.activity (id, name, icon, unity, description, category)
VALUES (nextval('activities.activity_sequence'), 'Gratitude', 'heart', 'items', 'Write what you are grateful for', 'Mindfulness');

INSERT INTO activities.activity (id, name, icon, unity, description, category)
VALUES (nextval('activities.activity_sequence'), 'Breathing', 'lungs', 'min', 'Breathing exercises', 'Mindfulness');

-- Learning
INSERT INTO activities.activity (id, name, icon, unity, description, category)
VALUES (nextval('activities.activity_sequence'), 'Reading', 'book-open-variant', 'pages', 'Books, articles or essays', 'Learning');

INSERT INTO activities.activity (id, name, icon, unity, description, category)
VALUES (nextval('activities.activity_sequence'), 'Coding', 'code-braces', 'min', 'Programming practice', 'Learning');

INSERT INTO activities.activity (id, name, icon, unity, description, category)
VALUES (nextval('activities.activity_sequence'), 'Language Practice', 'translate', 'min', 'Foreign language learning', 'Learning');

INSERT INTO activities.activity (id, name, icon, unity, description, category)
VALUES (nextval('activities.activity_sequence'), 'Online Course', 'laptop', 'min', 'E-learning and video courses', 'Learning');

INSERT INTO activities.activity (id, name, icon, unity, description, category)
VALUES (nextval('activities.activity_sequence'), 'Podcast', 'podcast', 'episodes', 'Educational or inspiring podcasts', 'Learning');

INSERT INTO activities.activity (id, name, icon, unity, description, category)
VALUES (nextval('activities.activity_sequence'), 'Flashcards', 'cards-playing-outline', 'cards', 'Spaced repetition study cards', 'Learning');

-- Creative
INSERT INTO activities.activity (id, name, icon, unity, description, category)
VALUES (nextval('activities.activity_sequence'), 'Drawing', 'pencil-box', 'min', 'Sketching or digital drawing', 'Creative');

INSERT INTO activities.activity (id, name, icon, unity, description, category)
VALUES (nextval('activities.activity_sequence'), 'Music Practice', 'music', 'min', 'Instrument or vocal practice', 'Creative');

INSERT INTO activities.activity (id, name, icon, unity, description, category)
VALUES (nextval('activities.activity_sequence'), 'Photography', 'camera', 'photos', 'Photography outing or project', 'Creative');

INSERT INTO activities.activity (id, name, icon, unity, description, category)
VALUES (nextval('activities.activity_sequence'), 'Writing', 'pencil', 'words', 'Creative writing or blogging', 'Creative');


INSERT INTO activities.activity_save_group VALUES (nextval('activities.activity_save_group_sequence'));
INSERT INTO activities.activity_save_group VALUES (nextval('activities.activity_save_group_sequence'));
INSERT INTO activities.activity_save_group VALUES (nextval('activities.activity_save_group_sequence'));

-- ========================
-- ACTIVITY SAVES (user 1 subscribes to some common activities)
-- Running: 3 times/week, objective 5km
INSERT INTO activities.activity_save VALUES (nextval('activities.activity_save_sequence'), 1, 1,1, 3, 5, NULL, NULL, NULL, NULL);
-- Reading: 5 times/week, objective 30 pages
INSERT INTO activities.activity_save VALUES (nextval('activities.activity_save_sequence'), 23, 1,2, 5, 30, NULL, NULL, NULL, NULL);
-- Meditation: 7 times/week, objective 10 min
INSERT INTO activities.activity_save VALUES (nextval('activities.activity_save_sequence'), 19, 1, 3, 10, NULL, NULL, NULL, NULL);

-- Sport
INSERT INTO sport.workout VALUES (nextval('sport.workout_sequence'), '2024-01-01', 'Morning session', 'Full body workout', 4, 45, 1);
INSERT INTO sport.exercise VALUES (nextval('sport.exercise_sequence'), 'Bench Press', 'Chest press on flat bench', 'weight-lifter', 4, 1);
INSERT INTO sport.serie VALUES (nextval('sport.serie_sequence'), 3, 1);

-- Nutrition
INSERT INTO nutrition.food VALUES (nextval('nutrition.food_sequence'), 'Apple', 52, 'food-apple');
INSERT INTO nutrition.food VALUES (nextval('nutrition.food_sequence'), 'Chicken Breast', 165, 'food-drumstick');
INSERT INTO nutrition.food VALUES (nextval('nutrition.food_sequence'), 'Brown Rice', 218, 'rice');
