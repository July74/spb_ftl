#!/bin/bash
PIDS=`ps -ef|grep java |grep spt_ftl|awk '{print $2}'`
if [ -z "$PIDS" ]; then
    echo "ERROR: The Service does not started!"
fi

echo -e "Stopping the Service... \n"
for PID in $PIDS ; do
    kill $PID > /dev/null 2>&1
done

echo "mv jar file"
cp ~/spt_ftl.jar /data/spt_ftl/
cd /data/smrabbit

echo  "current dir:" $(pwd)
echo "start service"
JAVA_OPTS=" -server -Xms256m -Xmx256m -XX:MetaspaceSize=32m -verbose:gc -Xloggc:/data/spt_ftl/spt_ftl.log "
nohup java $JAVA_OPTS -jar spt_ftl.jar > /data/smrabbit/console.log 2>&1 &