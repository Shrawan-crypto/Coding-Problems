# Write your MySQL query statement below
SELECT 
    sample_id,
    dna_sequence,
    species,

    -- starts with ATG
    CASE 
        WHEN dna_sequence REGEXP '^ATG' THEN 1 
        ELSE 0 
    END AS has_start,

    -- ends with TAA or TAG or TGA
    CASE 
        WHEN dna_sequence REGEXP '(TAA|TAG|TGA)$' THEN 1 
        ELSE 0 
    END AS has_stop,

    -- contains ATAT
    CASE 
        WHEN dna_sequence REGEXP 'ATAT' THEN 1 
        ELSE 0 
    END AS has_atat,

    -- contains 3+ consecutive G
    CASE 
        WHEN dna_sequence REGEXP 'GGG+' THEN 1 
        ELSE 0 
    END AS has_ggg

FROM Samples
ORDER BY sample_id;