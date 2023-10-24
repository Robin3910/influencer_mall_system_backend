FROM   amazoncorretto:11.0.20-alpine3.18

RUN mkdir -p /java/data

# RUN /bin/cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && echo 'Asia/Shanghai' >/etc/timezone

WORKDIR /java/data

ENTRYPOINT ["/bin/sh","-c","java -Dspring.profiles.active=prod -jar /data/java/mall-admin-1.0-SNAPSHOT.jar --server.port=9527"]

