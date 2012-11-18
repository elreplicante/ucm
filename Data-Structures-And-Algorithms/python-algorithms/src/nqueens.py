## {{{ http://code.activestate.com/recipes/576647/ (r3)

from itertools import permutations
import time
n = 8
cols = range(n)
sol = []
t0 = time.clock()
for vec in permutations(cols):
    if (n == len(set(vec[i] + i for i in cols))
          == len(set(vec[i] - i for i in cols))):
        sol.append(vec)
print (str(time.clock() - t0) + " segundos") 
print(sol)