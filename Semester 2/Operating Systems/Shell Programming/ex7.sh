#Consider a file containing a username on each line. Generate a comma-separated string with email addresses of the users
#that exist. The email address will be obtained by appending "@scs.ubbcluj.ro" at the end of each username. Make sure 
#the generated string does NOT end in a comma.

#!/bin/bash
names=`cat names.txt`

name_count=`wc names.txt | awk '{print $1}'`
counter=1

for I in $names ; do
        email=""
        email+=$I
        email+="@scs.ubbcluj.ro"

        echo -n $email

        if [ $counter != $name_count ] ; then
                echo -n ","
        fi

        ((counter++))
done
echo ""
