package com.example.foodproject.wishlist.repository;

import com.example.foodproject.db.MemoryDbRepositoryAbstract;
import com.example.foodproject.wishlist.entity.WishListEntity;
import org.springframework.stereotype.Repository;

@Repository
public class WishListRepository extends MemoryDbRepositoryAbstract<WishListEntity> {
}
