CREATE TABLE `sportsims`.`members` (
  `id` VARCHAR(50) NOT NULL,
  `password` VARCHAR(20) NOT NULL,
  `nickname` VARCHAR(20) NOT NULL,
  `email` VARCHAR(50) NOT NULL,
  `birthday` DATE NOT NULL,
  `money` INTEGER UNSIGNED NOT NULL,
  `state` INTEGER UNSIGNED NOT NULL,
  PRIMARY KEY (`id`)
)
ENGINE = InnoDB;

CREATE TABLE `sportsims`.`basketballs` (
  `gameId` INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  `dateTime` DATETIME NOT NULL,
  `awayName` VARCHAR(20) NOT NULL,
  `homeName` VARCHAR(20) NOT NULL,
  `pointSpread` DOUBLE NOT NULL,
  `total` DOUBLE UNSIGNED NOT NULL,
  `away` DOUBLE UNSIGNED NOT NULL DEFAULT 1,
  `home` DOUBLE UNSIGNED NOT NULL DEFAULT 1,
  `over` DOUBLE UNSIGNED NOT NULL DEFAULT 1,
  `under` DOUBLE UNSIGNED NOT NULL DEFAULT 1,
  `awayPk` DOUBLE UNSIGNED NOT NULL DEFAULT 1,
  `homePk` DOUBLE UNSIGNED NOT NULL DEFAULT 1,
  `awayQ1` DOUBLE UNSIGNED NOT NULL DEFAULT 1,
  `drawQ1` DOUBLE UNSIGNED NOT NULL DEFAULT 1,
  `homeQ1` DOUBLE UNSIGNED NOT NULL DEFAULT 1,
  `odd` DOUBLE UNSIGNED NOT NULL DEFAULT 1,
  `even` DOUBLE UNSIGNED NOT NULL DEFAULT 1,
  `away1to5` DOUBLE UNSIGNED NOT NULL DEFAULT 1,
  `away6to10` DOUBLE UNSIGNED NOT NULL DEFAULT 1,
  `away11to15` DOUBLE UNSIGNED NOT NULL DEFAULT 1,
  `away16to20` DOUBLE UNSIGNED NOT NULL DEFAULT 1,
  `away21to25` DOUBLE UNSIGNED NOT NULL DEFAULT 1,
  `away26` DOUBLE UNSIGNED NOT NULL DEFAULT 1,
  `home1to5` DOUBLE UNSIGNED NOT NULL DEFAULT 1,
  `home6to10` DOUBLE UNSIGNED NOT NULL DEFAULT 1,
  `home11to15` DOUBLE UNSIGNED NOT NULL DEFAULT 1,
  `home16to20` DOUBLE UNSIGNED NOT NULL DEFAULT 1,
  `home21to25` DOUBLE UNSIGNED NOT NULL DEFAULT 1,
  `home26` DOUBLE UNSIGNED NOT NULL DEFAULT 1,
  `awayResult` INTEGER UNSIGNED NOT NULL,
  `homeResult` INTEGER UNSIGNED NOT NULL,
  `awayQ1Result` INTEGER UNSIGNED NOT NULL,
  `homeQ1Result` INTEGER UNSIGNED NOT NULL,
  `status` INTEGER UNSIGNED NOT NULL,
  PRIMARY KEY (`gameId`)
)
ENGINE = InnoDB;

INSERT INTO basketballs (dateTime,awayName,homeName,pointSpread,total,away,home,over,under,awayPk,homePk,awayQ1,drawQ1,homeQ1,odd,even,
away1to5,away6to10,away11to15,away16to20,away21to25,away26,home1to5,home6to10,home11to15,home16to20,home21to25,home26,awayResult,homeResult,
awayQ1Result,homeQ1Result,status) VALUES("2016-04-01 07:00:00","布魯克林籃網","克里夫蘭騎士",-14.5,211.5,1.75,1.75,1.75,1.75,1,1,2.65,15,1.25,1.75,1.75,10,18,30,30,30,30,6.25,4.75,4.25,4.5,5.25,3.1,0,0,0,0,0);

INSERT INTO basketballs (dateTime,awayName,homeName,pointSpread,total,away,home,over,under,awayPk,homePk,awayQ1,drawQ1,homeQ1,odd,even,
away1to5,away6to10,away11to15,away16to20,away21to25,away26,home1to5,home6to10,home11to15,home16to20,home21to25,home26,awayResult,homeResult,
awayQ1Result,homeQ1Result,status) VALUES("2016-04-01 08:00:00","丹佛金塊","紐奧良鵜鶘",6.5,210.5,1.75,1.75,1.75,1.75,1.25,2.85,1.5,14,2,1.75,1.75,4.25,4.25,4.75,6,9,8.25,5.5,7.5,12.5,22,30,30,0,0,0,0,0);

