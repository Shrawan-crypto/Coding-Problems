# Write your MySQL query statement below
SELECT student_id, subject, first_score, latest_score
FROM (
    SELECT 
        student_id,
        subject,
        FIRST_VALUE(score) OVER (
            PARTITION BY student_id, subject
            ORDER BY exam_date ASC
        ) AS first_score,
        FIRST_VALUE(score) OVER (
            PARTITION BY student_id, subject
            ORDER BY exam_date DESC
        ) AS latest_score
    FROM Scores
) t
GROUP BY student_id, subject
HAVING latest_score > first_score
ORDER BY student_id, subject;