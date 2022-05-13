DROP DATABASE IF EXISTS host_agent;
CREATE DATABASE host_agent;
\c host_agent;

--DROP TABLE IF EXISTS host_info CASCADE;

CREATE TABLE IF NOT EXISTS host_info (
	id			    SERIAL NOT NULL,
	hostname		VARCHAR (100) NOT NULL,
	cpu_number		INT NOT NULL,
	cpu_architecture	VARCHAR (100) NOT NULL,
	cpu_model		VARCHAR (100) NOT NULL,
	cpu_mhz			NUMERIC NOT NULL,
	L2_cache		VARCHAR(100) NOT NULL,
	total_mem		INT NOT NULL,
	timestamp		TIMESTAMP NOT NULL,
	PRIMARY KEY     (id)

);

--INSERT INTO host_info (id, hostname, cpu_number, cpu_architecture, cpu_model, cpu_mhz, L2_cache, total_mem, "timestamp");

--DROP TABLE IF EXISTS host_usage;

CREATE TABLE IF NOT EXISTS host_usage (
	timestamp		TIMESTAMP NOT NULL,
	host_id			INT NOT NULL,
	memory_free		INT NOT NULL,
	cpu_idle		INT NOT NULL,
	cpu_kernel		INT NOT NULL,
	disk_io			INT NOT NULL,
	disk_available		VARCHAR (100) NOT NULL,
	FOREIGN KEY     (host_id)
	REFERENCES      host_info(id)

);

--INSERT INTO host_usage ("timestamp", host_id, memory_free, cpu_idle, cpu_kernel, disk_io, disk_available);



