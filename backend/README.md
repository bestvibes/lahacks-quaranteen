## Local Installation
1) clone the repo
2) enter the repo
3) run `docker-compose up -d`
4) exit using Ctrl-C or `docker-compose down`


## Execute Django Commands
`docker-compose run web python manage.py [CMD]`


## Create/Run Data Migrations
1) `docker-compose up -d`
2) `docker ps`, note container ID of backend_web image
3) `docker exec -it [CONTAINER_ID] bash`
4) `python manage.py makemigrations`, only necessary if changes made to models
5) `python manage.py migrate`
6) `exit` and `docker-compose down` whenever finished with application
