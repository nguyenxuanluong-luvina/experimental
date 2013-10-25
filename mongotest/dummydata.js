// var wddb = db.getSisterDB("wiperdog");
var wddb = db.getSisterDB("wiperdog_test");
var cols =  [
  "MySQL.Database_Area.InnoDBTablespace_Free.localhost-@MYSQL"
, "MySQL.Database_Area.Top30Database.localhost-@MYSQL"
, "MySQL.Database_Structure.DatabaseVersion.localhost-@MYSQL"
, "MySQL.Database_Structure.Parameter.localhost-@MYSQL"
, "MySQL.Performance.InnoDBBufferPool.localhost-@MYSQL"
, "MySQL.Performance.InnoDBIOStatus.localhost-@MYSQL"
, "MySQL.Performance.InnoDBLogBuffer.localhost-@MYSQL"
, "MySQL.Performance.MyISAMKeyBuffer.localhost-@MYSQL"
, "MySQL.Performance.Network_Traffic.localhost-@MYSQL"
, "MySQL.Performance.QueryCache.localhost-@MYSQL"
, "MySQL.Performance.Sorts.localhost-@MYSQL"
, "MySQL.Proactive_Check.Aborted_Information.localhost-@MYSQL"
, "MySQL.Proactive_Check.Resource_Limit.localhost-@MYSQL"
, "MySQL.Proactive_Check.SQLExecutions.localhost-@MYSQL"
, "MySQL.Proactive_Check.Thread.localhost-@MYSQL"
, "Postgres.Database_Area.Tablespace_Free.localhost-@PGSQL"
, "Postgres.Database_Statistic.Database_Info.localhost-@PGSQL"
, "Postgres.Database_Structure.Database_Version.localhost-@PGSQL"
, "Postgres.Database_Structure.Parameters.localhost-@PGSQL"
, "Postgres.Performance.Buffer_Cache_Hit.localhost-@PGSQL"
, "Postgres.Performance.Buffer_Cache_Usage.localhost-@PGSQL"
, "Postgres.Performance.Datafile_IO_Info.localhost-@PGSQL"
, "Postgres.Proactive_Check.Resource_Limit.localhost-@PGSQL"
, "Postgres.Proactive_Check.Session.localhost-@PGSQL"
, "Postgres.Proactive_Check.Transactions.localhost-@PGSQL"
, "SQL_Server.Database_Area.Database_free.localhost-@MSSQL"
, "SQL_Server.Database_Structure.Database_Version.localhost-@MSSQL"
, "SQL_Server.Database_Structure.Parameters.localhost-@MSSQL"
, "SQL_Server.Performance.Buffer_Cache_Hit_Ratio.localhost-@MSSQL"
, "SQL_Server.Performance.DATA_FILE_IO_STATUS.localhost-@MSSQL"
, "SQL_Server.Performance.Memory_Management.localhost-@MSSQL"
, "SQL_Server.Performance.Plan_Cache_Hit_Ratio.localhost-@MSSQL"
, "SQL_Server.Performance.Tempdb_Usage.localhost-@MSSQL"
, "SQL_Server.Performance.Wait_Status.localhost-@MSSQL"
, "SQL_Server.Proactive_Check.Batch_Requests.localhost-@MSSQL"
, "SQL_Server.Proactive_Check.RESOURCE_LIMIT.localhost-@MSSQL"
, "SQL_Server.Proactive_Check.SESSION.localhost-@MSSQL"
];

dummydata = { timestamp: new Date() };

// print ("var wddb = db.getSisterDB(\"wiperdog\");");
for (var i in cols) {
  wddb[cols[i]].insert(dummydata);
//   wddb[cols[i]].drop();
}

