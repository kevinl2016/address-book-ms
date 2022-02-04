drop table if exists address_book;

create table address_book ( id varchar(36) not null,
                            created_date timestamp,
                            last_modified_date timestamp,
                            version bigint,
                            name varchar(50) not null,
                            user_id varchar(50) not null,
                            primary key (id) );

drop table if exists contact;

create table contact ( id varchar(36) not null,
                       created_date timestamp,
                       last_modified_date timestamp,
                       version bigint,
                       first_name varchar(50) not null,
                       last_name varchar(50),
                       phone_number varchar(16) not null,
                       address_book_id varchar(36),
                       primary key (id) );
