import org.junit.Test

import org.junit.Assert.*

class MainKtTest {

    @Test
    fun printResultVKPay() {
        val cardType = VK_PAY
        val monthTransferSum = 0
        val vkMonthTransferSum = 0
        val dayTransferSum = 0
        val transfer = 5_500

        val result = printResult(
            cardType = cardType,
            monthTransferSum = monthTransferSum,
            vkMonthTransferSum = vkMonthTransferSum,
            dayTransferSum = dayTransferSum,
            transfer = transfer
        )
        assertEquals(0.toString(), result)

    }

    @Test
    fun printResultOthers() {
        val cardType = MASTERCARD
        val monthTransferSum = 20_000
        val vkMonthTransferSum = 50_000
        val dayTransferSum = 10_000
        val transfer = 100_000

        val result = printResult(
            cardType = cardType,
            monthTransferSum = monthTransferSum,
            vkMonthTransferSum = vkMonthTransferSum,
            dayTransferSum = dayTransferSum,
            transfer = transfer
        )
        assertEquals(170.toString(), result)

    }

    @Test
    fun printResultOutOfLimits() {
        val cardType = MASTERCARD
        val monthTransferSum = 20_000
        val vkMonthTransferSum = 50_000
        val dayTransferSum = 10_000
        val transfer = 500_000

        val result = printResult(
            cardType = cardType,
            monthTransferSum = monthTransferSum,
            vkMonthTransferSum = vkMonthTransferSum,
            dayTransferSum = dayTransferSum,
            transfer = transfer
        )
        assertEquals("Операция заблокирована из-за превышения лимита!", result)

    }

    @Test
    fun transferCommissionMastercard() {
        val cardType = MASTERCARD
        val transfer = 17_000

        val result = transferCommission(cardType, transfer)
        assertEquals(0, result)
    }

    @Test
    fun transferCommissionMaestro() {
        val cardType = MAESTRO
        val transfer = 17_000

        val result = transferCommission(cardType, transfer)
        assertEquals(0, result)
    }

    @Test
    fun transferCommissionVisa() {
        val cardType = VISA
        val transfer = 17_000

        val result = transferCommission(cardType, transfer)
        assertEquals(127, result)
    }

    @Test
    fun transferCommissionMir() {
        val cardType = MIR
        val transfer = 17_000

        val result = transferCommission(cardType, transfer)
        assertEquals(1007, result)
    }

    @Test
    fun visaMirCommissionMinimal() {
        val sum = 150

        val result = visaMirCommission(sum)
        assertEquals(VISA_MIR_MIN_COMMISSION, result)
    }
}