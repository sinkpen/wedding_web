#!/bin/sh

for x in `cat /tmp/codes.txt`; 
do
	convert -density 300 -size 2550x3300 xc:white  -font Courier -pointsize 24 -draw "text 1720,2200 '$x'" text_draw_$x.pdf;
	lpr -P HL2270DW text_draw_$x.pdf
done
