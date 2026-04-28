package fr.uga.miage.m1.Behavioral.Command

interface Command2 {
    fun execute()
    fun undo()
}

class BankAccount() {
    var balance: Int = 1000

    fun deposit(amount: Int) : Int {
        balance += amount
        return balance
    }

    fun withdraw(amount: Int) : Int {
        if (balance >= amount) {
            balance -= amount
            return balance
        }
        else return -1
    }
}

class DepositCommand(private val account : BankAccount, private val amount:Int) : Command2{
    override fun execute() {
        account.deposit(amount)
    }

    override fun undo() {
        account.withdraw(amount)
    }
}

class WithdrawCommand(private val account : BankAccount, private val amount:Int) : Command2{
    override fun execute() {
        account.withdraw(amount)
    }

    override fun undo() {
        account.deposit(amount)
    }
}

class CommandHistory2 {
    private val history = ArrayDeque<Command2>()

    fun push (command : Command2){
        history.add(command)
    }

    fun pop(): Command2? {
        return if (history.isEmpty()) null else history.removeLast()
    }
}

class BankingApp {
    private val history = CommandHistory2()

    fun executeCommand(command : Command2) {
        command.execute()
        history.push(command)
    }

    fun undo(){
        val command =  history.pop()
        command?.undo()
    }
}