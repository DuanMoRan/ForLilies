create database test1
use test1

create table book(
	id int primary key not null ,
	name varchar(20) not null,
	author varchar(20) not null,
	publicationDate date ,
	pages int not null ,
	typeOfBook varchar(30) not null ,
	price float not null
)

-- 1.1
insert into book(id , name , author , publicationDate , pages , typeOfBook , price) values
	(1 , '001' , '001' , '01-01-01' , 1 , '001' , 1.00),
	(2 , '002' , '002' , '02-02-02' , 2 , '002' , 2.00),
	(3 , '003' , '003' , '03-03-03' , 3 , '003' , 3.00)

-- 1.2
update book set price = price + 5 where author = '001'

-- 1.3
update book set price = case 
		when price - 5 < 10 then 10 
		else price - 5 
	end 
	where pages < 3 and typeOfBook = '002'
-- 1.4
delete from book where publicationDate = '01-01-01'

-- 1.5
go
create database test2
use test2

create table songs(
	id int not null primary key,
	name varchar(20) not null,
	playbackDuration int not null,
	singer varchar(20) not null,
	type varchar(20) not null
)

-- 1.6
create user anybodyWhoCare for login test
	with password = '123456'

-- 1.7
grant insert , delete , update , select on book to anybodyWhoCare
grant select on songs to anybodyWhoCare



create table student(
	id int not null primary key,
	name varchar(20) not null,
	age int not null,
	gender varchar(20),
	address varchar(20)
)

create table course(
	id int not null primary key,
	name varchar(20) not null,
	classHour int 
)

create table grade(
	id int not null primary key,
	idOfStu int not null,
	idOfCou int not null,
	grade int ,
	examTime date,
	foreign key (idOfStu) references student(id) ,
	foreign key (idOfCou) references course(id)
)

-- 2.1
insert into student(id , name , age , gender , address) values
	(111 , '111' , 21 , 'ÄÐ' , '111'),
	(222 , '222' , 22 , 'Å®' , '222'),
	(333 , '333' , 23 , 'ÄÐ' , '333')
insert into course(id , name , classHour) values
	(1 , '1' , 10),
	(2 , '2' , 12)
insert into grade(id , idOfStu , idOfCou , grade , examTime) values
	(1 , 111 , 1 , 10 , '01-01-01'),
	(2 , 111 , 2 , 20 , '01-01-02'),
	(3 , 222 , 1 , 30 , '01-01-01'),
	(4 , 222 , 2 , 40 , '01-01-02'),
	(5 , 333 , 1 , 50 , '01-01-01'),
	(6 , 333 , 2 , 60 , '01-01-02')

-- 2.2
select top 1 * from course order by classHour desc

-- 2.3
select age , COUNT(id) from student group by age

-- 2.4
select name ,  AVG(gender) from grade inner join student on student.id = idOfStu 
	group by idOfStu

-- 2.5
select idOfCou , MAX(grade) from grade group by idOfCou

-- 2.6
select s.id , s.name , c.name , grade , examTime from grade 
	inner join student s on s.id = idOfStu 
	inner join course c on c.id = idOfCou
	where DATEDIFF(day , examTime , GETDATE()) >= 0

-- 2.7
select top 1 s.name from grade
	inner join student s on s.id = idOfStu
	inner join course c on c.id = idOfCou
	where DATEDIFF(day , examTime , GETDATE()) >= 0
		and c.name = '1'
	order by age

-- 2.8
select top 1 name from student s
	inner join grade on s.id = idOfStu
	where DATEDIFF(day , examTime , GETDATE()) >= 0
	order by DATEDIFF(day , examTime , GETDATE())

-- 2.9
select name from course
	where classHour > (
		select classHour from course where name = '1'
	)

-- 2.10
select s.name from grade
	inner join student s on s.id = idOfStu
	inner join course c on c.id = idOfCou
	where c.name = '2' and grade > (
		select top 1 grade from grade 
			inner join student s on s.id = idOfStu
			inner join course c on c.id = idOfCou
			where gender = 'Å®' and c.name = '2'
			order by grade
	)
	and gender = 'ÄÐ'