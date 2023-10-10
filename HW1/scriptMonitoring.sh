LOGSFILENAME=logs.csv
isRunning=$(cat $INFOFILENAME)
echo $$
while [ $isRunning == true ]
    do  
        isRunning=$(cat $INFOFILENAME)
        CPU=$(ps -A -o %cpu | awk '{s+=$1} END {print s "%"}')
        timestamp=$(date +%s )
        res=$timestamp\;$CPU
        echo $res >> $LOGSFILENAME
        sleep $TIMESLEEP
    done 