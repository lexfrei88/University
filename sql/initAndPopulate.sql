-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

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








-- -----------------------------------------------------
-- ==================POPULATE===========================
-- -----------------------------------------------------

USE selection_committee;

INSERT INTO 
	faculty (faculty_name, student_limit) 
VALUES 
	("Assasination faculty", 10),		-- 1
	("Star Traveling faculty", 2),		-- 2
	("Programming faculty", 9999),		-- 3
	("Magic faculty", 1),				-- 4
	("Architecture faculty", 7),		-- 5
	("BioEngineering faculty", 10),		-- 6
	("Surgery faculty", 50),			-- 7
	("Time Traveling faculty", 20),		-- 8
	("Draw and disign faculty", 30),	-- 9
	("Actors faculty", 44);				-- 10

INSERT INTO 
	subject (subject_name) 
VALUES 
	("Stealth"), 			-- 1
	("Actors Art"), 		-- 2
	("Fight"),				-- 3
	("Math"), 				-- 4
	("Phisics"), 			-- 5
	("Phisical training"), 	-- 6
	("Investigation"),		-- 7
	("Chemistry"), 			-- 8
	("Mind power"), 		-- 9
	("Biology"), 			-- 10
	("Drawing"), 			-- 11
	("Building"), 			-- 12
	("Imagination"), 		-- 13
	("Speaking"); 			-- 14

INSERT INTO 
	faculty_m2m_subject (faculty_id, subject_id) 
VALUES 
	(1, 1), (1, 2), (1, 3),
	(2, 5), (2, 6), (2, 7),
	(3, 4), (3, 5), (3, 7),
	(4,  9), (4,  13),
	(5, 5), (5, 11), (5, 12), (5, 13),
	(6, 8), (6, 10), (6, 4), (6, 5),
	(7, 7), (7, 8), (7, 10),
	(8, 6), (8, 7), (8, 13),
	(9, 11), (9, 13),
	(10, 2), (10, 14);

INSERT INTO 
	user (first_name, last_name, email, password, faculty_id, approved_status) 
VALUES 
	("Arya", "Stark", "nobody@braavos.com", MD5("thewinteriscomming2"), 1, b'1'), 					-- 1
	("Jhon", "Snow", "jhon.s@nightwatch.com", MD5("kingOfNorth"), 2, b'1'),							-- 2
	("Cersei", "Lannister", "queen@kingslanding.com", MD5("Youlldiesoon1"), 3, b'1'),				-- 3
	("Hodor", "Hodor", "hodor@hodor.hodor", MD5("hodor"), 1, b'1'),									-- 4
	("Brandon", "Stark", "brandon.stark@winterfell.com", MD5("iknowwhatyoudidlastsummer"), 1, b'1'),-- 5
	("Teon", "Greyjoy", "bels@dinglebels.com", MD5("deadcantdie"), 1, b'1'),						-- 6
	("Bron", "Blackwater", "bron@deepa.com", MD5("yououghtmeacastle"), 1, b'0'),					-- 7
	("Eddar", "Start", "keeper@winterfell.com", MD5("goodmandeadman"), 4, b'1'),					-- 8
	("Davos", "Seaworth", "knight@dragonstone.com", MD5("knight"), 4, b'1'),						-- 9
	("Samwell", "Tarly", "sam@email.com", MD5("sam"), 7, b'1'),										-- 10
	("Tormund", "Giantsbase", "tormund@email.com", MD5("tormund"), 10, b'1'),						-- 11
	("Brienne", "Tarth", "brienne@email.com", MD5("brienne"), 9, b'1'),								-- 12
	("f_empty", "l_empty", "empty@email.com", MD5("empty"), null, b'1'),							-- 13
	("firstname_root", "lastname_root", "root@root", MD5("root"), null, b'1');						-- 14

INSERT INTO 
	user_role (user_id, role) 
VALUES 
	(1, "ROLE_USER"), 
	(2, "ROLE_USER"), 
	(3, "ROLE_USER"), (3, "ROLE_ADMIN"),
	(4, "ROLE_USER"),
	(5, "ROLE_USER"),
	(6, "ROLE_USER"),
	(7, "ROLE_USER"),
	(8, "ROLE_USER"),
	(9, "ROLE_USER"),
	(10, "ROLE_USER"), (10, "ROLE_ADMIN"),
	(11, "ROLE_USER"),
	(12, "ROLE_USER"),
	(13, "ROLE_USER"),
	(14, "ROLE_USER"), (14, "ROLE_ADMIN");

INSERT INTO 
	user_certificate (user_id, subject_id, score) 
VALUES 
	(1, 1, 10), (1, 2, 8), (1, 3, 9), (1, 6, 1), 
	(2, 5, 5), (2, 6, 10), (2, 7, 7),
	(3, 4, 9), (3, 5, 9), (3, 7, 11),
	(4, 1, 5), (4, 2, 5), (4, 3, 3),
	(5, 1, 9), (5, 2, 9),
	(6, 1, 3), (6, 2, 2), (6, 3, 2),
	(7, 1, 5), (7, 2, 7), (7, 3, 5),
	(8, 9, 7), (8, 13, 10), (8, 10, 2),
	(9, 9, 6), (9, 13, 8),
	(10, 7, 8), (10, 8, 8),
	(11, 2, 7), (11, 14, 7), (11, 4, 8),
	(12, 11, 5), (12, 10, 7), (12, 1, 1),
	(13, 1, 10), (13, 2, 10), (13, 3, 10), (13, 4, 10), (13, 5, 10), (13, 6, 10),
	(14, 1, 2), (14, 7, 3);
