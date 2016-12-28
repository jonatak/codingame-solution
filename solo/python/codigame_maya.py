import math
from itertools import izip, izip_longest

def grouper(iterable, n, fillvalue=None):
    args = [iter(iterable)] * n
    return list(izip_longest(*args, fillvalue=fillvalue))

def get_number(number, symbol):
    res = 0
    for index, elem in enumerate(reversed(number)):
        n = symbol.index(elem)
        res += n * pow(20, index)
    return res

def get_base_twenty(number):
    res = number
    li = []
    while res:
        li.append(res % 20)
        res = res / 20
    if len(li) == 0:
        li.append(0)
    return li

l, h = [int(i) for i in raw_input().split()]

symbol = [tuple("".join(e) for e in i) for i in izip(*[grouper(raw_input(), l) for a in xrange(h)])]

s1 = int(raw_input())
number1 = list(grouper([raw_input() for i in xrange(s1)], h))

s2 = int(raw_input())
number2 = list(grouper([raw_input() for i in xrange(s2)], h))

operation = raw_input()

n1 = get_number(number1, symbol)
n2 = get_number(number2, symbol)

res = eval(str(n1)+operation+str(n2))

for number in reversed(get_base_twenty(res)):
    for i in symbol[number]:
        print i
