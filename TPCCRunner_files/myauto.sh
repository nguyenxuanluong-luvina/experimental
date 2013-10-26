#!/bin/sh
echo '#!/bin/sh' > kill.sh
chmod  +x kill.sh

mysql -uroot -pinsight -vvv -n < sql/example/mysql/create_database.sql
mysql -uroot -pinsight -vvv -n < sql/example/mysql/create_table.sql
java -cp bin/:lib/mysql-connector-java-5.1.7-bin.jar iomark.TPCCRunner.Loader conf/example/mysql/loader.properties
mysql -uroot -pinsight -vvv -n < sql/example/mysql/create_index.sql

echo java -cp bin/:lib/mysql-connector-java-5.1.7-bin.jar iomark.TPCCRunner.Master conf/example/mysql/master.properties
java -cp bin/:lib/mysql-connector-java-5.1.7-bin.jar iomark.TPCCRunner.Master conf/example/mysql/master.properties > master.log 2>&1 &
masterPid=$!
echo 'kill -9 '$masterPid >> kill.sh

sleep 10

echo java -cp bin/:lib/mysql-connector-java-5.1.7-bin.jar iomark.TPCCRunner.Slave conf/example/mysql/slave1.properties
java -cp bin/:lib/mysql-connector-java-5.1.7-bin.jar iomark.TPCCRunner.Slave conf/example/mysql/slave1.properties  > slave1.log 2>&1 &

echo 'kill -9 '$! >> kill.sh

echo java -cp bin/:lib/mysql-connector-java-5.1.7-bin.jar iomark.TPCCRunner.Slave conf/example/mysql/slave2.properties
java -cp bin/:lib/mysql-connector-java-5.1.7-bin.jar iomark.TPCCRunner.Slave conf/example/mysql/slave2.properties > slave2.log 2>&1 &

echo 'kill -9 '$! >> kill.sh

wait $masterPid

