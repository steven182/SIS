CREATE SCHEMA IF NOT EXISTS `softbi_SIS` DEFAULT CHARACTER SET utf8 ;
USE `softbi_SIS`;
-- -----------------------------------------------------
-- Table `softbi_SIS`.`rol`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `softbi_SIS`.`rol` (
  `IdRol` INT(11) NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `descripcion` VARCHAR(45) NULL DEFAULT NULL,
  `estado` TINYINT(1) NOT NULL,
  PRIMARY KEY (`IdRol`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;
insert into rol (nombre, descripcion, estado) values
('Administrador', 'Controlador del sistema', true), ('Cliente', 'Usuario del sistema', true),
('Tecnico', 'Soporte tecnico', true);

-- -----------------------------------------------------
-- Table `softbi_SIS`.`Departamento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `softbi_SIS`.`Departamento` (
  `idDepartamento` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(30) NULL,
  PRIMARY KEY (`idDepartamento`))
ENGINE = InnoDB;
insert into Departamento (nombre) values
('Cundinamarca'),('Antioquia'),('Risaralda');

-- -----------------------------------------------------
-- Table `softbi_SIS`.`ciudad`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `softbi_SIS`.`ciudad` (
  `idciudad` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `Departamento_idDepartamento` INT NOT NULL,
  PRIMARY KEY (`idciudad`),
  INDEX `fk_ciudad_Departamento1_idx` (`Departamento_idDepartamento` ASC),
  CONSTRAINT `fk_ciudad_Departamento1`
    FOREIGN KEY (`Departamento_idDepartamento`)
    REFERENCES `softbi_SIS`.`Departamento` (`idDepartamento`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;
insert into ciudad (nombre, Departamento_idDepartamento)
value ('Bogotá',1),('Medellin',2),('Pereira',3);

-- -----------------------------------------------------
-- Table `softbi_SIS`.`persona`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `softbi_SIS`.`persona` (
  `idPersona` INT(11) NOT NULL AUTO_INCREMENT,
  `primernombre` VARCHAR(25) NOT NULL,
  `segundonombre` VARCHAR(25) NULL,
  `primerapellido` VARCHAR(25) NOT NULL,
  `segundoapellido` VARCHAR(25) NULL,
  `tipoIdentificacion` ENUM('CC', 'NIT', 'CE') NOT NULL,
  `numeroIdentificacion` VARCHAR(11) NOT NULL,
  `direccion` VARCHAR(40) NOT NULL,
  `telefono` LONG NOT NULL,
  `correo` VARCHAR(100) NOT NULL,
  `genero` ENUM('M', 'F') NOT NULL,
  `contrasena` VARCHAR(30) NOT NULL,
  `estado` TINYINT(1) NOT NULL,
  `foto` VARCHAR(50) NULL DEFAULT NULL,
  `permisoProducto` TINYINT(1) NULL,
  `rol_IdRol` INT(11) NOT NULL,
  `ciudad_idciudad` INT NOT NULL,
  PRIMARY KEY (`idPersona`),
  INDEX `fk_persona_rol1_idx` (`rol_IdRol` ASC),
  INDEX `fk_persona_ciudad1_idx` (`ciudad_idciudad` ASC),
  CONSTRAINT `fk_persona_rol1`
    FOREIGN KEY (`rol_IdRol`)
    REFERENCES `softbi_SIS`.`rol` (`IdRol`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_persona_ciudad1`
    FOREIGN KEY (`ciudad_idciudad`)
    REFERENCES `softbi_SIS`.`ciudad` (`idciudad`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;
insert into persona (primernombre, segundonombre, primerapellido, segundoapellido, 
tipoIdentificacion, numeroIdentificacion,direccion, telefono, correo, genero, contrasena, 
estado, foto, permisoProducto, rol_IdRol,ciudad_idciudad) values 
('Steven',null ,'Romero', null,'CC', 1074187602, 'Carrera 7 # 4-13', 
3123942083, 'rsromero20@misena.edu.co','M', 'blink182', true, null, true, 1, 1), 
('Tom', null, 'Delong',null, 'CC', 1073698520, 'Calle 12 # 65-09', 3154789632, 'rromero.rsrg@gmail.com',
'M', 'asdf789', true, null, true, 2, 2), 
('Matt', null,'Tuck', null,'CC', 80654789, 'Calle 45 # 05-98', 3113698501, 'N/A',
'M', 'qwerty123', true, null, true, 2, 3), 
('Edwin' ,null ,'Salazar',null, 'CC', 1074147650, 'Carrera 47 # 12-63', 3123695412, 'stevenrome.94@hotmail.com',
'M', 'pintor654', true,  null, true, 3, 1), 
('David',null, 'Pintor', null,'CC', 1073025987, 'Calle 12 # 45-03', 3201478965,
 'N/A', 'M', 'lkjdy3210', true, null, true,3, 2);
select * from persona where rol_IdRol = 2 and (permisoProducto = true) and (estado = true);
-- -----------------------------------------------------
-- Table `softbi_SIS`.`asignaciontecnico`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `softbi_SIS`.`asignaciontecnico` (
  `idAsignacionTecnico` INT(11) NOT NULL AUTO_INCREMENT,
  `disponibilidad` TINYINT(1) NOT NULL,
  `estado` TINYINT(1) NOT NULL,
  `Persona_idPersona` INT(11) NOT NULL,
  PRIMARY KEY (`idAsignacionTecnico`),
  INDEX `fk_AsignacionTecnico_Persona1_idx` (`Persona_idPersona` ASC),
  CONSTRAINT `fk_AsignacionTecnico_Persona1`
    FOREIGN KEY (`Persona_idPersona`)
    REFERENCES `softbi_SIS`.`persona` (`idPersona`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;
insert into asignaciontecnico (disponibilidad, estado, Persona_idPersona) values
(true, true, 4), (true, true, 5);

-- -----------------------------------------------------
-- Table `softbi_SIS`.`categoria`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `softbi_SIS`.`categoria` (
  `idCategoria` INT(11) NOT NULL AUTO_INCREMENT,
  `estado` TINYINT(1) NOT NULL,
  `nombreCategoria` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`idCategoria`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;
insert into categoria (estado, nombreCategoria) values
(true, 'Maquina'), (true, 'Insumos'), (true, 'Repuestos'),
(true, 'Otros');

-- -----------------------------------------------------
-- Table `softbi_SIS`.`marca`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `softbi_SIS`.`marca` (
  `idMarca` INT(11) NOT NULL AUTO_INCREMENT,
  `estado` TINYINT(1) NOT NULL,
  `nombreMarca` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`idMarca`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;
insert into marca (estado, nombreMarca) values
(true, 'Ricoh'), (true, 'Xerox'), (true, 'Sharp'),
(true, 'HP'), (true, 'Otro');

-- -----------------------------------------------------
-- Table `softbi_SIS`.`producto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `softbi_SIS`.`producto` (
  `idProducto` INT(11) NOT NULL AUTO_INCREMENT,
  `nombreProducto` VARCHAR(45) NOT NULL,
  `estado` TINYINT(1) NOT NULL,
  `Categoria_idCategoria` INT(11) NOT NULL,
  PRIMARY KEY (`idProducto`),
  INDEX `fk_Producto_Categoria1_idx` (`Categoria_idCategoria` ASC),
  CONSTRAINT `fk_Producto_Categoria1`
    FOREIGN KEY (`Categoria_idCategoria`)
    REFERENCES `softbi_SIS`.`categoria` (`idCategoria`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;
insert into producto (nombreProducto, estado, Categoria_idCategoria) values
('Multifunción', true, 1), ('Cartucho', true, 2);

-- -----------------------------------------------------
-- Table `softbi_SIS`.`DetalleProducto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `softbi_SIS`.`DetalleProducto` (
  `idDetalleProducto` INT(11) NOT NULL AUTO_INCREMENT,
  `modelo` VARCHAR(30) NOT NULL,
  `precio` INT NULL,
  `cantidad` INT NULL,
  `contadorMaquina` INT NULL,
  `descripcion` VARCHAR(100) NULL,
  `serialProducto` VARCHAR(50) NOT NULL,
  `estado` TINYINT(1) NOT NULL,
  `borrar` TINYINT(1) NOT NULL,
  `Producto_idProducto` INT(11) NOT NULL,
  `Marca_idMarca` INT(11) NOT NULL,
  PRIMARY KEY (`idDetalleProducto`),
  INDEX `fk_Producto_has_Marca_Marca1_idx` (`Marca_idMarca` ASC),
  INDEX `fk_Producto_has_Marca_Producto1_idx` (`Producto_idProducto` ASC),
  CONSTRAINT `fk_Producto_has_Marca_Marca1`
    FOREIGN KEY (`Marca_idMarca`)
    REFERENCES `softbi_SIS`.`marca` (`idMarca`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Producto_has_Marca_Producto1`
    FOREIGN KEY (`Producto_idProducto`)
    REFERENCES `softbi_SIS`.`producto` (`idProducto`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;
insert into DetalleProducto (modelo, precio, cantidad, contadorMaquina,
 descripcion, serialProducto, estado, borrar, Producto_idProducto,Marca_idMarca) values
('Aficio MPC 305', null, 1, 15642, null, 'A2121dd21f', true, true, 1, 1);

-- -----------------------------------------------------
-- Table `softbi_SIS`.`Asignacion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `softbi_SIS`.`Asignacion` (
  `idAsignacion` INT NOT NULL AUTO_INCREMENT,
  `persona_idPersona` INT(11) NOT NULL,
  `DetalleProducto_idDetalleProducto` INT(11) NOT NULL,
  `estado` TINYINT(1) NOT NULL,
  `borrar` TINYINT(1) NOT NULL,
  PRIMARY KEY (`idAsignacion`),
  INDEX `fk_Asignacion_persona1_idx` (`persona_idPersona` ASC),
  INDEX `fk_Asignacion_DetalleProducto1_idx` (`DetalleProducto_idDetalleProducto` ASC),
  CONSTRAINT `fk_Asignacion_persona1`
    FOREIGN KEY (`persona_idPersona`)
    REFERENCES `softbi_SIS`.`persona` (`idPersona`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Asignacion_DetalleProducto1`
    FOREIGN KEY (`DetalleProducto_idDetalleProducto`)
    REFERENCES `softbi_SIS`.`DetalleProducto` (`idDetalleProducto`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `softbi_SIS`.`contrato`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `softbi_SIS`.`contrato` (
  `idContrato` INT(11) NOT NULL AUTO_INCREMENT,
  `tipoContrato` CHAR(2) NULL,
  `valorContrato` INT NULL,
  `fechaInicio` DATE NULL,
  `fechaFin` DATE NULL,
  `descripcion` VARCHAR(300) NULL,
  `estado` TINYINT(1) NULL,
  `costeManoObra` INT NULL,
  `respuestosIncluidos` TINYINT(1) NULL,
  `fechaDePago` VARCHAR(45) NULL,
  `copiasIncluidas` INT NULL,
  `fotoContrato` VARCHAR(300) NULL,
  `Persona_idPersona` INT(11) NOT NULL,
  `Asignacion_idAsignacion` INT NOT NULL,
  PRIMARY KEY (`idContrato`),
  INDEX `fk_Contratos_Persona1_idx` (`Persona_idPersona` ASC),
  INDEX `fk_contrato_Asignacion1_idx` (`Asignacion_idAsignacion` ASC),
  CONSTRAINT `fk_Contratos_Persona1`
    FOREIGN KEY (`Persona_idPersona`)
    REFERENCES `softbi_SIS`.`persona` (`idPersona`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_contrato_Asignacion1`
    FOREIGN KEY (`Asignacion_idAsignacion`)
    REFERENCES `softbi_SIS`.`Asignacion` (`idAsignacion`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Table `softbi_SIS`.`cotizacion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `softbi_SIS`.`cotizacion` (
  `idCotizacion` INT(11) NOT NULL AUTO_INCREMENT,
  `descripcion` VARCHAR(300) NULL DEFAULT NULL,
  `borrar` TINYINT(1) NOT NULL,
  PRIMARY KEY (`idCotizacion`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;




-- -----------------------------------------------------
-- Table `softbi_SIS`.`OrdenServicio`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `softbi_SIS`.`OrdenServicio` (
  `idOrdenServicio` INT(11) NOT NULL AUTO_INCREMENT,
  `fechaInicio` DATE NOT NULL,
  `fechaFinalServicio` DATE NULL DEFAULT NULL,
  `tipoServicio` VARCHAR(45) NOT NULL,
  `descripcionServicio` VARCHAR(300) NULL DEFAULT NULL,
  `informeServicio` VARCHAR(300) NULL DEFAULT NULL,
  `descripcionFinalServicio` VARCHAR(300) NULL,
  `evidenciaFotografica` VARCHAR(300) NULL,
  `borrar` TINYINT(1) NOT NULL,
  `estadoServicio` CHAR(3) NOT NULL,
  `productosSolicitados` VARCHAR(300) NULL,
  `persona_idPersona` INT(11) NOT NULL,
  `asignaciontecnico_idAsignacionTecnico` INT(11) NOT NULL,
  PRIMARY KEY (`idOrdenServicio`),
  INDEX `fk_OrdenServicio_persona1_idx` (`persona_idPersona` ASC),
  INDEX `fk_OrdenServicio_asignaciontecnico1_idx` (`asignaciontecnico_idAsignacionTecnico` ASC),
  CONSTRAINT `fk_OrdenServicio_persona1`
    FOREIGN KEY (`persona_idPersona`)
    REFERENCES `softbi_SIS`.`persona` (`idPersona`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_OrdenServicio_asignaciontecnico1`
    FOREIGN KEY (`asignaciontecnico_idAsignacionTecnico`)
    REFERENCES `softbi_SIS`.`asignaciontecnico` (`idAsignacionTecnico`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;
insert into OrdenServicio (fechaInicio, fechaFinalServicio,tipoServicio,descripcionServicio,
informeServicio,descripcionFinalServicio,evidenciaFotografica,borrar,estadoServicio,persona_idPersona,
asignaciontecnico_idAsignacionTecnico) value
('2017-03-12',null,'Correctivo','Reparacion maquinas aficio',null,null,null,1,'A',2,1);
-- -----------------------------------------------------
-- Table `softbi_SIS`.`CalificacionServicio`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `softbi_SIS`.`CalificacionServicio` (
  `idCalificacionServicio` INT NOT NULL AUTO_INCREMENT,
  `puntualidad` INT NOT NULL,
  `ordenTecnico` INT NOT NULL,
  `ejecucionServicio` INT NOT NULL,
  `comentariosServicio` VARCHAR(300) NULL,
  `estado` TINYINT(1) NOT NULL,
  `borrar` TINYINT(1) NOT NULL,
  `OrdenServicio_idOrdenServicio` INT(11) NOT NULL,
  PRIMARY KEY (`idCalificacionServicio`),
  INDEX `fk_CalificacionServicio_OrdenServicio1_idx` (`OrdenServicio_idOrdenServicio` ASC),
  CONSTRAINT `fk_CalificacionServicio_OrdenServicio1`
    FOREIGN KEY (`OrdenServicio_idOrdenServicio`)
    REFERENCES `softbi_SIS`.`OrdenServicio` (`idOrdenServicio`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `softbi_SIS`.`DetalleOrdenServicio`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `softbi_SIS`.`DetalleOrdenServicio` (
  `idDetalleOrdenServicio` INT NOT NULL AUTO_INCREMENT,
  `estado` TINYINT(1) NOT NULL,
  `borrar` TINYINT(1) NOT NULL,
  `OrdenServicio_idOrdenServicio` INT(11) NOT NULL,
  `Asignacion_idAsignacion` INT NOT NULL,
  PRIMARY KEY (`idDetalleOrdenServicio`),
  INDEX `fk_DetalleOrdenServicio_OrdenServicio1_idx` (`OrdenServicio_idOrdenServicio` ASC),
  INDEX `fk_DetalleOrdenServicio_Asignacion1_idx` (`Asignacion_idAsignacion` ASC),
  CONSTRAINT `fk_DetalleOrdenServicio_OrdenServicio1`
    FOREIGN KEY (`OrdenServicio_idOrdenServicio`)
    REFERENCES `softbi_SIS`.`OrdenServicio` (`idOrdenServicio`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_DetalleOrdenServicio_Asignacion1`
    FOREIGN KEY (`Asignacion_idAsignacion`)
    REFERENCES `softbi_SIS`.`Asignacion` (`idAsignacion`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `softbi_SIS`.`DetalleCotizacion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `softbi_SIS`.`DetalleCotizacion` (
  `idDetalleCotizacion` INT NOT NULL AUTO_INCREMENT,
  `valorCotizacion` INT NULL,
  `fechaCotizacion` DATE NULL,
  `descripcionAdmin` VARCHAR(300) NULL,
  `estado` CHAR(5) NULL,
  `borrar` TINYINT(1) NOT NULL,
  `cotizacion_idCotizacion` INT(11) NOT NULL,
  `Asignacion_idAsignacion` INT NOT NULL,
  `persona_idPersona` INT(11) NOT NULL,
  PRIMARY KEY (`idDetalleCotizacion`),
  INDEX `fk_DetalleCotizacion_cotizacion1_idx` (`cotizacion_idCotizacion` ASC),
  INDEX `fk_DetalleCotizacion_Asignacion1_idx` (`Asignacion_idAsignacion` ASC),
  INDEX `fk_DetalleCotizacion_persona1_idx` (`persona_idPersona` ASC),
  CONSTRAINT `fk_DetalleCotizacion_cotizacion1`
    FOREIGN KEY (`cotizacion_idCotizacion`)
    REFERENCES `softbi_SIS`.`cotizacion` (`idCotizacion`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_DetalleCotizacion_Asignacion1`
    FOREIGN KEY (`Asignacion_idAsignacion`)
    REFERENCES `softbi_SIS`.`Asignacion` (`idAsignacion`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_DetalleCotizacion_persona1`
    FOREIGN KEY (`persona_idPersona`)
    REFERENCES `softbi_SIS`.`persona` (`idPersona`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB

select group_concat(distinct p.primernombre, ' ', p.primerapellido) nombres,
count(a.DetalleProducto_idDetalleProducto) as total
from persona as p inner join asignacion as a on a.persona_idPersona = p.idPersona
where p.idPersona != 3 group by p.primernombre;

select * from asignacion as a
inner join detalleproducto as d on d.idDetalleProducto = a.DetalleProducto_idDetalleProducto
where a.estado = true and d.estado = false;

select group_concat(idDetalleOrdenServicio) idDetalleOrdenServicio, 
group_concat(distinct OrdenServicio_idOrdenServicio) OrdenServicio_idOrdenServicio,
group_concat(Asignacion_idAsignacion) asignacion
from detalleordenservicio where borrar = true group by OrdenServicio_idOrdenServicio;

	select o.idOrdenServicio,o.fechaInicio,o.fechaFinalServicio,o.descripcionServicio,
	ifnull(o.descripcionFinalServicio, 'Sin Especificar')as descripcionFinalServicio,o.estadoServicio,
	group_concat(distinct p.primernombre, ' ',  p.primerapellido) as cliente,
	p.direccion,p.numeroIdentificacion,p.telefono,o.tipoServicio,p.correo,
	group_concat(dp.modelo) as modelo, 
	group_concat(distinct t.primernombre, ' ',  t.primerapellido) as tecnico from ordenservicio as o
	inner join persona as p on o.persona_idPersona = p.idPersona
	inner join asignaciontecnico as a on o.asignaciontecnico_idAsignacionTecnico =  a.idAsignacionTecnico
	inner join persona as t on t.idPersona = a.Persona_idPersona
	inner join detalleordenservicio as ds on o.idOrdenServicio = ds.OrdenServicio_idOrdenServicio
	inner join asignacion as ag on ag.idAsignacion = ds.Asignacion_idAsignacion
	inner join detalleproducto as dp on ag.DetalleProducto_idDetalleProducto = dp.idDetalleProducto
	where o.idOrdenServicio = 1 group by o.idOrdenServicio;


select c.idContrato, ifnull(c.tipoContrato, 'Sin Especificar') as tipoContrato, 
ifnull(c.valorContrato, 0000) as valorContrato, 
group_concat( distinct p.primernombre, ' ', p.primerapellido) as
cliente from contrato as c
inner join persona as p on c.Persona_idPersona = p.idPersona
group by c.idContrato;


select c.idCotizacion, c.descripcion, c.fechaCotizacion,
c.valorCotizacion, group_concat(p.primernombre, ' ', p.primerapellido) as cliente
from cotizacion as c inner join persona as p
on c.Persona_idPersona = p.idPersona 
group by c.idCotizacion;


select d.idDetalleCotizacion,d.valorCotizacion,d.fechaCotizacion,d.estado, d.descripcionAdmin,
group_concat(d.cotizacion_idCotizacion) as cotizacion_idCotizacion ,d.Asignacion_idAsignacion,
d.persona_idPersona from Detallecotizacion as d group by cotizacion_idCotizacion;