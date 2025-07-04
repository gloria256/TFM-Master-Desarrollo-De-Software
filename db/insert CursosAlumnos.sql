DELETE FROM `cursosalumnosdb`.`Cursos_has_Alumnos`;
DELETE FROM `cursosalumnosdb`.`Cursos`;
DELETE FROM `cursosalumnosdb`.`Alumnos`;

ALTER TABLE `cursosalumnosdb`.`Cursos` AUTO_INCREMENT = 1 ;
INSERT INTO `cursosalumnosdb`.`Cursos` VALUES (1,'Spring Boot', 50, 'Profesor Uno', 40, 'Desarrollo','');
INSERT INTO `cursosalumnosdb`.`Cursos` VALUES (2,'Java SE', 20, 'Profesor Dos', 15, 'Desarrollo','');
INSERT INTO `cursosalumnosdb`.`Cursos` VALUES (3,'Java EE', 30, 'Profesor Dos', 35, 'Desarrollo','');
INSERT INTO `cursosalumnosdb`.`Cursos` VALUES (4,'Accesibilidad en la enseñanza online', 20, 'Profesor Tres', 10, 'Educación','');
INSERT INTO `cursosalumnosdb`.`Cursos` VALUES (5,'Contabilidad Básica', 15, 'Profesor Cuatro', 20, 'Finanzas','');
INSERT INTO `cursosalumnosdb`.`Cursos` VALUES (6,'Contabilidad Avanzada', 30, 'Profesor Cuatro', 40, 'Finanzas','');

ALTER TABLE `cursosalumnosdb`.`Alumnos` AUTO_INCREMENT = 1 ;
INSERT INTO `cursosalumnosdb`.`Alumnos` VALUES (1, 'Alumno Uno', 'alumno.uno@uah.es');
INSERT INTO `cursosalumnosdb`.`Alumnos` VALUES (2, 'Alumno Dos', 'alumno.dos@uah.es');
INSERT INTO `cursosalumnosdb`.`Alumnos` VALUES (3, 'Alumno Tres', 'alumno.tres@uah.es');
INSERT INTO `cursosalumnosdb`.`Alumnos` VALUES (4, 'Alumno Cuatro', 'alumno.cuatro@uah.es');
INSERT INTO `cursosalumnosdb`.`Alumnos` VALUES (5, 'Alumno Cinco', 'alumno.cinco@uah.es');

INSERT INTO `cursosalumnosdb`.`Cursos_has_Alumnos` VALUES (1, 1);
INSERT INTO `cursosalumnosdb`.`Cursos_has_Alumnos` VALUES (2, 2);
INSERT INTO `cursosalumnosdb`.`Cursos_has_Alumnos` VALUES (3, 2);
INSERT INTO `cursosalumnosdb`.`Cursos_has_Alumnos` VALUES (3, 3);
INSERT INTO `cursosalumnosdb`.`Cursos_has_Alumnos` VALUES (4, 4);
INSERT INTO `cursosalumnosdb`.`Cursos_has_Alumnos` VALUES (5, 4);
INSERT INTO `cursosalumnosdb`.`Cursos_has_Alumnos` VALUES (5, 5);
INSERT INTO `cursosalumnosdb`.`Cursos_has_Alumnos` VALUES (6, 5);