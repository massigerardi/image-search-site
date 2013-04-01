#! /bin/bash
sed -e "s/SITE/$1/g" -e "s/FOLDER/$2/g" config-template.yml > $1-config.yml