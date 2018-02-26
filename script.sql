CREATE SCHEMA teste_dive DEFAULT CHARACTER SET utf8 ;
USE teste_dive ;

CREATE TABLE IF NOT EXISTS ficha (
  id_ficha INT NOT NULL AUTO_INCREMENT,
  dt_cadastro DATETIME NOT NULL,
  fl_ativo SMALLINT(1) NOT NULL DEFAULT 1,
  observacao VARCHAR(100) NULL,
  PRIMARY KEY (id_ficha))
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS animal (
  id_animal INT NOT NULL AUTO_INCREMENT,
  nome VARCHAR(50) NOT NULL,
  fl_ativo SMALLINT(1) NOT NULL DEFAULT 1,
  ficha_id_ficha INT NOT NULL,
  PRIMARY KEY (id_animal, ficha_id_ficha),
  INDEX fk_animal_ficha_idx (ficha_id_ficha ASC),
  CONSTRAINT fk_animal_ficha
    FOREIGN KEY (ficha_id_ficha)
    REFERENCES ficha (id_ficha)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;