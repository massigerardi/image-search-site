h1. image-search-site

a micro site for image search based on dropwizard 

h2. use

edit example.yml and add the following properties:
{code}
images: {absolute.path.for.image.folder}
host: {image host}
{code}

for example

{code}
images: /home/dart/contents/images/site01/
host: http://dart-archive.com/contents/images
{code}


run
{{java -jar target/image-search-site-0.0.1-SNAPSHOT.jar server example.yml}}

Image Search
{{http://localhost:8080/search/?image=image.absolute.path}}

Health Check
{{http://localhost:8081}}


