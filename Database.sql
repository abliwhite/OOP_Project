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
 
CREATE TABLE `subject_terms` (
   `ID` int(11) NOT NULL AUTO_INCREMENT,
   `Name` varchar(200) NOT NULL,
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
 
CREATE TABLE `subject_component_types` (
   `ID` int(11) NOT NULL AUTO_INCREMENT,
   `Name` varchar(100) NOT NULL,
   PRIMARY KEY (`ID`),
   UNIQUE KEY `ID_UNIQUE` (`ID`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
 
CREATE TABLE `common_subject_components` (
   `ID` int(11) NOT NULL AUTO_INCREMENT,
   `TypeID` int(11) NOT NULL,
   `MarkPercentage` decimal(11,1) NOT NULL,
   `Number` int(11) NOT NULL,
   `SubjectID` int(11) NOT NULL,
   PRIMARY KEY (`ID`),
   UNIQUE KEY `ID_UNIQUE` (`ID`),
   KEY `FK_Component_To_SubjectTemplate_idx` (`SubjectID`),
   KEY `FK_Component_To_Component_Types_idx` (`TypeID`),
   CONSTRAINT `FK_Component_To_Component_Type` FOREIGN KEY (`TypeID`) REFERENCES `subject_component_types` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
   CONSTRAINT `FK_Component_To_Subject` FOREIGN KEY (`SubjectID`) REFERENCES `subject` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

 
 
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
 
 CREATE TABLE `active_status` (
   `ID` int(11) NOT NULL AUTO_INCREMENT,
   `Name` varchar(200) NOT NULL,
   PRIMARY KEY (`ID`),
   UNIQUE KEY `ID_UNIQUE` (`ID`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
 
 CREATE TABLE `privacy_status` (
   `ID` int(11) NOT NULL AUTO_INCREMENT,
   `Name` varchar(200) NOT NULL,
   PRIMARY KEY (`ID`),
   UNIQUE KEY `ID_UNIQUE` (`ID`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
 
CREATE TABLE `lobby` (
   `ID` int(11) NOT NULL AUTO_INCREMENT,
   `SubjectComponentID` int(11) NOT NULL,
   PRIMARY KEY (`ID`),
   UNIQUE KEY `ID_UNIQUE` (`ID`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
 
 CREATE TABLE `group_chat` (
   `ID` int(11) NOT NULL AUTO_INCREMENT,
   `LobbyID` int(11) NOT NULL,
   `CreatorID` int(11) NOT NULL,
   `CreateDate` datetime NOT NULL,
   `Name` varchar(200) NOT NULL,
   `PrivacyStatusID` int(11) NOT NULL,
   `ActiveStatusID` int(11) NOT NULL,
   PRIMARY KEY (`ID`),
   UNIQUE KEY `ID_UNIQUE` (`ID`),
   KEY `FK_Group_To_Lobby_idx` (`LobbyID`),
   KEY `FK_Group_To_User_idx` (`CreatorID`),
   KEY `FK_Group_To_PrivacyStatus_idx` (`PrivacyStatusID`),
   KEY `FK_Group_To_ActiveStatus_idx` (`ActiveStatusID`),
   CONSTRAINT `FK_Group_To_ActiveStatus` FOREIGN KEY (`ActiveStatusID`) REFERENCES `active_status` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
   CONSTRAINT `FK_Group_To_Lobby` FOREIGN KEY (`LobbyID`) REFERENCES `lobby` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
   CONSTRAINT `FK_Group_To_PrivacyStatus` FOREIGN KEY (`PrivacyStatusID`) REFERENCES `privacy_status` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
   CONSTRAINT `FK_Group_To_User` FOREIGN KEY (`CreatorID`) REFERENCES `users` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
 
 
 CREATE TABLE `internal_group_messages` (
   `ID` int(11) NOT NULL AUTO_INCREMENT,
   `Message` varchar(2000) NOT NULL,
   `DateSent` datetime NOT NULL,
   `SenderID` int(11) NOT NULL,
   `GroupID` int(11) NOT NULL,
   PRIMARY KEY (`ID`),
   UNIQUE KEY `ID_UNIQUE` (`ID`),
   KEY `FK_InternalMessage_To_User_idx` (`SenderID`),
   KEY `FK_InternalMessage_To_GroupChat_idx` (`GroupID`),
   CONSTRAINT `FK_InternalMessage_To_GroupChat` FOREIGN KEY (`GroupID`) REFERENCES `group_chat` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
   CONSTRAINT `FK_InternalMessage_To_User` FOREIGN KEY (`SenderID`) REFERENCES `users` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
 
 
 CREATE TABLE `external_group_messages` (
   `ID` int(11) NOT NULL AUTO_INCREMENT,
   `Message` varchar(2000) NOT NULL,
   `DateSent` datetime NOT NULL,
   `SenderID` int(11) NOT NULL,
   `SenderGroupID` int(11) NOT NULL,
   `ReceiverGroupID` int(11) NOT NULL,
   PRIMARY KEY (`ID`),
   UNIQUE KEY `ID_UNIQUE` (`ID`),
   KEY `FK_ExternalMessage_To_User_idx` (`SenderID`),
   KEY `FK_ExternalMessage_To_SenderGroup_idx` (`SenderGroupID`),
   KEY `FK_ExternalMessage_To_ReceiverGroup_idx` (`ReceiverGroupID`),
   CONSTRAINT `FK_ExternalMessage_To_ReceiverGroup` FOREIGN KEY (`ReceiverGroupID`) REFERENCES `group_chat` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
   CONSTRAINT `FK_ExternalMessage_To_SenderGroup` FOREIGN KEY (`SenderGroupID`) REFERENCES `group_chat` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
   CONSTRAINT `FK_ExternalMessage_To_User` FOREIGN KEY (`SenderID`) REFERENCES `users` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
 
 CREATE TABLE `user_group_chats` (
   `ID` int(11) NOT NULL AUTO_INCREMENT,
   `UserID` int(11) NOT NULL,
   `GroupChatID` int(11) NOT NULL,
   PRIMARY KEY (`ID`),
   UNIQUE KEY `ID_UNIQUE` (`ID`),
   KEY `FK_UserGroupChat_To_User_idx` (`UserID`),
   KEY `FK_UserGroupChat_To_GroupChat_idx` (`GroupChatID`),
   CONSTRAINT `FK_UserGroupChat_To_GroupChat` FOREIGN KEY (`GroupChatID`) REFERENCES `group_chat` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
   CONSTRAINT `FK_UserGroupChat_To_User` FOREIGN KEY (`UserID`) REFERENCES `users` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
 
INSERT INTO subject_terms (name)
VALUES("Fall");

INSERT INTO subject_terms (name)
VALUES("Spring");

INSERT INTO subject_component_types (name)
VALUES("Quiz");

INSERT INTO subject_component_types (name)
VALUES("Assignment");

INSERT INTO subject_component_types (name)
VALUES("Midterm Exam");

INSERT INTO subject_component_types (name)
VALUES("Final exam");

INSERT INTO subject_component_types (name)
VALUES("Presentation");

INSERT INTO active_status (name)
VALUES("Active");

INSERT INTO active_status (name)
VALUES("Passive");

INSERT INTO active_status (name)
VALUES("Archived");

INSERT INTO privacy_status (name)
VALUES("Public");

INSERT INTO privacy_status (name)
VALUES("Private");

INSERT INTO privacy_status (name)
VALUES("Secret");
 