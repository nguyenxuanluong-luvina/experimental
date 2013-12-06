SELECT -- top 100
 objID, ra, dec,
    (CASE WHEN q.type IS NULL THEN 'NO' ELSE 'YES' END) AS found
    FROM PhotoObj AS p 
    OUTER APPLY dbo.fFootprintEq(ra,dec,0.1) AS q
    WHERE (ra BETWEEN 179.5 AND 182.3) AND (dec BETWEEN -1.0 AND 1.8) 

