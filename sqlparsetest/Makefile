all: gdatest

gdatest.o: gdatest.c
	gcc -c `pkg-config --cflags libgda-5.0` $<

gdatest: gdatest.o
	gcc -o gdatest $< `pkg-config --libs libgda-5.0`
