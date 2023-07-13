-- Create user table
CREATE TABLE IF NOT EXISTS user (
  id INT PRIMARY KEY,
  username VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL,
  alumni_id INT,
  display_name VARCHAR(255),
  avatar VARCHAR(255),
  cover_bg VARCHAR(255),
  email VARCHAR(255),
  status VARCHAR(255),
  user_type VARCHAR(255),
  slug VARCHAR(255),
  UNIQUE (username)
);

-- Create user_settings table
CREATE TABLE IF NOT EXISTS user_settings (
  id INT PRIMARY KEY,
  user_id INT NOT NULL,
  theme VARCHAR(255),
  language VARCHAR(255),
  lock_profile BOOLEAN,
  FOREIGN KEY (user_id) REFERENCES user(id)
);

-- Create friendship table
CREATE TABLE IF NOT EXISTS friendship (
  id INT PRIMARY KEY,
  user_1_id INT NOT NULL,
  user_2_id INT NOT NULL,
  FOREIGN KEY (user_1_id) REFERENCES user(id),
  FOREIGN KEY (user_2_id) REFERENCES user(id)
);

-- Create community table
CREATE TABLE IF NOT EXISTS `group` (
  id INT PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  created_date DATE,
  count_member INT,
  founder_id INT NOT NULL,
  FOREIGN KEY (founder_id) REFERENCES user(id)
);

-- Create post table
CREATE TABLE IF NOT EXISTS post (
  id INT PRIMARY KEY,
  content TEXT,
  lock_comment BOOLEAN,
  count_action INT,
  created_date DATE,
  user_id INT NOT NULL,
  FOREIGN KEY (user_id) REFERENCES user(id)
);

-- Create post_report table
CREATE TABLE IF NOT EXISTS post_report (
  id INT PRIMARY KEY,
  post_id INT NOT NULL,
  user_report_id INT NOT NULL,
  content VARCHAR(255),
  created_date DATE,
  FOREIGN KEY (post_id) REFERENCES post(id),
  FOREIGN KEY (user_report_id) REFERENCES user(id)
);

-- Create action table
CREATE TABLE IF NOT EXISTS action (
  id INT PRIMARY KEY,
  name VARCHAR(255),
  img VARCHAR(255)
);

-- Create post_action table
CREATE TABLE IF NOT EXISTS post_action (
  id INT PRIMARY KEY,
  post_id INT NOT NULL,
  action_id INT NOT NULL,
  user_id INT NOT NULL,
  FOREIGN KEY (post_id) REFERENCES post(id),
  FOREIGN KEY (action_id) REFERENCES action(id),
  FOREIGN KEY (user_id) REFERENCES user(id)
);

-- Create tags table
CREATE TABLE IF NOT EXISTS tags (
  id INT PRIMARY KEY,
  name VARCHAR(255)
);

-- Create post_tag table
CREATE TABLE IF NOT EXISTS post_tag (
  id INT PRIMARY KEY,
  post_id INT NOT NULL,
  tag_id INT NOT NULL,
  FOREIGN KEY (post_id) REFERENCES post(id),
  FOREIGN KEY (tag_id) REFERENCES tags(id)
);

-- Create image_post table
CREATE TABLE IF NOT EXISTS image_post (
  id INT PRIMARY KEY,
  url VARCHAR(255),
  post_id INT,
  FOREIGN KEY (post_id) REFERENCES post(id)
);

-- Create comment table
CREATE TABLE IF NOT EXISTS comment (
  id INT PRIMARY KEY,
  content VARCHAR(255),
  count_action INT,
  created_date DATE,
  comment_id INT,
  post_id INT NOT NULL,
  user_id INT NOT NULL,
  FOREIGN KEY (comment_id) REFERENCES comment(id),
  FOREIGN KEY (post_id) REFERENCES post(id),
  FOREIGN KEY (user_id) REFERENCES user(id)
);

-- Create comment_action table
CREATE TABLE IF NOT EXISTS comment_action (
  id INT PRIMARY KEY,
  comment_id INT NOT NULL,
  action_id INT NOT NULL,
  FOREIGN KEY (comment_id) REFERENCES comment(id),
  FOREIGN KEY (action_id) REFERENCES action(id)
);

-- Create survey table
CREATE TABLE IF NOT EXISTS survey (
  id INT PRIMARY KEY,
  post_id INT NOT NULL,
  content_question VARCHAR(255),
  FOREIGN KEY (post_id) REFERENCES post(id)
);

-- Create survey_answer table
CREATE TABLE IF NOT EXISTS survey_answer (
  id INT PRIMARY KEY,
  survey_id INT NOT NULL,
  answer VARCHAR(255),
  FOREIGN KEY (survey_id) REFERENCES survey(id)
);

-- Create survey_result table
CREATE TABLE IF NOT EXISTS survey_result (
  id INT PRIMARY KEY,
  survey_id INT,
  result VARCHAR(255),
  user_id INT,
  FOREIGN KEY (survey_id) REFERENCES survey(id),
  FOREIGN KEY (user_id) REFERENCES user(id)
);