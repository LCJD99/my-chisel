package lab2

import chisel3._
import chisel3.testers._
import chisel3.util._
import chiseltest._
import chiseltest.formal._
import chiseltest.simulator.WriteVcdAnnotation
import org.scalatest.flatspec.AnyFlatSpec

class SevenSegmentDisplayTest extends AnyFlatSpec with ChiselScalatestTester {
  "SevenSegmentDisplay" should "decode correctly" in {
    test(new SevenSegmentDisplay()) { c =>
      c.io.in.poke(0.U)
      c.clock.step(1)
      c.io.out.expect("b1111110".U)
    }
  }
}