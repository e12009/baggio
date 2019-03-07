#!/usr/bin/env sh

sh gradlew clean
echo 'Hacker clean build info'
sleep 1

sh gradlew build
echo 'Hacker build finished'
sleep 2

adb install -r app/build/outputs/apk/debug/app-debug.apk
echo 'Hacker installed successfully'
sleep 2

adb shell stop
echo 'Hacker stop android framework'
sleep 2

adb shell start
echo 'Hacker start android framework'

echo 'Hacker deploy successfully'