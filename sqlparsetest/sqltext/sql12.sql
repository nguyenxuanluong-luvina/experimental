SELECT -- TOP 10
 P.ObjID 	-- distinct cases
FROM PhotoPrimary AS P 	-- P is the primary object
    JOIN Neighbors AS N ON P.ObjID = N.ObjID 	-- N is the neighbor link
    JOIN PhotoPrimary AS L ON L.ObjID = N.NeighborObjID 
	-- L is the lens candidate of P
WHERE 	
    P.ObjID < L. ObjID 	-- avoid duplicates
    and abs((P.u-P.g)-(L.u-L.g))<0.05 	-- L and P have similar spectra.
    and abs((P.g-P.r)-(L.g-L.r))<0.05 	
    and abs((P.r-P.i)-(L.r-L.i))<0.05 	
    and abs((P.i-P.z)-(L.i-L.z))<0.05 	

