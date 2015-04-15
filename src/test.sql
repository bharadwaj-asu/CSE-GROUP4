-- phpMyAdmin SQL Dump
-- version 4.1.6
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Apr 15, 2015 at 03:41 PM
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

-- --------------------------------------------------------

--
-- Table structure for table `appointments`
--

CREATE TABLE IF NOT EXISTS `appointments` (
  `pkey` int(11) NOT NULL AUTO_INCREMENT,
  `patient` varchar(64) NOT NULL,
  `doctor` varchar(64) NOT NULL,
  `date` datetime NOT NULL,
  `dateMade` datetime NOT NULL,
  `authorized` tinyint(1) NOT NULL DEFAULT '0',
  `active` tinyint(1) NOT NULL DEFAULT '1',
  `notes` varchar(512) DEFAULT NULL,
  PRIMARY KEY (`pkey`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=11 ;

--
-- Dumping data for table `appointments`
--

INSERT INTO `appointments` (`pkey`, `patient`, `doctor`, `date`, `dateMade`, `authorized`, `active`, `notes`) VALUES
(1, 'cdjarboe', 'cjarbo2', '2015-06-21 12:00:00', '2015-04-01 00:00:00', 0, 1, ''),
(2, 'cdjarboe', 'cjarbo2', '2015-06-30 18:15:00', '2015-04-01 00:00:00', 0, 1, ''),
(3, 'cdjarboe', 'cjarbo2', '2015-05-22 12:00:00', '2015-04-30 00:00:00', 0, 1, ''),
(5, 'cdjarboe', 'cjarbo2', '2015-06-20 12:00:00', '2015-04-14 00:00:00', 0, 1, NULL),
(6, 'rgray', 'cjarbo2', '2015-06-21 12:00:00', '2015-04-14 00:00:00', 0, 1, NULL),
(7, 'rgray', 'cjarbo2', '2015-03-01 20:00:00', '2015-04-15 01:57:02', 0, 1, NULL),
(8, 'rgray', 'cjarbo2', '2015-01-01 14:00:00', '2015-04-15 02:00:47', 0, 1, NULL),
(9, 'cdjarboe', 'cjarbo2', '2015-01-01 17:00:00', '2015-04-15 02:01:55', 0, 1, NULL),
(10, 'cdjarboe', 'cjarbo2', '2015-11-01 12:00:00', '2015-04-15 02:02:34', 0, 1, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `patientdoctorjoin`
--

CREATE TABLE IF NOT EXISTS `patientdoctorjoin` (
  `pkey` int(11) NOT NULL AUTO_INCREMENT,
  `patient` varchar(20) NOT NULL,
  `doctor` varchar(20) NOT NULL,
  PRIMARY KEY (`pkey`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `patientdoctorjoin`
--

INSERT INTO `patientdoctorjoin` (`pkey`, `patient`, `doctor`) VALUES
(1, 'cdjarboe', 'cjarbo2'),
(2, 'rgray', 'cjarbo2');

-- --------------------------------------------------------

--
-- Table structure for table `reports`
--

CREATE TABLE IF NOT EXISTS `reports` (
  `pkey` int(11) NOT NULL AUTO_INCREMENT,
  `patient` varchar(20) NOT NULL,
  `doctor` varchar(32) NOT NULL,
  `dateTime` datetime NOT NULL,
  `pain` int(11) NOT NULL,
  `drowsiness` int(11) NOT NULL,
  `nausea` int(11) NOT NULL,
  `anxiety` int(11) NOT NULL,
  `depression` int(11) NOT NULL,
  `comments` varchar(255) NOT NULL,
  `doctorSuggestion` varchar(512) DEFAULT NULL,
  PRIMARY KEY (`pkey`),
  KEY `pkey` (`pkey`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=8 ;

--
-- Dumping data for table `reports`
--

INSERT INTO `reports` (`pkey`, `patient`, `doctor`, `dateTime`, `pain`, `drowsiness`, `nausea`, `anxiety`, `depression`, `comments`, `doctorSuggestion`) VALUES
(1, 'cdjarboe', 'cjarbo2', '2015-03-13 00:00:00', 1, 5, 3, 4, 2, 'slurpy', ''),
(2, 'cdjarboe', 'cjarbo2', '2015-03-21 02:08:49', 1, 1, 1, 1, 1, 'h h h', ''),
(3, 'cdjarboe', 'cjarbo2', '2015-03-21 11:25:51', 2, 1, 4, 5, 4, '1234', '; tickle me chode laddie -Dr. Clinton Jarboe'),
(5, 'cdjarboe', 'cjarbo2', '2015-04-14 17:00:29', 0, 0, 0, 0, 0, '', 'null; eat a chode -Dr. Clinton Jarboe'),
(6, 'rgray', 'cjarbo2', '2015-04-14 17:44:43', 0, 0, 0, 0, 0, 'ouch', 'hello -Dr. Clinton Jarboe; why me -Dr. Clinton Jarboe'),
(7, 'rgray', 'cjarbo2', '2015-04-15 04:14:44', 0, 0, 0, 0, 0, 'test', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `stats`
--

CREATE TABLE IF NOT EXISTS `stats` (
  `pkey` int(11) NOT NULL AUTO_INCREMENT,
  `mean` double NOT NULL,
  `count` double NOT NULL,
  `sd` double NOT NULL,
  `dateTime` datetime NOT NULL,
  PRIMARY KEY (`pkey`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `testtable`
--

CREATE TABLE IF NOT EXISTS `testtable` (
  `pKey` int(11) NOT NULL,
  `name` text NOT NULL,
  PRIMARY KEY (`pKey`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `testtable`
--

INSERT INTO `testtable` (`pKey`, `name`) VALUES
(0, 'bollox'),
(1, '1234');

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
  `salt` varchar(42) NOT NULL,
  PRIMARY KEY (`userName`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`userName`, `passHash`, `userType`, `lastLogin`, `fullname`, `salt`) VALUES
('cdjarboe', 'dkbd', 0, '2015-04-15 00:00:00', 'Jay Clinton', 'dLK5sF4gD'),
('cjarbo2', 'ichabod', 1, '2015-04-15 00:00:00', 'Clinton Jarboe', 'F8gsD6f5'),
('reception', 'hi', 3, '2015-04-15 00:00:00', 'Reception Staff', 's9ds'),
('rgray', 'hello', 0, '2015-04-15 00:00:00', 'Randalf Gray', 'sdL8f'),
('scohen', 'hey', 1, '2015-04-15 06:35:21', 'Sally Cohen', 'd9Fd7A');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
