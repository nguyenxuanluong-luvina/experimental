SELECT TOP 10
    objID, ra, dec, 
    sqrt( power(rowv,2) + power(colv, 2) ) as velocity 
FROM PhotoObj
WHERE
    (power(rowv,2) + power(colv, 2)) > 50 
    AND rowv != -9999 and colv != -9999 
