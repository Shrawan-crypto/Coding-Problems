# Write your MySQL query statement below
WITH ranked_reviews AS (
    SELECT
        pr.employee_id,
        pr.review_date,
        pr.rating,
        ROW_NUMBER() OVER (
            PARTITION BY pr.employee_id
            ORDER BY pr.review_date DESC
        ) AS rn
    FROM performance_reviews pr
),

last_three AS (
    SELECT *
    FROM ranked_reviews
    WHERE rn <= 3
),

ordered AS (
    SELECT
        employee_id,
        review_date,
        rating,
        ROW_NUMBER() OVER (
            PARTITION BY employee_id
            ORDER BY review_date ASC
        ) AS seq
    FROM last_three
),

checked AS (
    SELECT
        employee_id,
        MAX(CASE WHEN seq = 1 THEN rating END) AS r1,
        MAX(CASE WHEN seq = 2 THEN rating END) AS r2,
        MAX(CASE WHEN seq = 3 THEN rating END) AS r3
    FROM ordered
    GROUP BY employee_id
    HAVING 
        COUNT(*) = 3
        AND r1 < r2
        AND r2 < r3
)

SELECT
    e.employee_id,
    e.name,
    (c.r3 - c.r1) AS improvement_score
FROM checked c
JOIN employees e
    ON e.employee_id = c.employee_id
ORDER BY
    improvement_score DESC,
    e.name ASC;