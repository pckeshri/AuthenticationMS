CREATE TABLE `currency` (
  `currencycode` varchar(11) NOT NULL,
  `currencyrate` decimal(5,3) DEFAULT 0.0,
  `countryname` varchar(45) NOT NULL,
  PRIMARY KEY (`currencycode`)
) ENGINE=InnoDB
