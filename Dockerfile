FROM sbtscala/scala-sbt:eclipse-temurin-jammy-19.0.1_10_1.9.1_3.3.0

WORKDIR /reversi
ADD . /reversi
CMD sbt run

