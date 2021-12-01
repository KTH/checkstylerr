package com.jriddler.attrs;


import lombok.SneakyThrows;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * List of table attributes.
 */
public final class Attributes implements Iterable<AttributeDefinition> {

    /**
     * Actual list of attributes.
     */
    private final List<AttributeDefinition> attributes;

    /**
     * Ctor.
     *
     * @param tableName  Table with attributes
     * @param dataSource DataSource
     */
    @SneakyThrows(SQLException.class)
    @SuppressWarnings("LineLength")
    public Attributes(
            final String tableName,
            final DataSource dataSource
    ) {
        this.attributes = Attributes.fetchAttributesFromDb(
                tableName,
                dataSource,
                Collections.emptyMap()
        );
    }


    /**
     * Ctor.
     *
     * @param tableName      Table with attributes
     * @param dataSource     Datasource
     * @param userAttributes User defined attributes
     */
    @SneakyThrows(SQLException.class)
    @SuppressWarnings("LineLength")
    public Attributes(
            final String tableName,
            final DataSource dataSource,
            final Map<String, String> userAttributes
    ) {
        this.attributes = Attributes.fetchAttributesFromDb(
                tableName,
                dataSource,
                userAttributes
        );
    }

    @Override
    public Iterator<AttributeDefinition> iterator() {
        return this.attributes.iterator();
    }

    /**
     * Collect all table attributes into single list.
     *
     * @param tableName  Table name
     * @param dataSource Datasource
     * @param userAttrs User defined attributes
     * @return List of attributes
     * @throws SQLException if failed
     */
    @SuppressWarnings("LineLength")
    private static List<AttributeDefinition> fetchAttributesFromDb(
            final String tableName,
            final DataSource dataSource,
            final Map<String, String> userAttrs
    ) throws SQLException {
        final List<AttributeDefinition> attrs = new ArrayList<>(16);
        try (final Connection connection = dataSource.getConnection()) {
            try (final ResultSet columns = Attributes.columnsMetaData(connection, tableName)) {
                while (columns.next()) {
                    //Skip auto increment attributes
                    if (!"YES".equals(columns.getString("IS_AUTOINCREMENT"))) {
                        attrs.add(new AttrBuilder(columns, userAttrs));
                    }
                }

            }
        }
        return attrs;
    }

    /**
     * Create columns metadata.
     *
     * @param connection Connection
     * @param tableName  Table name
     * @return Columns metadata
     * @throws SQLException if failed
     */
    private static ResultSet columnsMetaData(
            final Connection connection,
            final String tableName
    ) throws SQLException {
        return connection.getMetaData().getColumns(
                connection.getCatalog(),
                connection.getSchema(),
                tableName,
                null
        );
    }
}
