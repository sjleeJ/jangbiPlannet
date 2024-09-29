CREATE TABLE IF NOT EXISTS `jangbiplannet`.`member`
(
    `id`
    BIGINT
    NOT
    NULL
    AUTO_INCREMENT,
    `email`
    VARCHAR
(
    100
) NOT NULL,
    `password` VARCHAR
(
    100
) NOT NULL,
    `nickname` VARCHAR
(
    100
) NULL,
    `phone` VARCHAR
(
    100
) NULL,
    `created_at` DATETIME NOT NULL,
    `updated_at` DATETIME NULL,
    `deleted_at` DATETIME NULL,
    PRIMARY KEY
(
    `id`
) VISIBLE)
    ENGINE = InnoDB


CREATE TABLE IF NOT EXISTS `jangbiplannet`.`role`
(
    `id`
    TINYINT
    NOT
    NULL
    AUTO_INCREMENT,
    `name`
    VARCHAR
(
    20
) NOT NULL,
    PRIMARY KEY
(
    `id`
) VISIBLE)
    ENGINE = InnoDB

CREATE TABLE IF NOT EXISTS `jangbiplannet`.`role_member`
(
    `id`
    BIGINT
    NOT
    NULL
    AUTO_INCREMENT,
    `role_id`
    TINYINT
    NOT
    NULL,
    `member_id`
    BIGINT
    NOT
    NULL,
    PRIMARY
    KEY
(
    `id`
) VISIBLE)
    ENGINE = InnoDB