package lab2

import chisel3._
import chisel3.testers._
import chisel3.util._
import chiseltest._
import chiseltest.formal._
import chiseltest.simulator.WriteVcdAnnotation
import org.scalatest.flatspec.AnyFlatSpec

class PriorityEncoderWithValidTest extends AnyFlatSpec with ChiselScalatestTester {
  "PriorityEncoderWithValid" should "encode correctly" in {
    test(new PriorityEncoderWithValid()) { c =>
      c.io.in.poke("b00000000".U)
      c.clock.step(1)
      c.io.valid.expect(0.B)

      c.io.in.poke("b00001000".U)
      c.clock.step(1)
      c.io.out.expect(3.U)
      c.io.valid.expect(1.B)

      c.io.in.poke("b10001001".U)
      c.clock.step(1)
      c.io.out.expect(7.U)
      c.io.valid.expect(1.B)

      c.io.in.poke("b00100001".U)
      c.clock.step(1)
      c.io.out.expect(5.U)
      c.io.valid.expect(1.B)
    }
  }
}