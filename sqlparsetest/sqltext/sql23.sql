SELECT first.plate, other.plate,
    COUNT(DISTINCT other.mjd) + COUNT(DISTINCT first.mjd) AS nightsObserved, 
    otherPlate.programname, count(DISTINCT other.bestObjID) AS objects
    FROM SpecObjAll first 
    JOIN SpecObjAll other ON first.bestObjID = other.bestObjID 
    JOIN PlateX AS firstPlate ON firstPlate.plate = first.plate 
    JOIN PlateX AS otherPlate ON otherPlate.plate = other.plate
    WHERE first.scienceprimary = 1 AND other.scienceprimary = 0 
    AND other.bestObjID > 0
    GROUP BY first.plate, other.plate, otherPlate.programname
    ORDER BY nightsObserved DESC, otherPlate.programname, 
    first.plate, other.plate
