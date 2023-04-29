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

// This function prints the top of the birthday cake
fun printCakeTop(age: Int) {
    // Print a row of equal signs for the top of the cake
    val timeToRepeat = age + 2
    repeat(timeToRepeat) {
        print("=")
    }
    println() // Empty line
}

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


/* OUTPUT
 ,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,
 ||||||||||||||||||||||||||||||||||
====================================
@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
@@@@@{  Happy Birthday, Sanju }@@@@@
@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

*/
