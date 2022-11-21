CREATE TABLE worker_node
(
    id          BIGINT      NOT NULL AUTO_INCREMENT COMMENT 'auto increment id',
    host_name   VARCHAR(64) NOT NULL COMMENT 'host name',
    port        VARCHAR(64) NOT NULL COMMENT 'port',
    type        INT         NOT NULL COMMENT 'node type: ACTUAL or CONTAINER',
    launch_date TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'launch date',
    modified    TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'modified time',
    created     TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'created time',
    PRIMARY KEY (id)
) COMMENT='DB WorkerID Assigner for UID Generator',ENGINE = INNODB;