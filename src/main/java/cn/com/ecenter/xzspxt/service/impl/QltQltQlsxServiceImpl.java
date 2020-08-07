package cn.com.ecenter.xzspxt.service.impl;

import cn.com.ecenter.xzspxt.entity.*;
import cn.com.ecenter.xzspxt.mapper.QltQlsxMapper;
import cn.com.ecenter.xzspxt.service.QltQlsxService;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.ByteArrayInputStream;
import java.util.*;

@Service
public class QltQltQlsxServiceImpl implements QltQlsxService {

    @Autowired
    private QltQlsxMapper qltQlsxMapper;

    /**
     * 分页查询所有需要同步的数据
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @Override
    public List<QltQlsxEntity> findQlsx(Integer pageIndex, Integer pageSize) {
        List<QltQlsxEntity> result = qltQlsxMapper.findQlsx(pageIndex, pageSize);
        return result;
    }

    /**
     * 查询所需要同步的数据的总条数
     * @return
     */
    @Override
    public int fingTotal() {
        int total = qltQlsxMapper.fingTotal();
        return total;
    }


    /**
     * 流程图XML解析
     * @param strXml
     * @return
     */
    @Override
    public Map<String, Object> flowAnalytical(String strXml, String pid) {
        String replace = strXml.replace("<农村工作办公室>", "&lt;农村工作办公室&gt;");
        SAXReader reader = new SAXReader();
        Document document = null;
        try {
            document = reader.read(new ByteArrayInputStream(replace.getBytes()));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        //定义变量
        Map<String, Object> resultMap = new HashMap<String, Object>();
        List<NytFlowInfoEntity> flowList = new ArrayList<NytFlowInfoEntity>();//岗位流程环节
        List<NytFileInfoEntity> fileList = new ArrayList<NytFileInfoEntity>();

        String flowId; //流程内部ID（内部关联使用）
        String changeFlagP; //岗位信息变化标识0：未变化，1：变化
        String changeFlagA; //环节信息变化标识 0：未变化，1：变化
        String flowTitle; //流程名称
        List<Element> positions = new ArrayList<Element>(); //岗位
        List<Element> flowActivitys = new ArrayList<Element>();; //环节
        List<Element> transations = new ArrayList<Element>();; //环节处理流程

        /**
         * 节点对象的操作方法
         */
        //获取文档根节点
        Element root = document.getRootElement();
        changeFlagP = root.element("P_CHANGE_FLAG").getTextTrim();
        changeFlagA = root.element("A_CHANGE_FLAG").getTextTrim();
        flowId = root.element("FLOW_ID").getTextTrim();
        flowTitle = root.element("FLOW_TITLE").getTextTrim();

        positions = root.element("POSITIONS").elements();
        flowActivitys = root.element("FLOW_ACTIVITYS").elements();
        transations = root.element("TRANSATIONS").elements();

        //定义子节点里的变量
        String positionId = ""; //岗位唯一标识
        String activityId = ""; //环节ID（内部关联使用）
        String temp = ""; //临时变量
        // 遍历最长的集合，
        if (positions.size() > flowActivitys.size() && positions.size() > transations.size()){
            // 以岗位为主遍历
            for (Element e : positions) {
                NytFlowInfoEntity flow = new NytFlowInfoEntity();
                flow.setpId(pid);
                flow.setChangeFlagP(changeFlagP);
                flow.setChangeFlagA(changeFlagA);
                flow.setFlowId(flowId);
                flow.setFlowTitle(flowTitle);
                positionId = e.element("POSITION_ID").getTextTrim();
                flow.setPositionId(e.element("POSITION_ID").getTextTrim());
                flow.setPositionName(e.element("NAME").getTextTrim());
                flow.setPositionDuty(e.element("DUTY").getTextTrim());
                flow.setDepartment(e.element("DEPARTMENT").getTextTrim());
                flow.setPerson(e.element("PERSON").getTextTrim());
                flow.setPersonGuids(e.element("PERSONGUIDS").getTextTrim());
                flow.setPersonNames(e.element("PERSONS").getTextTrim());
                for (Element e1 : flowActivitys) {
                    temp = e1.element("POSITION_ID").getTextTrim();
                    if (!positionId.equals(temp)){
                        continue;
                    }
                    activityId = e1.element("ACTIVITY_ID").getTextTrim();
                    flow.setActivityId(e1.element("ACTIVITY_ID").getTextTrim());
                    flow.setActivityType(e1.element("TYPE").getTextTrim());
                    flow.setActivityTitle(e1.element("TITLE").getTextTrim());
                    flow.setHandleTimelimt(e1.element("HANDLE_TIMELIMIT").getTextTrim());
                    flow.setHandleTimelimttype(e1.element("HANDLE_TIMELIMIT_TYPE").getTextTrim());
                    List<Element> fileElement = e1.element("FILES").elements();
                    for (Element fel : fileElement) {
                        NytFileInfoEntity file = new NytFileInfoEntity();
                        file.setpId(e1.element("ACTIVITY_ID").getTextTrim());
                        file.setFilename(fel.element("FILENAME").getTextTrim());
                        file.setFilekind(fel.element("FILEKIND").getTextTrim());
                        file.setFilecontent(fel.element("FILECONTENT").getTextTrim());
                        file.setFileurl(fel.element("FILEURL").getTextTrim());
                        file.setCreateTime(new Date());
                        file.setUpdateTime(new Date());
                        file.setIsDelete("0");
                        fileList.add(file);
                    }
                    break;
                }
                for (Element e1 : transations) {
                    temp = e1.element("TARGET_FLOW_ACTIVITY_ID").getTextTrim();
                    if (!activityId.equals(temp)){
                        continue;
                    }
                    flow.setTargetActivityId(e1.element("TARGET_FLOW_ACTIVITY_ID").getTextTrim());
                    flow.setSourceActivityId(e1.element("SOURCE_FLOW_ACTIVITY_ID").getTextTrim());
                    flow.setNote(e1.element("NOTE").getTextTrim());
                    break;
                }
                flow.setCreateTime(new Date());
                flow.setUpdateTime(new Date());
                flow.setIsDelete("0");
                flowList.add(flow);
            }
        } else if (flowActivitys.size() > positions.size() && flowActivitys.size() > transations.size()){
            //以环节为主遍历
            for (Element e : flowActivitys) {
                NytFlowInfoEntity flow = new NytFlowInfoEntity();
                flow.setpId(pid);
                flow.setChangeFlagP(changeFlagP);
                flow.setChangeFlagA(changeFlagA);
                flow.setFlowId(flowId);
                flow.setFlowTitle(flowTitle);
                activityId = e.element("ACTIVITY_ID").getTextTrim();
                flow.setActivityId(e.element("ACTIVITY_ID").getTextTrim());
                flow.setActivityType(e.element("TYPE").getTextTrim());
                flow.setActivityTitle(e.element("TITLE").getTextTrim());
                positionId = e.element("POSITION_ID").getTextTrim();
                flow.setPositionId(e.element("POSITION_ID").getTextTrim());
                flow.setHandleTimelimt(e.element("HANDLE_TIMELIMIT").getTextTrim());
                flow.setHandleTimelimttype(e.element("HANDLE_TIMELIMIT_TYPE").getTextTrim());
                List<Element> fileElement = e.element("FILES").elements();
                for (Element fel : fileElement) {
                    NytFileInfoEntity file = new NytFileInfoEntity();
                    file.setpId(e.element("ACTIVITY_ID").getTextTrim());
                    file.setFilename(fel.element("FILENAME").getTextTrim());
                    file.setFilekind(fel.element("FILEKIND").getTextTrim());
                    file.setFilecontent(fel.element("FILECONTENT").getTextTrim());
                    file.setFileurl(fel.element("FILEURL").getTextTrim());
                    file.setCreateTime(new Date());
                    file.setUpdateTime(new Date());
                    file.setIsDelete("0");
                    fileList.add(file);
                }
                for (Element e1 : positions) {
                    temp = e1.element("POSITION_ID").getTextTrim();
                    if (!positionId.equals(temp)){
                        continue;
                    }
                    flow.setPositionName(e1.element("NAME").getTextTrim());
                    flow.setPositionDuty(e1.element("DUTY").getTextTrim());
                    flow.setDepartment(e1.element("DEPARTMENT").getTextTrim());
                    flow.setPerson(e1.element("PERSON").getTextTrim());
                    flow.setPersonGuids(e1.element("PERSONGUIDS").getTextTrim());
                    flow.setPersonNames(e1.element("PERSONS").getTextTrim());
                    break;
                }
                for (Element e1 : transations) {
                    temp = e1.element("TARGET_FLOW_ACTIVITY_ID").getTextTrim();
                    if (!activityId.equals(temp)){
                        continue;
                    }
                    flow.setTargetActivityId(e1.element("TARGET_FLOW_ACTIVITY_ID").getTextTrim());
                    flow.setSourceActivityId(e1.element("SOURCE_FLOW_ACTIVITY_ID").getTextTrim());
                    flow.setNote(e1.element("NOTE").getTextTrim());
                    break;
                }
                flow.setCreateTime(new Date());
                flow.setUpdateTime(new Date());
                flow.setIsDelete("0");
                flowList.add(flow);
            }
        } else {
            // 以环节流程发运转遍历
            for (Element e : transations) {
                NytFlowInfoEntity flow = new NytFlowInfoEntity();
                flow.setpId(pid);
                flow.setChangeFlagP(changeFlagP);
                flow.setChangeFlagA(changeFlagA);
                flow.setFlowId(flowId);
                flow.setFlowTitle(flowTitle);
                flow.setSourceActivityId(e.element("SOURCE_FLOW_ACTIVITY_ID").getTextTrim());
                activityId = e.element("TARGET_FLOW_ACTIVITY_ID").getTextTrim();
                flow.setTargetActivityId(e.element("TARGET_FLOW_ACTIVITY_ID").getTextTrim());
                flow.setNote(e.element("NOTE").getTextTrim());
                for (Element e1 : flowActivitys) {
                    temp = e1.element("ACTIVITY_ID").getTextTrim();
                    if (!temp.equals(activityId)){
                        continue;
                    }
                    flow.setActivityId(e1.element("ACTIVITY_ID").getTextTrim());
                    flow.setActivityType(e1.element("TYPE").getTextTrim());
                    flow.setActivityId(e1.element("TITLE").getTextTrim());
                    positionId = e1.element("POSITION_ID").getTextTrim();
                    flow.setPositionId(e1.element("POSITION_ID").getTextTrim());
                    flow.setHandleTimelimt(e1.element("HANDLE_TIMELIMIT").getTextTrim());
                    flow.setHandleTimelimttype(e1.element("HANDLE_TIMELIMIT_TYPE").getTextTrim());
                    List<Element> fileElement = e1.element("FILES").elements();
                    for (Element fel : fileElement) {
                        NytFileInfoEntity file = new NytFileInfoEntity();
                        file.setpId(e1.element("ACTIVITY_ID").getTextTrim());
                        file.setFilename(fel.element("FILENAME").getTextTrim());
                        file.setFilekind(fel.element("FILEKIND").getTextTrim());
                        file.setFilecontent(fel.element("FILECONTENT").getTextTrim());
                        file.setFileurl(fel.element("FILEURL").getTextTrim());
                        file.setCreateTime(new Date());
                        file.setUpdateTime(new Date());
                        file.setIsDelete("0");
                        fileList.add(file);
                    }
                    break;
                }
                for (Element e1 : positions) {
                    temp = e1.element("POSITION_ID").getTextTrim();
                    if (!positionId.equals(temp)){
                        continue;
                    }
                    flow.setPositionId(e1.element("POSITION_ID").getTextTrim());
                    flow.setPositionName(e1.element("NAME").getTextTrim());
                    flow.setPositionDuty(e1.element("DUTY").getTextTrim());
                    flow.setDepartment(e1.element("DEPARTMENT").getTextTrim());
                    flow.setPerson(e1.element("PERSON").getTextTrim());
                    flow.setPersonGuids(e1.element("PERSONGUIDS").getTextTrim());
                    flow.setPersonNames(e1.element("PERSONS").getTextTrim());
                    break;
                }
                flow.setCreateTime(new Date());
                flow.setUpdateTime(new Date());
                flow.setIsDelete("0");
                flowList.add(flow);
            }
        }
        resultMap.put("flowList", flowList);
        resultMap.put("fileList", fileList);
        return resultMap;
    }

    /**
     * 材料格式说明XML解析
     * @param strXml
     * @return
     */
    @Override
    public List<NytMateralInfoEntity> materalAnalytical(String strXml, String pid) {
        //定义变量
        List<NytMateralInfoEntity> resultList = new ArrayList<NytMateralInfoEntity>();
        if (strXml.isEmpty()){
            return  resultList;
        }
        SAXReader reader = new SAXReader();
        Document document = null;
        try {
            document = reader.read(new ByteArrayInputStream(strXml.getBytes()));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        String changeFlag = ""; //变化标识 0：未变化，1：变化
        String populartypes = "";
        String conditions = "";
        List<Element> materals = new ArrayList<Element>(); //材料
        /**
         * 节点对象的操作方法
         */
        //获取文档根节点
        Element root = document.getRootElement();
        changeFlag = root.element("CHANGE_FLAG").getTextTrim();
        populartypes = root.element("POPULARTYPES").getTextTrim();
        conditions = root.element("CONDITIONS").getTextTrim();

        materals = root.element("MATERIALS").elements();
        for (Element e : materals){
            NytMateralInfoEntity materal = new NytMateralInfoEntity();
            materal.setpId(pid);
            materal.setChangeFlag(changeFlag);
            materal.setPopulartypes(populartypes);
            materal.setConditions(conditions);
            materal.setMaterialGuid(e.element("MATERIALGUID").getTextTrim());
            materal.setMaterialName(e.element("NAME").getTextTrim());
            materal.setMaterialFormat(e.element("FORMAT").getTextTrim());
            materal.setMaterialDetail(e.element("DETAIL_REQUIREMENT").getTextTrim());
            materal.setNecessity(e.element("NECESSITY").getTextTrim());
            materal.setNecessityDesc(e.element("NECESSITY_DESC").getTextTrim());
            materal.setMaterialRes(e.element("MATERIAL_RES").getTextTrim());
            materal.setIsRq(e.element("IS_RQ").getTextTrim());
            materal.setIspublish(e.element("ispublish").getTextTrim());
            materal.setFilenamek(e.element("EMPTYTABLE").element("FILENAME").getTextTrim());
            materal.setFilecontentk(e.element("EMPTYTABLE").element("FILECONTENT").getTextTrim());
            materal.setFileurlk(e.element("EMPTYTABLE").element("FILEURL").getTextTrim());
            materal.setFilename(e.element("EXAMPLETABLE").element("FILENAME").getTextTrim());
            materal.setFilecontent(e.element("EXAMPLETABLE").element("FILECONTENT").getTextTrim());
            materal.setFileurl(e.element("EXAMPLETABLE").element("FILEURL").getTextTrim());
            materal.setBak(e.element("BAK").getTextTrim());
            materal.setMaterialType(e.element("MATERIAL_TYPE").getTextTrim());
            materal.setSourceType(e.element("SOURCE_TYPE").getTextTrim());
            materal.setSourceExplain(e.element("SOURCE_EXPLAIN").getTextTrim());
            materal.setPageNum(e.element("PAGE_NUM").getTextTrim());
            materal.setPageFormat(e.element("PAGE_FORMAT").getTextTrim());
            materal.setFillExplian(e.element("FILL_EXPLIAN").getTextTrim());
            materal.setAcceptStand(e.element("ACCEPT_STAND").getTextTrim());
            materal.setByLaw(e.element("BY_LAW").getTextTrim());
            materal.setOrdernum(Integer.parseInt(e.element("ORDERNUM").getTextTrim()));
            materal.setCreateTime(new Date());
            materal.setUpdateTime(new Date());
            materal.setIsDelete("0");
            resultList.add(materal);
        }
        return resultList;
    }

    /**
     * 收费项目格式说明
     * @param strXml
     * @return
     */
    @Override
    public List<NytChargeitemInfoEntity> chargeitmeAnalytical(String strXml, String pid) {
        //定义变量
        List<NytChargeitemInfoEntity> resultList = new ArrayList<NytChargeitemInfoEntity>();
        if (strXml.isEmpty()){
            return  resultList;
        }
        SAXReader reader = new SAXReader();
        Document document = null;
        try {
            document = reader.read(new ByteArrayInputStream(strXml.getBytes()));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        String changeFlag = ""; //变化标识 0：未变化，1：变化
        List<Element> chargeitems = new ArrayList<Element>(); //收费项目集合
        /**
         * 节点对象的操作方法
         */
        //获取文档根节点
        Element root = document.getRootElement();
        changeFlag = root.element("CHANGE_FLAG").getTextTrim();
        chargeitems = root.element("CHARGEITEMS").elements();
        for (Element e : chargeitems){
            NytChargeitemInfoEntity chargeitem = new NytChargeitemInfoEntity();
            chargeitem.setpId(pid);
            chargeitem.setChangeFlag(changeFlag);
            chargeitem.setChargeitemGuid(e.element("CHARGEITEMGUID").getTextTrim());
            chargeitem.setChargeitemName(e.element("NAME").getTextTrim());
            chargeitem.setChargeitemBasis(e.element("BASIS").getTextTrim());
            chargeitem.setReductionDesc(e.element("REDUCTION_DESC").getTextTrim());
            chargeitem.setOrdernum(Integer.parseInt(e.element("ORDERNUM").getTextTrim()));
            chargeitem.setCreateTime(new Date());
            chargeitem.setUpdateTime(new Date());
            chargeitem.setIsDelete("0");
            resultList.add(chargeitem);
        }
        return resultList;
    }

    /**
     * 常见问题解答格式说明XML解析
     * @param strXml
     * @return
     */
    @Override
    public List<NytQaInfoEntity> qaAnalytical(String strXml, String pid) {
        //定义变量
        List<NytQaInfoEntity> resultList = new ArrayList<NytQaInfoEntity>();
        if (strXml.isEmpty()){
            return  resultList;
        }
        SAXReader reader = new SAXReader();
        Document document = null;
        try {
            document = reader.read(new ByteArrayInputStream(strXml.getBytes()));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        String changeFlag = ""; //变化标识 0：未变化，1：变化
        List<Element> qas = new ArrayList<Element>(); //问题集合
        /**
         * 节点对象的操作方法
         */
        //获取文档根节点
        Element root = document.getRootElement();
        changeFlag = root.element("CHANGE_FLAG").getTextTrim();
        qas = root.element("QAS").elements();
        for (Element e : qas){
            NytQaInfoEntity qa = new NytQaInfoEntity();
            qa.setpId(pid);
            qa.setChangeFlag(changeFlag);
            qa.setQuestion(e.element("QUESTION").getTextTrim());
            qa.setAnswer(e.element("ANSWER").getTextTrim());
            qa.setOrdernum(Integer.parseInt(e.element("ORDERNUM").getTextTrim()));
            qa.setCreateTime(new Date());
            qa.setUpdateTime(new Date());
            qa.setIsDelete("0");
            resultList.add(qa);
        }
        return resultList;
    }


    /**
     * 自由裁量格式说明XML解析
     * @param strXml
     * @return
     */
    @Override
    public List<NytFactInfoEntity> factAnalytical(String strXml, String pid) {
        //定义变量
        List<NytFactInfoEntity> resultList = new ArrayList<NytFactInfoEntity>();
        if (strXml.isEmpty()){
            return  resultList;
        }
        SAXReader reader = new SAXReader();
        Document document = null;
        try {
            document = reader.read(new ByteArrayInputStream(strXml.getBytes()));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        String changeFlag = ""; //变化标识 0：未变化，1：变化
        String factdescription = ""; //情节描述
        String law = ""; //处罚措施
        List<Element> facts = new ArrayList<Element>();
        /**
         * 节点对象的操作方法
         */
        //获取文档根节点
        Element root = document.getRootElement();
        changeFlag = root.element("CHANGE_FLAG").getTextTrim();
        facts = root.element("FACTS").elements();
        for (Element e : facts){
            NytFactInfoEntity fact = new NytFactInfoEntity();
            fact.setpId(pid);
            fact.setChangeFlag(changeFlag);
            fact.setFactdescription(e.element("FACTDESCRIPTION").getTextTrim());
            fact.setLaw(e.element("LAW").getTextTrim());
            fact.setCreateTime(new Date());
            fact.setUpdateTime(new Date());
            fact.setIsDelete("0");
            resultList.add(fact);
        }
        return resultList;
    }

    /**
     * 受理地点格式说明XML解析
     * @param strXml
     * @return
     */
    @Override
    public List<NytAddressInfoEntity> addressAnalytical(String strXml, String pid) {
        //定义变量
        List<NytAddressInfoEntity> resultList = new ArrayList<NytAddressInfoEntity>();
        if (strXml.isEmpty()){
            return  resultList;
        }
        SAXReader reader = new SAXReader();
        Document document = null;
        try {
            document = reader.read(new ByteArrayInputStream(strXml.getBytes()));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        String changeFlag = ""; //变化标识 0：未变化，1：变化
        List<Element> addresss = new ArrayList<Element>(); //问题集合
        /**
         * 节点对象的操作方法
         */
        //获取文档根节点
        Element root = document.getRootElement();
        changeFlag = root.element("CHANGE_FLAG").getTextTrim();
        addresss = root.element("ACCEPT_ADDRESSS").elements();
        for (Element e : addresss){
            NytAddressInfoEntity address = new NytAddressInfoEntity();
            address.setpId(pid);
            address.setChangeFlag(changeFlag);
            address.setAddress(e.element("ADDRESS").getTextTrim());
            address.setAddressKind(e.element("ADDRESS_KIND").getTextTrim());
            address.setAcceptTimedesc(e.element("ACCEPT_TIMEDESC").getTextTrim());
            address.setPhone(e.element("PHONE").getTextTrim());
            address.setUuid(e.element("UUID").getTextTrim());
            address.setCreateTime(new Date());
            address.setUpdateTime(new Date());
            address.setIsDelete("0");
            resultList.add(address);
        }
        return resultList;
    }

    /**
     * 相关附件信息XML解析
     * @param strXml
     * @return
     */
    @Override
    public List<NytRelatedInfoEntity> relatedAnalytical(String strXml, String pid) {
        //定义变量
        List<NytRelatedInfoEntity> resultList = new ArrayList<NytRelatedInfoEntity>();
        if (strXml.isEmpty()){
            return  resultList;
        }
        SAXReader reader = new SAXReader();
        Document document = null;
        try {
            document = reader.read(new ByteArrayInputStream(strXml.getBytes()));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        List<Element> relateds = new ArrayList<Element>(); //附件集合
        /**
         * 节点对象的操作方法
         */
        //获取文档根节点
        Element root = document.getRootElement();
        relateds = root.element("RELATEDS").elements();
        for (Element e : relateds){
            NytRelatedInfoEntity related = new NytRelatedInfoEntity();
            related.setpId(pid);
            related.setFilename(e.element("FILENAME").getTextTrim());
            related.setFileurl(e.element("FILEURL").getTextTrim());
            related.setCreateTime(new Date());
            related.setUpdateTime(new Date());
            related.setIsDelete("0");
            resultList.add(related);
        }
        return resultList;
    }
}
