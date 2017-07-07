CREATE SCHEMA myDatabase;

USE myDatabase;

CREATE TABLE `subject_info` (
   `ID` int(11) NOT NULL AUTO_INCREMENT,
   `LecturerName` varchar(200) DEFAULT NULL,
   `SyllabusPath` varchar(500) DEFAULT NULL,
   `Ects` int(11) DEFAULT NULL,
   `Language` varchar(200) DEFAULT NULL,
   PRIMARY KEY (`ID`),
   UNIQUE KEY `ID_UNIQUE` (`ID`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
 
CREATE TABLE `subject` (
   `ID` int(11) NOT NULL AUTO_INCREMENT,
   `Name` varchar(100) NOT NULL,
   `TermID` int(11) NOT NULL,
   `Year` int(11) NOT NULL,
   `SubjectInfoID` int(11) NOT NULL,
   PRIMARY KEY (`ID`),
   UNIQUE KEY `ID_UNIQUE` (`ID`),
   KEY `FK_Subject_To_Info_idx` (`SubjectInfoID`),
   KEY `FK_Subject_To_Term_idx` (`TermID`),
   CONSTRAINT `FK_Subject_To_Info` FOREIGN KEY (`SubjectInfoID`) REFERENCES `subject_info` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
   CONSTRAINT `FK_Subject_To_Term` FOREIGN KEY (`TermID`) REFERENCES `subject_terms` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
 
CREATE TABLE `subject_component_templates` (
   `ID` int(11) NOT NULL AUTO_INCREMENT,
   `Name` varchar(45) NOT NULL,
   `MarkPercentage` decimal(11,1) NOT NULL,
   `Number` int(11) NOT NULL,
   PRIMARY KEY (`ID`),
   UNIQUE KEY `ID_UNIQUE` (`ID`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
 
CREATE TABLE `common_subject_components` (
   `ID` int(11) NOT NULL AUTO_INCREMENT,
   `SubjectComponentTemplateID` int(11) NOT NULL,
   `SubjectID` int(11) NOT NULL,
   PRIMARY KEY (`ID`),
   UNIQUE KEY `ID_UNIQUE` (`ID`),
   KEY `FK_Component_To_Template_idx` (`SubjectComponentTemplateID`),
   KEY `FK_Component_To_SubjectTemplate_idx` (`SubjectID`),
   CONSTRAINT `FK_Component_To_Subject` FOREIGN KEY (`SubjectID`) REFERENCES `subject` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
   CONSTRAINT `FK_Component_To_Template` FOREIGN KEY (`SubjectComponentTemplateID`) REFERENCES `subject_component_templates` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
 ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

 
 
 CREATE TABLE `subject_component_materials` (
   `ID` int(11) NOT NULL AUTO_INCREMENT,
   `MaterialPath` varchar(45) NOT NULL,
   `UploadDate` datetime NOT NULL,
   `SubjectComponentID` int(11) NOT NULL,
   PRIMARY KEY (`ID`),
   UNIQUE KEY `ID_UNIQUE` (`ID`),
   KEY `FK_Material_to_Component_idx` (`SubjectComponentID`),
   CONSTRAINT `FK_Material_to_Component` FOREIGN KEY (`SubjectComponentID`) REFERENCES `common_subject_components` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
 

 
 CREATE TABLE `user_profiles` (
   `ID` int(11) NOT NULL AUTO_INCREMENT,
   `Name` varchar(100) NOT NULL,
   `Surname` varchar(100) NOT NULL,
   `Gender` varchar(100) NOT NULL,
   `CreateDate` datetime NOT NULL,
   PRIMARY KEY (`ID`),
   UNIQUE KEY `ID_UNIQUE` (`ID`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
 
 CREATE TABLE `users` (
   `ID` int(11) NOT NULL AUTO_INCREMENT,
   `Username` varchar(100) DEFAULT NULL,
   `Password` varchar(500) DEFAULT NULL,
   `Email` varchar(100) DEFAULT NULL,
   `Role` varchar(45) NOT NULL,
   `GmailID` varchar(500) DEFAULT NULL,
   `FacebookID` varchar(500) DEFAULT NULL,
   `ProfileID` int(11) NOT NULL,
   PRIMARY KEY (`ID`),
   UNIQUE KEY `ID_UNIQUE` (`ID`),
   KEY `FK_User_To_Profile_idx` (`ProfileID`),
   CONSTRAINT `FK_User_To_Profile` FOREIGN KEY (`ProfileID`) REFERENCES `user_profiles` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
 
 CREATE TABLE `user_subject_components` (
   `ID` int(11) NOT NULL AUTO_INCREMENT,
   `UserID` int(11) NOT NULL,
   `CommonSubjectComponentID` int(11) NOT NULL,
   `DeadLineDate` datetime DEFAULT NULL,
   PRIMARY KEY (`ID`),
   UNIQUE KEY `ID_UNIQUE` (`ID`),
   KEY `FK_Components_To_User_idx` (`UserID`),
   KEY `FK_Components_To_Common_Components_idx` (`CommonSubjectComponentID`),
   CONSTRAINT `FK_Components_To_Common_Components` FOREIGN KEY (`CommonSubjectComponentID`) REFERENCES `common_subject_components` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
   CONSTRAINT `FK_Components_To_User` FOREIGN KEY (`UserID`) REFERENCES `users` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
 
 CREATE TABLE `user_subjects` (
   `ID` int(11) NOT NULL AUTO_INCREMENT,
   `UserID` int(11) NOT NULL,
   `SubjectID` int(11) NOT NULL,
   PRIMARY KEY (`ID`),
   UNIQUE KEY `ID_UNIQUE` (`ID`),
   KEY `FK_UserSubjects_To_User_idx` (`UserID`),
   KEY `FK_UserSubjects_To_Subject_idx` (`SubjectID`),
   CONSTRAINT `FK_UserSubjects_To_Subject` FOREIGN KEY (`SubjectID`) REFERENCES `subject` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
   CONSTRAINT `FK_UserSubjects_To_User` FOREIGN KEY (`UserID`) REFERENCES `users` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
 
 CREATE TABLE `subject_terms` (
   `ID` int(11) NOT NULL AUTO_INCREMENT,
   `Name` varchar(200) NOT NULL,
   PRIMARY KEY (`ID`),
   UNIQUE KEY `ID_UNIQUE` (`ID`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

 