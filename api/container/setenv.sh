
export JAVA_OPTS="$JAVA_OPTS -Ddatabase.driver=org.postgresql.Driver"
export JAVA_OPTS="$JAVA_OPTS -Ddatabase.dialect=org.hibernate.dialect.PostgreSQL9Dialect"
export JAVA_OPTS="$JAVA_OPTS -Ddatabase.url=jdbc:postgresql://${DB_URL}:5432/${DB_NAME}"
export JAVA_OPTS="$JAVA_OPTS -Ddatabase.user=${DB_USER}"
export JAVA_OPTS="$JAVA_OPTS -Ddatabase.password=${DB_PASSWORD}"
export JAVA_OPTS="$JAVA_OPTS -Dorg.slf4j.simpleLogger.defaultLogLevel=DEBUG"
export JAVA_OPTS="$JAVA_OPTS -Daws.accessKeyId=AKIA6KLK5SWBK4MYUJJ7"
export JAVA_OPTS="$JAVA_OPTS -Daws.secretKey=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"
export JAVA_OPTS="$JAVA_OPTS -Dspring.profiles.active=dev"
export JAVA_OPTS="$JAVA_OPTS -Dserver.port=8080"
export JAVA_OPTS="$JAVA_OPTS -Daws.sqs.name=blog_dev"
export JAVA_OPTS="$JAVA_OPTS -Daws.s3.bucketName=yyh-bucket1"
export JAVA_OPTS="$JAVA_OPTS -Daws.sqs.region=us-east-1"


