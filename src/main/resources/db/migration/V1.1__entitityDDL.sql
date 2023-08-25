CREATE TABLE `users` (
    id BIGINT AUTO_INCREMENT NOT NULL,
	`username` VARCHAR(50) NOT NULL COLLATE 'latin1_swedish_ci',
	`first_name` VARCHAR(50) NOT NULL COLLATE 'latin1_swedish_ci',
	`last_name` VARCHAR(50) NOT NULL COLLATE 'latin1_swedish_ci',
	`email` VARCHAR(50) NOT NULL COLLATE 'latin1_swedish_ci',
	`password` VARCHAR(500) NOT NULL COLLATE 'latin1_swedish_ci',
	`roles` VARCHAR(500) NULL,
	PRIMARY KEY (id)
)