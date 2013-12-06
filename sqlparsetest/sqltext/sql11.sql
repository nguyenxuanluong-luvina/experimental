SELECT -- TOP 100
 u, g, r, i, z FROM Galaxy
WHERE
    (htmid*37 & 0x000000000000FFFF) 
        < (650 * 1) 

