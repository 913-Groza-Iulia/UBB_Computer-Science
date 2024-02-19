#Find recursively in a directory, all the files that have write permissions for everyone. Display their names, and the 
#permissions before and after removing the write permission for everybody. You will need to use chmod's symbolic 
#permissions mode, instead of the octal mode we have used in class. The the chmod manual for details.

#!/bin/bash

permissions=`find -ls | awk '{print $3, $11}'`

step=0

for I in $permissions ; do
        if [ $((step%2)) -eq 0 ] ; then
                if [ `echo $I | grep -E ".*w.*w.*w.*" | wc -m` != 0 ] ; then
                        echo $I
                fi
        fi
        ((step++))
done
