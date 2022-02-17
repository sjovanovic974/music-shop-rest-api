SET foreign_key_checks = 0;

--
-- Table structure for table `country`
--
CREATE TABLE `my-music-shop-app`.`country` (
  `id` BIGINT unsigned NOT NULL,
  `code` varchar(2) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB;

--
-- Table structure for table `state`
--

CREATE TABLE `my-music-shop-app`.`state` (
  `id` BIGINT unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `country_id` BIGINT unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_country` (`country_id`),
  CONSTRAINT `fk_country` FOREIGN KEY (`country_id`) REFERENCES `country` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1;

SET foreign_key_checks = 1;