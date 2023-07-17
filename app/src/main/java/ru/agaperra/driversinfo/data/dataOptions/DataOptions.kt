package ru.agaperra.driversinfo.data.dataOptions

object DataOptions {

    val latinLettersForVehicleNumber =
        listOf<CharSequence>("A", "X", "E", "T", "Y", "O", "P", "H", "K", "C", "B", "M")
    val cyrillicLettersForVehicleNumber =
        listOf<CharSequence>("А", "Х", "Е", "Т", "У", "О", "Р", "Н", "К", "С", "В", "М")

    private val numbersList = listOf(0,1,2,3,4,5,6,7,8,9)

    val cyrillicLettersListForCertificate =
        listOf<CharSequence>("А", "Б", "В", "Е", "З", "К", "М", "Н", "О", "Р", "С", "Т", "Х", "У")
    val latinLettersListForCertificate =
        listOf<CharSequence?>("A", null, "B", "E", null, "K", "M", "H", "O", "P", "C", "T", "X", "Y")

    val cyrillicLettersListForLicense =
        listOf<CharSequence>("А", "Б", "В", "Е", "К", "М", "Н", "О", "Р", "С", "Т", "Х", "У", "Ч")
    val latinLettersListForLicense =
        listOf<CharSequence?>("A", null, "B", "E", "K", "M", "H", "O", "P", "C", "T", "X", "Y", null)

    private val latinJoinVehicleNumberLetters = latinLettersForVehicleNumber.joinToString("|")
    private val latinJoinCertificateLetters = latinLettersListForCertificate.joinToString("|")
    private val latinJoLicenseLetters = latinLettersListForLicense.joinToString("|")
    private val cyrillicJoinVehicleNumberLetters = cyrillicLettersForVehicleNumber.joinToString("|")
    private val cyrillicJoinCertificateLetters = cyrillicLettersListForCertificate.joinToString("|")
    private val cyrillicJoinLicenseLetters = cyrillicLettersListForLicense.joinToString("|")
    private val numbersJoin = numbersList.joinToString("|")

    val patternForVehicleNumber = listOf(
        ("(?:($latinJoinVehicleNumberLetters|$cyrillicJoinVehicleNumberLetters)" +
                "($numbersJoin){3}" +
                "($latinJoinVehicleNumberLetters|$cyrillicJoinVehicleNumberLetters){2}" +
                "($numbersJoin){2})").toRegex(
            RegexOption.IGNORE_CASE
        ), //госномер 2 цифры регион
        ("(?:($latinJoinVehicleNumberLetters|$cyrillicJoinVehicleNumberLetters)" +
                "($numbersJoin){3}" +
                "($latinJoinVehicleNumberLetters|$cyrillicJoinVehicleNumberLetters){2}" +
                "($numbersJoin){3})").toRegex(
            RegexOption.IGNORE_CASE
        ), // госномер 3 цифры регион
        ("(?:($latinJoinVehicleNumberLetters|$cyrillicJoinVehicleNumberLetters){2}" +
                "($numbersJoin){5})").toRegex(
            RegexOption.IGNORE_CASE
        ), // госномер такси 2 цифры регион
        ("(?:($latinJoinVehicleNumberLetters|$cyrillicJoinVehicleNumberLetters){2}" +
                "($numbersJoin){6})").toRegex(
            RegexOption.IGNORE_CASE
        ), // госномер такси 3 цифры регион
        ("(?:($latinJoinVehicleNumberLetters|$cyrillicJoinVehicleNumberLetters){2}" +
                "($numbersJoin){6})").toRegex(
            RegexOption.IGNORE_CASE
        ), // госномер прицеп 2 цифры регион
        ("(?:($latinJoinVehicleNumberLetters|$cyrillicJoinVehicleNumberLetters){2}" +
                "($numbersJoin){7})").toRegex(
            RegexOption.IGNORE_CASE
        ), // госномер прицеп 3 цифры регион
        ("(?:($numbersJoin){6}" +
                "($latinJoinVehicleNumberLetters|$cyrillicJoinVehicleNumberLetters){2})").toRegex(
            RegexOption.IGNORE_CASE
        ), // госномер военный 2 цифры регион
        ("(?:($numbersJoin){7}" +
                "($latinJoinVehicleNumberLetters|$cyrillicJoinVehicleNumberLetters){2})").toRegex(
            RegexOption.IGNORE_CASE
        ) // госномер военный 3 цифры регион
    )

    val patternForCertificate = listOf(
        ("(?:($numbersJoin){10})").toRegex(
            RegexOption.IGNORE_CASE
        ),
        ("(?:($numbersJoin){2}" +
                "($latinJoinCertificateLetters|$cyrillicJoinCertificateLetters){2}" +
                "($numbersJoin){6})").toRegex(
            RegexOption.IGNORE_CASE
        ),
    )

    val patternForLicense = listOf(
        ("(?:($numbersJoin){10})").toRegex(
            RegexOption.IGNORE_CASE
        ),
        ("(?:($numbersJoin){2}" +
                "($latinJoLicenseLetters|$cyrillicJoinLicenseLetters){2}" +
                "($numbersJoin){6})").toRegex(
            RegexOption.IGNORE_CASE
        ),
    )
    fun changeLatinForCyrillic(letter: CharSequence, listCyrillic: List<CharSequence>, listLatin: List<CharSequence?>): CharSequence {
        return listCyrillic[listLatin.indexOf(letter)]
    }

    fun ifLetterInCyrillicOrInLatin(letter: CharSequence, list: List<CharSequence?>): Boolean {
        return list.contains(letter)
    }

    fun ifLetterInNumbers(letter: Int?): Boolean? {
        return if (letter == null){
            null
        } else numbersList.contains(letter)
    }

}