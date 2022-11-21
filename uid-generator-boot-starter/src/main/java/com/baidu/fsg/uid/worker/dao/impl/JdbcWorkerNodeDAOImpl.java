package com.baidu.fsg.uid.worker.dao.impl;

import com.baidu.fsg.uid.worker.dao.WorkerNodeDAO;
import com.baidu.fsg.uid.worker.entity.WorkerNodeEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Objects;

/**
 * @desc: JdbcWorkerNodeDAOImpl
 * @author: Yuan
 * @create: 2022/10/27
 **/
public class JdbcWorkerNodeDAOImpl implements WorkerNodeDAO {

    private final JdbcTemplate jdbcTemplate;
    private static final String ADD_WORKER_NODE_SQL =
            "INSERT INTO worker_node (host_name, port, type) VALUES (?, ?, ?)";

    public JdbcWorkerNodeDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addWorkerNode(final WorkerNodeEntity workerNodeEntity) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        PreparedStatementCreator preparedStatementCreator = con -> {
            PreparedStatement ps = con.prepareStatement(ADD_WORKER_NODE_SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, workerNodeEntity.getHostName());
            ps.setString(2, workerNodeEntity.getPort());
            ps.setInt(3, workerNodeEntity.getType());
            return ps;
        };
        jdbcTemplate.update(preparedStatementCreator, keyHolder);
        //获取最终插入的自增的id
        int id = Objects.requireNonNull(keyHolder.getKey()).intValue();
        workerNodeEntity.setId(id);
    }
}
