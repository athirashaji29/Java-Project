CREATE TABLE `movie` (
  `columnid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) DEFAULT NULL,
  `format` varchar(30) DEFAULT NULL,
  `showdate` date DEFAULT NULL,
  `showtime` time DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `seat` int(11) DEFAULT NULL,
  PRIMARY KEY (`columnid`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

INSERT INTO `movie` VALUES (3,'Vikram','Tamil 2D','2022-04-10','12:00:00',300.00,131),(5,' RRR ','Tamil 3D','2022-04-10','21:00:00',250.00,192),(7,'Avatar','Hindi 3D','2019-11-15','12:00:00',120.00,200),(8,'KGF 2','Hindi 3D','2022-04-10','12:00:00',100.00,100);

create table user(user_id int(11) NOT NULL AUTO_INCREMENT,name varchar(50),password varchar(50),constraint PRIMARY KEY(user_id));

insert into user values(1,'Athira Shaji','12345'),(2,'PS Anusha','12345'),(3,'Malavika','12345'),(4,'Dimna','1234
5'),(6,'Arjun','12345'),(7,'Daniel','12345');

CREATE TABLE `customer` (
    ->   `columnid` int(11) NOT NULL,
    ->   `Mname` varchar(30) DEFAULT NULL,
    ->   `format` varchar(30) DEFAULT NULL,
    ->   `date` date DEFAULT NULL,
    ->   `time` time DEFAULT NULL,
    ->   `price` decimal(10,2) DEFAULT NULL,
    ->   `seat` int(11) DEFAULT NULL,
    ->   `Cid` int(11) NOT NULL AUTO_INCREMENT,
    ->   `user_id` int(11) NOT NULL,
    ->   PRIMARY KEY (`Cid`),
    ->   KEY `columnid` (`columnid`),
    ->   CONSTRAINT `customer_ibfk_1` FOREIGN KEY (`columnid`) REFERENCES `movie` (`columnid`) ON DELETE CASCADE ON UPDATE CASCADE,
    ->   CONSTRAINT `customer_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
    -> ) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=latin1;

INSERT INTO `customer` VALUES (5,'RRR','Tamil 3D','2022-04-10','21:00:00',1000.00,4,19,1),(5,'RRR','Tamil 3D','2022-04-10','21:00:00',500.00,2,20,2),(3,'Vikram',' Tamil 2D','2022-04-10','12:00:00',1500.00,5,21,3),(3,'Vikram','Tamil 2D','2022-04-20','12:00:00',3600.00,12,23,4),(5,'RRR','Tamil 3D','2022-05-10','21:00:00',500.00,2,24,6),(3,'Vikram','Tamil 2D','2022-10-10','12:00:00',600.00,2,26,7);