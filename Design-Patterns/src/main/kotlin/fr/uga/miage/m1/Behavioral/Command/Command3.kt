package fr.uga.miage.m1.Behavioral.Command

interface Command3{
    fun execute()
    fun undo()
}

class Soldier {
    private var posX : Int = 0
    private var posY : Int = 0
    private var status : String = "Idle"

    fun move (deltaX : Int, deltaY : Int){
        this.posX += deltaX
        this.posY += deltaY
    }

    fun guard (){
        this.status = "Guarding"
    }

    fun getX () : Int{
        return this.posX
    }

    fun getY () : Int{
        return this.posY
    }

    fun getStatus (): String {
        return this.status
    }

    fun setStatus (status : String){
        this.status =  status
    }

    fun setPosition (deltaX: Int, deltaY: Int){
        this.posX = deltaX
        this.posY = deltaY
    }
}

class moveCommand(
    private val soldier: Soldier,
    private val deltaX: Int,
    private val deltaY: Int
): Command3 {

    private var previousX : Int = 0
    private var previousY : Int = 0

    override fun execute(){
        previousX = soldier.getX()
        previousY = soldier.getY()
        soldier.move(deltaX, deltaY)
    }

    override fun undo() {
        soldier.setPosition(previousX, previousY )
    }
}

class GuardCommand(
    private val soldier : Soldier
) : Command3 {

    private var previousStatus : String = ""

    override fun execute(){
        previousStatus =  soldier.getStatus()
        soldier.guard()
    }

    override fun undo() {
        soldier.setStatus(previousStatus)
    }
}

class PatrolCommand(
    private val soldier : Soldier
) : Command3 {
    private var commands = mutableListOf<Command3>()

    fun addCommand (command : Command3){
        commands.add(command)
    }

    fun removeCommand () : Command3? {
        return if (commands.isEmpty()) null else commands.removeLast()
    }

    override fun execute() {
        commands.forEach{ it.execute()}
    }

    override fun undo() {
        commands.reversed().forEach{ it.undo()}
    }
}

class CommandHistory3 {
    private val history = ArrayDeque<Command3>()

    fun push(command : Command3){
        history.add(command)
    }

    fun pop(): Command3? {
        return if (history.isEmpty()) null else history.removeLast()
    }
}

class Game {
    private val history = CommandHistory3()

    fun executeCommand(command : Command3){
        command.execute()
        history.push(command)
    }

    fun undoCommand(){
        val last = history.pop()
        last?.undo()
    }
}