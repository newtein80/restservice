CREATE TABLE IF NOT EXISTS rest.t_lower_test (
    lower_id integer,
    upper_id integer,
    lower_desc TEXT NOT NULL,
    lower_desc_default TEXT NOT NULL,
    PRIMARY KEY (lower_id)
);