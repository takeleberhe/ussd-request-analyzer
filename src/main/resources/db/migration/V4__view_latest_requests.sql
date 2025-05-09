-- View: latest_requests_view
-- Purpose: Joins latest USSD requests with their responses in descending time order.

CREATE OR REPLACE VIEW latest_requests_view AS
SELECT
    req.id AS request_id,            -- Primary key from ussd_requests
    req.correlation_id,              -- Shared ID to match requests and responses
    req.operation,                   -- Type of USSD operation (e.g., 'buyVoucher')
    req.request_data,                -- Raw data submitted by the user
    req.timestamp AS request_time,   -- When the request was made
    res.status,                      -- Response status ('success', 'error', etc.)
    res.reply,                       -- Response message sent back to the user
    res.timestamp AS response_time   -- When the response was generated
FROM
    ussd_requests req
JOIN
    ussd_responses res ON req.correlation_id = res.correlation_id
ORDER BY
    req.timestamp DESC;
