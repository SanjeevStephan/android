package com.sanjeev.stephan.murmu.mentorme.myarrays

class ArrayAllAuthors {

  enum class What_Do_You_Want {
    AUTHOR_NAME, AUTHOR_IMGAGES
  }

  var arrayList = arrayOf("")

  fun getAuthors(whatDoYouWant: What_Do_You_Want): Array<String> {

    when (whatDoYouWant) {
      What_Do_You_Want.AUTHOR_NAME -> arrayList = arrayOf(
              "Robert T.Kiyosaki",
              "Ken McRoy"

      )
    }
    return arrayList
  }
}