#Find recursively in a directory, all the files with the extension ".log" and sort their lines (replace the original \
#file with the sorted content)

#!/bin/bash
find .dir/ -name "*.log" | while read filename
do
    sort $filename > output.txt
    mv output.txt $filename
done
