package com.ProyectCAE.tools;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class DbIntrospect {

    public static void main(String[] args) throws Exception {
        Properties properties = new Properties();
        Path devPropertiesPath = Path.of("src/main/resources/application-dev.properties");
        if (Files.exists(devPropertiesPath)) {
            try (InputStream input = Files.newInputStream(devPropertiesPath)) {
                properties.load(input);
            }
        }

        String url = resolve(properties, "spring.datasource.url", "DB_URL");
        String user = resolve(properties, "spring.datasource.username", "DB_USER");
        String pass = resolve(properties, "spring.datasource.password", "DB_PASS");

        List<String> tables = args.length > 0
                ? Arrays.asList(args)
                : List.of("users", "folder", "document", "document_type", "documents", "folders", "document_types");

        System.out.println("JDBC URL: " + url);
        System.out.println("User: " + user);
        System.out.println("Tables inspected: " + tables);
        System.out.println();

        try (Connection connection = DriverManager.getConnection(url, user, pass)) {
            for (String table : tables) {
                printColumns(connection, table);
            }
        }
    }

    private static void printColumns(Connection connection, String tableName) throws Exception {
        String sql = """
                select column_name, data_type, udt_name, is_nullable
                from information_schema.columns
                where table_schema = 'public' and table_name = ?
                order by ordinal_position
                """;

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, tableName);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                boolean found = false;
                while (resultSet.next()) {
                    if (!found) {
                        found = true;
                        System.out.println("== public." + tableName + " ==");
                    }
                    System.out.printf(
                            "  - %-25s %-12s (udt=%-12s) nullable=%s%n",
                            resultSet.getString("column_name"),
                            resultSet.getString("data_type"),
                            resultSet.getString("udt_name"),
                            resultSet.getString("is_nullable")
                    );
                }
                if (found) {
                    System.out.println();
                }
            }
        }
    }

    private static String resolve(Properties properties, String propertyKey, String envKey) {
        String fromEnv = System.getenv(envKey);
        if (fromEnv != null && !fromEnv.isBlank()) return fromEnv;

        String value = properties.getProperty(propertyKey);
        if (value == null) return null;

        // handle ${ENV:default} pattern used by Spring
        if (value.startsWith("${") && value.endsWith("}")) {
            String inner = value.substring(2, value.length() - 1);
            int colon = inner.indexOf(':');
            if (colon > 0) {
                String envName = inner.substring(0, colon);
                String defaultValue = inner.substring(colon + 1);
                String envValue = System.getenv(envName);
                return (envValue == null || envValue.isBlank()) ? defaultValue : envValue;
            }
        }

        return value;
    }
}

