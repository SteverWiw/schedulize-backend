package com.schedulize.backend.adapters.infrastructure.repository;

import com.schedulize.backend.domain.entities.MenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface MenuRepository extends JpaRepository<MenuEntity, Long> {
    @Query(value = "select COALESCE(json_agg(json_build_object('menuparent',me.name,\n" +
            "'menuchildren',COALESCE((select json_agg(json_build_object('menuName' ,child.name ,\n" +
            "'viewName',vi.name ,\n" +
            "'viewRoute',vi.route ,\n" +
            "'viewIcon',vi.icon  ))\n" +
            "from core.menu child\n" +
            "join core.views vi on child.idview = vi.id\n" +
            "where child.parentid =me.id), '[]'))), '[]')\n" +
            "  from core.menuxrole mr\n" +
            "  join core.menu me on mr.idmenu = me.id\n" +
            " where me.isparent = 'S'\n" +
            "   and mr.idrole = :idRole", nativeQuery = true)
   String getMenuByRole(@Param("idRole") Long idRole);
}
