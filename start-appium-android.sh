#!/bin/bash

function die {
    echo $1
    exit 1
}

app_filename="/lib/Android/CATS-Mobility.apk"

appium --pre-launch --app-pkg net.fulcrum.mobility --app-activity .activities.LoginActivity --platform-name Android --app $PWD$app_filename
