SELECT -- TOP 1000
 objID
FROM Galaxy
WHERE
    (r - extinction_r) < 22 	-- extinction-corrected r magnitude
