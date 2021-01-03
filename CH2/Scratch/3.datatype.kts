//1. 숫자형
/*
Double : 64bit 부동소수점
Float: 32bit 부동소수점
Long: 64bit 정수
Int: 32bit 정수
Short: 16bit 정수
Byte: 8bit 정수
*/
// 코돌린 컴파일러는 자료형을 추론함
val a = 10
val b = 10L
val c = 10.0
val d = 10.0f

//2. 문자형
/*
String: 문자열
Char: 문자 1개
 */
// 코돌린에서 오브젝트 비교시엔 === 사용
var str = "hello"
if (str == "hello"){
    println("보이루")
}else{
    println("자이루")
}
// 문자열 탬플릿
// java
var str2 = "안녕"
println(str2 + "하세요")
// kotlin
println("$str2 하세요")
println("${str2} 하세요")

//3. array
val numbers1: Array<Int> = arrayOf(1, 2, 3, 4)
val numbers2 = arrayOf(1, 2, 3, 4)

println(numbers1[0])
numbers1[0] = 5
println(numbers1[0])