package fr.uga.miage.m1.Behavioral.Method

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class MedReportTest {

    @Test
    fun `Method test` (){

        val output1 ="📋 Collecting patient basic info...\n🩺 Running standard checkup analysis...\n📊 Formatting report as plain text...\n📧 Sending report to general practitioner...\n"
        val output2 = "📋 Collecting patient basic info...\n❤️ Running ECG and cardiac stress analysis...\n📊 Formatting report as PDF with charts...\n📧 Sending report to cardiology department...\n🔔 Notifying on-call cardiologist...\n"
        val output3 = "📋 Collecting patient basic info...\n🧒 Running growth and vaccine schedule analysis...\n📊 Formatting report as plain text...\n📧 Sending report to pediatrician...\n"
        val output4 = "📋 Collecting patient basic info...\n🎗️ Running tumor marker and imaging analysis...\n📊 Formatting report as encrypted PDF...\n📧 Sending report to oncology board...\n🔔 Alerting oncology coordinator...\n"

        val generalReport = GeneralReport()
        val cardiologyReport = CardiologyReport()
        val pediatricsReport = PediatricsReport()
        val oncologyReport = OncologyReport()

        assertEquals(generalReport.generate(), output1)
        assertEquals(cardiologyReport.generate(), output2)
        assertEquals(pediatricsReport.generate(), output3)
        assertEquals(oncologyReport.generate(), output4)
        assertNotEquals(generalReport.generate(), cardiologyReport.generate())

    }
}