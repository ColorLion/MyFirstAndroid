//1. 인터페이스의 선언
/*
    클래스는 단일 상속만 가능하지만 인터페이스는 다중 상속이 된다
    주로 클래스에 동일한 속성을 부여해 같은 매서드라도 다른 행동을 할 수 있게 만듦
 */
// 추상 클래스에선 abstract가 필요하지만 인터페이스는 필요 없다
interface Runnable{
    fun run()
}
// 구현된 메서드를 포함할 수 잇음
interface Runnable2{
    fun run()

    fun fastRun() = print("test")
}

//2.인터페이스의 구현
// 미구현 메서드를 작성할 땐 override를 추가
class Human : Runnable{
    override fun run(){
        println("헤헤 달린다")
    }
}

//3. 상속과 인터페이스를 함께 구현
// 상속은 하나의 클래스만 상속한다
// 인터페이스는 콤마로 구분해 여러 인터페이스를 동시에 구현 가능
open class Animal{

}

interface Runnable3{
    fun run()

    fun fastRun() = print("헤헤 빠르다")
}

interface Eatable{
    fun eat()
}

class Dog : Animal(), Runnable3, Eatable{
    override fun run(){
        println("헤헤 달린다")
    }
    override fun eat(){
        println("헤헤 먹는다")
    }
}

val dog = Dog()
dog.run()
dog.eat()