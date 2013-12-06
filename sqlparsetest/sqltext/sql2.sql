-- sample 2
SELECT TOP 100
    objID, ra ,dec 	-- Get the unique object ID and coordinates
FROM
    PhotoPrimary		-- From the table containing photometric data for unique objects
WHERE
    ra > 185 and ra < 185.1 
    AND dec > 15 and dec < 15.1 	-- that matches our criteria

