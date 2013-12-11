//Define functions to compare key and value
var eq = function(doc,key,value) { if (doc[key] === value) return true; else return false }
var not_eq = function(doc,key,value) { if (doc[key] !== value) return true; else  return false }
var gt = function(doc,key,value) { if (doc[key] > value) return true; else return false }
var lt = function(doc,key,value) { if (doc[key] < value) return true; else return false }
var gt_eq = function(doc,key,value) { if (doc[key] >= value) return true; else return false }
var lt_eq = function(doc,key,value) { if (doc[key] <= value) return true; else return false }
//Function to recusive search 
function find_recursive(doc, key, value, comOperator) {
    for (var attr in doc) {
        if ((typeof doc[key] != "undefined") && (typeof comOperator != "undefined" ) && comOperator !== "") {
            if(comOperator.trim() == "="){
                return eq(doc,key,value)
            }
            if(comOperator.trim() == "!="){
                return not_eq(doc,key,value)
            }
            if(comOperator.trim() == ">"){
                return gt.call(doc,key,value)
            }

            if(comOperator.trim() == ">="){
                return gt_eq(doc,key,value)
            }

            if(comOperator.trim() == "<"){
                return lt(doc,key,value)
            }

            if(comOperator.trim() == "<="){
                return lt_eq(doc,key,value)
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
/*
// Parameter : key , value and comparision operator passing to search function
var key = "join_type"
var value = "INNER"
var compOperator = "="

db['sqltext_parsed_test'].find().forEach( function( doc){
 if(find_recursive(doc,key,value,compOperator)){
 printjson(doc.statement.sql)
 print("-------------------------")
 }
});*/

