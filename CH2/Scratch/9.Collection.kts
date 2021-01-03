// Collection
//1. list
val foods: List<String> = listOf("라면", "갈비", "밥")
//형 추론으로 자료형 생략 가능
val foods1 = listOf("라면", "갈비", "밥")
// 요소를 변경하는 리스트 작성 시 mutableListOf 사용
val foods2 = mutableListOf("라면", "갈비", "밥")

foods2.add("초밥")     //리스트 맨 뒤에 초밥 추가
foods2.removeAt(0)    //맨 앞의 아이템 삭제
foods2[1] = "부찌대찌" //foods[1]의 값을 부찌대찌로 변경

println(foods2)
println(foods2[0])

//2. map
/*
key-value 쌍으로 이루어진 key가 중복될 수 없는 자료구조
*/
// 읽기 전용 map
val map = mapOf("a" to 1, "b" to 2, "c" to 3)
// 변경 가능한 map
val citiesMap = mutableMapOf("한국" to "서울", "일본" to "동경", "중국" to "북경")
// 요소에 덮어쓰기
citiesMap["한국"] = "서울특별시"
// 추가
citiesMap["미국"] = "워싱턴"

// 맵의 키와 값을 탐색
for ((k, v) in citiesMap){
    println("$k -> $v")
}

//3. 집합
/*
중복되지 않는 요소들로 구성된 자료구조
*/
// 읽기 전용 집합
val citySet = setOf("서울", "수원", "부산")
// 수정 가능한 집합
val citySet2 = mutableSetOf("서울", "수원", "부산")
citySet2.add("안양")
citySet2.remove("수원")
//집합의 크기
println(citySet2.size)
//'서울'이 집합에 포함되어 있는지
println(citySet2.contains("서울"))