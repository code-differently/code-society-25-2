SELECT g.id AS guest_id,
       g.first_name,
       g.last_name,
       coi.item_id,
       coi.checked_out_at,
       coi.due_at,
       coi.returned_at
FROM guests g
LEFT JOIN checked_out_items coi ON coi.guest_id = g.id
ORDER BY g.last_name, g.first_name;
