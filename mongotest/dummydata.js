// var wddb = db.getSisterDB("wiperdog");
var wddb = db.getSisterDB("wiperdog_test");
var mysqliid = "localhost-@MYSQL-information_schema";
var pgsqliid = "localhost-@PGSQL-postgres";
var mssqliid = "localhost-@MSSQL";

var cols =  [
  "MySQL.Database_Area.InnoDBTablespace_Free"
, "MySQL.Database_Area.Top30Database"
, "MySQL.Database_Structure.DatabaseVersion"
, "MySQL.Database_Structure.Parameter"
, "MySQL.Performance.InnoDBBufferPool"
, "MySQL.Performance.InnoDBIOStatus"
, "MySQL.Performance.InnoDBLogBuffer"
, "MySQL.Performance.MyISAMKeyBuffer"
, "MySQL.Performance.Network_Traffic"
, "MySQL.Performance.QueryCache"
, "MySQL.Performance.Sorts"
, "MySQL.Proactive_Check.Aborted_Information"
, "MySQL.Proactive_Check.Resource_Limit"
, "MySQL.Proactive_Check.SQLExecutions"
, "MySQL.Proactive_Check.Thread"
, "Postgres.Database_Area.Tablespace_Free"
, "Postgres.Database_Statistic.Database_Info"
, "Postgres.Database_Structure.Database_Version"
, "Postgres.Database_Structure.Parameters"
, "Postgres.Performance.Buffer_Cache_Hit"
, "Postgres.Performance.Buffer_Cache_Usage"
, "Postgres.Performance.Datafile_IO_Info"
, "Postgres.Proactive_Check.Resource_Limit"
, "Postgres.Proactive_Check.Session"
, "Postgres.Proactive_Check.Transactions"
, "SQL_Server.Database_Area.Database_free"
, "SQL_Server.Database_Structure.Database_Version"
, "SQL_Server.Database_Structure.Parameters"
, "SQL_Server.Performance.Buffer_Cache_Hit_Ratio"
, "SQL_Server.Performance.DATA_FILE_IO_STATUS"
, "SQL_Server.Performance.Memory_Management"
, "SQL_Server.Performance.Plan_Cache_Hit_Ratio"
, "SQL_Server.Performance.Tempdb_Usage"
, "SQL_Server.Performance.Wait_Status"
, "SQL_Server.Proactive_Check.Batch_Requests"
, "SQL_Server.Proactive_Check.RESOURCE_LIMIT"
, "SQL_Server.Proactive_Check.SESSION"
];

dummydata = { timestamp: new Date() };

// print ("var wddb = db.getSisterDB(\"wiperdog\");");
for (var i in cols) {
  var colname = "dummy";
  if (cols[i].indexOf("MySQL") >= 0) {
    colname = cols[i] + "." + mysqliid;
  } else if (cols[i].indexOf("Postgres") >= 0) {
    colname = cols[i] + "." + pgsqliid;
  } else if (cols[i].indexOf("SQL_Server") >= 0) {
    colname = cols[i] + "." + mssqliid;
  }
  wddb[colname].insert(dummydata);
//   wddb[cols[i]].drop();
}

