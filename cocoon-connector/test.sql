CREATE DATABASE `cocoon` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

CREATE TABLE cocoon.cc_test (
    id BIGINT auto_increment NOT NULL COMMENT '主键id',
    name varchar(32) DEFAULT '' NOT NULL,
    age INT DEFAULT 0 NOT NULL COMMENT '年龄',
    sex TINYINT NOT NULL COMMENT '1 男 ，0 女',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP NOT NULL COMMENT '最近更新时间',
    CONSTRAINT cc_test_pk PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO cocoon.cc_test (name, age, sex, create_time, update_time) VALUES('张三', 17, 1, '2022-10-08 10:44:24', '2022-10-08 10:44:24');
INSERT INTO cocoon.cc_test (name, age, sex, create_time, update_time) VALUES('李四', 19, 1, '2022-10-08 10:44:24', '2022-10-08 10:44:24');
INSERT INTO cocoon.cc_test (name, age, sex, create_time, update_time) VALUES('韩梅梅', 16, 0, '2022-10-08 10:44:24', '2022-10-08 10:44:24');
INSERT INTO cocoon.cc_test (name, age, sex, create_time, update_time) VALUES('李雷', 20, 1, '2022-10-08 10:44:24', '2022-10-08 10:44:24');


