package lab2

import chisel3._
import chisel3.util._

class PriorityEncoderWithValid extends Module {
  val io = IO(new Bundle {
    val in = Input(UInt(8.W))      // 8 位输入
    val out = Output(UInt(3.W))    // 3 位输出表示最高位的 1 的位置
    val valid = Output(Bool())     // 1 位有效位，表示是否有任何输入为 1
  })

  val r0 = 7.U(3.W) - PriorityEncoder(Reverse(io.in))
  io.out := r0

  io.valid := io.in.orR
}
