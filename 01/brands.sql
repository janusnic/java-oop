create table brands
(
  brand_id int unsigned not null auto_increment,
  brandName varchar(255) not null,
  
  primary key (brand_id)
);

insert into brands (brandName) values ('Peugeot');
insert into brands (brandName) values ('Honda');
insert into brands (brandName) values ('Volkswagen');
