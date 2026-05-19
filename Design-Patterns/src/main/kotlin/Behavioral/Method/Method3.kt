package fr.uga.miage.m1.Behavioral.Method

abstract class MedReport {
    fun generate() : String {
        var output = ""
        output += collect()
        output += analysis()
        output += formatting()
        output += sending()
        output += onReportComplete()
        return output
    }

    private fun collect (): String {
        return "📋 Collecting patient basic info...\n"
    }
    protected abstract fun analysis(): String
    protected abstract fun formatting(): String
    protected abstract fun sending(): String
    protected open fun onReportComplete(): String = ""
}

class GeneralReport : MedReport(){
    override fun analysis(): String {
        return "🩺 Running standard checkup analysis...\n"
    }

    override fun formatting(): String {
        return "📊 Formatting report as plain text...\n"
    }

    override fun sending(): String {
        return "📧 Sending report to general practitioner...\n"
    }
}

class CardiologyReport : MedReport(){
    override fun onReportComplete(): String {
        return "🔔 Notifying on-call cardiologist...\n"
    }

    override fun analysis(): String {
        return "❤️ Running ECG and cardiac stress analysis...\n"
    }

    override fun formatting(): String {
        return "📊 Formatting report as PDF with charts...\n"
    }

    override fun sending(): String {
        return "📧 Sending report to cardiology department...\n"
    }
}

class PediatricsReport : MedReport(){
    override fun analysis(): String {
        return "🧒 Running growth and vaccine schedule analysis...\n"
    }

    override fun formatting(): String {
        return "📊 Formatting report as plain text...\n"
    }

    override fun sending(): String {
        return "📧 Sending report to pediatrician...\n"
    }
}

class OncologyReport : MedReport(){
    override fun onReportComplete(): String {
        return "🔔 Alerting oncology coordinator...\n"
    }

    override fun analysis(): String {
        return "🎗️ Running tumor marker and imaging analysis...\n"
    }

    override fun formatting(): String {
        return "📊 Formatting report as encrypted PDF...\n"
    }

    override fun sending(): String {
        return "📧 Sending report to oncology board...\n"
    }
}