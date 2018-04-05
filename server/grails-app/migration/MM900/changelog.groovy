Package MM900
databaseChangeLog = {

    def MYSQL_MODIFICATION = ' engine innodb charset utf8mb4 collate utf8mb4_bin'

    def failIfTableExists = { t ->
        not {
            tableExists(tableName: "$t")
        }
    }

    def failIfIndexExists = { t, i ->
        not {
            indexExists(indexName: "$i", tableName: "$t")
        }
    }

    def failIfPrimaryKeyExists = { k ->
        not {
            primaryKeyExists(primaryKeyName: "$k")
        }
    }

    def failIfForeignKeyExists = { k ->
        not {
            foreignKeyConstraintExists(foreignKeyName: "$k")
        }
    }

    def failIfTableColumnExists = { t, c ->
        not {
            columnExists(columnName: "$c", tableName: "$t")
        }
    }

    changeSet(author: "rbe", id: "CreateProjectTable") {
        preConditions(onFail: "MARK_RAN", onFailMessage: "Table 'project' exists. Marking migration as run") {
            failIfTableExists("project")
        }
        createTable(tableName: "project") {
            column(autoIncrement: "true", name: "id", type: "BIGINT(20)") {
                constraints(primaryKey: "true", primaryKeyName: "PK_PROJECT")
            }

            column(name: "version", type: "BIGINT(20)") {
                constraints(nullable: false)
            }

            column(name: "name", type: "VARCHAR(20)") {
                constraints(nullable: true)
            }


            column(name: "state", type: "BIT(1)") {
                constraints(nullable: false)
            }
            column(name: "description", type: "TEXT") {
                constraints(nullable: true)
            }

            column(name: 'date_created', type: 'TIMESTAMP', defaultValueComputed: "CURRENT_TIMESTAMP()") {
                constraints(nullable: false)
            }
            column(name: 'last_updated', type: 'TIMESTAMP', defaultValueComputed: "CURRENT_TIMESTAMP()") {
                constraints(nullable: false)
            }
        }
        modifySql(dbms: 'mysql') {
            append(value: MYSQL_MODIFICATION)
        }
    }

    changeSet(author: "rbe", id: "CreateProjectIndex") {
        createIndex(tableName: 'project', indexName: 'Project_Index') {
            column(name: 'name')
        }
    }

    include file: 'MM900/versionTag.changelog.groovy'

}
