import sys
from math import ceil

# Auto-generated code below aims at helping you parse
# the standard input according to the problem statement.

 # w: width of the building.
 # h: height of the building.
w, h = [int(i) for i in raw_input().split()]
n = int(raw_input()) # maximum number of turns before game over.
x0, y0 = [int(i) for i in raw_input().split()]
# game loop
w, h = w, h
minx, miny = 0, 0
maxx, maxy = w, h

while 1:
    bomb_dir = raw_input() # the direction of the bombs from batman's current location (U, UR, R, DR, D, DL, L or UL)
    
    if "U" in bomb_dir:
        maxy = y0
        y0 = y0 - max(1, int(round(float(maxy - miny) /  2)))
    if "R" in bomb_dir:
        minx = x0
        x0 = x0 + max(1, int(round(float(maxx - minx) / 2)))
    if "D" in bomb_dir:
        miny = y0
        y0 = y0 + max(1, int(round(float(maxy - miny) / 2)))
    if "L" in bomb_dir:
        maxx = x0
        x0 = x0 - max(1, int(round(float(maxx - minx) / 2)))
    n = n - 1
    print x0, y0
