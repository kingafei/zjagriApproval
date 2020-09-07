package cn.com.ecenter.xzspxt.service.impl;

import cn.com.ecenter.xzspxt.entity.*;
import cn.com.ecenter.xzspxt.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 定时器调用的数据同步类
 */
@Service
public class DataSyschroServiceImpl implements DataSyschroService {

    @Autowired
    private QltQlsxService qltQlsxService;

    @Autowired
    private NytAddressInfoService nytAddressInfoService;

    @Autowired
    private NytChargeitemInfoService nytChargeitemInfoService;

    @Autowired
    private NytFactInfoService nytFactInfoService;

    @Autowired
    private NytFileInfoService nytFileInfoService;

    @Autowired
    private NytFlowInfoService nytFlowInfoService;

    @Autowired
    private NytMateralInfoService nytMateralInfoService;

    @Autowired
    private NytQaInfoService nytQaInfoService;

    @Autowired
    private NytQlsxService nytQlsxService;

    @Autowired
    private NytRelatedInfoService nytRelatedInfoService;

    /**
     * 定时器调用的数据同步方法
     * @param size
     */
    @Override
    public void dataSyschro(String size) {
        int total = 327196;//qltQlsxService.fingTotal();
        int pageIndex = 0;
        int pageSize = size.isEmpty() ? 1000 : Integer.valueOf(size);
        do{
            List<QltQlsxEntity> result = qltQlsxService.findQlsx(pageIndex * pageSize, pageSize);
            pageIndex++;
            for (QltQlsxEntity ql : result) {
                // 主表数据转换与写入
                NytQlsxEntity qls = oldToNew(ql);
                nytQlsxService.add(qls);
                String rowGuid = qls.getRowguid() + "_" +qls.getId(); // 主键和子表的外键关联
                Long tongid = qls.getTongid();
                // 解析内部流程信息格式XML并保存到子表
                String flowInfoXmlStr = ql.getInFlowInfo();
                if (null != flowInfoXmlStr) {
                    Map<String, Object> map = qltQlsxService.flowAnalytical(flowInfoXmlStr, rowGuid, tongid);
                    List<NytFlowInfoEntity> flowList = (List<NytFlowInfoEntity>) map.get("flowList");
                    List<NytFileInfoEntity> fileList = (List<NytFileInfoEntity>) map.get("fileList");
                    if (flowList.size() > 0) {
                        nytFlowInfoService.add(flowList);
                    }
                    if (fileList.size() > 0) {
                        nytFileInfoService.add(fileList);
                    }
                }
                // 申报材料格式XML并保存到子表
                String materalInfoXmlStr = ql.getMaterialInfo();
                if (null != materalInfoXmlStr) {
                    List<NytMateralInfoEntity> materalList = qltQlsxService.materalAnalytical(materalInfoXmlStr, rowGuid, tongid);
                    if (materalList.size() > 0) {
                        nytMateralInfoService.add(materalList);
                    }

                }
                // 收费项目格式XML并保存到子表
                String chargeitemInfoXmlStr = ql.getChargeitemInfo();
                if (null != chargeitemInfoXmlStr) {
                    List<NytChargeitemInfoEntity> chargeitemList = qltQlsxService.chargeitmeAnalytical(chargeitemInfoXmlStr, rowGuid, tongid);
                    if (chargeitemList.size() > 0) {
                        nytChargeitemInfoService.add(chargeitemList);
                    }
                }
                // 解析常见问题解答格式XML并保存到子表
                String qaInfoXmlStr = ql.getQaInfo();
                if (null != qaInfoXmlStr) {
                    List<NytQaInfoEntity> qaList = qltQlsxService.qaAnalytical(qaInfoXmlStr, rowGuid, tongid);
                    if (qaList.size() > 0) {
                        nytQaInfoService.add(qaList);
                    }
                }
                // 自由裁量格式XML并保存到子表
                String factXmlStr = ql.getFactInfo();
                if (null != factXmlStr) {
                    List<NytFactInfoEntity> factList = qltQlsxService.factAnalytical(factXmlStr, rowGuid, tongid);
                    if (factList.size() > 0) {
                        nytFactInfoService.add(factList);
                    }
                }
                // 受理地点XML并保存到子表
                String addressXmlStr = ql.getAcceptAddressInfo();
                if (null != addressXmlStr) {
                    List<NytAddressInfoEntity> addressList = qltQlsxService.addressAnalytical(addressXmlStr, rowGuid, tongid);
                    if (addressList.size() > 0) {
                        nytAddressInfoService.add(addressList);
                    }
                }
                // 相关附件信息格式表XML并保存到子表
                String relatedXmlStr = ql.getRelated();
                if (null != relatedXmlStr) {
                    List<NytRelatedInfoEntity> relatedList = qltQlsxService.relatedAnalytical(relatedXmlStr, rowGuid, tongid);
                    if (relatedList.size() > 0) {
                        nytRelatedInfoService.add(relatedList);
                    }
                }
            }
        } while ((pageSize * pageIndex) < total); // 当查询的起始条数大于总条数时跳出循环
    }


