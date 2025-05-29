# Exercício 2 - Horas de Aula

## 1. Quantidade de horas que cada professor tem comprometido em aulas (SQL)

```
SELECT 
    p.id AS professor_id,
    p.name AS professor_name,
    SUM(TIME_TO_SEC(TIMEDIFF(cs.end_time, cs.start_time))/3600) AS total_hours
FROM 
    PROFESSOR p
JOIN 
    CLASS c ON p.id = c.professor_id
JOIN 
    CLASS_SCHEDULE cs ON c.id = cs.class_id
GROUP BY 
    p.id, p.name
ORDER BY 
    total_hours DESC;
```
## 2. Lista de salas com horários livres 

```
SELECT 
    r.id AS room_id,
    b.name AS building_name,
    r.name AS room_name,
    cs.day_of_week,
    cs.start_time,
    cs.end_time,
    s.name AS subject_name,
    p.name AS professor_name
FROM 
    ROOM r
JOIN 
    BUILDING b ON r.building_id = b.id
LEFT JOIN 
    CLASS_SCHEDULE cs ON r.id = cs.room_id
LEFT JOIN 
    CLASS c ON cs.class_id = c.id
LEFT JOIN 
    SUBJECT s ON c.subject_id = s.id
LEFT JOIN 
    PROFESSOR p ON c.professor_id = p.id
ORDER BY 
    r.id, cs.day_of_week, cs.start_time;
```