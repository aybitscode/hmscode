/**
*	Initialize hms db schema
*/
source hms_schema_ops.sql
commit;
source hms_ddl.sql
commit;
source hms_ddl_constraints.sql
commit;
source hms_dml.sql
commit;
