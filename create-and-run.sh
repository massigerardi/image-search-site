#! /bin/bash
if [[ $#<4 ]]; then
	echo Please, use as following:
	echo create-and-run.sh {sitenane} {imagefolder} {port} {adminport}
	exit 1
fi
echo creating configuration $1-config.yml
sed -e "s/SITE/$1/g" -e "s/FOLDER/$2/g" -e "s/ADMINPORT/$4/g" -e "s/PORT/$3/g" config-template.yml > $1-config.yml
echo running java with output redirected to $1-output.log
java -Duniquename=$1 -jar target/image-search-site-0.0.1-SNAPSHOT.jar server $file > $1-output.log &