SET FOREIGN_KEY_CHECKS=0;

CREATE DATABASE `quizdb`
    CHARACTER SET 'utf8'
    COLLATE 'utf8_general_ci';

USE `quizdb`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `firstName` tinytext,
  `lastName` tinytext,
  `email` tinytext,
  `admin` bit(1),
  `attempts` int,
  `date` datetime DEFAULT NULL,
  `bestResult` float,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=252 DEFAULT CHARSET=utf8;

CREATE TABLE `question` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `questionText` text,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=252 DEFAULT CHARSET=utf8;

CREATE TABLE `answer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `text` text,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=252 DEFAULT CHARSET=utf8;