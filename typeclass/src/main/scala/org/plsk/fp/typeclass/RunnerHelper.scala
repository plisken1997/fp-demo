package org.plsk.fp.typeclass

trait RunnerHelper {

  def printSection(section: String)(content: => Any) = {
    println(s"${(0 until 10).map(_ => "#").mkString} $section ${(0 until 10).map(_ => "#").mkString}")
    println(content)
    println("---")
  }

}
