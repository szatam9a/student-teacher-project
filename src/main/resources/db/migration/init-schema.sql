CREATE TABLE answer (
  id BIGINT AUTO_INCREMENT NOT NULL,
   exercise_id BIGINT NULL,
   sentence_beginning VARCHAR(255) NULL,
   sentence_ending VARCHAR(255) NULL,
   correct_answers UNKNOWN__JAVA.UTIL.LIST<JAVA.LANG.STRING> NULL,
   position INT NOT NULL,
   answer VARCHAR(255) NULL,
   is_correct BIT(1) NOT NULL,
   left_pair VARCHAR(255) NULL,
   right_pair VARCHAR(255) NULL,
   CONSTRAINT pk_answer PRIMARY KEY (id)
);

CREATE TABLE course (
  id BIGINT AUTO_INCREMENT NOT NULL,
   title VARCHAR(255) NULL,
   user_id BIGINT NULL,
   CONSTRAINT pk_course PRIMARY KEY (id)
);

CREATE TABLE course_tags (
  course_id BIGINT NOT NULL,
   tag_id BIGINT NOT NULL
);

CREATE TABLE exercise (
  id BIGINT AUTO_INCREMENT NOT NULL,
   title VARCHAR(255) NULL,
   exercise_type VARCHAR(255) NULL,
   user_id BIGINT NULL,
   CONSTRAINT pk_exercise PRIMARY KEY (id)
);

CREATE TABLE exercise_tags (
  exercise_id BIGINT NOT NULL,
   tag_id BIGINT NOT NULL
);

CREATE TABLE flashcard (
  id BIGINT AUTO_INCREMENT NOT NULL,
   flash_card_front VARCHAR(255) NULL,
   flash_card_back VARCHAR(255) NULL,
   user_id BIGINT NULL,
   CONSTRAINT pk_flashcard PRIMARY KEY (id)
);

CREATE TABLE flashcard_tags (
  flashcard_id BIGINT NOT NULL,
   tag_id BIGINT NOT NULL
);

CREATE TABLE lesson (
  id BIGINT AUTO_INCREMENT NOT NULL,
   title VARCHAR(255) NULL,
   course_id BIGINT NULL,
   user_id BIGINT NULL,
   CONSTRAINT pk_lesson PRIMARY KEY (id)
);

CREATE TABLE lesson_tags (
  lesson_id BIGINT NOT NULL,
   tag_id BIGINT NOT NULL
);

CREATE TABLE studyrial (
  id BIGINT AUTO_INCREMENT NOT NULL,
   title VARCHAR(255) NULL,
   lesson_id BIGINT NULL,
   user_id BIGINT NULL,
   CONSTRAINT pk_studyrial PRIMARY KEY (id)
);

CREATE TABLE studyrial_exercises (
  exercise_id BIGINT NOT NULL,
   studyrial_id BIGINT NOT NULL
);

CREATE TABLE studyrial_flashcards (
  flashcard_id BIGINT NOT NULL,
   studyrial_id BIGINT NOT NULL
);

CREATE TABLE studyrial_tags (
  studyrial_id BIGINT NOT NULL,
   tag_id BIGINT NOT NULL
);

CREATE TABLE tag (
  id BIGINT AUTO_INCREMENT NOT NULL,
   background_color VARCHAR(255) NULL,
   text_color VARCHAR(255) NULL,
   name VARCHAR(255) NULL,
   user_id BIGINT NULL,
   CONSTRAINT pk_tag PRIMARY KEY (id)
);

CREATE TABLE users (
  id BIGINT AUTO_INCREMENT NOT NULL,
   email VARCHAR(255) NULL,
   password VARCHAR(255) NULL,
   username VARCHAR(255) NULL,
   first_name VARCHAR(255) NULL,
   last_name VARCHAR(255) NULL,
   roles VARCHAR(255) NULL,
   CONSTRAINT pk_users PRIMARY KEY (id)
);

ALTER TABLE answer ADD CONSTRAINT FK_ANSWER_ON_EXERCISE FOREIGN KEY (exercise_id) REFERENCES exercise (id);

ALTER TABLE course ADD CONSTRAINT FK_COURSE_ON_USER FOREIGN KEY (user_id) REFERENCES users (id);

ALTER TABLE exercise ADD CONSTRAINT FK_EXERCISE_ON_USER FOREIGN KEY (user_id) REFERENCES users (id);

ALTER TABLE flashcard ADD CONSTRAINT FK_FLASHCARD_ON_USER FOREIGN KEY (user_id) REFERENCES users (id);

ALTER TABLE lesson ADD CONSTRAINT FK_LESSON_ON_COURSE FOREIGN KEY (course_id) REFERENCES course (id);

ALTER TABLE lesson ADD CONSTRAINT FK_LESSON_ON_USER FOREIGN KEY (user_id) REFERENCES users (id);

ALTER TABLE studyrial ADD CONSTRAINT FK_STUDYRIAL_ON_LESSON FOREIGN KEY (lesson_id) REFERENCES lesson (id);

ALTER TABLE studyrial ADD CONSTRAINT FK_STUDYRIAL_ON_USER FOREIGN KEY (user_id) REFERENCES users (id);

ALTER TABLE tag ADD CONSTRAINT FK_TAG_ON_USER FOREIGN KEY (user_id) REFERENCES users (id);

ALTER TABLE course_tags ADD CONSTRAINT fk_coutag_on_course FOREIGN KEY (course_id) REFERENCES course (id);

ALTER TABLE course_tags ADD CONSTRAINT fk_coutag_on_tag FOREIGN KEY (tag_id) REFERENCES tag (id);

ALTER TABLE exercise_tags ADD CONSTRAINT fk_exetag_on_exercise FOREIGN KEY (exercise_id) REFERENCES exercise (id);

ALTER TABLE exercise_tags ADD CONSTRAINT fk_exetag_on_tag FOREIGN KEY (tag_id) REFERENCES tag (id);

ALTER TABLE flashcard_tags ADD CONSTRAINT fk_flatag_on_flashcard FOREIGN KEY (flashcard_id) REFERENCES flashcard (id);

ALTER TABLE flashcard_tags ADD CONSTRAINT fk_flatag_on_tag FOREIGN KEY (tag_id) REFERENCES tag (id);

ALTER TABLE lesson_tags ADD CONSTRAINT fk_lestag_on_lesson FOREIGN KEY (lesson_id) REFERENCES lesson (id);

ALTER TABLE lesson_tags ADD CONSTRAINT fk_lestag_on_tag FOREIGN KEY (tag_id) REFERENCES tag (id);

ALTER TABLE studyrial_exercises ADD CONSTRAINT fk_stuexe_on_exercise FOREIGN KEY (exercise_id) REFERENCES exercise (id);

ALTER TABLE studyrial_exercises ADD CONSTRAINT fk_stuexe_on_studyrial FOREIGN KEY (studyrial_id) REFERENCES studyrial (id);

ALTER TABLE studyrial_flashcards ADD CONSTRAINT fk_stufla_on_flashcard FOREIGN KEY (flashcard_id) REFERENCES flashcard (id);

ALTER TABLE studyrial_flashcards ADD CONSTRAINT fk_stufla_on_studyrial FOREIGN KEY (studyrial_id) REFERENCES studyrial (id);

ALTER TABLE studyrial_tags ADD CONSTRAINT fk_stutag_on_studyrial FOREIGN KEY (studyrial_id) REFERENCES studyrial (id);

ALTER TABLE studyrial_tags ADD CONSTRAINT fk_stutag_on_tag FOREIGN KEY (tag_id) REFERENCES tag (id);