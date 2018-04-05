databaseChangeLog = {
    changeSet(author: "rbe", id: "CreateDBTag_MM900") {
        sql("UPDATE DATABASECHANGELOG SET TAG = 'mm900_create_tables' WHERE FILENAME LIKE 'MM/%900';")
    }
}