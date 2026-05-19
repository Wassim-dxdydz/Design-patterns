package fr.uga.miage.m1.Behavioral.Observer

interface HubSubscriber {
    fun update(context : TheHub)
}

class TheHub {
    private val subscribers = mutableListOf<HubSubscriber>()
    var event : String = ""
        private set

    fun attach(subscriber : HubSubscriber){
        subscribers.add(subscriber)
    }

    fun detach(subscriber : HubSubscriber){
        subscribers.remove(subscriber)
    }

    private fun notifySubscribers(){
        for (s in subscribers){
            s.update(this)
        }
    }

    fun trigger(event : String){
        this.event = event
        notifySubscribers()
    }
}

class Alarm : HubSubscriber {
    var lastEvent: String? = null
    override fun update(context: TheHub) {
        lastEvent = context.event
        println("🚨 Alarm: reacting to ${context.event}")
    }
}

class Lights : HubSubscriber {
    var lastEvent: String? = null
    override fun update(context: TheHub) {
        lastEvent = context.event
        println("💡 Lights: adjusting to ${context.event}")
    }
}

class Thermostat : HubSubscriber {
    var lastEvent: String? = null
    override fun update(context: TheHub) {
        lastEvent = context.event
        println("🌡️ Thermostat: recalibrating after ${context.event}")
    }
}