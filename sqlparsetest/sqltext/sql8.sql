SELECT top 100
    objid, ra, dec, psfmag_i-extinction_i AS mag_i,
    psfmag_r-extinction_r AS mag_r, z            -- In SpecPhoto, "z" is the redshift
    FROM SpecPhoto
    WHERE
    (class = 'QSO')

