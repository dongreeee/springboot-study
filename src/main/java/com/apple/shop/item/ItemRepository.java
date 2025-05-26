package com.apple.shop.item;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
//<Entity명, id컬럼타입>
}
