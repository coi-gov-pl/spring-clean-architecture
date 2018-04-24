SELECT p
FROM PersonData p
WHERE
  p.name = :name
  AND p.surname = :surname
