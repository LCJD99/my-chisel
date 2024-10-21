package lab2

import chisel3._
import chisel3.util._

class SevenSegmentDisplay extends Module {
  val io = IO(new Bundle {
    val valid = Input(UInt(1.W))
    val in = Input(UInt(4.W))
    val out = Output(UInt(7.W))
  })

  when(io.valid === 1.U) {
    io.out := ~MuxLookup(io.in, 0.U(4.W))(Seq(
      0.U -> "b1111110".U,  
      1.U -> "b0110000".U,  
      2.U -> "b1101101".U,  
      3.U -> "b1111001".U,  
      4.U -> "b0110011".U,  
      5.U -> "b1011011".U,  
      6.U -> "b1011111".U,  
      7.U -> "b1110000".U,  
      8.U -> "b1111111".U,  
      9.U -> "b1111011".U   
    )) 
  }.otherwise {
    io.out := "b1111111".U
  }
  
}