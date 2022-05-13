#!/bin/bash

#CLI arguments
psql_host=$1
psql_port=$2
db_name=$3
psql_user=$4
psql_password=$5

#Check # of args
if [ $# -ne  5 ]; then
  echo "host info requires a hostname, port number, database name, username, and password"
  exit 1
fi

#lscpu_out='lscpu'
hostname=$(hostname -f)

#Retrieve hardware specification variables
cpu_number=$(lscpu  | egrep "^CPU\(s\):" | awk '{print $2}' | xargs)
cpu_architecture=$(lscpu | egrep "Architecture" | awk '{print $2}' | xargs)
cpu_model=$(lscpu | egrep "\bModel\s\bname" | awk '{print $3, $4, $5}' | xargs)
cpu_mhz=$(lscpu  | egrep "\bCPU\s\MHz" | awk '{print $3}' | xargs)
l2_cache=$(lscpu | egrep "\bL2\s\bcache" | awk '{print $3}' | xargs)
total_mem=$(grep "^MemTotal" /proc/meminfo | awk '{ print $2 }')
timestamp=$(vmstat -t | awk '{print $18, $19}' | tail -n1| xargs)

#Insert server usage data into host_info table
insert_stmt="INSERT INTO host_info (hostname, cpu_number, cpu_architecture, cpu_model, cpu_mhz, l2_cache, total_mem, timestamp) VALUES ('$hostname', '$cpu_number', '$cpu_architecture', '$cpu_model', '$cpu_mhz', '$l2_cache', '$total_mem', '$timestamp');"

#set up environment variable for pql cmd
export PGPASSWORD=$psql_password

#Insert data into database
psql -h $psql_host -p $psql_port -d $db_name -U $psql_user -c "$insert_stmt"
exit 0
