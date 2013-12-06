SELECT -- TOP 100
 G.objID, GN.distance
FROM Galaxy as G
JOIN dbo.fGetNearbyObjEq(185.,-0.5, 1) AS GN
    ON G.objID = GN.objID 
ORDER BY distance 
