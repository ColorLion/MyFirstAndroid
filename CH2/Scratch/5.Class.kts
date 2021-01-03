//1. 클래스 선언
// 클래스 선언
class Person1{

}
// 인스턴스 생성
val person = Person1()

//2. 생성자
class Person2(var name: String){

}
// 생성자에서 초기화 코드를 작성하려면 constructor로 생성자를 표현
class Person3{
    constructor(name: String){
        println(name)
    }
}
//init 블록에 작성한 코드가 클래스를 인스턴스화 할 때 먼저 초기화됨
class Person4(name: String){
    init{
        println(name)
    }
}

//3. 프로퍼티
// 클래스의 속성을 사용할 때는 멤버에 직접 접근하는데 이를 프로퍼티 라고 함
// Person 클래스는 name 프로퍼티를 가지고 있음, 프로퍼티에 값을 쓰려면 = 기호로 값을 대입
// 클래스 선언
class Person5(var name: String){

}
// 인스턴스 생성
val person5 = Person5("멋쟁이")
person5.name = "키다리"
println(person5.name)
//자바에서의 경우
/*
class JavaClass{
    private String name;

    public Person(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }
}
 */

//4. 접근 제한자
// 변수나 함수를 공개하는데 사용하는 키워드
/*
public(생략 가능): 전체 공개, default
private: 현재 파일 내부에서만 사용
internal: 같은 모듈 내에서만 사용
protected: 상송받은 클래스에서 사용 가능
 */
class A{
    val a = 1   //public
    private val b  = 2
    internal val c = 3
    protected val d = 4
}
// 이 책에서는 1개의 모듈만을 사용하지만 여러 모듈을 생성할 수 있음
// 같은 앱을 스마트폰용, tv, 시계용으로 만들면 3개의 모듈이 필요
// internal은 모듈간 접근을 제한 하는 것

//5. 클래스의 상속
// 기본적으로 코틀린은 상속 금지, 하지만 class앞에 open을 써서 할 수 는 있음
open class Animal1{

}
class Dog1 : Animal1(){

}
// 생성자 포함 가능
open class Animal2(val name: String){

}
class Dog2(name: String) : Animal2(name){

}

//6. 내부 클래스
// 내부 클래스는 외부 클래스에 대한 참조를 가지고 있음
class OuterGod{
    var a = 10

    // 내부 클래스
    inner class InnerGod{
        fun testSomething(){
            a = 20      // 접근 가능
        }
    }
}
// 만약 위의 코드에서 inner가 없다면 접근 불가능

//7. 추상 클래스
// 미구현 매서드가 포함된 클래스
// 미구현 매서드 앞엔 abstract 키워드를 붙어야 함
// 추상 클래스는 직접 인스턴스화 불가능, 다른 클래스가 상속 해 미구현 매서드 구현
abstract class AbCl{
    abstract fun func()

    fun func2(){

    }
}
class BCl : AbCl(){
    override fun func(){
        println("Hello World")
    }
}

//val aTest = AbCl() -> 에러남
val bTest = BCl() // 미구현 매서드를 구현해서 에러 안나는 듯