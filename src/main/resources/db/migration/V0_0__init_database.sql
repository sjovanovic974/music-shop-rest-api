-- -----------------------------------------------------
-- DDL Statements
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Table `my-music-shop-app`.`product_category`
-- -----------------------------------------------------
CREATE TABLE `my-music-shop-app`.`product_category` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `category_name` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE=InnoDB
AUTO_INCREMENT = 1;

-- -----------------------------------------------------
-- Table `my-music-shop-app`.`product`
-- -----------------------------------------------------
CREATE TABLE `my-music-shop-app`.`product` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `sku` VARCHAR(255) NOT NULL UNIQUE,
  `name` VARCHAR(255) NOT NULL,
  `description` VARCHAR(1600) DEFAULT NULL,
  `unit_price` DECIMAL(13,2) NOT NULL,
  `image_url` VARCHAR(255) NOT NULL,
  `active` BIT DEFAULT 1,
  `units_in_stock` INT NOT NULL,
  `date_created` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `last_updated` DATETIME DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `category_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_category` (`category_id`),
  CONSTRAINT `fk_category` FOREIGN KEY (`category_id`) REFERENCES `product_category` (`id`)
) 
ENGINE=InnoDB
AUTO_INCREMENT = 1;