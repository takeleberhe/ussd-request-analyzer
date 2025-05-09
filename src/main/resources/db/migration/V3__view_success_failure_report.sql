-- View: ussd_success_failure_view
-- Purpose: Shows the count of successful vs. failed USSD responses.

CREATE OR REPLACE VIEW ussd_success_failure_view AS
SELECT
    status,                 -- "success", "failed"
    COUNT(*) AS count       -- Total number of responses for each status
FROM
    ussd_responses
GROUP BY
    status;
