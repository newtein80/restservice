-- INSERT DATA
INSERT INTO rest.t_upper_test (
    upper_id,
    upper_desc,
    upper_desc_default
    )
VALUES
    (1, 'upper 1', 'Technical Writer'),
    (2, 'upper 2', 'Editor'),
    (3, 'upper 3', 'Reviewer'),
    (4, 'upper 4', 'Reader')
ON CONFLICT(upper_id)
DO UPDATE
SET
    upper_desc = EXCLUDED.upper_desc,
    upper_desc_default = EXCLUDED.upper_desc_default
;