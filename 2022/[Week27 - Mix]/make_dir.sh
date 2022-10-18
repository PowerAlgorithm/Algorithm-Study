#!/bin/bash

study_member=(김민조 김주현 송민기 이승훈 황태환)

for name in "${study_member[@]}"
do
    if [ ! -d $name ]; then
        mkdir $name
    fi
    if [ ! -e $name/temp ]; then
        touch $name/temp
    fi
done

