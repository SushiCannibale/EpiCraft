#!/bin/sh

[ $# != 2 ] && exit 1


jar=
case "$1" in
    proxy)
        jar="proxy/velocity-3.4.0-SNAPSHOT-450.jar"
        ;;
    jump)
        jar=server/jump/paper-1.21.1-131.jar
        ;;
    uhc)
        jar=server/uhc/paper-1.21.1-131.jar
        ;;
    thimble)
        jar=server/thimble/paper-1.21.1-131.jar
        ;;
    *)
        exit 1
        ;;
esac

java -Xms4G -Xmx4G -jar $jar --nogui