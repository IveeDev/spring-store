CREATE TABLE blacklisted_tokens (
    token_id   VARCHAR(36) PRIMARY KEY,
    expires_at DATETIME    NOT NULL
);