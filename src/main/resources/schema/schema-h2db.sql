DROP TABLE IF EXISTS help_desk;

create table help_desk (
    help_desk_id int not null,
    summary varchar(1024) not null,
    user_name varchar(50) not null,
    status varchar(50),
    created_at timestamp not null,
    eta timestamp not null,
    primary key (help_desk_id)
);