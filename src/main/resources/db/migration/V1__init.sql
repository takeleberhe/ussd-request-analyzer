 CREATE TABLE ussd_requests (
   id SERIAL PRIMARY KEY,
   correlation_id TEXT ,
   operation TEXT,
   request_data TEXT,
   timestamp TIMESTAMP
 );

 CREATE TABLE ussd_responses (
   id SERIAL PRIMARY KEY,
   correlation_id TEXT ,
   operation TEXT,
   status TEXT,
   reply TEXT,
   timestamp TIMESTAMP
 );

 CREATE TABLE blacklist (
   id SERIAL PRIMARY KEY,
   msisdn TEXT UNIQUE NOT NULL,
   reason TEXT,
   created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
 );
