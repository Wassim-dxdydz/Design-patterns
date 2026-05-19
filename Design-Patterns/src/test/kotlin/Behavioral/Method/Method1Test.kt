package fr.uga.miage.m1.Behavioral.Method

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class LabAnalysisTest {
    @Test
    fun `LabAnalysis template method test`() {
        val blood = BloodTest()
        val urine = UrineTest()
        val imaging = ImagingReport()

        blood.runAnalysis("Marie Curie")
        urine.runAnalysis("Marie Curie")
        imaging.runAnalysis("Marie Curie")

        assertTrue(blood.needsCalibration())
        assertFalse(UrineTest().needsCalibration())
        assertTrue(ImagingReport().needsCalibration())
    }
}