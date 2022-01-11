CREATE TABLE instrument_in_stock (
 instrument_id INT GENERATED ALWAYS AS IDENTITY
 (START WITH 2000000 INCREMENT BY 1),
 type_of_instrment VARCHAR(500) NOT NULL,
 brand VARCHAR(500),
 number_of_pcs_in_stock INT,
 fee INT NOT NULL
);

ALTER TABLE instrument_in_stock ADD CONSTRAINT PK_intrument_in_stock PRIMARY KEY (instrument_id);


CREATE TABLE person (
 person_id INT GENERATED ALWAYS AS IDENTITY
 (START WITH 6000000 INCREMENT BY 1),
 first_name VARCHAR(500) NOT NULL,
 last_name VARCHAR(500) NOT NULL,
 person_number VARCHAR(500),
 age  VARCHAR(500) NOT NULL,
 street VARCHAR(500) NOT NULL,
 zip VARCHAR(500) NOT NULL,
 city VARCHAR(500) NOT NULL
);

ALTER TABLE person ADD CONSTRAINT PK_person PRIMARY KEY (person_id);


CREATE TABLE phone (
 phone_number CHAR(500) NOT NULL,
 person_id INT NOT NULL
);


ALTER TABLE phone ADD CONSTRAINT PK_phone PRIMARY KEY (phone_number,person_id);


CREATE TABLE student (
 student_id INT GENERATED ALWAYS AS IDENTITY
 (START WITH 5000000 INCREMENT BY 1),
 quota INT,
 sibiling VARCHAR(500) NOT NULL,
 person_id INT NOT NULL
);

ALTER TABLE student ADD CONSTRAINT PK_student PRIMARY KEY (student_id);


CREATE TABLE Student_payment (
 student_id_0 INT NOT NULL,
 student_currancy VARCHAR(500) NOT NULL,
 discount VARCHAR(500),
 number_of_lessons CHAR(500),
 student_total_price VARCHAR(500) NOT NULL
);

ALTER TABLE Student_payment ADD CONSTRAINT PK_Student_payment PRIMARY KEY (student_id_0);


CREATE TABLE mail (
 mail_addrres CHAR(500) NOT NULL,
 person_id INT NOT NULL
);

ALTER TABLE mail ADD CONSTRAINT PK_mail PRIMARY KEY (mail_addrres,person_id);


CREATE TABLE parants (
 parants_id INT GENERATED ALWAYS AS IDENTITY
 (START WITH 4000000 INCREMENT BY 1),
 person_id INT NOT NULL,
 student_id INT NOT NULL
);

ALTER TABLE parants ADD CONSTRAINT PK_parants PRIMARY KEY (parants_id);


CREATE TABLE instructor (
 instructor_id INT  GENERATED ALWAYS AS IDENTITY
 (START WITH 7000000 INCREMENT BY 1),
 person_id INT NOT NULL
);

ALTER TABLE instructor ADD CONSTRAINT PK_instructor PRIMARY KEY (instructor_id);


CREATE TABLE instructor_payment  (
 instructor_id INT NOT NULL,
 instructor_currancy VARCHAR(500) NOT NULL,
 instructor_total_price VARCHAR(500) NOT NULL
);

ALTER TABLE instructor_payment   ADD CONSTRAINT PK_instructor_payment   PRIMARY KEY (instructor_id);


CREATE TABLE lesson (
 lesson_id INT GENERATED ALWAYS AS IDENTITY
 (START WITH 1000000 INCREMENT BY 1),
 place VARCHAR(500) NOT NULL,
 price VARCHAR(500) NOT NULL,
 instructor_id INT,
 time_slot TIMESTAMP(6),
 type_of_lesson VARCHAR(500) NOT NULL

);

ALTER TABLE lesson ADD CONSTRAINT PK_lesson PRIMARY KEY (lesson_id);


CREATE TABLE renting_instruments (
 student_id INT NOT NULL,
 instrument_id INT NOT NULL,
 starting_rent_period TIMESTAMP(6),
 end_instrument_period TIMESTAMP(6)
);

ALTER TABLE renting_instruments ADD CONSTRAINT PK_renting_instruments PRIMARY KEY (student_id,instrument_id);


CREATE TABLE availbility (
 time TIMESTAMP(6) NOT NULL,
 instructor_id INT NOT NULL
);

ALTER TABLE availbility ADD CONSTRAINT PK_availbility PRIMARY KEY (time,instructor_id);


CREATE TABLE ensemble_lesson (
 lesson_id INT NOT NULL,
 gener VARCHAR(500) NOT NULL,
 minmum_number_of_student_n VARCHAR(500) NOT NULL,
 maximum_number_of_student_n VARCHAR(500) NOT NULL
);

ALTER TABLE ensemble_lesson ADD CONSTRAINT PK_ensemble_lesson PRIMARY KEY (lesson_id);


