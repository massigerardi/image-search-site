#! /bin/bash
if [[ $#<4 ]]; then
	echo Please, use as following:
	echo create-site.sh {sitenane} {imagefolder} {port} {adminport}
	exit 1
fi
sed -e "s/SITE/$1/g" -e "s/FOLDER/$2/g" -e "s/ADMINPORT/$4/g" -e "s/PORT/$3/g" config-template.yml > $1-config.yml