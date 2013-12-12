#!/bin/sh

if [ ! -d many ];then
  mkdir many
fi

index=1
while [ $index -lt 20000 ];do
  for fn in *.sql;do
    cp -f $fn many/${index}_$fn
    index=`expr $index + 1`
  done
done
