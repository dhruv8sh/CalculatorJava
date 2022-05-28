# customOperatorPrecedenceCals
A customizable expression parsing implementation using java.
### Uses:
  - Where the operator precedence is to be changed, or extra mathematical functions have to be implemented.
    (You can follow, SAMDOB instead of BODMAS, or any precedence you want.)
  - Where operators, expression, or anything has to be masked as a mathematical function.
    (Example: You can have the user show '+', but do '-' operation.
  - An application with a UI could be simplified using this, since there are no string parsing requirements(although available).

## IDs
  These are int values to differentiate between operators which also represent the operator precendence.
  
  <b>ID = 0 is finalized for numbers for simplicity.</b>
  
  ID < 0 are possible.
  
## Default IDs
  These are the default IDs for the abstract... (Don't override if you want to use these)
  

    ID  Type          isUnary          Metadata
    
    0:  Number           -
    1:  +                N
    2:  -                N
    3:  *                N
    4:  /                N
    5:  sin              Y               0: Normal sine; 1: sinh
    6:  cos              Y               ----
    7:  tan              Y               ----
    8:  log              Y               0: log10; -1: ln; (+ve number:used as a base for log)
    9:  !                Y
    10: invpowr          N               0: sqrt; (+ve number: used as 1/power)
    11: power            N
    12: (                Y
    13: )                -

## Input style
Two arrays as an input:
  ### Array 1
    type: double
    data: double values for numbers and optional metadata for the operators.
  ### Array 2
    type: int
    data: IDs to differentiate between operators and numbers.
  ### Example
    Expression: 1.6/(4+2*3)*log6(2.3/3-1)
    Array 1: [1.6, 0,  0, 4, 0, 2, 0, 3,  0, 0, 6, 2.3, 0, 3, 0, 1,  0]
    Array 2: [  0, 4, 12, 0, 1, 0, 3, 0, 13, 3, 8,   0, 4, 0, 2, 0, 14]
    
## Workflow
- [x] Common binary operators(+,-,*,/) support.
- [x] Support for default unary functions such as sin, cos, log, !(fact), etc.
- [x] Testing and finishing touches for the above.
- [x] Abstraction support with default definitions to implement customizability.
- [ ] Complete testing for operator precedence customizability.(Currently halfway done)
- [ ] Operator metadata support and testing.
- [ ] Read IDs from text file(csv).
- [ ] A string-to-input-format convertor.
- [ ] A default UI using using JavaFX.
