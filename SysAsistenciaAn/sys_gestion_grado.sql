-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 11-04-2024 a las 14:54:18
-- Versión del servidor: 10.4.28-MariaDB
-- Versión de PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `sys_gestion_grado`
--

----------------------------------------------------------------------------

--
-- Estructura de tabla para la tabla `global_usuario_rol`
--

CREATE TABLE `global_usuario_rol` (
  `usuario_id` bigint(20) NOT NULL,
  `rol_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `global_usuario_rol`
--

INSERT INTO `global_usuario_rol` (`usuario_id`, `rol_id`) VALUES
(1, 1),
(1, 2),
(8, 1),
(8, 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `gobal_rol`
--

CREATE TABLE `gobal_rol` (
  `id` bigint(20) NOT NULL,
  `rol_nombre` enum('ROLE_ADMIN','ROLE_USER') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `gobal_rol`
--

INSERT INTO `gobal_rol` (`id`, `rol_nombre`) VALUES
(1, 'ROLE_ADMIN'),
(2, 'ROLE_USER');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `gobal_usuario`
--

CREATE TABLE `gobal_usuario` (
  `id` bigint(20) NOT NULL,
  `apellidos` varchar(255) NOT NULL,
  `correo` varchar(255) NOT NULL,
  `dni` varchar(255) NOT NULL,
  `estado` varchar(255) NOT NULL,
  `nombres` varchar(255) NOT NULL,
  `offlinex` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `perfil_prin` varchar(255) NOT NULL,
  `fecha_actualizacion` datetime(6) DEFAULT NULL,
  `fecha_creacion` datetime(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `gobal_usuario`
--

INSERT INTO `gobal_usuario` (`id`, `apellidos`, `correo`, `dni`, `estado`, `nombres`, `offlinex`, `password`, `perfil_prin`, `fecha_actualizacion`, `fecha_creacion`) VALUES
(1, 'Mamani Pari', 'davidmp@upeu.edu.pe', '43631917', 'Activo', 'David', 'SI', '$2a$10$XeTc0WESEycYxFrzLzT4jO/LTPndni8aoEkTdZyJwXzdWzJRR.pAC', 'upeu', NULL, NULL),
(8, 'Mamani Pari', 'eliasmp@upeu.edu.pe', '43631918', 'Activo', 'Elias', 'SI', '$2a$10$kD3JdRD68NGzKMzWPGucKOzyYviPagSDzHMyzlBZWPu6tzkFaDITq', 'upeu', NULL, NULL);

--
-- Estructura de tabla para la tabla `upeu_persona`
--

CREATE TABLE `upeu_persona` (
  `id` bigint(20) NOT NULL,
  `apellido_mat` varchar(40) NOT NULL,
  `apellido_pat` varchar(40) NOT NULL,
  `celular` varchar(12) NOT NULL,
  `clave` varchar(255) NOT NULL,
  `codigo` varchar(10) NOT NULL,
  `correo` varchar(40) NOT NULL,
  `estado` varchar(8) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `tipo` varchar(12) NOT NULL,
  `escuela_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `upeu_persona`
--

INSERT INTO `upeu_persona` (`id`, `apellido_mat`, `apellido_pat`, `celular`, `clave`, `codigo`, `correo`, `estado`, `nombre`, `tipo`, `escuela_id`) VALUES
(1, 'Mamani', 'Pari', '951782520', '123456', '43631917', 'dd@gmail.com', 'Activo', 'Dario', 'DTN', 1);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `global_usuario_rol`
--
ALTER TABLE `global_usuario_rol`
  ADD PRIMARY KEY (`usuario_id`,`rol_id`),
  ADD KEY `FKp8ulk53js4l5c0nxbudxd34tl` (`rol_id`);

--
-- Indices de la tabla `gobal_rol`
--
ALTER TABLE `gobal_rol`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `gobal_usuario`
--
ALTER TABLE `gobal_usuario`
  ADD PRIMARY KEY (`id`);
  
--
-- Indices de la tabla `upeu_persona`
--
ALTER TABLE `upeu_persona`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKkhqqsdy9tdqhuaqlkjynaidcn` (`escuela_id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `gobal_rol`
--
ALTER TABLE `gobal_rol`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `gobal_usuario`
--
ALTER TABLE `gobal_usuario`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de la tabla `upeu_persona`
--
ALTER TABLE `upeu_persona`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `global_usuario_rol`
--
ALTER TABLE `global_usuario_rol`
  ADD CONSTRAINT `FK7c58vygd75tq899d94hxr5ubw` FOREIGN KEY (`usuario_id`) REFERENCES `gobal_usuario` (`id`),
  ADD CONSTRAINT `FKp8ulk53js4l5c0nxbudxd34tl` FOREIGN KEY (`rol_id`) REFERENCES `gobal_rol` (`id`);

--
-- Filtros para la tabla `upeu_persona`
--
ALTER TABLE `upeu_persona`
  ADD CONSTRAINT `FKkhqqsdy9tdqhuaqlkjynaidcn` FOREIGN KEY (`escuela_id`) REFERENCES `upeu_escuela` (`id`);

COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
