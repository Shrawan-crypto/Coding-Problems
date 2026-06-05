# Write your MySQL query statement below
WITH user_pairs AS (
    SELECT 
        p1.user_id,
        p1.product_id AS product1_id,
        p2.product_id AS product2_id
    FROM ProductPurchases p1
    JOIN ProductPurchases p2
        ON p1.user_id = p2.user_id
       AND p1.product_id < p2.product_id
)

SELECT 
    up.product1_id,
    up.product2_id,
    pi1.category AS product1_category,
    pi2.category AS product2_category,
    COUNT(DISTINCT up.user_id) AS customer_count
FROM user_pairs up
JOIN ProductInfo pi1
    ON up.product1_id = pi1.product_id
JOIN ProductInfo pi2
    ON up.product2_id = pi2.product_id
GROUP BY 
    up.product1_id,
    up.product2_id,
    pi1.category,
    pi2.category
HAVING COUNT(DISTINCT up.user_id) >= 3
ORDER BY 
    customer_count DESC,
    product1_id ASC,
    product2_id ASC;