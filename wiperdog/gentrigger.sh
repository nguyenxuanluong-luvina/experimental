#!/bin/sh
WIPERDOGDIR=$HOME/work/wiperdog-0.2.2-SNAPSHOT
JOBDIR=$WIPERDOGDIR/var/job
# rm -f ${JOBDIR}/all.trg
cd ${JOBDIR}
# for cn in MySQL Postgres SQL_Server;do
for cn in MySQL Postgres;do
  for fn in ${cn}*.job;do
	jobname=`echo $fn | sed -s 's/\\.job//'`
cat <<EOF_CONTENTS > ${jobname}.instances
[
	test: [ schedule: "60i" ]
]
EOF_CONTENTS

  done
done
