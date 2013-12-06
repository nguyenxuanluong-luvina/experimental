SELECT prim.bestObjId, prim.mjd AS PrimMJD, prim.plate AS PrimPlate,
    other.mjd AS OtherMJD, other.plate AS OtherPlate,
    prim.z AS PrimZ, other.z AS OtherZ, plate.programname
FROM SpecObjAll prim
    JOIN SpecObjAll other ON prim.bestObjId = other.bestObjId
    JOIN platex AS plate ON other.plate = plate.plate AND other.mjd = plate.mjd
WHERE other.bestObjId > 0
    AND prim.sciencePrimary = 1
    AND other.sciencePrimary = 0
    AND prim.z > 2.5
ORDER BY prim.bestObjId

