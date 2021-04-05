DROP TABLE IF EXISTS url;

-- -------------------------------------------------------------
-- CREATE Table `url`
-- -------------------------------------------------------------
CREATE TABLE `url` (
  id bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
  original_url varchar(2048) NOT NULL,
  shortening_key varchar(20) NULL,
  req_count INT DEFAULT 1,
  created_at datetime DEFAULT CURRENT_TIMESTAMP NULL,
  updated_at datetime DEFAULT CURRENT_TIMESTAMP NULL
);
