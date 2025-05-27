package com.apple.shop.item;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private Integer price;



//    클래스 변수, 함수에 붙이는 modifier
//    1. public 붙이면 모든 곳에서 사용가능
//    2. 안 붙이면 package-private (같은 폴더의 클래스에서만 사용가능)
//    3. private 붙이면 다른 클래스에서 사용불가
//    4. protected 붙이면 package-private와 같음
//    (예외 : 상속한 클래스는 마음대로 사용 가능)
//    5. static 붙이면 클래스.변수 이렇게 직접 사용가능
}
