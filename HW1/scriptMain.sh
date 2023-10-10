TIMESLEEP=3
COLUMN1=timestamp
COLUMN2=CPU
STATUS=$1
LOGSFILENAME=logs.csv
INFOFILENAME=info.txt
isRunning=true

if [[ ! -f "$INFOFILENAME" ]] && [ "$STATUS" != "START" ]
then
echo doesn\'t works
exit
elif [[ ! -f "$INFOFILENAME" ]] && [ "$STATUS" = "START" ]
then
echo false > $INFOFILENAME
fi

case $STATUS in 
        "START")
            if [ $(cat $INFOFILENAME) == "true" ]
            then
            echo still works
            else
            isRunning=true
            echo $isRunning > $INFOFILENAME
            . ./scriptMonitoring.sh &
            echo $COLUMN1\;$COLUMN2 > $LOGSFILENAME
            fi
            ;;
        "STATUS")
            if [ $(cat $INFOFILENAME) == "true" ]
            then
            echo is working
            else
            echo doesn\'t works
            fi
            ;;
        "STOP")
            if [ $(cat $INFOFILENAME) == "false" ]
            then
            echo can\'t stop because it doesn\'t works
            else
            isRunning=false
            echo $isRunning > $INFOFILENAME
            fi
            ;;
        *)
            echo wrong status
        ;;
esac