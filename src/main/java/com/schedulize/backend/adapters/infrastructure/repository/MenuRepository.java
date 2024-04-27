package com.schedulize.backend.adapters.infrastructure.repository;

import com.schedulize.backend.domain.entities.MenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MenuRepository extends JpaRepository<MenuEntity, Long> {
    @Query(value = """
                      select COALESCE(json_agg(json_build_object('menuparent',me.name,
                                                                 'menuchildren',COALESCE((select json_agg(json_build_object('menuName' ,child.name ,
                                                                                                                              'viewName',vi.name ,
                                                                                                                              'viewRoute',vi.route))
                                                                                            from core.menu child
                                                                                            join core.views vi on child.idview = vi.id
                                                                                           where child.parentid =me.id), '[]'))), '[]')
                        from core.menuxrole mr
                        join core.menu me on mr.idmenu = me.id
                       where me.isparent = 'S'
                         and mr.idrole = :idRole""", nativeQuery = true)
   String getMenuByRole(@Param("idRole") Long idRole);

    List<MenuEntity> getMenuEntityByIsParent(@Param("isParent") String isParent);
    List<MenuEntity> getMenuEntityByParentId(@Param("parentId") Long parentId);
}
