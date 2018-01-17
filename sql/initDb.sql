-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema selection_committee
-- -----------------------------------------------------
-- Задание № 18:
-- 
-- 
-- 
-- Приемная комиссия.
-- 
-- 
-- Абитуриент регистрируется на один из Факультетов с фиксированным планом набора, вводит баллы по соответствующим Предметам и аттестату. 
-- Результаты Администратором регистрируются в Ведомости. Система подсчитывает сумму баллов и определяет Абитуриентов, зачисленных в учебное заведение.
-- 
-- 
-- 
-- Описание предметной области:
-- 
-- 
-- 
-- Абитуриенты хотят поступить в университет. 
-- 
-- Университет имеет множество факультетов. У каждого факультета свой лимит студентов, которых он может принять на обучение.
-- 
-- Отбор студентов осуществляется на основании оценок по предметам: абитуриенты должны предоставить сертификаты с оценками по предметам.
-- 
-- В зависимости от того, на какой факультет желает поступить абитуриент, требуются оценки по определенным предметам.
-- 
-- Абитуриент может принять участие в отборе на факультет, только после того, как предоставленные им данные были проверены Администратором.
-- 
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `selection_committee` DEFAULT CHARACTER SET utf8 ;
USE `selection_committee` ;

-- -----------------------------------------------------
-- Table `selection_committee`.`faculty`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `selection_committee`.`faculty` (
  `faculty_id` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `faculty_name` VARCHAR(100) NOT NULL COMMENT 'Name of the Faculty',
  `student_limit` INT NOT NULL COMMENT 'Limit of students that could study on the Faculty at the same time.',
  PRIMARY KEY (`faculty_id`),
  UNIQUE INDEX `idx_unique_faculty_name` (`faculty_name` ASC))
ENGINE = InnoDB
COMMENT = 'This table contains Faculties for current universety.\n';


-- -----------------------------------------------------
-- Table `selection_committee`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `selection_committee`.`user` (
  `user_id` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `first_name` VARCHAR(100) NOT NULL COMMENT 'First name of the User',
  `last_name` VARCHAR(100) NOT NULL COMMENT 'Last name of the User.',
  `email` VARCHAR(100) NOT NULL COMMENT 'E-mail of the User.',
  `password` CHAR(32) NOT NULL COMMENT 'Password hashed by MD5 algorithm.',
  `approved_status` BIT(1) NOT NULL DEFAULT 0 COMMENT 'Show is current User\'s scores has been approved by Administrator.',
  `faculty_id` INT UNSIGNED NULL COMMENT 'fk to faculty.faculty_id',
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX `idx_unique_user_email` (`email` ASC),
  INDEX `idx_fk_user_faculty` (`faculty_id` ASC),
  CONSTRAINT `fk_user_faculty`
    FOREIGN KEY (`faculty_id`)
    REFERENCES `selection_committee`.`faculty` (`faculty_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = 'This table contains User entity, what represent people who would like to study in the university and administrators.';


-- -----------------------------------------------------
-- Table `selection_committee`.`subject`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `selection_committee`.`subject` (
  `subject_id` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `subject_name` VARCHAR(100) NOT NULL COMMENT 'Name of the Subject.',
  PRIMARY KEY (`subject_id`),
  UNIQUE INDEX `idx_unique_subject_name` (`subject_name` ASC))
ENGINE = InnoDB
COMMENT = 'This table contains Subject names, to exclude duplication or mistakes in it\'s names.';


-- -----------------------------------------------------
-- Table `selection_committee`.`user_certificate`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `selection_committee`.`user_certificate` (
  `certificate_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` INT UNSIGNED NOT NULL COMMENT 'fk to users.user_id',
  `subject_id` INT UNSIGNED NOT NULL COMMENT 'fk to subjects.subject_id',
  `score` TINYINT(2) NOT NULL DEFAULT 0 COMMENT 'Score for specified subject for specified user.',
  INDEX `idx_fk_user_score_subject` (`subject_id` ASC),
  PRIMARY KEY (`certificate_id`),
  UNIQUE INDEX `idx_unique_user_id_subject_id` (`user_id` ASC, `subject_id` ASC),
  CONSTRAINT `fk_user_score_subject`
    FOREIGN KEY (`subject_id`)
    REFERENCES `selection_committee`.`subject` (`subject_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_score_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `selection_committee`.`user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = 'This table contains score for specified subject for specified user.';


-- -----------------------------------------------------
-- Table `selection_committee`.`user_role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `selection_committee`.`user_role` (
  `user_id` INT UNSIGNED NOT NULL COMMENT 'fk to user.user_id',
  `role` ENUM('ROLE_USER', 'ROLE_ADMIN') NOT NULL COMMENT 'Role of the User.',
  PRIMARY KEY (`user_id`, `role`),
  CONSTRAINT `fk_user_role_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `selection_committee`.`user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = 'This table contains User role which is used for security porpose:determining an actions accessed for specified User by his Role.';


-- -----------------------------------------------------
-- Table `selection_committee`.`faculty_m2m_subject`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `selection_committee`.`faculty_m2m_subject` (
  `faculty_id` INT UNSIGNED NOT NULL,
  `subject_id` INT UNSIGNED NOT NULL,
  INDEX `idx_fk_faculty_m2m_subject_subject` (`subject_id` ASC),
  PRIMARY KEY (`faculty_id`, `subject_id`),
  CONSTRAINT `fk_faculty_m2m_subject_faculty`
    FOREIGN KEY (`faculty_id`)
    REFERENCES `selection_committee`.`faculty` (`faculty_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_faculty_m2m_subject_subject`
    FOREIGN KEY (`subject_id`)
    REFERENCES `selection_committee`.`subject` (`subject_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = 'This table represent relations between Faculty and it Subjects, what needed for selection to this faculty.';


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
