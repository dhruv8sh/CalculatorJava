# customOperatorPrecedenceCalc
An expression parsing implementation to change operator precedence, mask operators using java.
### Uses:
  - Where the operator precedence is to be changed, or extra mathematical functions have to be implemented.
    (You can follow, SAMDOB instead of BODMAS, or any precedence you want.)
  - Where operators, expression, or anything has to be masked as a mathematical function.
    (Example: You can have the user show '>' to the end user, but do 'sin()' operation instead.)
  - An application with a UI could be simplified using this, since there are no string parsing requirements(although available).

# ID
- ID is an int array.
- Differentiates corresponding numbers and operators.
- Defines their precendence (more the value more the precedence). Example: '+' is given 10 and '-' is given 11, therefore, '-' has precedence over '+'.
- ID = 0 are preserved for numbers, meaning, numbers will have their ID = 0. This was done for simplicity.
- ID < 0 is possible, but it would make no difference for numbers, since IDs are for operator precedence only.

# Metadata/Mode controlling (Underway, not yet commited!)
> Only available for Unary functions.
- Not recommended, as this results in branching of switch/if statements in the code written by you.
Let's say you have to use sin, sinh, asin all at one but have to club them together under the same ID to prevent connfusion. The metadata/mode control is
just a fancy name for the use of value that is stored at the same index in corresponding double array as the ID for the operator. This helps in use of that
empty space and clubbing of operators. This values can be mapped to those functions.


# Input formats
## Default input style(Recommended)
  - Takes `one double and one int array` as inputs.
  - Unary functions start with their respective ID and end with the ID of ')'. (No opening paranthesis is used.)
  - Even Unary functions such as factorial, whose, sign is usually at the end of the number to calculate the factorial, must be of the above format.
  - The following examples show the exact format:

              Expression:  6 + 4 sin( 3  )
              double arr: [6,0,4,   0,3, 0]
              int arr:    [0,2,0,   5,0,13]
              
              
              Expression:  6 + 10 * 9
              double arr: [6,0,10,0,9]
              int arr:    [0,2, 0,3,0]
              
              
              Expression:  4  ( 2  )
              double arr: [4, 0,2, 0]
              int arr:    [0,12,0,13]
              
  - Here, the double array has the corresponding double values, and metadata(explained later) for the expression.
  - Whereas, int array has the corresponding IDs of the operaators used.
  - Note how the beggining paranthesis is clubbed with sin operator and not given an extra space.
  - However if the beggining paranthesis was alone, it would've been given it's own ID.
  - The space in double array wherever ID!=0 is reserved for a future feature, namely, metadata/mode control.

## String input style(Not implemented yet!)
  - Nothing just call `parse(String)` with your expression as a String.
  - Easy but slower because this just maps the string for `parse(double[], int[])` and then calls it.
  - You also have to give function the data for what operator is to mapped to what ID.

## DIY CLI?
  - Just do it yourself, bruv.
  - Take input as string from String args[] in main and call `parse(String)`.
  
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

    
# Workflow
- [x] Common binary operators(+,-,*,/) support.
- [x] Support for default unary functions such as sin, cos, log, !(fact), etc.
- [x] Testing and finishing touches for the above.
- [x] Abstraction support with default definitions to implement customizability.
- [x] Complete testing for operator precedence customizability.
- [ ] Operator metadata support and testing.
- [ ] Read IDs from text file(csv).
- [ ] A string-to-input-format convertor.
- [ ] A default UI using using JavaFX.
