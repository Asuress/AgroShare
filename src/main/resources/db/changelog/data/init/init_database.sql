--liquibase formatted sql

--changeset mkryuchkov:init_database logicalFilePath:/data/init/init_database.sql
create table categories
(
    category_id BIGINT PRIMARY KEY,
    title       VARCHAR(255)
);

create index category_title
    on categories (title);

CREATE SEQUENCE categories_s;

insert into public.categories (category_id,
                               title)
select nextval('categories_s'),
       values.name
from (select 'Удобрения' name
      union all
      select 'Минеральные удобрения' name
      union all
      select 'Органические удобрения' name
      union all
      select 'Макроудобрения' name
      union all
      select 'Микроудобрения' name
      union all
      select 'Техника' name
      union all
      select 'Тракторы' name
      union all
      select 'Комбайны' name
      union all
      select 'Самоходные комбайны' name
      union all
      select 'Навесные комбайны' name
      union all
      select 'Агрегаты' name
      union all
      select 'Плуг' name
      union all
      select 'Борона' name
      union all
      select 'Лущильник' name
      union all
      select 'Глубокорыхлитель' name
      union all
      select 'Культиватор' name
      union all
      select 'Рассадник' name
      union all
      select 'Опрыскиватель' name
      union all
      select 'Дождевальная машина' name
      union all
      select 'Разбрасыватель' name
      union all
      select 'СЗР' name
      union all
      select 'Химические СЗР' name
      union all
      select 'Органические СЗР' name
      union all
      select 'Инсектециды' name
      union all
      select 'Гербециды' name
      union all
      select 'Фунгициды' name
      union all
      select 'Бактарициды' name
      union all
      select 'Семена' name
      union all
      select 'Оригинальные семена' name
      union all
      select 'Элитные семена' name
      union all
      select 'Репродукционные семена' name
      union all
      select 'Корма' name
      union all
      select 'Силос' name
      union all
      select 'Зеленый корм' name
      union all
      select 'Сено' name
      union all
      select 'Комбикорм' name
      union all
      select 'Зерно на корм' name
      union all
      select 'Подкормки' name
      union all
      select 'Биологические активные добавки' name
      union all
      select 'Запчасти' name
      union all
      select 'Другое' name) values;

create table categories_hierarchy
(
    category_hierarchy_id BIGINT PRIMARY KEY,
    parent_id             BIGINT,
    child_id              BIGINT
);

create index category_parent
    on categories_hierarchy (parent_id);

create index category_child
    on categories_hierarchy (child_id);

CREATE SEQUENCE categories_hierarchy_s;

insert into categories_hierarchy (category_hierarchy_id, parent_id, child_id)
select nextval('categories_hierarchy_s'),
       (case
            when values.parent is null or values.parent = ''
                then null
            else
                (select category_id
                 from public.categories c
                 where c.title = values.parent)
           end),
       (select category_id
        from public.categories c
        where c.title = values.child)

from (select null parent, 'Удобрения' child
      union all
      select 'Удобрения' parent, 'Минеральные удобрения' child
      union all
      select 'Удобрения' parent, 'Органические удобрения' child
      union all
      select 'Минеральные удобрения' parent, 'Макроудобрения' child
      union all
      select 'Минеральные удобрения' parent, 'Микроудобрения' child
      union all
      select null parent, 'Техника' child
      union all
      select 'Техника' parent, 'Тракторы' child
      union all
      select 'Техника' parent, 'Комбайны' child
      union all
      select 'Комбайны' parent, 'Самоходные комбайны' child
      union all
      select 'Комбайны' parent, 'Самоходные комбайны' child
      union all
      select 'Комбайны' parent, 'Навесные комбайны' child
      union all
      select 'Техника' parent, 'Агрегаты' child
      union all
      select 'Агрегаты' parent, 'Плуг' child
      union all
      select 'Агрегаты' parent, 'Борона' child
      union all
      select 'Агрегаты' parent, 'Лущильник' child
      union all
      select 'Агрегаты' parent, 'Глубокорыхлитель' child
      union all
      select 'Агрегаты' parent, 'Культиватор' child
      union all
      select 'Агрегаты' parent, 'Рассадник' child
      union all
      select 'Агрегаты' parent, 'Опрыскиватель' child
      union all
      select 'Агрегаты' parent, 'Дождевальная машина' child
      union all
      select 'Агрегаты' parent, 'Разбрасыватель' child
      union all
      select null parent, 'СЗР' child
      union all
      select 'СЗР' parent, 'Органические СЗР' child
      union all
      select 'СЗР' parent, 'Химические СЗР' child
      union all
      select 'Химические СЗР' parent, 'Инсектециды' child
      union all
      select 'Химические СЗР' parent, 'Гербециды' child
      union all
      select 'Химические СЗР' parent, 'Фунгициды' child
      union all
      select 'Химические СЗР' parent, 'Бактерициды' child
      union all
      select null parent, 'Семена' child
      union all
      select 'Семена' parent, 'Оригинальные семена' child
      union all
      select 'Семена' parent, 'Элитные семена' child
      union all
      select 'Семена' parent, 'Репродукционные семена' child
      union all
      select null parent, 'Корма' child
      union all
      select 'Корма' parent, 'Силос' child
      union all
      select 'Корма' parent, 'Зеленый корм' child
      union all
      select 'Корма' parent, 'Сено' child
      union all
      select 'Корма' parent, 'Комбикорм' child
      union all
      select 'Корма' parent, 'Зерно на корм' child
      union all
      select 'Корма' parent, 'Подкормки' child
      union all
      select 'Корма' parent, 'Биологические активные добавки' child
      union all
      select null parent, 'Запчасти' child
      union all
      select null parent, 'Другое' child) values;

create table legal(
    legal_id bigint primary key,
    legal_name varchar(255)
);

create table individual(
    individual_id bigint primary key,
    first_name varchar(255),
    second_name varchar(255),
    last_name varchar(255)
);

alter table usr
add column user_type varchar(2),
add column typed_user_id bigint,
add column email varchar(255),
add column phone_number varchar(20),
add column image bytea,
add column location varchar(255),
add column inn varchar(20);

create table basket(
    basket_id bigint primary key,
    user_id bigint,
    order_id bigint
)
create sequence basket_s;

alter table public.publication
add column user_id bigint,
add column price varchar,
add column publication_type varchar(2),
add column category bigint,
add column descrition bigint;
