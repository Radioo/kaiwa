FROM maven:3.9.6-eclipse-temurin-21

COPY . /app

RUN apt-get update

RUN apt-get install -y \
    curl \
    ca-certificates \
    gnupg

RUN mkdir -p /etc/apt/keyrings
RUN curl -fsSL https://deb.nodesource.com/gpgkey/nodesource-repo.gpg.key | gpg --dearmor -o /etc/apt/keyrings/nodesource.gpg
RUN echo "deb [signed-by=/etc/apt/keyrings/nodesource.gpg] https://deb.nodesource.com/node_21.x nodistro main" | tee /etc/apt/sources.list.d/nodesource.list

RUN apt-get update && apt-get install -y \
    nodejs

RUN npm install --global yarn

WORKDIR /app

RUN yarn install
RUN yarn mix --production

RUN mvn clean package -DskipTests

ENTRYPOINT ["java","-jar","/app/target/demo-0.0.1-SNAPSHOT.jar"]