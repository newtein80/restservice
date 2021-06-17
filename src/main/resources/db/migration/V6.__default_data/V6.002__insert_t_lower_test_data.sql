-- INSERT DATA
INSERT INTO rest.t_lower_test (lower_id, upper_id, lower_desc, lower_desc_default)
VALUES
    (1, 1,   'lower 1', 'lower desc 1'),
    (2, 1,   'lower 2', 'lower desc 2'),
    (3, 2,   'lower 3', 'lower desc 3'),
    (4, null,'lower 4', 'lower desc 4')
ON CONFLICT(lower_id)
DO UPDATE
SET
    upper_id = EXCLUDED.upper_id,
    lower_desc = EXCLUDED.lower_desc,
    lower_desc_default = EXCLUDED.lower_desc_default
;