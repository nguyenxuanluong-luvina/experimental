
while (true) ; do
 mysql -u root -pinsight -e 'select * from information_schema.processlist' > /dev/null
done
