package fr.uga.miage.m1.Behavioral.Method

abstract class LabAnalysis {
    fun runAnalysis(patientName: String) {
        collectSample(patientName)
        if (needsCalibration()) {
            calibrate()
        }
        analyze(patientName)
        interpret(patientName)
        generateReport(patientName)
        println("✅ Analysis complete for $patientName \n")
    }
    private fun collectSample(patientName: String) {
        println("🧪 Collecting sample for patient: $patientName")
    }
    private fun generateReport(patientName: String) {
        println("📄 Generating final report for: $patientName")
    }
    open fun needsCalibration(): Boolean = false
    protected open fun calibrate() {
        println("⚙️ Running default calibration...")
    }
    protected abstract fun analyze(patientName: String)
    protected abstract fun interpret(patientName: String)
}

class BloodTest : LabAnalysis() {
    public override fun needsCalibration(): Boolean = true

    override fun calibrate() {
        println("🔬 Calibrating blood cell counter...")
    }
    override fun analyze(patientName: String) {
        println("🩸 Measuring hemoglobin, white cells, platelets for: $patientName")
    }
    override fun interpret(patientName: String) {
        println("📊 Flagging values outside normal ranges for: $patientName")
    }
}

class UrineTest : LabAnalysis() {
    override fun analyze(patientName: String) {
        println("💧 Measuring pH, protein, glucose levels for: $patientName")
    }
    override fun interpret(patientName: String) {
        println("🔍 Detecting abnormal concentration levels for: $patientName")
    }
}

class ImagingReport : LabAnalysis() {
    override fun needsCalibration(): Boolean = true

    override fun calibrate() {
        println("📡 Calibrating imaging sensors and scan metadata reader...")
    }
    override fun analyze(patientName: String) {
        println("🖥️ Processing scan/X-ray metadata for: $patientName")
    }
    override fun interpret(patientName: String) {
        println("🩻 Generating radiologist summary note for: $patientName")
    }
}