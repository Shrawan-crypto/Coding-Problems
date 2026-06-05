# Write your MySQL query statement below
SELECT 
    user_id,

    ROUND(
        SUM(CASE WHEN activity_type = 'free_trial' THEN activity_duration ELSE 0 END)
        / NULLIF(SUM(CASE WHEN activity_type = 'free_trial' THEN 1 ELSE 0 END), 0),
    2) AS trial_avg_duration,

    ROUND(
        SUM(CASE WHEN activity_type = 'paid' THEN activity_duration ELSE 0 END)
        / NULLIF(SUM(CASE WHEN activity_type = 'paid' THEN 1 ELSE 0 END), 0),
    2) AS paid_avg_duration

FROM UserActivity
GROUP BY user_id
HAVING 
    SUM(activity_type = 'free_trial') > 0
    AND SUM(activity_type = 'paid') > 0
ORDER BY user_id;