INSERT INTO basketballs (dateTime,awayName,homeName,pointSpread,total,away,home,over,under,awayPk,homePk,awayQ1,drawQ1,homeQ1,odd,even,
away1to5,away6to10,away11to15,away16to20,away21to25,away26,home1to5,home6to10,home11to15,home16to20,home21to25,home26,awayResult,homeResult,
awayQ1Result,homeQ1Result,status) VALUES("2016-04-01 10:00:00","波士頓塞爾提克","波特蘭拓荒者",-2.5,214.5,1.75,1.75,1.75,1.75,2.05,1.5,1.8,13,1.6,1.75,1.75,4.25,5.5,8.5,15,30,30,4,4.25,5.5,8.5,15,20,0,0,0,0,0);

INSERT INTO basketballs (dateTime,awayName,homeName,pointSpread,total,away,home,over,under,awayPk,homePk,awayQ1,drawQ1,homeQ1,odd,even,
away1to5,away6to10,away11to15,away16to20,away21to25,away26,home1to5,home6to10,home11to15,home16to20,home21to25,home26,awayResult,homeResult,
awayQ1Result,homeQ1Result,status) VALUES("2016-04-09 07:00:00","布魯克林籃網","夏洛特黃蜂",-15.5,204.5,1.75,1.75,1.75,1.75,1,1,2.8,15,1.22,1.75,1.75,11.5,20,25,30,30,30,6.5,5,4.25,4.25,5,2.85,0,0,0,0,0);

INSERT INTO basketballs (dateTime,awayName,homeName,pointSpread,total,away,home,over,under,awayPk,homePk,awayQ1,drawQ1,homeQ1,odd,even,
away1to5,away6to10,away11to15,away16to20,away21to25,away26,home1to5,home6to10,home11to15,home16to20,home21to25,home26,awayResult,homeResult,
awayQ1Result,homeQ1Result,status) VALUES("2016-04-09 07:00:00","紐約尼克","費城76人",2.5,201.5,1.75,1.75,1.75,1.75,1.5,2.05,1.6,13,1.8,1.75,1.75,3.75,4.25,5.5,8.5,16,22,4.25,5.5,8.5,16,25,30,0,0,0,0,0);

INSERT INTO basketballs (dateTime,awayName,homeName,pointSpread,total,away,home,over,under,awayPk,homePk,awayQ1,drawQ1,homeQ1,odd,even,
away1to5,away6to10,away11to15,away16to20,away21to25,away26,home1to5,home6to10,home11to15,home16to20,home21to25,home26,awayResult,homeResult,
awayQ1Result,homeQ1Result,status) VALUES("2016-04-09 08:00:00","洛杉磯湖人","紐奧良鵜鶘",-1.5,202.5,1.75,1.75,1.75,1.75,1.9,1.6,1.75,13,1.65,1.75,1.75,4,5.25,7.75,14,28,30,3.75,4.25,6,9.5,18,28,0,0,0,0,0);

INSERT INTO basketballs (dateTime,awayName,homeName,pointSpread,total,away,home,over,under,awayPk,homePk,awayQ1,drawQ1,homeQ1,odd,even,
away1to5,away6to10,away11to15,away16to20,away21to25,away26,home1to5,home6to10,home11to15,home16to20,home21to25,home26,awayResult,homeResult,
awayQ1Result,homeQ1Result,status) VALUES("2016-04-09 08:30:00","曼斐斯灰熊","達拉斯小牛",-6.5,194.5,1.75,1.75,1.75,1.75,2.85,1.25,2,13,1.45,1.75,1.75,5.25,7.75,13,25,28,30,4.25,4,4.5,6,9.25,9.25,0,0,0,0,0);



