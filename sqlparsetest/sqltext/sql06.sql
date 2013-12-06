SELECT p.objID, p.ra, p.dec
FROM PhotoObj p
    JOIN dbo.fGetObjFromRectEq(179.5, -1.0, 182.3, 1.8) r ON p.objID = r.objID

