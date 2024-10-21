import chisel3._
import _root_.circt.stage.ChiselStage
import lab2.DigitalDisplay

object Main extends App{
  ChiselStage.emitSystemVerilogFile(
    new DigitalDisplay,
    firtoolOpts = Array("-disable-all-randomization", "-strip-debug-info")
  )
}