CREATE TABLE `sportsims`.`lotteries` (
  `lotteryId` INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  `memberId` VARCHAR(50) NOT NULL,
  `dateTime` DATETIME NOT NULL,
  `selections` INTEGER UNSIGNED NOT NULL,
  `game1Id` INTEGER UNSIGNED NOT NULL,
  `game1Item` VARCHAR(15) NOT NULL,
  `game1Spread` DOUBLE,
  `game1Odds` DOUBLE UNSIGNED NOT NULL,
  `game2Id` INTEGER UNSIGNED,
  `game2Item` VARCHAR(15),
  `game2Spread` DOUBLE,
  `game2Odds` DOUBLE UNSIGNED,
  `game3Id` INTEGER UNSIGNED,
  `game3Item` VARCHAR(15),
  `game3Spread` DOUBLE,
  `game3Odds` DOUBLE UNSIGNED,
  `game4Id` INTEGER UNSIGNED,
  `game4Item` VARCHAR(15),
  `game4Spread` DOUBLE,
  `game4Odds` DOUBLE UNSIGNED,
  `game5Id` INTEGER UNSIGNED,
  `game5Item` VARCHAR(15),
  `game5Spread` DOUBLE,
  `game5Odds` DOUBLE UNSIGNED,
  `game6Id` INTEGER UNSIGNED,
  `game6Item` VARCHAR(15),
  `game6Spread` DOUBLE,
  `game6Odds` DOUBLE UNSIGNED,
  `game7Id` INTEGER UNSIGNED,
  `game7Item` VARCHAR(15),
  `game7Spread` DOUBLE,
  `game7Odds` DOUBLE UNSIGNED,
  `game8Id` INTEGER UNSIGNED,
  `game8Item` VARCHAR(15),
  `game8Spread` DOUBLE,
  `game8Odds` DOUBLE UNSIGNED,
  `pass1` BOOLEAN NOT NULL,
  `pass2` BOOLEAN,
  `pass3` BOOLEAN,
  `pass4` BOOLEAN,
  `pass5` BOOLEAN,
  `pass6` BOOLEAN,
  `pass7` BOOLEAN,
  `pass8` BOOLEAN,
  `stake` INTEGER UNSIGNED NOT NULL,
  `price` INTEGER UNSIGNED NOT NULL DEFAULT 0,
  `status` INTEGER UNSIGNED NOT NULL DEFAULT 0,
  PRIMARY KEY (`lotteryId`)
)
ENGINE = InnoDB;

INSERT INTO lotteries (memberId,dateTime,selections,game1Id,game1Item,game1Spread,game1Odds,game2Id,game2Item,game2Spread,game2Odds,game3Id,game3Item,game3Spread,game3Odds,pass1,pass2,pass3,stake,price,status) VALUES("kent11240","2016-04-12 11:26:34",3,4,"away",-15.5,1.75,5,"away",2.5,1.75,6,"over",202.5,1.75,false,false,true,100,0,0);

INSERT INTO lotteries (memberId,dateTime,selections,game1Id,game1Item,game1Spread,game1Odds,game2Id,game2Item,game2Spread,game2Odds,game3Id,game3Item,game3Spread,game3Odds,pass1,pass2,pass3,stake,price,status) VALUES("kent11240","2016-04-12 11:27:14",3,4,"home",-15.5,1.75,5,"away",2.5,1.75,6,"over",202.5,1.75,false,true,true,30,0,0);

INSERT INTO lotteries (memberId,dateTime,selections,game1Id,game1Item,game1Spread,game1Odds,game2Id,game2Item,game2Spread,game2Odds,game3Id,game3Item,game3Spread,game3Odds,pass1,pass2,pass3,stake,price,status) VALUES("kent11240","2016-04-12 11:28:41",3,4,"home",-15.5,1.75,5,"away",2.5,1.75,6,"over",202.5,1.75,false,false,true,30,0,0);

INSERT INTO lotteries (memberId,dateTime,selections,game1Id,game1Item,game1Spread,game1Odds,game2Id,game2Item,game2Spread,game2Odds,game3Id,game3Item,game3Spread,game3Odds,pass1,pass2,pass3,stake,price,status) VALUES("kent11240","2016-04-12 11:30:27",3,4,"home",-15.5,1.75,5,"home",2.5,1.75,6,"over",202.5,1.75,true,false,false,40,0,0);

INSERT INTO lotteries (memberId,dateTime,selections,game1Id,game1Item,game1Spread,game1Odds,game2Id,game2Item,game2Spread,game2Odds,game3Id,game3Item,game3Spread,game3Odds,pass1,pass2,pass3,stake,price,status) VALUES("kent11240","2016-04-12 11:31:02",3,4,"away",-15.5,1.75,5,"away",2.5,1.75,7,"home",-6.5,1.75,false,false,true,100,0,0);

INSERT INTO lotteries (memberId,dateTime,selections,game1Id,game1Item,game1Spread,game1Odds,game2Id,game2Item,game2Spread,game2Odds,game3Id,game3Item,game3Spread,game3Odds,pass1,pass2,pass3,stake,price,status) VALUES("kent11240","2016-04-12 11:38:58",3,4,"home11to15",NULL,4.25,5,"away6to10",NULL,4.25,7,"home6to10",NULL,4.25,false,false,true,100,0,0);