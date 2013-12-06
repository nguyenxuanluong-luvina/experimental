SELECT -- TOP 1000
    objID, 
    ra, 
    dec, 
    psfMag_u, psfMag_g, psfMag_r, psfMag_i, psfMag_z, 
    dbo.fPhotoFlagsN(flags) 
FROM Galaxy
WHERE
    (flags & (dbo.fPhotoFlags('SATURATED'))) != 0 

