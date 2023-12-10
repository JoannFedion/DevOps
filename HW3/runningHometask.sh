#!/bin/sh
docker-compose build
docker-compose up &
cd ansible
ansible-playbook -i inventory.ini playbook.yaml