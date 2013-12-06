SELECT TOP 100
    r.run, 
    r.rerun, 
    f.camCol, 
    f.field, 
    p.objID, 
    p.ra, 
    p.dec, 
    p.modelMag_r, 
    f.psfWidth_r 
FROM
    PhotoTag AS p 
    JOIN Field AS f ON f.fieldid = p.fieldid 
    JOIN Run AS r ON f.run = r.run 
WHERE mode=1     -- select primary objects only
    and f.psfWidth_r > 1.2 
    and p.modelMag_r < 21. 
    and r.stripe = 21 

