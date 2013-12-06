#!/bin/sh

for fn in `ls sqltext`;do
	./gdatest < sqltext/$fn | mongoimport -d wiperdog -c sqltext_parsed
done

