function find_recursive(doc, key, value, comOperator) {
    for (var attr in doc) {
        if (doc[key] != "undefined" && comOperator != 'undefined' && comOperator !== "") {
            switch (comOperator.trim()) {
            case "=":
                if (doc[key] == value) {
                    return true;
                }
                break;
            case "!=":
                if (doc[key] !== value) {
                    return true;
                }
                break;
            case ">":
                if (doc[key] > value) {
                    return true;
                }
            case ">=":
                if (doc[key] >= value) {
                    return true;
                }
                break;
            case "<":
                if (doc[key] < value) {
                    return true;
                }
                break;
            case "<=":
                if (doc[key] <= value) {
                    return true;
                }
                break;
            default:
                print("Incorrect comparison operator")

            }
        }
        if (doc[attr] !== null && typeof (doc[attr]) == "object") {
            if (doc[attr] instanceof Array) {
                for (var i = 0; i < doc[attr].length; i++) {
                    var result = find_recursive(doc[attr][i], key, value, comOperator);
                    if (result) {
                        return result
                    }
                }
            } else {
                var result = find_recursive(doc[attr], key, value, comOperator);
                if (result) {
                    return result
                }
            }
        }
    }
}
/* Usage example :
//Parameter : key , value and comparision operator passing to search function
var key = "table_name"
var value = "SpecObjAll"
var compOperator = "="

db['sqltext_parsed_test'].find().forEach( function( doc){
 if(find_recursive(doc,key,value,compOperator)){
 printjson(doc.statement.sql)
 print("-------------------------")
 }
});
*/

