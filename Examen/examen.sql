SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

CREATE DATABASE IF NOT EXISTS `examen` DEFAULT CHARACTER SET utf8 COLLATE utf8_spanish2_ci;
USE `examen`;

DROP TABLE IF EXISTS `opciones`;
CREATE TABLE IF NOT EXISTS `opciones` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idPregunta` int(11) NOT NULL,
  `enunciado` varchar(255) NOT NULL,
  `correcta` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `pregunta_id` (`idPregunta`)
) ENGINE=InnoDB AUTO_INCREMENT=109 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

INSERT INTO `opciones` (`id`, `idPregunta`, `enunciado`, `correcta`) VALUES
(1, 1, 'George Washington', 1),
(2, 1, 'El primer presidente', 1),
(3, 1, 'Thomas Jefferson', 0),
(4, 1, 'James Madison', 0),
(5, 2, 'París', 1),
(6, 2, 'Ciudad del amor', 1),
(7, 2, 'Marsella', 0),
(8, 2, 'Lyon', 0),
(9, 3, 'Euro', 1),
(10, 3, 'Pizza', 0),
(11, 3, 'Dirham', 0),
(12, 3, 'Piccolo', 0),
(13, 4, 'Cristóbal Colón', 1),
(14, 4, 'Américo Vespuccio', 0),
(15, 4, 'Juan Sebastián Elcano', 0),
(16, 4, 'Colón', 1),
(17, 5, '100ºC', 1),
(18, 5, '120ºC', 0),
(19, 5, '212ºF', 1),
(20, 5, '225ºF', 0),
(21, 6, 'Francia', 1),
(22, 6, 'Bélgica', 0),
(23, 6, 'Luxemburgo', 0),
(24, 6, 'República de Francia', 1),
(25, 7, 'Sydney', 0),
(26, 7, 'No tiene capital', 0),
(27, 7, 'Camberra', 1),
(28, 7, 'Está al Este', 1),
(29, 8, 'Homero', 1),
(30, 8, 'Arquímides', 0),
(31, 8, 'Leónidas', 0),
(32, 8, 'Homerus', 1),
(33, 9, 'Lope de Vega', 0),
(34, 9, 'Miguel de Cervantes', 1),
(35, 9, 'Cervantes', 1),
(36, 9, 'Miguel', 1),
(37, 10, 'Australia', 1),
(38, 10, 'Está en Oceanía', 1),
(39, 10, 'Nueva Zelanda', 0),
(40, 10, 'Filipinas', 0),
(41, 11, 'Pirámides de Giza', 1),
(42, 11, 'Esfinge ', 1),
(43, 11, 'Los cocodrilos del Nilo', 0),
(44, 11, 'La arena del desierto', 0),
(45, 12, 'Hipócrates', 1),
(46, 12, 'Alexander Flemming', 0),
(47, 12, 'Los de Anatomía de Gray', 0),
(48, 12, 'Hipócrates de Cos', 1),
(49, 13, 'Francia', 1),
(50, 13, 'Austria', 0),
(51, 13, 'República de Francia', 1),
(52, 13, 'Irlanda', 0),
(53, 14, 'Arthur ', 1),
(54, 14, 'Conan', 1),
(55, 14, 'Doyle', 1),
(56, 14, 'Paco Ramírez Torres', 0),
(57, 15, 'Rusia', 1),
(58, 15, 'Federación de Rusia', 1),
(59, 15, 'China', 0),
(60, 15, 'Canadá', 0),
(61, 16, 'Moscú', 1),
(62, 16, 'Moscow', 1),
(63, 16, 'San Petesburgo', 0),
(64, 16, 'Volgogrado', 0),
(65, 17, 'Holanda', 1),
(66, 17, 'Países Bajos', 0),
(67, 17, 'Luxemburgo', 0),
(68, 17, 'Venecia', 0),
(69, 18, 'Yen', 1),
(70, 18, 'Yuan', 0),
(71, 18, 'Won', 0),
(72, 18, 'Yan', 0),
(73, 19, 'Antoine de Saint-Exupéry', 1),
(74, 19, 'Es anónimo', 0),
(75, 19, 'No es anónimo', 1),
(76, 19, 'El Antoine', 1),
(77, 20, 'Ciudad del Vaticano', 1),
(78, 20, 'Vaticano', 1),
(79, 20, 'Andorra', 0),
(80, 20, 'Hungría', 0),
(81, 21, 'Fue en mayo de 1789', 1),
(82, 21, '5 de mayo de 1788', 0),
(83, 21, '5 de mayo de 1789', 1),
(84, 21, '5 de mayo de 1798', 0),
(85, 22, 'Nigeria', 1),
(86, 22, 'Marruecos', 0),
(87, 22, 'República Federal de Nigeria', 1),
(88, 22, 'Argelia', 0),
(89, 23, 'Amazonas', 0),
(90, 23, 'Nilo', 1),
(91, 23, 'El que está en Egipto', 1),
(92, 23, 'Mississippi', 0),
(93, 24, 'Adolfo Suárez', 1),
(94, 24, 'El rey Emérito', 1),
(95, 24, 'Felipe VI', 0),
(96, 24, 'Torcuato Fernández Miranda', 1),
(97, 25, 'Brasil', 1),
(98, 25, 'México', 0),
(99, 25, 'Argentina', 0),
(100, 25, 'Colombia', 0),
(101, 26, 'Leonardo Di Caprio', 0),
(102, 26, 'Leonardo Da Vinci', 1),
(103, 26, 'Los alumnos de Da Vinci', 0),
(104, 26, 'Da Vinci', 1),
(105, 27, 'Burj Khalifa', 1),
(106, 27, 'Empire State', 0),
(107, 27, 'Shanghai Tower', 0),
(108, 27, 'El to alto ese que está en Dubai', 1);

DROP TABLE IF EXISTS `preguntas`;
CREATE TABLE IF NOT EXISTS `preguntas` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pregunta` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=91 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

