-- schema.sql
DROP PROCEDURE IF EXISTS GetCompanies;
DELIMITER //

CREATE PROCEDURE GetCompanies(
    IN activeStatus BOOLEAN,
    IN pageNum INT,
    IN pageSize INT
)
BEGIN
    SET @offset = (pageNum - 1) * pageSize;

    SELECT *
    FROM company
    WHERE (activeStatus IS NULL OR is_active = activeStatus)
    LIMIT pageSize OFFSET @offset;
END //

DELIMITER ;
