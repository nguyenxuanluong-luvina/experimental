SELECT -- TOP 100
 run,
    camCol, 
    rerun, 
    field, 
    objID, 
    u, g, r, i, z, 
    ra, dec 	-- Just get some basic quantities
FROM Star		-- From all stellar primary detections
WHERE u - g < 0.4
    and g - r < 0.7 
    and r - i > 0.4 
    and i - z > 0.4 	-- that meet the color criteria
