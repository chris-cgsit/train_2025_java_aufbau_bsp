/*
 *  Copyright © 2025 CGS IT Solutions GmbH.
 *  All rights reserved.
 *
 *  This source code is proprietary intellectual property
 *  of CGS IT Solutions GmbH and is provided solely for use
 *  within licensed training programs.
 *
 *   Any copying, redistribution, modification, or use
 *   beyond the permitted scope is strictly prohibited.
 */

import java.sql.*;

public class TestInsert {

    static void main() throws SQLException {

        Connection conn = MakeDbConnection.makeMyConnection();

        TestEntity entity = new TestEntity();
        entity.setAktiv(Boolean.TRUE);
        entity.setName("testone");

        TestInsert.insertTest(conn,entity);
    }

    public static void insertTest(Connection conn, TestEntity entity) throws SQLException {

        // 1 . input validation .. ist der input korrekt. z.b. name ist nicht null
        // throw new MyBusinessException("name nicht vorhanden")

        String sql = "INSERT INTO test_entity (name, aktiv) VALUES (?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, entity.getName());
            ps.setBoolean(2, entity.getAktiv());

            int result = ps.executeUpdate();

            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    entity.setId(keys.getLong(1));
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
