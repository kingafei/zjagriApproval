package cn.com.ecenter.common.entity;

import cn.com.ecenter.system.entity.Dept;
import cn.com.ecenter.xzspxt.entity.po.NytDataDictEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author MrBird
 */
@Data
public class DictTree<T> implements Serializable {

    private static final long serialVersionUID = 7681873362531265829L;

    private String id;
    private String icon;
    private String href;
    private String name;
    private Map<String, Object> state;
    private boolean checked = false;
    private Map<String, Object> attributes;
    private List<DictTree<T>> children = new ArrayList<>();
    private String parentId;
    private boolean hasParent = false;
    private boolean hasChild = false;

    private NytDataDictEntity data;


}
