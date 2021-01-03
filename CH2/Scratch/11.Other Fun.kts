// 기타 기능
//1. 확장 함수
/*
기존 클래스에 함수를 추가하는 것 => Int라는 a, b변수가 Int 클래스 이기 때문에 함수를 추가할 수 있는 것
확장 함수 내부에선 이 객체를 this로 접근할 수 있고 이걸 리시버 객체라고 함
그리고 자바에선 이런거 하려면 상속을 받고 추가 메서드를 작성해야 했으며 String 클래스는 final로 상속이 막혀있어 불가능 햇음 ㅋㅋ
*/
fun Int.isEven() = this % 2 == 0

val a = 5
val b = 6

println(a.isEven())
println(b.isEven())

//2. 형변환
// 숫자형 끼린 to 뭐시기로 가능
val c = 10L
val d = 20

val e = c.toInt()
val f = d.toDouble()
val g = c.toString()
//숫자 형태의 문자열을 숫자로 바꿀 때
val intStr = "10"
val str = Integer.parseInt(intStr)

//일반 클래스 간 형변환 as 사용
open class Animal

class Dog: Animal()

val dog = Dog()
val animal = dog as Animal

//3. 형 체크
val str1 = "hello"

if (str1 is String){
    println(str1.toUpperCase())
}

//4. 고차함수
/*
함수의 인수로 함수를 받거나 함수를 반환하는 함수를 고차함수라고 함
*/
// 인수: 숫자, 숫자, 하나의 숫자를 인수로하는 반환값이 없는 함수
fun add(x: Int, y:Int, callback: (sun: Int) -> Unit){
    callback(x + y)
}
// 함수는 {}로 감싸고 내부에서는 반환값을 it으로 접근할 수 있음
add(5, 3, {println(it)})

//5. 동반 객체
/*
프래그먼트는 특수한 제약 때문에 팩토리 메서드를 정의하여 인스턴스를 생성해야 함.
팩토리 메서드는 생성자가 아닌 메서드를 사용해 객체를 생성하는 코딩 패턴, 클래스와 별개로 보며 포함관계도 아님
*/
class Fragment{
    companion object{
        fun newInstance() {
            println("생성됨")
        }
    }
}

val fragment = Fragment.newInstance()

//6. let()
// 블록에 자기 자신을 인수로 전달하고 수행된 결과를 반환
// 인수로 전달된 객체는 it으로 참조
// run <T, R> T.let(block: (T) -> R): R
val stringInt = "123"
val result = stringInt?.let{
    Integer.parseInt(stringInt)
} // 요 코드의 경우 str이 null이 아닐 경우 숫자로 바꾸는 코드겟지, if문을 쉽게 대체할 수 있다는 점이 좋은듯

//7. with()
// 인수로 객체를 받고 블록에 리비서 객체로 전달후 수행된 결과를 반환
// 리시버 객체로 전달된 객체는 this로 접근 가능, 생략도 가능
//fun <T, R> with(receiver: T, block T.() -> R): R
val testString = "Test"
with(testString){
    println(this.toUpperCase())
} // 솔직히 잘 모르겟음, 나와봐야알듯

//8. apply()
//블록에 객체 자신이 리시버 객체로 전달되고 이 객체가 반환함
// 객체의 상태를 변화시키고 그 객체를 다시 반환할 때 사용

val result = car?.apply{
    car.setColor(color.RED)
    car.setPrice(1000)
}

//9. run()
// 객체에서 호출하는 방법 모두 제공
// 익명 함수 처럼 사용시 블록의 결과를 반환하며 블록안에 선언된 변수는 모두 임시변수
// 복잡한 계산에 임시변수가 많이 필요할 때 유용함
val avg = run{
    val korean = 100
    val english = 90
    val math = 40

    (korean + english + math) / 3
}
str?.run{
    println(toUpperCase())
}