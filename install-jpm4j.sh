#!/bin/sh
curl -sL http://bit.ly/jpm4j >jpm4j.jar
sudo java -jar jpm4j.jar -g init
jpm install com.codacy:codacy-coverage-reporter:assembly
