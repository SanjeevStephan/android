# Create a Happy birthday message program in Kotlin

### Set up your starter code
1. In your browser, open https://developer.android.com/training/kotlinplayground. This opens a browser-based Kotlin programming tool.

### Prerequisites
* How to open and edit code in https://developer.android.com/training/kotlinplayground, a browser-based Kotlin programming tool.
* For Detailed Tutorial Guide, refrer to this article [Create a birthday message program in Kotlin](https://developer.android.com/codelabs/basic-android-kotlin-training-kotlin-birthday-message?continue=https%3A%2F%2Fdeveloper.android.com%2Fcourses%2Fpathways%2Fandroid-basics-kotlin-one%23codelab-https%3A%2F%2Fdeveloper.android.com%2Fcodelabs%2Fbasic-android-kotlin-training-kotlin-birthday-message#0)

### What you will learn
* How to print more complex text from your program.
* [Defining Variable](https://kotlinlang.org/docs/reference/basic-syntax.html#defining-variables) : How to do basic math in Kotlin and store the results in variables for later use.
* [Defining functions](https://kotlinlang.org/docs/reference/basic-syntax.html#defining-functions) : How to create a function to print the same string several times.
* [`repat` statement](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/repeat.html) : How to create a loop that prints a text snippet multiple times.

### What you need
* A computer with internet access and a modern web browser, such as the latest version of Chrome.

### What you will build
* You will create a short program that you can use to print birthday messages, a text-based picture of a cake, and and a birthday banner to always be the right size with the right number of candles for any age.
* For Detail Instructions & Guide. Refer to this [Guide](https://developer.android.com/codelabs/basic-android-kotlin-training-kotlin-birthday-message?continue=https%3A%2F%2Fdeveloper.android.com%2Fcourses%2Fpathways%2Fandroid-basics-kotlin-one%23codelab-https%3A%2F%2Fdeveloper.android.com%2Fcodelabs%2Fbasic-android-kotlin-training-kotlin-birthday-message#0) .
  * You will create a total of three functions for drawing a layered cake with candles.
  * You will use a repeat() inside another repeat(), creating what's called a "nested loop".
  * The way you will build up this code is how you can build up any program, starting with the big picture and adding detail. This is called "top-down development".
  * The instructions are not as detailed for this practice, and you can refer to the finished code if you get stuck.

Here is a picture of the cake you will be baking:
```
 ,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,
 ||||||||||||||||||||||||||||||||||
====================================
@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
@@@@@{  Happy Birthday, Sanju }@@@@@
@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
```

## And here are the instructions.
#### Create the main() function
1. Reset your code in the editor to the Hello, world! program.
2. You can remove the argument to main(), because you won't be using it.
3. In main(), create a variable age and set it to 24.
4. In main(), create a second variable layers and set it to 5.
5. In main(), call a function printCakeCandles() and pass in the age. This will leave you with an error, because you have not created that function yet.
6. Samewise, call a function printCakeTop() and also pass in the age.
7. Finally, call a function printCakeBottom() and pass in the age and also the number of layers.
8. To get rid of the errors, comment out the three function calls by adding // at the beginning of each line, as shown below. This technique allows you to draft your code without triggering errors.
9. Run your program, and it should have no errors and do nothing.
Your main() function should look like the code below.

```
fun main() {
    val age = 24
    val layers = 5
    // printCakeCandles(age)
    // printCakeTop(age)
    // printCakeBottom(age, layers)
}
```
#### Create printCakeTop()
The `printCakeTop()` function to print the top of the cake, a line of equal signs, is almost the same as the printBorder() function you created earlier in this codelab.

``` 
==========================
```

1. Below the main() function, add a blank line, and then create a function, printCakeTop() that takes one argument, age, of type Int.
2. Inside, use a repeat() statement to print one equal sign age times plus 2. The extra two equals signs are so that the candles won't fall off the side of the cake.
3. At the end, when the repeat() is done, print an empty line.
4. In main(), remove the two // symbols from the beginning of the line of code for printCakeTop(), because the function now exists.

``` 
printCakeTop(age) 
```
Here is your finished function.
```
// This function prints the top of the birthday cake
fun printCakeTop(age: Int) {
    // Print a row of equal signs for the top of the cake
    val timeToRepeat = age + 2
    repeat(timeToRepeat) {
        print("=")
    }
    println() // Empty line
}
```
5. Run your code to see the top of the cake.

#### Create printCakeCandles()
Each candle is made up of two symbols: a comma (,) for the flame, and a vertical line (|) for the candle body.
`,,,,,,,,,,,,,,,,,,,,,,,,`

`||||||||||||||||||||||||`
To accomplish this in one function, put two repeat() statements, one for the flames and one for the bodies, into your function.
1. Below the `main()` function and the `printCakeTop()` function, create a new function, `printCakeCandles()` that takes one argument, `age`, of type `Int`.
2. Inside, use a `repeat()` statement to print one comma , for the flame.
3. Repeat this `age` times.
4. At the end, print an empty line.
5. Add a print statement to print one space for insetting the candles.
6. Below, repeat the steps to create a second `repeat()` statement to print the candle bodies with a vertical line |.
7. At the end, print a new line, using `println()`.
8. In `main()`, remove the two // symbols from the beginning of the line of code for `printCakeCandles()`.

```
printCakeCandles(age)
```
9. Run your code to see the top of the cake and the candles

Solution:
```
// This function prints the candles on top of the birthday cake
fun printCakeCandles(age: Int) {
    // Print a row of commas (for the candles)
    print(" ")
    repeat(age) {
        print(',')
    }
    println() // Empty line
    
    // Print a row of vertical bars (for the candle flames)
    print(" ")
    repeat(age) {
        print("|")
    }
    println()
}
```

#### Create printCakeBottom()
In this function, you are drawing a cake bottom that is as wide as `age + 2`, and you draw it for a height of a given number of layers.
```
@@@@@@@@@@@@@@@@@@@@@@@@@@
@@@@@@@@@@@@@@@@@@@@@@@@@@
@@@@@@@@@@@@@@@@@@@@@@@@@@
@@@@@@@@@@@@@@@@@@@@@@@@@@
@@@@@@@@@@@@@@@@@@@@@@@@@@
```

* This means your function needs two arguments, one for the width (age) and one for the height (layers).
* To print the bottom of the cake, you first repeat the ‘at' @ symbol age + 2 times to print one layer. Then, you repeat printing one layer layers times.

##### Draw the at symbol age+2 times to create a layer
1. Below the existing functions, create a function printCakeBottom() with two arguments, age and layers, both of type Int.
2. Inside the function, use a repeat() statement to print one layer of ‘at' @ symbols age + 2 times. Finish by printing an empty line, as shown below.

```
// This function prints the bottom layers of the birthday cake
fun printCakeBottom(age: Int, layers: Int) {
    repeat(age + 2) {
        print("@")
    }
    println()
}
```
3. In main(), remove the two // symbols from the beginning of the line of code for printCakeBottom(age, layers).
4. Run your code to verify that it prints one line of cake bottom.
```
 ,,,,,,,,,,,,,,,,,,,,,,,,
 ||||||||||||||||||||||||
==========================
@@@@@@@@@@@@@@@@@@@@@@@@@@
```
##### Nested repeat() statements

To print multiple identical layers of cake bottom, you could say:

For layer 1 repeat the symbol 12 times: @@@@@@@@@@@@

For layer 2 repeat the symbol 12 times: @@@@@@@@@@@@

For layer 3 repeat the symbol 12 times: @@@@@@@@@@@@

Or you can say this much more concisely as:

Repeat for three layers:

Repeat the symbol 12 times.

@@@@@@@@@@@@

@@@@@@@@@@@@

@@@@@@@@@@@@

> Now, this is something neat you can do with repeat() statements. You can put one repeat() statement inside another repeat() statement. So you could create a repeat() statement within a repeat() statement to print the symbol a certain number of times for a certain number of layers.

##### Use a nested repeat() to print cake layers
5. Put a second repeat() statement around all of the code inside the function. Repeat this loop layers times.
6. In main(), remove only the two // from the line of code for printCakeBottom()

``` printCakeBottom(age, layers) ```
7. Run your code to see the whole cake.

Solution for `printCakeBottom()`.
```
// This function prints the bottom layers of the birthday cake
fun printCakeBottom(age:Int, layers: Int) {
    // Calculate the number of layers to print
    val layersToRepeat = layers / 2
    repeat(layersToRepeat) {
        // Print a row of @ symbols for each layer
        val timeToRepeat = age + 2
        repeat(timeToRepeat) {
            print("@")
        }
        println()
    }
}
```
Congratulations! You've just finished a pretty complex program with several functions and a nested repeat statement. And your cake will always have the right number of candles!

The final output of your program should be:
```
 ,,,,,,,,,,,,,,,,,,,,,,,,
 ||||||||||||||||||||||||
==========================
@@@@@@@@@@@@@@@@@@@@@@@@@@
@@@@@@@@@@@@@@@@@@@@@@@@@@
@@@@@@@@@@@@@@@@@@@@@@@@@@
@@@@@@@@@@@@@@@@@@@@@@@@@@
@@@@@@@@@@@@@@@@@@@@@@@@@@
```
#### You Can Add One More Function 
To make the cake look nice you can add one more function to display the `{  Happy Birthday, Sanju }` between the layer of the cake such that the output will be:
```
@@@@@{  Happy Birthday, Sanju }@@@@@
```
Solution :
```
// This function prints a birthday message, centered on the age of the person
fun printBirthDayMsg(age: Int, message: String) {
    // Calculate the amount of padding needed to center the message
    val padding = age - message.length
    val msgLen = message.length
    val leftPadding = padding / 2
    val rightPadding = padding - leftPadding
    
    // Print the left padding (with @ symbol), the message, and the right padding (with @ symbol)
    repeat(Math.abs(leftPadding) + 1) {
        print("@")
    }
    print(message)
    repeat(Math.abs(rightPadding) + 1) {
        print("@")
    }
    println()
}
```
### You need to Modify the `main()` to add above function
```
// The main function prints a birthday cake and message
fun main() {
    val age = 34
    val layers = 4
    
    // Print the candles, top, bottom, message, and bottom of the cake
    printCakeCandles(age)
    printCakeTop(age)
    printCakeBottom(age, layers)
    printBirthDayMsg(age, "{  Happy Birthday, Sanju }")
    printCakeBottom(age, layers)   
}
```
> Here, we reduces the layer of the cake to 4 (two above & two below the BirthdayMessage) and call the function `printBirthDayMsg(age, "{  Happy Birthday, Sanju }")` between the function `printCakeBottom(age, layers)` to print the `{  Happy Birthday, Sanju }` between the layers of the cake.

Final Output Should Be : 
```
 ,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,
 ||||||||||||||||||||||||||||||||||
====================================
@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
@@@@@{  Happy Birthday, Sanju }@@@@@
@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
```
### Here is the [Complete Kotlin Code ](https://github.com/SanjeevStephan/android/blob/master/training-course/101-kotlin-programs/Happy-Birthday-Kotlin-Program/happy-birthday-cake.kt)
### Summary Explaination
Overall, [this code](https://github.com/SanjeevStephan/android/blob/master/training-course/101-kotlin-programs/Happy-Birthday-Kotlin-Program/happy-birthday-cake.kt) 
* creates a birthday cake with candles and layers, and prints a centered birthday message on top of the cake. 
* The `printBirthDayMsg()` function calculates the amount of padding needed to center the message based on the person's age, and then prints the message with `@` symbols on either side to fill the padding. 
* The `printCakeCandles()`, `printCakeTop()`, and `printCakeBottom()` functions print the different parts of the cake, including 
  * candles, 
  * the top of the cake, and 
  * the bottom layers. 
* The `main()` function calls all of these functions to assemble the cake and message.
