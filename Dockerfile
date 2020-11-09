FROM openjdk:11

ENV APP_HOME "/opt/app"

ADD ./target/transaction-routine-0.0.1-SNAPSHOT.jar $APP_HOME/transaction-routine-0.0.1-SNAPSHOT.jar

CMD	java -Xmx512M -Xms256M -jar /opt/app/transaction-routine-0.0.1-SNAPSHOT.jar