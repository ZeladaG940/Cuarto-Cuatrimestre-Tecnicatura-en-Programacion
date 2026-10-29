#NaN (Not a Number)
import math
from decimal import Decimal

a = float('nan')
print(f'a: {a}')

#Modulo match
a = float('nan')
print(f'Es de tipo NaN(Not a Number)?: {math.isnan(a)}')

#Modulo decimal
a = Decimal('nan')
print(f'Es de tipo NaN(Not a Number)a: {math.isnan(a)}')