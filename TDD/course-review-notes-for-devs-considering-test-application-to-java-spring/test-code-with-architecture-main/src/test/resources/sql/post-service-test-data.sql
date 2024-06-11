insert into `users` (`id`, `email`, `nickname`, `address`, `certification_code`, `status`, `last_login_at`)
values (99, 'jos@gmail.com', 'jos', 'Seoul', 'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaa', 'ACTIVE', 0);
insert into `users` (`id`, `email`, `nickname`, `address`, `certification_code`, `status`, `last_login_at`)
values (2, 'jos2@gmail.com', 'jos2', 'Seoul', 'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaab', 'PENDING', 0);
insert into `posts` (`id`, `content`, `created_at`, `modified_at`, `user_id`)
values (99, 'written content1', 0, 0, 99);