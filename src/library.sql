-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1:3306
-- Tiempo de generación: 12-10-2023 a las 15:29:23
-- Versión del servidor: 8.0.31
-- Versión de PHP: 8.0.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `library`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `authors`
--

DROP TABLE IF EXISTS `authors`;
CREATE TABLE IF NOT EXISTS `authors` (
  `nit` varchar(30) NOT NULL,
  `name` varchar(30) NOT NULL,
  `worked_editorial` varchar(30) NOT NULL,
  `city` varchar(30) NOT NULL,
  `number_books` int NOT NULL,
  PRIMARY KEY (`nit`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `authors`
--

INSERT INTO `authors` (`nit`, `name`, `worked_editorial`, `city`, `number_books`) VALUES
('123', 'Gabriel Garcia Marquez', 'norma', 'Bogota', 769);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `books`
--

DROP TABLE IF EXISTS `books`;
CREATE TABLE IF NOT EXISTS `books` (
  `code` varchar(30) NOT NULL,
  `name` varchar(30) NOT NULL,
  `editorial` varchar(30) NOT NULL,
  `code_author` varchar(30) NOT NULL,
  `reference` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`code`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `books`
--

INSERT INTO `books` (`code`, `name`, `editorial`, `code_author`, `reference`) VALUES
('09876', 'Doce cuentos peregrinos', 'norma', '123', 'explicación de por qué doce, por qué cuentos y por qué peregrinos');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
