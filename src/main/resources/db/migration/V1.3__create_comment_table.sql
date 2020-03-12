CREATE TABLE comment (
    /*id            INTEGER NOT NULL default nextval('employee_id_seq'),*/
    id              SERIAL NOT NULL,
    content         VARCHAR(300),
    post_id         INT
);
