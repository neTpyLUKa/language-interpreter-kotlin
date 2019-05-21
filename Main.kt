val funs = mutableMapOf<String, String>() // name, function body

val args = mutableMapOf<String, MutableMap<String, Int>>() // variable name, index

fun addFun(s: String): Boolean {
    if (s.isEmpty()) {
        return true
    }
    if (s[0].isLetter()) {
        var i = 0
        while (s[i].isLetter()) {
            ++i
        }

        val name = s.substring(0, i)
        var k = i + 1
        var cnt = 1
        while (cnt > 0) {
            if (s[k] == '(') ++cnt
            if (s[k] == ')') --cnt
            ++k
        }

        if (k == s.length) {
            return false
        }

        args[name] = mutableMapOf()
        val l = s.substring(i + 1, k - 1).split(',')

        for (j in l.indices) {
            args[name]!![l[j]] = j
        }

        funs[name] = s.substring(k + 2, s.length - 1)
        return true
    }
    return false
}

fun evalExpr(s: String): Int { // value, index
    if (s.isEmpty()) {
        return 0
    }

    if (s[0] == '[') {
        var cnt = 1
        var i = 1
        while (cnt > 0) {
            if (s[i] == '[') ++cnt
            if (s[i] == ']') --cnt
            ++i
        }
        val condition = evalExpr(s.substring(1, i - 1))

        i += 2
        var prev = i
        cnt = 1
        while (cnt > 0) {
            if (s[i] == '{') ++cnt
            if (s[i] == '}') --cnt
            ++i
        }

        if (condition != 0) {
            return evalExpr(s.substring(prev, i - 1))
        }

        i += 2
        prev = i
        cnt = 1
        while (cnt > 0) {
            if (s[i] == '{') ++cnt
            if (s[i] == '}') --cnt
            ++i
        }
        return evalExpr(s.substring(prev, i - 1))
    }
    var i = 0
    var lhs = 0

    if (s[0].isLetter()) {
        while (s[i].isLetter()) {
            ++i
        }

        var prev = i + 1
        val name = s.substring(0, i)
        i += 1
        var cnt = 1
        while (cnt > 0) {
            if (s[i] == '(') ++cnt
            if (s[i] == ')') --cnt
            ++i
        }

        val argsList = mutableListOf<String>()
        var j = prev
        cnt = 0
        while (j < i) {
            if (s[j] == '(') ++cnt
            if (s[j] == ')') --cnt
            if (cnt == 0 && s[j] == ',' || cnt == -1 && s[j] == ')') {
                argsList.add(s.substring(prev, j))
                prev = j + 1
            }
            ++j
        }

        var new = ""
        j = 0

        while (j < funs[name]!!.length) {
            if (funs[name]!![j].isLowerCase()) {
                prev = j
                while (funs[name]!![j].isLetter()) {
                    ++j
                }
                val arg = funs[name]!!.substring(prev, j)
                if (args[name]!![arg] != null) {
                 //   println(args[name]!![arg])
                    new += argsList[args[name]!![arg]!!]
                } else {
                    new += arg
                }
            } else {
                new += funs[name]!![j]
                ++j
            }
        }

        lhs = evalExpr(new)
    } else if (s[0] == '(') {
        var cnt = 1
        i = 1

        while (cnt > 0) {
            if (s[i] == '(') ++cnt
            if (s[i] == ')') --cnt
            ++i
        }

        lhs = evalExpr(s.substring(1, i - 1))
    } else if (s[0].isDigit()) {
        while (i < s.length && s[i].isDigit()) {
            ++i
        }
        lhs = s.substring(0, i).toInt()
    }
    if (i == s.length) {
        return lhs
    }

    val op = s[i]
    ++i
    val rhs = evalExpr(s.substring(i))

    return when (op) {
        '+' -> lhs + rhs
        '*' -> lhs * rhs
        '/' -> lhs / rhs
        '>' -> if (lhs > rhs) 1 else 0
        '<' -> if (lhs < rhs) 1 else 0
        '=' -> if (lhs == rhs) 1 else 0
        '%' -> lhs % rhs
        else -> lhs - rhs
    }
}

fun main() {
    var s = readLine()
    var last = 0
    while (s != null) {
        if (!addFun(s)) {
            last = evalExpr(s)
        }
        s = readLine()
    }
    println(last)
}