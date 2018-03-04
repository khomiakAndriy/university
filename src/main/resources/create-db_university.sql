DROP TABLE IF EXISTS `departments_lectors`;
DROP TABLE IF EXISTS `departments`;
DROP TABLE IF EXISTS `lectors`;
DROP TABLE IF EXISTS `degrees`;


CREATE TABLE `departments` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) NOT NULL,
  `chief` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
); 





CREATE TABLE `degrees` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,  
  PRIMARY KEY (`id`)    
);

CREATE TABLE `lectors` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,  
  `salary` decimal(10, 2) NOT NULL, 
  `degree_id` int(11) NOT NULL,   
  PRIMARY KEY (`id`),
  FOREIGN KEY (`degree_id`) 
  REFERENCES `degrees` (`id`)
);


CREATE TABLE `departments_lectors` (
  `departments_id` int(11) NOT NULL,
  `lectors_id` int(11) NOT NULL,
  
  PRIMARY KEY (`departments_id`,`lectors_id`),
  
  FOREIGN KEY (`departments_id`) 
  REFERENCES `departments` (`id`) 
  ON DELETE NO ACTION ON UPDATE NO ACTION,
  
  FOREIGN KEY (`lectors_id`) 
  REFERENCES `lectors` (`id`) 
  ON DELETE NO ACTION ON UPDATE NO ACTION
); 