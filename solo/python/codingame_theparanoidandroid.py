nb_floors, width, nb_rounds, exit_floor, exit_pos, nb_total_clones, nb_additional_elevators, nb_elevators = [int(i) for i in raw_input().split()]

elevators = {}
elevators[exit_floor] = exit_pos
marvins = {}

for i in xrange(nb_elevators):
    elevator_floor, elevator_pos = [int(j) for j in raw_input().split()]
    elevators[elevator_floor] = elevator_pos

while 1:
    clone_floor, clone_pos, direction = raw_input().split()
    clone_floor = int(clone_floor)
    clone_pos = int(clone_pos)
    if clone_floor == -1 and clone_pos == -1 and direction == "NONE":
        print "WAIT"
    elif (direction == "LEFT" and elevators[clone_floor] <= clone_pos) \
            or (direction == "RIGHT" and elevators[clone_floor] >= clone_pos) \
            or marvins.get(clone_floor, -1) != -1:
        print "WAIT"
    else:
        print "BLOCK"
        marvins[clone_floor] = clone_pos
