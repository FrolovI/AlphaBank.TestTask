.\gradlew jibDockerBuild --image=alpha-bank
docker run -p8080:8080 --name alpha-bank-container -d alpha-bank