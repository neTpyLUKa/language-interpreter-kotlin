import kotlin.test.assertEquals

fun makeTesting() {
    assertEquals(addFun("f(x)={(x+2)}"), true)

    assertEquals(evalExpr("(f(3)*(f(6)-f(4)))"), 10)

    assertEquals(evalExpr("((1+1)*((2+3)/(2)))"), 4)

    assertEquals(addFun("g(x,y)={(x*y)}"),true)

    assertEquals(evalExpr("g(g(1,2),g(3,4))"), 24)

    assertEquals(evalExpr("[(f(4)>5)]?{1}:{2}"), 1)

    assertEquals(addFun("t(x)={[(x>6)]?{(x*x)}:{x}}"), true)

    assertEquals(evalExpr("[(t(8)>64)]?{2}:{(8/7)}"), 1)

    assertEquals(evalExpr("(t(9)/t(7))"), 1)

    assertEquals(evalExpr("(t(10)/t(7))"), 2)

    println("All tests passed")
}