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

public class TestReadById {

    static void main() throws SQLException {

        Connection conn = MakeDbConnection.makeMyConnection();

        TestReadById myInstance = new TestReadById();
        TestEntity entity = myInstance.loadTest(conn, 2);

        if (entity != null) {
            System.out.println("result: " + entity.toString());
        } else {
            System.out.println("entty not found");
        }

    }

    public TestEntity loadTest(Connection conn, long id) throws SQLException {

        String sql = "SELECT * FROM test_entity WHERE id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) {
                    return null;
                }

                TestEntity e = new TestEntity(
                        rs.getString("name"),
                        rs.getBoolean("aktiv")
                );

                e.setId(rs.getLong("id"));
                e.setCreatedAt(rs.getTimestamp("created_at").toInstant());

                return e;
            }
        }
    }


}
