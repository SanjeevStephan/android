fun printBirthDayMsg(age: Int,message : String) {
        //val message = "{ Happy Birthday, Sanjeev Stephan }"
    val padding = age - message.length
    val msgLen = message.length
    //println("Len :${msgLen}")
    val leftPadding = padding / 2
    //println("leftPadding : ${leftPadding}")
    val rightPadding = padding - leftPadding
    //println("rightPadding : ${rightPadding}")
    repeat(Math.abs(leftPadding) + 1) {
        print("@")
    }
    print(message)
    repeat(Math.abs(rightPadding) + 1) {
        print("@")
    }
    println()
}

fun printCakeCandles(age: Int)
{
    print(" ")
    repeat(age) 
    {
        print(',')
    }
    println() //empty line
    print(" ")
    repeat(age) {
        print("|")
    }
    println()
}
fun printCakeTop(age: Int) {
    val timeToRepeat = age + 2
    repeat(timeToRepeat) {
       print("=")
    }
    println() //print an empty-line
}
fun printCakeBottom(age:Int, layers: Int) {
    val layersToRepeat = layers / 2
	repeat(layersToRepeat){
            val timeToRepeat = age + 2
            repeat(timeToRepeat) {
                print("@")
            }
            println()
            
    }
    
    
}
fun main() {
    
    println("Hello, World")
    val age = 34
    val layers = 4
    printCakeCandles(age)
    printCakeTop(age)
    printCakeBottom(age,layers)
    printBirthDayMsg(age, "{  Happy Birthday, Sanju }")
        printCakeBottom(age,layers)   
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
