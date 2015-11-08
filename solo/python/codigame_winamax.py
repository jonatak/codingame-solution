import sys
import math

# Auto-generated code below aims at helping you parse
# the standard input according to the problem statement.

cards = {
    'J':11,
    'Q':12,
    'K':13,
    'A':14
}

deck_1 = []
deck_2 = []

n = int(raw_input()) # the number of cards for player 1
for i in xrange(n):
    cardp_1 = raw_input()[:-1] # the n cards of player 1
    deck_1.append(cards.get(cardp_1, cardp_1))
    deck_1 = [int(i) for i in deck_1]
m = int(raw_input()) # the number of cards for player 2
for i in xrange(m):
    cardp_2 = raw_input()[:-1] # the m cards of player 2
    deck_2.append(cards.get(cardp_2, cardp_2))
    deck_2 = [int(i) for i in deck_2]

#print deck_1, deck_2
winner=0
turn=0

def war(pile1, deck_1, pile2, deck_2):
    if len(deck_1) <= 3 or len(deck_2) <= 3:
        return [], []
    pile1, pile2 = pile1 + deck_1[:3], pile2 + deck_2[:3]
    deck_1, deck_2 = deck_1[3:], deck_2[3:]
    card1, card2 = deck_1[:1], deck_2[:1]
    pile1, pile2 = pile1 + card1, pile2 + card2
    deck_1, deck_2 = deck_1[1:], deck_2[1:]
    if card1 == card2:
        return war(pile1, deck_1, pile2, deck_2)
    elif card1 > card2:
        deck_1 = deck_1 + pile1 + pile2
    else:
        deck_2 = deck_2 + pile1 + pile2
    return deck_1, deck_2

while len(deck_1) > 0 and len(deck_2) > 0:
    card1, card2 = deck_1[:1], deck_2[:1]
    deck_1, deck_2 = deck_1[1:], deck_2[1:]
    if card1 == card2:
        deck_1, deck_2 = war(card1, deck_1, card2, deck_2)
    elif card1 > card2:
        deck_1 = deck_1 + card1 + card2
    else:
        deck_2 = deck_2 + card1 + card2
    turn += 1
    
if len(deck_1) > 0 and len(deck_2) == 0:
    print 1, turn
elif len(deck_2) > 0 and len(deck_1) == 0:
    print 2, turn
else:
    print "PAT"