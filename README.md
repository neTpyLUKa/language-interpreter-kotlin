In order to run tests add makeTesting() function call in main

Необходимо реализовать интерпретатор языка, описанного ниже. На стандартный вход подается программа на данном языке. 
Необходимо вывести значение последнего выражения в программе в десятичной системе счисления

Язык задан следующей грамматикой:

<character>  ::= "A" | "B" | "C" | "D" | "E" | "F" | "G" | "H" | "I" | "J" | "K" | "L" | "M" | "N" | "O" | "P" | "Q" | "R" |
"S" | "T" | "U" | "V" | "W" | "X" | "Y" | "Z" | "a" | "b" | "c" | "d" | "e" | "f" | "g" | "h" | "i" | "j" | "k" | "l" | "m" |
"n" | "o" | "p" | "q" | "r" | "s" | "t" | "u" | "v" | "w" | "x" | "y" | "z" | "_"
<digit>   ::= "0" | "1" | "2" | "3" | "4" | "5" | "6" | "7" | "8" | "9"
<number> ::= <digit> | <digit> <number>
<identifier> ::= <character> | <identifier> <character>
<operation> ::= "+" | "-" | "*" | "/" | "%" | ">" | "<" | "="

<constant-expression> ::= "-" <number> | <number>
<binary-expression> ::= "(" <expression> <operation> <expression>  ")"
<argument-list> ::= <expression> | <expression> "," <argument-list>
<call-expression> ::= <identifier> "(" <argument-list> ")"
<if-expression> ::= "[" <expression> "]?(" <expression> "):("<expression>")"


<expression> ::= <identifier>
                  | <constant-expression>
                  | <binary-expression>
                  | <if-expression>
                  | <call-expression>

<parameter-list> ::= <identifier> | <identifier> "," <parameter-list>

<function-definition> ::= <identifier>"(" <parameter_list> ")" "={" <expression> "}"

<function-definition-list> : ""
                             | <function-definition> <EOL>
                             | <function-definition> <EOL> <function-definition-list>

<program> ::= <function-definition-list> <expression>

<EOL> - символ перевода строки --- \n, программа не содержит других пробельных символов(пробел, табуляция, и т.д.);

Семантика языка задается следующим образом:

    Все переменные имеют тип 32-битный Integer;
    Гаранитруется, что вычисления не приводят к переполнению;
    Все арифметические операции аналогичны соответствующим операциям для 32-битного int в языка Java;
    Операции сравнения возвращают 1 если сравнение истинно и 0 если ложно;
    <if-expression> исполняет второе выражение, если первое выражение не равно 0; иначе исполняет третье;
    <call-expression> вызывает функцию с соответствующим именем
    Выражение вычисляются слева направо;

Выполнение задания будет оцениваться по следующим пунктам:

    Калькулятор: На вход подается корректная программа без <if-expression>, у которой <function-definition-list> пустой.

    Пример:

    (2+2)

    Ответ:

    4

    Пример:

    (2+((3*4)/5))

    Ответ:

    4

    Поддержка <if-expression>: в программе присутствуют <if-expression> Пример:

    [((10+20)>(20+10))]?{1}:{0}

    Ответ:

    0

    Поддержка функций: <function-definition-list> не пустой Пример:

    g(x)={(f(x)+f((x/2)))}
    f(x)={[(x>1)]?{(f((x-1))+f((x-2)))}:{x}}
    g(10)

    Ответ:

    60

    Обработка ошибок:

        Если программа не соответствует грамматике необходимо вывести:

        SYNTAX ERROR

        Если в программе используется неопределенная переменная необходимо вывести:

        PARAMETER NOT FOUND <name>:<line>

        здесь и далее <name> и <line> это ошибочное имя и номер строки на которой произошла ошибка

        Если программа вызывает функцию с неизвестным именем, то необходимо вывести:

        FUNCTION NOT FOUND <name>:<line>

        Если программа вызывает функцию с неверным числом аргументов, то необходимо вывести:

        ARGUMENT NUMBER MISMATCH <name>:<line>

        Если произошла ошибка выполнения необходимо, то вывести:

        RUNTIME ERROR <expression>:<line>

        <expression> --- выражение в котором произошла ошибка.

    Пример:

    1 + 2 + 3 + 4 + 5

    Ответ:

    SYNTAX ERROR

    Пример:

    f(x)={y}
    f(10)

    Ответ:

    PARAMETER NOT FOUND y:1

    Пример:

    g(x)={f(x)}
    g(10)

    Ответ:

    FUNCTION NOT FOUND f:1

    Пример:

    g(x)={(x+1)}
    g(10,20)

    Ответ:

    ARGUMENT NUMBER MISMATCH g:2

    Пример:

    g(a,b)={(a/b)}
    g(10,0)

    Ответ:

    RUNTIME ERROR (a/b):1

Пункты необходимо выполнять последовательно. Чем больше будет выполнено пунктов задания - тем лучше. Задание необходимо выполнить на языке Java или Kotlin. Итоговое решение должно иметь бинарные зависимости только на стандартную библиотеку и тестовый фреймфокр(по желанию). Ответ на данное задание это ссылка на git репозиторий на github'е или любом другом публичном гит хостинге. Commit message должны быть осмысленными. Решение также должно включать в себя тесты.
