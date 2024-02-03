CREATE DATABASE IF NOT EXISTS `dungeonrun` /*!40100 DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci */;

CREATE TABLE IF NOT EXISTS `player` (
  `PlayerID` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(30) DEFAULT NULL,
  `Strength` int(11) DEFAULT NULL,
  `Intelligence` int(11) DEFAULT NULL,
  `Agility` int(11) DEFAULT NULL,
  `Health` int(11) DEFAULT NULL,
  `Basedamage` int(11) DEFAULT NULL,
  `Experience` int(11) DEFAULT NULL,
  `Level` int(11) DEFAULT NULL,
  PRIMARY KEY (`PlayerID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;


CREATE TABLE IF NOT EXISTS `monster` (
  `MonsterID` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(30) DEFAULT NULL,
  `Strength` int(11) DEFAULT NULL,
  `Intelligence` int(11) DEFAULT NULL,
  `Agility` int(11) DEFAULT NULL,
  `Health` int(11) DEFAULT NULL,
  `Basedamage` int(11) DEFAULT NULL,
  PRIMARY KEY (`MonsterID`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;


CREATE TABLE IF NOT EXISTS`fighthistory` (
  `FightID` int(11) NOT NULL AUTO_INCREMENT,
  `PlayerID` int(11) DEFAULT NULL,
  `MonsterID` int(11) DEFAULT NULL,
  `Didflee` tinyint(1) DEFAULT NULL,
  `Didwin` tinyint(1) DEFAULT NULL,
  `Timestamp` datetime DEFAULT NULL,
  PRIMARY KEY (`FightID`),
  KEY `fighthistory_FK` (`MonsterID`),
  KEY `fighthistory_FK_1` (`PlayerID`),
  CONSTRAINT `fighthistory_FK` FOREIGN KEY (`MonsterID`) REFERENCES `monster` (`MonsterID`),
  CONSTRAINT `fighthistory_FK_1` FOREIGN KEY (`PlayerID`) REFERENCES `player` (`PlayerID`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;