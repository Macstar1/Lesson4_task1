fun main() {
    val cardType = MIR
    val dayTransferSum = 0
    val monthTransferSum = 0
    val vkMonthTransferSum = 0
    val transfer = 75_500

    println(printResult(cardType, monthTransferSum, dayTransferSum, vkMonthTransferSum, transfer))
}

fun printResult(
    cardType: String,
    monthTransferSum: Int,
    vkMonthTransferSum: Int,
    dayTransferSum: Int,
    transfer: Int
): String {
    return if (
        (cardType == VK_PAY) &&
        (transfer < VK_PAY_MAX_TRANSFER) &&
        (transfer + vkMonthTransferSum < VK_PAY_MAX_MONTH_TRANSFER)
    ) {
        transferCommission(cardType, transfer).toString()
    } else if (
        (monthTransferSum + transfer <= MAX_MONTH_TRANSFER) &&
        (dayTransferSum + transfer <= MAX_DAY_TRANSFER)
    )
        transferCommission(cardType, transfer).toString()

    else "Операция заблокирована из-за превышения лимита!"
}

fun transferCommission(
    cardType: String, transfer: Int
): Int {
    return when (cardType) {
        MASTERCARD -> mastercordMaestroCommission(transfer)
        MAESTRO -> mastercordMaestroCommission(transfer)
        VISA -> visaMirCommission(transfer)
        MIR -> visaMirCommission(transfer)
        VK_PAY -> 0
        else -> 0
    }
}

fun mastercordMaestroCommission(sum: Int): Int {
    return if (sum in MASTERCARD_MIN_LIMIT..MASTERCARD_LIMIT) 0
    else ((sum - MASTERCARD_LIMIT) * MASTERCARD_MAESTRO_COMMISSION + MASTERCARD_MAESTRO_MIN_COMMISSION).toInt()
}

fun visaMirCommission(sum: Int): Int {
    return if (sum * VISA_MIR_COMMISSION < VISA_MIR_MIN_COMMISSION) {
        VISA_MIR_MIN_COMMISSION
    } else (sum * VISA_MIR_COMMISSION).toInt()
}

const val MASTERCARD_MIN_LIMIT = 300
const val MASTERCARD_LIMIT = 75_000

const val MAX_DAY_TRANSFER = 150_000
const val MAX_MONTH_TRANSFER = 600_000

const val VK_PAY_MAX_TRANSFER = 15_000
const val VK_PAY_MAX_MONTH_TRANSFER = 40_000

const val MIR = "Мир"
const val VISA = "Visa"
const val MASTERCARD = "Mastercard"
const val MAESTRO = "Maestro"
const val VK_PAY = "VK Pay"

const val VISA_MIR_COMMISSION = 0.0075
const val MASTERCARD_MAESTRO_COMMISSION = 0.006
const val MASTERCARD_MAESTRO_MIN_COMMISSION = 20
const val VISA_MIR_MIN_COMMISSION = 35

