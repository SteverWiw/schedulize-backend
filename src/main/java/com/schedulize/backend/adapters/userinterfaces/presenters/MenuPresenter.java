package com.schedulize.backend.adapters.userinterfaces.presenters;

import com.schedulize.backend.domain.entities.MenuEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MenuPresenter {
    private Long id;
    private String name;
    private String isParent;
    private Long idView;
    private Long parentId;
    private String status;

    public static MenuPresenter fromEntity(MenuEntity entity) {
        MenuPresenter presenter = new MenuPresenter();
        presenter.setId(entity.getId());
        presenter.setName(entity.getName());
        presenter.setIsParent(entity.getIsParent());
        presenter.setIdView(entity.getIdView());
        presenter.setParentId(entity.getParentId());
        presenter.setStatus(entity.getStatus());
        return presenter;
    }
}
