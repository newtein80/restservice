-- INSERT DATA
INSERT INTO rest.t_sample_info (sample_id, content, title, viewcount)
	VALUES
    (1, 'TEST 1', 'test desc 1', 0),
    (2, 'TEST 2', 'test desc 2', 0),
    (3, 'TEST 3', 'test desc 3', 0),
    (4, 'TEST 4', 'test desc 4', 0)
ON CONFLICT(sample_id)
DO UPDATE
SET
    content = EXCLUDED.content,
    title = EXCLUDED.title
;