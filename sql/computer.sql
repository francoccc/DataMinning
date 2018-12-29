create Table `Computer`(
  `id` int(9) NOT NULL auto_increment,
  `age` int(3) NOT NULL,
  `income` int(3) NOT NULL,
  `student` boolean NOT NULL,
  `credit` int(3) NOT NULL,
  `buys` boolean NOT NULL,
  primary key(`id`)
)engine = InnoDB auto_increment = 1 default char set = utf8

INSERT INTO `datamining`.`Computer` (`id`, `age`, `income`, `student`, `credit`, `buys`) VALUES ('1', '3', '3', 0, '2', 0);
INSERT INTO `datamining`.`Computer` (`id`, `age`, `income`, `student`, `credit`, `buys`) VALUES ('2', '3', '3', 0, '1', 0);
INSERT INTO `datamining`.`Computer` (`id`, `age`, `income`, `student`, `credit`, `buys`) VALUES ('3', '2', '3', 0, '2', 1);
INSERT INTO `datamining`.`Computer` (`id`, `age`, `income`, `student`, `credit`, `buys`) VALUES ('4', '1', '2', 0, '2', 1);
INSERT INTO `datamining`.`Computer` (`id`, `age`, `income`, `student`, `credit`, `buys`) VALUES ('5', '1', '1', 1, '2', 1);
INSERT INTO `datamining`.`Computer` (`id`, `age`, `income`, `student`, `credit`, `buys`) VALUES ('6', '1', '1', 1, '1', 0);
INSERT INTO `datamining`.`Computer` (`id`, `age`, `income`, `student`, `credit`, `buys`) VALUES ('7', '2', '1', 1, '1', 1);
INSERT INTO `datamining`.`Computer` (`id`, `age`, `income`, `student`, `credit`, `buys`) VALUES ('8', '3', '2', 0, '2', 0);
INSERT INTO `datamining`.`Computer` (`id`, `age`, `income`, `student`, `credit`, `buys`) VALUES ('9', '3', '1', 1, '2', 1);
INSERT INTO `datamining`.`Computer` (`id`, `age`, `income`, `student`, `credit`, `buys`) VALUES ('10', '1', '2', 1, '2', 1);
INSERT INTO `datamining`.`Computer` (`id`, `age`, `income`, `student`, `credit`, `buys`) VALUES ('11', '3', '2', 1, '1', 1);
INSERT INTO `datamining`.`Computer` (`id`, `age`, `income`, `student`, `credit`, `buys`) VALUES ('12', '2', '2', 0, '1', 1);
INSERT INTO `datamining`.`Computer` (`id`, `age`, `income`, `student`, `credit`, `buys`) VALUES ('13', '2', '3', 1, '2', 1);
INSERT INTO `datamining`.`Computer` (`id`, `age`, `income`, `student`, `credit`, `buys`) VALUES ('14', '1', '2', 0, '1', 0);
