-- V2__add_indexes_to_speed_up_queries.sql

-- Index on correlation_id for joining requests and responses
CREATE INDEX IF NOT EXISTS idx_ussd_requests_correlation_id
ON ussd_requests (correlation_id);

CREATE INDEX IF NOT EXISTS idx_ussd_responses_correlation_id
ON ussd_responses (correlation_id);

--Index on timestamp to speed up recent N query operations
CREATE INDEX IF NOT EXISTS idx_ussd_requests_timestamp
ON ussd_requests (timestamp);

CREATE INDEX IF NOT EXISTS idx_ussd_responses_timestamp
ON ussd_responses (timestamp);
