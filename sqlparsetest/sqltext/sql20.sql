    SELECT
    sl.plate,sl.mjd,sl.fiber,
    sl.caIIKside,sl.caIIKerr,sl.caIIKmask,
    sp.fehadop,sp.fehadopunc,sp.fehadopn,
    sp.loggadopn,sp.loggadopunc,sp.loggadopn
    FROM sppLines AS sl
    JOIN sppParams AS sp
    ON sl.specobjid = sp.specobjid
    WHERE
    fehadop < -3.5
    AND fehadopunc between 0.01
    and 0.5 and fehadopn > 3 
