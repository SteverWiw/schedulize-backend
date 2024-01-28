package com.ajsoftware.backendjwtauth.repository;

import com.ajsoftware.backendjwtauth.model.ApiInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ApiInfoRepository extends JpaRepository<ApiInfoEntity,Long> {
    @Query(value = """
            SELECT a.*\s
            FROM ajscore.apiinfo a
            INNER JOIN ajscore.apirole b ON a.id = b.idapi
            INNER JOIN ajscore.role c ON b.idrole = c.id
            where upper(a.apiname) =  upper(:apiname)
            and upper(c.rolename) =  upper(:rolename)""", nativeQuery = true)
    Optional<ApiInfoEntity> findByApiNameWithApiRole(@Param("apiname") String apiName,@Param("rolename") String roleName);


}
