CREATE  TABLE IF NOT EXISTS person (
  id_person INT NOT NULL AUTO_INCREMENT,
  vc_name varchar(128) NOT NULL ,
  dc_height DECIMAL(5,2) NULL ,
  dc_mass DECIMAL(5,2) NULL ,
  vc_hair_color VARCHAR(16) NULL ,
  ty_gender TINYINT(1) NOT NULL ,
  vc_planet varchar (64) NOT NULL,
  PRIMARY KEY (`id_person`) )
ENGINE = INNODB;