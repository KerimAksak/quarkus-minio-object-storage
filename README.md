# MinIO & Quarkus

[MinIO](https://min.io/) is a Multi-Cloud Object is storage. Offers high-performance, S3 compatible object storage.

In our Quarkus project, we were connected to the server with MinIO [SDKs](https://docs.min.io/) and file operations were done.

Backend and frontend were created using [Quarkus](https://quarkus.io/) and React in the project.

![-----------------------------------------------------](https://raw.githubusercontent.com/andreasbm/readme/master/assets/lines/aqua.png)

## Running the application in dev mode

### MinIO Server

```shell script
cd src/main/resources/docker
docker-compose -f minio-compose.yml up -d
```

### Backend

You can run your application in dev mode that enables live coding using:

```shell script
./mvnw quarkus:dev
```

> **_NOTE:_** Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/

### Frontend

You can run your application in dev mode that enables live coding using:

```shell script
cd web
npm install
npm run dev
```

and open to [http://localhost:3000](http://localhost:3000)

> **_NOTE:_** You must refresh the page after uploading and deleting through the browser..

![-----------------------------------------------------](https://raw.githubusercontent.com/andreasbm/readme/master/assets/lines/aqua.png)

To track uploaded files via MinIO, go to http://localhost:9000

> **_NOTE:_** MinIO server credentials are in minio-compose.yml. (MINIO_ROOT_USER - MINIO_ROOT_PASSWORD)
