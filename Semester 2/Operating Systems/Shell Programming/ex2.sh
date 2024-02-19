#Find recursively in a directory all ".c" files having more than 500 lines. Stop after finding 2 such files.

#!/bin/bash

count=0
find ./dir -name "*.c" | while read filename
do
   lines=`wc -l < "$filename"`
   if [lines -gt 500]
   then
      echo "$filename has more than 500 lines."
      ((count++))
    fi

    if [$count -eq 2]
    then
      echo "Found 2 files.Exiting..."
    fi
done
echo "Done searching"

    
