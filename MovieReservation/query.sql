
create table member(
	id varchar(20) primary key,
	name varchar(20) not null,
	password varchar(20) not null,
	phone varchar(11),
	jumin varchar(6) not null,
	email varchar(30) not null,
	grade varchar(10),
	buycount int
);

create table movie(
	sNum int auto_increment primary key,
	title varchar(20) not null,
	price varchar(10) not null,
	outline varchar(10) not null,
	nation varchar(20) not null,
	opendate date not null,
	director varchar(20) not null,
	actor varchar(130) not null,
	limitAge int
);

create table seat(
	
);

create table reservation(
	reserNum smallint(5) auto_increment primary key,
	id varchar(20) not null,
	sNum int not null,
	screenNum char(1) not null,
	playtime varchar(20) not null,
	seatNum int,
	playdate char(10)
);

create table food2(
	id varchar(20),
	tdate timestamp,
	type varchar(255),
	price int,
	primary key(id,tdate)
);


--교수님 아래 insert 쿼리 실행하셔야 영화 포스터 클릭했을때 영화정보가 보입니다.

insert into movie(title,price,outline,nation,opendate,director,actor,limitAge) 
values('보헤미안 랩소디','15000','드라마','미국,영국','2018-10-31','브라이언 싱어','라미 밀렉,루시 보인턴,..',12);

insert into movie(title,price,outline,nation,opendate,director,actor,limitAge) 
values('국가부도의 날','15000','드라마','한국','2018-11-18','최국희','김혜수,유아인,허준호,..',12);

insert into movie(title,price,outline,nation,opendate,director,actor,limitAge) 
values('성난 황소','15000','범죄,액션','한국','2018-11-22','김민호','마동석,송지효,..',15);

insert into movie(title,price,outline,nation,opendate,director,actor,limitAge) 
values('완벽한 타인','15000','드라마,코미디','한국','2018-10-31','이재규','유해진,조진웅,이서진,..',15);

insert into movie(title,price,outline,nation,opendate,director,actor,limitAge) 
values('신비한 동물들과 그린델왈드의 범죄','15000','모험,가족,판타지','미국,영국','2018-11-14','데이빗 예이츠','에디 레드메인,조니 뎁,..',12);

insert into movie(title,price,outline,nation,opendate,director,actor,limitAge) 
values('투 프렌즈','15000','애니메이션,모험','러시아연방','2018-11-29','빅터 아즈에프','조연우(맥스 목소리),신정훈(밥 목소리)..',0);

insert into movie(title,price,outline,nation,opendate,director,actor,limitAge) 
values('베일리 어게인','15000','모험,코미디,드라마','미국','2018-11-22','라세 할스트롬','조시 게드,데니스 퀘이드,..',0);

insert into movie(title,price,outline,nation,opendate,director,actor,limitAge) 
values('거미줄에 걸린 소녀','15000','범죄,액션,스릴러','미국,영국,스웨덴,독일','2018-11-28','페데 알바레즈','클레이 포이,실비아 획스,..',15);






















