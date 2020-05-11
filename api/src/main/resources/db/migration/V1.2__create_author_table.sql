CREATE TABLE author (
    /*id            INTEGER NOT NULL default nextval('employee_id_seq'),*/
    id              SERIAL NOT NULL,
    name            VARCHAR(30) not null unique,
    email           VARCHAR(300),
    register_date   date default CURRENT_DATE
);