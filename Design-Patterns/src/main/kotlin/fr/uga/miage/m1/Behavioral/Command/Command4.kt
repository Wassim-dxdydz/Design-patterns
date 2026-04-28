package fr.uga.miage.m1.Behavioral.Command

interface PipelineCommand{
    fun execute() : Boolean
    fun undo()
}

class BuildCommand : PipelineCommand{
    override fun execute(): Boolean {
        TODO("Not yet implemented")
    }

    override fun undo() {
        TODO("Not yet implemented")
    }

}
class RunTestsCommand : PipelineCommand{
    override fun execute(): Boolean {
        TODO("Not yet implemented")
    }

    override fun undo() {
        TODO("Not yet implemented")
    }

}
class PushImageCommand : PipelineCommand{
    override fun execute(): Boolean {
        TODO("Not yet implemented")
    }

    override fun undo() {
        TODO("Not yet implemented")
    }

}
class DeployCommand : PipelineCommand{
    override fun execute(): Boolean {
        TODO("Not yet implemented")
    }

    override fun undo() {
        TODO("Not yet implemented")
    }

}