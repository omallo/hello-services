FROM openjdk:17-alpine3.14 as builder

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} application.jar
RUN java -Djarmode=layertools -jar application.jar extract

# ======================================================================

FROM openjdk:17-alpine3.14

COPY --from=builder dependencies/ ./
# empty COPY commented due to: https://github.com/moby/moby/issues/37965
#COPY --from=builder snapshot-dependencies/ ./
COPY --from=builder spring-boot-loader/ ./
COPY --from=builder application/ ./

ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]
