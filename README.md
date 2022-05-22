# CalculatorJava
A simple expression parsing method implementation using java.
BTW, came up with this myself, thought this could be useful in applications with a UI.
Tell me if this was implemented earlier.


## IDs
  To differentiate and store the preference of operators while being efficient.
  (Could change in the near future.)

    ID  Type                    Metadata
    
    0:  Number
    1:  +
    2:  -
    3:  *
    4:  /
    5:  sin                     0: Normal sine; 1: sinh
    6:  cos                     ----
    7:  tan                     ----
    8:  log                     0: log10; -1: ln; (+ve number:used as a base for log)
    9:  !
    10: underoot                0: sqrt; (+ve number: used as 1/power)
    11: power
    12: (
    13: )

## Input style
Two arrays as an input:
  ### Array 1
    type: double
    size: any
    data: double values for numbers and optional metadata for the operators.
  ### Array 2
    type: int
    size: any
    data: IDs to differentiate between operators and numbers.
  ### Example
    Expression: 1.6/(4+2*3)*log6(2.3/3-1)
    Array 1: [1.6, 0,  0, 4, 0, 2, 0, 3,  0, 0, 6,  0, 2.3, 0, 3, 0, 1,  0]
    Array 2: [  0, 4, 12, 0, 1, 0, 3, 0, 13, 3, 8, 12,   0, 4, 0, 2, 0, 13]
