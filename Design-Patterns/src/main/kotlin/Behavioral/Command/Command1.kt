package fr.uga.miage.m1.Behavioral.Command

interface Command1 {
    fun execute()
    fun undo()
}

class DimCommand(private val light: Light, private val brightness: Int) : Command1 {
    private var previousBrightness: Int = 0

    override fun execute() {
        previousBrightness = light.getBrightness()
        light.setBrightness(brightness)
    }
    override fun undo() {
        light.setBrightness(previousBrightness)
    }
}

class Light() {
    private var brightness: Int = 10

    fun getBrightness(): Int {
        return brightness
    }

    fun setBrightness(brightness: Int) {
        this.brightness = brightness
    }
}

class CommandHistory {
    private val history = ArrayDeque<Command1>()

    fun push( command : Command1){
        history.add(command)
    }

    fun pop(): Command1? {
        return if (history.isEmpty()) null else history.removeLast()
    }
}

class RemoteControl{
    private val history = CommandHistory()

    fun executeCommand(command : Command1){
        command.execute()
        history.push(command)
    }

    fun undo(){
        val command = history.pop()
        command?.undo()
    }
}