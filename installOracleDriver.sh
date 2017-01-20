#!/bin/bash

mvn install:install-file -DgroupId=com.oracle -DartifactId=ojdbc6 -Dversion=11.2.0.4 -Dpackaging=jar -Dfile=lib/Jars/ojdbc6.jar -DgeneratePom=true
