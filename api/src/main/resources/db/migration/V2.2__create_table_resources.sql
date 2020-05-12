CREATE TABLE resource (
    id              BIGSERIAL NOT NULL,
    user_id         bigint not null,
    file_name       VARCHAR(64),
    s3key           varchar(512),
    creation_time   TIMESTAMP
);
ALTER TABLE resource ADD CONSTRAINT resources_pk PRIMARY KEY ( id );

alter table resource
ADD CONSTRAINT FK_resource_users
foreign key (user_id) references users(id)  on delete cascade;