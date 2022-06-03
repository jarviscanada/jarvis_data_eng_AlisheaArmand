-- group hosts by hardware info
select cpu_number, id, total_mem
from host_info
group by cpu_number, id
order by cpu_number, total_mem desc ;


-- Average memory usage
CREATE FUNCTION round5(ts timestamp) RETURNS timestamp AS
$$
BEGIN
    RETURN date_trunc('hour', ts) + date_part('minute', ts):: int / 5 * interval '5 min';
END;
$$
    LANGUAGE PLPGSQL;

select host_id, hostname, round5(host_usage.timestamp) as rounded_timestamp ,  (((avg(total_mem - memory_free))/total_mem)*100) as avg_used_mem_percentage
from host_usage
inner join host_info on host_usage.host_id = host_info.id
group by rounded_timestamp, host_id, hostname, total_mem, memory_free;

-- detect host failure, uses round 5 function from average memory usage query
select host_id, round5(timestamp) as ts, count(timestamp) as num_data_points
from host_usage
group by host_id, ts
having count(timestamp) < 3