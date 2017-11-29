-- auto-generated definition
CREATE TABLE notifications
(
  id        BIGSERIAL NOT NULL
    CONSTRAINT notifications_pkey
    PRIMARY KEY,
  id_event  BIGINT
    CONSTRAINT notifications_events_id_fk
    REFERENCES events
    ON DELETE CASCADE,
  id_user   BIGINT
    CONSTRAINT notifications_users_id_fk
    REFERENCES users
    ON DELETE CASCADE,
  is_viewed BOOLEAN DEFAULT FALSE
);

