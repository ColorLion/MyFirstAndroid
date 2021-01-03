//1. if
val a = 10
val b = 20

var max = a
if ( a < b ) max =b

if (a > b){
    max = a
    println(max)
}else{
    max = b
    println(max)
}

val max2 = if (a > b) a else b
println(max2)

//2. when
// switch 문에 해당함
val x = 1
when (x){
    1 -> println("x == 1")
    2, 3 -> println("x == 2 or x == 3")
    in 4..7 -> println("x == 4~7")
    !in 8..10-> println("x == 8~10 x")
    else -> {
        print("x는 1혹은 2가 아님")
    }
}
//식 처럼 사용할 수 있음
val numberStr = 1
val numStr = when( numberStr % 2 ){
    0 -> "짝"
    else -> "홀"
}
// 함수의 return 값으로도 사용할 수 있음
val number = 1
fun isEven(num: Int) = when (num % 2){
    0 -> "짝"
    else -> "홀"
}
println(isEven(number))

//3. for
val numbers  = arrayOf(1, 2, 3, 4, 5)
for (num in numbers){
    print(num)
}
for (i in 1..3){
    print(i)
}
for(i in 0..10 step 2){
    print(i)
}
for(i in 10 downTo 0 step 2){
    print(i)
}

//4. while
var x1 = 10
println(x1)
while(x1 > 0){
    x1--
    println(x1)
}

var x2 = 10
do{
    x2--
    println(x2)
}while (x2 > 0)