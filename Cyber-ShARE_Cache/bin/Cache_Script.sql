drop table if exists cachedb;
create table cachedb(
	URL longtext default null,
	VIZ longtext default null,
	Num_Accessed int default 0,
	Hash int default 0,
	Last_Accessed timestamp default current_timestamp not null on update current_timestamp,
	Last_Updated timestamp default current_timestamp not null,
	Dirty tinyint default 0,
	Unreachable tinyint default 0,
	ID int not null unique auto_increment

);