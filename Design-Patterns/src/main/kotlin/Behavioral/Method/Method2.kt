package fr.uga.miage.m1.Behavioral.Method

abstract class HotBeverage{
    fun prepare() : String {
        var output : String = ""
        output += boilWater()
        output += brew()
        output += pourInCup()
        output += addCondiments()
        return output
    }
    private fun boilWater() : String {
        return "🫖 Boiling water...\n"
    }
    private fun pourInCup() : String {
         return "🥤 Pouring into cup...\n"
    }
    protected open fun addCondiments() : String {
        return ""
    }

    protected abstract fun brew() : String
}

class Tea : HotBeverage (){
    override fun addCondiments(): String {
        return "🍋 Adding a slice of lemon...\n"
    }

    override fun brew(): String {
        return "🍵 Steeping the tea bag for 3 minutes...\n"
    }
}

class Coffee : HotBeverage (){
    override fun addCondiments(): String {
        return "🥛 Adding sugar and milk...\n"
    }

    override fun brew(): String {
        return "☕ Brewing coffee grinds...\n"
    }
}

class HotChocolate : HotBeverage (){
    override fun addCondiments(): String {
        return "🍡 Adding marshmallows...\n"
    }

    override fun brew(): String {
        return "🍫 melting chocolate powder into hot water...\n"
    }
}