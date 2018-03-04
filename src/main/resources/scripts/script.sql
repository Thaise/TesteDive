CREATE SCHEMA IF NOT EXISTS teste_dive DEFAULT CHARACTER SET utf8 ;
USE teste_dive ;

CREATE TABLE IF NOT EXISTS animal (
  id_animal INT NOT NULL AUTO_INCREMENT,
  nome VARCHAR(50) NOT NULL,
  fl_ativo SMALLINT(1) NOT NULL DEFAULT 1,
  PRIMARY KEY (id_animal))
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS ficha (
  id_ficha INT NOT NULL AUTO_INCREMENT,
  dt_cadastro DATETIME NOT NULL,
  fl_ativo SMALLINT(1) NOT NULL DEFAULT 1,
  observacao VARCHAR(100) NOT NULL,
  PRIMARY KEY (id_ficha))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS ficha_animal (
  id_ficha INT NOT NULL,
  id_animal INT NOT NULL,
  PRIMARY KEY (id_ficha, id_animal),
  INDEX fk_ficha_has_animal_animal1_idx (id_animal ASC),
  INDEX fk_ficha_has_animal_ficha_idx (id_ficha ASC),
  CONSTRAINT fk_ficha_has_animal_ficha
    FOREIGN KEY (id_ficha)
    REFERENCES ficha (id_ficha)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_ficha_has_animal_animal1
    FOREIGN KEY (id_animal)
    REFERENCES animal (id_animal)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

INSERT INTO animal(id_animal, nome) values (1, 'Porco');
INSERT INTO animal(id_animal, nome) values (2, 'Gato');
INSERT INTO animal(id_animal, nome) values (3, 'Cachorro');
INSERT INTO animal(id_animal, nome) values (4, 'Cavalo');
INSERT INTO animal(id_animal, nome) values (5, 'Galinha');
INSERT INTO animal(id_animal, nome) values (6, 'Onça');
INSERT INTO animal(id_animal, nome) values (7, 'Tamanduá');
INSERT INTO animal(id_animal, nome) values (8, 'Jararaca');
INSERT INTO animal(id_animal, nome) values (9, 'Papagaio');
INSERT INTO animal(id_animal, nome) values (10, 'Besouro');
