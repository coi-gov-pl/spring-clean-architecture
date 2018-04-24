SELECT p
FROM PetData p
  LEFT JOIN FETCH p.ownership o
  LEFT JOIN FETCH o.person pp
ORDER BY p.id ASC
