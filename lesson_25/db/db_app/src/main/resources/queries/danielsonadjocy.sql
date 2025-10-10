select type , COUNT(*) as count 
from mediaItems group by type;

select SUM(pages) as total_pages 
from checked_out_items INNER JOIN mediaItems 
ON checked_out_items.item_id = mediaItems.id 
where mediaItems.pages IS NOT NULL;


select guests.id, guests.name, checked_out_items.item_id, checked_out_items.checked_out_date
from guests
left join checked_out_items on guests.id = checked_out_items.guest_id;