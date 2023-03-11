insert into genre (id, `name`)
values (100, 'Genre-1');
insert into genre (id, `name`)
values (200, 'Genre-2');
insert into genre (id, `name`)
values (300, 'Genre-3');

insert into author (id, `first_name`, `last_name`)
values (100, 'Last name 1', 'First Name 1');
insert into author (id, `first_name`, `last_name`)
values (200, 'Last name 2', 'First Name 2');
insert into author (id, `first_name`, `last_name`)
values (300, 'Last name 3', 'First Name 3');

insert into book (id, `name`, `author_id`, `genre_id`)
values (100, 'Book name 1', 100, 100);
insert into book (id, `name`, `author_id`, `genre_id`)
values (200, 'Book name 2', 200, 200);
insert into book (id, `name`, `author_id`, `genre_id`)
values (300, 'Book name 3', 200, 200);

insert into comment(id, comment_text)
values (100, 'Comment 1');
insert into comment(id, comment_text)
values (200, 'Comment 2');
insert into comment(id, comment_text)
values (300, 'Comment 3');
insert into comment(id, comment_text)
values (400, 'Comment 4');
insert into comment(id, comment_text)
values (500, 'Comment 5');
insert into comment(id, comment_text)
values (600, 'Comment 6');

insert into book_comment(id, book_id, comment_id)
values (100, 100, 100);
insert into book_comment(id, book_id, comment_id)
values (200, 100, 200);
insert into book_comment(id, book_id, comment_id)
values (300, 200, 300);
insert into book_comment(id, book_id, comment_id)
values (400, 200, 400);
insert into book_comment(id, book_id, comment_id)
values (500, 300, 500);
insert into book_comment(id, book_id, comment_id)
values (600, 300, 600);


