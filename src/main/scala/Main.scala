import chisel3._
import _root_.circt.stage.ChiselStage
import lab6.RandomNum

object Main extends App{
  ChiselStage.emitSystemVerilogFile(
    new RandomNum,
    firtoolOpts = Array("-disable-all-randomization", "-strip-debug-info")
  )
}