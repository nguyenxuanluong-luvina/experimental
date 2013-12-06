SELECT -- TOP 10
 G.ObjID, G.u, G.g, G.r, G.i, G.z 	-- get the ObjID and final mags
FROM Galaxy AS G 	-- use two Views, Galaxy and Star, as a
    JOIN Star AS S 	-- convenient way to compare objects
       ON G.parentID = S.parentID 	-- JOIN condition: star has same parent
WHERE G.parentID > 0 	-- galaxy has a "parent", which tells us this
	-- object was deblended 

