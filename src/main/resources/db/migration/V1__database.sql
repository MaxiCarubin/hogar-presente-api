-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema db_springboot_hogar_presente
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema db_springboot_hogar_presente
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `db_springboot_hogar_presente` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `db_springboot_hogar_presente` ;

-- -----------------------------------------------------
-- Table `db_springboot_hogar_presente`.`alumnos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_springboot_hogar_presente`.`alumnos` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `apellido` VARCHAR(255) NOT NULL,
  `clave` VARCHAR(255) NOT NULL,
  `correo` VARCHAR(255) NOT NULL,
  `foto` VARCHAR(255) NULL DEFAULT NULL,
  `nombre` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `UK_og6h2u8qc1l7lc396uu28u65j` (`correo` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `db_springboot_hogar_presente`.`cursos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_springboot_hogar_presente`.`cursos` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `capacitador` VARCHAR(255) NULL DEFAULT NULL,
  `descripcion` VARCHAR(255) NULL DEFAULT NULL,
  `horas` FLOAT NOT NULL,
  `nombre` VARCHAR(255) NOT NULL,
  `precio` DOUBLE NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `db_springboot_hogar_presente`.`inscripciones`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_springboot_hogar_presente`.`inscripciones` (
  `inscripcion_at` DATE NOT NULL,
  `alumno_id` BIGINT NULL DEFAULT NULL,
  `curso_id` BIGINT NULL DEFAULT NULL,
  PRIMARY KEY (`inscripcion_at`),
  INDEX `FKkvw4t2x22abixq6y5kvdps4lm` (`alumno_id` ASC) VISIBLE,
  INDEX `FKeil6g011skmwvhuklpwwxnpdv` (`curso_id` ASC) VISIBLE,
  CONSTRAINT `FKeil6g011skmwvhuklpwwxnpdv`
    FOREIGN KEY (`curso_id`)
    REFERENCES `db_springboot_hogar_presente`.`cursos` (`id`),
  CONSTRAINT `FKkvw4t2x22abixq6y5kvdps4lm`
    FOREIGN KEY (`alumno_id`)
    REFERENCES `db_springboot_hogar_presente`.`alumnos` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `db_springboot_hogar_presente`.`alumnos_inscripciones`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_springboot_hogar_presente`.`alumnos_inscripciones` (
  `alumno_entity_id` BIGINT NOT NULL,
  `inscripciones_inscripcion_at` DATE NOT NULL,
  UNIQUE INDEX `UK_hrui3u68xlgnd1kacfx8frv4w` (`inscripciones_inscripcion_at` ASC) VISIBLE,
  INDEX `FK97ec778ix2qlfcdicr6cf75m6` (`alumno_entity_id` ASC) VISIBLE,
  CONSTRAINT `FK97ec778ix2qlfcdicr6cf75m6`
    FOREIGN KEY (`alumno_entity_id`)
    REFERENCES `db_springboot_hogar_presente`.`alumnos` (`id`),
  CONSTRAINT `FKmm9o0j9kncwtspos5iiviw642`
    FOREIGN KEY (`inscripciones_inscripcion_at`)
    REFERENCES `db_springboot_hogar_presente`.`inscripciones` (`inscripcion_at`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `db_springboot_hogar_presente`.`cursos_inscripciones`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_springboot_hogar_presente`.`cursos_inscripciones` (
  `curso_entity_id` BIGINT NOT NULL,
  `inscripciones_inscripcion_at` DATE NOT NULL,
  UNIQUE INDEX `UK_oac07j170y8s9h308nu4q5w4c` (`inscripciones_inscripcion_at` ASC) VISIBLE,
  INDEX `FKqudoaa1fmxruuebglbm5gikcb` (`curso_entity_id` ASC) VISIBLE,
  CONSTRAINT `FK5d9l4fajfiityylaat8mvhr7i`
    FOREIGN KEY (`inscripciones_inscripcion_at`)
    REFERENCES `db_springboot_hogar_presente`.`inscripciones` (`inscripcion_at`),
  CONSTRAINT `FKqudoaa1fmxruuebglbm5gikcb`
    FOREIGN KEY (`curso_entity_id`)
    REFERENCES `db_springboot_hogar_presente`.`cursos` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `db_springboot_hogar_presente`.`unidades`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_springboot_hogar_presente`.`unidades` (
  `nombre` VARCHAR(255) NOT NULL,
  `descripcion` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`nombre`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `db_springboot_hogar_presente`.`cursos_unidades`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_springboot_hogar_presente`.`cursos_unidades` (
  `curso_entity_id` BIGINT NOT NULL,
  `unidades_nombre` VARCHAR(255) NOT NULL,
  UNIQUE INDEX `UK_cwyhogfhovpw9rp4kkf9nw2pi` (`unidades_nombre` ASC) VISIBLE,
  INDEX `FK1wrh6h2niu3yh0fe46vo5rmi9` (`curso_entity_id` ASC) VISIBLE,
  CONSTRAINT `FK1wrh6h2niu3yh0fe46vo5rmi9`
    FOREIGN KEY (`curso_entity_id`)
    REFERENCES `db_springboot_hogar_presente`.`cursos` (`id`),
  CONSTRAINT `FKlb4l3qrwd7u1cxrqxu8eiv282`
    FOREIGN KEY (`unidades_nombre`)
    REFERENCES `db_springboot_hogar_presente`.`unidades` (`nombre`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `db_springboot_hogar_presente`.`tareas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_springboot_hogar_presente`.`tareas` (
  `nombre` VARCHAR(255) NOT NULL,
  `descripcion` VARCHAR(255) NULL DEFAULT NULL,
  `documento` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`nombre`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `db_springboot_hogar_presente`.`unidades_tareas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_springboot_hogar_presente`.`unidades_tareas` (
  `unidad_entity_nombre` VARCHAR(255) NOT NULL,
  `tareas_nombre` VARCHAR(255) NOT NULL,
  UNIQUE INDEX `UK_owx3tc4yerkrqbrwlwu2fvrbd` (`tareas_nombre` ASC) VISIBLE,
  INDEX `FKpph3mobf916i84s83t4f7oche` (`unidad_entity_nombre` ASC) VISIBLE,
  CONSTRAINT `FKlcdy68euxdoox2ag9as9ygfj5`
    FOREIGN KEY (`tareas_nombre`)
    REFERENCES `db_springboot_hogar_presente`.`tareas` (`nombre`),
  CONSTRAINT `FKpph3mobf916i84s83t4f7oche`
    FOREIGN KEY (`unidad_entity_nombre`)
    REFERENCES `db_springboot_hogar_presente`.`unidades` (`nombre`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
