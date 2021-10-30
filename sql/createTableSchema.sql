DROP TABLE `algoApp`.`activityLog`;
DROP TABLE `algoApp`.`user`;
DROP TABLE `algoApp`.`benchmark`;
DROP TABLE `algoApp`.`machineConfiguration`;
DROP TABLE `algoApp`.`problemInstance`;
DROP TABLE `algoApp`.`implementation`;
DROP TABLE `algoApp`.`algorithm`;
DROP TABLE `algoApp`.`classification`;

CREATE TABLE `algoApp`.`classification` (
  `className` VARCHAR(40) NOT NULL,
  `parentClassName` VARCHAR(40),
  PRIMARY KEY (`className`)
);
CREATE TABLE `algoApp`.`algorithm` (
	`algoName` VARCHAR(40),
    `className` VARCHAR(40),
    PRIMARY KEY (`algoName`),
    FOREIGN KEY (`className`) REFERENCES classification(`className`)
);
CREATE TABLE `algoApp`.`implementation` (
    `implName` VARCHAR(40),
    `codeURL` VARCHAR(120),
    `language` VARCHAR(30),
    `algoName` VARCHAR(40),
    PRIMARY KEY (`implName`, `algoName`),
    FOREIGN KEY (`algoName`) REFERENCES algorithm(`algoName`)
);
CREATE TABLE `algoApp`.`machineConfiguration` (
	`machineConfigUUID` CHAR(36),
    `L1Cache` INT,
    `L2Cache` INT,
    `chip` VARCHAR(80),
    `threads` INT,
    PRIMARY KEY (`machineConfigUUID`)
);
CREATE TABLE `algoApp`.`problemInstance` (
	`probInstanceUUID` CHAR(36),
    `probInstanceName` VARCHAR(40),
    `dataset` VARCHAR(500),
    `algoName` VARCHAR(40),
    PRIMARY KEY (`probInstanceUUID`),
    FOREIGN KEY (`algoName`) REFERENCES algorithm(`algoName`)
    /*FOREIGN KEY (`benchmarkID`) REFERENCES benchmark(`benchmarkID`)*/
);
CREATE TABLE `algoApp`.`benchmark` (
	`benchmarkUUID` CHAR(36),
    `benchmarkName` VARCHAR(40),
    `timeToRun` LONG,
    `dateRun` DATE,
    `algoName` VARCHAR(40),
    `implName` VARCHAR(40),
    `machineConfigUUID` CHAR(36),
    `probInstanceUUID` CHAR(36),
    PRIMARY KEY (`benchmarkUUID`),
    FOREIGN KEY (`algoName`) REFERENCES algorithm(`algoName`),
    FOREIGN KEY (`implName`) REFERENCES implementation(`implName`),
    FOREIGN KEY (`machineConfigUUID`) REFERENCES machineConfiguration(`machineConfigUUID`),
    FOREIGN KEY (`probInstanceUUID`) REFERENCES problemInstance(`probInstanceUUID`)
);
CREATE TABLE `algoApp`.`user` (
	`username` VARCHAR(20) NOT NULL,
    `isAdmin` INT NOT NULL,
    PRIMARY KEY (`username`)
);
CREATE TABLE `algoApp`.`activityLog` (
	`activityLogUUID` CHAR(36),
    `username` VARCHAR(20),
    `action` VARCHAR(100),
    `date` DATE,
    PRIMARY KEY (`activityLogUUID`),
    FOREIGN KEY (`username`) REFERENCES user(`username`)
);