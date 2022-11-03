package org.example;

import liquibase.Contexts;
import liquibase.LabelExpression;
import liquibase.Liquibase;
import liquibase.Scope;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.ClassLoaderResourceAccessor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class App {
    public static void main (String[] args) {
        System.out.println("args = " + Arrays.deepToString(args));

        String url = "jdbc:postgresql://" + "localhost" + ":5432" + "/" + "test1";
        String user = "postgres";
        String password = "admin";

        String changesetFileName = "changeset.sql";

        Map<String, Object> config = new HashMap<>();

        try {
            Scope.child(config, () -> {
                Connection connection = DriverManager.getConnection(url, user, password);

                Database database = DatabaseFactory.getInstance()
                                                   .findCorrectDatabaseImplementation(new JdbcConnection(
                                                       connection));

                Liquibase liquibase = new Liquibase(changesetFileName,
                                                    new ClassLoaderResourceAccessor(),
                                                    database
                );

                Contexts contexts = new Contexts();
                LabelExpression labelExpression = new LabelExpression();
                liquibase.update(contexts, labelExpression);
            });
        } catch (Exception e) {
            System.out.println("e.getMessage() = " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
