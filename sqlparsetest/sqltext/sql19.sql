SELECT
    s.plate, s.fiberid, s.mjd, s.z, s.zwarning,
    g.h_beta_flux, g.h_beta_flux_err,
    g.h_alpha_flux, g.h_alpha_flux_err
    FROM GalSpecLine AS g
    JOIN SpecObj AS s
    ON s.specobjid = g.specobjid
    WHERE
    h_alpha_flux > h_alpha_flux_err*5
    AND h_beta_flux > h_beta_flux_err*5
    AND h_beta_flux_err > 0
    AND h_alpha_flux > 10.*h_beta_flux
    AND s.class = 'GALAXY'
    AND s.zwarning = 0 
