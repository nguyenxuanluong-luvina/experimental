var start = ISODate("2013-10-06T17:40:00+09:00")
var end = ISODate("2013-10-06T18:36:00+09:00")
var wddb = db.getSisterDB("wiperdog");
var cols = wddb.getCollectionNames();

for (var i in cols) {
    if (cols[i] != "system.indexes") {
        print ("fix the collection: " + cols[i]);
        wddb[cols[i]].remove( {"fetchedAt_bin" : { $lt: start.getTime(), $gt : end.getTime() / 1000 } } );
    }
}
