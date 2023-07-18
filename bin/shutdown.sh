#!/bin/bash

# Description : do shutdown
# Author      : chench
# Date        : 2023-07-17

echo "Shutdown ..."

#current_path=`pwd`
case "`uname`" in
	Linux)
		bin_absolute_path=$(readlink -f $(dirname $0)) 
		;;
	*)
		bin_absolute_path=`cd $(dirname $0);pwd`
		;;
esac

export LANG=en_US.UTF-8
base_dir=${bin_absolute_path}/..
pid_file=$base_dir/bin/pid
#pid_file=/var/run/dt.pid

#curl -X POST http://127.0.0.1:8000/ops/shutdown
curl -X POST http://127.0.0.1:8081/ops/shutdown --connect-timeout 3 --max-time 5

# check process exists
count=`ps -ef |grep SpringbootScaffoldingApplication |grep -v "grep" |wc -l`
if [ $count -gt 0 ]; then
    if [ -f "$pid_file" ]; then
        pid=`cat $pid_file`
        kill -15 $pid
    else
        pid=`ps -ef |grep SpringbootScaffoldingApplication |grep -v "grep"| awk '{print $2}'`
        kill -15 $pid
    fi
fi

rm -rf /tmp/tomcat*
rm -rf $pid_file

echo ""
echo "Done."
