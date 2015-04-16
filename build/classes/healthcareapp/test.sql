-- phpMyAdmin SQL Dump
-- version 4.1.6
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Mar 23, 2015 at 05:55 PM
-- Server version: 5.6.16
-- PHP Version: 5.5.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `test`
--
CREATE DATABASE IF NOT EXISTS `test` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `test`;

-- --------------------------------------------------------

--
-- Table structure for table `appointments`
--

CREATE TABLE IF NOT EXISTS `appointments` (
  `pkey` int(11) NOT NULL AUTO_INCREMENT,
  `patient` varchar(64) NOT NULL,
  `doctor` varchar(64) NOT NULL,
  `date` datetime NOT NULL,
  PRIMARY KEY (`pkey`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `appointments`
--

INSERT INTO `appointments` (`pkey`, `patient`, `doctor`, `date`) VALUES
(1, 'cdjarboe', 'cjarbo2', '2015-03-20 00:00:00'),
(2, 'cdjarboe', 'cjarbo2', '2015-03-27 00:00:00'),
(3, 'cdjarboe', 'cjarbo2', '2015-03-25 05:55:12');

-- --------------------------------------------------------

--
-- Table structure for table `patientdoctorjoin`
--

CREATE TABLE IF NOT EXISTS `patientdoctorjoin` (
  `pkey` int(11) NOT NULL AUTO_INCREMENT,
  `patient` varchar(20) NOT NULL,
  `doctor` varchar(20) NOT NULL,
  PRIMARY KEY (`pkey`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `patientdoctorjoin`
--

INSERT INTO `patientdoctorjoin` (`pkey`, `patient`, `doctor`) VALUES
(1, 'cdjarboe', 'cjarbo2');

-- --------------------------------------------------------

--
-- Table structure for table `reports`
--

CREATE TABLE IF NOT EXISTS `reports` (
  `pkey` int(11) NOT NULL AUTO_INCREMENT,
  `patient` varchar(20) NOT NULL,
  `dateTime` datetime NOT NULL,
  `pain` int(11) NOT NULL,
  `drowsiness` int(11) NOT NULL,
  `nausea` int(11) NOT NULL,
  `anxiety` int(11) NOT NULL,
  `depression` int(11) NOT NULL,
  `comments` varchar(255) NOT NULL,
  PRIMARY KEY (`pkey`),
  KEY `pkey` (`pkey`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `reports`
--

INSERT INTO `reports` (`pkey`, `patient`, `dateTime`, `pain`, `drowsiness`, `nausea`, `anxiety`, `depression`, `comments`) VALUES
(1, 'cdjarboe', '2015-03-13 00:00:00', 1, 5, 3, 4, 2, 'slurpy'),
(2, 'cdjarboe', '2015-03-21 02:08:49', 1, 1, 1, 1, 1, 'h h h'),
(3, 'cdjarboe', '2015-03-21 11:25:51', 2, 1, 4, 5, 4, '1234');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `userName` varchar(14) NOT NULL,
  `passHash` varchar(14) NOT NULL,
  `userType` int(11) NOT NULL,
  `lastLogin` datetime NOT NULL,
  `fullname` varchar(64) NOT NULL,
  PRIMARY KEY (`userName`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`userName`, `passHash`, `userType`, `lastLogin`, `fullname`) VALUES
('cdjarboe', 'ichabod2', 0, '2015-03-16 00:00:00', 'Jay Clinton'),
('cjarbo2', 'ichabod', 1, '2015-03-15 00:00:00', 'Clinton Jarboe');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
