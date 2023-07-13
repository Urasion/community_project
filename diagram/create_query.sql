-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema communitydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema communitydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `communitydb` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
-- -----------------------------------------------------
-- Schema communitydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema communitydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `communitydb` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `communitydb` ;

-- -----------------------------------------------------
-- Table `communitydb`.`MEMBER`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `communitydb`.`MEMBER` (
  `member_id` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NULL,
  `name` VARCHAR(45) NULL,
  `role` VARCHAR(45) NULL,
  `create_at` DATETIME NULL,
  PRIMARY KEY (`member_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `communitydb`.`CATEGORY`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `communitydb`.`CATEGORY` (
  `category_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `parent` INT NULL,
  PRIMARY KEY (`category_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `communitydb`.`BOARD`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `communitydb`.`BOARD` (
  `board_id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(100) NULL,
  `content` TEXT NULL,
  `member_id` VARCHAR(45) NULL,
  `view` INT NULL,
  `create_at` DATETIME NULL,
  `category_id` INT NULL,
  PRIMARY KEY (`board_id`),
  INDEX `id_idx` (`member_id` ASC) VISIBLE,
  INDEX `category_id_idx` (`category_id` ASC) VISIBLE,
  CONSTRAINT `board_m`
    FOREIGN KEY (`member_id`)
    REFERENCES `communitydb`.`MEMBER` (`member_id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `board_c`
    FOREIGN KEY (`category_id`)
    REFERENCES `communitydb`.`CATEGORY` (`category_id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `communitydb`.`COMMENT`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `communitydb`.`COMMENT` (
  `comment_id` INT NOT NULL AUTO_INCREMENT,
  `board_id` INT NULL,
  `content` TEXT NULL,
  `create_at` DATETIME NULL,
  `state` VARCHAR(45) NULL,
  `dtype` VARCHAR(45) NULL,
  PRIMARY KEY (`comment_id`),
  INDEX `id_idx` (`board_id` ASC) VISIBLE,
  CONSTRAINT `comment_b`
    FOREIGN KEY (`board_id`)
    REFERENCES `communitydb`.`BOARD` (`board_id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `communitydb`.`FILE`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `communitydb`.`FILE` (
  `file_id` INT NOT NULL AUTO_INCREMENT,
  `board_id` INT NULL,
  `path` VARCHAR(100) NULL,
  `name` VARCHAR(100) NULL,
  `type` VARCHAR(100) NULL,
  `size` VARCHAR(45) NULL,
  PRIMARY KEY (`file_id`),
  INDEX `board_id_idx` (`board_id` ASC) VISIBLE,
  CONSTRAINT `file_b`
    FOREIGN KEY (`board_id`)
    REFERENCES `communitydb`.`BOARD` (`board_id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `communitydb`.`COMMENT_LOGIN`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `communitydb`.`COMMENT_LOGIN` (
  `comment_login_id` INT NOT NULL,
  `member_id` VARCHAR(45) NULL,
  `role` VARCHAR(45) NULL,
  `member_name` VARCHAR(45) NULL,
  PRIMARY KEY (`comment_login_id`),
  CONSTRAINT `comment_login_id`
    FOREIGN KEY (`comment_login_id`)
    REFERENCES `communitydb`.`COMMENT` (`comment_id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `communitydb`.`COMMENT_NOLOGIN`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `communitydb`.`COMMENT_NOLOGIN` (
  `comment_nologin_id` INT NOT NULL,
  `name` VARCHAR(45) NULL,
  `password` VARCHAR(45) NULL,
  PRIMARY KEY (`comment_nologin_id`),
  CONSTRAINT `comment_nologin_id`
    FOREIGN KEY (`comment_nologin_id`)
    REFERENCES `communitydb`.`COMMENT` (`comment_id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `communitydb`.`RECOMMEND`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `communitydb`.`RECOMMEND` (
  `recommend_id` INT NOT NULL AUTO_INCREMENT,
  `board_id` INT NULL,
  `member_id` VARCHAR(45) NULL,
  PRIMARY KEY (`recommend_id`),
  INDEX `board_id_idx` (`board_id` ASC) VISIBLE,
  INDEX `member_id_idx` (`member_id` ASC) VISIBLE,
  CONSTRAINT `recommend_b`
    FOREIGN KEY (`board_id`)
    REFERENCES `communitydb`.`BOARD` (`board_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `recommend_m`
    FOREIGN KEY (`member_id`)
    REFERENCES `communitydb`.`MEMBER` (`member_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

USE `communitydb` ;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
