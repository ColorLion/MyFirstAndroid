// 기본적으로 고돌린은 객체에 null값을 허용하지 않음
// 사용하려면 별도 연산자가 필요함
//1. null 허용
// val a : String => 초기화 하지 않아 오류 발생
// val a : String = null => 코돌린은 null을 허용하지 않음
val a : String? = null

//2. lateinit으로 늦은 초기화
// 안드로이드의 경우 특정 타이밍에 객체를 초기화 할 때 사용
// 초기화를 잊으면 잘못된 null 값을 참조해 app이 멈출 수 있으니 주의
/*
 1. var 변수에서만 사용 가능
 2. null값으로 초기화 불가능
 3. 초기화 전엔 변수를 사용할 수 없음
 4. int, Long, Double, short 불가능
 */
lateinit var b : String

b = "hello"
println(b)

//3. lazy로 늦은 초기화
// 상수를 늦은 초기화
// 늦은 초기화 시 앱이 시작될 때 연산을 분산시킬 수 있어 빠른 실행에 도움이 된다
val str: String by lazy{
    println("초기화")
    "안녕"
}
println(str)
println(str)

//4. null 값이 아님을 보증
// 변수 뒤에 !!를 추가해 null 값이 아님을 보증함
// 형변환에 사용되는듯
val name: String? = "키다리"

// 에러다 val name2: String = name
val name3: String? = name
val name4: String = name!!

//5. 안전한 호출 ?.
// 메서드 호출 시 .? 사용하면 null이 아닌 경우에만 호출 한다
// 귀찮은 if문 없어도 안전한 호출 가능
val str1: String? = null
var upperCase = if (str1 != null) str1 else null
upperCase = str1?.toUpperCase()
println(upperCase)

//6. 엘비스 연산자 ?:
// 안전한 호출 시 null이 아닌 기본값을 반환하기 위해 사용
// 안전한 호출과 함께 쓰는 것이 일반적인가보다
val str2: String? = null
var upperCase2 = if (str2 != null) str2 else null
upperCase2 = str2?.toUpperCase() ?: "어린 중생아 초기화를 하거라"
println(upperCase2)