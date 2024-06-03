
  
In more complex queries where you can't know your min or max value that you'll be going to compare to beforehand, then using BETWEEN is almost equivalent to using the >= and <= operators.

But when you know beforehand exactly what your min and max values are, it is cheaper not to use the BETWEEN operator and instead compare the wanted results to 1 number below the min and 1 number above the max.