# rest-client-hello

Simple hello rest service implemented using Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

This image is public in [docker hub](https://hub.docker.com/r/rpardom/simple_rest/tags)

## Microservice

The service has a *hello* endpoint that answers with the current time.

### Packaging and running the application

The application can be packaged using `./mvnw package`.
It produces the `rest-client-hello-1.0.0-SNAPSHOT-runner.jar` file in the `/target` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/lib` directory.

The application is now runnable using `java -jar target/rest-client-hello-1.0.0-SNAPSHOT-runner.jar`

### Creating a native executable

You can create a native executable using: `./mvnw package -Pnative`.

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: `./mvnw package -Pnative -Dquarkus.native.container-build=true`.

You can then execute your native executable with: `./target/rest-client-hello-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/building-native-image-guide.

You can try it with:

```bash
curl localhost:8080/hello -v
```

## Docker image management

**Recommended**: just pull it :stuck_out_tongue_winking_eye:

```bash
docker pull rpardom/simple_rest:native
```

```bash
docker pull rpardom/simple_rest:jvm
```

You may want to push it to your own repo:
1. Build the image:
   ```bash
   docker build -f src/main/docker/Dockerfile.native -t simple_rest:native .
   ```

2. Retag the image with your repo prefix:

   ```bash
   docker tag simple_rest:wildfly rpardom/simple_rest:native
   ```

3. Push it to the repo:

   ```
   docker push rpardom/simple_rest:native
   ```

## Docker image use

Just use it as a service in your own `docker-compose`:

```yml
...
native:
        # This is a simple rest microservice that sends a hello response including the current time.
        # It has been built from a quarkus project using native image
        image: simple_rest:native
        container_name: perfromance_test_native
        ports:
            # To receive the rest requests to respond
            - 8090:8080
        networks:
            - perfromance_test

    jvm:
        # This is a simple rest microservice that sends a hello response including the current time.
        # It has been built from a quarkus project using a normal image.
        image: simple_rest:jvm
        container_name: perfromance_test_jvm
        ports:
            # To receive the rest requests to respond
            - 8091:8080
        networks:
            - perfromance_test
...
```