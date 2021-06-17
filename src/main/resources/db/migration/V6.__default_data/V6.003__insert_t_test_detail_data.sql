-- INSERT DATA
INSERT INTO rest.t_test_detail (test_id, test_name, test_desc)
VALUES
    (1, 'TEST 1', 'test desc 1'),
    (2, 'TEST 2', 'test desc 2'),
    (3, 'TEST 3', 'test desc 3'),
    (4, 'TEST 4', 'test desc 4')
ON CONFLICT(test_id)
DO UPDATE
SET
    test_name = EXCLUDED.test_name,
    test_desc = EXCLUDED.test_desc
;