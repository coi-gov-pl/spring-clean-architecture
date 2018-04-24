SELECT p
FROM PersonData p
  LEFT JOIN FETCH p.ownerships os
  LEFT JOIN FETCH os.pet pt
  LEFT JOIN FETCH pt.formerOwners fo
WHERE
  p.name = :name
  AND p.surname = :surname
