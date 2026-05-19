package fr.uga.miage.m1.Behavioral.Observer

interface Watchers {
    fun update(context : StockTracker)
}

class StockTracker (){
    private var subscribers = mutableListOf<Watchers>()
    var price : Double = 0.0
        private set

    fun attach(watchers : Watchers) {
        subscribers.add(watchers)
    }

    fun detach(watchers : Watchers) {
        subscribers.remove(watchers)
    }

    private fun notifySubscribers(){
        for (s in subscribers) {
            s.update(this)
        }
    }

    fun trigger(p : Double){
        this.price = p
        notifySubscribers()
    }
}


class MobileAlert : Watchers {
    var lastPrice : Double = 0.0

    override fun update(context: StockTracker) {
        lastPrice = context.price
        println("📱 Mobile alert: New price is : $${context.price}")
    }
}

class TradingBot : Watchers {
    var lastPrice : Double = 0.0

    override fun update(context: StockTracker) {
        lastPrice = context.price
        println("🤖 Bot: analyzing new price : $${context.price}")
    }
}

class PortfolioDashboard : Watchers {
    var lastPrice : Double = 0.0

    override fun update(context: StockTracker) {
        lastPrice = context.price
        println("📊 Dashboard: updating chart for price : $${context.price}")
    }
}