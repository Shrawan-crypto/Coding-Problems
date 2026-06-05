# Write your MySQL query statement below
WITH first_positive AS (
    SELECT
        patient_id,
        MIN(test_date) AS first_positive_date
    FROM covid_tests
    WHERE result = 'Positive'
    GROUP BY patient_id
),

first_negative_after_positive AS (
    SELECT
        c.patient_id,
        MIN(c.test_date) AS first_negative_date
    FROM covid_tests c
    JOIN first_positive p
        ON c.patient_id = p.patient_id
    WHERE c.result = 'Negative'
      AND c.test_date > p.first_positive_date
    GROUP BY c.patient_id
),

recovered AS (
    SELECT
        p.patient_id,
        pat.patient_name,
        pat.age,
        DATEDIFF(n.first_negative_date, p.first_positive_date) AS recovery_time
    FROM first_positive p
    JOIN first_negative_after_positive n
        ON p.patient_id = n.patient_id
    JOIN patients pat
        ON pat.patient_id = p.patient_id
)

SELECT *
FROM recovered
ORDER BY recovery_time ASC, patient_name ASC;