INSERT INTO `preguntas` (`id`, `pregunta`) VALUES
(1, '¿Quién fue el primer presidente de Estados Unidos?'),
(2, '¿Cuál es la capital de Francia?'),
(3, '¿Cuál es la moneda oficial de Italia?'),
(4, '¿Quién descubrió América?'),
(5, '¿A qué temperatura hierve el agua?'),
(6, '¿Qué país es conocido por el palacio de Versalles?'),
(7, '¿Cuál es la capital de Australia?'),
(8, '¿Quién escribió la Odisea?'),
(9, '¿Quién es el autor de \"Don Quijote de la Mancha\"?'),
(10, '¿Qué país es conocido por la \"Opera de Sydney\"?'),
(11, '¿Cuáles son los monumentos más famosos de Egipto?'),
(12, '¿Quién es el padre de la medicina?'),
(13, '¿Qué país es conocido por la Torre Eiffel?'),
(14, '¿Quién escribió \"Sherlock Holmes\"?'),
(15, '¿Cuál es el país más grande del mundo en términos de superficie?'),
(16, '¿Cuál es la capital de Rusia?'),
(17, '¿Qué país es conocido por sus canales y molinos de viento?'),
(18, '¿Cuál es la moneda oficial de Japón?'),
(19, '¿Quién es el autor de \"El Principito\"?'),
(20, '¿Cuál es el país más pequeño del mundo en términos de población?'),
(21, '¿En qué año se produjo la Revolución Francesa?'),
(22, '¿Cuál es el país más poblado de África?'),
(23, '¿Cuál es el río más largo del mundo?'),
(24, '¿Qué personas fueron relevantes durante la Transición?'),
(25, '¿Cuál es el país más grande de América Latina?'),
(26, '¿Quién pintó la Mona Lisa?'),
(27, '¿Cuál es el edificio más alto del mundo?');


ALTER TABLE `opciones`
  ADD CONSTRAINT `opciones_ibfk_1` FOREIGN KEY (`idPregunta`) REFERENCES `preguntas` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
