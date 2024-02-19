#Write a script that receives dangerous program names as command line arguments. The script will monitor all 
#the processes in the system, and whenever a program known to be dangerous is run, the script will kill it and 
#display a message.

#!/bin/bash
for I in $@ ; do
        processName=`ps | tail -n +2 | awk '{print $4}'`
        for J in $processName ; do
                if `test $I == $J` ; then
                        pkill $J
                fi
        done
done
