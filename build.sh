#!/usr/bin/env bash

# Description : Build Project
# Author      : chench
# Date        : 2023-07-17

profile=$1
if [ ! -n "$profile" ]; then
    echo "Usage: sh $0 dev|test|prod [build_number]"
    echo "  e.g: sh build.sh dev"
    echo ""
    exit 1
fi

build_number=$2
if [ ! -n "$build_number" ]; then
	build_number=`date '+%Y%m%d%H%M%S'`
fi

config_src=src/main/resources/application.properties
config_tmp=src/main/resources/application.properties.tmp
startup_src=bin/startup.sh
startup_tmp=bin/startup.sh.tmp
static_dir=src/main/resources/public

#
# setup
#
setup() {
    echo "setup..."
    cp $config_src $config_tmp
    cp $startup_src $startup_tmp

    #if [ -d $static_dir ]; then
    #    rm -rf $static_dir
    #fi
    #mkdir $static_dir
    #cp -r ../digitaltwin-web/work/dist/* $static_dir
    rm -rf release
    echo "setup done."
}

#
# fix jmx host config
#
fixJmxHost() {
    echo "fix jmx host..."
    h=$1
    # find the row
    r=`sed -n '/^OPTS_JMX/p' $startup_src`
    # replace the row
    rp='OPTS_JMX="-Dcom.sun.management.jmxremote -Djava.rmi.server.hostname='$h' -Dcom.sun.management.jmxremote.port=8383 -Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote.ssl=false"'
    sed -i "s/^$r/$rp/g" $startup_src
    echo "fix jmx done."
}

#
# package jar
#
packageJar() {
    echo "package jar..."
    echo "spring.profiles.active=$profile" >> $config_src
    if [ "dev" == "$profile" ]; then
        echo "fix dev"
        #fixJmxHost 172.17.172.64
    fi

    if [ "integration" == "$profile" ]; then
        echo "fix integration"
        #fixJmxHost 172.17.170.215
    fi

    mvn clean package -Dmaven.test.skip=true -Dbuild.number=$build_number -P$profile
    echo "package jar done."
}

#
# clean
#
clean() {
    echo "clean..."
    mv -f $config_tmp $config_src
    mv -f $startup_tmp $startup_src
    #rm -rf $static_dir
    echo "clean done."
}

echo "Build start..."
setup
packageJar
clean
echo "Done."
