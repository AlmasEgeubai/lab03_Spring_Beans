CREATE TABLE IF NOT EXISTS `notebook` (
    `id` INT(2) NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(11) NOT NULL,
    `num` VARCHAR(4) NOT NULL,
    PRIMARY KEY (`id`)
)  ENGINE=INNODB DEFAULT CHARSET=UTF8;

INSERT INTO notebook (`name`, num) VALUES ("Жанибек", "87055667439");
INSERT INTO notebook (`name`, num) VALUES ("Адиль", "87774507735");
INSERT INTO notebook (`name`, num) VALUES ("Меирбек", "87477573365");

SET GLOBAL time_zone = '+6:00';