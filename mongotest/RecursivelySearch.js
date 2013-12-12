//Function to recusive search 
function find_recursive(doc,comparator) {
    for (var attr in doc) {
            if (comparator(doc)) {
                return true;
            }

        if (doc[attr] !== null && typeof (doc[attr]) == "object") {
            if (doc[attr] instanceof Array) {
                for (var i = 0; i < doc[attr].length; i++) {
                    var result = find_recursive(doc[attr][i],comparator);
                    if (result) {
                        return result
                    }
                }
            } else {
                var result = find_recursive(doc[attr], comparator);
                if (result) {
                    return result
                }
            }
        }
    }
}
/* Usage :
//Define functions to compare key and value
var comparator = function(doc) { if (doc["join_type"] == "INNER") return true; else return false }

//Query usign comparator and search function
db['sqltext_parsed_test'].find().forEach( function( doc){
 if(find_recursive(doc,comparator)){
 printjson(doc.statement.sql)
 print("-------------------------")
 }
});*/

