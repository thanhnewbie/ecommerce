package com.example.ecommerce.ecommerce.Repository;

import com.example.ecommerce.ecommerce.DAO.Gallery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GalleryRepository extends JpaRepository<Gallery, Long> {
}
