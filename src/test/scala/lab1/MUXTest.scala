package lab1

import chisel3._
import chisel3.testers._
import chisel3.util._
import chiseltest._
import chiseltest.formal._
import chiseltest.simulator.WriteVcdAnnotation
import org.scalatest.flatspec.AnyFlatSpec

class MUXTest extends AnyFlatSpec with ChiselScalatestTester {
  "MUX" should "pass" in {
    test(new MUX)
      .withAnnotations(Seq(VerilatorBackendAnnotation, WriteVcdAnnotation)) { c =>
      c.io.x0.poke(0.U)
      c.io.x1.poke(1.U)
      c.io.x2.poke(2.U)
      c.io.x3.poke(3.U)
      c.io.y.poke(1.U)   // 选择 x1
      c.clock.step(1)
      c.io.f.expect(1.U) // 输出应该是 x1 的值

      c.io.y.poke(2.U)   // 选择 x2
      c.clock.step(1)
      c.io.f.expect(2.U) // 输出应该是 x2 的值
    }
  }
}