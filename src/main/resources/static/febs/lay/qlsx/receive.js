/**
 * 环节弹窗相关js
 */
layui.define(['jquery', 'form', 'table', 'febs', 'laydate', 'layer'], function(exports){
    var $ = layui.jquery,
        form = layui.form,
        table = layui.table,
        febs = layui.febs,
        laydate = layui.laydate,
        upload = layui.upload,
        attrIns,
        historyIns;
    form.render();
    form.render('select');

    Receive = function () {}
    /* 打开弹窗成功加载事件*/
    Receive.prototype.openSuccess = function openSuccess(projid) {
        $.ajax({
            type: 'get',
            url: ctx + 'apasinfo/getById?projid=' + projid,
            dataType: 'json',
            success: function (result) {
                // 基本信息
                var apasin = result.data;
                form.val("apasinfo", {
                    "projid": apasin.projid,
                    "createTime": apasin.createTime,
                    "syncStatus": apasin.syncStatus,
                    "dataversion": apasin.dataversion,
                    "servicecode": apasin.servicecode,
                    "serviceDeptid": apasin.serviceDeptid,
                    "xkLydw": apasin.xkLydw,
                    "xkLydwdm": apasin.xkLydwdm,
                    "tongid": apasin.tongid,
                    "busType": apasin.busType,
                    "deptname": apasin.deptname,
                    "applyPropertiy": apasin.applyPropertiy,
                    "applyfrom": apasin.applyfrom,
                    "servicename": apasin.servicename,
                    "projectname": apasin.projectname,
                    "applyname": apasin.applyname,
                    "approveType": apasin.approveType,
                    "applyCardtype": apasin.applyCardtype,
                    "applyCardnumber": apasin.applyCardnumber,
                    "legalman": apasin.legalman,
                    "xkFrSfzh": apasin.xkFrSfzh,
                    "contactman": apasin.contactman,
                    "telphone": apasin.telphone,
                    "dtelphone": apasin.dtelphone,
                    "contactmanCardnumber": apasin.contactmanCardnumber,
                    "postcode": apasin.postcode,
                    "address": apasin.address,
                    "extend2": apasin.extend2,
                    "projnum": apasin.projnum,
                    "applyType": apasin.applyType,
                    "isTouzip": apasin.isTouzip,
                    "busModeDesc": apasin.busModeDesc,
                    "anticipateType": apasin.anticipateType,
                    "promiseDay": apasin.promiseDay,
                    "promiseDay1": apasin.promiseDay + getDayType(apasin.anticipateType),
                });
                initAttr(apasin.tongid, apasin.projid);
                // 申报者信息
                form.val("smsg", {
                    "isTouzip2": apasin.isTouzip,
                    "applyPropertiy2": apasin.applyPropertiy,
                    "applyname2": apasin.applyname,
                    "legalman2": apasin.legalman,
                    "applyCardtype2": apasin.applyCardtype,
                    "applyCardnumber2": apasin.applyCardnumber,
                    "contactman2": apasin.contactman,
                    "telphone2": apasin.telphone,
                    "postcode2": apasin.postcode,
                    "address2": apasin.address,
                });
                // 申报历史记录
                initHistory(apasin.applyCardnumber);
            }
        });
    }

    function getDayType(type) {
        switch (type) {
            case '1':
                return '工作日';
                break;
            case '2':
                return '月';
                break;
            case '3':
                return '年';
                break;
            case '4':
                return '天';
                break;
            default:
                return '';
                break;
        }
    }

    /*加载相关材料数据*/
    function initAttr(tongId, projid) {
        attrIns = febs.table.init({
            elem: '#configureAttr',
            id: "configureAttr",
            url: ctx + 'attr/getByTongId?tongId=' + tongId + '&projid=' + projid,
            cellMinWidth: 80,
            page: false,
            cols: [[
                {field: 'unid', title: '<strong>主键</strong>', hide: true,templet: function (d) {
                        var hide = '<input type="hidden" name="unid" lay-verify="title" value="' + d.unid + '" autocomplete="off" class="layui-input">';
                        hide += '<input type="hidden" name="projid" lay-verify="title" value="' + d.projid + '" autocomplete="off" class="layui-input">';
                        hide += '<input type="hidden" name="attrname" lay-verify="title" value="' + d.attrname + '" autocomplete="off" class="layui-input">';
                        hide += '<input type="hidden" name="attrid" lay-verify="title" value="' + d.attrid + '" autocomplete="off" class="layui-input">';
                        return hide;
                    }},
                {type: 'numbers', title: '<strong>序号</strong>', width: '7%', align: 'center'},
                {field: 'attrname', title: '<strong>材料名称</strong>', width: '29%', align: 'center'},
                {field: 'amount', title: '<strong>份数</strong>', width: '7%', templet: '#amount', align: 'center'},
                {field: 'memo', title: '<strong>备注</strong>', width: '15%', templet: '#memo', align: 'center'},
                {field: 'taketype', title: '<strong>收取方式</strong>', width: '13%', templet: '#taketype', align: 'center'},
                {
                    field: 'attrname', title: '<strong>文件操作</strong>', width: '30%', templet: function (d) {
                        var list = d.list,
                            ids = '';
                        var html = '';
                        for (var i in list) {
                            html += '<p>';
                            html += '   <a href="' + list[i].filepath + '">' + list[i].name + '</a>';
                            html += '   <button onclick="deleteFileByUNID(\'' + list[i].unid + '\',\'' + d.tongid + '\',\'' + d.projid + '\')" class="layui-btn layui-btn-xs layui-btn-danger demo-delete">删除</button>';
                            html += '</p>';
                            if (i == 0) {
                                ids += list[i].id;
                            } else {
                                ids += ',' + list[i].id
                            }
                        }
                        html += '<input type="hidden" name="extend" lay-verify="title" value="' + ids + '" autocomplete="off" class="layui-input">';
                        return html;
                    }
                }
            ]]
        });
    }

    /*加载申报历史记录数据*/
    function initHistory(cardId) {
        historyIns = febs.table.init({
            elem: '#history',
            id: "history",
            url: ctx + 'apasinfo/getByCardId?cardId=' + cardId,
            page: false,
            cols: [[
                {type: 'numbers', title: '<strong>序号</strong>', width: '6%', align: 'center'},
                {field: 'projectname', title: '<strong>项目名称</strong>', width: '25%', align: 'center'},
                {field: 'servicename', title: '<strong>审批事项</strong>', width: '24%', align: 'center'},
                {field: 'projid', title: '<strong>申报号</strong>', width: '20%', align: 'center'},
                {field: 'receivetime', title: '<strong>申报时间</strong>', width: '16%', align: 'center'},
                {
                    title: '操作', width: '10%', align: 'center', templet: function (d) {
                        return "<a onclick=\"look('" + d.projid + "')\" class=\"layui-btn layui-btn-xs\">查看</a>";
                    }
                }
            ]]
        });
    }

    // 点击材料删除按钮时删除材料
    deleteFileByUNID = function (unid, tongid, projid) {
        layer.msg('删除成功')
        if (confirm('确定要删除吗？')) {
            $.ajax({
                type: 'get',
                url: ctx + 'formfile/deleteByUUID?unid=' + unid,
                dataType: 'json',
                success: function (result) {
                    initAttr(tongid, projid);
                }
            })
        }
    }


    look = function (projid) {
        layer.msg("查看历史记录详情" + projid);
    }

    var receive = new Receive();
    exports('receive', receive);

})