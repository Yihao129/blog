CREATE TABLE post (
    /*id            INTEGER NOT NULL default nextval('employee_id_seq'),*/
    id              SERIAL NOT NULL,
    author          VARCHAR(30) not null,
    content         VARCHAR(300),
    date            date default CURRENT_DATE,
    author_id       INT
);