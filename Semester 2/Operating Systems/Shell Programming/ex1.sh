#Display a report showing the full name of all the users currently connected, and the number of processes belonging to each of them.

#!bin/bash

for I in `cat who.fake | awk '{print $1}'`; do
    counter=0
    for J in `cat ps.fake | awk '{print $1}'` ; do
          if `test "$I" == "$J"` ; then
                counter=`expr $counter+1`
          fi
    done
    echo -n $I
    echo -n " "
    echo $counter
done