    private NytQlsxEntity oldToNew(QltQlsxEntity q) {
        NytQlsxEntity n = new NytQlsxEntity();
        n.setRowguid(q.getRowguid());
        n.setUpdateDate(q.getUpdateDate());
        n.setUpdateType(q.getUpdateType());
        n.setQlKind(q.getQlKind());
        n.setQlAtt(q.getQlAtt());
        n.setQlFullId(q.getQlFullId());
        n.setQlMainitemId(q.getQlMainitemId());
        n.setQlSubitemId(q.getQlSubitemId());
        n.setShiquancj(q.getShiquancj());
        n.setBelongxiaqucode(q.getBelongxiaqucode());
        n.setQlDepOrgcdoe(q.getQlDepOrgcdoe());
        n.setOuorgcode(q.getOuorgcode());
        n.setOuguid(q.getOuguid());
        n.setItemsource(q.getItemsource());
        n.setItemsourcetype(q.getItemsourcetype());
        n.setEntrust(q.getEntrust());
        n.setEntrustdes(q.getEntrustdes());
        n.setVersionNumber(q.getVersionNumber());
        n.setVersionDate(q.getVersionDate());
        n.setQlEffectTime(q.getQlEffectTime());
        n.setQlInnerCode(q.getQlInnerCode());
        n.setQlName(q.getQlName());
        n.setQlState(q.getQlState());
        n.setLawbasis(q.getLawbasis());
        n.setAnticipateDay(q.getAnticipateDay());
        n.setAnticipateType(q.getAnticipateType());
        n.setPromiseDay(q.getPromiseDay());
        n.setFeebasis(q.getFeebasis());
        n.setIsLevywaiver(q.getIsLevywaiver());
        n.setApplyerminCount(q.getApplyerminCount());
        n.setIsPilot(q.getIsPilot());
        n.setIsSimplepunish(q.getIsSimplepunish());
        n.setQlDep(q.getQlDep());
        n.setAcpInstitution(q.getAcpInstitution());
        n.setDecInstitution(q.getDecInstitution());
        n.setLeadDept(q.getLeadDept());
        n.setBjtype(q.getBjtype());
        n.setBenjispxz(q.getBenjispxz());
        n.setHandleFrequency(q.getHandleFrequency());
        n.setContentInvolve(q.getContentInvolve());
        n.setXingzhenxdrxz(q.getXingzhenxdrxz());
        n.setApplicableObject(q.getApplicableObject());
        n.setXingzhenxdrxy(q.getXingzhenxdrxy());
        n.setApplyCondition(q.getApplyCondition());
        n.setCountLimit(q.getCountLimit());
        n.setCountNote(q.getCountNote());
        n.setBanRequirement(q.getBanRequirement());
        n.setShixiangsctype(q.getShixiangsctype());
        n.setShixiangsclx(q.getShixiangsclx());
        n.setBanjianFinishfiles(q.getBanjianFinishfiles());
        n.setLinkTel(q.getLinkTel());
        n.setSuperviseTel(q.getSuperviseTel());
        n.setApplyType(q.getApplyType());
        n.setApplyTypeTel(q.getApplyTypeTel());
        n.setApplyTypeMail(q.getApplyTypeMail());
        n.setApplyTypeFax(q.getApplyTypeFax());
        n.setHandleType(q.getHandleType());
        n.setWebapplyurl(q.getWebapplyurl());
        n.setWebconsulturl(q.getWebconsulturl());
        n.setMbfarenadd(q.getMbfarenadd());
        n.setMbgerenflag(q.getMbgerenflag());
        n.setNosuitApply(q.getNosuitApply());
        n.setIsUnifydo(q.getIsUnifydo());
        n.setIsUpunify(q.getIsUpunify());
        n.setIsCs(q.getIsCs());
        n.setIsVlb(q.getIsVlb());
        n.setUnifydodes(q.getUnifydodes());
        n.setWebapplymode(q.getWebapplymode());
        n.setChargeFlag(q.getChargeFlag());
        n.setChargeBasis(q.getChargeBasis());
        n.setHangyeclasstype(q.getHangyeclasstype());
        n.setRightclassQiyezt(q.getRightclassQiyezt());
        n.setRightclassQiyedx(q.getRightclassQiyedx());
        n.setRightclassGerendx(q.getRightclassGerendx());
        n.setRightclassGerensx(q.getRightclassGerensx());
        n.setQlSubKind(q.getQlSubKind());
        n.setImFlowUrl(q.getImFlowUrl());
        n.setOutFlowUrl(q.getOutFlowUrl());
        n.setFarenurl(q.getFarenurl());
        n.setServiceMode(q.getServiceMode());
        n.setServiceDay(q.getServiceDay());
        n.setDestime(q.getDestime());
        n.setGerenflag(q.getGerenflag());
        n.setIsTongjian(q.getIsTongjian());
        n.setQlInnerCodeItem(q.getQlInnerCodeItem());
        n.setApplyerminCountDesc(q.getApplyerminCountDesc());
        n.setOutypecode(q.getOutypecode());
        n.setOutFlowDesc(q.getOutFlowDesc());
        n.setBanjianFinishtype(q.getBanjianFinishtype());
        n.setIsSpecialpro(q.getIsSpecialpro());
        n.setBaknote(q.getBaknote());
        n.setState2(q.getState2());
        n.setSyncDate(q.getSyncDate());
        n.setSyncErrorDesc(q.getSyncErrorDesc());
        n.setAppwebapplyurl(q.getAppwebapplyurl());
        n.setAppappointmenturl(q.getAppappointmenturl());
        n.setAppointmenturl(q.getAppointmenturl());
        n.setIsWebappointment(q.getIsWebappointment());
        n.setWebappointmentperiod(q.getWebappointmentperiod());
        n.setMaincontext(q.getMaincontext());
        n.setDoDept(q.getDoDept());
        n.setRelatedguid(q.getRelatedguid());
        n.setIsExpress(q.getIsExpress());
        n.setIspyc(q.getIspyc());
        n.setLbsx(q.getLbsx());
        n.setServiceSubKind(q.getServiceSubKind());
        n.setBak1(q.getBak1());
        n.setBak2(q.getBak2());
        n.setBak3(q.getBak3());
        n.setBak4(q.getBak4());
        n.setBak5(q.getBak5());
        n.setBak6(q.getBak6());
        n.setBak7(q.getBak7());
        n.setBak8(q.getBak8());
        n.setBak9(q.getBak9());
        n.setBak10(q.getBak10());
        n.setBak12(q.getBak12());
        n.setBak13(q.getBak13());
        n.setOp(q.getOp());
        n.setTongTime(q.getTongTime());
        n.setSendtime(q.getSendtime());
        n.setTongid(q.getTongid());
        n.setIsTouzip(q.getIsTouzip());
        n.setNounifyDo(q.getNounifyDo());
        n.setUnunifydoOther(q.getUnunifydoOther());
        n.setIshasownflow(q.getIshasownflow());
        n.setBsznUrl(q.getBsznUrl());
        n.setNosuitReasondesc(q.getNosuitReasondesc());
        n.setBusinessRegulate(q.getBusinessRegulate());
        return n;
    }

}
