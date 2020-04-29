CREATE TABLE resources (
    id              BIGSERIAL NOT NULL,
    user_id         bigint not null,
    file_name       VARCHAR(64),
    s3key           varchar(512),
    creation_time   TIMESTAMP
);
ALTER TABLE resources ADD CONSTRAINT resources_pk PRIMARY KEY ( id );