DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `open_id` VARCHAR(64) NOT NULL,
  `username` VARCHAR(64) NOT NULL,
  `password` VARCHAR(64) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE (`open_id`),
  UNIQUE (`username`)
);

DROP TABLE IF EXISTS `token`;

CREATE TABLE `token` (
  `token` VARCHAR(64) NOT NULL,
  `user_id` BIGINT NOT NULL,
  PRIMARY KEY (`token`)
);

INSERT INTO `user` (`id`, `open_id`, `username`, `password`) VALUES (1, 'OA', 'UA', '86A7F47B333770C6E85F03EE3CAA6EF9DFEBFE2CB3444B738CC2DF5D177CE38A');
INSERT INTO `user` (`id`, `open_id`, `username`, `password`) VALUES (2, 'OB', 'UB', 'F9085BF3A4E33576E4E0C0D46BCBFF81EB47265D015846A96EDEA91B2BD4445B');
INSERT INTO `user` (`id`, `open_id`, `username`, `password`) VALUES (3, 'OC', 'UC', '21D017C40A91C15748F0B98CD826BA445D2D3FE227E310BFD58DCB6C431826A0');

INSERT INTO `token` (`token`, `user_id`) VALUES ('TA', 1);
INSERT INTO `token` (`token`, `user_id`) VALUES ('TB', 2);
INSERT INTO `token` (`token`, `user_id`) VALUES ('TC', 2);


DROP TABLE IF EXISTS `good`;

CREATE TABLE `good` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `price` INT NOT NULL,
  `ship_cost` INT NOT NULL,
  `title` VARCHAR(2048) NOT NULL,
  `detail` VARCHAR(2048) NOT NULL,
  `pic` VARCHAR(2048) NOT NULL,
  PRIMARY KEY (`id`)
);

INSERT INTO `good` (`id`, `price`, `ship_cost`, `title`, `detail`, `pic`) VALUES (1, 123456, 999, '火之高兴', '霜之哀伤超级克星', '/static/image/33363aa8aef00d6c403cf2feebb70670.jpg');
INSERT INTO `good` (`id`, `price`, `ship_cost`, `title`, `detail`, `pic`) VALUES (2, 654321, 999, '圣剑', '一次购买，终身（被）追杀', '/static/image/b6aab8412948314ff35fddd173d54d65.jpg');
INSERT INTO `good` (`id`, `price`, `ship_cost`, `title`, `detail`, `pic`) VALUES (3, 200000000, 1000000, '波音747', '王八蛋老板詹姆斯迈克纳尼，吃喝嫖赌欠下了350个亿，带着他的小姨子跑了！', '/static/image/061cf76145af5b8f1b7de3d722246f79.jpg');


DROP TABLE IF EXISTS `order`;

CREATE TABLE `order` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `good_id` BIGINT NOT NULL,
  `pay_id` VARCHAR(64) NULL DEFAULT NULL,
  `user_id` BIGINT NOT NULL,
  `created_time` BIGINT NOT NULL,
  `contact` VARCHAR(2048) NOT NULL,
  `address` VARCHAR(2048) NOT NULL,
  `phone` VARCHAR(2048) NOT NULL,
  PRIMARY KEY (`id`)
);

INSERT INTO `order` (`id`, `good_id`, `pay_id`, `user_id`, `created_time`, `contact`, `address`, `phone`) VALUES (1, 1, 'TEST_PAYMENT_ID_AAA', 1, 1501560000000, '伊利丹', '拼多多总部', '13012345678');
INSERT INTO `order` (`id`, `good_id`, `pay_id`, `user_id`, `created_time`, `contact`, `address`, `phone`) VALUES (2, 2, NULL, 1, 1501567200000, '伊利丹', '拼多多总部', '13012345678');
INSERT INTO `order` (`id`, `good_id`, `pay_id`, `user_id`, `created_time`, `contact`, `address`, `phone`) VALUES (3, 3, NULL, 1, 1501574400000, '伊利丹', '拼多多总部', '13012345678');
