SELECT count(*) as 'total',
    sum( case when (Type=3) then 1 else 0 end) as 'Galaxies', 
    sum( case when (Type=6) then 1 else 0 end) as 'Stars', 
    sum( case when (Type not in (3,6)) then 1 else 0 end) as 'Other' 
FROM PhotoPrimary	-- for each object
WHERE (( u - g > 2.0) or (u > 22.3) ) -- apply the quasar color cut.
    and ( i between 0 and 19 ) 
    and ( g - r > 1.0 ) 
    and ( (r - i < 0.08 + 0.42 * (g - r - 0.96)) or (g - r > 2.26 ) ) 
    and ( i - z < 0.25 ) 

