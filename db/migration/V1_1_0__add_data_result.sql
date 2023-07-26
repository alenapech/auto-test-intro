CREATE TABLE IF NOT EXISTS data_result
(id integer PRIMARY KEY AUTOINCREMENT,
status_code text NOT NULL,
request_url text NOT NULL,
request_method text NOT NULL,
request_time text NOT NULL
);
