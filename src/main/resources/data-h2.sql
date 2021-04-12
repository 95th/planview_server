insert into user (first_name, last_name, date_of_birth, email, city, state, country, zip, user_name, password, role)
values (
    'the', 'admin', PARSEDATETIME('01-01-2001', 'dd-MM-yyyy'), 'admin@planview.com', 'admin city', 'admin state',
    'admin country', 'admin zip', 'admin', '$2y$12$PqHYbsHorZGuRQNbI4C7wuUl5LqoNUUTe5Q4TCap/P3Ue1zjRz2Qu', 'ADMIN'
);
