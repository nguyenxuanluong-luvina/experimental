// var wddb = db.getSisterDB("wiperdog");
var wddb = db.getSisterDB("wiperdog_test");
var cols = wddb.getCollectionNames();

// print ("var wddb = db.getSisterDB(\"wiperdog\");");
for (var i in cols) {
    if (cols[i].search("Test") > 0) {
        print("wddb[\"" + cols[i] + "\"].drop();");
        wddb[cols[i]].drop();
    }
}
