SELECT -- TOP 10
 fld.run, fld.avg_sky_muJy, fld.runarea AS area, ISNULL(fp.nfirstmatch,0)
FROM (
--first part: for each run, get total area and average sky brightness
    SELECT run, sum(primaryArea) AS runarea, 
    3631e6*avg(power(cast(10. as float),-0.4*sky_r)) as avg_sky_muJy 
    FROM Field 
    GROUP BY run
    ) AS fld
    LEFT OUTER JOIN (
    -- second part: for each run,get total number of FIRST matches. To get the run number
    -- for each FIRST match, need to join FIRST with PHOTOPRIMARY. Some runs may have
    -- 0 FIRST matches, so these runs will not appear in the result set of this subquery.
    -- But we want to keep all runs from the first query in the final result, hence
    -- we need a LEFT OUTER JOIN between the first and the second query.
    -- The LEFT OUTER JOIN returns all the rows from the first subquery and matches
    -- with the corresponding rows from the second query. Where the second query
    -- has no corresponding row, a NULL is returned. The ISNULL() function in the
    -- SELECT above converts this NULL into a 0 to say "0 FIRST matches in this run".
    SELECT p.run, count(*) AS nfirstmatch 
    FROM FIRST AS fm 
    INNER JOIN photoprimary as p 
    ON p.objid=fm.objid 
    GROUP BY p.run
    ) AS fp
    ON fld.run=fp.run
    ORDER BY fld.run 
