-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 02, 2016 at 04:15 AM
-- Server version: 5.7.16-log
-- PHP Version: 5.6.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `bank`
--

-- --------------------------------------------------------

--
-- Table structure for table `balance`
--

CREATE TABLE `balance` (
  `username` varchar(40) NOT NULL,
  `money` int(10) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `balance`
--

INSERT INTO `balance` (`username`, `money`) VALUES
('lucky123', 0),
('Nitish2027', 18000);

-- --------------------------------------------------------

--
-- Table structure for table `client`
--

CREATE TABLE `client` (
  `username` varchar(40) NOT NULL,
  `password` longblob NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `client`
--

INSERT INTO `client` (`username`, `password`) VALUES
('lucky123', 0x34653635366631653831363866646536356431396564373331336465653939),
('Nitish2027', 0x6364653465323936333864333132653830323637663563316263363133);

-- --------------------------------------------------------

--
-- Table structure for table `current`
--

CREATE TABLE `current` (
  `sno` int(10) NOT NULL DEFAULT '0',
  `username` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `current`
--

INSERT INTO `current` (`sno`, `username`) VALUES
(1, 'Nitish2027');

-- --------------------------------------------------------

--
-- Table structure for table `employee`
--

CREATE TABLE `employee` (
  `username` varchar(40) NOT NULL,
  `password` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `employee`
--

INSERT INTO `employee` (`username`, `password`) VALUES
('nitish', '123456');

-- --------------------------------------------------------

--
-- Table structure for table `loan`
--

CREATE TABLE `loan` (
  `username` varchar(40) NOT NULL,
  `amount` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `loan`
--

INSERT INTO `loan` (`username`, `amount`) VALUES
('Nitish2027', 200),
('Nitish2027', 200),
('Nitish2027', 200),
('Nitish2027', 200),
('Nitish2027', 200),
('Nitish2027', 1000),
('Nitish2027', 100),
('aditya123', 9000000),
('aditya123', 100),
('Nitish2027', 6000);

-- --------------------------------------------------------

--
-- Table structure for table `newclient`
--

CREATE TABLE `newclient` (
  `firstname` varchar(40) NOT NULL,
  `lastname` varchar(40) NOT NULL,
  `username` varchar(40) NOT NULL,
  `email` varchar(40) NOT NULL,
  `reemail` varchar(40) NOT NULL,
  `password` longblob NOT NULL,
  `bday` varchar(10) NOT NULL,
  `bmonth` varchar(10) NOT NULL,
  `byear` varchar(10) NOT NULL,
  `gender` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `newclient`
--

INSERT INTO `newclient` (`firstname`, `lastname`, `username`, `email`, `reemail`, `password`, `bday`, `bmonth`, `byear`, `gender`) VALUES
('Lakshay', 'Jindal', 'lucky123', 'lucky123@gmail.com', 'lucky123@gmail.com', 0x34653635366631653831363866646536356431396564373331336465653939, '28', '4', '1996', 'Male'),
('Nitish', 'Singh', 'Nitish2027', 'nitish2027@gmail.com', 'nitish2027@gmail.com', 0x6364653465323936333864333132653830323637663563316263363133, '20', '11', '1995', 'Male');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `balance`
--
ALTER TABLE `balance`
  ADD PRIMARY KEY (`username`);

--
-- Indexes for table `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`username`);

--
-- Indexes for table `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`username`);

--
-- Indexes for table `newclient`
--
ALTER TABLE `newclient`
  ADD PRIMARY KEY (`username`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
