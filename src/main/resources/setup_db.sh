#!/bin/sh

user="root"
password="\n"
database="influential_people"
host="localhost"
port=3306

if [ ! -z "$1" ]; then
   user=$1
fi

if [ ! -z "$2" ]; then
    password=$2
fi

if [ ! -z "$3" ]; then
    host=$3
fi

if [ ! -z "$4" ]; then
    port=$4
fi

mysql -h ${host} -u ${user} -p${password} < init.sql
mysql -h ${host} -u ${user} -p${password} ${database} < schema-mysql.sql
echo "script executed"



