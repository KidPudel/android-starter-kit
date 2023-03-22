package com.example.crossnotes

import kotlin.coroutines.coroutineContext

class Greeting {
    private val platform: Platform = getPlatform()

    fun greet(): String {
        val people = mutableListOf(
            Human(name = "Mark", age = 20, gender = Gender.MALE, dream = "airplanes"),
            Human(name = "Anjela", age = 34, gender = Gender.FEMALE, dream = "ocean"),
            Human(name = "John", age = 40, gender = Gender.MALE, dream = "cigarette"),
            Human(name = "Lily", age = 8, gender = Gender.FEMALE, dream = "treehouse"),
            Human(name = "Jake", age = 4, gender = Gender.MALE, dream = "banana"),
            Human(name = "Finn", age = 4, gender = Gender.MALE, dream = "pineapple"),
            Human(name = "Pem", age = 28, gender = Gender.FEMALE, dream = "Jim"),
            Human(name = "Jim", age = 29, gender = Gender.MALE, dream = "Pem"),
        )
        val peopleUnderThirty = people.filter { human -> human.age < 30 }
        val numberOfPeopleNameStartsAtJ = people.count { human -> human.name.first() == 'J' }
        val fruits = arrayListOf<String>("apple", "banana", "pineapple")
        val fruitDreams = people.filter { human -> fruits.contains(human.dream) }
        val oldestHuman = people.maxBy { human -> human.age }
        val numberOfMalesAndFemales = people.groupingBy { human -> human.gender }.eachCount()
        val findFemales =
            people.filter { human -> human.gender.apply { if (human.name == "Pem") println("${human.name}!!") } == Gender.FEMALE }
        return "Hello, ${platform.name}!"
    }
}

data class Human(
    val name: String,
    val age: Int,
    val gender: Gender,
    val dream: String
)

enum class Gender {
    MALE, FEMALE
}
