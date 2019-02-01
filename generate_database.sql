BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS `users` (
	`id`	integer NOT NULL,
	`name`	varchar ( 25 ) NOT NULL,
	`role`	integer NOT NULL,
	PRIMARY KEY(`id`),
	FOREIGN KEY(`role`) REFERENCES `roles`(`id`)
);
INSERT INTO `users` VALUES (1,'Amila Sikalo',1);
CREATE TABLE IF NOT EXISTS `roles` (
	`id`	integer NOT NULL,
	`name`	varchar ( 25 ) NOT NULL UNIQUE,
	PRIMARY KEY(`id`)
);
INSERT INTO `roles` VALUES (1,'Administrator');
CREATE TABLE IF NOT EXISTS `planes` (
	`id`	integer NOT NULL,
	`airline_company`	integer NOT NULL,
	`supplier`	varchar ( 0 ) NOT NULL,
	`type`	varchar ( 0 ) NOT NULL,
	`seats`	integer NOT NULL,
	PRIMARY KEY(`id`),
	FOREIGN KEY(`airline_company`) REFERENCES `airline_companies`(`id`)
);
INSERT INTO `planes` VALUES (1,1,'Airbus','A320',130);
CREATE TABLE IF NOT EXISTS `passengers` (
	`id`	integer NOT NULL,
	`name`	varchar ( 25 ) NOT NULL,
	`flight`	integer NOT NULL,
	`qr_code`	bytea,
	FOREIGN KEY(`flight`) REFERENCES `flights`(`id`),
	PRIMARY KEY(`id`)
);
INSERT INTO `passengers` VALUES (1,'Amila Sikalo',1,NULL);
CREATE TABLE IF NOT EXISTS `luggages` (
	`id`	integer NOT NULL,
	`passenger`	integer NOT NULL,
	PRIMARY KEY(`id`),
	FOREIGN KEY(`passenger`) REFERENCES `passengers`(`id`)
);
INSERT INTO `luggages` VALUES (1,1);
CREATE TABLE IF NOT EXISTS `flights` (
	`id`	integer NOT NULL,
	`flight_number`	varchar ( 7 ) NOT NULL,
	`plane`	integer NOT NULL,
	`runway_occupy_start`	text,
	`runway_occupy_end`	text,
	`flight_type`	integer NOT NULL,
	`reg_user`	integer NOT NULL,
	FOREIGN KEY(`plane`) REFERENCES `planes`(`id`),
	FOREIGN KEY(`reg_user`) REFERENCES `users`(`id`),
	PRIMARY KEY(`id`),
	FOREIGN KEY(`flight_type`) REFERENCES `flight_types`(`id`)
);
INSERT INTO `flights` VALUES (1,'LH 876',1,'1546340400','1546340500',1,1);
CREATE TABLE IF NOT EXISTS `flight_types` (
	`id`	integer NOT NULL,
	`name`	varchar ( 25 ) NOT NULL UNIQUE,
	PRIMARY KEY(`id`)
);
INSERT INTO `flight_types` VALUES (1,'Charter');
CREATE TABLE IF NOT EXISTS `airline_companies` (
	`id`	integer NOT NULL,
	`name`	varchar ( 25 ) NOT NULL,
	`code`	varchar ( 3 ) NOT NULL UNIQUE,
	PRIMARY KEY(`id`)
);
INSERT INTO `airline_companies` VALUES (1,'Lufthansa','LH');
COMMIT;
