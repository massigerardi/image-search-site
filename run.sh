#! /bin/bash
if [[ $#<1 ]]; then
	echo Please, use as following:
	echo run.sh {sitenane} 
	exit 1
fi
file=$1-config.yml
if [[ ! -f $file ]]; then
	echo please create configuration $file for $1
	exit 2
fi
echo running java
java -Duniquename=$1 -jar target/image-search-site-0.0.1-SNAPSHOT.jar server $file > $1-output.log &