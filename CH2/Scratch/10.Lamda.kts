//람다 식
// 하나의 함수를 표현하는 방법으로 클래스나 익명 함수를 간결하게 표현 가능하지만 디버깅이 어렵고 코드 가독성이 떨어질 수 있음
//예제
fun add(x: Int, y: Int): Int{
    return x + y
}
//fun add(x: Int, y: Int) = x + y
/*
var add = {x: Int, y: Int -> x + y}
println(add(2, 5))
*/

//1. SAM(Single Abstract Method) 변환
/*
추상 메서드 하나를 인수로 사용할 때 함수를 인수로 전달하면 편함
자바로 작성된 메서드가 하나인 인터페이스를 구현할 땐 대신 함수를 작성할 수 있음
가장 중요한 것은 SAM 변환은 자바에서 작성한 인터페이스 일때 만 동작함
코돌린에선 인터페이스 대신 함수를 사용하는 것이 좋다
*/
//View.OnClickListener 인터페이스에는 onClick() 추상 메서드가 있기 때문에 override 함
//이때 이걸 람다 식으로 변환할 수 있다
button.setOnClickListener(object: View.OnClickLister{
    override fun onClick(v: View?){
        //클릭 시 처리
    }
})
// 람다 식
button.setOnClickListener({ v: View? ->
    //클릭 시 처리
})
//가장 깔끔하게 변환
button.setOnClickListener{
    it.visibility = View.GONE
}