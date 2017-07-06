-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Czas generowania: 07 Lip 2017, 01:09
-- Wersja serwera: 10.1.21-MariaDB
-- Wersja PHP: 5.6.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Baza danych: `sesti`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `uzytkownik`
--

CREATE TABLE `uzytkownik` (
  `id` int(11) NOT NULL,
  `login` varchar(8) COLLATE utf8mb4_unicode_520_ci NOT NULL,
  `imie` varchar(20) COLLATE utf8mb4_unicode_520_ci NOT NULL,
  `nazwisko` varchar(30) COLLATE utf8mb4_unicode_520_ci NOT NULL,
  `email` varchar(40) COLLATE utf8mb4_unicode_520_ci NOT NULL,
  `rola` varchar(20) COLLATE utf8mb4_unicode_520_ci NOT NULL,
  `haslo` varchar(12) COLLATE utf8mb4_unicode_520_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;

--
-- Zrzut danych tabeli `uzytkownik`
--

INSERT INTO `uzytkownik` (`id`, `login`, `imie`, `nazwisko`, `email`, `rola`, `haslo`) VALUES
(1, 'sysadmin', 'sysadmin', 'sysadmin', 'sysadmin@wp.pl', 'administrator', '1234'),
(2, 'admin', 'admin', 'admin', 'admin@wp.pl', 'administrator', '1234'),
(3, 'MS001', 'Marta', 'Skitek', 'm.skitek@wp.pl', 'uzytkownik', '1234'),
(4, 'BM002', 'Beata', 'Minta', 'beataminta@wp.pl', 'uzytkownik', '1234');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `wydzial`
--

CREATE TABLE `wydzial` (
  `id` int(11) NOT NULL,
  `nazwa` varchar(100) COLLATE utf8mb4_unicode_520_ci NOT NULL,
  `symbol` char(20) COLLATE utf8mb4_unicode_520_ci NOT NULL,
  `numerKonta` varchar(20) COLLATE utf8mb4_unicode_520_ci NOT NULL,
  `uwagi` varchar(70) COLLATE utf8mb4_unicode_520_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;

--
-- Zrzut danych tabeli `wydzial`
--

INSERT INTO `wydzial` (`id`, `nazwa`, `symbol`, `numerKonta`, `uwagi`) VALUES
(1, 'Szkola Podstawowa', 'SZPPL', '34732238748345554', 'brak'),
(2, 'Gimnazjum', 'GPPL', '65456738748234231', 'brak'),
(3, 'Liceum Ogolnoksztalcace', 'LOPPL', '82256738225912453', 'brak');

--
-- Indeksy dla zrzutów tabel
--

--
-- Indexes for table `uzytkownik`
--
ALTER TABLE `uzytkownik`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id_UNIQUE` (`id`);

--
-- Indexes for table `wydzial`
--
ALTER TABLE `wydzial`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id_UNIQUE` (`id`),
  ADD UNIQUE KEY `symbol_UNIQUE` (`symbol`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT dla tabeli `uzytkownik`
--
ALTER TABLE `uzytkownik`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT dla tabeli `wydzial`
--
ALTER TABLE `wydzial`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
