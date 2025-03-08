package com.hawolt.h2;

import com.hawolt.config.JsonSource;
import com.hawolt.logger.Logger;
import com.hawolt.runtime.Core;
import com.hawolt.runtime.RunLevel;
import com.hawolt.sql.Hikari;
import org.h2.tools.Server;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;

public class LocalDatabase {
    private static final Path database = Paths.get(System.getProperty("java.io.tmpdir")).resolve(".h2");

    static {
        try (InputStream configStream = RunLevel.get("sql/config.json")) {
            Server h2Server = Server.createTcpServer("-ifNotExists").start();
            if (h2Server.isRunning(true)) {
                Logger.info(h2Server.getStatus());
            } else {
                Logger.fatal("Could not start H2 server");
            }
            Hikari.setup(
                    String.format("jdbc:h2:tcp://localhost:9092/%s;mode=MySQL", database),
                    JsonSource.of(configStream)
            );
            try (InputStream statementStream = RunLevel.get("build.sql")) {
                try (Connection connection = Hikari.getManager().getConnection()) {
                    try (Statement statement = connection.createStatement()) {
                        statement.execute(Core.read(statementStream).toString());
                    }
                }
            } catch (SQLException e) {
                Logger.warn("Failed to execute database build query");
                Logger.error(e);
            }
        } catch (SQLException | IOException e) {
            Logger.fatal("Unable to initialize embedded in-memory database");
            Logger.error(e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return Hikari.getManager().getConnection();
    }
}
