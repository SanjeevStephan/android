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

` ==========================`

1. Below the main() function, add a blank line, and then create a function, printCakeTop() that takes one argument, age, of type Int.
2. Inside, use a repeat() statement to print one equal sign age times plus 2. The extra two equals signs are so that the candles won't fall off the side of the cake.
3. At the end, when the repeat() is done, print an empty line.
4. In main(), remove the two // symbols from the beginning of the line of code for printCakeTop(), because the function now exists.

`printCakeTop(age)`

### Explaination
Overall, [this code](https://github.com/SanjeevStephan/android/blob/master/training-course/101-kotlin-programs/Happy-Birthday-Kotlin-Program/happy-birthday-cake.kt) creates a birthday cake with candles and layers, and prints a centered birthday message on top of the cake. The printBirthDayMsg function calculates the amount of padding needed to center the message based on the person's age, and then prints the message with @ symbols on either side to fill the padding. The printCakeCandles, printCakeTop, and printCakeBottom functions print the different parts of the cake, including candles, the top of the cake, and the bottom layers. The main function calls all of these functions to assemble the cake and message.
