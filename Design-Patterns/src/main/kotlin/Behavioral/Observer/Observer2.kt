package fr.uga.miage.m1.Behavioral.Observer

interface Services {
    fun update(context :  Ecommerce)
}

class Ecommerce {
    private var subscribers = mutableListOf<Services>()
    var status : String = ""
        private set

    fun attach(subscriber: Services) {
        subscribers.add(subscriber)
    }

    fun detach (subscriber: Services) {
        subscribers.remove(subscriber)
    }

    private fun notifySubscribers(){
        for (s in subscribers) {
            s.update(this)
        }
    }

    fun trigger(status : String){
        this.status = status
        notifySubscribers()
    }
}

class Notification : Services {
    var lastStatus: String = ""
    override fun update(context: Ecommerce) {
        lastStatus = context.status
        println("Notification : ${context.status}")
    }
}

class Inventory : Services {
    var lastStatus: String = ""
    override fun update(context: Ecommerce) {
        lastStatus = context.status
        println("Inventory : ${context.status}")
    }
}

class Invoice : Services {
    var lastStatus: String = ""
    override fun update(context: Ecommerce) {
        lastStatus = context.status
        println("Invoice : ${context.status}")
    }
}