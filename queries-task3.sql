
/*
task3-1
*/
SELECT
EXTRACT (MONTH FROM time_slot) AS the_month,
COUNT (lesson_id) AS lesson_per_month,
COUNT (CASE WHEN type_of_lesson LIKE '%individual lesson%' then 1 end) AS number_of_indiviuadual_lesson,
COUNT (CASE WHEN type_of_lesson LIKE '%group_lesson%' then 1 end) AS number_of_group_lesson,
COUNT (CASE WHEN type_of_lesson LIKE '%ensemble_lesson%' then 1 end) AS  number_of_ensemble_lesson
FROM lesson
WHERE EXTRACT (YEAR FROM time_slot ) = '2015'
GROUP BY the_month;





/*
task3-2

*/

SELECT  EXTRACT (YEAR FROM time_slot ) AS year1, 
ROUND (COUNT(lesson_id)/12::NUMERIC,2 )AS lesson,
ROUND (COUNT (CASE WHEN type_of_lesson LIKE '%individual lesson%' then 1 end)/12::NUMERIC,2) AS number_of_indiviuadual_lesson,
ROUND (COUNT (CASE WHEN type_of_lesson LIKE '%group_lesson%' then 1 end)/12::NUMERIC,2) AS number_of_group_lesson,
ROUND (COUNT (CASE WHEN type_of_lesson LIKE '%ensemble_lesson%' then 1 end)/12::NUMERIC ,2) AS  number_of_ensemble_lesson
FROM lesson 
WHERE EXTRACT (YEAR FROM time_slot ) = '2015'
GROUP BY year1
ORDER BY year1;





/*
task3-3
*/
SELECT p.first_name , p.last_name, COUNT (ls.instructor_id) AS number_per_lesson
FROM instructor AS i 
INNER JOIN person AS p 
ON i.person_id= p.person_id 
INNER JOIN lesson AS ls
ON i.instructor_id= ls.instructor_id
WHERE EXTRACT (MONTH FROM time_slot) = EXTRACT(MONTH FROM CURRENT_DATE) 
AND EXTRACT (YEAR FROM time_slot) = EXTRACT(YEAR FROM CURRENT_DATE)
GROUP BY ls.instructor_id,p.first_name,p.last_name
HAVING COUNT (ls.instructor_id)>0
ORDER BY number_per_lesson





/*
task 3_4
*/

Select EXTRACT('day' FROM l.time_slot) AS day , e.gener,CASE 
WHEN (SELECT COUNT(se.lesson_id) 
	 	FROM students_in_ensemble_lessons AS se
	 	WHERE l.lesson_id = se.lesson_id) 
			= 20 THEN ' full booked'
			WHEN (SELECT COUNT(se.lesson_id) 
	 	FROM students_in_ensemble_lessons AS se
	 	WHERE l.lesson_id = se.lesson_id) 
			=19 THEN '1-2 seats left'
			WHEN (SELECT COUNT(se.lesson_id) 
	 	FROM students_in_ensemble_lessons AS se
	 	WHERE l.lesson_id = se.lesson_id) 
			=18 THEN '1-2 seats left'
			 ELSE 'more seats left' END AS status

FROM lesson AS l 
INNER JOIN ensemble_lesson AS e
ON l.lesson_id=e.lesson_id
WHERE l.type_of_lesson ='ensemble lesson' AND  EXTRACT ('week' FROM l.time_slot ) = EXTRACT ('week' FROM CURRENT_DATE ) +1
ORDER BY e.gener, day;



