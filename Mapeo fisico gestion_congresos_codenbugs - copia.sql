-- Cada clase ENUM con su nombre, las demas clases con sus metodos para registrar y sus atributos
CREATE DATABASE IF NOT EXISTS gestion_congresos_codenbugs;

USE gestion_congresos_codenbugs;

CREATE TABLE IF NOT EXISTS usuario (
    id VARCHAR(20) NOT NULL,
    institucion VARCHAR(100),
    nombre VARCHAR(100) NOT NULL,
    numero_telefonico VARCHAR(20) NOT NULL,
    correo VARCHAR(100) NOT NULL,
    monedero double NOT NULL DEFAULT 0,
    foto VARCHAR(100),
    tipo_usuario ENUM('ADMIN_CONGRESO','ADMIN_SISTEMA','PARTICIPANTE') NOT NULL,
    password VARCHAR(20) NOT NULL,
    CONSTRAINT pk_usuario PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS participante(
nombre VARCHAR(100) NOT NULL,
id_usuario VARCHAR(20) NOT NULL,
tipo_participante ENUM('PONENTE','TALLERISTA','ASISTENTE') NOT NULL,
CONSTRAINT fk_id_usuario FOREIGN KEY (id_usuario) REFERENCES usuario(id)
);

CREATE TABLE IF NOT EXISTS congreso(
codigo_congreso VARCHAR(20) NOT NULL,
fecha_inicio DATE NOT NULL,
precio DOUBLE NOT NULL,
CONSTRAINT pk_codigo_congreso PRIMARY KEY (codigo_congreso)
);

CREATE TABLE IF NOT EXISTS actividad(
id_encargado VARCHAR(100) NOT NULL,
descripcion VARCHAR(500) NOT NULL,
hora_inicio TIME NOT NULL,
hora_fin TIME NOT NULL,
nombre_actividad VARCHAR(100) NOT NULL,
codigo_actividad VARCHAR(20) NOT NULL,
codigo_congreso VARCHAR(20) NOT NULL,
tipo ENUM('PONENCIA','TALLER') NOT NULL,
salon VARCHAR(100) NOT NULL,
CONSTRAINT pk_codigo_actividad PRIMARY KEY (codigo_actividad),
CONSTRAINT fk_codigo_congreso FOREIGN KEY (codigo_congreso) REFERENCES congreso(codigo_congreso),
CONSTRAINT fk_id_encargado FOREIGN KEY (id_encargado) REFERENCES usuario(id)
);

CREATE TABLE IF NOT EXISTS inscripcion(
codigo_congreso VARCHAR(20) NOT NULL,
costo double NOT NULL DEFAULT 35,
id_usuario VARCHAR(100) NOT NULL,
CONSTRAINT fk_codigo_congreso_inscripcion FOREIGN KEY (codigo_congreso) REFERENCES congreso(codigo_congreso),
CONSTRAINT fk_id_usuario_inscripcion FOREIGN KEY (id_usuario) REFERENCES usuario(id)
 );
 
 Create TABLE IF NOT EXISTS pago(
 codigo_congreso VARCHAR(20) NOT NULL,
 fecha DATE NOT NULL,
 monto double NOT NULL,
 id_usuario VARCHAR(100) NOT NULL,
 CONSTRAINT fk_codigo_congreso_pago FOREIGN KEY (codigo_congreso) REFERENCES congreso(codigo_congreso),
 CONSTRAINT fk_id_usuario_pago FOREIGN KEY (id_usuario) REFERENCES usuario(id)
  );
 
 
 CREATE TABLE IF NOT EXISTS institucion(
 codigo_congreso VARCHAR(20) NOT NULL,
 id_administrador VARCHAR(100) NOT NULL,
 nombre_institucion VARCHAR(100) NOT NULL,
 CONSTRAINT pk_nombre_institucion PRIMARY KEY (nombre_institucion),
 CONSTRAINT fk_codigo_congreso_institucion FOREIGN KEY (codigo_congreso) REFERENCES congreso(codigo_congreso),
 CONSTRAINT fk_id_administrador FOREIGN KEY (id_administrador) REFERENCES usuario(id)
 );
 
 CREATE TABLE IF NOT EXISTS salones(
 nombre_salon VARCHAR(100) NOT NULL,
 institucion VARCHAR(100) NOT NULL,
 CONSTRAINT pk_nombre_salon PRIMARY KEY (nombre_salon),
 CONSTRAINT fk_institucion FOREIGN KEY (institucion) REFERENCES institucion(nombre_institucion)
 );
 
 CREATE TABLE IF NOT EXISTS instalacion(
 nombre VARCHAR(100) NOT NULL,
 codigo_congreso VARCHAR(20),
 CONSTRAINT pk_nombre PRIMARY KEY (nombre),
 CONSTRAINT fk_codigo_congreso_instalacion FOREIGN KEY (codigo_congreso) REFERENCES congreso(codigo_congreso)
 );
 
 CREATE TABLE IF NOT EXISTS diploma(
 codigo_congreso VARCHAR(20) NOT NULL,
 id_participante VARCHAR(100) NOT NULL, 
 CONSTRAINT fk_codigo_congreso_diploma FOREIGN KEY (codigo_congreso) REFERENCES congreso(codigo_congreso),
 CONSTRAINT fk_id_participante FOREIGN KEY (id_participante) REFERENCES usuario(id)
  );
  
  CREATE TABLE IF NOT EXISTS asistencia(
  codigo_actividad VARCHAR(20) NOT NULL,
  id_participante VARCHAR(20) NOT NULL,
  CONSTRAINT fk_codigo_actividad FOREIGN KEY (codigo_actividad) REFERENCES actividad(codigo_actividad),
  CONSTRAINT fk_id_participante_asistencia FOREIGN KEY (id_participante) REFERENCES usuario(id)
  );
  
  ALTER TABLE usuario
  ADD CONSTRAINT fk_institucion_usuario FOREIGN KEY (institucion) REFERENCES institucion(nombre_institucion);
  
  ALTER TABLE congreso
  ADD COLUMN institucion VARCHAR(100) NOT NULL, 
  ADD COLUMN instalacion VARCHAR(100)NOT NULL;
  
  ALTER TABLE congreso
  ADD CONSTRAINT fk_institucion_congreso FOREIGN KEY (institucion) REFERENCES institucion(nombre_institucion),
  ADD CONSTRAINT fk_instalacion FOREIGN KEY (instalacion) REFERENCES instalacion(nombre);

  
  CREATE TABLE IF NOT EXISTS taller(
  codigo_actividad VARCHAR(20) NOT NULL,
  cupo_maximo INT NOT NULL,
  cupo_disponibke INT NOT NULL,
  CONSTRAINT pk_codigo_actividad_taller PRIMARY KEY(codigo_actividad),
  CONSTRAINT fk_codigo_actividad_taller FOREIGN KEY(codigo_actividad) REFERENCES actividad(codigo_actividad)
  );
  
  CREATE TABLE IF NOT EXISTS reserva(
  id_usuario VARCHAR(100) NOT NULL,
  codigo_congreso VARCHAR(20) NOT NULL,
  codigo_actividad VARCHAR(20) NOT NULL,
  CONSTRAINT fk_id_usuario_reserva FOREIGN KEY (id_usuario) REFERENCES usuario(id),
  CONSTRAINT fk_codigo_congreso_reserva FOREIGN KEY (codigo_congreso) REFERENCES congreso(codigo_congreso),
  CONSTRAINT fk_codigo_actividad_reserva FOREIGN KEY (codigo_actividad) REFERENCES actividad(codigo_actividad)
  );
  
  CREATE TABLE IF NOT EXISTS configuracion_ganancia(
  id INT NOT NULL DEFAULT 1,
  total_recaudado DOUBLE DEFAULT 0.0,
  total_ganancia DOUBLE DEFAULT 0.0,
  porcentaje_comision DOUBLE DEFAULT 0.1
  );
  
  INSERT INTO configuracion_ganancia (id, total_recaudado, total_ganancia, porcentaje_comision) VALUES (1, 0.0, 0.0, 0.1);
  
  GRANT ALL PRIVILEGES ON gestion_congresos_codenbugs.* TO 'universal'@'localhost';
  FLUSH PRIVILEGES;