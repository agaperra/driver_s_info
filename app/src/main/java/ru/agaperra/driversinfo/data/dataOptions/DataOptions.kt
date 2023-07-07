package ru.agaperra.driversinfo.data.dataOptions

object DataOptions {

    val latinLetterList =
        listOf<CharSequence>("A", "X", "E", "T", "Y", "O", "P", "H", "K", "C", "B", "M")
    val cyrillicLetterList =
        listOf<CharSequence>("А", "Х", "Е", "Т", "У", "О", "Р", "Н", "К", "С", "В", "М")

    val numberList = listOf(0,1,2,3,4,5,6,7,8,9)

    val cyrillicLetterListForVRC =
        listOf<CharSequence>("А", "Б", "В", "Е", "З", "К", "М", "Н", "О", "Р", "С", "Т", "Х", "У")
    val latinLetterListForVRC =
        listOf<CharSequence?>("A", null, "B", "E", null, "K", "M", "H", "O", "P", "C", "T", "X", "Y")

    val cyrillicLetterListForDL =
        listOf<CharSequence>("А", "Б", "В", "Е", "К", "М", "Н", "О", "Р", "С", "Т", "Х", "У", "Ч")
    val latinLetterListForDL =
        listOf<CharSequence?>("A", null, "B", "E", "K", "M", "H", "O", "P", "C", "T", "X", "Y", null)

    val latinJoin = latinLetterList.joinToString("|")
    val latinJoinVRC = latinLetterListForVRC.joinToString("|")
    val latinJoinDL = latinLetterListForDL.joinToString("|")
    val cyrillicJoin = cyrillicLetterList.joinToString("|")
    val cyrillicJoinVRC = cyrillicLetterListForVRC.joinToString("|")
    val cyrillicJoinDL = cyrillicLetterListForDL.joinToString("|")
    val numbersJoin = numberList.joinToString("|")

    val patternForAutoNumber = listOf(
        ("(?:($latinJoin|$cyrillicJoin)" +
                "($numbersJoin)($numbersJoin)" +
                "($numbersJoin)" +
                "($latinJoin|$cyrillicJoin)" +
                "($latinJoin|$cyrillicJoin)" +
                "($numbersJoin)" +
                "($numbersJoin))").toRegex(
            RegexOption.IGNORE_CASE
        ), //госномер 2 цифры регион
        ("(?:($latinJoin|$cyrillicJoin)" +
                "($numbersJoin)" +
                "($numbersJoin)" +
                "($numbersJoin)" +
                "($latinJoin|$cyrillicJoin)" +
                "($latinJoin|$cyrillicJoin)" +
                "($numbersJoin)" +
                "($numbersJoin)" +
                "($numbersJoin))").toRegex(
            RegexOption.IGNORE_CASE
        ), // госномер 3 цифры регион
        ("(?:($latinJoin|$cyrillicJoin)" +
                "($latinJoin|$cyrillicJoin)" +
                "($numbersJoin)" +
                "($numbersJoin)" +
                "($numbersJoin)" +
                "($numbersJoin)" +
                "($numbersJoin))").toRegex(
            RegexOption.IGNORE_CASE
        ), // госномер такси 2 цифры регион
        ("(?:($latinJoin|$cyrillicJoin)" +
                "($latinJoin|$cyrillicJoin)" +
                "($numbersJoin)" +
                "($numbersJoin)" +
                "($numbersJoin)" +
                "($numbersJoin)" +
                "($numbersJoin)" +
                "($numbersJoin))").toRegex(
            RegexOption.IGNORE_CASE
        ), // госномер такси 3 цифры регион
        ("(?:($latinJoin|$cyrillicJoin)" +
                "($latinJoin|$cyrillicJoin)" +
                "($numbersJoin)" +
                "($numbersJoin)" +
                "($numbersJoin)" +
                "($numbersJoin)" +
                "($numbersJoin)" +
                "($numbersJoin))").toRegex(
            RegexOption.IGNORE_CASE
        ), // госномер прицеп 2 цифры регион
        ("(?:($latinJoin|$cyrillicJoin)" +
                "($latinJoin|$cyrillicJoin)" +
                "($numbersJoin)" +
                "($numbersJoin)" +
                "($numbersJoin)" +
                "($numbersJoin)" +
                "($numbersJoin)" +
                "($numbersJoin)" +
                "($numbersJoin))").toRegex(
            RegexOption.IGNORE_CASE
        ), // госномер прицеп 3 цифры регион
        ("(?:($numbersJoin)" +
                "($numbersJoin)" +
                "($numbersJoin)" +
                "($numbersJoin)" +
                "($numbersJoin)" +
                "($numbersJoin)" +
                "($latinJoin|$cyrillicJoin)" +
                "($latinJoin|$cyrillicJoin))").toRegex(
            RegexOption.IGNORE_CASE
        ), // госномер военный 2 цифры регион
        ("(?:($numbersJoin)" +
                "($numbersJoin)" +
                "($numbersJoin)" +
                "($numbersJoin)" +
                "($numbersJoin)" +
                "($numbersJoin)" +
                "($numbersJoin)" +
                "($latinJoin|$cyrillicJoin)" +
                "($latinJoin|$cyrillicJoin))").toRegex(
            RegexOption.IGNORE_CASE
        ) // госномер военный 3 цифры регион
    )

    val patternForVRC = listOf(
        ("(?:($numbersJoin)" +
                "($numbersJoin)" +
                "($numbersJoin)" +
                "($numbersJoin)" +
                "($numbersJoin)" +
                "($numbersJoin)" +
                "($numbersJoin)" +
                "($numbersJoin)" +
                "($numbersJoin)" +
                "($numbersJoin))").toRegex(
            RegexOption.IGNORE_CASE
        ),
        ("(?:($numbersJoin)" +
                "($numbersJoin)" +
                "($latinJoinVRC|$cyrillicJoinVRC)" +
                "($latinJoinVRC|$cyrillicJoinVRC)" +
                "($numbersJoin)" +
                "($numbersJoin)" +
                "($numbersJoin)" +
                "($numbersJoin)" +
                "($numbersJoin)" +
                "($numbersJoin))").toRegex(
            RegexOption.IGNORE_CASE
        ),
    )

    val patternForDL = listOf(
        ("(?:($numbersJoin)" +
                "($numbersJoin)" +
                "($numbersJoin)" +
                "($numbersJoin)" +
                "($numbersJoin)" +
                "($numbersJoin)" +
                "($numbersJoin)" +
                "($numbersJoin)" +
                "($numbersJoin)" +
                "($numbersJoin))").toRegex(
            RegexOption.IGNORE_CASE
        ),
        ("(?:($numbersJoin)" +
                "($numbersJoin)" +
                "($latinJoinDL|$cyrillicJoinDL)" +
                "($latinJoinDL|$cyrillicJoinDL)" +
                "($numbersJoin)" +
                "($numbersJoin)" +
                "($numbersJoin)" +
                "($numbersJoin)" +
                "($numbersJoin)" +
                "($numbersJoin))").toRegex(
            RegexOption.IGNORE_CASE
        ),
    )
}