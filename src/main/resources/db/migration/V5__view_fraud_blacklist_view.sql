-- View: fraud_blacklist_view
-- Purpose: Flags users (by MSISDN) who requested multiple different voucher numbers.
-- Assumes request_data is in JSON format containing {"msisdn": "...", "voucher": "..."}

CREATE OR REPLACE VIEW fraud_blacklist_view AS
SELECT
    request_data::json->>'msisdn' AS msisdn,                  -- Extract MSISDN from JSON
    COUNT(DISTINCT request_data::json->>'voucher') AS voucher_variants -- Count different vouchers per MSISDN
FROM
    ussd_requests
GROUP BY
    request_data::json->>'msisdn'
HAVING
    COUNT(DISTINCT request_data::json->>'voucher') > 1;       -- Only include users with >1 voucher usage