CREATE TABLE group_lesson (
 lesson_id INT NOT NULL,
 level_g VARCHAR(500) NOT NULL,
 instrument_g VARCHAR(500) NOT NULL,
 minmum_number_of_student_g VARCHAR(500) NOT NULL,
 maximum_number_of_student_g VARCHAR(500) NOT NULL
);

ALTER TABLE group_lesson ADD CONSTRAINT PK_group_lesson PRIMARY KEY (lesson_id);


CREATE TABLE individual_lesson (
 student_id INT NOT NULL,
 lesson_id INT NOT NULL,
 level_i VARCHAR(500) NOT NULL,
 instrument_i VARCHAR(500) NOT NULL
);

ALTER TABLE individual_lesson ADD CONSTRAINT PK_individual_lesson PRIMARY KEY (student_id,lesson_id);


CREATE TABLE students_in_ensemble_lessons (
 student_id INT NOT NULL,
 lesson_id INT NULL
);

ALTER TABLE students_in_ensemble_lessons ADD CONSTRAINT PK_students_in_ensemble_lessons PRIMARY KEY (student_id,lesson_id);


CREATE TABLE students_in_group_lessons (
 student_id INT NOT NULL,
 lesson_id INT NOT NULL
);

ALTER TABLE students_in_group_lessons ADD CONSTRAINT PK_students_in_group_lessons PRIMARY KEY (student_id,lesson_id);


ALTER TABLE phone ADD CONSTRAINT FK_phone_0 FOREIGN KEY (person_id) REFERENCES person (person_id);


ALTER TABLE student ADD CONSTRAINT FK_student_0 FOREIGN KEY (person_id) REFERENCES person (person_id);


ALTER TABLE Student_payment ADD CONSTRAINT FK_Student_payment_0 FOREIGN KEY (student_id_0) REFERENCES student (student_id);


ALTER TABLE mail ADD CONSTRAINT FK_mail_0 FOREIGN KEY (person_id) REFERENCES person (person_id);


ALTER TABLE parants ADD CONSTRAINT FK_parants_0 FOREIGN KEY (person_id) REFERENCES person (person_id);
ALTER TABLE parants ADD CONSTRAINT FK_parants_1 FOREIGN KEY (student_id) REFERENCES student (student_id);


ALTER TABLE instructor ADD CONSTRAINT FK_instructor_0 FOREIGN KEY (person_id) REFERENCES person (person_id);


ALTER TABLE InstructorPayment  ADD CONSTRAINT FK_InstructorPayment_0 FOREIGN KEY (instructor_id) REFERENCES instructor (instructor_id);


ALTER TABLE lesson ADD CONSTRAINT FK_lesson_0 FOREIGN KEY (instructor_id) REFERENCES instructor (instructor_id);


ALTER TABLE renting_instruments ADD CONSTRAINT FK_renting_instruments_0 FOREIGN KEY (student_id) REFERENCES student (student_id);
ALTER TABLE renting_instruments ADD CONSTRAINT FK_renting_instruments_1 FOREIGN KEY (instrument_id) REFERENCES instrument_in_stock (instrument_id);


ALTER TABLE availbility ADD CONSTRAINT FK_availbility_0 FOREIGN KEY (instructor_id) REFERENCES instructor (instructor_id);


ALTER TABLE ensemble_lesson ADD CONSTRAINT FK_ensemble_lesson_0 FOREIGN KEY (lesson_id) REFERENCES lesson (lesson_id);


ALTER TABLE group_lesson ADD CONSTRAINT FK_group_lesson_0 FOREIGN KEY (lesson_id) REFERENCES lesson (lesson_id);


ALTER TABLE individual_lesson ADD CONSTRAINT FK_individual_lesson_0 FOREIGN KEY (student_id) REFERENCES student (student_id);
ALTER TABLE individual_lesson ADD CONSTRAINT FK_individual_lesson_1 FOREIGN KEY (lesson_id) REFERENCES lesson (lesson_id);


ALTER TABLE students_in_ensemble_lessons ADD CONSTRAINT FK_students_in_ensemble_lessons_0 FOREIGN KEY (student_id) REFERENCES student (student_id);
ALTER TABLE students_in_ensemble_lessons ADD CONSTRAINT FK_students_in_ensemble_lessons_1 FOREIGN KEY (lesson_id) REFERENCES ensemble_lesson (lesson_id);


ALTER TABLE students_in_group_lessons ADD CONSTRAINT FK_students_in_group_lessons_0 FOREIGN KEY (student_id) REFERENCES student (student_id);
ALTER TABLE students_in_group_lessons ADD CONSTRAINT FK_students_in_group_lessons_1 FOREIGN KEY (lesson_id) REFERENCES group_lesson (lesson_id);

