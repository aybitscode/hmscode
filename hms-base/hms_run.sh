#!/bin/sh
echo "Starting HMS Application.."

export classpath=lib/*.jar:$classpath
export path=conf/:$path

java -cp lib/hms-main-api.jar:lib/hms-domain-api.jar:lib/hms-rest-api.jar:lib/hms-domain-deps.jar:lib/mysql-connector-java-5.1.46.jar com.aybits.hms.init.HmsRestServiceMain