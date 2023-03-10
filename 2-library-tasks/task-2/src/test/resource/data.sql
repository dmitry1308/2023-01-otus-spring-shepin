insert into genre (id, `name`)
values (10, 'Genre-1');
insert into genre (id, `name`)
values (20, 'Genre-2');
insert into genre (id, `name`)
values (30, 'Genre-3');

insert into author (id, `first_name`, `last_name`)
values (10, 'Last name 1', 'First Name 1');
insert into author (id, `first_name`, `last_name`)
values (20, 'Last name 2', 'First Name 2');
insert into author (id, `first_name`, `last_name`)
values (30, 'Last name 3', 'First Name 3');

insert into book (id, `name`, `author_id`, `genre_id`) values (10,'Book name 1', 10, 10);
insert into book (id, `name`, `author_id`, `genre_id`) values (20,'Book name 2', 20, 20);
insert into book (id, `name`, `author_id`, `genre_id`) values (30,'Book name 3', 20, 20);
