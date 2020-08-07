package cn.com.ecenter.xzspxt.service;

import cn.com.ecenter.xzspxt.entity.*;

import java.util.List;
import java.util.Map;

public interface QltQlsxService {

    public List<QltQlsxEntity> findQlsx(Integer pageIndex, Integer pageSize);

    public int fingTotal();

    public Map<String, Object> flowAnalytical(String strXml, String pid);

    public List<NytMateralInfoEntity> materalAnalytical(String strXml, String pid);

    public List<NytChargeitemInfoEntity> chargeitmeAnalytical(String strXml, String pid);

    public List<NytQaInfoEntity> qaAnalytical(String strXml, String pid);

    public List<NytFactInfoEntity> factAnalytical(String strXml, String pid);

    public List<NytAddressInfoEntity> addressAnalytical(String strXml, String pid);

    public List<NytRelatedInfoEntity> relatedAnalytical(String strXml, String pid);




}
