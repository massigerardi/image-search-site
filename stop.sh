#! /bin/bash
id=`ps ax | grep uniquename=$1 | grep -v grep | awk '{print $1}'`
if [[ -z "$id" ]]; then
	echo no process with identifier $1 found
	exit 1
fi
echo stopping process $1 with id $id
kill -9 $id